package guru.qa.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties (ignoreUnknown = true)
public class User {
    private Integer id;
    private String name;
    private String job;
}
