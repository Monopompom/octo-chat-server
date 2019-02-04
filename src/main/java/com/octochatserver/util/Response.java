package com.octochatserver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;

public class Response {
    private static final String DATA = "data";
    private static final String ERROR = "error";
    private static final String SUCCESS = "success";

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

    public String toJSON() throws JsonProcessingException {
        return new ObjectMapper().writerWithView(Views.Normal.class).writeValueAsString(response);
    }

    public String toJSONMiddle() throws JsonProcessingException {
        return new ObjectMapper().writerWithView(Views.Middle.class).writeValueAsString(response);
    }

    public String toJSONManager() throws JsonProcessingException {
        return new ObjectMapper().writerWithView(Views.Manager.class).writeValueAsString(response);
    }
}