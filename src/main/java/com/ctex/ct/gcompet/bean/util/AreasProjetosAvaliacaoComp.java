/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean.util;

import com.ctex.ct.gcompet.modelo.relatorios.RelatorioAreasProjetos;
import java.util.Comparator;

/**
 *
 * @author Ralfh
 */
public class AreasProjetosAvaliacaoComp implements Comparator<RelatorioAreasProjetos> {    
    @Override
    public int compare(RelatorioAreasProjetos o1, RelatorioAreasProjetos o2) {        
        int a1 = (int) o1.getAvaliacao();
        int a2 = (int) o2.getAvaliacao();
        return a1 - a2;
    }    
    
}
