package com.example.section1.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {
    private Profile profile;

    private BooleanQuestion questionIsThereRelocation;
    private Answer answerThereIsRelocation;
    private Answer answerThereIsNotRelocation;

    private BooleanQuestion questionReimbursesTuition;
    private Answer answerDoesNotReimburseTuition;
    private Answer answerReimbursesTuition;

    private Criteria criteria;

    @BeforeEach
    public void createProfile() {
        profile = new Profile();
    }


    @BeforeEach
    public void createQuestionAndAnswer() {
        questionIsThereRelocation = new BooleanQuestion(1, "Relocation package");
        answerThereIsRelocation = new Answer(questionIsThereRelocation, Bool.TRUE);
        answerThereIsNotRelocation = new Answer(questionIsThereRelocation, Bool.FALSE);

        questionReimbursesTuition = new BooleanQuestion(1, "Reimburses tuition?");
        answerDoesNotReimburseTuition = new Answer(questionReimbursesTuition, Bool.FALSE);
        answerReimbursesTuition = new Answer(questionReimbursesTuition, Bool.TRUE);
    }

    @BeforeEach
    public void createCriteria() {
        criteria = new Criteria();
    }

    @Test
    public void matchesWhenProfileContainsMatchingAnswer() {
        profile.add(answerThereIsRelocation);
        Criterion criterion = new Criterion(answerThereIsRelocation, Weight.Important);

        boolean result = profile.matches(criterion);

        assertTrue(result);
    }

    @Test
    public void doesNotMatchWhenNotMatchingAnswer() {
        profile.add(answerThereIsNotRelocation);
        Criterion criterion = new Criterion(answerThereIsRelocation, Weight.Important);

        boolean result = profile.matches(criterion);

        assertFalse(result);

    }

    @Test
    public void matchesWhenContainsMultipleAnswers() {
        profile.add(answerThereIsRelocation);
        profile.add(answerDoesNotReimburseTuition);

        Criterion criterion = new Criterion(answerThereIsRelocation, Weight.Important);

        boolean result = profile.matches(criterion);

        assertTrue(result);

    }

    @Test
    public void doesNotMatchWhenNoneOfMultipleCriteriaMatch() {
        profile.add(answerDoesNotReimburseTuition);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
        criteria.add(new Criterion(answerReimbursesTuition, Weight.Important));

        assertFalse(profile.matches(criteria));
    }

    @Test
    public void doesNotMatchWhenAnyMustMeetCriteriaNotMet() {
        profile.add(answerThereIsRelocation);
        profile.add(answerDoesNotReimburseTuition);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
        criteria.add(new Criterion(answerReimbursesTuition, Weight.MustMatch));

        assertFalse(profile.matches(criteria));
    }

    @Test
    public void matchesWhenCriterionIsDontCare() {
        profile.add(answerDoesNotReimburseTuition);
        Criterion criterion = new Criterion(answerReimbursesTuition, Weight.DontCare);

        assertTrue(profile.matches(criterion));
    }

    @Test
    public void scoreIsZeroWhenThereAreNoMatches() {
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));

        ProfileMatch match = profile.match(criteria);

        assertThat(match.getScore()).isEqualTo(0);
    }
}
