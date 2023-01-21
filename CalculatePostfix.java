import java.util.*;

public class CalculatePostfix {
    
    // property initialization
    private String inputPostfix;
    private int result;
    private boolean validCalc = true;
    // calculation constructor
    CalculatePostfix(String inputPostfix) {
        inputPostfixSetter(inputPostfix);
        resultSetter();
    }
    // set the input postfix
    private void inputPostfixSetter(String inputPostfix) {
        this.inputPostfix = inputPostfix;
    }
    // set the validator
    private void validSetter(boolean setValid) {
        this.validCalc = setValid;
    }
    // set the result
    private void resultSetter() {
        this.result = calculation();
    }
    // get the result
    public String getResult() {

        if(getValid()) return Integer.toString(result);

        return "Invalid input";
            
    }
    // get the validator
    public boolean getValid() {
        return validCalc;
    }
    // get the input postfix
    public String getInputPostfix() {
        return inputPostfix;
    }
    // calculation method
    private int calculation() {
        // initialize the stack
        Stack<String> topStack = new Stack<String>();
        // initialize result
        int res;

        try {
            
            // split the postfix input into array of string
            String splits[] = inputPostfix.split(" ");

            // iterate until the end of string array
            for(int i = 0; i < splits.length; i++) {
                // take the current string
                String currentString = splits[i];
                // push the string into the top of stack
                topStack.push(currentString);
                // check if the input is not a digit
                if(!Character.isDigit(topStack.peek().charAt(0))) {
                    // take the operation
                    String op = topStack.pop();
                    // take the number stored from the stack
                    int num1 = Integer.parseInt(topStack.pop());
                    int num2 = Integer.parseInt(topStack.pop());
                    // determine the operation
                    switch(op) {
                        case "+": res = num2 + num1; break; // addition
                        case "-": res = num2 - num1; break; // substraction
                        case "*": res = num2 * num1; break; // multiplication
                        case "/": res = num2 / num1; break; // division
                        default: res = -1; //throw new java.lang.Error("Invalid input!"); // throw the exception if the input is invalid
                    }
                    // push the result into the top of stack
                    topStack.push(Integer.toString(res));
                }
            }
            // catch the exception
        } catch (Exception e) {
            validSetter(false); // set the valid to false
            // System.out.println(e); // print out the exception error
            return -1; // return -1 indicates an error has occured
        }
        // return the result from the top of stack
        return Integer.parseInt(topStack.pop());

    }

    
}
