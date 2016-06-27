/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.modelo.Usuarios;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ralfh
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "com.ctex.ct_GCompet_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    
    public Usuarios validate(String usuario, String senha)  {
        Usuarios valido;
        TypedQuery<Usuarios> tq = getEntityManager().createNamedQuery("Usuarios.findByLogin", Usuarios.class);
        tq.setParameter("pusername", usuario);
        tq.setParameter("ppassword", encryptPassword(senha));
        try {
            valido = (tq.getSingleResult());
        } catch (NoResultException nre) {
            valido = null;
        }
        return valido;
    }
    
   public String encryptPassword(String password) {
        String encPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes("UTF-8")); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            encPassword = bigInt.toString(16);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            //Logger.getLogger(PasswordTest.class.getName()).log(Level.SEVERE, null, ex);
        }       
        return encPassword;
    }    
    
    
    
}
