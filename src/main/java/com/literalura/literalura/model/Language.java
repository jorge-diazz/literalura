package com.literalura.literalura.model;

public enum Language {
    ENGLISH("Ingles", "en"),
    SPANISH("Español", "es"),
    PORTUGUESE("Portugues", "pt"),
    FRENCH("Frances", "fr");

    private String languageTranslation;
    private String languageCode;

    Language(String languageTranslation, String languageCode) {
        this.languageTranslation = languageTranslation;
        this.languageCode = languageCode;
    }

    public static Language fromString(String language) {
        for (Language lang : Language.values()) {
            if (lang.languageCode.equalsIgnoreCase(language)) {
                return lang;
            }
        }
        throw new IllegalArgumentException("Lenguaje invalido: " + language);
    }

    public static Language fromStringTranslation(String language) {
        for (Language lang : Language.values()) {
            if (lang.languageTranslation.equalsIgnoreCase(language)) {
                return lang;
            }
        }
        throw new IllegalArgumentException("Lenguaje invalido: " + language);
    }
}
