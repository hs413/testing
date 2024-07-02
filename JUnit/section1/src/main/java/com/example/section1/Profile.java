package com.example.section1;


import java.util.HashMap;
import java.util.Map;

public class Profile {
    private Map<String, Answer> answers = new HashMap<>();

    private int score;
    private String name;

    public Profile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void add(Answer answer) {
        answers.put(answer.getQuestionText(), answer);
    }

    public boolean matches(Criteria criteria) {
        calculateScore(criteria);

        if (doesNotMeetAnyMustMatchCriterion(criteria))
            return false;

        return anyMatches(criteria);
    }

    // Criterion 클래스로 이동
//    private boolean matches(Criterion criterion, Answer answer) {
//        return criterion.getWeight() == Weight.DontCare || answer.match(criterion.getAnswer());
//    }

    private Answer answerMatching(Criterion criterion) {
        return answers.get(criterion.getAnswer().getQuestionText());
    }

    private boolean anyMatches(Criteria criteria) {
        boolean anyMatches = false;
        for (Criterion criterion : criteria)
            anyMatches |= criterion.matches(answerMatching(criterion));

        return anyMatches;
    }

    private void calculateScore(Criteria criteria) {
        score = 0;
        for (Criterion criterion: criteria)
            if (criterion.matches(answerMatching(criterion)))
                score += criterion.getWeight().getValue();
    }

    private boolean doesNotMeetAnyMustMatchCriterion(Criteria criteria) {
        for (Criterion criterion: criteria) {
            boolean match = criterion.matches(answerMatching(criterion));
            if (!match && criterion.getWeight()== Weight.MustMatch)
                return true;
        }
        return false;
    }
}
