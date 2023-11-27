package com.yunchits;

import com.yunchits.menu.MenuHandler;
import com.yunchits.utils.log.LogFileWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ConsoleScribeApp {

    public static final Logger LOGGER = LogManager.getLogger(ConsoleScribeApp.class);

    public static void main(String[] args) {

        try (LogFileWriter logFileWriter = new LogFileWriter("output-log.txt")) {
            MenuHandler menuHandler = new MenuHandler(logFileWriter);
            menuHandler.displayMenu();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
