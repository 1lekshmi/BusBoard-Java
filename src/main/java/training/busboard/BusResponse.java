package training.busboard;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusResponse {
    private String lineId;
    private Integer timeToStation;

    public BusResponse() {
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getBusId() {
        return lineId;
    }

    public String getLineId() {
        return lineId;
    }

    public Integer getTimeToStation() {
        return timeToStation;
    }

    public void setTimeToStation(Integer timeToStation) {
        timeToStation = timeToStation / 60;
        this.timeToStation = timeToStation;
    }
}
