package com.develpers3x2.thymeleaf.entidad;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="transacciones")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction", nullable = false)
    private long id;
    @NotEmpty(message = "El concepto no puede estar vacio")
    @Column(name = "concept")
    private String concept;
    @NotNull(message = "El monto no puede estar vacio")
    //@Min(value = 1, message = "debe ser un valor mayor a 0")
    @Column(name = "amount")
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
        setUpdateAt(new Date());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        setUpdateAt(new Date());
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
        setUpdateAt(new Date());
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
        setUpdateAt(new Date());
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

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", concept='" + concept + '\'' +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                ", updateAt=" + updateAt +
                ", estado=" + estado +
                '}';
    }
}
