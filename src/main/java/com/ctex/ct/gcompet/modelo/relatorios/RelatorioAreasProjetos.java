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

public class RelatorioAreasProjetos implements Comparable  {
    
    private Integer projeto_id;
    private Integer area_id;
    private String area;
    private String projeto;
    private long avaliadores;
    private long avaliacao;

    public RelatorioAreasProjetos() {
    }

    /**
     * @return the projeto_id
     */
    public Integer getProjeto_id() {
        return projeto_id;
    }

    /**
     * @param projeto_id the projeto_id to set
     */
    public void setProjeto_id(Integer projeto_id) {
        this.projeto_id = projeto_id;
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

    @Override
    public int compareTo(Object compProjPeso) {
        int compareage=(int) ((RelatorioAreasProjetos)compProjPeso).getAvaliacao();
        /* For Ascending order*/
        //return this.avaliacao-compareage;

        /* For Descending order do like this */
        return (int) (compareage-this.avaliacao);
    }

    
    // agrupa todas as contagens referentes as repetições dos projetos na lista,
    // desconsiderando o efeito das áreas de pesquisa (Não sei se é isto que deve ser feito!!!)
    public static RelatorioAreasProjetos[] agrupaProjetos(ArrayList lista) {
        
        
        RelatorioAreasProjetos[] arrayAreasProjetosCapacidade = new RelatorioAreasProjetos[lista.size()];
        int i=0;
        int j=0;
        int k=0;
        while (i < lista.size()-1) {                    
            j = i + 1;
            RelatorioAreasProjetos item = (RelatorioAreasProjetos) lista.get(i);
            while (j < lista.size()-1) {
                RelatorioAreasProjetos itemSeguinte = (RelatorioAreasProjetos) lista.get(j);
                if (Objects.equals(item.getProjeto_id(), itemSeguinte.getProjeto_id())) {                
                    long avaliacao = item.getAvaliacao()+itemSeguinte.getAvaliacao();
                    long avaliadores = item.getAvaliadores() + itemSeguinte.getAvaliadores();
                    item.setAvaliacao(avaliacao);
                    item.setAvaliadores(avaliadores);
                    j++;
                } else {
                    arrayAreasProjetosCapacidade[k] = item;
                    k++;
                    i = j;
                    break;
                }
            }
            if (j == lista.size()-1) {
                arrayAreasProjetosCapacidade[k] = item;
                i = j ;
            }
        }        
        
/*        
        ArrayList<RelatorioAreasProjetos> listaProjetos = new ArrayList<>();
        
        int i=0;
        int j=0;
        while (i < lista.size()-1) {                    
            j = i + 1;
            RelatorioAreasProjetos item = (RelatorioAreasProjetos) lista.get(i);
            while (j < lista.size()-1) {
                RelatorioAreasProjetos itemSeguinte = (RelatorioAreasProjetos) lista.get(j);
                if (Objects.equals(item.getProjeto_id(), itemSeguinte.getProjeto_id())) {                
                    long avaliacaoProjeto = item.getAvaliacao()+itemSeguinte.getAvaliacao();
                    long avaliadoresProjeto = item.getAvaliadores() + itemSeguinte.getAvaliadores();
                    item.setAvaliacao(avaliacaoProjeto);
                    item.setAvaliadores(avaliadoresProjeto);
                    j++;
                } else {
                    listaProjetos.add(item);
                    i = j;
                    break;
                }
            }
            if (j == lista.size()-1) {
                listaProjetos.add(item);
                i = j ;
            }
        }        
        return listaProjetos;
*/        
        return arrayAreasProjetosCapacidade;
        
    }
    
    // Encapsula uma lista de objetos do tipo RelatorioAreasProjetos em um array do tipo RelatorioAreasProjetos
    public static RelatorioAreasProjetos[] castAreasProjetos(ArrayList<RelatorioAreasProjetos> listaProjetos) {    
        RelatorioAreasProjetos[] arrayAreasProjetos = new RelatorioAreasProjetos[listaProjetos.size()];
        for (int a=0;a<listaProjetos.size();a++) {
            arrayAreasProjetos[a] = listaProjetos.get(a);
        }        
        return arrayAreasProjetos;
    }
    

}
