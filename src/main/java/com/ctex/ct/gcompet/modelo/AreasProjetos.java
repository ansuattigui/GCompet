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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ralfh
 */
@Entity
@Table(name = "areas_projetos", catalog = "gcompet", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AreasProjetos.findAll", query = "SELECT a FROM AreasProjetos a"),
    @NamedQuery(name = "AreasProjetos.findById", query = "SELECT a FROM AreasProjetos a WHERE a.id = :id"),
    @NamedQuery(name = "AreasProjetos.findByAvaliacao", query = "SELECT a FROM AreasProjetos a WHERE a.avaliacao = :avaliacao"),
    @NamedQuery(name = "AreasProjetos.findByAvaliada", query = "SELECT a FROM AreasProjetos a WHERE a.avaliada = :avaliada"),
    @NamedQuery(name = "AreasProjetos.findAllByArea", query = "SELECT ap FROM AreasProjetos ap WHERE ap.area = :area AND ap.avaliacao != -1 ORDER BY ap.projeto.id"),
    @NamedQuery(name = "AreasProjetos.findAllByUsuario", query = "SELECT ap FROM AreasProjetos ap WHERE ap.area = :area AND ap.avaliacao != -1 AND ap.usuario = :user ORDER BY ap.projeto.id")})
public class AreasProjetos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "avaliacao")
    private Short avaliacao;
    @Column(name = "avaliada")
    private Boolean avaliada;
    @JoinColumn(name = "USUARIO_id", referencedColumnName = "id")
    @ManyToOne
    private Usuarios usuario;
    @JoinColumn(name = "AREA_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Areas area;
    @JoinColumn(name = "PROJETO_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Projetos projeto;

    public AreasProjetos() {
    }

    public AreasProjetos(Integer id) {
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

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Areas getArea() {
        return area;
    }

    public void setArea(Areas area) {
        this.area = area;
    }

    public Projetos getProjeto() {
        return projeto;
    }

    public void setProjeto(Projetos projeto) {
        this.projeto = projeto;
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
        if (!(object instanceof AreasProjetos)) {
            return false;
        }
        AreasProjetos other = (AreasProjetos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ctex.ct.gcompet.modelo.AreasProjetos[ id=" + id + " ]";
    }
    
}
