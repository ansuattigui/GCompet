/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template report, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.modelo.Areas;
import com.ctex.ct.gcompet.modelo.Capacidades;
import com.ctex.ct.gcompet.modelo.Empresas;
import com.ctex.ct.gcompet.modelo.Projetos;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioAreasEmpresas;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioAreasProjetos;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioCapacidadesAreas;
import java.io.Serializable;
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
@Named("graficoAreasCapacidades")
public class GraficoAreasBean implements Serializable {
    
    private BarChartModel barChartCapacidades;
    private BarChartModel barChartProjetos;
    private BarChartModel barChartEmpresas;
    private Areas area;
    
    //Array para o Subrelatório Empresas
    private RelatorioAreasEmpresas[] arrayEmpresasAreas;
    
    private Map<Object,Number> mapCapacidades;
    private Map<Object,Number> mapProjetos;
    private Map<Object,Number> mapEmpresas;
    
    private Capacidades selectedCapacidade;
    private Integer selectedCapacidadePontos;
    private Projetos selectedProjeto;
    private Integer selectedProjetoPontos;
    private Empresas selectedEmpresa;
    private Integer selectedEmpresaPontos;
    
    @EJB private RelatorioCapacidadesFacade ejbRCAFacade;
    @EJB private RelatorioProjetosFacade ejbRAPFacade;
    @EJB private RelatorioEmpresasFacade ejbRAEFacade;
    
    @EJB private CapacidadesFacade ejbCapacidades;
    @EJB private ProjetosFacade ejbProjeto;
    @EJB private EmpresasFacade ejbEmpresas;
    
    /**
     * @return the barChartCapacidades
     */
    public BarChartModel getGraficoCapacidades() {
        return barChartCapacidades;
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
        createGraficoCapacidades();
        createGraficoProjetos();
        createGraficoEmpresas();
        selectedCapacidade = null;
        selectedCapacidadePontos = null;
        selectedEmpresa = null;
        selectedEmpresaPontos = null;
        selectedProjeto = null;
        selectedProjetoPontos = null;
        return "/user/graficosnatela/areas/AreasCapacidadesGrafico";
    }
    
    private void createGraficoCapacidades() {
        barChartCapacidades = initBarModelCapacidades();
        barChartCapacidades.setAnimate(true);
        barChartCapacidades.setLegendPosition("ne");
        barChartCapacidades.getAxis(AxisType.X).setTickAngle(-90);
        Axis yAxis = barChartCapacidades.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setLabel("Pontos");
        Axis xAxisAreas = barChartCapacidades.getAxis(AxisType.X);
        //xAxisAreas.setLabel("id Area");
    }    
    private BarChartModel initBarModelCapacidades() {
        BarChartModel model = new BarChartModel();
        ChartSeries capacidades = new ChartSeries();            
        capacidades.setLabel("Capacidades Operativas do EB");        
        
        RelatorioCapacidadesAreas[] rca = ejbRCAFacade.findAllCapacidadesPorArea(area,"peso");
        mapCapacidades = new LinkedHashMap<>(rca.length);
        
        int i = 0;
        while(i < rca.length) {
            String label = rca[i].getCapacidade_id().toString();
            Integer pDivA = (int) rca[i].getAvaliacao();
            mapCapacidades.put(label,pDivA);
            i++;
        }
        capacidades.setData(mapCapacidades);
        model.addSeries(capacidades);        
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

        RelatorioAreasProjetos[] rpa = ejbRAPFacade.findAllProjetosPorArea(area,"peso");
        
        mapProjetos = new LinkedHashMap<>(rpa.length);
        
        int i = 0;
        while(i < rpa.length) {
            String label = rpa[i].getProjeto_id().toString();
            Integer pDivA = (int) rpa[i].getAvaliacao();
            mapProjetos.put(label,pDivA);
            i++;
        }
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
        
        RelatorioAreasEmpresas[] rea = ejbRAEFacade.findAllEmpresasPorArea(area, "peso");      
        
        mapEmpresas = new LinkedHashMap<>(rea.length);
        
        int i = 0;
        while(i < rea.length) {
            String label = rea[i].getEmpresa_id().toString();
            Integer pDivA = (int) rea[i].getAvaliacao();
            mapEmpresas.put(label,pDivA);
            i++;
        }
        empresas.setData(mapEmpresas);
        model.addSeries(empresas);
        return model;        
    }
    
    //Consulta as Areas de pesquisa afins da area selecionada - Ordem do id das áreas de pesquisa
    public RelatorioCapacidadesAreas[] getArrayAreasCapacidadePorArea() {
        return ejbRCAFacade.findAllCapacidadesPorArea(area,"capacidade");
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
     * @return the ejbRCAFacade
     */
    public RelatorioCapacidadesFacade getEjbRCAFacade() {
        return ejbRCAFacade;
    }

    /**
     * @return the area
     */
    public Areas getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(Areas area) {
        this.area = area;
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
                setSelectedCapacidade(Integer.parseInt(label));
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
     * @return the selectedCapacidade
     */
    public Capacidades getSelectedCapacidade() {
        return selectedCapacidade;
    }

    /**
     * @param selectedCapacidadeId
     */
    public void setSelectedCapacidade(Integer selectedCapacidadeId) {        
        selectedCapacidade = ejbCapacidades.find(selectedCapacidadeId);                
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
