/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template report, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.modelo.Capacidades;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioAreasEmpresas;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioAreasProjetos;
import com.ctex.ct.gcompet.modelo.relatorios.RelatorioCapacidadesAreas;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
@RequestScoped
public class RelatorioCapacidadesAreasBean implements Serializable {
    
    private String jasperCapacidadesAreas;
    private String relatorio;
    private ExternalContext context;
    private JRDataSource jrDataSourceMainReport;
    private JRDataSource jrDataSourceSubReport1;
    private JRDataSource jrDataSourceSubReport2;
    private JasperPrint jasperPrintCapacidadesAreas;
    private JasperPrint jasperPrintProjetosAreas;
    private Connection connection;
    private String relatorioAC;
    private Capacidades capacidade;
    private RelatorioCapacidadesAreas[] arrayCapacidadesAreas;
    private RelatorioAreasProjetos[] arrayAreasProjetos;
    private RelatorioAreasEmpresas[] arrayAreasEmpresas;
    private RelatorioAreasProjetos[] arrayAreasProjetosCapacidade;
        
    @EJB 
    private RelatorioCapacidadesAreasFacade ejbFacade;

 
    /**
     * Creates a new instance of RelatorioDivisao
     */
    public RelatorioCapacidadesAreasBean() { 
    }    

    /**
     * @return the jasperCapacidadesAreas
     */
    public String getJasperCapacidadesAreas() {
        jasperCapacidadesAreas = getContext().getRealPath("user/relatorios/capacidades/RelatorioCapacidadeAreas.jasper");
        return jasperCapacidadesAreas;
    }

    /**
     * @param jasperCapacidadesAreas the jasperCapacidadesAreas to set
     */
    public void setJasperCapacidadesAreas(String jasperCapacidadesAreas) {
        this.jasperCapacidadesAreas = jasperCapacidadesAreas;
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
    
    public JRDataSource getJrDataSourceMainReport() {        
        jrDataSourceMainReport = new JRBeanArrayDataSource(arrayCapacidadesAreas);
        return jrDataSourceMainReport;
    }
    
    /**
     * @return the jrDataSourceSubReport1
     */
    public JRDataSource getJrDataSourceSubReport1() {
        jrDataSourceSubReport1 = new JRBeanArrayDataSource(getArrayAreasProjetosCapacidade());
        return jrDataSourceSubReport1;
    }

    /**
     * @param jrDataSourceSubReport1 the jrDataSourceSubReport1 to set
     */
    public void setJrDataSourceSubReport1(JRDataSource jrDataSourceSubReport1) {
        this.jrDataSourceSubReport1 = jrDataSourceSubReport1;
    }

    /**
     * @return the jrDataSourceSubReport2
     */
    public JRDataSource getJrDataSourceSubReport2() {
        jrDataSourceSubReport2 = new JRBeanArrayDataSource(getArrayCapacidadesAreas());
        return jrDataSourceSubReport2;
    }

    /**
     * @param jrDataSourceSubReport2 the jrDataSourceSubReport2 to set
     */
    public void setJrDataSourceSubReport2(JRDataSource jrDataSourceSubReport2) {
        this.jrDataSourceSubReport2 = jrDataSourceSubReport2;
    }
    
    public RelatorioCapacidadesAreas[] getArrayCapacidadesAreas() {
        List<Object[]> lista;
        lista = ejbFacade.findAll(capacidade);        
        arrayCapacidadesAreas = new RelatorioCapacidadesAreas[lista.size()];
        
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
            
            arrayCapacidadesAreas[i] = rca;
            i++;
        }
        return arrayCapacidadesAreas;
    }

    /**
     * @param arrayCapacidadesAreas the arrayCapacidadesAreas to set
     */
    public void setArrayCapacidadesAreas(RelatorioCapacidadesAreas[] arrayCapacidadesAreas) {
        this.arrayCapacidadesAreas = arrayCapacidadesAreas;
    }
    
    /**
     * @return the arrayAreasProjetos
     */
    public RelatorioAreasProjetos[] getArrayAreasProjetos() {
        List<Object[]> lista;
        lista = ejbFacade.findAllAreasProjetos();        
        arrayAreasProjetos = new RelatorioAreasProjetos[lista.size()];
        
        int i = 0;
        for (Object[] item : lista) {            
            Integer projeto_id = (Integer) item[0];
            Integer area_id = (Integer) item[1];
            String area = (String)item[2];
            String projeto = (String)item[3];
            long avaliadores = (long) item[4];
            long avaliacao = (long) item[5];

            RelatorioAreasProjetos rca = new RelatorioAreasProjetos();
            rca.setProjeto_id(projeto_id);
            rca.setArea_id(area_id);
            rca.setArea(area);
            rca.setProjeto(projeto);
            rca.setAvaliadores(avaliadores);
            rca.setAvaliacao(avaliacao);
            
            arrayAreasProjetos[i] = rca;
            i++;
        }
        return arrayAreasProjetos;
    }

