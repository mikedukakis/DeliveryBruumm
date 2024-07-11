package com.imf.exceptionsandtools;

import java.util.InputMismatchException;
import java.util.Scanner;

public class KeyboardInputHandler {
    private static final Scanner KEYBOARD_INPUT = new Scanner(System.in);

    public static int readInt(String question) {
        int intNumber = 0;
        boolean error = true;
        do {
            try {
                intNumber = KEYBOARD_INPUT.nextInt();
                KEYBOARD_INPUT.nextLine();
                error = false;
            } catch(InputMismatchException e) {
                KEYBOARD_INPUT.nextLine();
                System.out.println("Format error, integer expected");
                System.out.println(question);
            }
        } while (error);
        return intNumber;
    }

    public static String readString(String question) {
        String str = "";
        boolean error = true;
        do {
            try{
                str = KEYBOARD_INPUT.nextLine();
                if(str.matches("[0-9]+") || str.isEmpty()){
                    throw new Exception();
                } else {
                    error = false;
                }
            } catch(Exception e) {
                System.out.println("Format error, string expected");
                System.out.println(question);
            }
        } while(error);
        return str;
    }
}
