/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template report, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.modelo.Projetos;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioAreasEmpresas;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioAreasProjetos;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioCapacidadesAreas;
import java.io.Serializable;
import java.sql.Connection;
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
@ManagedBean(name = "relatorioProjetosAreas")
@RequestScoped
public class RelatorioProjetosBean implements Serializable {
    
    private String jasperProjetosAreas;
    private String relatorio;
    private ExternalContext context;
    private JRDataSource jrDataSourceMainReport;
    private JRDataSource jrDataSourceSubReport1;
    private JRDataSource jrDataSourceSubReport2;
    private JasperPrint jasperPrintCapacidadesAreas;
    private JasperPrint jasperPrintProjetosAreas;
    private String relatorioAC;
    private Projetos projeto;
    //Array para o Subrelatório Projetos
    private RelatorioCapacidadesAreas[] arrayCapacidadesAreas;
    //Array para o Subrelatório Empresas
    private RelatorioAreasEmpresas[] arrayEmpresasAreas;
        
    @EJB 
    private RelatorioAreasFacade ejbRAFacade;
    @EJB
    private RelatorioCapacidadesFacade ejbRCFacade;
    @EJB
    private RelatorioEmpresasFacade ejbRAEFacade;

 
    /**
     * Creates a new instance of RelatorioDivisao
     */
    public RelatorioProjetosBean() { 
    }    

    /**
     * @return the jasperProjetosAreas
     */
    public String getJasperProjetosAreas() {
        jasperProjetosAreas = getContext().getRealPath("user/relatorios/projetos/RelatorioProjetoAreas.jasper");
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
        jrDataSourceMainReport = new JRBeanArrayDataSource(getArrayAreasProjetosPorPeso());
        return jrDataSourceMainReport;
    }
    
    /**
     * @return the jrDataSourceSubReport1
     */
    public JRDataSource getJrDataSourceSubReport1() {
        jrDataSourceSubReport1 = new JRBeanArrayDataSource(getArrayCapacidadesAreas());
        return jrDataSourceSubReport1;
    }
/*    
    /**
     * @param jrDataSourceSubReport1 the jrDataSourceSubReport1 to set
     /
    public void setJrDataSourceSubReport1(JRDataSource jrDataSourceSubReport1) {
        this.jrDataSourceSubReport1 = jrDataSourceSubReport1;
    }
*/
    /**
     * @return the jrDataSourceSubReport2
     */
    public JRDataSource getJrDataSourceSubReport2() {
        jrDataSourceSubReport2 = new JRBeanArrayDataSource(getArrayEmpresasAreas());
        return jrDataSourceSubReport2;
    }
/*
    /**
     * @param jrDataSourceSubReport2 the jrDataSourceSubReport2 to set
     /
    public void setJrDataSourceSubReport2(JRDataSource jrDataSourceSubReport2) {
        this.jrDataSourceSubReport2 = jrDataSourceSubReport2;
    }
*/
    
    //Consulta as Areas de pesquisa afins da projeto selecionada - Ordem decrescente do numero de avaliações positivas
    public RelatorioAreasEmpresas[] getArrayEmpresas() {
        return ejbRAEFacade.findAllEmpresasAreas();
    }

    //Consulta as Capacidades operativas relacionadas as areas selecionadas - Ordem do id das áreas de pesquisa
    public RelatorioCapacidadesAreas[] getArrayCapacidades() {        
        return ejbRCFacade.findAllCapacidades();
    }   
    
/*    
    /**
     * @param arrayAreasPorCapacidade the arrayAreasPorProjeto to set
     /
    public void setArrayCapacidadesAreas(RelatorioCapacidadesAreas[] arrayAreasPorCapacidade) {
        this.arrayCapacidadesAreas = arrayAreasPorCapacidade;
    }
*/
    
    //Consulta as Areas afins  - Ordem do id das areas
    public RelatorioAreasProjetos[] getArrayAreasProjetos() {
        return ejbRAFacade.findAllAreasPorProjeto(projeto, "area");    
    }

