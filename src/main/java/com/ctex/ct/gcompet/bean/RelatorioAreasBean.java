/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template report, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.modelo.Areas;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioAreasEmpresas;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioAreasProjetos;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioCapacidadesAreas;
import java.io.Serializable;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;

/**
 *
 * @author ralfh
 */
@ManagedBean(name = "relatorioAreasCapacidades")
@RequestScoped
public class RelatorioAreasBean implements Serializable {
    
    private String jasperAreasCapacidades;
    private String relatorio;
    private ExternalContext context;
    private JRDataSource jrDataSourceMainReport;
    private JRDataSource jrDataSourceSubReport1;
    private JRDataSource jrDataSourceSubReport2;
    private JasperPrint jasperPrintAreasCapacidades;
    private JasperPrint jasperPrintProjetosAreas;
    private String relatorioAC;
    private Areas area;
    //Array do Conteudo principal - Capacidades por Area selecionada
    private RelatorioCapacidadesAreas[] arrayCapacidadesPorArea;
    //Array para o Subrelatório Projetos
    private RelatorioAreasProjetos[] arrayProjetosPorArea;
    //Array para o Subrelatorio Empresas
    private RelatorioAreasEmpresas[] arrayEmpresasPorArea;

    @EJB 
    private RelatorioCapacidadesFacade ejbRACFacade;
    @EJB 
    private RelatorioAreasFacade ejbRCAFacade;
    @EJB
    private RelatorioProjetosFacade ejbRAPFacade;
    @EJB
    private RelatorioEmpresasFacade ejbRAEFacade;

 
    /**
     * Creates a new instance of RelatorioDivisao
     */
    public RelatorioAreasBean() { 
    }    

    /**
     * @return the jasperAreasCapacidades
     */
    public String getJasperAreasCapacidades() {
        jasperAreasCapacidades = getContext().getRealPath("user/relatorios/areas/RelatorioAreaCapacidades.jasper");
        return jasperAreasCapacidades;
    }

    /**
     * @param jasperAreasCapacidades the jasperAreasCapacidades to set
     */
    public void setJasperAreasCapacidades(String jasperAreasCapacidades) {
        this.jasperAreasCapacidades = jasperAreasCapacidades;
    }

    /**
     * @return the context
     */
    public ExternalContext getContext() {
        context = FacesContext.getCurrentInstance().getExternalContext();
        return context;
    }

    /**
     * @param context the context to set
     */
    public void setContext(ExternalContext context) {
        this.context = context;
    }
    
    
    public JRDataSource getJrDataSourceMainReport() {        
        jrDataSourceMainReport = new JRBeanArrayDataSource(getArrayAreasCapacidadesPorPeso());
        return jrDataSourceMainReport;
    }
    
    /**
     * @return the jrDataSourceSubReport1
     */
    public JRDataSource getJrDataSourceSubReport1() {
//        jrDataSourceSubReport1 = new JRBeanArrayDataSource(getArrayAreasProjetosArea());
        jrDataSourceSubReport1 = new JRBeanArrayDataSource(getArrayProjetosPorArea());
        return jrDataSourceSubReport1;
    }

    /**
     * @param jrDataSourceSubReport1 the jrDataSourceSubReport1 to set
     */
    public void setJrDataSourceSubReport1(JRDataSource jrDataSourceSubReport1) {
        this.jrDataSourceSubReport1 = jrDataSourceSubReport1;
    }

    /**
     * @return the jrDataSourceSubReport2
     */
    public JRDataSource getJrDataSourceSubReport2() {
        //jrDataSourceSubReport2 = new JRBeanArrayDataSource(getArrayAreasEmpresasArea());
        jrDataSourceSubReport2 = new JRBeanArrayDataSource(getArrayEmpresasPorArea());
        return jrDataSourceSubReport2;
    }

    /**
     * @param jrDataSourceSubReport2 the jrDataSourceSubReport2 to set
     */
    public void setJrDataSourceSubReport2(JRDataSource jrDataSourceSubReport2) {
        this.jrDataSourceSubReport2 = jrDataSourceSubReport2;
    }
    
    //Array com as Capacidades Operativas relacionadas a uma determinada área selecionada
    //Ordenação decrescente do numero de avaliações positivas
    public RelatorioCapacidadesAreas[] getArrayAreasCapacidadesPorPeso() {
        arrayCapacidadesPorArea = ejbRACFacade.findAllCapacidadesPorArea(area,"peso");
        return arrayCapacidadesPorArea;
    }
    
    /**
     * @return the arrayProjetosPorArea  OKOKOK
     */
    public RelatorioAreasProjetos[] getArrayProjetosPorArea() {
        arrayProjetosPorArea = ejbRAPFacade.findAllProjetosPorArea(area,"projeto");    
        return arrayProjetosPorArea;
    }

