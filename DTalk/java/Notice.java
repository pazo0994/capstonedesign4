package com.example.tpwls.maketalk;

/**
 * Created by tpwls on 2018-02-08.
 */

public class Notice {

    String title;
    String content;
    String name;
    String date;

    public Notice(String title, String content, String name, String date) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
