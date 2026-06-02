package com.example.eventify.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name = "events")
@SQLDelete(sql = "UPDATE events SET active = false WHERE id = ?")
@SQLRestriction("active = true")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate eventDate;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "events_categories",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new LinkedHashSet<>();
}
