package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusID {
    private Integer naptanId;
    private String commonName;

    public BusID() {

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
