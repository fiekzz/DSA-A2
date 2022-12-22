import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter an arithmetic expression: ");
        String userInput = input.nextLine();
        InfixCalculator test1 = new InfixCalculator(userInput);
        System.out.println(test1.getResult());


        input.close();

    }

}