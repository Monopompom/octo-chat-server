package com.octochatserver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

public class Response {
    private static final String DATA = "data";
    private static final String ERROR = "error";
    private static final String SUCCESS = "success";

    private static final Logger LOGGER = LoggerFactory.getLogger(Response.class);

    private Map<String, Object> response;

    public Response() {
        response = new LinkedHashMap<>();
    }

    public Response success() {
        response.put(SUCCESS, true);

        return this;
    }

    public Response failure() {
        response.put(SUCCESS, false);

        return this;
    }

    public Response data(Object data) {
        response.put(DATA, data);

        return this;
    }

    public Response error(Object error) {
        response.put(ERROR, error);

        return this;
    }

    public Map<String, Object> toMap() {
        return response;
    }

    public String toJSON() {
        StringBuilder result = new StringBuilder();

        try {
            result.append(
                new ObjectMapper()
                    .writerWithView(Views.Normal.class)
                    .withDefaultPrettyPrinter()
                    .writeValueAsString(response)
            );
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage());
        }

        return result.toString();
    }

    public String toJSONMiddle() {
        StringBuilder result = new StringBuilder();

        try {
            result.append(
                new ObjectMapper()
                    .writerWithView(Views.Middle.class)
                    .withDefaultPrettyPrinter()
                    .writeValueAsString(response)
            );
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage());
        }

        return result.toString();
    }

    public String toJSONHeight() {
        StringBuilder result = new StringBuilder();

        try {
            result.append(
                new ObjectMapper()
                    .writerWithView(Views.Height.class)
                    .withDefaultPrettyPrinter()
                    .writeValueAsString(response)
            );
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage());
        }

        return result.toString();
    }
}