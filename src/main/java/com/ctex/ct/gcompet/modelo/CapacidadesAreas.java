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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ralfh
 */
@Entity
@Table(name = "capacidades_areas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CapacidadesAreas.findAll", query = "SELECT ca FROM CapacidadesAreas ca"),
    @NamedQuery(name = "CapacidadesAreas.findById", query = "SELECT ca FROM CapacidadesAreas ca WHERE ca.id = :id"),
    @NamedQuery(name = "CapacidadesAreas.findByNome", query = "SELECT ca FROM CapacidadesAreas ca WHERE ca.area.nome = :nome"),
    @NamedQuery(name = "CapacidadesAreas.findAllByCapacidade", query = "SELECT ca FROM CapacidadesAreas ca WHERE ca.capacidade = :capacidade AND ca.avaliacao != -1 ORDER BY ca.area.id"),
    @NamedQuery(name = "CapacidadesAreas.findAllByUsuario", query = "SELECT ca FROM CapacidadesAreas ca WHERE ca.capacidade = :capacidade AND ca.avaliacao != -1 AND ca.usuario = :user ORDER BY ca.area.id")
})

public class CapacidadesAreas implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;   
    @JoinColumn(name = "USUARIO_id", referencedColumnName = "id")    
    @ManyToOne
    private Usuarios usuario;    
    @JoinColumn(name = "capacidade_id", referencedColumnName = "id")
    @ManyToOne
    private Capacidades capacidade;    
    @ManyToOne
    private Areas area;    
    @Basic(optional = false)
    @NotNull
    @Column(name = "avaliada")
    private boolean avaliada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "avaliacao")
    private short avaliacao;

    public CapacidadesAreas() {
    }


    public boolean getAvaliada() {
        return avaliada;
    }

    public void setAvaliada(boolean avaliada) {
        this.avaliada = avaliada;
    }

    public short getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(short avaliacao) {
        this.avaliacao = avaliacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CapacidadesAreas)) {
            return false;
        }
        CapacidadesAreas other = (CapacidadesAreas) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ctex.ct.gcompet.modelo.CapacidadesAreas[ id=" + getId() + " ]";
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
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
}
