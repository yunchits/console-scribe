package com.yunchits.menu;

import com.yunchits.utils.TextFileReader;
import com.yunchits.utils.input.InputScanner;
import com.yunchits.utils.log.LogFileWriter;
import com.yunchits.utils.log.MenuLogWriter;
import com.yunchits.utils.text.TextAnalyzer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class MenuHandler {

    public static final Logger LOGGER = LogManager.getLogger(MenuHandler.class);

    private final InputScanner scanner;

    private final TextFileReader reader;

    private final MenuLogWriter menuLogger;

    public MenuHandler(LogFileWriter logFileWriter) {
        this.scanner = new InputScanner();
        this.reader = new TextFileReader();
        this.menuLogger = new MenuLogWriter(logFileWriter);
    }

    public void displayMenu() throws IOException {
        LOGGER.info("1 - Enter text...");
        LOGGER.info("2 - Read from text file...");

        int choice = scanner.scanInt(1, 2);
        String text = switch (choice) {
            case 1 -> enterText();
            case 2 -> readFromFile();
            default -> throw new IllegalArgumentException("Unexpected input: " + choice);
        };
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
            LOGGER.info("1 - Unique words");
            LOGGER.info("2 - Number of letters");
            LOGGER.info("3 - Word search");
            LOGGER.info("0 - Exit");

            choice = scanner.scanInt(0, 3);
            switch (choice) {
                case 1:
                    uniqueWords(s);
                    break;
                case 2:
                    numberOfLetters(s);
                    break;
                case 3:
                    searchWord(s);
                    break;
                case 0:
                    LOGGER.info("Exiting...");
                    scanner.close();
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected input: " + choice);
            }
        } while (choice != 0);
    }

    private void uniqueWords(String s) {
        int uniqueWords = TextAnalyzer.countUniqueWords(s);
        LOGGER.info("Number of unique words: " + uniqueWords);

        menuLogger.writeUniqueWords(s, uniqueWords);
    }

    private void numberOfLetters(String s) {
        int allLetters = TextAnalyzer.countLetters(s);
        String result = TextAnalyzer.split(s);
        LOGGER.info("Result: " + result);
        LOGGER.info("Number of letters: " + allLetters);

        menuLogger.writeNumberOfLetters(s, allLetters);
    }

    private void searchWord(String s) {
        LOGGER.info("Enter the word you want to search: ");
        String word = scanner.scanWord();

        LOGGER.info("Search for the word: " + word);

        int count = TextAnalyzer.countWordOccurrences(s, word);

        if (count == 0) {
            LOGGER.info("No matches found");
        } else {
            LOGGER.info("Result: " + count + " match found");
        }

        menuLogger.writeSearchWord(s, word, count);

    }
}
