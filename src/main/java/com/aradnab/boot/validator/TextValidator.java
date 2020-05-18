package com.aradnab.boot.validator;

public class TextValidator {
    public static TextValidator defaultValidator = new TextValidator();

    public static boolean equals(String a,String b){
        if (a==null) return b==null;
        if (b==null) return a==null;
        return a.equals(b);
    }

}
