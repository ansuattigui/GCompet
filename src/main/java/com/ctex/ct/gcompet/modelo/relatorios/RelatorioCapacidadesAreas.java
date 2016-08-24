/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.modelo.relatorios;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author ralfh
 */

public class RelatorioCapacidadesAreas implements Comparable<RelatorioCapacidadesAreas> {

    private Integer capacidade_id;
    private String capacidade;    
    private Integer area_id;
    private String area;    
    private long avaliadores;
    private long avaliacao;

    public RelatorioCapacidadesAreas() {
    }

    /**
     * @return the area_id
     */
    public Integer getArea_id() {
        return area_id;
    }

    /**
     * @param area_id the area_id to set
     */
    public void setArea_id(Integer area_id) {
        this.area_id = area_id;
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return the avaliadores
     */
    public long getAvaliadores() {
        return avaliadores;
    }

    /**
     * @param avaliadores the avaliadores to set
     */
    public void setAvaliadores(long avaliadores) {
        this.avaliadores = avaliadores;
    }

    /**
     * @return the avaliacao
     */
    public long getAvaliacao() {
        return avaliacao;
    }

    /**
     * @param avaliacao the avaliacao to set
     */
    public void setAvaliacao(long avaliacao) {
        this.avaliacao = avaliacao;
    }


    /**
     * @return the capacidade_id
     */
    public Integer getCapacidade_id() {
        return capacidade_id;
    }

    /**
     * @param capacidade_id the capacidade_id to set
     */
    public void setCapacidade_id(Integer capacidade_id) {
        this.capacidade_id = capacidade_id;
    }

    /**
     * @return the capacidade
     */
    public String getCapacidade() {
        return capacidade;
    }

    /**
     * @param capacidade the capacidade to set
     */
    public void setCapacidade(String capacidade) {
        this.capacidade = capacidade;
    }

    // agrupa todas as contagens referentes as repetições das capacidades na lista,
    // desconsiderando o efeito das áreas de pesquisa (Não sei se é isto que deve ser feito!!!)
    public static ArrayList<RelatorioCapacidadesAreas> agrupaCapacidades(ArrayList lista) {
        
        ArrayList<RelatorioCapacidadesAreas> listaCapacidades = new ArrayList<>();
        
        int i=0;
        int j=0;
        while (i < lista.size()-1) {                    
            j = i + 1;
            RelatorioCapacidadesAreas item = (RelatorioCapacidadesAreas) lista.get(i);
            while (j < lista.size()-1) {
                RelatorioCapacidadesAreas itemSeguinte = (RelatorioCapacidadesAreas) lista.get(j);
                if (Objects.equals(item.getCapacidade_id(), itemSeguinte.getCapacidade_id())) {                
                    long avaliacaoCapacidade = item.getAvaliacao()+itemSeguinte.getAvaliacao();
                    long avaliadoresCapacidade = item.getAvaliadores() + itemSeguinte.getAvaliadores();
                    item.setAvaliacao(avaliacaoCapacidade);
                    item.setAvaliadores(avaliadoresCapacidade);
                    j++;
                } else {
                    listaCapacidades.add(item);
                    i = j;
                    break;
                }
            }
            if (j == lista.size()-1) {
                listaCapacidades.add(item);
                i = j ;
            }
        }        
        return listaCapacidades;
    }
    
    // Encapsula uma lista de objetos do tipo RelatorioCapacidadesAreas em um array do tipo RelatorioCapacidadesAreas
    public static RelatorioCapacidadesAreas[] castCapacidadesAreas(ArrayList<RelatorioCapacidadesAreas> listaCapacidades) {    
        RelatorioCapacidadesAreas[] arrayCapacidadesAreas = new RelatorioCapacidadesAreas[listaCapacidades.size()];
        for (int a=0;a<listaCapacidades.size();a++) {
            arrayCapacidadesAreas[a] = listaCapacidades.get(a);
        }        
        return arrayCapacidadesAreas;
    }
    
    public static Map<Object,Number> mapCapacidadesAreas(ArrayList<RelatorioCapacidadesAreas> listaCapacidades) {        
        Map<Object,Number> mapCapacidades = new LinkedHashMap<>(listaCapacidades.size());
        int i = 0;
        while(i < listaCapacidades.size()) {
            String label = listaCapacidades.get(i).getCapacidade_id().toString();
            Integer x = (int)listaCapacidades.get(i).getAvaliacao();
            mapCapacidades.put(label,x);
            i++;
        }        
        return mapCapacidades;
    }
    
    
    
    @Override
    public int compareTo(RelatorioCapacidadesAreas o) {
        return (int) ((int)o.getAvaliacao()-(int)this.getAvaliacao());
    }
    
    
}
