package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.bean.util.JsfUtil;
import com.ctex.ct.gcompet.bean.util.JsfUtil.PersistAction;
import com.ctex.ct.gcompet.modelo.Capacidades;
import com.ctex.ct.gcompet.modelo.CapacidadesAreas;
import java.io.Serializable;
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
    private List<CapacidadesAreas> items = null;
    private CapacidadesAreas selected;
    private Capacidades capacidade;
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
    
    public void onRowSelect(SelectEvent event) {
        this.capacidade = ((Capacidades) event.getObject());
        System.out.println(this.capacidade.getNome());
    }    

}
