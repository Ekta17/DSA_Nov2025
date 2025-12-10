package dsa.topten;

import java.util.Stack;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/*
 Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.
 */
public class ValidParentheses {
    public static boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
        int i = 0;
        char top;
        while(i < s.length()){
            char c = s.charAt(i);
            switch(c){
                case '(':
                case '[':
                case '{':
                    stack.push(c);
                    break;
                case ')':
                    if(stack.isEmpty())
                        return false;
                    top = stack.pop();
                    if(top != '(')
                        return false;
                    break;
                case ']':
                    if(stack.isEmpty())
                        return false;
                    top = stack.pop();
                    if(top != '[')
                        return false;
                    break;
                case '}':
                    if(stack.isEmpty())
                        return false;
                    top = stack.pop();
                    if(top != '{')
                        return false;
                    break;

            }
            i++;
        }

        if (stack.isEmpty() && i == s.length())
            return true;
        return false;
    }

    public static void main(String[] args) {
        assertTrue(isValid("()"));
        assertTrue(isValid("()[]{}"));
        assertFalse(isValid("(]"));
        assertTrue(isValid("([])"));
        assertFalse(isValid("([)]"));
        assertFalse(isValid("}"));
        assertFalse(isValid("["));
    }
}
