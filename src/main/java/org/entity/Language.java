package org.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Byte id;

    @Column(name = "name", columnDefinition="CHAR")
    private String name;

    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

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
}
