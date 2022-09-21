package training.busboard;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import org.glassfish.jersey.jackson.JacksonFeature;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.sound.sampled.SourceDataLine;

public class Main {
    public static void main(String args[]) {

        // String TFLKey = "aec31fc0bc514ac68776d4cfbf3a980c";
        // String TFLPass = "89ecf502d128444f856d63f1e0f76baf";
        Scanner scanner = new Scanner(System.in);
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

        System.out.println("Provide a bus stop ID");
        String busStop = scanner.nextLine();

        String testStopId = "490008660N";


        List<BusResponse> list = client.target("https://api.tfl.gov.uk")
                .path("StopPoint/" + busStop +"/Arrivals")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<BusResponse>>() {});

        List<BusResponse> sortedList = list.stream()
            .sorted(Comparator.comparing(BusResponse::getTimeToStation))
            .limit(5)
            .collect(Collectors.toList());
        
            for(BusResponse id: sortedList) {
                System.out.println("Incoming Bus: " 
                + id.getBusId() 
                + " | Arriving in: " 
                + id.getTimeToStation()
                + " minutes:");
            }
    }
}
