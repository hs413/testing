package com.example.section1;

import java.util.ArrayList;
import java.util.List;

public class ScoreCollection {
    private List<Scoreable> scores = new ArrayList<>();

    public void add(Scoreable scoreable) {
        if (scoreable == null) throw new IllegalArgumentException();
        scores.add(scoreable);
    }

    public int arithmeticMean() {
        if (scores.isEmpty()) return 0;

        long total = scores.stream().mapToLong(Scoreable::getScore).sum();

        return (int)(total / scores.size());
    }
}
