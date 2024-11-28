package com.dashboardservice.dashboard_service.service;

import com.dashboardservice.dashboard_service.model.ProblemTopic;
import com.dashboardservice.dashboard_service.repository.ProblemTopicRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ProblemTopicServiceTest {

    private final ProblemTopicRepository repository = mock(ProblemTopicRepository.class);
    private final ProblemTopicService service = new ProblemTopicService(repository);

    @Test
    void testAddTopic() {
        ProblemTopic topic = new ProblemTopic();
        topic.setTopicName("Arrays");
        topic.setTopicDescription("Array-related problems");

        when(repository.save(topic)).thenReturn(topic);

        ProblemTopic result = service.addTopic(topic);
        assertEquals("Arrays", result.getTopicName());
        verify(repository, times(1)).save(topic);
    }

    @Test
    void testGetAllTopics() {
        ProblemTopic topic1 = new ProblemTopic();
        topic1.setTopicName("Arrays");

        ProblemTopic topic2 = new ProblemTopic();
        topic2.setTopicName("Strings");

        when(repository.findAll()).thenReturn(Arrays.asList(topic1, topic2));

        List<ProblemTopic> topics = service.getAllTopics();
        assertEquals(2, topics.size());
        assertEquals("Arrays", topics.get(0).getTopicName());
        assertEquals("Strings", topics.get(1).getTopicName());
        verify(repository, times(1)).findAll();
    }
}
