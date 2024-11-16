package com.literalura.literalura.service;

public interface IConvertData {
    <T> T convert(String source, Class<T> target);
}
