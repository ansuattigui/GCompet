/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.modelo.Areas;
import com.ctex.ct.gcompet.modelo.AreasEmpresas;
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
public class AreasEmpresasFacade extends AbstractFacade<AreasEmpresas> {
    @PersistenceContext(unitName = "com.ctex.ct_GCompet_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AreasEmpresasFacade() {
        super(AreasEmpresas.class);
    }
    
    @Override
    public List<AreasEmpresas> findAll() {
        TypedQuery<AreasEmpresas> tq;
        tq = getEntityManager().createNamedQuery("AreasEmpresas.findAll",AreasEmpresas.class);
        List<AreasEmpresas> lista = tq.getResultList();
        return lista;
    }

    public List<AreasEmpresas> findAll(Areas area) {
        TypedQuery<AreasEmpresas> tq;
        tq = getEntityManager().createNamedQuery("AreasEmpresas.findAllByArea", AreasEmpresas.class);
        tq.setParameter("area", area);
        List<AreasEmpresas> lista = tq.getResultList();
        return lista;
    }

    public List<AreasEmpresas> findAll(Areas area, Usuarios user) {
        TypedQuery<AreasEmpresas> tq;
        tq = getEntityManager().createNamedQuery("AreasEmpresas.findAllByUsuario", AreasEmpresas.class);
        tq.setParameter("area", area);
        tq.setParameter("user", user);
        List<AreasEmpresas> lista = tq.getResultList();
        return lista;
    }
    
}
