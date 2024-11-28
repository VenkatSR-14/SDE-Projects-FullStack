package com.dashboardservice.dashboard_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class ProblemTopic {

    @Id
    @GeneratedValue
    @UuidGenerator // Generates UUID for the primary key
    private UUID id;

    @Column(nullable = false, unique = true) // Topic name must be unique and non-null
    private String topicName;

    @Column(columnDefinition = "TEXT") // Allows longer descriptions
    private String topicDescription;
}
