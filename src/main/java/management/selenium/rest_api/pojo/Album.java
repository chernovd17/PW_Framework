package management.selenium.rest_api.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import management.selenium.rest_api.dto.BasePojo;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Album extends BasePojo {

    @JsonProperty("userId")
    public Integer userId;

    @JsonProperty("id")
    public Integer id;

    @JsonProperty("title")
    public String title;
}
