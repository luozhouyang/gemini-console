package me.stupidme.console.main;

/**
 * Created by allen on 18-3-30.
 */
public class Article {
    private String type;
    private String title;
    private String date;
    private String author;

    public Article() {

    }

    public Article(String type, String title, String date, String author) {
        this.type = type;
        this.title = title;
        this.date = date;
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
