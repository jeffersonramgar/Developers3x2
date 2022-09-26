package com.develpers3x2.thymeleaf.entidad;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Negative;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="transacciones")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction", nullable = false)
    private long id;
    @javax.validation.constraints.NotEmpty
    @Column(name = "concept", nullable = false)
    private String concept;
    @Column(name = "amount", nullable = false)
    private Double amount;
    @Column(name = "create_at")
    private Date createdAt;
    @Column(name = "update_at")
    private Date  updateAt;

    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