    //Consulta as Areas afins  - Ordem do id das areas
    public RelatorioAreasProjetos[] getArrayAreasProjetosPorPeso() {
        return ejbRAFacade.findAllAreasPorProjeto(projeto, "peso");    
    }
    
/*    
    /**
     * @param arrayProjetosAreasAux the arrayCapacidadesAreasAux to set
     /
    public void setArrayProjetosAreasAux(RelatorioAreasProjetos[] arrayProjetosAreasAux) {
        this.arrayCapacidadesAreasAux = arrayProjetosAreasAux;
    }
*/
 
/*    
    /**
     * @return the arrayEmpresasAreasAux
     /
    //Array com todas as empresas relacionadas as áreas selecionadas
    public RelatorioAreasEmpresas[] getArrayEmpresasAreasAux() {
        arrayEmpresasAreasAux = ejbRAEFacade.findAllEmpresasAreas();
        return arrayEmpresasAreasAux;
    }

/*    
    /**
     * @param arrayEmpresasAreasAux the arrayEmpresasAreasAux to set
     /
    public void setArrayEmpresasAreasAux(RelatorioAreasEmpresas[] arrayEmpresasAreasAux) {
        this.arrayEmpresasAreasAux = arrayEmpresasAreasAux;
    }
*/    

    
    /**
     * @return the arrayCapacidadesAreas
     */
    public RelatorioCapacidadesAreas[] getArrayCapacidadesAreas() {              
        // popula uma lista com os relacionamentos entre Capacidades Operativas e Areas de Pesquisa
        RelatorioCapacidadesAreas[] capacidadesArray = getArrayCapacidades();
        // popula uma lista com os relacionamentos entre Areas de Pesquisa e projetos do CTEx
        RelatorioAreasProjetos[] areasArray = getArrayAreasProjetos();        
        // efetua uma relação de correspondencia direta entre os arrays e produz uma lista co o resultado
        // os projetos relacionados a áreas que não constem da lista Capacidade/Areas não são contados
        // cria uma lista com os projetos selecionadas 
        ArrayList<RelatorioCapacidadesAreas> lista = new ArrayList<>();        
        for(RelatorioCapacidadesAreas itemCA : capacidadesArray) {                
            for(RelatorioAreasProjetos itemAP : areasArray) {            
                if (Objects.equals(itemAP.getArea_id(), itemCA.getArea_id())) {                    
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

/*    
    /**
     * @param arrayProjetosAreas the arrayCapacidadesAreas to set
     /
    public void setArrayProjetosAreas(RelatorioAreasProjetos[] arrayProjetosAreas) {
        this.arrayCapacidadesAreas = arrayProjetosAreas;
    }
*/
    
    /**
     * @return the arrayEmpresasAreas
     */
    public RelatorioAreasEmpresas[] getArrayEmpresasAreas() {
        // popula uma lista com os relacionamentos entre Empresas e Areas de Pesquisa
        RelatorioAreasEmpresas[]  empresasArray = getArrayEmpresas();
        // popula uma lista com os relacionamentos entre Areas de Pesquisa e projetos do CTEx
        RelatorioAreasProjetos[] areasArray = getArrayAreasProjetos();        
        
        // efetua uma relação de correspondencia direta entre os arrays e produz uma lista com o resultado
        // as empresas relacionadas a áreas que não constem da lista Capacidade/Areas não são contadas
        // cria uma lista com as empresas selecionadas 
        ArrayList<RelatorioAreasEmpresas> lista = new ArrayList<>();        
        for(RelatorioAreasEmpresas itemAE : empresasArray) {                
            for(RelatorioAreasProjetos itemAP : areasArray) {            
                if (Objects.equals(itemAE.getArea_id(), itemAP.getArea_id())) {                    
                    lista.add(itemAE);                    
                }                
            }            
        }       

        // agrupa todas as contagens referentes as repetições dos projetos na lista,
        // desconsiderando o efeito das áreas de pesquisa (Não sei se é isto que deve ser feito!!!)
        ArrayList<RelatorioAreasEmpresas> listaEmpresas = RelatorioAreasEmpresas.agrupaEmpresas(lista);
        
        // ordena a lista de empresas pelo seu id 
        Collections.sort(listaEmpresas);
        
        // devolve um array com o resultado dos projetos resultantes da pesquisa 
        // com a Capacidade Operativa selecionada.
        arrayEmpresasAreas = RelatorioAreasEmpresas.castAreasEmpresas(listaEmpresas);
        
        return arrayEmpresasAreas;
    }
    
    /**
     * @return the relatorio
     */
    public String getRelatorio() {        
        relatorio = "/reports/projeto/Projeto.pdf";
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
        relatorio = "/reports/projeto/Projeto.pdf";
        try {                
            JasperExportManager.exportReportToPdfFile(getJasperPrintProjetosAreas(), getContext().getRealPath(relatorio));
        } catch (JRException ex) {
            Logger.getLogger(RelatorioProjetosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        relatorioAC = "/user/relatorios/projetos/ProjetosAreas";
        return relatorioAC;
    }

    /**
     * @return the projeto
     */
    public Projetos getProjeto() {
        return projeto;
    }

    /**
     * @param projeto the projeto to set
     */
    public void setProjeto(Projetos projeto) {
        this.projeto = projeto;
    }

    /**
     * @return the jasperPrintProjetosAreas
     */
    public JasperPrint getJasperPrintProjetosAreas() {
        ImageIcon logotipo = new ImageIcon(getContext().getRealPath("resources/img/logo-ctex.png"));                
        HashMap hm = new HashMap<>();
        hm.put("par_logotipo",logotipo.getImage());        
        hm.put("par_nomerelat","Avaliação de Projetos do CTEx: "+projeto.getNome().toUpperCase());  
        hm.put("par_dados_capacidade", getJrDataSourceSubReport1());
        hm.put("par_dados_empresas", getJrDataSourceSubReport2());
        try {   
            jasperPrintProjetosAreas = JasperFillManager.fillReport(getJasperProjetosAreas(),hm,getJrDataSourceMainReport());
        } catch (JRException ex) {
            Logger.getLogger(RelatorioProjetosBean.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return jasperPrintProjetosAreas;
    }
}
