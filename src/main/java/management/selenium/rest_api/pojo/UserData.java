package management.selenium.rest_api.pojo;

import lombok.*;
import management.selenium.rest_api.dto.BasePojo;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserData extends BasePojo {

    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}
