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

public class RelatorioEmpresasAreas implements Comparable<RelatorioEmpresasAreas> {

    private Integer areas_id;
    private String area;    
    private Integer empresas_id;
    private String empresa;
    private long avaliadores;
    private long avaliacao;

    public RelatorioEmpresasAreas() {
    }

    /**
     * @return the areas_id
     */
    public Integer getAreas_id() {
        return areas_id;
    }

    /**
     * @param areas_id the areas_id to set
     */
    public void setAreas_id(Integer areas_id) {
        this.areas_id = areas_id;
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
     * @return the empresas_id
     */
    public Integer getEmpresas_id() {
        return empresas_id;
    }

    /**
     * @param empresas_id the empresas_id to set
     */
    public void setEmpresas_id(Integer empresas_id) {
        this.empresas_id = empresas_id;
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
    public int compareTo(RelatorioEmpresasAreas o) {        
        if(this.avaliacao > o.getAvaliacao()){
            return -1;
        } else if(this.avaliacao < o.getAvaliacao()){
            return 1;
        }   
        return this.getEmpresa().compareToIgnoreCase(o.getEmpresa());
    }

    

    // agrupa todas as contagens referentes as repetições das empresas na lista,
    // desconsiderando o efeito das áreas de pesquisa (Não sei se é isto que deve ser feito!!!)
    public static ArrayList<RelatorioEmpresasAreas> agrupaEmpresas(ArrayList lista) {
        
        ArrayList<RelatorioEmpresasAreas> listaEmpresas = new ArrayList<>();
        
        int i=0;
        int j=0;
        while (i < lista.size()-1) {                    
            j = i + 1;
            RelatorioEmpresasAreas item = (RelatorioEmpresasAreas) lista.get(i);
            while (j < lista.size()-1) {
                RelatorioEmpresasAreas itemSeguinte = (RelatorioEmpresasAreas) lista.get(j);
                if (Objects.equals(item.getEmpresas_id(), itemSeguinte.getEmpresas_id())) {                
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
    public static RelatorioEmpresasAreas[] castAreasEmpresas(ArrayList<RelatorioEmpresasAreas> listaEmpresas) {    
        RelatorioEmpresasAreas[] arrayEmpresasAreas = new RelatorioEmpresasAreas[listaEmpresas.size()];
        for (int a=0;a<listaEmpresas.size();a++) {
            arrayEmpresasAreas[a] = listaEmpresas.get(a);
        }        
        return arrayEmpresasAreas;
    }
    
    
    
}
