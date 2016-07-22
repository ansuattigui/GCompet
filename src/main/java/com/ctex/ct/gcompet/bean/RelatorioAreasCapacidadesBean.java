/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template report, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.modelo.Areas;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioAreasCapacidades;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioAreasEmpresas;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioAreasProjetos;
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
public class RelatorioAreasCapacidadesBean implements Serializable {
    
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
    private RelatorioAreasProjetos[] arrayAreasProjetos;
    private RelatorioAreasCapacidades[] arrayAreasCapacidades;
    private RelatorioAreasEmpresas[] arrayAreasEmpresas;
    private RelatorioAreasProjetos[] arrayAreasProjetosArea;
    private RelatorioAreasEmpresas[] arrayAreasEmpresasArea;

    @EJB 
    private RelatorioAreasCapacidadesFacade ejbRACFacade;
    @EJB 
    private RelatorioCapacidadesAreasFacade ejbRCAFacade;
    @EJB
    private RelatorioAreasProjetosFacade ejbRAPFacade;
    @EJB
    private RelatorioAreasEmpresasFacade ejbRAEFacade;

 
    /**
     * Creates a new instance of RelatorioDivisao
     */
    public RelatorioAreasCapacidadesBean() { 
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
        jrDataSourceSubReport1 = new JRBeanArrayDataSource(getArrayAreasProjetos());
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
        jrDataSourceSubReport2 = new JRBeanArrayDataSource(getArrayAreasEmpresas());
        return jrDataSourceSubReport2;
    }

    /**
     * @param jrDataSourceSubReport2 the jrDataSourceSubReport2 to set
     */
    public void setJrDataSourceSubReport2(JRDataSource jrDataSourceSubReport2) {
        this.jrDataSourceSubReport2 = jrDataSourceSubReport2;
    }
    
    public RelatorioAreasCapacidades[] getArrayAreasCapacidadesPorPeso() {
        arrayAreasCapacidades = ejbRACFacade.findAllAreasCapacidades(area,"peso");
        return arrayAreasCapacidades;
    }
    
    /**
     * @return the arrayAreasProjetos
     */
    public RelatorioAreasProjetos[] getArrayAreasProjetos() {
        arrayAreasProjetos = ejbRAPFacade.findAllAreasProjetosPorArea(area,null);    
        return arrayAreasProjetos;
    }

    /**
     * @param arrayAreasProjetos the arrayAreasProjetos to set
     */
    public void setArrayAreasProjetos(RelatorioAreasProjetos[] arrayAreasProjetos) {
        this.arrayAreasProjetos = arrayAreasProjetos;
    }
    
    /**
     * @return the arrayAreasEmpresas
     */
    public RelatorioAreasEmpresas[] getArrayAreasEmpresas() {
        arrayAreasEmpresas = ejbRAEFacade.findAllAreasEmpresasPorArea(area,null);
        return arrayAreasEmpresas;
    }

    /**
     * @param arrayAreasEmpresas the arrayAreasEmpresas to set
     */
    public void setArrayAreasEmpresas(RelatorioAreasEmpresas[] arrayAreasEmpresas) {
        this.arrayAreasEmpresas = arrayAreasEmpresas;
    }
    

    
    /**
     * @return the arrayAreasProjetosArea
     */
    public RelatorioAreasProjetos[] getArrayAreasProjetosArea() {              
/*
        // popula uma lista com os relacionamentos entre Areas de Pesquisa e projetos do CTEx
        getArrayAreasProjetos();
        
        // efetua uma relação de correspondencia direta entre os arrays e produz uma lista co o resultado
        // os projetos relacionados a áreas que não constem da lista Capacidade/Areas não são contados
        // cria uma lista com os projetos selecionadas         
        ArrayList<RelatorioAreasProjetos> lista = new ArrayList<>();        
        for(RelatorioAreasProjetos itemAP : arrayAreasProjetos) {                
            if (Objects.equals(itemAP.getArea_id(), area.getId())) {                    
                lista.add(itemAP);                    
            }                
        }       

        // agrupa todas as contagens referentes as repetições dos projetos na lista,
        // desconsiderando o efeito das áreas de pesquisa (Não sei se é isto que deve ser feito!!!)
        ArrayList<RelatorioAreasProjetos> listaProjetos = RelatorioAreasProjetos.agrupaProjetos(lista);
        
        //Ordena a lista de projetos em ordem decrescente de avaliação
         Collections.sort(listaProjetos);
        
        // devolve um array com o resultado dos projetos resultantes da pesquisa 
        // com a Capacidade Operativa selecionada.
        arrayAreasProjetosArea = RelatorioAreasProjetos.castAreasProjetos(listaProjetos);
*/
        return arrayAreasProjetosArea;
    }

