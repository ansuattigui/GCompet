/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.modelo.Projetos;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioAreasProjetos;
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
public class RelatorioProjetosAreasFacade extends AbstractFacade<RelatorioAreasProjetos> {
    @PersistenceContext(unitName = "com.ctex.ct_GCompet_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RelatorioProjetosAreasFacade() {
        super(RelatorioAreasProjetos.class);
    }
        
    public RelatorioAreasProjetos[] findAllAreasPorProjeto(Projetos proj, String ordem) {
        String sqlString = null;        
        if (null != ordem) switch (ordem) {
            case "area":
                sqlString = "SELECT pa.area_id as area_id, a.nome as area, "+                        
                    "(SELECT count(pa1.area_id) FROM gcompet.projetos_areas pa1 "+
                    "WHERE pa1.projeto_id = pa.projeto_id and pa1.area_id = pa.area_id) as avaliadores, "+                        
                    "(SELECT count(pa2.avaliacao) FROM gcompet.projetos_areas pa2 WHERE pa2.avaliacao = 1 "+
                    "AND pa2.projeto_id = pa.projeto_id AND pa2.area_id = pa.area_id) as avaliacao " +                        
                    "FROM gcompet.projetos_areas pa, gcompet.areas a " +
                    "WHERE pa.projeto_id = ? AND pa.AREA_id = a.id " +
                    "GROUP BY pa.AREA_id  " +
                    "HAVING avaliacao > 0 " +
                    "ORDER BY pa.AREA_id ASC ";
                break;
            case "peso":
                sqlString = "SELECT pa.area_id as area_id, a.nome as area, "+                        
                    "(SELECT count(pa1.area_id) FROM gcompet.projetos_areas pa1 "+
                    "WHERE pa1.projeto_id = pa.projeto_id and pa1.area_id = pa.area_id) as avaliadores, "+                        
                    "(SELECT count(pa2.avaliacao) FROM gcompet.projetos_areas pa2 WHERE pa2.avaliacao = 1 "+
                    "AND pa2.projeto_id = pa.projeto_id AND pa2.area_id = pa.area_id) as avaliacao " +                        
                    "FROM gcompet.projetos_areas pa, gcompet.areas a " +
                    "WHERE pa.projeto_id = ? AND pa.AREA_id = a.id " +
                    "GROUP BY pa.AREA_id  " +
                    "HAVING avaliacao > 0 " +
                    "ORDER BY avaliacao DESC ";
                break;            
        }
        
        Query qr = getEntityManager().createNativeQuery(sqlString);
        qr.setParameter(1, proj.getId());
        List<Object[]> lista = qr.getResultList();
        
        RelatorioAreasProjetos[] arrayAreasProjetos = new RelatorioAreasProjetos[lista.size()];
        
        int i = 0;
        for (Object[] item : lista) {            
            Integer area_id = (Integer) item[0];
            String area = (String)item[1];
            long avaliadores = (long) item[2];
            long avaliacao = (long) item[3];

            RelatorioAreasProjetos rap = new RelatorioAreasProjetos();
            rap.setArea_id(area_id);
            rap.setArea(area);
            rap.setAvaliadores(avaliadores);
            rap.setAvaliacao(avaliacao);
            
            arrayAreasProjetos[i] = rap;
            i++;
        }
        
        return arrayAreasProjetos;
    }
    
    
}
