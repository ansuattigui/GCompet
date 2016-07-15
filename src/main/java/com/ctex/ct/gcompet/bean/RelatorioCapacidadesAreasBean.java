/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template report, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.modelo.Capacidades;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioCapacidadesAreas;
import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;

/**
 *
 * @author ralfh
 */
@ManagedBean(name = "relatorioCapacidadesAreas")
@SessionScoped
public class RelatorioCapacidadesAreasBean implements Serializable {
    
    private String jasper;
    private String relatorio;
    private ExternalContext context;
    private JRDataSource jrDataSource;
    private JasperPrint jasperPrint;
    private Connection connection;
    private String contentType;
    private String relatorioAC;
    private Capacidades capacidade;
    private RelatorioCapacidadesAreas[] arrayRelatorio;
        
    @EJB 
    private RelatorioCapacidadesAreasFacade ejbFacade;

 
    /**
     * Creates a new instance of RelatorioDivisao
     */
    public RelatorioCapacidadesAreasBean() { 
    }    

    /**
     * @return the jasper
     */
    public String getJasper() {
        jasper = getContext().getRealPath("user/relatorios/capacidades/RelatorioCapacidadeAreas.jasper");
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
    
    public JRDataSource getJrDataSource() {
        
        jrDataSource = new JRBeanArrayDataSource(getArrayRelatorio());
        return jrDataSource;
    }
    
    public RelatorioCapacidadesAreas[] getArrayRelatorio() {
        List<Object[]> lista;
        lista = ejbFacade.findAll(capacidade);        
        arrayRelatorio = new RelatorioCapacidadesAreas[lista.size()];
        
        int i = 0;
        for (Object[] item : lista) {            
            Integer area_id = (Integer) item[0];
            String area = (String)item[1];
            long avaliadores = (long) item[2];
            long avaliacao = (long) item[3];

            RelatorioCapacidadesAreas rca = new RelatorioCapacidadesAreas();
            rca.setArea_id(area_id);
            rca.setArea(area);
            rca.setAvaliadores(avaliadores);
            rca.setAvaliacao(avaliacao);
            
            arrayRelatorio[i] = rca;
            i++;
        }
        return arrayRelatorio;
    }

    /**
     * @param arrayRelatorio the arrayRelatorio to set
     */
    public void setArrayRelatorio(RelatorioCapacidadesAreas[] arrayRelatorio) {
        this.arrayRelatorio = arrayRelatorio;
    }
    
    /**
     * @return the jasperPrint
     */
    public JasperPrint getJasperPrint() {  
        ImageIcon logotipo = new ImageIcon(getContext().getRealPath("resources/img/logo-ctex.png"));                
        HashMap hm = new HashMap<>();
        hm.put("par_logotipo",logotipo.getImage());        
        hm.put("par_nomerelat","Avaliação de Capacidades Operacionais: "+capacidade.getNome().toUpperCase());  
        hm.put("par_capacidade", capacidade.getId());
        try {   
            jasperPrint = JasperFillManager.fillReport(getJasper(),hm,getJrDataSource());
        } catch (JRException ex) {
            Logger.getLogger(RelatorioCapacidadesAreasBean.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return jasperPrint;
    }

    /**
     * @param jasperPrint the jasperPrint to set
     */
    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }
    
    /**
     * @return the relatorio
     */
    public String getRelatorio() {        
        relatorio = "/reports/capacidade/CapacidadesAreas.pdf";
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
     * @return 
     */
    public Connection getConnection() {
        connection = CapacidadesAreasFacade.getConnection();
        return connection;
    }

    /**
     * @param connection the connection to set
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public void closeConnection() {
        CapacidadesAreasFacade.closeConnection(connection);
    }

    /**
     * @return the ejbFacade
     */
    public RelatorioCapacidadesAreasFacade getEjbFacade() {
        return ejbFacade;
    }

    /**
     * @param ejbFacade the ejbFacade to set
     */
    public void setEjbFacade(RelatorioCapacidadesAreasFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    /**
     * @return the relatorioAC
     */
    public String getRelatorioAC() {
        relatorio = "/reports/capacidade/CapacidadesAreas.pdf";
        try {                
            JasperExportManager.exportReportToPdfFile(getJasperPrint(), getContext().getRealPath(relatorio));
        } catch (JRException ex) {
            Logger.getLogger(RelatorioCapacidadesAreasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        relatorioAC = "/user/relatorios/capacidades/CapacidadesAreas";
        return relatorioAC;
    }

    /**
     * @param relatorioAC the relatorioAC to set
     */
    public void setRelatorioAC(String relatorioAC) {
        this.relatorioAC = relatorioAC;
    }

    /**
     * @return the capacidade
     */
    public Capacidades getCapacidade() {
        return capacidade;
    }

    /**
     * @param capacidade the capacidade to set
     */
    public void setCapacidade(Capacidades capacidade) {
        this.capacidade = capacidade;
    }


}
