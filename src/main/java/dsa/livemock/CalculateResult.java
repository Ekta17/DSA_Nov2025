package dsa.livemock;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

//implement a calculate() method with input as String expression like ("1 - 2 + 3") -> here output is 2
//Only symbols are + - digits space
public class CalculateResult {

    //parse string and add to stack
    //Apply operation
    //return result
    private static CalculateResult calculateResult;

    public static void main(String[] args) {
        calculateResult = new CalculateResult();
        assertEquals(2, calculateResult.calculate("1 - 2 + 3"));
        assertEquals(3, calculateResult.calculate(" 2-1 + 2 "));
        assertEquals(2, calculateResult.calculate("1 + 1"));
    }

    public int calculate(String expression){
        Stack<String> stack = new Stack<>();
        calculateResult.parseExpression(expression, stack);

        int result = 0;
        while(!stack.isEmpty() && stack.size() > 1){
            int operand1 = Integer.valueOf(stack.pop());
            String symbol = stack.pop();
            int operand2 = Integer.valueOf(stack.pop());

            switch(symbol){
                case "+": result = calculateResult.add(operand1, operand2); break;
                case "-": result = calculateResult.subtract(operand1, operand2); break;
                default:
                    System.out.println("Invalid symbol encountered"); break; //throw new Exception("Invalid symbol encountered"); break;
            }
            stack.push(String.valueOf(result));
        }
        return result;
    }

    private int add(int operand1, int operand2){
        return operand1 + operand2;
    }

    private int subtract(int operand1, int operand2){
        return operand1 - operand2;
    }

    private void parseExpression(String expression , Stack<String> stack){
        int i = expression.length() - 1;
        while(i >= 0){
            int k = i;
            StringBuilder sb = new StringBuilder();
            while( k >= 0 && expression.charAt(k) != '+' && expression.charAt(k) != '-'){
                if(expression.charAt(k) == ' '){
                    k--;
                    continue;
                }
                sb.append(expression.charAt(k));
                k--;
                if(k < 0)
                    break;
            }
            stack.push(sb.toString());
            if(k > 0) {
                stack.push(String.valueOf(expression.charAt(k)));
                k--;
            }
            i = k;
        }

        //System.out.println("After parsing stack is "+ stack);
    }
}
