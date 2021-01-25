package ch.fhnw.webfr.sivakumm.zirper.domain;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "zirps")
public class Zirp {

    @Id
    private String id;

    @Size(min = 3, max = 15)
    private String username;

    @Size(min = 1, max = 280)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zirp other = (Zirp) o;
        return id.equals(other.id) && username.equals(other.username) && zirp.equals(other.zirp) && date.equals(other.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, zirp, date);
    }
}
