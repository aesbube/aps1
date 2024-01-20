package LabExercises.lab1.ex3;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int sumSales(SalesPerson sp) {
        int temp = 0;
        for (int i = 0; i < 4; i++) {
            List<Integer> revenues = Arrays.asList(sp.getQuarters()[i].getRevenues());
            temp += revenues.parallelStream().mapToInt(Integer::intValue).sum();
        }

        return temp;
    }

    public static SalesPerson salesChampion(SalesPerson[] arr) {
        SalesPerson max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (sumSales(max) < sumSales(arr[i])) {
                max = arr[i];
            }
        }

        return max;
    }


    public static void table(SalesPerson[] arr) {
        System.out.println("SP   1   2   3   4   Total");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i].toString() + sumSales(arr[i]));
        }
        System.out.print("\n");

    }


    public static void main(String[] args) {

        int n, c;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        SalesPerson[] arr = new SalesPerson[n];
        String na;
        for (int i = 0; i < n; i++) {
            na = input.next();
            QuarterlySales[] quarters = new QuarterlySales[4];
            for (int j = 0; j < 4; j++) {
                c = input.nextInt();
                Integer[] sales = new Integer[c];
                quarters[j] = new QuarterlySales();
                for (int k = 0; k < c; k++) {
                    sales[k] = input.nextInt();
                }
                quarters[j].setNumOfSales(c);
                quarters[j].setRevenues(sales);
                quarters[j].setQuarterNo(j);
            }
            arr[i] = new SalesPerson(na, quarters);
        }

        table(arr);
        System.out.println("SALES CHAMPION: " + salesChampion(arr).getName());

    }
}
