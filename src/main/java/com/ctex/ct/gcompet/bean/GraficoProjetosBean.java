/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template report, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.bean.util.MapSortByValue;
import com.ctex.ct.gcompet.modelo.Areas;
import com.ctex.ct.gcompet.modelo.Capacidades;
import com.ctex.ct.gcompet.modelo.Empresas;
import com.ctex.ct.gcompet.modelo.Projetos;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioAreasEmpresas;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioAreasProjetos;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioCapacidadesAreas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;


/**
 *
 * @author ralfh
 */
@SessionScoped
@Named("graficoProjetosAreas")
public class GraficoProjetosBean implements Serializable {
    
    private BarChartModel barChartAreas;
    private BarChartModel barChartCapacidades;
    private BarChartModel barChartEmpresas;
    private Projetos projeto;
    //Array para o Subrelatório Empresas
    private RelatorioAreasEmpresas[] arrayEmpresasAreas;
    
    private Map<Object,Number> mapAreas;
    private Map<Object,Number> mapCapacidades;
    private Map<Object,Number> mapEmpresas;
    
    private Areas selectedArea;
    private Integer selectedAreaPontos;
    private Capacidades selectedCapacidade;
    private Integer selectedCapacidadePontos;
    private Empresas selectedEmpresa;
    private Integer selectedEmpresaPontos;
    
    @EJB private RelatorioAreasFacade ejbRCAFacade;
    @EJB private RelatorioCapacidadesFacade ejbRACFacade;
    @EJB private RelatorioEmpresasFacade ejbRAEFacade;
    
    @EJB private AreasFacade ejbAreas;
    @EJB private CapacidadesFacade ejbCapacidade;
    @EJB private EmpresasFacade ejbEmpresas;
    
    /**
     * @return the barChartAreas
     */
    public BarChartModel getGraficoAreas() {
        return barChartAreas;
    }

    public BarChartModel getGraficoProjetos() {
        return barChartCapacidades;
    }

    public BarChartModel getGraficoEmpresas() {
        return barChartEmpresas;
    }
    
    /**
     * @return the graficoAreas
     */
    public String getGraficos() {
        createGraficoAreas();
        createGraficoCapacidades();
        createGraficoEmpresas();
        selectedArea = null;
        selectedAreaPontos = null;
        selectedEmpresa = null;
        selectedEmpresaPontos = null;
        selectedCapacidade = null;
        selectedCapacidadePontos = null;
        return "/user/graficosnatela/projetos/ProjetosAreasGrafico";
    }
    
    private void createGraficoAreas() {
        barChartAreas = initBarModelAreas();
        barChartAreas.setAnimate(true);
        barChartAreas.setLegendPosition("ne");
        barChartAreas.getAxis(AxisType.X).setTickAngle(-90);
        Axis yAxis = barChartAreas.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setLabel("Pontos");
        //Axis xAxisAreas = barChartAreas.getAxis(AxisType.X);
        //xAxisAreas.setLabel("id Area");
    }    
    private BarChartModel initBarModelAreas() {
        BarChartModel model = new BarChartModel();
        ChartSeries areas = new ChartSeries();            
        areas.setLabel("Areas de Pesquisa");        
        
        RelatorioAreasProjetos[] rap = ejbRCAFacade.findAllAreasPorProjeto(projeto,"peso");
        mapAreas = new LinkedHashMap<>(rap.length);
        
        int i = 0;
        while(i < rap.length) {
            String label = rap[i].getArea_id().toString();
            Integer pDivA = (int) rap[i].getAvaliacao();
            mapAreas.put(label,pDivA);
            i++;
        }
        areas.setData(mapAreas);
        model.addSeries(areas);        
        return model;        
    }

    private void createGraficoCapacidades() {
        barChartCapacidades = initBarModelProjetos();
        barChartCapacidades.setAnimate(true);
        barChartCapacidades.setLegendPosition("ne");
        barChartCapacidades.getAxis(AxisType.X).setTickAngle(-90);
        Axis yAxis = barChartCapacidades.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setLabel("Pontos");
        //Axis xAxisProjetos = barChartCapacidades.getAxis(AxisType.X);
//        xAxisProjetos.setLabel("id Projeto");
    }    
    private BarChartModel initBarModelProjetos() {
        BarChartModel model = new BarChartModel();
        ChartSeries projetos = new ChartSeries();            
        projetos.setLabel("Capacidades");        
        
        mapCapacidades = getMapCapacidadesAreas();
        
        projetos.setData(mapCapacidades);
        model.addSeries(projetos);
        return model;        
    }

