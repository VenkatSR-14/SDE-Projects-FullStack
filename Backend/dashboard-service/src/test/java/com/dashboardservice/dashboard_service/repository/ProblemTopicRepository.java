package com.dashboardservice.dashboard_service.repository;

import com.dashboardservice.dashboard_service.model.ProblemTopic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ProblemTopicRepositoryTest {

    @Autowired
    private ProblemTopicRepository repository;

    @Test
    void testSaveAndFindAll() {
        ProblemTopic topic1 = new ProblemTopic();
        topic1.setTopicName("Arrays");
        topic1.setTopicDescription("Array-related problems");

        ProblemTopic topic2 = new ProblemTopic();
        topic2.setTopicName("Strings");
        topic2.setTopicDescription("String manipulation problems");

        repository.save(topic1);
        repository.save(topic2);

        List<ProblemTopic> topics = repository.findAll();
        assertEquals(2, topics.size());
    }
}
