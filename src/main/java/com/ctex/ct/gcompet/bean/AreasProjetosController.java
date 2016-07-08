package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.bean.util.JsfUtil;
import com.ctex.ct.gcompet.bean.util.JsfUtil.PersistAction;
import com.ctex.ct.gcompet.modelo.Areas;
import com.ctex.ct.gcompet.modelo.AreasProjetos;
import com.ctex.ct.gcompet.modelo.Projetos;
import com.ctex.ct.gcompet.modelo.Usuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

@Named("areasProjetosController")
@SessionScoped
public class AreasProjetosController implements Serializable {

    @EJB
    private AreasProjetosFacade ejbFacade;
    @EJB
    private ProjetosFacade ejbProjetosFacade;
    
    private List<AreasProjetos> items = null;
    private AreasProjetos selected;
    private ProjetosCandidatos projeto;
    private Areas area;

    public AreasProjetosController() {
    }

    public AreasProjetos getSelected() {
        return selected;
    }

    public void setSelected(AreasProjetos selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AreasProjetosFacade getFacade() {
        return ejbFacade;
    }

    public AreasProjetos prepareCreate() {
        selected = new AreasProjetos();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AreasProjetosCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AreasProjetosUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AreasProjetosDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AreasProjetos> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public List<AreasProjetos> getItemsByArea() {
        Usuarios user = LoginController.returnUserLoggedIn();
        return getFacade().findAll(area);
    }
    
    public List<AreasProjetos> getItemsByUsuario() {
        Usuarios user = LoginController.returnUserLoggedIn();
        return getFacade().findAll(area,user);
    }
    

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }
    
    private void persist(PersistAction persistAction, AreasProjetos selected) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    

    public AreasProjetos getAreasProjetos(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<AreasProjetos> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AreasProjetos> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the area
     */
    public Areas getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(Areas area) {
        this.area = area;
    }

    /**
     * @return the ejbProjetosFacade
     */
    public ProjetosFacade getEjbProjetosFacade() {
        return ejbProjetosFacade;
    }

    /**
     * @param ejbProjetosFacade
     */
    public void setEjbProjetosFacade(ProjetosFacade ejbProjetosFacade) {
        this.ejbProjetosFacade = ejbProjetosFacade;
    }

    /**
     * @return the projeto
     */
    public ProjetosCandidatos getProjeto() {
        return projeto;
    }

    /**
     * @param projeto the projeto to set
     */
    public void setProjeto(ProjetosCandidatos projeto) {
        this.projeto = projeto;
    }

    @FacesConverter(forClass = AreasProjetos.class)
    public static class AreasProjetosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AreasProjetosController controller = (AreasProjetosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "areasProjetosController");
            return controller.getAreasProjetos(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof AreasProjetos) {
                AreasProjetos o = (AreasProjetos) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AreasProjetos.class.getName()});
                return null;
            }
        }        
    }
    
    /**********************************************
        Classe de Projetos Candidatos a Associação
    *********************************************/
    
    private List<ProjetosCandidatos> projetosCandidatos;
    private ProjetosCandidatos pcSelected;

    public void geraProjetosCandidatos() {
        Usuarios user = LoginController.returnUserLoggedIn();
        projetosCandidatos = new ArrayList<>();
        List<Projetos> projetos = getEjbProjetosFacade().findAll(area,user);        
        for(Projetos projeto: projetos) {
            ProjetosCandidatos pc = new ProjetosCandidatos();
            pc.setProjeto(projeto);
            pc.setArea(area);
            projetosCandidatos.add(pc);
        }        
    }
    
    public void salvaProjetosCandidatos() {
        for (ProjetosCandidatos pc: projetosCandidatos) {
            if (pc.getAvaliacao()!=-1) {
                AreasProjetos ap = new AreasProjetos();
                ap.setUsuario(pc.getUsuario());
                ap.setProjeto(pc.getProjeto());
                ap.setArea(pc.getArea());
                ap.setAvaliacao(pc.getAvaliacao());
                ap.setAvaliada(true);                
                persist(PersistAction.CREATE, ap);
            }
        }
    }
    
    
    public List<ProjetosCandidatos> getProjetosCandidatos() {
        return projetosCandidatos;
    }
    
    public void setProjetosCandidatos(List<ProjetosCandidatos> projetosCandidatos) {
        this.projetosCandidatos = projetosCandidatos;
    }
    
    
    public ProjetosCandidatos getPcSelected() {
        return pcSelected;
    }
    
    
    public void setPcSelected(ProjetosCandidatos pcSelected) {
        this.pcSelected = pcSelected;
    }
    
    public class ProjetosCandidatos {
        private int id;
        private Usuarios usuario;
        private Areas area;
        private Projetos projeto;
        private Boolean avaliada;
        private short avaliacao;

        private ProjetosCandidatos() {
            usuario = LoginController.returnUserLoggedIn();
            avaliada = false;
            avaliacao = -1;
        }

        /**
         * @return the id
         */
        public int getId() {
            return id;
        }

        /**
         * @param id the id to set
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * @return the usuario
         */
        public Usuarios getUsuario() {
            return usuario;
        }

        /**
         * @param usuario the usuario to set
         */
        public void setUsuario(Usuarios usuario) {
            this.usuario = usuario;
        }

        /**
         * @return the area
         */
        public Areas getArea() {
            return area;
        }

        /**
         * @param area the area to set
         */
        public void setArea(Areas area) {
            this.area = area;
        }

        /**
         * @return the projeto
         */
        public Projetos getProjeto() {
            return projeto;
        }

        /**
         * @param projeto the projeto to set
         */
        public void setProjeto(Projetos projeto) {
            this.projeto = projeto;
        }

        /**
         * @return the avaliada
         */
        public Boolean getAvaliada() {
            return avaliada;
        }

        /**
         * @param avaliada the avaliada to set
         */
        public void setAvaliada(Boolean avaliada) {
            this.avaliada = avaliada;
        }

        /**
         * @return the avaliacao
         */
        public short getAvaliacao() {
            return avaliacao;
        }

        /**
         * @param avaliacao the avaliacao to set
         */
        public void setAvaliacao(short avaliacao) {
            this.avaliacao = avaliacao;
        }
        
    }    

    public void onAreaSelect(SelectEvent event) {
        this.area = ((Areas) event.getObject());
    }    

    public void onProjetoSelect(SelectEvent event) {
        this.setProjeto((ProjetosCandidatos) event.getObject());
    }    
    
    


}
