package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusStopPoints {
    private Integer naptanId;
    private String commonName;

    public BusStopPoints() {

    }

    public Integer getNaptanId() {
        return naptanId;
    }

    public void setNaptanId(Integer naptanId) {
        this.naptanId = naptanId;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    

    
}
