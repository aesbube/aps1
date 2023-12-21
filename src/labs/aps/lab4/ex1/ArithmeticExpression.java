package labs.aps.lab4.ex1;

import java.util.Stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ArithmeticExpression {
    public static int presmetaj(char c[], int l, int r) {
        Stack<Integer> values = new Stack<>();

        Stack<Character> operators = new Stack<>();

        for (int i = l; i <= r; i++) {
            if (c[i] == ' ')
                continue;

            if (Character.isDigit(c[i])) {
                StringBuilder sb = new StringBuilder();
                while (i <= r && Character.isDigit(c[i])) {
                    sb.append(c[i++]);
                }
                i--;
                values.push(Integer.parseInt(sb.toString()));
            } else if (c[i] == '(') {
                operators.push(c[i]);
            } else if (c[i] == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                if (!operators.isEmpty()) {
                    operators.pop();
                }
            } else if (c[i] == '+' || c[i] == '-') {
                while (!operators.isEmpty() && hasPrecedence(c[i], operators.peek())) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(c[i]);
            }
        }

        while (!operators.isEmpty()) {
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
        }

        if (values.isEmpty()) return 0;
        return values.pop();
    }

    private static boolean hasPrecedence(char op1, char op2) {
        return (op2 == '+' || op2 == '-') && (op1 == '+' || op1 == '-');
    }

    private static int applyOperator(char operator, int b, int a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length - 1);
        System.out.println(rez);

        br.close();
    }
}
