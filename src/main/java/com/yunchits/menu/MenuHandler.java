package com.yunchits.menu;

import com.yunchits.utils.TextFileReader;
import com.yunchits.utils.text.TextAnalyzer;
import com.yunchits.utils.input.InputScanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class MenuHandler {

    private final InputScanner scanner;

    private final TextFileReader reader;

    public static final Logger LOGGER = LogManager.getLogger(MenuHandler.class);

    public MenuHandler() {
        this.scanner = new InputScanner();
        this.reader = new TextFileReader();
    }

    public void displayMenu() throws IOException {
        int choice;

        LOGGER.info("1 - Enter text...");
        LOGGER.info("2 - Read from text file...");

        choice = scanner.scanInt(1, 2);
        String text;
        switch (choice) {
            case 1:
                text = enterText();
                break;
            case 2:
                text = readFromFile();
                break;
            default:
                throw new IllegalArgumentException("Unexpected input: " + choice);
        }

        displayTextMenu(text);
    }

    private String enterText() {
        LOGGER.info("Enter text:");
        return scanner.scanString();
    }

    private String readFromFile() throws IOException {
        LOGGER.info("Enter file path:");
        String path = scanner.scanString();
        return reader.readFileToString(path);
    }

    private void displayTextMenu(String s) {
        int choice;
        do {
            LOGGER.info("1 - Unique letters");
            LOGGER.info("2 - Number of letters");
            LOGGER.info("3 - Word search");
            LOGGER.info("0 - Exit");

            choice = scanner.scanInt(0, 3);
            switch (choice) {
                case 1:
                    uniqueLetters(s);
                    break;
                case 2:
                    numberOfLetters(s);
                    break;
                case 3:
                    searchWord(s);
                    break;
                case 0:
                    LOGGER.info("Exiting...");
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected input: " + choice);
            }
        } while (choice != 0);
    }

    private void uniqueLetters(String s) {
        int uniqueLetters = TextAnalyzer.countUniqueLetters(s);
        LOGGER.info("Number of unique letters: " + uniqueLetters);
    }

    private void numberOfLetters(String s) {
        int allLetters = TextAnalyzer.countLetters(s);
        LOGGER.info("Result: " + TextAnalyzer.split(s));
        LOGGER.info("Number of letters: " + allLetters);
    }

    private void searchWord(String s) {
        LOGGER.info("Enter the word you want to search: ");
        String word = scanner.scanWord();

        LOGGER.info("Search for the word: " + word);

        if (TextAnalyzer.hasWord(s, word)) {
            int count = TextAnalyzer.countWordOccurrences(s, word);
            LOGGER.info("Result: " + count + " match found");
        } else {
            LOGGER.info("No matches found");
        }
    }
}
