package entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "script")
public class Script {
    @Id
    private String id;

    @Indexed(unique = true)
    private String title;

    private String text;

    public Script(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public Script() {
    }

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
