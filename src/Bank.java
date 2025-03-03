import java.util.Scanner;

public class Bank {
    private double balance = 0;
    private final String name;
    private final int password;
    Log log = new Log();

    Bank(String name, int password, double balance) {
        this.password = password;
        this.name = name;
        this.balance = balance;
    }

    public void loop() {
        Scanner scanner = new Scanner(System.in);
        int option;
        double amount;
        do {
            System.out.println("*********************");
            System.out.println("Welcome to Java Bank");
            System.out.println("*********************");
            System.out.println("Enter a option: ");
            System.out.println("*********************");
            System.out.println("1. getBalance");
            System.out.println("2. Deposit amount");
            System.out.println("3. withdraw amount");
            System.out.println("4. Exit from interface");
            System.out.println("*********************");
            System.out.print("Choose an option : ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> {
                    System.out.println("Your Current Balance is : " + log.getBalance(this.name));
                }
                case 2 -> {
                    System.out.print("\nEnter the amount to deposit: ");
                    amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("To perform the operation enter your password: ");
                    int userEnteredPassword = scanner.nextInt();
                    if (userEnteredPassword == this.password) {
                        this.addAmount(amount);
                    } else {
                        System.out.println("Wrong Password");
                    }
                }
                case 3 -> {
                    System.out.print("\nEnter the amount to withdraw: ");
                    amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("To perform the operation enter your password: ");
                    int userEnteredPassword = scanner.nextInt();
                    if (userEnteredPassword == this.password) {
                        this.withdrawAmount(amount);
                    } else {
                        System.out.println("Wrong Password");
                    }

                }
                case 4 -> {
                    System.out.println("Saving to database");
                    if (log.writeToDataBase(this.name, this.password, this.balance) == 200) {
                        System.out.println("Updated the database");
                    }
                    System.out.println("Thank you for visiting our bank");
                }
                default -> System.out.println("Invalid option");
            }
        } while (option != 4);
        scanner.close();
    }

    public void addAmount(double amount) {
        if (amount <= 0) {
            System.out.println("Enter a number greater than 0");
            System.out.println("Failed to add amount");
        } else {
            this.balance += amount;
            String message = amount + " has been deposited to " + this.name + " account";
            log.writeToDataBase(this.name, this.password, this.balance);
            if (log.writeQuery(this.name, message, this.balance) == 200) {
                System.out.println("Entering into the logs");
                System.out.println(message);
            } else {
                System.out.println("Couldn't enter the transaction into the logs");
            }
        }
    }

    public void withdrawAmount(double amount) {
        if (balance < amount) {
            System.out.println("Insufficient Balance");
        } else {
            balance -= amount;
            String message = amount + " has been withdrawn from " + this.name + " account";
            log.writeToDataBase(this.name, this.password, this.balance);
            if (log.writeQuery(this.name, message, this.balance) == 200) {
                System.out.println("Entering into the logs");
                System.out.println(message);
            } else {
                System.out.println("Couldn't enter the transaction into the logs");
            }
        }
    }
}
