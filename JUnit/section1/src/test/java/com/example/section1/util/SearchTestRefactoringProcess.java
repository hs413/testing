package com.example.section1.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.logging.Level;

import static com.example.section1.util.ContainsMatches.containsMatches;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SearchTestRefactoringProcess {

    // 기존 테스트 코드
    public void testSearch() {
        try {
            String pageContent = "There are certain queer times and occasions "
                    + "in this strange mixed affair we call life when a man "
                    + "takes this whole universe for a vast practical joke, "
                    + "though the wit thereof he but dimly discerns, and more "
                    + "than suspects that the joke is at nobody's expense but "
                    + "his own.";
            byte[] bytes = pageContent.getBytes();
            ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
            // search
            Search search = new Search(stream, "practical joke", "1");
            Search.LOGGER.setLevel(Level.OFF);
            search.setSurroundingCharacterCount(10);
            search.execute();
            assertFalse(search.errored());
            List<Match> matches = search.getMatches();
            assertThat(matches).isNotNull();
            assertTrue(matches.size() >= 1);
            Match match = matches.get(0);
            assertThat(match.searchString).isEqualTo("practical joke");
            assertThat(match.surroundingContext).isEqualTo("or a vast practical joke, though t");
            stream.close();

            // negative
            URLConnection connection =
                    new URL("http://bit.ly/15sYPA7").openConnection();
            InputStream inputStream = connection.getInputStream();
            search = new Search(inputStream, "smelt", "http://bit.ly/15sYPA7");
            search.execute();
            assertThat(search.getMatches().size()).isEqualTo(0);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
            fail("exception thrown in test" + e.getMessage());
        }
    }

    // 1. 예외처리 (try/catch) 제거
    public void refactoring1() throws IOException {
        String pageContent = "There are certain queer times and occasions "
                + "in this strange mixed affair we call life when a man "
                + "takes this whole universe for a vast practical joke, "
                + "though the wit thereof he but dimly discerns, and more "
                + "than suspects that the joke is at nobody's expense but "
                + "his own.";
        byte[] bytes = pageContent.getBytes();
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        // search
        Search search = new Search(stream, "practical joke", "1");
        Search.LOGGER.setLevel(Level.OFF);
        search.setSurroundingCharacterCount(10);
        search.execute();
        assertFalse(search.errored());
        List<Match> matches = search.getMatches();
        assertThat(matches).isNotNull();
        assertTrue(matches.size() >= 1);
        Match match = matches.get(0);
        assertThat(match.searchString).isEqualTo("practical joke");
        assertThat(match.surroundingContext).isEqualTo("or a vast practical joke, though t");
        stream.close();

        // negative
        URLConnection connection =
                new URL("http://bit.ly/15sYPA7").openConnection();
        InputStream inputStream = connection.getInputStream();
        search = new Search(inputStream, "smelt", "http://bit.ly/15sYPA7");
        search.execute();
        assertThat(search.getMatches().size()).isEqualTo(0);
        stream.close();
    }
    // 2. not null 단언 제거
    public void refactoring2() throws IOException {
        // not null 단언 제거
//        assertThat(matches).isNotNull();
    }

    // 3. getMatches() 메서드 클래스로 추상화
    public void refactoring3() throws IOException {
        String pageContent = "...";
        byte[] bytes = pageContent.getBytes();
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        Search search = new Search(stream, "practical joke", "1");

//        List<Match> matches = search.getMatches();
//        assertTrue(matches.size() >= 1);
//        Match match = matches.get(0);
//        assertThat(match.searchString).isEqualTo("practical joke");
//        assertThat(match.surroundingContext).isEqualTo("or a vast practical joke, though t");

        assertThat(search.getMatches()).isEqualTo(containsMatches(new Match[] {
                new Match("1", "practical joke",
                        "or a vast practical joke, though t") }));

        // ...
    }


    // 4. 크기 비교 -> isEmpty()
    public void refactoring4() throws IOException {
        String pageContent = "...";
        byte[] bytes = pageContent.getBytes();
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        Search search = new Search(stream, "practical joke", "1");

        // 크기 비교 -> isEmpty()
//        assertThat(search.getMatches().size()).isEqualTo(0);
        assertThat(search.getMatches()).isEmpty();

        // ...
    }

    // 5. 리터럴 -> 상수로 변경 - 의미를 분명하게
    private static final String A_TITLE = "1";

    public void refactoring5() throws IOException {
        String pageContent = "...";
        byte[] bytes = pageContent.getBytes();
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);

//        Search search = new Search(stream, "practical joke", "1");
        Search search = new Search(stream, "practical joke", A_TITLE);


        // negative
        URLConnection connection =
                new URL("http://bit.ly/15sYPA7").openConnection();
        InputStream inputStream = connection.getInputStream();
        search = new Search(inputStream, "smelt", A_TITLE);
    }

    // 6. inputStream 객체 생성 도우미 메서드
    private InputStream streamOn(String pageContent) {
        return new ByteArrayInputStream(pageContent.getBytes());
    }

    public void refactoring6() throws IOException {
//        String pageContent = "There are certain queer times and occasions "
//                + "in this strange mixed affair we call life when a man "
//                + "takes this whole universe for a vast practical joke, "
//                + "though the wit thereof he but dimly discerns, and more "
//                + "than suspects that the joke is at nobody's expense but "
//                + "his own.";
//        byte[] bytes = pageContent.getBytes();
//        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);

        InputStream stream =
                streamOn("There are certain queer times and occasions "
                        + "in this strange mixed affair we call life when a man "
                        + "takes this whole universe for a vast practical joke, "
                        + "though the wit thereof he but dimly discerns, and more "
                        + "than suspects that the joke is at nobody's expense but "
                        + "his own.");
    }

    // 7. 테스트 분할
    // 테스트는 하나의 기능을 검증 하는 것이 좋음
    public void returnsMatchesShowingContextWhenSearchStringInContent() throws IOException {
        InputStream stream =
                streamOn("There are certain queer times and occasions "
                        + "in this strange mixed affair we call life when a man "
                        + "takes this whole universe for a vast practical joke, "
                        + "though the wit thereof he but dimly discerns, and more "
                        + "than suspects that the joke is at nobody's expense but "
                        + "his own.");
        Search search = new Search(stream, "practical joke", A_TITLE);
        Search.LOGGER.setLevel(Level.OFF);
        search.setSurroundingCharacterCount(10);
        search.execute();
        assertFalse(search.errored());
        assertThat(search.getMatches()).isEqualTo(containsMatches(new Match[] {
                new Match(A_TITLE, "practical joke",
                        "or a vast practical joke, though t") }));
        stream.close();
    }

    public void noMatchesReturnedWhenSearchStringNotIntContent() throws MalformedURLException, IOException {
        URLConnection connection =
                new URL("http://bit.ly/15sYPA7").openConnection();
        InputStream inputStream = connection.getInputStream();
        Search search = new Search(inputStream, "smelt", A_TITLE);
        search.execute();
        assertThat(search.getMatches().size()).isEqualTo(0);
        inputStream.close();
    }

    // 8. 테스트와 무관한 세부사항(공통 항목) 추출 및 제거
    // 9. 준비, 실행, 단언 단계 구분
    private InputStream stream;

    @BeforeEach
    public void turnOffLogging() {
        Search.LOGGER.setLevel(Level.OFF);
    }

    @AfterEach
    public void closeResources() throws IOException {
        stream.close();
    }

    public void returnsMatchesShowingContextWhenSearchStringInContent2() {
        // 준비
        stream =
                streamOn("There are certain queer times and occasions "
                        + "in this strange mixed affair we call life when a man "
                        + "takes this whole universe for a vast practical joke, "
                        + "though the wit thereof he but dimly discerns, and more "
                        + "than suspects that the joke is at nobody's expense but "
                        + "his own.");
        Search search = new Search(stream, "practical joke", A_TITLE);
        search.setSurroundingCharacterCount(10);

        // 실행
        search.execute();

        // 단언
        assertThat(search.getMatches()).isEqualTo(containsMatches(new Match[] {
                new Match(A_TITLE, "practical joke",
                        "or a vast practical joke, though t") }));
    }

    // 10. 테스트를 이해하기 쉽게 작성
    public void returnsMatchesShowingContextWhenSearchStringInContent3() {
        // 준비
        stream = streamOn("rest of text here"
                + "1234567890search term1234567890"
                + "more rest of text");
        Search search = new Search(stream, "search term", A_TITLE);
        search.setSurroundingCharacterCount(10);

        // 실행
        search.execute();

        // 단언
        assertThat(search.getMatches()).isEqualTo(containsMatches(new Match[] {
                new Match(A_TITLE, "search term",
                        "1234567890search term1234567890") }));
    }

    public void noMatchesReturnedWhenSearchStringNotInContent() {
        stream = streamOn("any text");
        Search search = new Search(stream, "text that doesn't match", A_TITLE);

        search.execute();

        assertTrue(search.getMatches().isEmpty());
    }

    // 11.
    public void returnsErroredWhenUnableToReadStream() {
        stream = createStreamThrowingErrorWhenRead();
        Search search = new Search(stream, "", "");

        search.execute();

        assertTrue(search.errored());
    }

    public void erroredReturnsFalseWhenReadStream() {
        stream = streamOn("");
        Search search = new Search(stream, "", "");

        search.execute();

        assertFalse(search.errored());
    }

    private InputStream createStreamThrowingErrorWhenRead() {
        return new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException();
            }
        };
    }
}
