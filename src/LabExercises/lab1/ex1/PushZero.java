package LabExercises.lab1.ex1;

import java.util.Scanner;

public class PushZero {
    static void pushZerosToBeginning(int arr[], int n) {
        int a = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] != 0) {
                arr[a--] = arr[i];
            }
        }

        while (a >= 0) {
            arr[a--] = 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        pushZerosToBeginning(arr, n);
        System.out.println("Transformiranata niza e:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

    }
}
