package org.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Byte id;

    @Column(name = "name")
    private String name;

    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @ManyToMany
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name="category_id", referencedColumnName = "category_id"),
            inverseJoinColumns = @JoinColumn(name= "film_id", referencedColumnName = "film_id"))
    private Set<Film> films;

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }
}
