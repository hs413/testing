package com.example.section1.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatCompilerTest {
    @Test
    public void responseByQuestionAnswersCountsByQuestionText() {
        StatCompiler stats = new StatCompiler();
        List<BooleanAnswer> answers = new ArrayList<>();
        answers.add(new BooleanAnswer(1, true));
        answers.add(new BooleanAnswer(1, true));
        answers.add(new BooleanAnswer(1, true));
        answers.add(new BooleanAnswer(1, false));
        answers.add(new BooleanAnswer(2, true));
        answers.add(new BooleanAnswer(2, true));

        Map<Integer, String> questions = new HashMap<>();
        questions.put(1, "Tu");
        questions.put(2, "Re");

        Map<String, Map<Boolean, AtomicInteger>> responses = stats.responsesByQuestion(answers, questions);

        assertThat(responses.get("Tu").get(Boolean.TRUE).get()).isEqualTo(3);
        assertThat(responses.get("Tu").get(Boolean.FALSE).get()).isEqualTo(1);
        assertThat(responses.get("Re").get(Boolean.TRUE).get()).isEqualTo(2);
        assertThat(responses.get("Re").get(Boolean.FALSE).get()).isEqualTo(0);
    }
}
