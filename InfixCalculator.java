import java.util.LinkedList;
import java.util.Queue;

public class InfixCalculator {
    
    // userinput
    private String userInput;
    // input validator
    private boolean isValid = true;
    // result
    private int inputResult;

    // constructor
    InfixCalculator(String userInput) {
        setUserInput(userInput);
        setResult(calculateInput(userInput));
    }
    // result setter
    private void setResult(int inputResult) {
        this.inputResult = inputResult;
    }
    // result getter
    public int getResult() {
        return inputResult;
    }
    // valid getter
    public boolean getValid() {
        return isValid;
    }
    // define the user input
    private void setUserInput(String userInput) {
        this.userInput = userInput;
    }
    // get ther user input
    public String getUserInput() {
        return userInput;
    }

    // perform calculation on the input
    private int calculation(LinkedList<String> numList, LinkedList<Character> opList) {
        // check if the size is valid
        int checkValid = numList.size() - opList.size();
        if(checkValid != 1) {
            setValidInput(false);
            return 0;
        }
        
        // the result will be initiallly 0
        int result = 0;
        int temp;

        for(int i = 0; i < numList.size(); i++) {

            // first input will be added into the result
            if(i == 0) {

                result += Integer.parseInt(numList.get(i));

            } else {

                // perform calculation
                temp = Integer.parseInt(numList.get(i));

                switch(opList.get(i - 1)) {
                    case '+': result += temp; break;
                    case '-': result -= temp; break;
                    case '*': result *= temp; break;
                    case '/': result /= temp; break;
                }

            }

        }

        return result;
    }

    // perfrom calculation on the input
    private int calculateInput(String userInput) {

        // variable declaration to perform calculation
        Queue<Character> queueNum = new LinkedList<Character>();
        StringBuilder sb = new StringBuilder();
        LinkedList<String> numList = new LinkedList<String>();
        LinkedList<Character> opList = new LinkedList<Character>();

        // for in bracket // under construction
        Queue<Character> queueInside = new LinkedList<Character>();
        StringBuilder insideSb = new StringBuilder();
        boolean flagOpenBracket = false;

        for(int i = 0; i < userInput.length(); i++) {

            char currentChar = userInput.charAt(i);

            // continue even if the user enters space
            if(currentChar == ' ') continue;

            // return if the input is invalid
            if(!Character.isDigit(currentChar) && (currentChar != '+' && currentChar != '-' && currentChar != '*' && currentChar != '/' && currentChar != '(' && currentChar != ')')) {
                System.out.println("Failed");
                setValidInput(false);
                return -1;
            }

            // check if the number is a digit
            if(Character.isDigit(currentChar)) {
                queueNum.add(currentChar);
            }

            // check if the current character is an operation
            if(currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/' || i == (userInput.length() - 1)) {
                
                if(i != (userInput.length() - 1)) {
                    // add operator into the list
                    opList.add(currentChar);
                }
    
                while(!queueNum.isEmpty()) {
                    sb.append(queueNum.poll());
                }
    
                numList.add(sb.toString());
    
                sb.setLength(0);
            }

        }
        
        return calculation(numList, opList);
    }

    // set valid input
    private void setValidInput(boolean valid) {
        this.isValid = valid;
    }

}
