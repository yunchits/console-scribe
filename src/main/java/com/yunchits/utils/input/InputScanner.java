package com.yunchits.utils.input;

import com.yunchits.exceptions.InvalidIntegerException;
import com.yunchits.exceptions.InvalidWordException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class InputScanner {

    public static final Logger LOGGER = LogManager.getLogger(InputScanner.class);

    private final Scanner scanner;

    public InputScanner() {
        this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
    }

    public String scanString() {
        return scanner.nextLine();
    }

    public int scanInt(int min, int max) {
        int input;
        while (true){
            while (!scanner.hasNextInt()) {
                LOGGER.info("Please enter a valid integer: ");
                scanner.nextLine();
            }
            input = scanner.nextInt();
            scanner.nextLine();
            try {
                InputValidator.validateInt(input, min, max);
                return input;
            } catch (InvalidIntegerException e) {
                LOGGER.info("Invalid input: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    public String scanWord() {
        String input;
        while (true){
            LOGGER.info("Please enter a single word: ");
            input = scanner.nextLine();
           try {
               InputValidator.validateWord(input);
               return input;
           } catch (InvalidWordException e) {
               LOGGER.info("Invalid input: " + e.getMessage());
           }
        }
    }

    public void close() {
        scanner.close();
    }
}
