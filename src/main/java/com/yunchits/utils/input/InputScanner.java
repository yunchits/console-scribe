package com.yunchits.utils.input;

import com.yunchits.exceptions.InvalidWordException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class InputScanner {

    public static final Logger LOGGER = LogManager.getLogger(InputScanner.class);

    private final Scanner scanner;
    private final InputValidator validator;

    public InputScanner() {
        this.scanner = new Scanner(System.in);
        this.validator = new InputValidator();
    }

    public String scanString() {
        return scanner.nextLine();
    }

    public int scanInt(int min, int max) {
        int input;
        do {
            while (!scanner.hasNextInt()) {
                LOGGER.info("Please enter a valid integer: ");
                scanner.next();
            }

            input = scanner.nextInt();


            try {
                validator.validateInt(input, min, max);
            } catch (InvalidWordException e) {
                LOGGER.info("Invalid input: " + e.getMessage());
            }
        } while (!validator.isValidInt(input, min, max));
        scanner.nextLine();
        return input;
    }

    public String scanWord() {
        String input;
        do {
            LOGGER.info("Please enter a single word: ");
            input = scanner.next();
           try {
               validator.validateWord(input);
           } catch (InvalidWordException e) {
               LOGGER.info("Invalid input: " + e.getMessage());
           }


        } while (!validator.isValidWord(input));
        return input;
    }
}
