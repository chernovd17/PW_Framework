package inst.entities;

import inst.environment.InstEnvironment;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

    private String username;
    private String password;


    private User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static User createUser(String username, String password) {
        return new User(username, password);
    }


    public String toString() {
        return username;
    }

    public static User getDefaultUser(){
        return createUser(InstEnvironment.get().getUsername(), InstEnvironment.get().getPassword());
    }
}
