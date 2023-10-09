import java.util.Stack;

class Calculator {
    public static void main(String[] args) {
        String exp = "6*(4/2)+3*1";
        System.out.println(MathChallenge(exp));
    }

    // Method that takes the str parameter and evaluate the mathematical expression within it
    static int MathChallenge(String exp) {

        // Create a stack for operands
        Stack<Integer> operandStack = new Stack<>();

        // Create a stack for operators
        Stack<Character> operatorStack = new Stack<>();

        // Define the precedence of operators
        int[] precedence = new int[256];
        precedence['+'] = 1;
        precedence['-'] = 1;
        precedence['*'] = 2;
        precedence['/'] = 2;

        // Scan all characters one by one
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (c == ' ')
                continue;

            // If the scanned character is a digit, extract the number and push it to the operand stack
            if (Character.isDigit(c)) {
                int num = 0;

                while (i < exp.length() && Character.isDigit(exp.charAt(i))) {
                    num = num * 10 + (int) (exp.charAt(i) - '0');
                    i++;
                }
                i--;

                operandStack.push(num);
            }

            // If the scanned character is an operator
            else if (c == '+' || c == '-' || c == '*' || c == '/') {
                // Pop operators from the operator stack and apply them to operands
                // according to precedence until the operator stack is empty or
                // an operator with lower precedence is encountered
                while (!operatorStack.isEmpty() && precedence[c] <= precedence[operatorStack.peek()]) {
                    applyOperator(operandStack, operatorStack);
                }

                // Push the current operator to the operator stack
                operatorStack.push(c);
            } else if (c == '(') {
                // Push an open parenthesis onto the operator stack
                operatorStack.push(c);
            } else if (c == ')') {
                // Pop operators from the operator stack and apply them to operands
                // until an open parenthesis is encountered
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    applyOperator(operandStack, operatorStack);
                }

                // Pop the open parenthesis from the operator stack
                operatorStack.pop();
            }
        }

        // Apply any remaining operators on the stack to the operands
        while (!operatorStack.isEmpty()) {
            applyOperator(operandStack, operatorStack);
        }

        // The final result will be at the top of the operand stack
        return operandStack.pop();
    }

    private static void applyOperator(Stack<Integer> operandStack, Stack<Character> operatorStack) {
        char operator = operatorStack.pop();
        int operand2 = operandStack.pop();
        int operand1 = operandStack.pop();

        switch (operator) {
            case '+' -> operandStack.push(operand1 + operand2);
            case '-' -> operandStack.push(operand1 - operand2);
            case '*' -> operandStack.push(operand1 * operand2);
            case '/' -> operandStack.push(operand1 / operand2);
        }
    }
}
