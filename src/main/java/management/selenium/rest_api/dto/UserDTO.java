package management.selenium.rest_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class UserDTO extends BasePojo {

    private String name;
    private String job;
}
