import java.util.Stack;

public class CalculatePostfix {
    
    private Stack<String> postfixStack = new Stack<String>();

    private String inputPostfix;
    private int result;
    private boolean validCalc = true;

    CalculatePostfix(String inputPostfix) {
        inputPostfixSetter(inputPostfix);
        resultSetter();
    }

    private void inputPostfixSetter(String inputPostfix) {
        this.inputPostfix = inputPostfix;
    }

    private void validSetter(boolean setValid) {
        this.validCalc = setValid;
    }

    private void resultSetter() {
        this.result = calculate();
    }

    public int getResult() {
        return result;
    }

    public boolean getValid() {
        return validCalc;
    }

    public String getInputPostfix() {
        return inputPostfix;
    }

    private int calculate() {

        int res = 0;

        Stack<String> topStack = new Stack<String>();

        StringBuilder builder = new StringBuilder();

        try {
            for(int i = 0; i < inputPostfix.length(); i++) {

                char ch = inputPostfix.charAt(i);

                builder.append(ch);

                if(ch == ' ' && Character.isDigit(builder.toString().charAt(0))) {

                    builder.setLength(builder.length() - 1);
                    topStack.push(builder.toString());
                    builder.setLength(0);

                }

                if(ch == '+' || ch == '-' || ch == '*' || ch == '/') {

                    double num1 = (double) Integer.parseInt(String.valueOf(topStack.pop()));
                    double num2 = (double) Integer.parseInt(String.valueOf(topStack.pop()));

                    switch(ch) {
                        case '+': res = (int)(num2 + num1); break;
                        case '-': res = (int)(num2 - num1); break;
                        case '*': res = (int)(num2 * num1); break;
                        case '/': res = (int)(num2 / num1); break;
                    }

                    topStack.push(Integer.toString(res));

                    builder.setLength(0);
                    
                }

            }
        } catch (Exception e) {
            validSetter(false);
            return -1;
        }

        return Integer.parseInt(topStack.pop());

    }

    public void displayPostfixStack() {
        System.out.println(postfixStack);
    }

}
