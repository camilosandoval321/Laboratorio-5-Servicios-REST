package com.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Entidad Producto.
 * Cada producto pertenece a un Competitor (relación ManyToOne).
 *
 * El nombre está en español para evitar conflictos con tablas
 * del sistema en la base de datos (igual que en el laboratorio original).
 */
@Entity
@Table(name = "PRODUCTO")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.DATE)
    private Calendar createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.DATE)
    private Calendar updatedAt;

    private String name;
    private Double price;
    private String description;

    /**
     * Relación ManyToOne: muchos productos pueden pertenecer a un competidor.
     *
     * cascade=ALL  → operaciones se propagan al competidor asociado
     *
     * EFECTO EN BD: Esta anotación es la que genera la columna "competitor_id"
     * en la tabla PRODUCTO. Esa columna es la clave foránea que apunta
     * a la llave primaria de la tabla COMPETITOR.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "competitor_id")  // nombre explícito de la columna FK
    @JsonBackReference  // evita recursión infinita al serializar
    private Competitor competitor;

    public Producto() {}

    public Producto(String name, Double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @PrePersist
    private void creationTimestamp() {
        this.createdAt = this.updatedAt = Calendar.getInstance();
    }

    @PreUpdate
    private void updateTimestamp() {
        this.updatedAt = Calendar.getInstance();
    }

    // --- Getters y Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Competitor getCompetitor() { return competitor; }
    public void setCompetitor(Competitor competitor) { this.competitor = competitor; }

    public Calendar getCreatedAt() { return createdAt; }
    public Calendar getUpdatedAt() { return updatedAt; }
}
