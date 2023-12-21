package labs.aps.lab3.ex2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CountWordPairs {

    //TODO: implement function
//    public static int countWordPairs(String[] words) {
//        int sum = 0;
//        for (int i = 0; i < words.length; i++) {
//            for (int j = i + 1; j < words.length; j++) {
//                if (words[i].charAt(0) == words[j].charAt(0)) {
//                    sum++;
//                }
//            }
//        }
//
//        return sum;
//    }

    public static int countWordPairs(String[] words) {
        // Create a map to store the count of words for each starting character
        Map<Character, Integer> charCountMap = new HashMap<>();
        int sum = 0;

        for (String word : words) {
            char firstChar = word.charAt(0);

            // Increment the count for the starting character
            charCountMap.put(firstChar, charCountMap.getOrDefault(firstChar, 0) + 1);

            // Calculate the sum based on the count of words starting with the same character
            sum += charCountMap.get(firstChar) - 1;
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();

        String words[] = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = input.next();
        }

        System.out.println(countWordPairs(words));

    }
}