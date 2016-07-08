/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.modelo.Areas;
import com.ctex.ct.gcompet.modelo.Projetos;
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
public class ProjetosFacade extends AbstractFacade<Projetos> {

    @PersistenceContext(unitName = "com.ctex.ct_GCompet_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProjetosFacade() {
        super(Projetos.class);
    }
    
    
    public List<Projetos> findAll(Areas area, Usuarios user) {       
        TypedQuery<Projetos> tq;
        tq = getEntityManager().createNamedQuery("Projetos.findAllNaoAvaliados", Projetos.class);
        tq.setParameter("area", area);        
        tq.setParameter("user", user);
        List<Projetos> lista = tq.getResultList();
        return lista;
    }
    
}
