package com.springex.controller.formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.format.Formatter;

public class LocalDateFormatter implements Formatter<LocalDate>{

    @Override
    public LocalDate parse(String text, Locale locale) {
        // parse: String표현을 LocalDate객체로 파싱하는 역할을 한다.
        // Locale: 지역 또는 언와 관련된정보를 나타내는 java 클래스. html lang="ko"
        return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(object);
    }

}