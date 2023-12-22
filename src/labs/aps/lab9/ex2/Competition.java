package labs.aps.lab9.ex2;

import java.util.Scanner;
import java.util.Stack;

public class Competition {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < n; i++) {
            stack.push(Integer.parseInt(sc.nextLine()));
        }
        int m = Integer.parseInt(sc.nextLine());
        int a = 1;
        while (!stack.isEmpty()) {
            Integer player = stack.pop();
            if (player > m) a++;
        }

        System.out.println(a);
    }
}
