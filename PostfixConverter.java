import java.util.*;

public class PostfixConverter {

    public void calculate(String s) {
        List<String> postFixString = getPostFixString(s);
        this.postFixStringN = postFixString;
    }
    
    private List<String> postFixStringN;

    private int getPreference(char c){
        if(c=='+'|| c=='-') return 1;
        else if(c=='*' || c=='/') return 2;
        else return -1;
    }

    private List<String> getPostFixString(String s){
        Stack<Character> stack = new Stack<>();
        List<String> postFixList = new ArrayList<>();
        boolean flag = false;
        for(int i=0;i<s.length();i++){
            char word = s.charAt(i);
            if(word==' '){
                continue;
            }
            if(word=='('){
                stack.push(word);
                flag = false;
            }else if(word==')'){
                flag = false;
                while(!stack.isEmpty()){
                    if(stack.peek()=='('){
                        stack.pop();
                        break;
                    }else{
                        postFixList.add(stack.pop()+"");
                    }
                }
            }else if(word=='+' || word=='-' || word=='*' || word=='/'){
                flag = false;
                if(stack.isEmpty()){
                    stack.push(word);
                }
                else{
                    while(!stack.isEmpty() && getPreference(stack.peek())>=getPreference(word)){
                        postFixList.add(stack.pop()+"");
                    }
                    stack.push(word);
                }
            }else{
                if(flag){
                    String lastNumber = postFixList.get(postFixList.size()-1);
                    lastNumber+=word;
                    postFixList.set(postFixList.size()-1, lastNumber);
                }else
                postFixList.add(word+"");
                flag = true;
            }
        }
        while(!stack.isEmpty()){
            postFixList.add(stack.pop()+"");
        }
        return postFixList;
    }

    public List<String> getStringPostfixList() {

        return postFixStringN;

    }

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
