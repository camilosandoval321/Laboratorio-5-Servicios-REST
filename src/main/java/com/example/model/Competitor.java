package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "competitor")
public class Competitor implements Serializable {

    private static final long serialVersionUID = 1L;

    // 🔥 FIX: SQLite compatible (NO usar IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Fechas modernas (mejor que Calendar)
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Datos básicos
    private String name;
    private String surname;
    private int age;
    private String telephone;
    private String cellphone;
    private String address;
    private String city;
    private String country;
    private boolean winner;

    // -------------------------
    // LOGIN
    // -------------------------

    @Column(unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    // -------------------------
    // RELACIÓN
    // -------------------------

    @OneToMany(mappedBy = "competitor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Producto> productos;

    // Constructor vacío
    public Competitor() {
    }

    public Competitor(String name, String surname, int age, String telephone,
            String cellphone, String address, String city,
            String country, boolean winner, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.telephone = telephone;
        this.cellphone = cellphone;
        this.address = address;
        this.city = city;
        this.country = country;
        this.winner = winner;
        this.email = email;
        this.password = password;
    }

    // -------------------------
    // TIMESTAMPS AUTOMÁTICOS
    // -------------------------

    @PrePersist
    protected void onCreate() {
        this.createdAt = this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // -------------------------
    // GETTERS Y SETTERS
    // -------------------------

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProducts(Set<Producto> productos) {
        this.productos = productos;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}