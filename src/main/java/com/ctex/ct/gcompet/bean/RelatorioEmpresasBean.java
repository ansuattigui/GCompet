/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template report, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.modelo.Empresas;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioAreasEmpresas;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioAreasProjetos;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioCapacidadesAreas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
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
@ManagedBean(name = "relatorioEmpresasAreas")
@RequestScoped
public class RelatorioEmpresasBean implements Serializable {
    
    private String jasperProjetosAreas;
    private String relatorio;
    private ExternalContext context;
    private JRDataSource jrDataSourceMainReport;
    private JRDataSource jrDataSourceSubReport1;
    private JRDataSource jrDataSourceSubReport2;
    private JasperPrint jasperPrintCapacidadesAreas;
    private JasperPrint jasperPrintProjetosAreas;
    private String relatorioAC;
    private Empresas empresa;
    //Array para o Subrelatório Projetos
    private RelatorioCapacidadesAreas[] arrayCapacidadesAreas;
    //Array para o Subrelatório Projetos
    private RelatorioAreasProjetos[] arrayProjetosAreas;
        
    @EJB 
    private RelatorioAreasFacade ejbRAFacade;
    @EJB
    private RelatorioCapacidadesFacade ejbRCFacade;
    @EJB
    private RelatorioProjetosFacade ejbRAPFacade;

 
    /**
     * Creates a new instance of RelatorioDivisao
     */
    public RelatorioEmpresasBean() { 
    }    

    /**
     * @return the jasperProjetosAreas
     */
    public String getJasperProjetosAreas() {
        jasperProjetosAreas = getContext().getRealPath("user/relatorios/empresas/RelatorioEmpresaAreas.jasper");
        return jasperProjetosAreas;
    }

    /**
     * @param jasperProjetosAreas the jasperProjetosAreas to set
     */
    public void setJasperProjetosAreas(String jasperProjetosAreas) {
        this.jasperProjetosAreas = jasperProjetosAreas;
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
        jrDataSourceMainReport = new JRBeanArrayDataSource(getArrayAreasEmpresasPorPeso());
        return jrDataSourceMainReport;
    }
    
    /**
     * @return the jrDataSourceSubReport1
     */
    public JRDataSource getJrDataSourceSubReport1() {
        jrDataSourceSubReport1 = new JRBeanArrayDataSource(getArrayCapacidadesAreas());
        return jrDataSourceSubReport1;
    }

    /**
     * @return the jrDataSourceSubReport2
     */
    public JRDataSource getJrDataSourceSubReport2() {
        jrDataSourceSubReport2 = new JRBeanArrayDataSource(getArrayProjetosAreas());
        return jrDataSourceSubReport2;
    }
    
    //Consulta as Areas de pesquisa afins da empresa selecionada - Ordem decrescente do numero de avaliações positivas
    public RelatorioAreasProjetos[] getArrayProjetos() {
        return ejbRAPFacade.findAllProjetosAreas();
    }

    //Consulta as Capacidades operativas relacionadas as areas selecionadas - Ordem do id das áreas de pesquisa
    public RelatorioCapacidadesAreas[] getArrayCapacidades() {        
        return ejbRCFacade.findAllCapacidades();
    }   
    
    //Consulta as Areas afins  - Ordem do id das areas
    public RelatorioAreasEmpresas[] getArrayAreasEmpresas() {
        return ejbRAFacade.findAllAreasPorEmpresa(empresa, "area");    
    }

    //Consulta as Areas afins  - Ordem do id das areas
    public RelatorioAreasEmpresas[] getArrayAreasEmpresasPorPeso() {
        return ejbRAFacade.findAllAreasPorEmpresa(empresa, "peso");    
    }
    
    /**
     * @return the arrayCapacidadesAreas
     */
    public RelatorioCapacidadesAreas[] getArrayCapacidadesAreas() {              
        // popula uma lista com os relacionamentos entre Capacidades Operativas e Areas de Pesquisa
        RelatorioCapacidadesAreas[] capacidadesArray = getArrayCapacidades();
        // popula uma lista com os relacionamentos entre Areas de Pesquisa e empresas do CTEx
        RelatorioAreasEmpresas[] areasArray = getArrayAreasEmpresas();        
        // efetua uma relação de correspondencia direta entre os arrays e produz uma lista co o resultado
        // os projetos relacionados a áreas que não constem da lista Capacidade/Areas não são contados
        // cria uma lista com as capacidades selecionadas 
        ArrayList<RelatorioCapacidadesAreas> lista = new ArrayList<>();        
        for(RelatorioCapacidadesAreas itemCA : capacidadesArray) {                
            for(RelatorioAreasEmpresas itemAE : areasArray) {            
                if (Objects.equals(itemAE.getArea_id(), itemCA.getArea_id())) {                    
                    lista.add(itemCA);                    
                }                
            }            
        }       
        // agrupa todas as contagens referentes as repetições das capacidades na lista,
        // desconsiderando o efeito das áreas de pesquisa (Não sei se é isto que deve ser feito!!!)
        ArrayList<RelatorioCapacidadesAreas> listaCapacidades = RelatorioCapacidadesAreas.agrupaCapacidades(lista);
        
        //Ordena a lista de capacidades em ordem decrescente de avaliação
         Collections.sort(listaCapacidades);
        
        // devolve um array com o resultado das capacidades resultantes da pesquisa 
        // com o Projeto selecionado.
        arrayCapacidadesAreas = RelatorioCapacidadesAreas.castCapacidadesAreas(listaCapacidades);

        return arrayCapacidadesAreas;
    }

