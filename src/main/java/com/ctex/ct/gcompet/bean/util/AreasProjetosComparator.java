/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean.util;

import com.ctex.ct.gcompet.modelo.relatorios.RelatorioAreasProjetos;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Ralfh
 */
public class AreasProjetosComparator implements Comparator<RelatorioAreasProjetos> {

    private List<Comparator<RelatorioAreasProjetos>> listComparators;
 
    @SafeVarargs
    public AreasProjetosComparator(Comparator<RelatorioAreasProjetos>... comparators) {
        this.listComparators = Arrays.asList(comparators);
    }    
    
    @Override
    public int compare(RelatorioAreasProjetos o1, RelatorioAreasProjetos o2) {
        
        for (Comparator<RelatorioAreasProjetos> comparator : listComparators) {
            int result = comparator.compare(o1, o2);
            if (result != 0) {
                return result;
            }
        }
        return 0;

        
/*        Float x1 = (float) o1.getAvaliacao()/o1.getAvaliadores();
        Integer I1 = (int) Math.ceil(x1)*100;
        Float x2 = (float) o2.getAvaliacao()/o2.getAvaliadores();
        Integer I2 = (int) Math.ceil(x2)*100;
        
        return I1 - I2; */
        
    }
    
    
}
