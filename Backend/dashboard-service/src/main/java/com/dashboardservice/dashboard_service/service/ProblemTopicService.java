package com.dashboardservice.dashboard_service.service;

import com.dashboardservice.dashboard_service.model.ProblemTopic;
import com.dashboardservice.dashboard_service.repository.ProblemTopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemTopicService {

    private final ProblemTopicRepository problemTopicRepository;

    public ProblemTopicService(ProblemTopicRepository problemTopicRepository) {
        this.problemTopicRepository = problemTopicRepository;
    }

    /**
     * Add a new topic to the database.
     *
     * @param topic The ProblemTopic object to be added.
     * @return The saved ProblemTopic.
     */
    public ProblemTopic addTopic(ProblemTopic topic) {
        return problemTopicRepository.save(topic);
    }

    /**
     * Fetch all topics from the database.
     *
     * @return A list of all ProblemTopic objects.
     */
    public List<ProblemTopic> getAllTopics() {
        return problemTopicRepository.findAll();
    }
}
