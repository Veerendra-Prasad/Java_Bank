import java.io.*;
import java.time.LocalTime;

public class Log {
    private final String password;
    private final String filename = "logs.txt";
    Log(LocalTime time, String acctName, String operation, Boolean taken, double takenBalance, double currentBalance, int password) throws IOException {
        this.password = this.encryptPassword(password);
        try(BufferedWriter wr = new BufferedWriter(new FileWriter(filename))){
        } catch (FileNotFoundException e){
            System.out.println("File not Found");
        }
        catch (IOException e){
            System.out.println("The inputs are not vaild");
        }
    }

    public String encryptPassword(int password) {
        int number = 96;
        int buffer = 0;
        String encryptedPassword = "";
        while (password != 0) {
            buffer = password % 10;
            buffer += number;
            char ch = (char) buffer;
            StringBuilder sb  = new StringBuilder(encryptedPassword);
            sb.append(ch);
            encryptedPassword = sb.toString();
            password = password / 10;
        }
        encryptedPassword = new StringBuilder(encryptedPassword).reverse().toString();
        return encryptedPassword;
    }
}
