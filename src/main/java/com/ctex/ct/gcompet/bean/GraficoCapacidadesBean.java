/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template report, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.bean.util.AreasProjetosAvaliacaoComp;
import com.ctex.ct.gcompet.bean.util.AreasProjetosAvaliadorComp;
import com.ctex.ct.gcompet.bean.util.AreasProjetosComparator;
import com.ctex.ct.gcompet.modelo.Capacidades;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioAreasEmpresas;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioAreasProjetos;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioCapacidadesAreas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.olap4j.impl.ArrayMap;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;


/**
 *
 * @author ralfh
 */
@SessionScoped
@Named("graficoCapacidadesAreas")
public class GraficoCapacidadesBean implements Serializable {
    
    private BarChartModel barChartAreas;
    private BarChartModel barChartProjetos;
    private BarChartModel barChartEmpresas;
    private Capacidades capacidade;
    //Array para o Subrelatório Empresas
    private RelatorioAreasEmpresas[] arrayEmpresasAreas;
        
    @EJB 
    private RelatorioAreasFacade ejbRCAFacade;
    @EJB
    private RelatorioProjetosFacade ejbRAPFacade;
    @EJB
    private RelatorioEmpresasFacade ejbRAEFacade;
    
    /**
     * @return the barChartAreas
     */
    public BarChartModel getGraficoAreas() {
        return barChartAreas;
    }

    public BarChartModel getGraficoProjetos() {
        return barChartProjetos;
    }

    
    /**
     * @return the graficoAreas
     */
    public String getGraficos() {
        createGraficoAreas();
        createGraficoProjetos();
        
        return "/user/graficosnatela/capacidades/CapacidadesAreasGrafico";
    }
    
    private void createGraficoAreas() {
        barChartAreas = initBarModelAreas();
//        barChartAreas.setTitle("Areas de Pesquisa");
        barChartAreas.setAnimate(true);
        barChartAreas.setLegendPosition("ne");
        Axis yAxis = barChartAreas.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(100);  
        yAxis.setLabel("%");
        Axis xAxisAreas = barChartAreas.getAxis(AxisType.X);
        xAxisAreas.setLabel("id Area");
    }
    
    private BarChartModel initBarModelAreas() {
        BarChartModel model = new BarChartModel();
        ChartSeries areas = new ChartSeries();            
        areas.setLabel("Areas de Pesquisa");        
        
        RelatorioCapacidadesAreas[] rca = ejbRCAFacade.findAllAreasPorCapacidade(capacidade,"peso");
        Map<Object,Number> mapAreas = new LinkedHashMap<>(rca.length);
        
        int i = 0;
        while(i < rca.length) {
            String label = rca[i].getArea_id().toString();
            Float pDivA = (float)rca[i].getAvaliacao()*100/rca[i].getAvaliadores();
            mapAreas.put(label,pDivA);
            i++;
        }
        areas.setData(mapAreas);
        model.addSeries(areas);        
        return model;        
    }

    private void createGraficoProjetos() {
        barChartProjetos = initBarModelProjetos();
//        barChartAreas.setTitle("Projetos");
        barChartProjetos.setAnimate(true);
        barChartProjetos.setLegendPosition("ne");
        Axis yAxis = barChartProjetos.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(100);  
        yAxis.setLabel("%");
        Axis xAxisProjetos = barChartProjetos.getAxis(AxisType.X);
        xAxisProjetos.setLabel("id Projeto");
}
    
    private BarChartModel initBarModelProjetos() {
        BarChartModel model = new BarChartModel();
        ChartSeries projetos = new ChartSeries();            
        projetos.setLabel("Projetos");        
        
        Map<Object,Number> mapProjetos = new ArrayMap<>(); //  HashMap<>();
        RelatorioAreasProjetos[] rpa = getArrayProjetosAreas();
        
        int i = 0;
        while(i < rpa.length) {
            String label = rpa[i].getProjeto_id().toString();
            Float pDivA = ((float)rpa[i].getAvaliacao()/rpa[i].getAvaliadores())*100;
            mapProjetos.put(label,pDivA);
            i++;
        }
        
        projetos.setData(mapProjetos);
        model.addSeries(projetos);
        return model;        
    }
    
    /**
     * @return the jrDataSourceSubReport2
     *
    public JRDataSource getJrDataSourceSubReport2() {
        jrDataSourceSubReport2 = new JRBeanArrayDataSource(getArrayEmpresasAreas());
        return jrDataSourceSubReport2;
    }

*/ 
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
                if (itemAP.getArea_id()==itemCA.getArea_id()) {                    
                    lista.add(itemAP);                    
                }                
            }            
        }       

        // agrupa todas as contagens referentes as repetições dos projetos na lista,
        // desconsiderando o efeito das áreas de pesquisa (Não sei se é isto que deve ser feito!!!)
        ArrayList<RelatorioAreasProjetos> listaProjetos = RelatorioAreasProjetos.agrupaProjetos(lista);

        // devolve um array com o resultado dos projetos resultantes da pesquisa 
        // com a Capacidade Operativa selecionada.
        RelatorioAreasProjetos[] arrayProjetosAreas = RelatorioAreasProjetos.castAreasProjetos(listaProjetos);
        
        Arrays.sort(arrayProjetosAreas, new AreasProjetosComparator(
                new AreasProjetosAvaliacaoComp(),
                new AreasProjetosAvaliadorComp())
        );
        
        
        return arrayProjetosAreas;
    }

    /**
     * @return the arrayEmpresasAreas
     *
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
*/
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
