import java.util.Scanner;

public class Bank {
    private double balance = 0;
    private String name;
    private final int password;

    Bank(String name, double amount,int password) {
        this.name = name;
        this.balance = amount;
        this.password = password;
        System.out.println("Account created Successfully");
    }

    public void getBalance() {
        System.out.println("Balance in " + this.name + " account is: " + this.balance);
    }

    public void addAmount(double amount, int password) {
        if (checkPassword(password)) {
            if (amount <= 0) {
                System.out.println("Enter a number greater than 0");
                System.out.println("Failed to add amount");
                return;
            } else {
                this.balance += amount;
                System.out.println("Successfully added the balance");
                this.getBalance();
            }
        }
        else{
            System.out.println("Password does not match");
        }
    }

    public void withdrawAmount(double amount, int password) {
        if (checkPassword(password)) {
            if (balance < amount) {
                System.out.println("Insufficient Balance");
                return;
            } else {
                balance -= amount;
                System.out.println(amount + " has been withdrawn from " + this.name + " account");
                getBalance();
            }
        } else {
            System.out.println("Password does not match");
        }
    }

    private Boolean checkPassword(int password) {
        if (this.password == 0) {
            return false;
        }
        return this.password == password;
    }
}
