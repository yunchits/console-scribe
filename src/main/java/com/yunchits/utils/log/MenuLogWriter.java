package com.yunchits.utils.log;

import com.yunchits.utils.text.TextAnalyzer;

public class MenuLogWriter {

    private final LogFileWriter logFileWriter;

    public MenuLogWriter(LogFileWriter logFileWriter) {
        this.logFileWriter = logFileWriter;
    }

    public void writeUniqueWords(String s, int uniqueWords) {
        writeHeader("UNIQUE WORDS");
        writeSourceText(s);
        logFileWriter.add("Number of different words: " + uniqueWords);
    }

    public void writeNumberOfLetters(String s, int allLetters) {
        writeHeader("NUMBER OF LETTERS");
        writeSourceText(s);
        writeResult(TextAnalyzer.split(s));
        logFileWriter.add("Number of letters: " + allLetters);
    }

    public void writeSearchWord(String s, String word, int matchesFound) {
        writeHeader("WORD SEARCH");
        writeSourceText(s);
        logFileWriter.add("Search for: " + word);
        logFileWriter.add("Result: " + matchesFound + " matches found");
    }

    public void writeHeader(String header) {
        logFileWriter.add("-----------------------");
        logFileWriter.add(header);
        logFileWriter.add("-----------------------");
    }

    public void writeSourceText(String sourceText) {
        logFileWriter.add("Source text: " + sourceText);
    }

    public void writeResult(String result) {
        logFileWriter.add("Result: " + result);
    }
}