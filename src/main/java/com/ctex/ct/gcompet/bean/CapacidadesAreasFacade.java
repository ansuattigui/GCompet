/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.modelo.Capacidades;
import com.ctex.ct.gcompet.modelo.CapacidadesAreas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ralfh
 */
@Stateless
public class CapacidadesAreasFacade extends AbstractFacade<CapacidadesAreas> {
    @PersistenceContext(unitName = "com.ctex.ct_GCompet_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CapacidadesAreasFacade() {
        super(CapacidadesAreas.class);
    }
    
    @Override
    public List<CapacidadesAreas> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(CapacidadesAreas.class));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<CapacidadesAreas> findAll(Capacidades cap) {
        TypedQuery<CapacidadesAreas> tq;
        tq = getEntityManager().createNamedQuery("CapacidadesAreas.findAllByCapacidade", CapacidadesAreas.class);
        tq.setParameter("capacidade", cap);
        List<CapacidadesAreas> lista = tq.getResultList();
        return lista;
    }
    
}
