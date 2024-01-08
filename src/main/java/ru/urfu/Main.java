package ru.urfu;

import ru.urfu.application.Application;

public class Main {
    private static final String PATH_TO_CSV = "Forbes.csv";
    private static final String PATH_TO_URL_DB = "jdbc:sqlite:identifier.sqlite";

    public static void main(String[] args) {
        Application.startApp(PATH_TO_CSV, PATH_TO_URL_DB);
    }
}