    /**
     * @return the arrayProjetosAreas
     */
    public RelatorioAreasProjetos[] getArrayProjetosAreas() {
        // popula uma lista com os relacionamentos entre Projetos e Areas de Pesquisa
        RelatorioAreasProjetos[]  projetosArray = getArrayProjetos();
        // popula uma lista com os relacionamentos entre Areas de Pesquisa e empresas do CTEx
        RelatorioAreasEmpresas[] areasArray = getArrayAreasEmpresas();        
        
        // efetua uma relação de correspondencia direta entre os arrays e produz uma lista com o resultado
        // as empresas relacionadas a áreas que não constem da lista Capacidade/Areas não são contadas
        // cria uma lista com as empresas selecionadas 
        ArrayList<RelatorioAreasProjetos> lista = new ArrayList<>();        
        for(RelatorioAreasProjetos itemAP : projetosArray) {                
            for(RelatorioAreasEmpresas itemAE : areasArray) {            
                if (Objects.equals(itemAP.getArea_id(), itemAE.getArea_id())) {                    
                    lista.add(itemAP);                    
                }                
            }            
        }       

        // agrupa todas as contagens referentes as repetições dos projetos na lista,
        // desconsiderando o efeito das áreas de pesquisa (Não sei se é isto que deve ser feito!!!)
        ArrayList<RelatorioAreasProjetos> listaProjetos = RelatorioAreasProjetos.agrupaProjetos(lista);
        
        // ordena a lista de projetos pelo seu id 
//        Collections.sort(listaProjetos);
        
        // devolve um array com o resultado dos projetos resultantes da pesquisa 
        // com a Capacidade Operativa selecionada.
        arrayProjetosAreas = RelatorioAreasProjetos.castAreasProjetos(listaProjetos);
        
        return arrayProjetosAreas;
    }
    
    /**
     * @return the relatorio
     */
    public String getRelatorio() {        
        relatorio = "/reports/empresa/AreasEmpresas.pdf";
        return relatorio;
    }

    /**
     * @return the ejbRAFacade
     */
    public RelatorioAreasFacade getEjbRAFacade() {
        return ejbRAFacade;
    }

    /**
     * @param ejbRAFacade the ejbRAFacade to set
     */
    public void setEjbRAFacade(RelatorioAreasFacade ejbRAFacade) {
        this.ejbRAFacade = ejbRAFacade;
    }

    /**
     * @return the relatorioAC
     */
      public String getRelatorioAC() {
        relatorio = "/reports/empresa/AreasEmpresas.pdf";
        try {                
            JasperExportManager.exportReportToPdfFile(getJasperPrintProjetosAreas(), getContext().getRealPath(relatorio));
        } catch (JRException ex) {
            Logger.getLogger(RelatorioEmpresasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        relatorioAC = "/user/relatorios/empresas/EmpresasAreas";
        return relatorioAC;
    }

    /**
     * @return the empresa
     */
    public Empresas getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(Empresas empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the jasperPrintProjetosAreas
     */
    public JasperPrint getJasperPrintProjetosAreas() {
        ImageIcon logotipo = new ImageIcon(getContext().getRealPath("resources/img/logo-ctex.png"));                
        HashMap hm = new HashMap<>();
        hm.put("par_logotipo",logotipo.getImage());        
        hm.put("par_nomerelat","Avaliação de Empresas do CTEx: "+empresa.getNome().toUpperCase());  
        hm.put("par_dados_capacidades", getJrDataSourceSubReport1());
        hm.put("par_dados_projetos", getJrDataSourceSubReport2());
        try {   
            jasperPrintProjetosAreas = JasperFillManager.fillReport(getJasperProjetosAreas(),hm,getJrDataSourceMainReport());
        } catch (JRException ex) {
            Logger.getLogger(RelatorioEmpresasBean.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return jasperPrintProjetosAreas;
    }
}
