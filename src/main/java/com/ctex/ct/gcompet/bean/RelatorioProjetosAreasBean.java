/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template report, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.modelo.Projetos;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioAreasCapacidades;
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
@ManagedBean(name = "relatorioProjetosAreas")
@RequestScoped
public class RelatorioProjetosAreasBean implements Serializable {
    
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
    private RelatorioAreasProjetos[] arrayAreasProjetos;
    private RelatorioAreasCapacidades[] arrayAreasCapacidades;
    private RelatorioAreasEmpresas[] arrayAreasEmpresas;
    private RelatorioAreasProjetos[] arrayAreasProjetosCapacidade;
    private RelatorioAreasEmpresas[] arrayAreasEmpresasCapacidade;
        
    @EJB 
    private RelatorioProjetosAreasFacade ejbRPAFacade;
    @EJB
    private RelatorioAreasProjetosFacade ejbRAPFacade;
    @EJB
    private RelatorioAreasEmpresasFacade ejbRAEFacade;

 
    /**
     * Creates a new instance of RelatorioDivisao
     */
    public RelatorioProjetosAreasBean() { 
    }    

    /**
     * @return the jasperProjetosAreas
     */
    public String getJasperProjetosAreas() {
        jasperProjetosAreas = getContext().getRealPath("user/relatorios/projetos/RelatorioProjetosAreas.jasper");
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
        jrDataSourceMainReport = new JRBeanArrayDataSource(getArrayAreasProjetos());  //getArrayCapacidadesAreasPorPeso());
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
     * @param jrDataSourceSubReport1 the jrDataSourceSubReport1 to set
     */
    public void setJrDataSourceSubReport1(JRDataSource jrDataSourceSubReport1) {
        this.jrDataSourceSubReport1 = jrDataSourceSubReport1;
    }

    /**
     * @return the jrDataSourceSubReport2
     */
    public JRDataSource getJrDataSourceSubReport2() {
        jrDataSourceSubReport2 = new JRBeanArrayDataSource(getArrayAreasEmpresasCapacidade());
        return jrDataSourceSubReport2;
    }

    /**
     * @param jrDataSourceSubReport2 the jrDataSourceSubReport2 to set
     */
    public void setJrDataSourceSubReport2(JRDataSource jrDataSourceSubReport2) {
        this.jrDataSourceSubReport2 = jrDataSourceSubReport2;
    }
    
    public RelatorioAreasCapacidades[] getArrayAreasProjetoPorPeso() {
        arrayAreasCapacidades = ejbRAPFacade.findAllAreasPorProjeto(projeto,"peso");
        return arrayAreasProjetos;
    }

    public RelatorioAreasProjetos[] getArrayAreasProjetoPorArea() {
        arrayAreasCapacidades = ejbRAPFacade.findAllAreasPorProjeto(projeto,"area");
        return arrayAreasProjetos;
    }
    
    /**
     * @param arrayCapacidadesAreas the arrayAreasCapacidades to set
     */
    public void setArrayCapacidadesAreas(RelatorioCapacidadesAreas[] arrayCapacidadesAreas) {
        this.arrayAreasCapacidades = arrayCapacidadesAreas;
    }
    
    /**
     * @return the arrayAreasProjetos
     */
    public RelatorioAreasProjetos[] getArrayAreasProjetos() {
        arrayAreasProjetos = ejbRPAFacade.findAllAreasPorProjeto(projeto, "peso");
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
        arrayAreasEmpresas = ejbRAEFacade.findAllAreasEmpresas();
        return arrayAreasEmpresas;
    }

    /**
     * @param arrayAreasEmpresas the arrayAreasEmpresas to set
     */
    public void setArrayAreasEmpresas(RelatorioAreasEmpresas[] arrayAreasEmpresas) {
        this.arrayAreasEmpresas = arrayAreasEmpresas;
    }
    

    
    /**
     * @return the arrayAreasProjetosCapacidade
     */
    public RelatorioAreasCapacidades[][] getArrayAreasProjetosCapacidade() {              
        // popula uma lista com os relacionamentos entre Capacidades Operativas e Areas de Pesquisa
        getArrayCapacidadesAreasPorArea();
        // popula uma lista com os relacionamentos entre Areas de Pesquisa e projetos do CTEx
        getArrayAreasProjetos();
        
        // efetua uma relação de correspondencia direta entre os arrays e produz uma lista co o resultado
        // os projetos relacionados a áreas que não constem da lista Capacidade/Areas não são contados
        // cria uma lista com os projetos selecionadas 
        ArrayList<RelatorioAreasProjetos> lista = new ArrayList<>();        
        for(RelatorioAreasProjetos itemAP : arrayAreasProjetos) {                
            for(RelatorioCapacidadesAreas itemCA : arrayAreasCapacidades) {            
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
        arrayAreasProjetosCapacidade = RelatorioAreasProjetos.castAreasProjetos(listaProjetos);

        return arrayAreasProjetosCapacidade;
    }

    /**
     * @param arrayAreasProjetosCapacidade the arrayAreasProjetosCapacidade to set
     */
    public void setArrayAreasProjetosCapacidade(RelatorioAreasProjetos[] arrayAreasProjetosCapacidade) {
        this.arrayAreasProjetosCapacidade = arrayAreasProjetosCapacidade;
    }
    
    /**
     * @return the arrayAreasEmpresasCapacidade
     */
    public RelatorioAreasEmpresas[] getArrayAreasEmpresasCapacidade() {
        // popula uma lista com os relacionamentos entre Capacidades Operativas e Areas de Pesquisa
        getArrayCapacidadesAreasPorArea();
        
        // popula uma lista com os relacionamentos entre Areas de Pesquisa e empresas parceiras do CTEx
        getArrayAreasEmpresas();
        
        // efetua uma relação de correspondencia direta entre os arrays e produz uma lista com o resultado
        // as empresas relacionadas a áreas que não constem da lista Capacidade/Areas não são contadas
        // cria uma lista com as empresas selecionadas 
        ArrayList<RelatorioAreasEmpresas> lista = new ArrayList<>();        
        for(RelatorioAreasEmpresas itemAE : arrayAreasEmpresas) {                
            for(RelatorioCapacidadesAreas itemCA : arrayAreasCapacidades) {            
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
        arrayAreasEmpresasCapacidade = RelatorioAreasEmpresas.castAreasEmpresas(listaEmpresas);
        
        return arrayAreasEmpresasCapacidade;
    }

    /**
     * @param arrayAreasEmpresasCapacidade the arrayAreasEmpresasCapacidade to set
     */
    public void setArrayAreasEmpresasCapacidade(RelatorioAreasEmpresas[] arrayAreasEmpresasCapacidade) {
        this.arrayAreasEmpresasCapacidade = arrayAreasEmpresasCapacidade;
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
            jasperPrintCapacidadesAreas = JasperFillManager.fillReport(getJasperProjetosAreas(),hm,getJrDataSourceMainReport());
        } catch (JRException ex) {
            Logger.getLogger(RelatorioProjetosAreasBean.class.getName()).log(Level.SEVERE, null, ex);
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
     * @return the ejbRPAFacade
     */
    public RelatorioCapacidadesAreasFacade getEjbRCAFacade() {
        return ejbRPAFacade;
    }

    /**
     * @param ejbRCAFacade the ejbRPAFacade to set
     */
    public void setEjbRCAFacade(RelatorioCapacidadesAreasFacade ejbRCAFacade) {
        this.ejbRPAFacade = ejbRCAFacade;
    }

    /**
     * @return the relatorioAC
     */
    public String getRelatorioAC() {
        relatorio = "/reports/capacidade/CapacidadesAreas.pdf";
        try {                
            JasperExportManager.exportReportToPdfFile(getJasperPrintCapacidadesAreas(), getContext().getRealPath(relatorio));
        } catch (JRException ex) {
            Logger.getLogger(RelatorioProjetosAreasBean.class.getName()).log(Level.SEVERE, null, ex);
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