    /**
     * @param arrayAreasProjetosArea the arrayAreasProjetosArea to set
     */
    public void setArrayAreasProjetosArea(RelatorioAreasProjetos[] arrayAreasProjetosArea) {
        this.arrayAreasProjetosArea = arrayAreasProjetosArea;
    }
    
    /**
     * @return the arrayAreasEmpresasArea
     */
    public RelatorioAreasEmpresas[] getArrayAreasEmpresasArea() {

/*        
        // popula uma lista com os relacionamentos entre Areas de Pesquisa e empresas parceiras do CTEx
        getArrayAreasEmpresas();
        
        // efetua uma relação de correspondencia direta entre os arrays e produz uma lista com o resultado
        // as empresas relacionadas a áreas que não constem da lista Capacidade/Areas não são contadas
        // cria uma lista com as empresas selecionadas 
        ArrayList<RelatorioAreasEmpresas> lista = new ArrayList<>();        
        for(RelatorioAreasEmpresas itemAE : arrayAreasEmpresas) {                
            if (Objects.equals(itemAE.getArea_id(), area.getId())) {                    
                lista.add(itemAE);                    
            }                
        }       

        // agrupa todas as contagens referentes as repetições dos projetos na lista,
        // desconsiderando o efeito das áreas de pesquisa (Não sei se é isto que deve ser feito!!!)
        ArrayList<RelatorioAreasEmpresas> listaEmpresas = RelatorioAreasEmpresas.agrupaEmpresas(lista);
        
        // ordena a lista de empresas pelo seu id 
        Collections.sort(listaEmpresas);
        
        // devolve um array com o resultado dos projetos resultantes da pesquisa 
        // com a Capacidade Operativa selecionada.
        arrayAreasEmpresasArea = RelatorioAreasEmpresas.castAreasEmpresas(listaEmpresas);
*/        
        return arrayAreasEmpresasArea;
    }

    /**
     * @param arrayAreasEmpresasArea the arrayAreasEmpresasArea to set
     */
    public void setArrayAreasEmpresasArea(RelatorioAreasEmpresas[] arrayAreasEmpresasArea) {
        this.arrayAreasEmpresasArea = arrayAreasEmpresasArea;
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
            Logger.getLogger(RelatorioAreasCapacidadesBean.class.getName()).log(Level.SEVERE, null, ex);
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
    public RelatorioCapacidadesAreasFacade getEjbRCAFacade() {
        return ejbRCAFacade;
    }

    /**
     * @param ejbRCAFacade the ejbRCAFacade to set
     */
    public void setEjbRCAFacade(RelatorioCapacidadesAreasFacade ejbRCAFacade) {
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
            Logger.getLogger(RelatorioAreasCapacidadesBean.class.getName()).log(Level.SEVERE, null, ex);
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
    public RelatorioAreasCapacidadesFacade getEjbRACFacade() {
        return ejbRACFacade;
    }

    /**
     * @param ejbRACFacade the ejbRACFacade to set
     */
    public void setEjbRACFacade(RelatorioAreasCapacidadesFacade ejbRACFacade) {
        this.ejbRACFacade = ejbRACFacade;
    }

}
