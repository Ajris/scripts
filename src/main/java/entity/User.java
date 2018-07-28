package entity;

import org.bson.internal.Base64;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {
    @Id
    private String id;

    @Indexed(unique = true)
    private String login;

    private String password;

    private String username;

    private String email;

    private String[] scripts;

    private String[] Role;

    public User() {
    }

    public User(String id, String login, String password, String username, String email, String[] scripts) {
        this.id = id;
        this.login = login;
        this.password = Base64.encode(password.getBytes());
        this.username = username;
        this.email = email;
        this.scripts = scripts;
    }

    public User(String id, String login, String password, String username, String email, String[] scripts, String[] role) {
        this.id = id;
        this.login = login;
        this.password = Base64.encode(password.getBytes());
        this.username = username;
        this.email = email;
        this.scripts = scripts;
        Role = role;
    }

    public String[] getRole() {
        return Role;
    }

    public String[] getScripts() {
        return scripts;
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }


    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
