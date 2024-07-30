package management.selenium.rest_api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginDTO extends BasePojo {

    private String username;
    private String password;

    public UserLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String toString() {
        return "UserLoginDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
