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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ralfh
 */
@Entity
@Table(name = "atribuicoes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Atribuicoes.findAll", query = "SELECT a FROM Atribuicoes a"),
    @NamedQuery(name = "Atribuicoes.findById", query = "SELECT a FROM Atribuicoes a WHERE a.id = :id"),
    @NamedQuery(name = "Atribuicoes.findByNome", query = "SELECT a FROM Atribuicoes a WHERE a.nome = :nome")})
public class Atribuicoes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 120)
    @Column(name = "nome")
    private String nome;

    public Atribuicoes() {
    }

    public Atribuicoes(Integer id) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atribuicoes)) {
            return false;
        }
        Atribuicoes other = (Atribuicoes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ctex.ct.gcompet.modelo.Atribuicoes[ id=" + id + " ]";
    }
    
}
