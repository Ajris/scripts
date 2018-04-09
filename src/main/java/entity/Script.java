package entity;


import org.apache.tomcat.jni.Address;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "script")
public class Script {
    @Id
    private String id;
    private String title;
    private String text;

    public Script(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public Script() {}

    public Script(String personId, String title, String text) {
        super();
        this.id = personId;
        this.title = title;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format(
                "Script[id=%s, title='%s', text='%s']",
                id, title, text);
    }
}
