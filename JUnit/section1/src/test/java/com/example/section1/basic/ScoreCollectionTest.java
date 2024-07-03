package com.example.section1.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ScoreCollectionTest {
    private ScoreCollection collection;

    @BeforeEach
    public void create() {
        collection = new ScoreCollection();
    }
    @Test
    public void test() {
        // stub
//        fail("Not yet implemented");
    }

    @Test
    public void answersArithmeticMeanOfTwoNumbers() {
        // 준비 give
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 5);
        collection.add(() -> 7);

        // 실행 when
        int actualResult = collection.arithmeticMean();

        // 단언 then
        assertThat(actualResult).isEqualTo(6);
    }

    @Test
    public void throwsExceptionWhenAddingNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            collection.add(null);
        });
    }

    @Test
    public void answersZeroWhenNoElementsAdded() {
        assertThat(collection.arithmeticMean()).isEqualTo(0);
    }

    @Test
    public void dealsWithIntegerOverflow() {
        collection.add(() -> Integer.MAX_VALUE);
        collection.add(() -> 1);

        assertThat(collection.arithmeticMean()).isEqualTo(1073741824);
    }
}
