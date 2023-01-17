package com.bleschunov.yellowsubs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Bleschunov Dmitry
 */
@Entity
@Setter
@Table(name = "event")
public class Event {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "timepad_id")
    private long timepadId;

    @Column(name = "title")
    private String title;

    @Column(name = "description_short")
    private String descriptionShort;

    @Column(name = "url")
    private String url;

    @Column(name = "starts_at")
    private ZonedDateTime startsAt;

    @Column(name = "ends_at")
    private ZonedDateTime endsAt;

    @OneToMany(mappedBy = "event")
    private List<Ticket> tickets;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;
}
