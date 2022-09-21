package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopPoints {
    private BusID stopPoints;

    public StopPoints() {

    }

    public BusID getStopPoint() {
        return stopPoints;
    }   
}