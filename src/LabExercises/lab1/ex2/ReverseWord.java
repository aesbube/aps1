package LabExercises.lab1.ex2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ReverseWord {
    public static String printReversed(String word) {
        char[] wordChar = word.toCharArray();
        List<Character> revWord = new ArrayList<>();
        for (char c : wordChar) {
            revWord.add(c);
        }

        Collections.reverse(revWord);
        StringBuilder str = new StringBuilder();
        for (char a :
                revWord) {
            str.append(a);
        }

        return str.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String word = scanner.next();
            System.out.println(printReversed(word));
        }

    }
}
