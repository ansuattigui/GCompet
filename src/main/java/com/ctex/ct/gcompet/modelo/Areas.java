/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ctex.ct.gcompet.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ralfh
 */
@Entity
@Table(name = "areas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Areas.findAll", query = "SELECT a FROM Areas a"),
    @NamedQuery(name = "Areas.findById", query = "SELECT a FROM Areas a WHERE a.id = :id"),
    @NamedQuery(name = "Areas.findByNome", query = "SELECT a FROM Areas a WHERE a.nome = :nome"),
    @NamedQuery(name = "Areas.findAllNotAssociatedWithCapacidade", 
        query = "SELECT a FROM Areas a ORDER BY a.nome") })
public class Areas implements Serializable {

//        query = "SELECT a FROM Areas a JOIN FETCH a.capacidadesList WHERE a.capacidadesList.id != :parmcap") })
    
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "nome")
    private String nome;
    @JoinTable(name = "capacidades_areas", joinColumns = {
        @JoinColumn(name = "areas_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "capacidades_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Capacidades> capacidadesList;
    @JoinTable(name = "areas_empresas", joinColumns = {
        @JoinColumn(name = "areas_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "empresas_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Empresas> empresasList;
    @JoinTable(name = "areas_projetos", joinColumns = {
        @JoinColumn(name = "areas_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "projetos_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Projetos> projetosList;

    public Areas() {
    }

    public Areas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public List<Capacidades> getCapacidadesList() {
        return capacidadesList;
    }

    public void setCapacidadesList(List<Capacidades> capacidadesList) {
        this.capacidadesList = capacidadesList;
    }

    @XmlTransient
    public List<Empresas> getEmpresasList() {
        return empresasList;
    }

    public void setEmpresasList(List<Empresas> empresasList) {
        this.empresasList = empresasList;
    }

    @XmlTransient
    public List<Projetos> getProjetosList() {
        return projetosList;
    }

    public void setProjetosList(List<Projetos> projetosList) {
        this.projetosList = projetosList;
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
        if (!(object instanceof Areas)) {
            return false;
        }
        Areas other = (Areas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ctex.ct.gcompet.modelo.Areas[ id=" + id + " ]";
    }
    
}
