public class Main {

    public static void main(String[] args) {

        String[] strArr = {"1+2", "a+b", "1+2*6", "(1+2)*6", "(1+2*6"};
        
        for(int i = 0; i < strArr.length; i++) {

            PostfixConverter converter = new PostfixConverter();

            converter.calculate(strArr[i]);

            CalculatePostfix calcPostfix = new CalculatePostfix(converter.getStringPostFix());

            System.out.println(calcPostfix.getResult());

        }

    }

}