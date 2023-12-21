package labs.aps.lab4.ex3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SumOfAbsoluteDifferences {
    static int solve(int[] numbers, int N, int K) {
        int maxSum = Integer.MIN_VALUE;

        // Find all possible combinations of K elements
        for (int mask = 0; mask < (1 << N); mask++) {
            if (Integer.bitCount(mask) == K) {
                int[] chosen = new int[K];
                int idx = 0;

                for (int i = 0; i < N; i++) {
                    if ((mask & (1 << i)) != 0) {
                        chosen[idx++] = numbers[i];
                    }
                }

                // Calculate the sum of absolute differences for the chosen sequence
                int sum = 0;
                for (int i = 1; i < K; i++) {
                    sum += Math.abs(chosen[i] - chosen[i - 1]);
                }

                // Update the maximum sum
                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] numbers = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int res = solve(numbers, N, K);
        System.out.println(res);

        br.close();
    }
}
