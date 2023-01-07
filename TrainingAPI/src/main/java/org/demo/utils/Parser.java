package org.demo.utils;

public class Parser {
    public String parsedType(String type){
        String[] tmp = type.split("\"");
        return tmp[3];
    }
}