    private void createGraficoEmpresas() {
        barChartEmpresas = initBarModelEmpresas();
        barChartEmpresas.setAnimate(true);
        barChartEmpresas.setLegendPosition("ne");
        barChartEmpresas.getAxis(AxisType.X).setTickAngle(-90);
        Axis yAxis = barChartEmpresas.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setLabel("Pontos");
        Axis xAxisProjetos = barChartEmpresas.getAxis(AxisType.X);
//        xAxisProjetos.setLabel("id Projeto");
    }    
    private BarChartModel initBarModelEmpresas() {
        BarChartModel model = new BarChartModel();
        ChartSeries empresas = new ChartSeries();            
        empresas.setLabel("Empresas");        
        
        mapEmpresas = getMapEmpresasAreas();
        
        empresas.setData(mapEmpresas);
        model.addSeries(empresas);
        return model;        
    }
    
    //Consulta as Areas de pesquisa afins da projeto selecionada - Ordem do id das áreas de pesquisa
    public RelatorioCapacidadesAreas[] getArrayCapacidades() {
        return ejbRACFacade.findAllCapacidades();
    }   
    
    /**
     * @return the arrayProjetosAreasAux
     */
    //Consulta os Projetos afins  - Ordem do id dos projetos
    public RelatorioAreasProjetos[] getArrayAreasProjetos() {
        return ejbRCAFacade.findAllAreasPorProjeto(projeto, "area"); 
    }

    /**
     * @return the arrayEmpresasAreasAux
     */
    //Array com todas as empresas relacionadas as áreas selecionadas
    public RelatorioAreasEmpresas[] getArrayEmpresasAreas() {
        return ejbRAEFacade.findAllEmpresasAreas();
    }


