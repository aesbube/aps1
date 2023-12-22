package labs.aps.lab9.ex3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bus {
    public static boolean valid(int capacity, int N, String[] times) {
        Map<Integer, Integer> passCount = new HashMap<>();
        for (String time : times) {
            String[] time1 = time.split(" ");
            String[] board = time1[0].split(":");
            String[] exit = time1[1].split(":");

            int boardHr = Integer.parseInt(board[0]);
            int boardMin = Integer.parseInt(board[1]);
            int exitHr = Integer.parseInt(exit[0]);
            int exitMin = Integer.parseInt(exit[1]);

            for (int i = boardHr * 60 + boardMin; i <= exitHr * 60 + exitMin; i++) {
                Integer count = passCount.get(i);
                if (count == null) passCount.put(i, 1);
                else passCount.put(i, count + 1);
                if (passCount.get(i) > capacity) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int capacity = Integer.parseInt(sc.nextLine());
        int N = Integer.parseInt(sc.nextLine());
        String[] times = new String[N];
        for (int i = 0; i < N; i++) {
            times[i] = sc.nextLine();
        }

        boolean res = valid(capacity, N, times);
        System.out.println(res);
    }

}
