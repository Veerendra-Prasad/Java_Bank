import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class Log {

    public double getBalance(String name) {
        HashMap<String, String> list = this.getData(name);
        if (list.containsValue(name)) {
            return Double.parseDouble(list.get("balance"));
        }
        return 0;
    }

    public boolean checkPassword(String name, int password) {
        HashMap<String, String> list = this.getData(name);
        if (list.containsValue(name)) {
            return password == decryptPassword(list.get("password"));
        } else {
            return false;
        }
    }

    public int writeToDataBase(String name, int password, double balance) {
        Gson gson = new Gson();

        String content = "";
        String filePath = "src/database.json";
        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (NoSuchFileException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("Error...");
        }

        DataWrapper dataWrapper = gson.fromJson(content, DataWrapper.class);

        List<Person> persons = dataWrapper.getData();

        Person newPerson = new Person();
        newPerson.setName(name);
        newPerson.setPassword(encryptPassword(password));
        newPerson.setBalance(balance);

        boolean personExists = false;
        for (Person person : persons) {
            if (person.getName().equals(newPerson.getName())) {
                person.setBalance(balance);
                personExists = true;
                break;
            }
        }

        if (!personExists) {
            persons.add(newPerson);
        }

        dataWrapper.setData(persons);

        String updatedJson = gson.toJson(dataWrapper);

        // Write the updated JSON string to the file
        try {
            Files.write(Paths.get(filePath), updatedJson.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            return 200;
        } catch (IOException e) {
            System.out.println("Error writing to file...");
        }
        return 404;
    }

    public boolean getName(String name) {
        HashMap<String, String> list = this.getData(name);
        return list.containsValue(name);
    }

    public int writeQuery(String name, String query, double balance) {
        // construct the parameters into a string and include time and date in the first row and write them to the database
        // there should be at least 4 spaces between them HINT: use '\t' between the strings
        // if success then return 200 else 404
        LocalDateTime dateTime = LocalDateTime.now();
        String filepath = "logs.txt";

        try {
            String content = "\n" + String.valueOf(dateTime) + "\t" + name + "\t" + query + "\t\t" + String.valueOf(balance);
            Files.write(Paths.get(filepath), content.getBytes(), StandardOpenOption.APPEND);
            return 200;
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("Error...");
        }
        return 404;
    }

    public String encryptPassword(int password) {
        int buffer;
        String encryptedPassword = "";
        while (password != 0) {
            buffer = password % 10;
            buffer += 97;
            char ch = (char) buffer;
            StringBuilder sb = new StringBuilder(encryptedPassword);
            sb.append(ch);
            encryptedPassword = sb.toString();
            password = password / 10;
        }
        encryptedPassword = new StringBuilder(encryptedPassword).reverse().toString();
        return encryptedPassword;
    }

    public int decryptPassword(String password) {
        int buffer;
        char ch;
        String decryptedPassword = "";
        while (!password.isEmpty()) {
            ch = password.charAt(password.length() - 1);
            buffer = (int) ch - 97;
            StringBuilder sb = new StringBuilder(decryptedPassword);
            sb.append(String.valueOf(buffer));
            decryptedPassword = sb.toString();
            password = password.substring(0, password.length() - 1);
        }
        decryptedPassword = new StringBuilder(decryptedPassword).reverse().toString();
        return Integer.parseInt(decryptedPassword);
    }

    public HashMap<String, String> getData(String name) {
        Gson gson = new Gson();

        // Read JSON file content into a string
        String content = "";
        String filePath = "src/database.json";
        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (NoSuchFileException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("Error...");
        }

        // Parse the JSON content into a DataWrapper object
        DataWrapper dataWrapper = gson.fromJson(content, DataWrapper.class);

        // Get the list of Person objects
        List<Person> personList = dataWrapper.getData();

        // Iterate through the list and print the values
        for (Person person : personList) {
            if (name.equals(person.getName())) {
                HashMap<String, String> list = new HashMap<>();
                list.put("name", person.getName());
                list.put("password", person.getPassword());
                list.put("balance", String.valueOf(person.getBalance()));
                return list;
            }
        }
        return new HashMap<>();
    }
}
