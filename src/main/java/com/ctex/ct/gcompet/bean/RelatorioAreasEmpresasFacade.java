/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.modelo.Areas;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioAreasEmpresas;
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
public class RelatorioAreasEmpresasFacade extends AbstractFacade<RelatorioAreasEmpresas> {
    @PersistenceContext(unitName = "com.ctex.ct_GCompet_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RelatorioAreasEmpresasFacade() {
        super(RelatorioAreasEmpresas.class);
    }

    // Todas as Empresas relacionadas as areas avaliadas
    public RelatorioAreasEmpresas[] findAllAreasEmpresas() {        
        String sqlString = "SELECT ae.empresas_id,ae.areas_id,a.nome as area,em.nome as empresa,"+
            "count(ae.empresas_id) as avaliadores, "+
            "(SELECT count(ae1.empresas_id) FROM gcompet.areas_empresas ae1 "+
            " WHERE ae1.areas_id = ae.areas_id AND ae.empresas_id = ae1.empresas_id "+
            " AND ae1.avaliacao = 1 GROUP BY ae.areas_id, ae.empresas_id) as avaliacao "+
            " FROM gcompet.areas_empresas ae, gcompet.empresas em, gcompet.areas a "+
            " WHERE ae.empresas_id=em.id and ae.areas_id=a.id "+
            " GROUP BY ae.areas_id, ae.empresas_id "+
            " HAVING avaliacao > 0 "+
            " ORDER BY ae.empresas_id, ae.areas_id ";
        
        Query qr = getEntityManager().createNativeQuery(sqlString);
        List<Object[]> lista = qr.getResultList();            
        
        RelatorioAreasEmpresas[] arrayAreasEmpresas = new RelatorioAreasEmpresas[lista.size()];        
        
        int i = 0;
        for (Object[] item : lista) {            
            Integer empresa_id = (Integer) item[0];
            Integer area_id = (Integer) item[1];
            String area = (String)item[2];
            String empresa = (String)item[3];
            long avaliadores = (long) item[4];
            long avaliacao = (long) item[5];

            RelatorioAreasEmpresas rae = new RelatorioAreasEmpresas();
            rae.setEmpresa_id(empresa_id);
            rae.setArea_id(area_id);
            rae.setArea(area);
            rae.setEmpresa(empresa);
            rae.setAvaliadores(avaliadores);
            rae.setAvaliacao(avaliacao);
            
            arrayAreasEmpresas[i] = rae;
            i++;
        }        
        
        return arrayAreasEmpresas;
    }
    
    
    // Todas as Empresas relacionadas as areas avaliadas
    public RelatorioAreasEmpresas[] findAllAreasEmpresasPorArea(Areas area, String ordem) {        
        String sqlString = "SELECT ae.empresas_id,em.nome as empresa,"+
            "count(ae.empresas_id) as avaliadores, "+
            "(SELECT count(ae1.empresas_id) FROM gcompet.areas_empresas ae1 "+
            " WHERE ae1.areas_id = ae.areas_id AND ae.empresas_id = ae1.empresas_id "+
            " AND ae1.avaliacao = 1) as avaliacao " + //" GROUP BY ae.empresas_id) as avaliacao "
            " FROM gcompet.areas_empresas ae, gcompet.empresas em "+
            " WHERE ae.empresas_id=em.id and ae.areas_id= ? "+
            " GROUP BY ae.empresas_id "+
            " HAVING avaliacao > 0 "+
            " ORDER BY ae.empresas_id ";
        
        Query qr = getEntityManager().createNativeQuery(sqlString);
        qr.setParameter(1, area.getId());
        List<Object[]> lista = qr.getResultList();            
        
        RelatorioAreasEmpresas[] arrayAreasEmpresas = new RelatorioAreasEmpresas[lista.size()];        
        
        int i = 0;
        for (Object[] item : lista) {            
            Integer empresa_id = (Integer) item[0];
            String empresa = (String)item[1];
            long avaliadores = (long) item[2];
            long avaliacao = (long) item[3];

            RelatorioAreasEmpresas rae = new RelatorioAreasEmpresas();
            rae.setEmpresa_id(empresa_id);
            rae.setEmpresa(empresa);
            rae.setAvaliadores(avaliadores);
            rae.setAvaliacao(avaliacao);
            
            arrayAreasEmpresas[i] = rae;
            i++;
        }        
        
        return arrayAreasEmpresas;
    }
    
    
    
    
    
    
    
}
