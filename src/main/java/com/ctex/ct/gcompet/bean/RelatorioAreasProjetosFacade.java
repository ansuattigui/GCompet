/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.modelo.relatorios.RelatorioAreasProjetos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ralfh
 */
@Stateless
public class RelatorioAreasProjetosFacade extends AbstractFacade<RelatorioAreasProjetos> {
    @PersistenceContext(unitName = "com.ctex.ct_GCompet_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RelatorioAreasProjetosFacade() {
        super(RelatorioAreasProjetos.class);
    }

    
    public RelatorioAreasProjetos[] findAllAreasProjetos() {
        
        String sqlString = "SELECT ap.projeto_id,ap.area_id,a.nome as area,pj.nome as projeto,"+
            "count(ap.PROJETO_id) as avaliadores, "+
            "(SELECT count(ap1.PROJETO_id) FROM gcompet.areas_projetos ap1 "+
            " WHERE ap1.AREA_id = ap.AREA_id AND ap.PROJETO_id = ap1.PROJETO_id "+
            " AND ap1.avaliacao = 1 GROUP BY ap.AREA_id, ap.PROJETO_id) as avaliacao "+
            " FROM gcompet.areas_projetos ap, gcompet.projetos pj, gcompet.areas a "+
            " WHERE ap.PROJETO_id=PJ.id and ap.AREA_id=a.id "+
            " GROUP BY ap.AREA_id, ap.PROJETO_id "+
            " HAVING avaliacao > 0 "+
            " ORDER BY ap.PROJETO_id, ap.AREA_id ";
        
        Query qr = getEntityManager().createNativeQuery(sqlString);
        List<Object[]> lista = qr.getResultList();            
        
        RelatorioAreasProjetos[] arrayAreasProjetos = new RelatorioAreasProjetos[lista.size()];        
        
        int i = 0;
        for (Object[] item : lista) {            
            Integer projeto_id = (Integer) item[0];
            Integer area_id = (Integer) item[1];
            String area = (String)item[2];
            String projeto = (String)item[3];
            long avaliadores = (long) item[4];
            long avaliacao = (long) item[5];

            RelatorioAreasProjetos rca = new RelatorioAreasProjetos();
            rca.setProjeto_id(projeto_id);
            rca.setArea_id(area_id);
            rca.setArea(area);
            rca.setProjeto(projeto);
            rca.setAvaliadores(avaliadores);
            rca.setAvaliacao(avaliacao);
            
            arrayAreasProjetos[i] = rca;
            i++;
        }        
        
        return arrayAreasProjetos;
    }
    
    
    
    
    
    
    
    
    
}