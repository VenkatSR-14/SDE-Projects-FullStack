package com.dashboardservice.dashboard_service.controller;

import com.dashboardservice.dashboard_service.dto.ProblemTopicDto;
import com.dashboardservice.dashboard_service.model.ProblemTopic;
import com.dashboardservice.dashboard_service.service.ProblemTopicService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/topics")
public class ProblemTopicController {

    private final ProblemTopicService problemTopicService;
    private final ModelMapper modelMapper;

    public ProblemTopicController(ProblemTopicService problemTopicService, ModelMapper modelMapper) {
        this.problemTopicService = problemTopicService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<ProblemTopicDto> addTopic(@Valid @RequestBody ProblemTopicDto topicDto) {
        // Convert DTO to entity
        ProblemTopic topic = modelMapper.map(topicDto, ProblemTopic.class);

        // Add topic using the service
        ProblemTopic savedTopic = problemTopicService.addTopic(topic);

        // Convert saved entity to DTO and return
        ProblemTopicDto savedTopicDto = modelMapper.map(savedTopic, ProblemTopicDto.class);
        return ResponseEntity.ok(savedTopicDto);
    }

    @GetMapping
    public ResponseEntity<List<ProblemTopicDto>> getAllTopics() {
        // Fetch all topics using the service
        List<ProblemTopic> topics = problemTopicService.getAllTopics();

        // Convert entities to DTOs
        List<ProblemTopicDto> topicDtos = topics.stream()
                .map(topic -> modelMapper.map(topic, ProblemTopicDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(topicDtos);
    }
}
