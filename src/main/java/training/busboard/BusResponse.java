package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusResponse {

    private String lineId;

    private Integer timeToStation;
    

    public Integer getTimeToStation() {
        return timeToStation;
    }

    public void setTimeToStation(Integer timeToStation) {
        timeToStation = timeToStation/60;
        this.timeToStation = timeToStation;
    }

    public BusResponse() {
        }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

   
    
}
