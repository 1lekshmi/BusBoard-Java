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


    

    // private String naptanId;

    // private String commonName;

    

    // public String getNaptanId() {
    //     return naptanId;
    // }

    // public void setNaptanId(String naptanId) {
    //     this.naptanId = naptanId;
    // }

    // public String getCommonName() {
    //     return commonName;
    // }

    // public void setCommonName(String commonName) {
    //     this.commonName = commonName;
    // }
    
    

