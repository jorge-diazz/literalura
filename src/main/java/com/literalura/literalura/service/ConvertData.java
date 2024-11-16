package com.literalura.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertData implements IConvertData {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T convert(String source, Class<T> target) {
        try {
            return objectMapper.readValue(source, target);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
