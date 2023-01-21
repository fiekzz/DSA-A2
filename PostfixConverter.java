import java.util.*;

public class PostfixConverter {

    // declaring List of String
    private List<String> postFixStringN;

    // calculate the converter
    public void calculate(String s) {
        List<String> postFixString = getPostFixString(s);
        this.postFixStringN = postFixString;
    }
    
    // get the operator rank
    private int calculateOperatorRank(char c){
        if(c=='+'|| c=='-') return 1;
        else if(c=='*' || c=='/') return 2;
        else return -1;
    }

    // convert the infix to postfix
    private List<String> getPostFixString(String s){

        // declaring stack
        Stack<Character> stack = new Stack<Character>();
        // declaring postfix list of String
        List<String> postFixList = new ArrayList<String>();
        // declaring the flag
        boolean flag = false;

        // iterate each character of the string
        for(int i = 0; i < s.length(); i++){

            // get the current character
            char currentChar = s.charAt(i);

            // skip if a space is found
            if(currentChar == ' '){
                continue;
            }
            if(currentChar == '('){
                // push the char into the stack
                stack.push(currentChar);
                flag = false;
            }else if(currentChar == ')'){
                flag = false;
                // pop every char inside the bracket
                while(!stack.isEmpty()){
                    if(stack.peek() == '('){
                        stack.pop();
                        break;
                    }else{
                        postFixList.add(stack.pop() + "");
                    }
                }
            }else if(currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/'){
                flag = false;
                if(stack.isEmpty()){
                    stack.push(currentChar);
                }
                else{
                    // check if the operation rank is higher
                    while(!stack.isEmpty() && calculateOperatorRank(stack.peek())>=calculateOperatorRank(currentChar)){
                        postFixList.add(stack.pop()+"");
                    }
                    // push into the stack
                    stack.push(currentChar);
                }
            }else{
                if(flag){
                    String lastNumber = postFixList.get(postFixList.size()-1);
                    lastNumber+=currentChar;
                    postFixList.set(postFixList.size()-1, lastNumber);
                }else
                postFixList.add(currentChar + "");
                flag = true;
            }
        }
        // pop all the stack into the postfix list
        while(!stack.isEmpty()){
            postFixList.add(stack.pop()+"");
        }

        return postFixList;
    }

    // get the string postfix list
    public List<String> getStringPostfixList() {

        return postFixStringN;

    }
    // get the string postfix list in the string format
    public String getStringPostFix() {

        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < postFixStringN.size(); i++) {

            builder.append(postFixStringN.get(i));
            if (i != postFixStringN.size() - 1) {
                builder.append(' ');
            }

        }

        return builder.toString();

    }
}
