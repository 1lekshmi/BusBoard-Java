package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostCodeResult {
    private PostCodeResponse result;

    public PostCodeResult() {

    }

    public PostCodeResponse getResult() {
        return result;
    }   
}
