import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank;
        double amount;
        String name;
        int password;
        double balance;
        int option;

        System.out.print("Enter your name for account name : ");
        name = scanner.nextLine();
        System.out.print("\nEnter initial amount for the account : ");
        balance = scanner.nextDouble();
        scanner.nextLine();
        do {
            System.out.print("Enter a Password for the Account: ");
            password = scanner.nextInt();
            if (checkPassword(password)) {
                System.out.println("Password is set");
            } else {
                System.out.println("The password must be longer than or equal to 8 characters");
            }
        } while (!checkPassword(password));
        bank = new Bank(name, balance, password);

        do {
            System.out.println("*********************");
            System.out.println("Welcome to Java Bank");
            System.out.println("*********************");
            System.out.println("Enter a option: ");
            System.out.println("*********************");
            System.out.println("1. getBalance");
            System.out.println("2. Deposit amount");
            System.out.println("3. withdraw amount ");
            System.out.println("4. Exit from interface ");
            System.out.println("*********************");
            System.out.print("Choose an option : ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> bank.getBalance();
                case 2 -> {
                    System.out.print("\nEnter the amount to deposit: ");
                    amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("To perform the operation enter your password: ");
                    try {
                        int userPassword = scanner.nextInt();
                        bank.addAmount(amount, userPassword);
                    } catch (InputMismatchException e) {
                        System.out.println("Not a Valid Password");
                    }
                }
                case 3 -> {
                    System.out.print("\nEnter the amount to withdraw: ");
                    amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("To perform the operation enter your password: ");
                    try {
                        int userPassword = scanner.nextInt();
                        bank.withdrawAmount(amount, userPassword);
                    } catch (InputMismatchException e) {
                        System.out.println("Not a Valid Password");
                    }
                }
                case 4 -> System.out.println("Thank you for visiting our bank");
                default -> System.out.println("Invalid option");
            }
        } while (option != 4);
        scanner.close();
    }

    public static Boolean checkPassword(int password) {
        return String.valueOf(password).length() >= 4;
    }

}