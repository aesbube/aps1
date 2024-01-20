package FirstMidtermExercises.ex3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bus {

    public static void minMaxValues(int n, int m) {
        int min = 100 * n;
        if (n <= m) min = 100 * n + (m - n) * 100;
        int max = 100 * n + (m - 1) * 100;
        if (m == 0) max = 100 * n;

        System.out.println(min + "\n" + max);
    }

    public static void main(String[] args) throws Exception {
        int i, j, k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        br.close();

        minMaxValues(N, M);


    }

}
