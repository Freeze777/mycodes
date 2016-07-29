package algo.stack;

import java.util.Stack;

public class EvaluatePostfixExpression {
	public int evalRPN(String[] tokens) {
		if (tokens.length == 0)
			return 0;
		Stack<Integer> operand = new Stack<Integer>();
		for (int i = 0; i < tokens.length; i++) {
			//operator
			if (tokens[i].length() == 1 && !Character.isDigit(tokens[i].charAt(0))) {
				int b = operand.pop();
				int a = operand.pop();
				int res = 0;
				switch (tokens[i].charAt(0)) {
				case '+':
					res = a + b;
					break;
				case '-':
					res = a - b;
					break;
				case '*':
					res = a * b;
					break;
				case '/':
					res = a / b;
					break;
				}
				operand.push(res);
			} else {//operand
				operand.push(Integer.parseInt(tokens[i]));
			}
		}
		return operand.peek();
	}
}
