package com.example.section1.tdd;

public class Profile {
    private Answer answer;

    public boolean matches(Criterion criterion) {
        return answer != null;
    }

    public void add(Answer answer) {
        this.answer = answer;
    }
}
