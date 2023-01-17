package org.demo.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrainingTest {

    @Test
    void getDate() {
        var now = LocalDateTime.now();
        var training = new Training("test",now);
        assertEquals(training.getDate(),now);
    }

    @Test
    void getType() {
        var now = LocalDateTime.now();
        var training = new Training("test",now);
        assertEquals(training.getType(),"test");
    }

}