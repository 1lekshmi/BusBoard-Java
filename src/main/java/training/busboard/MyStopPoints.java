package training.busboard;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MyStopPoints {
    
    private String naptanId;

    private String commonName;
    
    public String getCommonName() {
        return commonName;
    }


    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }


    public void setNaptanId(String naptanId) {
        this.naptanId = naptanId;
    }


    public String getNaptanId() {
        return naptanId;
    }


    public MyStopPoints() {
    }



}
