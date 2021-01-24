package ch.fhnw.webfr.sivakumm.zirper.domain;

import java.util.Date;

public class Zirp {
    private int id;
    private String username;
    private String zirp;
    private Date date;

    public Zirp(String username, String zirp) {
        this.username = username;
        this.zirp = zirp;
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

    public String getZirp() {
        return zirp;
    }

    public void setZirp(String zirp) {
        this.zirp = zirp;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
