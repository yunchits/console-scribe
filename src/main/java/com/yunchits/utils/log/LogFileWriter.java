package com.yunchits.utils.log;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LogFileWriter implements AutoCloseable {

    public static final Logger LOGGER = LogManager.getLogger(LogFileWriter.class);

    private final StringBuilder content;

    private final String filename;

    public LogFileWriter(String filename) {
        this.content = new StringBuilder();
        this.filename = filename;
    }

    public void add(String content) {
        this.content.append(content).append("\n");
    }

    private void write() {
        try {
            FileUtils.writeStringToFile(
                new File(filename),
                content.toString(),
                StandardCharsets.UTF_8,
                true
            );
        } catch (IOException e) {
            LOGGER.error("Error writing to log file: " + e.getMessage());
        }
    }

    @Override
    public void close() {
        write();
    }
}