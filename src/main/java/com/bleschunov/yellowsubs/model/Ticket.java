package com.bleschunov.yellowsubs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

/**
 * @author Bleschunov Dmitry
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "ticket")
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "timepad_id")
    private String timepadId;

    @ManyToOne
    @JoinColumn(name = "app_user_id", referencedColumnName = "id")
    private AppUser owner;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;
}
