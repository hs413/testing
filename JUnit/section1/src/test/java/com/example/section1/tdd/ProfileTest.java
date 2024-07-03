package com.example.section1.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    @Test
    public void matchesNothingWhenProfileEmpty() {
        Profile profile = new Profile();
        Question question = new BooleanQuestion(1, "Relocation package");
        Criterion criterion = new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare);
        boolean result = profile.matches(criterion);
        assertFalse(result);

    }
}
