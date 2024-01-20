package SecondMidtermExercises.ex3.withJavaMaps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MostFrequentSubstring {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> hashTable = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine().trim();
        List<String> substrings = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            for (int j = i + 1; j < word.length() + 1; j++) {
                substrings.add(word.substring(i, j));
            }
        }

        substrings.forEach(i -> {
            hashTable.putIfAbsent(i, 0);
            hashTable.put(i, hashTable.get(i) + 1);
        });

        int max = -1;
        String result = "";
        for (String key : hashTable.keySet()) {
            if (hashTable.get(key) > max || (hashTable.get(key) == max && key.length() > result.length()) ||
                    (hashTable.get(key) == max && key.length() == result.length() && key.compareTo(result) < 0)) {
                max = hashTable.get(key);
                result = key;
            }
        }
        System.out.println(result);

    }
}
