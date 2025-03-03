# Java Bank Program

## Overview
This Java Bank Program is designed to manage bank accounts with features such as logging, password encryption, and JSON file-based database storage.

## Features
- **Logging**: Logs all activities and transactions.
- **Password Encryption**: Encrypts user passwords for secure storage.
- **JSON Database**: Uses a JSON file to store user data and transaction history.

## Requirements
- Java Development Kit (JDK) 8 or higher
- Gson library for JSON parsing

## Installation
1. **Clone the repository**:
    ```bash
    git clone https://github.com/yourusername/javabankprogram.git
    cd javabankprogram
    ```

2. **Install the Gson library**:
    If you are using Maven, add the following dependency to your `pom.xml`:
    ```xml
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.8.6</version>
    </dependency>
    ```

## Usage
1. **Compile the program**:
    ```bash
    javac -cp .;gson-2.8.6.jar Main.java
    ```

2. **Run the program**:
    ```bash
    java -cp .;gson-2.8.6.jar Main
    ```

## Configuration
- **Database File**: The JSON file used as the database is located at `src/database.json`.

## Example
Here's an example of how to use the program:
1. **Create a new account**:
    - Enter your name, password, and initial balance.
2. **Login to your account**:
    - Enter your name and password.
3. **Perform transactions**:
    - Deposit, withdraw, or check your balance.

## Contributing
Feel free to fork this repository and contribute by submitting a pull request. Please ensure your changes are well-documented and tested.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact
For any inquiries, please contact `veerendraprasadkarnam27@gmail.com`.

## Acknowledgments
- The Gson library for JSON parsing
- Creating a custom class for logging the transcations
