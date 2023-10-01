package org.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="film_id")
    private Film film;

    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;

    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
