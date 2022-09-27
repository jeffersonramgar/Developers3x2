package com.develpers3x2.thymeleaf.entidad;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_profile", nullable = false)
    private Long id;
    @Column(name="nombre")
    private String nombre;
    @Column(name="image")
    private String image;
    @Column(name="phone")
    private String phone;
    @Column(name="create_at")
    private Date createdAt;
    @Column(name="update_at")
    private Date updatedAt;

    public Profile() {
        this.createdAt = new Date();
    }

    public Profile(String nombre, String image, String phone) {
        this.nombre = nombre;
        this.image = image;
        this.phone = phone;
        this.createdAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        updatedAt = new Date();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        updatedAt = new Date();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        updatedAt = new Date();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        updatedAt = new Date();
    }

    public Date getCreatedAt(Date date) {
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
        return "Profile{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", image='" + image + '\'' +
                ", phone='" + phone + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
