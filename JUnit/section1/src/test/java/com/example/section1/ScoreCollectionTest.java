package com.example.section1;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ScoreCollectionTest {

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

}
