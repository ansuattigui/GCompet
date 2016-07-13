package com.ctex.ct.gcompet.bean;

import com.ctex.ct.gcompet.bean.util.JsfUtil;
import com.ctex.ct.gcompet.bean.util.JsfUtil.PersistAction;
import com.ctex.ct.gcompet.modelo.Areas;
import com.ctex.ct.gcompet.modelo.AreasEmpresas;
import com.ctex.ct.gcompet.modelo.Empresas;
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

@Named("areasEmpresasController")
@SessionScoped
public class AreasEmpresasController implements Serializable {

    @EJB
    private AreasEmpresasFacade ejbFacade;
    @EJB
    private EmpresasFacade ejbEmpresasFacade;
    
    private List<AreasEmpresas> items = null;
    private AreasEmpresas selected;
    private EmpresasCandidatas empresa;
    private Areas area;

    public AreasEmpresasController() {
    }

    public AreasEmpresas getSelected() {
        return selected;
    }

    public void setSelected(AreasEmpresas selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AreasEmpresasFacade getFacade() {
        return ejbFacade;
    }

    public AreasEmpresas prepareCreate() {
        selected = new AreasEmpresas();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AreasEmpresasCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AreasEmpresasUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AreasEmpresasDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AreasEmpresas> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public List<AreasEmpresas> getItemsByArea() {
//        Usuarios user = LoginController.returnUserLoggedIn();
        Usuarios user = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
        return getFacade().findAll(area);
    }
    
    public List<AreasEmpresas> getItemsByUsuario() {
//        Usuarios user = LoginController.returnUserLoggedIn();
        Usuarios user = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
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
    
    private void persist(PersistAction persistAction, AreasEmpresas selected) {
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
    

    public AreasEmpresas getAreasEmpresas(Integer id) {
        return getFacade().find(id);
    }

    public List<AreasEmpresas> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AreasEmpresas> getItemsAvailableSelectOne() {
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
     * @return the ejbEmpresasFacade
     */
    public EmpresasFacade getEjbEmpresasFacade() {
        return ejbEmpresasFacade;
    }

    /**
     * @param ejbEmpresasFacade
     */
    public void setEjbEmpresasFacade(EmpresasFacade ejbEmpresasFacade) {
        this.ejbEmpresasFacade = ejbEmpresasFacade;
    }

    /**
     * @return the item
     */
    public EmpresasCandidatas getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the item to set
     */
    public void setEmpresa(EmpresasCandidatas empresa) {
        this.empresa = empresa;
    }

    @FacesConverter(forClass = AreasEmpresas.class)
    public static class AreasEmpresasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AreasEmpresasController controller = (AreasEmpresasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "areasProjetosController");
            return controller.getAreasEmpresas(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof AreasEmpresas) {
                AreasEmpresas o = (AreasEmpresas) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AreasEmpresas.class.getName()});
                return null;
            }
        }        
    }
    
    /**********************************************
        Classe de Projetos Candidatos a Associação
    *********************************************/
    
    private List<EmpresasCandidatas> empresasCandidatas;
    private EmpresasCandidatas pcSelected;

    public void geraEmpresasCandidatas() {
//        Usuarios user = LoginController.returnUserLoggedIn();
        Usuarios user = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
        empresasCandidatas = new ArrayList<>();
        List<Empresas> empresas = getEjbEmpresasFacade().findAll(area,user);        
        for(Empresas item: empresas) {
            EmpresasCandidatas pc = new EmpresasCandidatas();
            pc.setEmpresa(item);
            pc.setArea(area);
            empresasCandidatas.add(pc);
        }        
    }
    
    public void salvaEmpresasCandidatas() {
        for (EmpresasCandidatas ec: empresasCandidatas) {
            if (ec.getAvaliacao()!=-1) {
                AreasEmpresas ae = new AreasEmpresas();
                ae.setUsuario(ec.getUsuario());
                ae.setEmpresa(ec.getEmpresa());
                ae.setArea(ec.getArea());
                ae.setAvaliacao(ec.getAvaliacao());
                ae.setAvaliada(true);                
                persist(PersistAction.CREATE, ae);
            }
        }
    }
    
    
    public List<EmpresasCandidatas> getEmpresasCandidatas() {
        return empresasCandidatas;
    }
    
    public void setEmpresasCandidatas(List<EmpresasCandidatas> empresasCandidatas) {
        this.empresasCandidatas = empresasCandidatas;
    }
    
    
    public EmpresasCandidatas getPcSelected() {
        return pcSelected;
    }
    
    
    public void setPcSelected(EmpresasCandidatas pcSelected) {
        this.pcSelected = pcSelected;
    }
    
    public class EmpresasCandidatas {
        private int id;
        private Usuarios usuario;
        private Areas area;
        private Empresas empresa;
        private Boolean avaliada;
        private short avaliacao;

        private EmpresasCandidatas() {
//            usuario = LoginController.returnUserLoggedIn();
        usuario = (Usuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
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
         * @return the item
         */
        public Empresas getEmpresa() {
            return empresa;
        }

        /**
         * @param empresa the item to set
         */
        public void setEmpresa(Empresas empresa) {
            this.empresa = empresa;
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

    public void onEmpresaSelect(SelectEvent event) {
        this.setEmpresa((EmpresasCandidatas) event.getObject());
    }    
    
    


}
