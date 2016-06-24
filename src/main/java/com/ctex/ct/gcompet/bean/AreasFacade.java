/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.modelo.Areas;
import com.ctex.ct.gcompet.modelo.Capacidades;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ralfh
 */
@Stateless
public class AreasFacade extends AbstractFacade<Areas> {

    @PersistenceContext(unitName = "com.ctex.ct_GCompet_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AreasFacade() {
        super(Areas.class);
    }
    
    public List<Areas> findAll(Capacidades cap) {       
        TypedQuery<Areas> tq;
        tq = getEntityManager().createNamedQuery("Areas.findAllNotAssociatedWithCapacidade", Areas.class);
//        tq.setParameter("parmcap", cap);
        
        List<Areas> lista = tq.getResultList();
        return lista;
    }
    
    
}
