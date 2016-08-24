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
@Named("graficoEmpresasAreas")
public class GraficoEmpresasBean implements Serializable {
    
    private BarChartModel barChartAreas;
    private BarChartModel barChartCapacidades;
    private BarChartModel barChartProjetos;
    private Empresas empresa;
    //Array para o Subrelatório Empresas
    private RelatorioAreasEmpresas[] arrayEmpresasAreas;
    
    private Map<Object,Number> mapAreas;
    private Map<Object,Number> mapCapacidades;
    private Map<Object,Number> mapProjetos;
    
    private Areas selectedArea;
    private Integer selectedAreaPontos;
    private Capacidades selectedCapacidade;
    private Integer selectedCapacidadePontos;
    private Projetos selectedProjeto;
    private Integer selectedProjetoPontos;
    
    @EJB private RelatorioAreasFacade ejbRCAFacade;
    @EJB private RelatorioCapacidadesFacade ejbRACFacade;
    @EJB private RelatorioProjetosFacade ejbRAPFacade;
    
    @EJB private AreasFacade ejbAreas;
    @EJB private CapacidadesFacade ejbCapacidade;
    @EJB private ProjetosFacade ejbProjetos;
    
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
        return barChartProjetos;
    }
    
    /**
     * @return the graficoAreas
     */
    public String getGraficos() {
        createGraficoAreas();
        createGraficoCapacidades();
        createGraficoProjetos();
        selectedArea = null;
        selectedAreaPontos = null;
        selectedProjeto = null;
        selectedProjetoPontos = null;
        selectedCapacidade = null;
        selectedCapacidadePontos = null;
        return "/user/graficosnatela/empresas/EmpresasAreasGrafico";
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
        
        RelatorioAreasEmpresas[] rap = ejbRCAFacade.findAllAreasPorEmpresa(empresa,"peso");
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
        barChartCapacidades = initBarModelCapacidades();
        barChartCapacidades.setAnimate(true);
        barChartCapacidades.setLegendPosition("ne");
        barChartCapacidades.getAxis(AxisType.X).setTickAngle(-90);
        Axis yAxis = barChartCapacidades.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setLabel("Pontos");
        //Axis xAxisProjetos = barChartCapacidades.getAxis(AxisType.X);
//        xAxisProjetos.setLabel("id Projeto");
    }    
    private BarChartModel initBarModelCapacidades() {
        BarChartModel model = new BarChartModel();
        ChartSeries projetos = new ChartSeries();            
        projetos.setLabel("Capacidades");        
        
        mapCapacidades = getMapCapacidadesAreas();
        
        projetos.setData(mapCapacidades);
        model.addSeries(projetos);
        return model;        
    }

    private void createGraficoProjetos() {
        barChartProjetos = initBarModelProjetos();
        barChartProjetos.setAnimate(true);
        barChartProjetos.setLegendPosition("ne");
        barChartProjetos.getAxis(AxisType.X).setTickAngle(-90);
        Axis yAxis = barChartProjetos.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setLabel("Pontos");
        Axis xAxisProjetos = barChartProjetos.getAxis(AxisType.X);
//        xAxisProjetos.setLabel("id Projeto");
    }    
    private BarChartModel initBarModelProjetos() {
        BarChartModel model = new BarChartModel();
        ChartSeries empresas = new ChartSeries();            
        empresas.setLabel("Projetos");        
        
        mapProjetos = getMapProjetosAreas();
        
        empresas.setData(mapProjetos);
        model.addSeries(empresas);
        return model;        
    }
    
    //Consulta as Areas de pesquisa afins da empresa selecionada - Ordem do id das áreas de pesquisa
    public RelatorioCapacidadesAreas[] getArrayCapacidades() {
        return ejbRACFacade.findAllCapacidades();
    }   
    
    /**
     * @return the arrayProjetosAreasAux
     */
    //Consulta os Projetos afins  - Ordem do id dos projetos
    public RelatorioAreasEmpresas[] getArrayAreasEmpresas() {
        return ejbRCAFacade.findAllAreasPorEmpresa(empresa, "area"); 
    }

    /**
     * @return the arrayEmpresasAreasAux
     */
    //Array com todas as empresas relacionadas as áreas selecionadas
    public RelatorioAreasProjetos[] getArrayProjetosAreas() {
        return ejbRAPFacade.findAllProjetosAreas();
    }


    /**
     * @return the mapProjetosAreas
     */
    public Map<Object,Number> getMapCapacidadesAreas() {              
        // popula uma lista com os relacionamentos entre Capacidades Operativas e Areas de Pesquisa
        RelatorioCapacidadesAreas[] capacidadesArray = getArrayCapacidades();
        // popula uma lista com os relacionamentos entre Areas de Pesquisa e projetos do CTEx
        RelatorioAreasEmpresas[] areasArray = getArrayAreasEmpresas();
        
        // efetua uma relação de correspondencia direta entre os arrays e produz uma lista co o resultado
        // os projetos relacionados a áreas que não constem da lista Capacidade/Areas não são contados
        // cria uma lista com os projetos selecionadas 
        ArrayList<RelatorioCapacidadesAreas> lista = new ArrayList<>();        
        for(RelatorioCapacidadesAreas itemCA : capacidadesArray) {                
            for(RelatorioAreasEmpresas itemAE : areasArray) {            
                if (itemAE.getArea_id()==itemCA.getArea_id()) {                    
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
    public Map<Object,Number> getMapProjetosAreas() {
        // popula uma lista com os relacionamentos entre Capacidades Operativas e Areas de Pesquisa
        RelatorioAreasEmpresas[] areasArray = getArrayAreasEmpresas();
        
        // popula uma lista com os relacionamentos entre Areas de Pesquisa e empresas parceiras do CTEx
        RelatorioAreasProjetos[] projetosArray = getArrayProjetosAreas();
        
        // efetua uma relação de correspondencia direta entre os arrays e produz uma lista com o resultado
        // as empresas relacionadas a áreas que não constem da lista Capacidade/Areas não são contadas
        // cria uma lista com as empresas selecionadas 
        ArrayList<RelatorioAreasProjetos> lista = new ArrayList<>();        
        for(RelatorioAreasProjetos itemAP : projetosArray) {                
            for(RelatorioAreasEmpresas itemAE : areasArray) {            
                if (itemAP.getArea_id()==itemAE.getArea_id()) {                    
                    lista.add(itemAP);                    
                }                
            }            
        }       

        // agrupa todas as contagens referentes as repetições dos projetos na lista,
        // desconsiderando o efeito das áreas de pesquisa (Não sei se é isto que deve ser feito!!!)
        ArrayList<RelatorioAreasProjetos> listaProjetos = RelatorioAreasProjetos.agrupaProjetos(lista);
        
        // devolve um array com o resultado dos projetos resultantes da pesquisa 
        // com a Capacidade Operativa selecionada.
        Map<Object,Number> mapProjetosAreas = RelatorioAreasProjetos.mapAreasProjetos(listaProjetos);
        Map<Object,Number> mapPA = MapSortByValue.sortByValue(mapProjetosAreas,"DESC");
        
        return mapPA;
    }

    /**
     * @return the ejbRCAFacade
     */
    public RelatorioAreasFacade getEjbRCAFacade() {
        return ejbRCAFacade;
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
        ChartSeries cs = barChartProjetos.getSeries().get(event.getSeriesIndex());
        Map<Object,Number> map = cs.getData();
        int i = 0;
        for (Map.Entry<Object, Number> entry : map.entrySet()) {
            if (i == event.getItemIndex()) {
                label = (String) entry.getKey();
                selectedProjetoPontos = (Integer) entry.getValue();
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
     * @return the selectedProjeto
     */
    public Projetos getSelectedProjeto() {
        return selectedProjeto;
    }

    /**
     * @param selectedEmpresaId
     */
    public void setSelectedEmpresa(Integer selectedProjetoId) {
        selectedProjeto = ejbProjetos.find(selectedProjetoId);
    }

    /**
     * @return the selectedProjetoPontos
     */
    public Integer getSelectedProjetoPontos() {
        return selectedProjetoPontos;
    }

    /**
     * @param selectedProjetoPontos the selectedProjetoPontos to set
     */
    public void setSelectedProjetoPontos(Integer selectedProjetoPontos) {
        this.selectedProjetoPontos = selectedProjetoPontos;
    }
    
}
