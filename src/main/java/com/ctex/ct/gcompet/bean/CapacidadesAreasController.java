package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.bean.util.JsfUtil;
import com.ctex.ct.gcompet.bean.util.JsfUtil.PersistAction;
import com.ctex.ct.gcompet.modelo.Areas;
import com.ctex.ct.gcompet.modelo.Capacidades;
import com.ctex.ct.gcompet.modelo.CapacidadesAreas;
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

@Named("capacidadesAreasController")
@SessionScoped
public class CapacidadesAreasController implements Serializable {

    @EJB
    private com.ctex.ct.gcompet.bean.CapacidadesAreasFacade ejbFacade;
    @EJB
    private com.ctex.ct.gcompet.bean.AreasFacade ejbAreasFacade;
    
    
    private List<CapacidadesAreas> items = null;
    private CapacidadesAreas selected;
    private Capacidades capacidade;
    private AreasCandidatas area;
    private short associacao = -1;

    public CapacidadesAreasController() {
    }

    public CapacidadesAreas getSelected() {
        return selected;
    }

    public void setSelected(CapacidadesAreas selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CapacidadesAreasFacade getFacade() {
        return ejbFacade;
    }

    public CapacidadesAreas prepareCreate() {
        selected = new CapacidadesAreas();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CapacidadesAreasCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CapacidadesAreasUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CapacidadesAreasDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CapacidadesAreas> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<CapacidadesAreas> getItemsByCapacidade() {
        return getFacade().findAll(capacidade);
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
    
    private void persist(PersistAction persistAction, CapacidadesAreas selected) {
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
    

    public CapacidadesAreas getCapacidadesAreas(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<CapacidadesAreas> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CapacidadesAreas> getItemsAvailableSelectOne() {
        return getFacade().findAll();
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
     * @return the associacao
     */
    public short getAssociacao() {
        return associacao;
    }

    /**
     * @param associacao the associacao to set
     */
    public void setAssociacao(short associacao) {
        this.associacao = associacao;
    }

    /**
     * @return the ejbAreasFacade
     */
    public com.ctex.ct.gcompet.bean.AreasFacade getEjbAreasFacade() {
        return ejbAreasFacade;
    }

    /**
     * @param ejbAreasFacade the ejbAreasFacade to set
     */
    public void setEjbAreasFacade(com.ctex.ct.gcompet.bean.AreasFacade ejbAreasFacade) {
        this.ejbAreasFacade = ejbAreasFacade;
    }

    /**
     * @return the area
     */
    public AreasCandidatas getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(AreasCandidatas area) {
        this.area = area;
    }


    @FacesConverter(forClass = CapacidadesAreas.class)
    public static class CapacidadesAreasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CapacidadesAreasController controller = (CapacidadesAreasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "capacidadesAreasController");
            return controller.getCapacidadesAreas(getKey(value));
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
            if (object instanceof CapacidadesAreas) {
                CapacidadesAreas o = (CapacidadesAreas) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CapacidadesAreas.class.getName()});
                return null;
            }
        }

    }
    
    /**********************************************
        Classe de Areas Candidatas a Associação
    *********************************************/
    
    private List<AreasCandidatas> areasCandidatas;
    private AreasCandidatas acSelected;

    public void geraAreasCandidatas() {
        areasCandidatas = new ArrayList<>();
        List<Areas> areas = getEjbAreasFacade().findAll(capacidade);        
        for(Areas area: areas) {
            AreasCandidatas ac = new AreasCandidatas();
            ac.setArea(area);
            ac.setCapacidade(capacidade);
            areasCandidatas.add(ac);
        }        
    }
    
    public void salvaAreasCandidatas() {
        for (AreasCandidatas ac: areasCandidatas) {
            if (ac.avaliacao!=-1) {
                CapacidadesAreas ca = new CapacidadesAreas();
                ca.setUsuario(ac.getUsuario());
                ca.setCapacidade(ac.getCapacidade());
                ca.setArea(ac.getArea());
                ca.setAvaliacao(ac.getAvaliacao());
                ca.setAvaliada(true);
                
                persist(PersistAction.CREATE, ca);
            }
        }
    }
    

    /**
     * @return the areasCandidatas
     */
    public List<AreasCandidatas> getAreasCandidatas() {
        return areasCandidatas;
    }

    /**
     * @param areasCandidatas the areasCandidatas to set
     */
    public void setAreasCandidatas(List<AreasCandidatas> areasCandidatas) {
        this.areasCandidatas = areasCandidatas;
    }
    
    /**
     * @return the acSelected
     */
    public AreasCandidatas getAcSelected() {
        return acSelected;
    }

    /**
     * @param acSelected the acSelected to set
     */
    public void setAcSelected(AreasCandidatas acSelected) {
        this.acSelected = acSelected;
    }
    
    public class AreasCandidatas {
        private int id;
        private Usuarios usuario;
        private Capacidades capacidade;
        private Areas area;
        private Boolean avaliada;
        private short avaliacao;

        private AreasCandidatas() {
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
    
    
    public void onCapacidadeSelect(SelectEvent event) {
        this.capacidade = ((Capacidades) event.getObject());
    }    

    public void onAreaSelect(SelectEvent event) {
        this.area = ((AreasCandidatas) event.getObject());
    }    
    
}
