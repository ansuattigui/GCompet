/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template report, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.bean.util.AreasEmpresasSortByValue;
import com.ctex.ct.gcompet.bean.util.AreasProjetosSortByValue;
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

//çgnmerkbnorb

/**
 *
 * @author ralfh
 */
@SessionScoped
@Named("graficoCapacidadesAreas")
public class GraficoProjetosBean implements Serializable {
    
    private BarChartModel barChartAreas;
    private BarChartModel barChartProjetos;
    private BarChartModel barChartEmpresas;
    private Capacidades capacidade;
    //Array para o Subrelatório Empresas
    private RelatorioAreasEmpresas[] arrayEmpresasAreas;
    
    private Map<Object,Number> mapAreas;
    private Map<Object,Number> mapProjetos;
    private Map<Object,Number> mapEmpresas;
    
    private Areas selectedArea;
    private Integer selectedAreaPontos;
    private Projetos selectedProjeto;
    private Integer selectedProjetoPontos;
    private Empresas selectedEmpresa;
    private Integer selectedEmpresaPontos;
    
    @EJB private RelatorioAreasFacade ejbRCAFacade;
    @EJB private RelatorioProjetosFacade ejbRAPFacade;
    @EJB private RelatorioEmpresasFacade ejbRAEFacade;
    
    @EJB private AreasFacade ejbAreas;
    @EJB private ProjetosFacade ejbProjeto;
    @EJB private EmpresasFacade ejbEmpresas;
    
    /**
     * @return the barChartAreas
     */
    public BarChartModel getGraficoAreas() {
        return barChartAreas;
    }

    public BarChartModel getGraficoProjetos() {
        return barChartProjetos;
    }

    public BarChartModel getGraficoEmpresas() {
        return barChartEmpresas;
    }
    
    /**
     * @return the graficoAreas
     */
    public String getGraficos() {
        createGraficoAreas();
        createGraficoProjetos();
        createGraficoEmpresas();
        selectedArea = null;
        selectedAreaPontos = null;
        selectedEmpresa = null;
        selectedEmpresaPontos = null;
        selectedProjeto = null;
        selectedProjetoPontos = null;
        return "/user/graficosnatela/capacidades/CapacidadesAreasGrafico";
    }
    
    private void createGraficoAreas() {
        barChartAreas = initBarModelAreas();
        barChartAreas.setAnimate(true);
        barChartAreas.setLegendPosition("ne");
        barChartAreas.getAxis(AxisType.X).setTickAngle(-90);
        Axis yAxis = barChartAreas.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setLabel("Pontos");
        Axis xAxisAreas = barChartAreas.getAxis(AxisType.X);
        //xAxisAreas.setLabel("id Area");
    }    
    private BarChartModel initBarModelAreas() {
        BarChartModel model = new BarChartModel();
        ChartSeries areas = new ChartSeries();            
        areas.setLabel("Areas de Pesquisa");        
        
        RelatorioCapacidadesAreas[] rca = ejbRCAFacade.findAllAreasPorCapacidade(capacidade,"peso");
        mapAreas = new LinkedHashMap<>(rca.length);
        
        int i = 0;
        while(i < rca.length) {
            String label = rca[i].getArea_id().toString();
            Integer pDivA = (int) rca[i].getAvaliacao();
            mapAreas.put(label,pDivA);
            i++;
        }
        areas.setData(mapAreas);
        model.addSeries(areas);        
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
        ChartSeries projetos = new ChartSeries();            
        projetos.setLabel("Projetos");        
        
        mapProjetos = getMapProjetosAreas();
        
        projetos.setData(mapProjetos);
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
     * @return the mapProjetosAreas
     */
    public Map<Object,Number> getMapProjetosAreas() {              
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
        Map<Object,Number> mapProjetosAreas = RelatorioAreasProjetos.mapAreasProjetos(listaProjetos);
        Map<Object,Number> mapPA = AreasProjetosSortByValue.sortByValue(mapProjetosAreas,"DESC");
        
        return mapPA;
    }

    /**
     * @return 
     */
    public Map<Object,Number> getMapEmpresasAreas() {
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
                if (itemAE.getArea_id()==itemCA.getArea_id()) {                    
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
        Map<Object,Number> mapEA = AreasEmpresasSortByValue.sortByValue(mapEmpresasAreas,"DESC");
        
        return mapEA;
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

    public void itemProjetoSelect(ItemSelectEvent event) {
        String label = null;
        ChartSeries cs = barChartProjetos.getSeries().get(event.getSeriesIndex());
        Map<Object,Number> map = cs.getData();
        int i = 0;
        for (Map.Entry<Object, Number> entry : map.entrySet()) {
            if (i == event.getItemIndex()) {
                label = (String) entry.getKey();
                selectedProjetoPontos = (Integer) entry.getValue();
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
     * @return the selectedProjeto
     */
    public Projetos getSelectedProjeto() {
        return selectedProjeto;
    }

    /**
     * @param selectedProjetoId
     */
    public void setSelectedProjeto(Integer selectedProjetoId) {
        selectedProjeto = ejbProjeto.find(selectedProjetoId);
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
