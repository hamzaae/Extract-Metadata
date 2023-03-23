package service;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "metaData")
public class MetaData implements Serializable {
    private String title;
    private String author;
    private String date;
    private String type;

    // getters, and setters
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getDate() {
        return date;
    }
    public String getType() {
        return type;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setType(String type) {
        this.type = type;
    }

}
