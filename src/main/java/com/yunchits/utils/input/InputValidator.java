package com.yunchits.utils.input;

import com.yunchits.exceptions.InvalidWordException;

public class InputValidator {

    public boolean isValidInt(int input, int min, int max) {
        return input >= min && input <= max;
    }

    public boolean isValidWord(String input) {
        return input.matches("^[a-zA-Z]{2,}$");
    }

    public void validateInt(int input, int min, int max) throws InvalidWordException {
        if (!isValidInt(input, min, max)) {
            throw new InvalidWordException("Please enter an integer in the range from " + min + " to " + max);
        }
    }

    public void validateWord(String input) throws InvalidWordException {
        if (!isValidWord(input)) {
            throw new InvalidWordException("Please enter a word with more than 2 letters without spaces or special characters.");
        }
    }
}
