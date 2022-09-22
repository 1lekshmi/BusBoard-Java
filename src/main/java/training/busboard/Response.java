package training.busboard;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Response {

    private List<MyStopPoints> stopPoints;

    public List<MyStopPoints> getStopPoints() {
        return stopPoints;
    }

    public Response() {
    }
}


    
    

