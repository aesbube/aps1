package LabExercises.lab5.ex1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class OddEvenSort {

    static void oddEvenSort(int a[], int n) {
        int even = 0, odd = 0;
        int[] temp = new int[a.length];
        int num = a.length - 1;
        int num2 = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 0) {
                temp[num--] = a[i];
                even++;
            } else {
                temp[num2++] = a[i];
                odd++;
            }
        }
        num2 = 0;
        int[] even1 = new int[even];
        for (int i = odd; i < a.length; i++) {
            even1[num2++] = temp[i];
        }
        Arrays.sort(even1);

        if (odd != 0) Arrays.sort(temp, 0, odd);

        for (int i = 0; i < odd; i++) a[i] = temp[i];
        num2 = even1.length;
        for (int i = odd; i < a.length; i++) a[i] = even1[--num2];

    }

    public static void main(String[] args) throws IOException {
        int i;
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String[] pom = s.split(" ");
        int[] a = new int[n];
        for (i = 0; i < n; i++)
            a[i] = Integer.parseInt(pom[i]);
        oddEvenSort(a, n);
        for (i = 0; i < n - 1; i++)
            System.out.print(a[i] + " ");
        System.out.print(a[i]);
    }
}