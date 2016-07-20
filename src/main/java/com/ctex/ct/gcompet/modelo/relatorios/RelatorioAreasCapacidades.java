/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.modelo.relatorios;

/**
 *
 * @author ralfh
 */

public class RelatorioAreasCapacidades implements Comparable {

    private Integer capacidade_id;
    private String capacidade;    
    private long avaliadores;
    private long avaliacao;

    public RelatorioAreasCapacidades() {
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

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
