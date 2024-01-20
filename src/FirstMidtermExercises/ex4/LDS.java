package FirstMidtermExercises.ex4;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.max;


public class LDS {


    private static int najdolgaOpagackaSekvenca(int[] a) {
        int[] counter = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            int first = a[i];
            for (int j = 0; j < i; j++) {
                int elem = a[j];
                if (first < elem) {
                    counter[i] = max(counter[i], counter[j] + 1);
                }
            }
        }
        Arrays.sort(counter);
        if (counter[a.length - 1] == 0) return 1;
        return counter[a.length - 1] + 1;


    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(najdolgaOpagackaSekvenca(a));
    }


}
