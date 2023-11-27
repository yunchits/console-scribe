package com.yunchits.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TextFileReader {

    private static final String ALLOWED_EXTENSION = ".txt";

    public String readFileToString(String filePath) throws IOException {
        File file = new File(filePath);

        validateFile(filePath, file);

        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

    private void validateFile(String filePath, File file) throws IOException {
        if (!file.exists() || !file.isFile() || !isAllowedExtension(file)) {
            throw new IOException("Invalid file:" + filePath);
        }
    }

    private boolean isAllowedExtension(File file) {
        return file.getName().toLowerCase().endsWith(ALLOWED_EXTENSION);
    }
}
