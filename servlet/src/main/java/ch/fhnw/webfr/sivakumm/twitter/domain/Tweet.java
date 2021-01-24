package ch.fhnw.webfr.sivakumm.twitter.domain;

import java.util.Date;

public class Tweet {
    private int id;
    private String username;
    private String tweet;
    private Date date;

    public Tweet(String username, String tweet) {
        this.username = username;
        this.tweet = tweet;
        this.date = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
