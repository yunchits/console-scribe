package com.yunchits;

import com.yunchits.menu.MenuHandler;

import java.io.IOException;

public class ConsoleScribeApp {

    public static void main(String[] args) {

        MenuHandler menuHandler = new MenuHandler();

        try {
            menuHandler.displayMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
