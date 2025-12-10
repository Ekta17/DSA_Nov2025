package dsa.topten;

/*

Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
1 <= s.length <= 3 * 105
s consists of digits, '+', '-', '(', ')', and ' '.
s represents a valid expression.
 */

import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class BasicCalculator {

    /*
     First I am thinking if I should use two different stacks one for operators and another for operands
     but thats difficult to operate on as not sure which operator to apply on which operands

     Second approach -> take one stack
     keep pushing to stack till a closing bracket is encontered
     if closing bracket -> pop till the opening bracket and push the popped elements to another temp stack with operators
     once opening bracket reached -> pop 3 (two operands and one symbol) apply and push the result to this temp stack
     push the final result to main stack and continue till the end of string

     Third approach -> take one stack but keep solving along
     get the first char: if ( push to stack , if digit push to stack , if symbol push to stack
     make a count of 3-> operand 1 count - 1, symbol count 2 , operand2 count 3 -> pop three times , calculate the result and push to stack
     example: (1+(4+5+2)-3)+(6+8)
     stack = []
     count = 0
     stack [(,1,+,(,4,+,5]
     [(,1,+,(,9,+,2]
     [(,1,+,(,11]
     [(,1,+,11]
     [(,12]
     [(,12,-,3]
     [(,9]
     [(,9,+,(,6,+,8]
     [(,9,+,(,14)]
     [23]

     but I am confused on how to solve for like the technique I was thinking of keeping a count. like when to pop elements to calculate temp result to push stack

     */
    public static int calculate(String s) {

        //After checking the algorithm:
        /*
         1. number operator number -> evaluate and push to stack the result
         2. digit -> parse till not a digit and covert to number and push to stack -> check number operator number
         3. '(' or '+' or '-' -> push to stack
         4. ')' -> pop three in order -> num2, operator , num1 -> then compute the result and push to stack and then check number operator number
         5.  end once the whole expression parsed

         To check the pattern for stack as -> number operator number
         we can use get() on stack
         -> stack.get(stack.size() - 1) = top of the stack
         -> stack.get(stack.size() - 2) = top of the stack - 1
         -> stack.get(stack.size() - 3) = top of the stack - 2
         */

        Stack<String> stack = new Stack<>();
        int i = 0;
        while(i < s.length()){

            if(s.charAt(i) == ' '){
                System.out.println("ignoring space");
                i++;
            } else if(s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '(') {
                stack.push(String.valueOf(s.charAt(i)));
                i++;
                System.out.println("Stack after processing a symbol:: "+stack);

            } else if(s.charAt(i) == ')'){
                System.out.println("found a closing bracket");
                popUtilOpeningBracket(stack);
                System.out.println("stack after evaluating inside brackets:"+stack);
                tryEvaluation(stack);
                System.out.println("stack after evaluating inside brackets and number operator number:"+stack);
                i++;
            } else { //digit

                System.out.println("found a digit");
                //System.out.println("i = "+ i);
                int k = i;
                StringBuilder sb = new StringBuilder();
                while(k < s.length()  && s.charAt(k) != ' ' && s.charAt(k) != '+' && s.charAt(k) != '-' && s.charAt(k) != '(' && s.charAt(k) != ')'){
                    sb.append(s.charAt(k));
                    k++;
                }
                //System.out.println("k = "+k);
                System.out.println("string builder: "+ sb.toString());
                stack.push(sb.toString());
                i = k;
                //System.out.println("i = "+ i);
                tryEvaluation(stack);
                System.out.println("stack after adding a digit and evaluating number operator number:"+ stack);
            }
        }

        System.out.println("stack outside before returning"+stack);

        return Integer.valueOf(stack.pop());
    }

    private static void  popUtilOpeningBracket(Stack<String> stack){
        System.out.println("inside popUtilOpeningBracket: "+stack);
        while(stack.size() > 2 && !stack.get(stack.size() - 1).equals("(")){
            int num2 = Integer.valueOf(stack.pop());
            String operator = stack.pop();
            int num1 = Integer.valueOf(stack.pop());
            int result = operator.equals("+") ? num1 + num2 : num1 - num2;
            stack.push(String.valueOf(result));
        }

        if(stack.get(stack.size() - 1).equals("("))
            stack.pop();
        else if (stack.size() < 2)
            return;
        else if (stack.size() <= 2 && stack.get(stack.size() - 2).equals("(")){
            int val = Integer.valueOf(stack.pop());
            stack.pop();
            stack.push(String.valueOf(val));
        }
    }

    private static void tryEvaluation(Stack<String> stack){
        if(stack.size()>2) {
            String snum2 = stack.get(stack.size() - 1);
            String symbol = stack.get(stack.size() - 2);
            String snum1 = stack.get(stack.size() - 3);

            System.out.println("snum1=["+snum1+"], symbol=["+symbol+"], snum2=["+snum2+"]");

            if (isNumber(snum2) && isNumber(snum1) && isOperator(symbol)) {
                int num2 = Integer.valueOf(stack.pop());
                String operator = stack.pop();
                int num1 = Integer.valueOf(stack.pop());

                int result = operator.equals("+") ? num1 + num2 : num1 - num2;
                System.out.println("stack before adding the result:"+ stack);
                if (stack.size() > 1 && stack.get(stack.size() - 1).equals("("))
                    stack.pop();
                System.out.println("stack before adding the result and hopefully popping out opening bracket:"+ stack);
                stack.push(String.valueOf(result));
                System.out.println("stack after adding result:"+stack);
            }

        }

    }

    private static boolean isNumber(String s){
        try{
            Integer.parseInt(s);
        }catch(NumberFormatException e){
            return false;
        }
        return true;
    }

    private static boolean isOperator(String s){
        return s.equals("+") || s.equals("-");
    }

    public static void main(String[] args) {
        assertEquals(2, calculate("1+1"));
        assertEquals(3, calculate(" 2-1 + 2 "));
        assertEquals(23, calculate("(1+(4+5+2)-3)+(6+8)"));
    }


}
