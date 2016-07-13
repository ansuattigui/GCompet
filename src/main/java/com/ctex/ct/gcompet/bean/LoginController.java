/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.modelo.Perfil;
import com.ctex.ct.gcompet.modelo.Usuarios;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Ralfh
 */
@Named("loginController")
@SessionScoped
public class LoginController implements Serializable {
    
    @EJB
    private UsuariosFacade ejbFacade;
    private String senha;
    private String msg;
    private String usuario;
    private Usuarios userLoggedIn;
    private Perfil perfilLogged;
 
    private static final long serialVersionUID = 1094801825228386363L;
    
    public LoginController() {
    }
    
    public String getSenha() {
        return senha;
    }
 
    public void setSenha(String senha) {
        this.senha = senha;
    }
 
    public String getMsg() {
        return msg;
    }
 
    public void setMsg(String msg) {
        this.msg = msg;
    }
 
    public String getUsuario() {
        return usuario;
    }
 
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
 
    //validate login
    public String login() {        
        Usuarios user = getEjbFacade().validate(usuario, senha);   
        FacesContext context = FacesContext.getCurrentInstance();
        if (user!=null) {
            userLoggedIn = user;
            perfilLogged = user.getPerfil();
            context.getExternalContext().getSessionMap().put("usuarioLogado",user);
            return "index?faces-redirect=true";
        } else {
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, new FacesMessage("Usuário não encontrado"));
            return "login?faces-redirect=true";
        }
    }
    
    //logout event, invalidate session
    public String logout() {               
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("usuarioLogado");
        return "/login?faces-redirect=true";
    }    

    /**
     * @return the ejbFacade
     */
    private UsuariosFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @return the userLoggedIn
     */
    public Usuarios returnUserLoggedIn() {
        return userLoggedIn;
    }

    public Usuarios getUserLoggedIn() {
        return userLoggedIn;
    }
    
    /**
     * @param userLoggedIn the userLoggedIn to set
     */
    public void setUserLoggedIn(Usuarios userLoggedIn) {
        this.userLoggedIn = userLoggedIn;
    }

    /**
     * @return the perfilLogged
     */
    public Perfil getPerfilLogged() {
        return perfilLogged;
    }

    /**
     * @param perfilLogged the perfilLogged to set
     */
    public void setPerfilLogged(Perfil perfilLogged) {
        this.perfilLogged = perfilLogged;
    }

}
