package com.example.section1;

import com.example.section1.util.Http;

import java.io.IOException;

public class AddressRetriever {
    private Http http;

    public AddressRetriever(Http http) {
        this.http = http;
    }

    public Address retrieve(double latitude, double longitude) throws IOException {
        return null;
    }
}
