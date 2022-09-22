package training.busboard.Response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusIdStopPoints {
    private List<BusIdResponse> stopPoints;

    public BusIdStopPoints() {
    }

    public List<BusIdResponse> getStopPoint() {
        return stopPoints;
    }

    public void setStopPoints(List<BusIdResponse> stopPoints) {
        this.stopPoints = stopPoints;
    }   
}