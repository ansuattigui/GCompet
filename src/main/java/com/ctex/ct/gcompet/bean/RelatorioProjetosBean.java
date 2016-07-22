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
    
    private String jasperCapacidadesAreas;
    private String relatorio;
    private ExternalContext context;
    private JRDataSource jrDataSourceMainReport;
    private JRDataSource jrDataSourceSubReport1;
    private JRDataSource jrDataSourceSubReport2;
    private JasperPrint jasperPrintCapacidadesAreas;
    private JasperPrint jasperPrintProjetosAreas;
    private Connection connection;
    private String relatorioAC;
    private Projetos projeto;
    //Array do Conteudo principal - Areas por Capacidade selecionada
    private RelatorioCapacidadesAreas[] arrayAreasPorCapacidade;
    //Array para o Subrelatório Projetos
    private RelatorioAreasProjetos[] arrayProjetosAreas;
    private RelatorioAreasProjetos[] arrayProjetosAreasAux;
    //Array para o Subrelatório Empresas
    private RelatorioAreasEmpresas[] arrayEmpresasAreas;
    private RelatorioAreasEmpresas[] arrayEmpresasAreasAux;
        
    @EJB 
    private RelatorioAreasFacade ejbRCAFacade;
    @EJB
    private RelatorioProjetosFacade ejbRAPFacade;
    @EJB
    private RelatorioEmpresasFacade ejbRAEFacade;

 
    /**
     * Creates a new instance of RelatorioDivisao
     */
    public RelatorioProjetosBean() { 
    }    

    /**
     * @return the jasperCapacidadesAreas
     */
    public String getJasperCapacidadesAreas() {
        jasperCapacidadesAreas = getContext().getRealPath("user/relatorios/capacidades/RelatorioCapacidadeAreas.jasper");
        return jasperCapacidadesAreas;
    }

    /**
     * @param jasperCapacidadesAreas the jasperCapacidadesAreas to set
     */
    public void setJasperCapacidadesAreas(String jasperCapacidadesAreas) {
        this.jasperCapacidadesAreas = jasperCapacidadesAreas;
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
        jrDataSourceMainReport = new JRBeanArrayDataSource(getArrayAreasPorCapacidadePorPeso());
        return jrDataSourceMainReport;
    }
    
    /**
     * @return the jrDataSourceSubReport1
     */
    public JRDataSource getJrDataSourceSubReport1() {
        jrDataSourceSubReport1 = new JRBeanArrayDataSource(getArrayProjetosAreas());
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
        jrDataSourceSubReport2 = new JRBeanArrayDataSource(getArrayEmpresasAreas());
        return jrDataSourceSubReport2;
    }

    /**
     * @param jrDataSourceSubReport2 the jrDataSourceSubReport2 to set
     */
    public void setJrDataSourceSubReport2(JRDataSource jrDataSourceSubReport2) {
        this.jrDataSourceSubReport2 = jrDataSourceSubReport2;
    }
    
    //Consulta as Areas de pesquisa afins da projeto selecionada - Ordem decrescente do numero de avaliações positivas
    public RelatorioCapacidadesAreas[] getArrayAreasPorCapacidadePorPeso() {
        arrayAreasPorCapacidade = ejbRCAFacade.findAllAreasPorCapacidade(projeto,"peso");
        return arrayAreasPorCapacidade;
    }

    //Consulta as Areas de pesquisa afins da projeto selecionada - Ordem do id das áreas de pesquisa
    public RelatorioCapacidadesAreas[] getArrayAreasPorCapacidadePorArea() {
        arrayAreasPorCapacidade = ejbRCAFacade.findAllAreasPorCapacidade(projeto,"area");
        return arrayAreasPorCapacidade;
    }   
    /**
     * @param arrayAreasPorCapacidade the arrayAreasPorCapacidade to set
     */
    public void setArrayAreasPorCapacidade(RelatorioCapacidadesAreas[] arrayAreasPorCapacidade) {
        this.arrayAreasPorCapacidade = arrayAreasPorCapacidade;
    }
    
    /**
     * @return the arrayProjetosAreasAux
     */
    //Consulta os Projetos afins  - Ordem do id dos projetos
    public RelatorioAreasProjetos[] getArrayProjetosAreasAux() {
        arrayProjetosAreasAux = ejbRAPFacade.findAllProjetos();    
        return arrayProjetosAreasAux;
    }

    /**
     * @param arrayProjetosAreasAux the arrayProjetosAreasAux to set
     */
    public void setArrayProjetosAreasAux(RelatorioAreasProjetos[] arrayProjetosAreasAux) {
        this.arrayProjetosAreasAux = arrayProjetosAreasAux;
    }
    
    /**
     * @return the arrayEmpresasAreasAux
     */
    //Array com todas as empresas relacionadas as áreas selecionadas
    public RelatorioAreasEmpresas[] getArrayEmpresasAreasAux() {
        arrayEmpresasAreasAux = ejbRAEFacade.findAllEmpresasAreas();
        return arrayEmpresasAreasAux;
    }

    /**
     * @param arrayEmpresasAreasAux the arrayEmpresasAreasAux to set
     */
    public void setArrayEmpresasAreasAux(RelatorioAreasEmpresas[] arrayEmpresasAreasAux) {
        this.arrayEmpresasAreasAux = arrayEmpresasAreasAux;
    }
    

    
    /**
     * @return the arrayProjetosAreas
     */
    public RelatorioAreasProjetos[] getArrayProjetosAreas() {              
        // popula uma lista com os relacionamentos entre Capacidades Operativas e Areas de Pesquisa
        getArrayAreasPorCapacidadePorArea();
        // popula uma lista com os relacionamentos entre Areas de Pesquisa e projetos do CTEx
        getArrayProjetosAreasAux();
        
        // efetua uma relação de correspondencia direta entre os arrays e produz uma lista co o resultado
        // os projetos relacionados a áreas que não constem da lista Capacidade/Areas não são contados
        // cria uma lista com os projetos selecionadas 
        ArrayList<RelatorioAreasProjetos> lista = new ArrayList<>();        
        for(RelatorioAreasProjetos itemAP : arrayProjetosAreasAux) {                
            for(RelatorioCapacidadesAreas itemCA : arrayAreasPorCapacidade) {            
                if (Objects.equals(itemAP.getArea_id(), itemCA.getArea_id())) {                    
                    lista.add(itemAP);                    
                }                
            }            
        }       

        // agrupa todas as contagens referentes as repetições dos projetos na lista,
        // desconsiderando o efeito das áreas de pesquisa (Não sei se é isto que deve ser feito!!!)
        ArrayList<RelatorioAreasProjetos> listaProjetos = RelatorioAreasProjetos.agrupaProjetos(lista);
        
        //Ordena a lista de projetos em ordem decrescente de avaliação
         Collections.sort(listaProjetos);
        
        // devolve um array com o resultado dos projetos resultantes da pesquisa 
        // com a Capacidade Operativa selecionada.
        arrayProjetosAreas = RelatorioAreasProjetos.castAreasProjetos(listaProjetos);

        return arrayProjetosAreas;
    }

    /**
     * @param arrayProjetosAreas the arrayProjetosAreas to set
     */
    public void setArrayProjetosAreas(RelatorioAreasProjetos[] arrayProjetosAreas) {
        this.arrayProjetosAreas = arrayProjetosAreas;
    }
    
    /**
     * @return the arrayEmpresasAreas
     */
    public RelatorioAreasEmpresas[] getArrayEmpresasAreas() {
        // popula uma lista com os relacionamentos entre Capacidades Operativas e Areas de Pesquisa
        getArrayAreasPorCapacidadePorArea();
        
        // popula uma lista com os relacionamentos entre Areas de Pesquisa e empresas parceiras do CTEx
        getArrayEmpresasAreasAux();
        
        // efetua uma relação de correspondencia direta entre os arrays e produz uma lista com o resultado
        // as empresas relacionadas a áreas que não constem da lista Capacidade/Areas não são contadas
        // cria uma lista com as empresas selecionadas 
        ArrayList<RelatorioAreasEmpresas> lista = new ArrayList<>();        
        for(RelatorioAreasEmpresas itemAE : arrayEmpresasAreasAux) {                
            for(RelatorioCapacidadesAreas itemCA : arrayAreasPorCapacidade) {            
                if (Objects.equals(itemAE.getArea_id(), itemCA.getArea_id())) {                    
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
     * @param arrayEmpresasAreas the arrayEmpresasAreas to set
     */
    public void setArrayEmpresasAreas(RelatorioAreasEmpresas[] arrayEmpresasAreas) {
        this.arrayEmpresasAreas = arrayEmpresasAreas;
    }
    
    
    
    /**
     * @return the jasperPrintCapacidadesAreas
     */
    public JasperPrint getJasperPrintCapacidadesAreas() {  
        ImageIcon logotipo = new ImageIcon(getContext().getRealPath("resources/img/logo-ctex.png"));                
        HashMap hm = new HashMap<>();
        hm.put("par_logotipo",logotipo.getImage());        
        hm.put("par_nomerelat","Avaliação de Capacidades Operacionais: "+projeto.getNome().toUpperCase());  
        hm.put("par_dados_projetos", getJrDataSourceSubReport1());
        hm.put("par_dados_empresas", getJrDataSourceSubReport2());
        try {   
            jasperPrintCapacidadesAreas = JasperFillManager.fillReport(getJasperCapacidadesAreas(),hm,getJrDataSourceMainReport());
        } catch (JRException ex) {
            Logger.getLogger(RelatorioProjetosBean.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return jasperPrintCapacidadesAreas;
    }

    /**
     * @param jasperPrintCapacidadesAreas the jasperPrintCapacidadesAreas to set
     */
    public void setJasperPrintCapacidadesAreas(JasperPrint jasperPrintCapacidadesAreas) {
        this.jasperPrintCapacidadesAreas = jasperPrintCapacidadesAreas;
    }
    
    /**
     * @return the relatorio
     */
    public String getRelatorio() {        
        relatorio = "/reports/capacidade/CapacidadesAreas.pdf";
        return relatorio;
    }

    /**
     * @param relatorio the relatorio to set
     */
    public void setRelatorio(String relatorio) {
        this.relatorio = relatorio;
    }

    /**
     * @return 
     */
    public Connection getConnection() {
        connection = CapacidadesAreasFacade.getConnection();
        return connection;
    }

    /**
     * @param connection the connection to set
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public void closeConnection() {
        CapacidadesAreasFacade.closeConnection(connection);
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
        relatorio = "/reports/capacidade/CapacidadesAreas.pdf";
        try {                
            JasperExportManager.exportReportToPdfFile(getJasperPrintCapacidadesAreas(), getContext().getRealPath(relatorio));
        } catch (JRException ex) {
            Logger.getLogger(RelatorioProjetosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        relatorioAC = "/user/relatorios/capacidades/CapacidadesAreas";
        return relatorioAC;
    }

    /**
     * @param relatorioAC the relatorioAC to set
     */
    public void setRelatorioAC(String relatorioAC) {
        this.relatorioAC = relatorioAC;
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
        return jasperPrintProjetosAreas;
    }

    /**
     * @param jasperPrintProjetosAreas the jasperPrintProjetosAreas to set
     */
    public void setJasperPrintProjetosAreas(JasperPrint jasperPrintProjetosAreas) {
        this.jasperPrintProjetosAreas = jasperPrintProjetosAreas;
    }

}
