/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.modelo.relatorios;

import java.io.Serializable;

/**
 *
 * @author ralfh
 */
public class RelatorioProjetosAreas implements Serializable {
    
    private Integer projetos_id;
    private Integer areas_id;
    private String area;
    private String projeto;
    private long avaliadores;
    private long avaliacao;

    public RelatorioProjetosAreas() {
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
     * @return the projetos_id
     */
    public Integer getProjetos_id() {
        return projetos_id;
    }

    /**
     * @param projetos_id the projetos_id to set
     */
    public void setProjetos_id(Integer projetos_id) {
        this.projetos_id = projetos_id;
    }

    /**
     * @return the projeto
     */
    public String getProjeto() {
        return projeto;
    }

    /**
     * @param projeto the projeto to set
     */
    public void setProjeto(String projeto) {
        this.projeto = projeto;
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

}