    /**
     * @param arrayAreasProjetos the arrayAreasProjetos to set
     */
    public void setArrayAreasProjetos(RelatorioAreasProjetos[] arrayAreasProjetos) {
        this.arrayAreasProjetos = arrayAreasProjetos;
    }

    
    /**
     * @return the arrayAreasProjetosCapacidade
     */
    public RelatorioAreasProjetos[] getArrayAreasProjetosCapacidade() {      
        
        // popula uma lista com os relacionamentos entre Capacidades Operativas e Areas de Pesquisa
        getArrayCapacidadesAreas();
        // popula uma lista com os relacionamentos entre Areas de Pesquisa e projetos do CTEx
        getArrayAreasProjetos();
        
        // efetua uma relação de correspondencia direta entre os arrays e produz uma lista co o resultado
        // os projetos relacionados a áreas que não constem da lista Capacidade/Areas não são contados
        // cria uma lista com os projetos selecionadas 
        ArrayList<RelatorioAreasProjetos> lista = new ArrayList<>();        
        for(RelatorioCapacidadesAreas itemCA : arrayCapacidadesAreas) {            
            for(RelatorioAreasProjetos itemAP : arrayAreasProjetos) {                
                if (Objects.equals(itemAP.getArea_id(), itemCA.getArea_id())) {                    
                    lista.add(itemAP);                    
                }                
            }            
        }       
        
        // ordena a lista de projetos pelo seu id 
        Collections.sort(lista, new Comparator() {
            @Override
            public int compare(Object r1, Object r2) {
                Integer id1 = ((RelatorioAreasProjetos) r1).getProjeto_id();
                Integer id2 = ((RelatorioAreasProjetos) r2).getProjeto_id();
                // ordem crescente
                 return id1.compareTo(id2);

                // ordem decrescente
                //return id2.compareTo(id1);
            }
        });
        
        // agrupa todas as contagens referentes as repetições dos projetos na lista,
        // desconsiderando o efeito das áreas de pesquisa (Não sei se é isto que deve ser feito!!!)
        ArrayList<RelatorioAreasProjetos> listaProjetos = RelatorioAreasProjetos.agrupaProjetos(lista);
        
        // devolve um array com o resultado dos projetos resultantes da pesquisa 
        // com a Capacidade Operativa selecionada.
        arrayAreasProjetosCapacidade = RelatorioAreasProjetos.castAreasProjetos(listaProjetos);

        return arrayAreasProjetosCapacidade;
    }

    /**
     * @param arrayAreasProjetosCapacidade the arrayAreasProjetosCapacidade to set
     */
    public void setArrayAreasProjetosCapacidade(RelatorioAreasProjetos[] arrayAreasProjetosCapacidade) {
        this.arrayAreasProjetosCapacidade = arrayAreasProjetosCapacidade;
    }
    
    
    /**
     * @return the jasperPrintCapacidadesAreas
     */
    public JasperPrint getJasperPrintCapacidadesAreas() {  
        ImageIcon logotipo = new ImageIcon(getContext().getRealPath("resources/img/logo-ctex.png"));                
        HashMap hm = new HashMap<>();
        hm.put("par_logotipo",logotipo.getImage());        
        hm.put("par_nomerelat","Avaliação de Capacidades Operacionais: "+capacidade.getNome().toUpperCase());  
        hm.put("par_dados_subrelatorio", getJrDataSourceSubReport1());
        try {   
            jasperPrintCapacidadesAreas = JasperFillManager.fillReport(getJasperCapacidadesAreas(),hm,getJrDataSourceMainReport());
        } catch (JRException ex) {
            Logger.getLogger(RelatorioCapacidadesAreasBean.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return jasperPrintCapacidadesAreas;
    }

    /**
     * @param jasperPrintCapacidadesAreas the jasperPrintCapacidadesAreas to set
     */
    public void setJasperPrintCapacidadesAreas(JasperPrint jasperPrintCapacidadesAreas) {
        this.jasperPrintCapacidadesAreas = jasperPrintCapacidadesAreas;
    }
    
    /**
     * @return the relatorio
     */
    public String getRelatorio() {        
        relatorio = "/reports/capacidade/CapacidadesAreas.pdf";
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
            JasperExportManager.exportReportToPdfFile(getJasperPrintCapacidadesAreas(), getContext().getRealPath(relatorio));
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


    /**
     * @return the arrayAreasEmpresas
     */
    public RelatorioAreasEmpresas[] getArrayAreasEmpresas() {
        return arrayAreasEmpresas;
    }

    /**
     * @param arrayAreasEmpresas the arrayAreasEmpresas to set
     */
    public void setArrayAreasEmpresas(RelatorioAreasEmpresas[] arrayAreasEmpresas) {
        this.arrayAreasEmpresas = arrayAreasEmpresas;
    }

    /**
     * @return the jasperPrintProjetosAreas
     */
    public JasperPrint getJasperPrintProjetosAreas() {
        return jasperPrintProjetosAreas;
    }

    /**
     * @param jasperPrintProjetosAreas the jasperPrintProjetosAreas to set
     */
    public void setJasperPrintProjetosAreas(JasperPrint jasperPrintProjetosAreas) {
        this.jasperPrintProjetosAreas = jasperPrintProjetosAreas;
    }

}
