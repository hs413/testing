package com.example.section1.basic;

import org.junit.jupiter.api.BeforeEach;

class ProfileTest {
//    @Test
//    public void test() {
//        Profile profile = new Profile("Bull Hockey. Inc.");
//        Question question = new BooleanQuestion(1, "Got bonuses?");
//        Criteria criteria = new Criteria();
//        Answer criteriaAnswer = new Answer(question, Bool.TRUE);
//        Criterion criterion = new Criterion(criteriaAnswer, Weight.MustMatch);
//        criteria.add(criterion);
//    }
//
//    @Test
//    public void test() {
//        Profile profile = new Profile("Bull Hockey. Inc.");
//        Question question = new BooleanQuestion(1, "Got bonuses?");
//        Answer profileAnswer = new Answer(question, Bool.FALSE);
//        profile.add(profileAnswer);
//        Criteria criteria = new Criteria();
//        Answer criteriaAnswer = new Answer(question, Bool.TRUE);
//        Criterion criterion = new Criterion(criteriaAnswer, Weight.MustMatch);
//        criteria.add(criterion);
//    }

    private Profile profile;
    private BooleanQuestion question;
    private Criteria criteria;
    private Answer answerReimbursesTuition;
    private Answer answerDoesNotReimburseTuition;
    private Answer answerThereIsRelocation;
    private Answer answerThereIsNoRelocation;
    private Question questionIsThereRelocation;
    private Question questionReimbursesTuition;

    @BeforeEach
    public void create() {
        profile = new Profile("Bull Hockey. Inc.");
        question = new BooleanQuestion(1, "Got bonuses?");
        criteria = new Criteria();

        questionIsThereRelocation =
                new BooleanQuestion(1, "Relocation package?");

        answerThereIsRelocation =
                new Answer(questionIsThereRelocation, Bool.TRUE);
        answerThereIsNoRelocation =
                new Answer(questionIsThereRelocation, Bool.FALSE);

        questionReimbursesTuition =
                new BooleanQuestion(1, "Reimburses tuition?");
        answerReimbursesTuition =
                new Answer(questionReimbursesTuition, Bool.TRUE);
        answerDoesNotReimburseTuition =
                new Answer(questionReimbursesTuition, Bool.FALSE);
    }

//    @Test
//    public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
//        profile.add(new Answer(question, Bool.FALSE));
//        criteria.add(new Criterion(new Answer(question, Bool.TRUE),
//                Weight.MustMatch));
//
//        boolean matches = profile.matches(criteria);
//        assertFalse(matches);
//    }
//
//    @Test
//    public void matchAnswersTrueForAnyDontCareCriteria() {
//        profile.add(new Answer(question, Bool.FALSE));
//        criteria.add(new Criterion(new Answer(question, Bool.TRUE),
//                Weight.DontCare));
//
//        boolean matches = profile.matches(criteria);
//        assertTrue(matches);
//    }
//
//    @Test
//    public void matchAnswersTrueWhenAnyOfMultipleCriteriaMatch() {
//        profile.add(answerThereIsRelocation);
//        profile.add(answerDoesNotReimburseTuition);
//        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
//        criteria.add(new Criterion(answerReimbursesTuition, Weight.Important));
//
//        boolean matches = profile.matches(criteria);
//
//        assertTrue(matches);
//    }
}
