package com.dashboardservice.dashboard_service.repository;

import com.dashboardservice.dashboard_service.model.ProblemTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProblemTopicRepository extends JpaRepository<ProblemTopic, UUID> {

}
