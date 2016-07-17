/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.modelo.Capacidades;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioCapacidadesAreas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ralfh
 */
@Stateless
public class RelatorioCapacidadesAreasFacade extends AbstractFacade<RelatorioCapacidadesAreas> {
    @PersistenceContext(unitName = "com.ctex.ct_GCompet_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RelatorioCapacidadesAreasFacade() {
        super(RelatorioCapacidadesAreas.class);
    }
    
    @Override
    public List<RelatorioCapacidadesAreas> findAll() {
        TypedQuery<RelatorioCapacidadesAreas> tq;
        tq = getEntityManager().createNamedQuery("RelatorioCapacidadesAreas.findAll",RelatorioCapacidadesAreas.class);
        List<RelatorioCapacidadesAreas> lista = tq.getResultList();
        return lista;
    }

    public List<Object[]> findAll(Capacidades cap) {
        String sqlString = "SELECT ca.AREA_id as area_id, a.nome as area, "+
            "(SELECT count(ca1.AREA_id) FROM gcompet.capacidades_areas ca1 "+
            "WHERE ca1.CAPACIDADE_id = ca.CAPACIDADE_id and ca1.AREA_id = ca.AREA_id) as avaliadores, "+
            "(SELECT count(ca2.avaliacao) FROM gcompet.capacidades_areas ca2 WHERE ca2.avaliacao = 1 "+
            "AND ca2.CAPACIDADE_id = ca.CAPACIDADE_id AND ca2.AREA_id = ca.AREA_id) as avaliacao " +
            "FROM gcompet.capacidades_areas ca, gcompet.areas a " +
            "WHERE ca.CAPACIDADE_id = ? AND ca.AREA_id = a.id " +
            "GROUP BY ca.AREA_id  " +
            "HAVING avaliacao > 0 " +
            "ORDER BY ca.AREA_id ASC ";
        
        Query qr = getEntityManager().createNativeQuery(sqlString);
        qr.setParameter(1, cap.getId());
        List<Object[]> lista = qr.getResultList();
        
        return lista;
    }
    
    public List<Object[]> findAllAreasProjetos() {
                
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
        return lista;
    }
    
    
    
    
    
    
    
    
    
}
