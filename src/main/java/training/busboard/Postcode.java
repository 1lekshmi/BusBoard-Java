package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Postcode{

    private MyResult result;
    
    public Postcode() {
  
    }

    public MyResult getResult() {
        return result;
    }



}