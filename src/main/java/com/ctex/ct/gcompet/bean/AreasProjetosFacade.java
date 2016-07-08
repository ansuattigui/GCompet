/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.modelo.Areas;
import com.ctex.ct.gcompet.modelo.AreasProjetos;
import com.ctex.ct.gcompet.modelo.Usuarios;
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
public class AreasProjetosFacade extends AbstractFacade<AreasProjetos> {
    @PersistenceContext(unitName = "com.ctex.ct_GCompet_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AreasProjetosFacade() {
        super(AreasProjetos.class);
    }
    
    @Override
    public List<AreasProjetos> findAll() {
        TypedQuery<AreasProjetos> tq;
        tq = getEntityManager().createNamedQuery("AreasProjetos.findAll",AreasProjetos.class);
        List<AreasProjetos> lista = tq.getResultList();
        return lista;
    }

    public List<AreasProjetos> findAll(Areas area) {
        TypedQuery<AreasProjetos> tq;
        tq = getEntityManager().createNamedQuery("AreasProjetos.findAllByArea", AreasProjetos.class);
        tq.setParameter("area", area);
        List<AreasProjetos> lista = tq.getResultList();
        return lista;
    }

    public List<AreasProjetos> findAll(Areas area, Usuarios user) {
        TypedQuery<AreasProjetos> tq;
        tq = getEntityManager().createNamedQuery("AreasProjetos.findAllByUsuario", AreasProjetos.class);
        tq.setParameter("area", area);
        tq.setParameter("user", user);
        List<AreasProjetos> lista = tq.getResultList();
        return lista;
    }
    
}
