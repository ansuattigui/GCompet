/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.modelo.Areas;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioCapacidadesAreas;
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
public class RelatorioCapacidadesFacade extends AbstractFacade<RelatorioCapacidadesAreas> {
    @PersistenceContext(unitName = "com.ctex.ct_GCompet_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RelatorioCapacidadesFacade() {
        super(RelatorioCapacidadesAreas.class);
    }
    
    // Todas as Capacidades relacionadas a uma determinada area avaliada
    public RelatorioCapacidadesAreas[] findAllCapacidadesPorArea(Areas area, String ordem) {
        String sqlString = null;        
        if (null != ordem) switch (ordem) {
            case "capacidade":
                sqlString = "SELECT ca.capacidade_id as capacidade_id, c.nome as capacidade, "+
                        "(SELECT count(ca1.capacidade_id) FROM gcompet.capacidades_areas ca1 "+
                        "WHERE ca1.CAPACIDADE_id = ca.CAPACIDADE_id and ca1.AREA_id = ca.AREA_id) as avaliadores, "+
                        "(SELECT count(ca2.avaliacao) FROM gcompet.capacidades_areas ca2 WHERE ca2.avaliacao = 1 "+
                        "AND ca2.CAPACIDADE_id = ca.CAPACIDADE_id AND ca2.AREA_id = ca.AREA_id) as avaliacao " +                        
                        "FROM gcompet.capacidades_areas ca, gcompet.capacidades c " +
                        "WHERE ca.area_id = ? AND ca.capacidade_id = c.id " +
                        "GROUP BY ca.capacidade_id  " +
                        "HAVING avaliacao > 0 " +
                        "ORDER BY ca.capacidade_id ASC ";
                break;
            case "peso":
                sqlString = "SELECT ca.capacidade_id as capacidade_id, c.nome as capacidade, "+
                        "(SELECT count(ca1.capacidade_id) FROM gcompet.capacidades_areas ca1 "+
                        "WHERE ca1.CAPACIDADE_id = ca.CAPACIDADE_id and ca1.AREA_id = ca.AREA_id) as avaliadores, "+
                        "(SELECT count(ca2.avaliacao) FROM gcompet.capacidades_areas ca2 WHERE ca2.avaliacao = 1 "+
                        "AND ca2.CAPACIDADE_id = ca.CAPACIDADE_id AND ca2.AREA_id = ca.AREA_id) as avaliacao " +                        
                        "FROM gcompet.capacidades_areas ca, gcompet.capacidades c " +
                        "WHERE ca.area_id = ? AND ca.capacidade_id = c.id " +
                        "GROUP BY ca.capacidade_id  " +
                        "HAVING avaliacao > 0 " +
                        "ORDER BY avaliacao DESC ";
                break;            
        }
        
        Query qr = getEntityManager().createNativeQuery(sqlString);
        qr.setParameter(1, area.getId());
        List<Object[]> lista = qr.getResultList();
        
        RelatorioCapacidadesAreas[] arrayAreasCapacidades = new RelatorioCapacidadesAreas[lista.size()];
        
        int i = 0;
        for (Object[] item : lista) {            
            Integer capacidade_id = (Integer) item[0];
            String capacidade = (String)item[1];
            long avaliadores = (long) item[2];
            long avaliacao = (long) item[3];

            RelatorioCapacidadesAreas rac = new RelatorioCapacidadesAreas();
            rac.setCapacidade_id(capacidade_id);
            rac.setCapacidade(capacidade);
            rac.setAvaliadores(avaliadores);
            rac.setAvaliacao(avaliacao);
            
            arrayAreasCapacidades[i] = rac;
            i++;
        }
        
        return arrayAreasCapacidades;
    }
    
    
}