    /**
     * @param arrayProjetosPorArea the arrayProjetosPorArea to set
     */
    public void setArrayProjetosPorArea(RelatorioAreasProjetos[] arrayProjetosPorArea) {
        this.arrayProjetosPorArea = arrayProjetosPorArea;
    }
    
    /**
     * @return the arrayEmpresasPorArea    OKOKOK
     */
    public RelatorioAreasEmpresas[] getArrayEmpresasPorArea() {
        arrayEmpresasPorArea = ejbRAEFacade.findAllEmpresasPorArea(area,"peso");
        return arrayEmpresasPorArea;
    }

    /**
     * @param arrayEmpresasPorArea the arrayEmpresasPorArea to set
     */
    public void setArrayEmpresasPorArea(RelatorioAreasEmpresas[] arrayEmpresasPorArea) {
        this.arrayEmpresasPorArea = arrayEmpresasPorArea;
    }
    
    
    /**
     * @return the jasperPrintAreasCapacidades
     */
    public JasperPrint getJasperPrintAreasCapacidades() {  
        ImageIcon logotipo = new ImageIcon(getContext().getRealPath("resources/img/logo-ctex.png"));                
        HashMap hm = new HashMap<>();
        hm.put("par_logotipo",logotipo.getImage());        
        hm.put("par_nomerelat","Avaliação de Areas de Pesquisa: "+area.getNome().toUpperCase());  
        hm.put("par_dados_projetos", getJrDataSourceSubReport1());
        hm.put("par_dados_empresas", getJrDataSourceSubReport2());
        try {   
            jasperPrintAreasCapacidades = JasperFillManager.fillReport(getJasperAreasCapacidades(),hm,getJrDataSourceMainReport());
        } catch (JRException ex) {
            Logger.getLogger(RelatorioAreasBean.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return jasperPrintAreasCapacidades;
    }

    /**
     * @param jasperPrintAreasCapacidades the jasperPrintAreasCapacidades to set
     */
    public void setJasperPrintAreasCapacidades(JasperPrint jasperPrintAreasCapacidades) {
        this.jasperPrintAreasCapacidades = jasperPrintAreasCapacidades;
    }
    
    /**
     * @return the relatorio
     */
    public String getRelatorio() {        
        relatorio = "/reports/area/AreasProjetos.pdf";
        return relatorio;
    }

    /**
     * @param relatorio the relatorio to set
     */
    public void setRelatorio(String relatorio) {
        this.relatorio = relatorio;
    }

    /**
     * @return the ejbRCAFacade
     */
    public RelatorioAreasFacade getEjbRCAFacade() {
        return ejbRCAFacade;
    }

    /**
     * @param ejbRCAFacade the ejbRCAFacade to set
     */
    public void setEjbRCAFacade(RelatorioAreasFacade ejbRCAFacade) {
        this.ejbRCAFacade = ejbRCAFacade;
    }

    /**
     * @return the relatorioAC
     */
    public String getRelatorioAC() {
        relatorio = "/reports/area/AreasProjetos.pdf";
        try {                
            JasperExportManager.exportReportToPdfFile(getJasperPrintAreasCapacidades(), getContext().getRealPath(relatorio));
        } catch (JRException ex) {
            Logger.getLogger(RelatorioAreasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        relatorioAC = "/user/relatorios/areas/AreasCapacidades";
        return relatorioAC;
    }

    /**
     * @param relatorioAC the relatorioAC to set
     */
    public void setRelatorioAC(String relatorioAC) {
        this.relatorioAC = relatorioAC;
    }

    /**
     * @return the area
     */
    public Areas getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(Areas area) {
        this.area = area;
    }

    /**
     * @return the jasperPrintProjetosAreas
     */
    public JasperPrint getJasperPrintProjetosAreas() {
        return jasperPrintProjetosAreas;
    }

    /**
     * @param jasperPrintProjetosAreas the jasperPrintProjetosAreas to set
     */
    public void setJasperPrintProjetosAreas(JasperPrint jasperPrintProjetosAreas) {
        this.jasperPrintProjetosAreas = jasperPrintProjetosAreas;
    }

    /**
     * @return the ejbRACFacade
     */
    public RelatorioCapacidadesFacade getEjbRACFacade() {
        return ejbRACFacade;
    }

    /**
     * @param ejbRACFacade the ejbRACFacade to set
     */
    public void setEjbRACFacade(RelatorioCapacidadesFacade ejbRACFacade) {
        this.ejbRACFacade = ejbRACFacade;
    }

}
