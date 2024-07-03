package com.example.section1.basic;


import lombok.Getter;

public class Profile {
    private AnswerCollection answers = new AnswerCollection();

    @Getter
    private String name;

    public Profile(String name) {
        this.name = name;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public MatchSet getMatchSet(Criteria criteria) {
        return new MatchSet(answers, criteria);
    }

}
