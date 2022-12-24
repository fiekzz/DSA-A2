import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter an arithmetic expression: ");
        String userInput = input.nextLine();

        // String userInput = "3+3*(1+1-3-(2+2))"; // testing purposes
        InfixCalculator test1 = new InfixCalculator(userInput);

        // check if the input is valid
        if(test1.getValid()) {
            System.out.println("Output of your expression: " + test1.getResult());
        } else {
            System.out.println("Invalid input");
        }



        input.close();

    }

}