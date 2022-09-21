package training.busboard;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MyStopPoints {
    
    private String naptanId;


    public String getNaptanId() {
        return naptanId;
    }


    public MyStopPoints() {
    }



}
