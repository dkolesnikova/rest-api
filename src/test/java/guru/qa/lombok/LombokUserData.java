package guru.qa.lombok;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LombokUserData {
    @JsonProperty("data")
    private User user;
    @JsonProperty("name")
    private String name;
    @JsonProperty("job")
    private String job;
}