    /**
     * @return the mapProjetosAreas
     */
    public Map<Object,Number> getMapCapacidadesAreas() {              
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
                if (itemAP.getArea_id()==itemCA.getArea_id()) {                    
                    lista.add(itemCA);                    
                }                
            }            
        }       

        // agrupa todas as contagens referentes as repetições dos projetos na lista,
        // desconsiderando o efeito das áreas de pesquisa (Não sei se é isto que deve ser feito!!!)
        ArrayList<RelatorioCapacidadesAreas> listaCapacidades = RelatorioCapacidadesAreas.agrupaCapacidades(lista);

        // devolve um array com o resultado dos projetos resultantes da pesquisa 
        // com a Capacidade Operativa selecionada.
        Map<Object,Number> mapCapacidadesAreas = RelatorioCapacidadesAreas.mapCapacidadesAreas(listaCapacidades);
        Map<Object,Number> mapCA =  MapSortByValue.sortByValue(mapCapacidadesAreas,"DESC");
        
        return mapCA;
    }

    /**
     * @return 
     */
    public Map<Object,Number> getMapEmpresasAreas() {
        // popula uma lista com os relacionamentos entre Capacidades Operativas e Areas de Pesquisa
        RelatorioAreasProjetos[] areasArray = getArrayAreasProjetos();
        
        // popula uma lista com os relacionamentos entre Areas de Pesquisa e empresas parceiras do CTEx
        RelatorioAreasEmpresas[] empresasArray = getArrayEmpresasAreas();
        
        // efetua uma relação de correspondencia direta entre os arrays e produz uma lista com o resultado
        // as empresas relacionadas a áreas que não constem da lista Capacidade/Areas não são contadas
        // cria uma lista com as empresas selecionadas 
        ArrayList<RelatorioAreasEmpresas> lista = new ArrayList<>();        
        for(RelatorioAreasEmpresas itemAE : empresasArray) {                
            for(RelatorioAreasProjetos itemAP : areasArray) {            
                if (itemAE.getArea_id()==itemAP.getArea_id()) {                    
                    lista.add(itemAE);                    
                }                
            }            
        }       

        // agrupa todas as contagens referentes as repetições dos projetos na lista,
        // desconsiderando o efeito das áreas de pesquisa (Não sei se é isto que deve ser feito!!!)
        ArrayList<RelatorioAreasEmpresas> listaEmpresas = RelatorioAreasEmpresas.agrupaEmpresas(lista);
        
        // devolve um array com o resultado dos projetos resultantes da pesquisa 
        // com a Capacidade Operativa selecionada.
        Map<Object,Number> mapEmpresasAreas = RelatorioAreasEmpresas.mapAreasEmpresas(listaEmpresas);
        Map<Object,Number> mapEA = MapSortByValue.sortByValue(mapEmpresasAreas,"DESC");
        
        return mapEA;
    }

    /**
     * @return the ejbRCAFacade
     */
    public RelatorioAreasFacade getEjbRCAFacade() {
        return ejbRCAFacade;
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

    public void itemAreaSelect(ItemSelectEvent event) {
        String label = null;
        ChartSeries cs = barChartAreas.getSeries().get(event.getSeriesIndex());
        Map<Object,Number> map = cs.getData();
        int i = 0;
        for (Map.Entry<Object, Number> entry : map.entrySet()) {
            if (i == event.getItemIndex()) {
                label = (String) entry.getKey();
                selectedAreaPontos = (Integer) entry.getValue();
                setSelectedArea(Integer.parseInt(label));
                break;
            }
            i++;
        }        
    }

    public void itemCapacidadeSelect(ItemSelectEvent event) {
        String label = null;
        ChartSeries cs = barChartCapacidades.getSeries().get(event.getSeriesIndex());
        Map<Object,Number> map = cs.getData();
        int i = 0;
        for (Map.Entry<Object, Number> entry : map.entrySet()) {
            if (i == event.getItemIndex()) {
                label = (String) entry.getKey();
                selectedCapacidadePontos = (Integer) entry.getValue();
                setSelectedProjeto(Integer.parseInt(label));
                break;
            }
            i++;
        }        
    }

    public void itemEmpresaSelect(ItemSelectEvent event) {
        String label = null;
        ChartSeries cs = barChartEmpresas.getSeries().get(event.getSeriesIndex());
        Map<Object,Number> map = cs.getData();
        int i = 0;
        for (Map.Entry<Object, Number> entry : map.entrySet()) {
            if (i == event.getItemIndex()) {
                label = (String) entry.getKey();
                selectedEmpresaPontos = (Integer) entry.getValue();
                setSelectedEmpresa(Integer.parseInt(label));
                break;
            }
            i++;
        }        
    }
    
    /**
     * @return the selectedArea
     */
    public Areas getSelectedArea() {
        return selectedArea;
    }

    /**
     * @param selectedAreaId
     */
    public void setSelectedArea(Integer selectedAreaId) {        
        selectedArea = ejbAreas.find(selectedAreaId);                
    }

    /**
     * @return the selectedAreaPontos
     */
    public Integer getSelectedAreaPontos() {
        return selectedAreaPontos;
    }

    /**
     * @param selectedAreaPontos the selectedAreaPontos to set
     */
    public void setSelectedAreaPontos(Integer selectedAreaPontos) {
        this.selectedAreaPontos = selectedAreaPontos;
    }

    /**
     * @return the selectedCapacidade
     */
    public Capacidades getSelectedCapacidade() {
        return selectedCapacidade;
    }

    /**
     * @param selectedCapacidadeId
     */
    public void setSelectedProjeto(Integer selectedCapacidadeId) {
        selectedCapacidade = ejbCapacidade.find(selectedCapacidadeId);
    }

    /**
     * @return the selectedCapacidadePontos
     */
    public Integer getSelectedCapacidadePontos() {
        return selectedCapacidadePontos;
    }

    /**
     * @param selectedCapacidadePontos the selectedCapacidadePontos to set
     */
    public void setSelectedCapacidadePontos(Integer selectedCapacidadePontos) {
        this.selectedCapacidadePontos = selectedCapacidadePontos;
    }

    /**
     * @return the selectedEmpresa
     */
    public Empresas getSelectedEmpresa() {
        return selectedEmpresa;
    }

    /**
     * @param selectedEmpresaId
     */
    public void setSelectedEmpresa(Integer selectedEmpresaId) {
        selectedEmpresa = ejbEmpresas.find(selectedEmpresaId);
    }

    /**
     * @return the selectedEmpresaPontos
     */
    public Integer getSelectedEmpresaPontos() {
        return selectedEmpresaPontos;
    }

    /**
     * @param selectedEmpresaPontos the selectedEmpresaPontos to set
     */
    public void setSelectedEmpresaPontos(Integer selectedEmpresaPontos) {
        this.selectedEmpresaPontos = selectedEmpresaPontos;
    }
    
}
