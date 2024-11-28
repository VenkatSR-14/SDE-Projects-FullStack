package com.dashboardservice.dashboard_service.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
public class UserStatsDto {
    private int problemsSolved;
    private int contributionStreak;
    private int badgesEarned;
    private String skillLevel; // eg beginner, advanced etc.
}
