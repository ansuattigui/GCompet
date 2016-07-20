/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.modelo.relatorios;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author ralfh
 */

public class RelatorioAreasEmpresas implements Comparable<RelatorioAreasEmpresas> {

    private Integer empresa_id;
    private String empresa;
    private Integer area_id;
    private String area;    
    private long avaliadores;
    private long avaliacao;

    public RelatorioAreasEmpresas() {
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
     * @return the empresa_id
     */
    public Integer getEmpresa_id() {
        return empresa_id;
    }

    /**
     * @param empresa_id the empresa_id to set
     */
    public void setEmpresa_id(Integer empresa_id) {
        this.empresa_id = empresa_id;
    }

    /**
     * @return the empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    @Override
    public int compareTo(RelatorioAreasEmpresas o) {        
        if(this.avaliacao > o.getAvaliacao()){
            return -1;
        } else if(this.avaliacao < o.getAvaliacao()){
            return 1;
        }   
        return this.getEmpresa().compareToIgnoreCase(o.getEmpresa());
    }

    

    // agrupa todas as contagens referentes as repetições das empresas na lista,
    // desconsiderando o efeito das áreas de pesquisa (Não sei se é isto que deve ser feito!!!)
    public static ArrayList<RelatorioAreasEmpresas> agrupaEmpresas(ArrayList lista) {
        
        ArrayList<RelatorioAreasEmpresas> listaEmpresas = new ArrayList<>();
        
        int i=0;
        int j=0;
        while (i < lista.size()-1) {                    
            j = i + 1;
            RelatorioAreasEmpresas item = (RelatorioAreasEmpresas) lista.get(i);
            while (j < lista.size()-1) {
                RelatorioAreasEmpresas itemSeguinte = (RelatorioAreasEmpresas) lista.get(j);
                if (Objects.equals(item.getEmpresa_id(), itemSeguinte.getEmpresa_id())) {                
                    long avaliacaoEmpresa = item.getAvaliacao()+itemSeguinte.getAvaliacao();
                    long avaliadoresEmpresa = item.getAvaliadores() + itemSeguinte.getAvaliadores();
                    item.setAvaliacao(avaliacaoEmpresa);
                    item.setAvaliadores(avaliadoresEmpresa);
                    j++;
                } else {
                    listaEmpresas.add(item);
                    i = j;
                    break;
                }
            }
            if (j == lista.size()-1) {
                listaEmpresas.add(item);
                i = j ;
            }
        }        
        return listaEmpresas;
    }
    
    // Encapsula uma lista de objetos do tipo RelatorioAreasEmpresas em um array do tipo RelatorioAreasEmpresas
    public static RelatorioAreasEmpresas[] castAreasEmpresas(ArrayList<RelatorioAreasEmpresas> listaEmpresas) {    
        RelatorioAreasEmpresas[] arrayAreasEmpresas = new RelatorioAreasEmpresas[listaEmpresas.size()];
        for (int a=0;a<listaEmpresas.size();a++) {
            arrayAreasEmpresas[a] = listaEmpresas.get(a);
        }        
        return arrayAreasEmpresas;
    }
    
    
    
}
