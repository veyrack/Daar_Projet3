package com.daar.projet3.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Decodeur {

    public static void main(String[] args) {
        ArrayList<String> res = new ArrayList<>();
        res.add("c%23");
        res.add("c%2B%2B");

        System.out.println(decode(res));

    }

    public static List<String> decode(List<String> filtres){
        List<String> res = new ArrayList<String>();

        try {
            for(String language: filtres) {
                String result = java.net.URLDecoder.decode(language, StandardCharsets.UTF_8.name());
                res.add(result);
            }
        } catch (UnsupportedEncodingException e) {
            // not going to happen - value came from JDK's own StandardCharsets
        }

        return res;
    }

    public static String decode(String filtre){

        try {
            String result = java.net.URLDecoder.decode(filtre, StandardCharsets.UTF_8.name());
            return result;
            } catch (UnsupportedEncodingException unsupportedEncodingException) {
            unsupportedEncodingException.printStackTrace();
        }
        return "Null";
    }
}
