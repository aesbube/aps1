package FirstMidtermExercises.ex5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ExpressionEvaluator {

    public static int evaluateExpression(String expression) {
        String[] addition = expression.split("\\+");
        int sum = 0;
        for (String s :
                addition) {
            String[] mult = s.split("\\*");
            int res = 1;
            for (String s1 :
                    mult) {
                res *= Integer.parseInt(s1);
            }
            sum += res;
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }

}