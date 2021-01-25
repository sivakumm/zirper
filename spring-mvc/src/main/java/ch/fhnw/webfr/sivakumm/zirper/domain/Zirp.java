package ch.fhnw.webfr.sivakumm.zirper.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "zirps")
public class Zirp {

    @Id
    private String id;

    private String username;
    private String zirp;
    private Date date;

    public Zirp() {
        this.date = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
