import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String userInput = "1+2*6"; // testing purposes
        
        Solution inputSolution = new Solution();

        inputSolution.calculate(userInput);

        String res = inputSolution.getStringPostFix();

        CalculatePostfix calc = new CalculatePostfix(res);

        System.out.println(calc.getValid() ? calc.getResult() : "Invalid input");


        input.close();

    }

}