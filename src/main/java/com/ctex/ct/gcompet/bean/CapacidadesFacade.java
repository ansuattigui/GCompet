/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.modelo.Capacidades;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ralfh
 */
@Stateless
public class CapacidadesFacade extends AbstractFacade<Capacidades> {

    @PersistenceContext(unitName = "com.ctex.ct_GCompet_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CapacidadesFacade() {
        super(Capacidades.class);
    }
    
}
