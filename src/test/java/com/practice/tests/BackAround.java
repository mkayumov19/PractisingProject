package com.practice.tests;

public class BackAround {
    public static void main(String[] args) {
        String str = "cat";
        System.out.println(backAround("dog"));

        String str1 = "";
        System.out.println("str1 = " + startOz(str1));

    }

    public static String backAround(String str){
        String temp;
        if (str.length() < 1){
            return "Word less than 1";
        }
        else {
            temp = str.substring(str.length()-1);
            str = temp + str + temp;
            str.charAt(0);
            return str;
        }
    }

    public static String startOz(String str1) {
        String temp = "";
        if (str1.isEmpty()) {
            temp = str1;
        } else if (str1.startsWith("oz")) {
            temp = str1.substring(0, 2);
            return temp;
        } else if (str1.startsWith("o")) {
            temp = str1.substring(0, 1);
            return temp;
        } else if (str1.substring(1, 2).equals("z")) {
            temp = str1.substring(1, 2);
            return temp;
        }
    return temp;
    }
}
