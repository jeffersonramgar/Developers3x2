package com.develpers3x2.thymeleaf.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name="empresas")
public class Enterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_empresa", nullable = false)
    private long id;
    @NotEmpty(message = "El nombre no puede estar vacio")
    @Column(name="name")
    private String name;
    @NotEmpty(message = "El documento no puede estar vacio")
    @Column(name="document")
    private String document;
    @NotEmpty(message = "El teléfono no puede estar vacio")
    @Column(name="phone")
    private String phone;
    @NotEmpty(message = "La dirección no puede estar vacia")
    @Column(name="address")
    private String address;
    @Column(name="create_at")
    private Date createdAt = new Date();
    @Column(name="update_at")
    private Date updatedAt;
    @Column(name="estado")
    private boolean estado;



    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {

        this.estado = estado;
        setUpdatedAt(new Date());
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        setUpdatedAt(new Date());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setUpdatedAt(new Date());
    }


    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
        setUpdatedAt(new Date());
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        setUpdatedAt(new Date());
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        setUpdatedAt(new Date());
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Enterprise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", document='" + document + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", estado=" + estado +
                '}';
    }
}
