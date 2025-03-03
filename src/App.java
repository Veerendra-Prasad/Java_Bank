import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Variables
        Bank bank;
        Log log = new Log();
        String name;
        int password;
        int flag = 0;

        System.out.println("Welcome to Java Bank");
        System.out.println("#####################");
        System.out.print("\nEnter your name for account name : ");
        name = scanner.nextLine();

        if (log.getName(name)) {
            // check if the username has the same password
            System.out.println("Welcome Back " + name);
            do {
                System.out.print("Enter your 4-digit password: ");
                password = scanner.nextInt();
            } while (!log.checkPassword(name, password));
            // returns true or false based on password
        } else {
            System.out.println("Creating a new Account");
            // Need to make sure the password is 4-digit long before accepting
            do {
                System.out.print("\nEnter your 4-digit password: ");
                password = scanner.nextInt();
                if (String.valueOf(password).length() != 4) {
                    System.out.println("Invalid Password");
                }
            } while (String.valueOf(password).length() != 4);
            int status = log.writeToDataBase(name, password, 0);
            // if written to the file correctly returns 200 or else 404
            if (status != 200) {
                System.out.println("Failed to save Account Details");
                System.out.println("Please restart the program");
                flag += 1;
            } else {
                System.out.println("Acct Created Successfully");
            }
        }

        if (flag == 0) {
            bank = new Bank(name, password, log.getBalance(name));
            bank.loop();
        }

        scanner.close();
    }

}