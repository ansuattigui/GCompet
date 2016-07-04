/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.modelo.Capacidades;
import com.ctex.ct.gcompet.modelo.CapacidadesAreas;
import com.ctex.ct.gcompet.modelo.Usuarios;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;

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
        TypedQuery<CapacidadesAreas> tq;
        tq = getEntityManager().createNamedQuery("CapacidadesAreas.findAll",CapacidadesAreas.class);
        List<CapacidadesAreas> lista = tq.getResultList();
        return lista;
    }

    public List<CapacidadesAreas> findAll(Capacidades cap) {
        TypedQuery<CapacidadesAreas> tq;
        tq = getEntityManager().createNamedQuery("CapacidadesAreas.findAllByCapacidade", CapacidadesAreas.class);
        tq.setParameter("capacidade", cap);
        List<CapacidadesAreas> lista = tq.getResultList();
        return lista;
    }

    public List<CapacidadesAreas> findAll(Capacidades cap, Usuarios user) {
        TypedQuery<CapacidadesAreas> tq;
        tq = getEntityManager().createNamedQuery("CapacidadesAreas.findAllByUsuario", CapacidadesAreas.class);
        tq.setParameter("capacidade", cap);
        tq.setParameter("user", user);
        List<CapacidadesAreas> lista = tq.getResultList();
        return lista;
    }
    
    public Connection getConnection() {
        Context ctx;
        DataSource ds;
        Connection conn = null;
        try {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("jdbc/gcompet");
            conn = ds.getConnection("root","arv0702");
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(CapacidadesAreasFacade.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
/*            if (conn!= null) try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CapacidadesAreasFacade.class.getName()).log(Level.SEVERE, null, ex);
           }
*/
        }
        return conn;
    }
            
    
}
