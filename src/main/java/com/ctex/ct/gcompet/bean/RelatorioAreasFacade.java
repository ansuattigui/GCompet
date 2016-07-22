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

/**
 *
 * @author ralfh
 */
@Stateless
public class RelatorioAreasFacade extends AbstractFacade<RelatorioCapacidadesAreas> {
    @PersistenceContext(unitName = "com.ctex.ct_GCompet_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RelatorioAreasFacade() {
        super(RelatorioCapacidadesAreas.class);
    }
        
    public RelatorioCapacidadesAreas[] findAllAreasPorCapacidade(Capacidades cap, String ordem) {
        String sqlString = null;        
        if (null != ordem) switch (ordem) {
            case "area":
                // Consulta todas as áreas de pesquisa afins a uma determinada capacidade operativa selecionada
                // Ordenada pelo id da area de pesquisa
                sqlString = "SELECT ca.AREA_id as area_id, a.nome as area, "+
                        "(SELECT count(ca1.AREA_id) FROM gcompet.capacidades_areas ca1 "+
                        "WHERE ca1.CAPACIDADE_id = ca.CAPACIDADE_id and ca1.AREA_id = ca.AREA_id) as avaliadores, "+
                        "(SELECT count(ca2.avaliacao) FROM gcompet.capacidades_areas ca2 WHERE ca2.avaliacao = 1 "+
                        "AND ca2.CAPACIDADE_id = ca.CAPACIDADE_id AND ca2.AREA_id = ca.AREA_id) as avaliacao " +
                        "FROM gcompet.capacidades_areas ca, gcompet.areas a " +
                        "WHERE ca.CAPACIDADE_id = ? AND ca.AREA_id = a.id " +
                        "GROUP BY ca.AREA_id  " +
                        "HAVING avaliacao > 0 " +
                        "ORDER BY ca.AREA_id ASC ";
                break;
            case "peso":
                // Consulta todas as áreas de pesquisa afins a uma determinada capacidade operativa selecionada
                // Ordenada em ordem decrescente pelo número de avaliações positivas
                sqlString = "SELECT ca.AREA_id as area_id, a.nome as area, "+
                        "(SELECT count(ca1.AREA_id) FROM gcompet.capacidades_areas ca1 "+
                        "WHERE ca1.CAPACIDADE_id = ca.CAPACIDADE_id and ca1.AREA_id = ca.AREA_id) as avaliadores, "+
                        "(SELECT count(ca2.avaliacao) FROM gcompet.capacidades_areas ca2 WHERE ca2.avaliacao = 1 "+
                        "AND ca2.CAPACIDADE_id = ca.CAPACIDADE_id AND ca2.AREA_id = ca.AREA_id) as avaliacao " +
                        "FROM gcompet.capacidades_areas ca, gcompet.areas a " +
                        "WHERE ca.CAPACIDADE_id = ? AND ca.AREA_id = a.id " +
                        "GROUP BY ca.AREA_id  " +
                        "HAVING avaliacao > 0 " +
                "ORDER BY avaliacao DESC ";
                break;            
        }
        
        Query qr = getEntityManager().createNativeQuery(sqlString);
        qr.setParameter(1, cap.getId());
        List<Object[]> lista = qr.getResultList();
        
        RelatorioCapacidadesAreas[] arrayCapacidadesAreas = new RelatorioCapacidadesAreas[lista.size()];
        
        int i = 0;
        for (Object[] item : lista) {            
            Integer area_id = (Integer) item[0];
            String area = (String)item[1];
            long avaliadores = (long) item[2];
            long avaliacao = (long) item[3];

            RelatorioCapacidadesAreas rca = new RelatorioCapacidadesAreas();
            rca.setArea_id(area_id);
            rca.setArea(area);
            rca.setAvaliadores(avaliadores);
            rca.setAvaliacao(avaliacao);
            
            arrayCapacidadesAreas[i] = rca;
            i++;
        }
        
        return arrayCapacidadesAreas;
    }
    
    
}
