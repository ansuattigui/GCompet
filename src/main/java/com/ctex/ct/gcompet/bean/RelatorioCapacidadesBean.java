/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template report, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.modelo.Capacidades;
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
@ManagedBean(name = "relatorioCapacidadesAreas")
@RequestScoped
public class RelatorioCapacidadesBean implements Serializable {
    
    private String jasperCapacidadesAreas;
    private String relatorio;
    private ExternalContext context;
    private JRDataSource jrDataSourceMainReport;
    private JRDataSource jrDataSourceSubReport1;
    private JRDataSource jrDataSourceSubReport2;
    private JasperPrint jasperPrintCapacidadesAreas;
    private JasperPrint jasperPrintProjetosAreas;
    private String relatorioAC;
    private Capacidades capacidade;
    //Array para o Subrelatório Projetos
    private RelatorioAreasProjetos[] arrayProjetosAreas;
    //Array para o Subrelatório Empresas
    private RelatorioAreasEmpresas[] arrayEmpresasAreas;
        
    @EJB 
    private RelatorioAreasFacade ejbRCAFacade;
    @EJB
    private RelatorioProjetosFacade ejbRAPFacade;
    @EJB
    private RelatorioEmpresasFacade ejbRAEFacade;

 
    /**
     * Creates a new instance of RelatorioDivisao
     */
    public RelatorioCapacidadesBean() { 
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
        jrDataSourceMainReport = new JRBeanArrayDataSource(getArrayAreasCapacidadePorPeso());
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
     * @return the jrDataSourceSubReport2
     */
    public JRDataSource getJrDataSourceSubReport2() {
        jrDataSourceSubReport2 = new JRBeanArrayDataSource(getArrayEmpresasAreas());
        return jrDataSourceSubReport2;
    }

    //Consulta as Areas de pesquisa afins da capacidade selecionada - Ordem decrescente do numero de avaliações positivas
    public RelatorioCapacidadesAreas[] getArrayAreasCapacidadePorPeso() {
        return ejbRCAFacade.findAllAreasPorCapacidade(capacidade,"peso");
    }

    //Consulta as Areas de pesquisa afins da capacidade selecionada - Ordem do id das áreas de pesquisa
    public RelatorioCapacidadesAreas[] getArrayAreasCapacidadePorArea() {
        return ejbRCAFacade.findAllAreasPorCapacidade(capacidade,"area");
    }   
    
    /**
     * @return the arrayProjetosAreasAux
     */
    //Consulta os Projetos afins  - Ordem do id dos projetos
    public RelatorioAreasProjetos[] getArrayProjetosAreasAux() {
        return ejbRAPFacade.findAllProjetosAreas();    
    }

    /**
     * @return the arrayEmpresasAreasAux
     */
    //Array com todas as empresas relacionadas as áreas selecionadas
    public RelatorioAreasEmpresas[] getArrayEmpresasAreasAux() {
        return ejbRAEFacade.findAllEmpresasAreas();
    }

    /**
     * @return the arrayProjetosAreas
     */
    public RelatorioAreasProjetos[] getArrayProjetosAreas() {              
        // popula uma lista com os relacionamentos entre Capacidades Operativas e Areas de Pesquisa
        RelatorioCapacidadesAreas[] areasArray = getArrayAreasCapacidadePorArea();
        // popula uma lista com os relacionamentos entre Areas de Pesquisa e projetos do CTEx
        RelatorioAreasProjetos[] projetosArray = getArrayProjetosAreasAux();
        
        // efetua uma relação de correspondencia direta entre os arrays e produz uma lista co o resultado
        // os projetos relacionados a áreas que não constem da lista Capacidade/Areas não são contados
        // cria uma lista com os projetos selecionadas 
        ArrayList<RelatorioAreasProjetos> lista = new ArrayList<>();        
        for(RelatorioAreasProjetos itemAP : projetosArray) {                
            for(RelatorioCapacidadesAreas itemCA : areasArray) {            
                if (Objects.equals(itemAP.getArea_id(), itemCA.getArea_id())) {                    
                    lista.add(itemAP);                    
                }                
            }            
        }       

        // agrupa todas as contagens referentes as repetições dos projetos na lista,
        // desconsiderando o efeito das áreas de pesquisa (Não sei se é isto que deve ser feito!!!)
        ArrayList<RelatorioAreasProjetos> listaProjetos = RelatorioAreasProjetos.agrupaProjetos(lista);
        
        //Ordena a lista de projetos em ordem decrescente de avaliação
//         Collections.sort(listaProjetos);         
        
/*        
        listaProjetos.sort(JsfUtil.<RelatorioAreasProjetos>compare()
            .thenComparing(p -> p.getAvaliacao())
            .thenComparing(p -> p.getAvaliadores()));
*/        
        
        // devolve um array com o resultado dos projetos resultantes da pesquisa 
        // com a Capacidade Operativa selecionada.
        arrayProjetosAreas = RelatorioAreasProjetos.castAreasProjetos(listaProjetos);

        return arrayProjetosAreas;
    }

    /**
     * @return the arrayEmpresasAreas
     */
    public RelatorioAreasEmpresas[] getArrayEmpresasAreas() {
        // popula uma lista com os relacionamentos entre Capacidades Operativas e Areas de Pesquisa
        RelatorioCapacidadesAreas[] areasArray = getArrayAreasCapacidadePorArea();
        
        // popula uma lista com os relacionamentos entre Areas de Pesquisa e empresas parceiras do CTEx
        RelatorioAreasEmpresas[] empresasArray = getArrayEmpresasAreasAux();
        
        // efetua uma relação de correspondencia direta entre os arrays e produz uma lista com o resultado
        // as empresas relacionadas a áreas que não constem da lista Capacidade/Areas não são contadas
        // cria uma lista com as empresas selecionadas 
        ArrayList<RelatorioAreasEmpresas> lista = new ArrayList<>();        
        for(RelatorioAreasEmpresas itemAE : empresasArray) {                
            for(RelatorioCapacidadesAreas itemCA : areasArray) {            
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
     * @return the jasperPrintCapacidadesAreas
     */
    public JasperPrint getJasperPrintCapacidadesAreas() {  
        ImageIcon logotipo = new ImageIcon(getContext().getRealPath("resources/img/logo-ctex.png"));                
        HashMap hm = new HashMap<>();
        hm.put("par_logotipo",logotipo.getImage());        
        hm.put("par_nomerelat","Avaliação de Capacidades Operacionais: "+capacidade.getNome().toUpperCase());  
        hm.put("par_dados_projetos", getJrDataSourceSubReport1());
        hm.put("par_dados_empresas", getJrDataSourceSubReport2());
        try {   
            jasperPrintCapacidadesAreas = JasperFillManager.fillReport(getJasperCapacidadesAreas(),hm,getJrDataSourceMainReport());
        } catch (JRException ex) {
            Logger.getLogger(RelatorioCapacidadesBean.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return jasperPrintCapacidadesAreas;
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
     * @return the ejbRCAFacade
     */
    public RelatorioAreasFacade getEjbRCAFacade() {
        return ejbRCAFacade;
    }

    /**
     * @return the relatorioAC
     */
    public String getRelatorioAC() {
        relatorio = "/reports/capacidade/CapacidadesAreas.pdf";
        try {                
            JasperExportManager.exportReportToPdfFile(getJasperPrintCapacidadesAreas(), getContext().getRealPath(relatorio));
        } catch (JRException ex) {
            Logger.getLogger(RelatorioCapacidadesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        relatorioAC = "/user/relatorios/capacidades/CapacidadesAreas";
        return relatorioAC;
    }

    /**
     * @return the capacidade
     */
    public Capacidades getCapacidade() {
        return capacidade;
    }

    /**
     * @param capacidade the capacidade to set
     */
    public void setCapacidade(Capacidades capacidade) {
        this.capacidade = capacidade;
    }

    /**
     * @return the jasperPrintProjetosAreas
     */
    public JasperPrint getJasperPrintProjetosAreas() {
        return jasperPrintProjetosAreas;
    }

}
