package com.example.project;

public class BoardData {

    String name;
    String title;
    String date;
    String board;

    public BoardData(String name, String title, String date, String board) {
        this.name = name;
        this.title = title;
        this.date = date;
        this.board = board;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }
}
