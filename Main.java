public class Main {

    public static void main(String[] args) {

        // Array of question from the template
        String[] strArr = {"1+2", "a+b", "1+2*6", "(1+2)*6", "(1+2*6"};
        
        // declaring converter variable
        PostfixConverter converter = new PostfixConverter();

        // iterate each question
        for(int i = 0; i < strArr.length; i++) {

            // convert the question into postfix form
            converter.calculate(strArr[i]);

            // calculate the postfix conversion
            CalculatePostfix calcPostfix = new CalculatePostfix(converter.getStringPostFix());

            // display the result
            System.out.println(calcPostfix.getResult());

        }

    }

}