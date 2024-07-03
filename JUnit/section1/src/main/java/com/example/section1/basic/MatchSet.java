package com.example.section1.basic;

public class MatchSet {
    private AnswerCollection answers;
    private Criteria criteria;

    public MatchSet(AnswerCollection answers, Criteria criteria) {
        this.answers = answers;
        this.criteria = criteria;
    }
    public int getScore() {
        int score = 0;
        for (Criterion criterion: criteria)
            if (criterion.matches(answers.answerMatching(criterion)))
                score += criterion.getWeight().getValue();

        return score;
    }

    public boolean matches() {
        if (doesNotMeetAnyMustMatchCriterion())
            return false;

        return anyMatches();
    }

    private boolean anyMatches() {
        boolean anyMatches = false;
        for (Criterion criterion : criteria)
            anyMatches |= criterion.matches(answers.answerMatching(criterion));

        return anyMatches;
    }


    private boolean doesNotMeetAnyMustMatchCriterion() {
        for (Criterion criterion: criteria) {
            boolean match = criterion.matches(answers.answerMatching(criterion));
            if (!match && criterion.getWeight()== Weight.MustMatch)
                return true;
        }
        return false;
    }
}