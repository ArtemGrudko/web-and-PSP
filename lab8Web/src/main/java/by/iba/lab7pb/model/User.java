package by.iba.lab7pb.model;

import lombok.Data;

@Data
public class User {

    private int id;
    private String login;
    private byte[] passw;
    private boolean type;

    public User(String name, byte[] password, boolean type, int id) {
        this.login = name;
        this.passw = password;
        this.type = type;
        this.id = id;
    }


}
