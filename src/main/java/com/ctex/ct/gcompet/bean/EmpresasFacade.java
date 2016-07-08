/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.modelo.Areas;
import com.ctex.ct.gcompet.modelo.Empresas;
import com.ctex.ct.gcompet.modelo.Usuarios;
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
public class EmpresasFacade extends AbstractFacade<Empresas> {

    @PersistenceContext(unitName = "com.ctex.ct_GCompet_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpresasFacade() {
        super(Empresas.class);
    }
    
    public List<Empresas> findAll(Areas area, Usuarios user) {       
        TypedQuery<Empresas> tq;
        tq = getEntityManager().createNamedQuery("Empresas.findAllNaoAvaliadas", Empresas.class);
        tq.setParameter("area", area);        
        tq.setParameter("user", user);
        List<Empresas> lista = tq.getResultList();
        return lista;
    }    
}
