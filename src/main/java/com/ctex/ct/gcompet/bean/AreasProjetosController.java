package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.bean.util.JsfUtil;
import com.ctex.ct.gcompet.bean.util.JsfUtil.PersistAction;
import com.ctex.ct.gcompet.modelo.AreasProjetos;
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

@Named("areasProjetosController")
@SessionScoped
public class AreasProjetosController implements Serializable {

    @EJB
    private com.ctex.ct.gcompet.bean.AreasProjetosFacade ejbFacade;
    private List<AreasProjetos> items = null;
    private AreasProjetos selected;

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

    public AreasProjetos getAreasProjetos(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<AreasProjetos> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AreasProjetos> getItemsAvailableSelectOne() {
        return getFacade().findAll();
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

}
