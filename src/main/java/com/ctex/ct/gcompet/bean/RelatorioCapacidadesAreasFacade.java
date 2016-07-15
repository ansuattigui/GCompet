/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.modelo.Capacidades;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioCapacidadesAreas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ralfh
 */
@Stateless
public class RelatorioCapacidadesAreasFacade extends AbstractFacade<RelatorioCapacidadesAreas> {
    @PersistenceContext(unitName = "com.ctex.ct_GCompet_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RelatorioCapacidadesAreasFacade() {
        super(RelatorioCapacidadesAreas.class);
    }
    
    @Override
    public List<RelatorioCapacidadesAreas> findAll() {
        TypedQuery<RelatorioCapacidadesAreas> tq;
        tq = getEntityManager().createNamedQuery("RelatorioCapacidadesAreas.findAll",RelatorioCapacidadesAreas.class);
        List<RelatorioCapacidadesAreas> lista = tq.getResultList();
        return lista;
    }

    public List<Object[]> findAll(Capacidades cap) {
        String sqlString = "SELECT ca.AREA_id as area_id, a.nome as area, "+
            "(SELECT count(ca1.AREA_id) FROM gcompet.capacidades_areas ca1 "+
            "WHERE ca1.CAPACIDADE_id = ca.CAPACIDADE_id and ca1.AREA_id = ca.AREA_id) as avaliadores, "+
            "(SELECT count(ca2.avaliacao) FROM gcompet.capacidades_areas ca2 WHERE ca2.avaliacao = 1 "+
            "AND ca2.CAPACIDADE_id = ca.CAPACIDADE_id AND ca2.AREA_id = ca.AREA_id) as avaliacao " +
            "FROM gcompet.capacidades_areas ca, gcompet.areas a " +
            "WHERE ca.CAPACIDADE_id = ? AND ca.AREA_id = a.id " +
            "GROUP BY ca.AREA_id  " +
            "HAVING avaliacao > 0 " +
            "ORDER BY ca.AREA_id ASC ";
        
        Query qr = getEntityManager().createNativeQuery(sqlString);
        qr.setParameter(1, cap.getId());
        List<Object[]> lista = qr.getResultList();
        
        return lista;
    }
    
//    public Connection getConnection() {
        
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gcompet",
                    "root", "arv0702");
            return conn;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Database.getConnection() Error -->" + ex.getMessage());
            return null;
        }
    }
         
        
/*        
        
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

        }



        return conn;
    }
*/
            
    public static void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (Exception ex) {
        }
    }        
 

}
