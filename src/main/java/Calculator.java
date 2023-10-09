import java.util.Stack;

class Calculator {

    // Method to evaluate value of a postfix expression
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

                // Push the current operator to the operator stack
                operatorStack.push(c);
            } else if (c == '(') {
                // Push an open parenthesis onto the operator stack
                operatorStack.push(c);
            } else if (c == ')') {
                // Pop operators from the operator stack and apply them to operands
                // until an open parenthesis is encountered
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
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

                // Pop the open parenthesis from the operator stack
                operatorStack.pop();
            }
        }

        // Apply any remaining operators on the stack to the operands
        while (!operatorStack.isEmpty()) {
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

        // The final result will be at the top of the operand stack
        return operandStack.pop();
    }

    // Driver program to test above functions
    public static void main(String[] args) {
        String exp = "6/3-1";

        // Function call
        System.out.println(MathChallenge(exp));
    }
}
