package com.example.section1;

import com.example.section1.util.Http;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AddressRetrieverTest {
    @Mock
    private Http http;
    @InjectMocks
    private AddressRetriever retriever;

    @BeforeEach
    public void createRetriever() {
        retriever = new AddressRetriever();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void answersAppropriateAddressForValidCoordinates()
            throws IOException {
        when(http.get(contains("lat=38.000000&lon=-104.000000")))
                .thenReturn(
                        "{\"address\":{"
                                + "\"house_number\":\"324\","
                                + "\"road\":\"North Tejon Street\","
                                + "\"city\":\"Colorado Springs\","
                                + "\"state\":\"Colorado\","
                                + "\"postcode\":\"80903\","
                                + "\"country_code\":\"us\"}"
                                + "}"
                );

        Address address = retriever.retrieve(38.0,-104.0);

        assertThat(address.houseNumber).isEqualTo("324");
        assertThat(address.road).isEqualTo("North Tejon Street");
        assertThat(address.city).isEqualTo("Colorado Springs");
        assertThat(address.state).isEqualTo("Colorado");
        assertThat(address.zip).isEqualTo("80903");
    }

}
