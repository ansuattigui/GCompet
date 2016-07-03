/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template report, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author ralfh
 */
@ManagedBean(name = "relatorioContagem")
@SessionScoped
public class RelatorioContagem implements Serializable {
    
    private String jasper;
    private String relatorio;
    private ExternalContext context;
    private JasperPrint jasperPrint;
    private Connection connection;
    private String contentType;
    private String relatorioContagemAvaliacoes;
        
    @EJB 
    private CapacidadesAreasFacade ejbFacade;

 
    /**
     * Creates a new instance of RelatorioDivisao
     */
    public RelatorioContagem() { 
    }    

    /**
     * @return the jasper
     */
    public String getJasper() {
        jasper = getContext().getRealPath("user/relatorios/contagemAvaliacoes.jasper");
        return jasper;
    }

    /**
     * @param jasper the jasper to set
     */
    public void setJasper(String jasper) {
        this.jasper = jasper;
    }

    /**
     * @return the context
     */
    public ExternalContext getContext() {
        context = FacesContext.getCurrentInstance().getExternalContext();
        return context;
    }

    /**
     * @param context the context to set
     */
    public void setContext(ExternalContext context) {
        this.context = context;
    }

    /**
     * @return the jasperPrint
     */
    public JasperPrint getJasperPrint() {        
        ImageIcon logotipo = new ImageIcon(getContext().getRealPath("/resources/img/logo-ctex.png"));                
        HashMap hm = new HashMap<>();
        hm.put("par_logotipo",logotipo.getImage());        
        hm.put("par_nomerelat","Associação Capacidades Operacionais / Areas de Pesquisa");        
        try {   
            jasperPrint = JasperFillManager.fillReport(getJasper(),hm, getConnection());
        } catch (JRException ex) {
            Logger.getLogger(RelatorioContagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jasperPrint;
    }

    /**
     * @param jasperPrint the jasperPrint to set
     */
    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }
    
    
    /*
    public String getRelatorioContagemAvaliacoes() {
        relatorio = "/reports/contagem/capacidadesAreas.pdf";
        try {                
            JasperExportManager.exportReportToPdfFile(getJasperPrint(), getContext().getRealPath(relatorio));
        } catch (JRException ex) {
            Logger.getLogger(RelatorioContagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/user/relatorios/capacidadeArea";
    }

    /**
     * @param relatorioContagemAvaliacoes
     */
   /*
    public void setRelatorioContagemAvaliacoes(String relatorioContagemAvaliacoes) {
        this.relatorioContagemAvaliacoes = relatorioContagemAvaliacoes;
    }
    
    /**
     * @return the relatorio
     */
    public String getRelatorio() {        
        relatorio = "/reports/contagem/capacidadesAreas.pdf";
        contentType = FacesContext.getCurrentInstance().getExternalContext().getMimeType(relatorio);
        return relatorio;
    }

    /**
     * @param relatorio the relatorio to set
     */
    public void setRelatorio(String relatorio) {
        this.relatorio = relatorio;
    }

    /**
     * @return the connection
     */
    public Connection getConnection() {
        return getEjbFacade().getConnection();
    }

    /**
     * @param connection the connection to set
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * @return the ejbFacade
     */
    public CapacidadesAreasFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(CapacidadesAreasFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    /**
     * @return the relatorioContagemAvaliacoes
     */
    public String getRelatorioContagemAvaliacoes() {
        relatorio = "/reports/contagem/capacidadesAreas.pdf";
        try {                
            JasperExportManager.exportReportToPdfFile(getJasperPrint(), getContext().getRealPath(relatorio));
        } catch (JRException ex) {
            Logger.getLogger(RelatorioContagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        relatorioContagemAvaliacoes = "/user/relatorios/capacidadeArea";
        return relatorioContagemAvaliacoes;
    }

    /**
     * @param relatorioContagemAvaliacoes the relatorioContagemAvaliacoes to set
     */
    public void setRelatorioContagemAvaliacoes(String relatorioContagemAvaliacoes) {
        this.relatorioContagemAvaliacoes = relatorioContagemAvaliacoes;
    }

}
