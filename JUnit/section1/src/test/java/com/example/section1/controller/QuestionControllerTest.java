package com.example.section1.controller;

import com.example.section1.domain.Question;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class QuestionControllerTest {
    private QuestionController controller;
    @BeforeEach
    public void create() {
        controller = new QuestionController();
        controller.deleteAll();
    }
    @Test
    public void questionAnswersDateAdded() {
        Instant now = new Date().toInstant();
        controller.setClock(Clock.fixed(now, ZoneId.of("America/Denver")));
        int id = controller.addBooleanQuestion("text");

        Question question = controller.find(id);
        Assertions.assertThat(question.getCreateTimestamp()).isEqualTo(now);

    }
}
