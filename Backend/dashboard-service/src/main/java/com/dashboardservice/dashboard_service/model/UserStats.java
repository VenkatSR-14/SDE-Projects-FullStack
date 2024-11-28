package com.dashboardservice.dashboard_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class UserStats {

    @Id
    @GeneratedValue
    @UuidGenerator // Generates UUID for the primary key
    private UUID id;

    @Column(nullable = false, unique = true) // Each user must have a unique userId
    private UUID userId; // Links to a user in the User Service

    @Column(nullable = false)
    private int problemsSolved;

    @Column(nullable = false)
    private int contributionStreak; // Days of consistent activity

    @Column(nullable = false)
    private int badgesEarned;

    private String skillLevel; // Beginner, Intermediate, Advanced, etc.
}
