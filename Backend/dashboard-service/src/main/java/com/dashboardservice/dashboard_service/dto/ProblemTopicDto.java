package com.dashboardservice.dashboard_service.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProblemTopicDto {
    @NotBlank (message = "Topic name can't be blank")
    private String topicName;
    private String topicDescription;
    private int totalProblems;
}
