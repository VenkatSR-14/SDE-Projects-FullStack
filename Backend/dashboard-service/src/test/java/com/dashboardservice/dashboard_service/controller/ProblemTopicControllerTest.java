package com.dashboardservice.dashboard_service.controller;

import com.dashboardservice.dashboard_service.dto.ProblemTopicDto;
import com.dashboardservice.dashboard_service.model.ProblemTopic;
import com.dashboardservice.dashboard_service.service.ProblemTopicService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ProblemTopicControllerTest {

    private final ProblemTopicService problemTopicService = mock(ProblemTopicService.class);
    private final ProblemTopicController controller = new ProblemTopicController(problemTopicService, null);

    @Test
    void testGetAllTopics() {
        ProblemTopic topic1 = new ProblemTopic();
        topic1.setTopicName("Arrays");

        ProblemTopic topic2 = new ProblemTopic();
        topic2.setTopicName("Strings");

        Mockito.when(problemTopicService.getAllTopics()).thenReturn(Arrays.asList(topic1, topic2));

        ResponseEntity<List<ProblemTopicDto>> response = controller.getAllTopics();
        assertEquals(2, response.getBody().size());
        assertEquals("Arrays", response.getBody().get(0).getTopicName());
    }
}
