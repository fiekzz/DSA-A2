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

        if(!isValid) {
            return -1;
        }
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
                // operations based on the user input
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

        // in bracket variable declaration
        Queue<Character> queueInside = new LinkedList<Character>();
        StringBuilder insideSb = new StringBuilder();
        boolean flagOpenBracket = false;
        int bracketCounter = 0;

        for(int i = 0; i < userInput.length(); i++) {

            char currentChar = userInput.charAt(i);

            // continue even if the user enters space
            if(currentChar == ' ') continue;

            // return if the input is invalid
            if(!Character.isDigit(currentChar) && (currentChar != '+' && currentChar != '-' && currentChar != '*' && currentChar != '/' && currentChar != '(' && currentChar != ')')) {
                setValidInput(false);
                return -1;
            }

            // check if the open bracket flag is activated
            if(!flagOpenBracket) {

                // detect if current input is an open bracket
                if(currentChar == '(') {
    
                    flagOpenBracket = true;
                    bracketCounter++;
                    continue;
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
                    // append all characters into the string builder
                    while(!queueNum.isEmpty()) {
                        sb.append(queueNum.poll());
                    }
                    // add the string into the numlist
                    numList.add(sb.toString());
                    // reset the string builder
                    sb.setLength(0);
                }

            } else {

                // count the open bracket
                // increase in open bracket cause more recursion
                if(currentChar == '(') {
                    bracketCounter++;
                } else if(currentChar == ')') {
                    bracketCounter--;
                }
                // exclude the last close bracket
                if(bracketCounter != 0) {
                    queueInside.add(currentChar);
                }
                // check if the bracket counter is last then proceed to calculation
                if(bracketCounter == 0) {
                    // append all inside bracker characters into the string builder
                    while(!queueInside.isEmpty()) {
                        insideSb.append(queueInside.poll());
                    }
                    // get the result recursively
                    String res = Integer.toString(calculateInput(insideSb.toString()));
                    // add the result into the numlist
                    numList.add(res);
                    // flag false the open bracket to perform calculation
                    flagOpenBracket = false;

                }
                // check if the input is the last but still bracketcount is not null
                if(i == userInput.length() && bracketCounter != 0) {
                    setValidInput(false);
                    return -1;
                }
            }
        }
        return calculation(numList, opList);
    }

    // set valid input
    private void setValidInput(boolean valid) {
        this.isValid = valid;
    }

}
