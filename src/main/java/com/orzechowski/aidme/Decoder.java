package com.orzechowski.aidme;

public class Decoder
{
    //szyfrowanie znakow w linii zapytania
    //xyz121 = .
    //xyz122 = @

    public String decodeEmail(String input)
    {
        return input.replace("xyz122", "@").replace("xyz121", ".");
    }
}
