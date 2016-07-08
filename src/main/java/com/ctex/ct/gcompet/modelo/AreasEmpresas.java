/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ralfh
 */
@Entity
@Table(name = "areas_empresas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AreasEmpresas.findAll", query = "SELECT a FROM AreasEmpresas a"),
    @NamedQuery(name = "AreasEmpresas.findById", query = "SELECT a FROM AreasEmpresas a WHERE a.id = :id"),
    @NamedQuery(name = "AreasEmpresas.findByUsuariosId", query = "SELECT a FROM AreasEmpresas a WHERE a.usuario = :usuario"),
    @NamedQuery(name = "AreasEmpresas.findByAvaliacao", query = "SELECT a FROM AreasEmpresas a WHERE a.avaliacao = :avaliacao"),
    @NamedQuery(name = "AreasEmpresas.findByAvaliada", query = "SELECT a FROM AreasEmpresas a WHERE a.avaliada = :avaliada"),
    @NamedQuery(name = "AreasEmpresas.findAllByArea", query = "SELECT a FROM AreasEmpresas a WHERE a.area = :area AND a.avaliacao != -1 ORDER BY a.empresa.id"),
    @NamedQuery(name = "AreasEmpresas.findAllByUsuario", query = "SELECT a FROM AreasEmpresas a WHERE a.area = :area AND a.avaliacao != -1 AND a.usuario = :user ORDER BY a.empresa.id")})
public class AreasEmpresas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "usuarios_id", referencedColumnName = "id")
    @ManyToOne
    private Usuarios usuario;
    @JoinColumn(name = "areas_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Areas area;
    @JoinColumn(name = "empresas_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Empresas empresa;
    @Column(name = "avaliacao")
    private Short avaliacao;
    @Column(name = "avaliada")
    private Boolean avaliada;

    public AreasEmpresas() {
    }

    public AreasEmpresas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Short getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Short avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Boolean getAvaliada() {
        return avaliada;
    }

    public void setAvaliada(Boolean avaliada) {
        this.avaliada = avaliada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AreasEmpresas)) {
            return false;
        }
        AreasEmpresas other = (AreasEmpresas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ctex.ct.gcompet.modelo.AreasEmpresas[ id=" + id + " ]";
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
     * @return the empresa
     */
    public Empresas getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(Empresas empresa) {
        this.empresa = empresa;
    }
    
}
