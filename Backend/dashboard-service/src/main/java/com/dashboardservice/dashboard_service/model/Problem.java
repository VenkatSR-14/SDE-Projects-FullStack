package com.dashboardservice.dashboard_service.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
public class Problem {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    private String ProblemTitle;
    private String ProblemDescription;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)


    private ProblemTopic problemTopic;
}
