package com.example.section1.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import static com.example.section1.util.ContainsMatches.containsMatches;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SearchTest {
    private static final String A_TITLE = "1";
    private InputStream stream;

    @BeforeEach
    public void turnOffLogging() {
        Search.LOGGER.setLevel(Level.OFF);
    }

    @AfterEach
    public void closeResources() throws IOException {
        stream.close();
    }


    @Test
    public void returnsMatchesShowingContextWhenSearchStringInContent() {
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

    @Test
    public void noMatchesReturnedWhenSearchStringNotInContent() {
        // 준비
        stream = streamOn("any text");
        Search search = new Search(stream, "text that doesn't match", A_TITLE);

        // 실행
        search.execute();

        // 단언
        assertTrue(search.getMatches().isEmpty());
    }

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

    private InputStream streamOn(String pageContent) {
        return new ByteArrayInputStream(pageContent.getBytes());
    }
}
