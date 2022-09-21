package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Response {

    private MyStopPoints stopPoints;

    public MyStopPoints getStopPoints() {
        return stopPoints;
    }

    public Response() {
    }
}


    
    

