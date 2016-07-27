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
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author ralfh
 */
@ManagedBean(name = "graficoCapacidadesAreas")
@SessionScoped
public class GraficoCapacidadesBean implements Serializable {
    
    private JRDataSource jrDataSourceMainReport;
    private BarChartModel barChartAreas;
    private JRDataSource jrDataSourceSubReport2;
    private JasperPrint jasperPrintCapacidadesAreas;
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
    public GraficoCapacidadesBean() { 
    }    

    @PostConstruct
    public void init() {
        createGraphics();
    }    
    
    private void createGraphics() {
        createBarChartAreas();
    }
    
    private void createBarChartAreas() {
        barChartAreas = initBarModel();
        barChartAreas.setTitle("Areas de Pesquisa");
        barChartAreas.setAnimate(true);
        barChartAreas.setLegendPosition("ne");
        Axis yAxis = barChartAreas.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(200);    
    }
    
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        ChartSeries areas = new ChartSeries();            
        areas.setLabel("Areas de Pesquisa");        
        
        Map<Integer,Double> mapAreas = new HashMap<>();
        RelatorioCapacidadesAreas[] rca = ejbRCAFacade.findAllAreasPorCapacidade(capacidade,"peso");
        
        int i = 0;
        while(i < rca.length) {
            mapAreas.put(rca[i].getArea_id(), (Double) (rca[i].getAvaliacao()/rca[i].getAvaliadores()));
        }
        
        return model;
        
    }


    public JRDataSource getJrDataSourceMainReport() {        
        jrDataSourceMainReport = new JRBeanArrayDataSource(getArrayAreasCapacidadePorPeso());
        return jrDataSourceMainReport;
    }

    /**
     * @return the barChartAreas
     */
    public BarChartModel getBarChartAreas() {
        
        
        
        
        return barChartAreas;
    }

    
    /**
     * @return the jrDataSourceSubReport2
     */
    public JRDataSource getJrDataSourceSubReport2() {
        jrDataSourceSubReport2 = new JRBeanArrayDataSource(getArrayEmpresasAreas());
        return jrDataSourceSubReport2;
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
         Collections.sort(listaProjetos);         
        
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
        return jasperPrintCapacidadesAreas;
    }

    /**
     * @return the ejbRCAFacade
     */
    public RelatorioAreasFacade getEjbRCAFacade() {
        return ejbRCAFacade;
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


}
