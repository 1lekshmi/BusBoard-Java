package training.busboard.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostCodeGetResult {
    private PostCodeResponse result;

    public PostCodeGetResult() {

    }

    public PostCodeResponse getResult() {
        return result;
    }   
}
