package com.springex.controller.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

public class CheckboxFormatter implements Formatter<Boolean>{

    @Override
    public Boolean parse(String text, Locale locale) throws ParseException{
        if(text == null) {
            return false;
        }

        return text.equals("on"); //on값이 있으면 true를 반환
    }

    @Override
    public String print(Boolean object, Locale locale) {
        //true or false로 출력. 
        return object.toString();
    }
}