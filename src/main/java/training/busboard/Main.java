package training.busboard;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import org.glassfish.jersey.jackson.JacksonFeature;

import java.util.Comparator;
import java.util.HashMap;
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

        System.out.println("Provide a postcode");
        String postCode = scanner.nextLine();
        HashMap<String, Double> hm = new HashMap<>();
        HashMap<String, Integer> nc = new HashMap<>();

        String testStopId = "490008660N";

        // Call to postcode API
        List<PostCodeResponse> postCodeList = client.target("https://api.postcodes.io")
                .path("postcodes/" + postCode)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<PostCodeResponse>>() {});
        
        // use hashmap to store long/lat KEY: String VALUE: Int
        for (PostCodeResponse l: postCodeList) {
            hm.put("longitude", l.getLongitude());
            hm.put("latitude", l.getLatitude());
        }

        // Call to tfl api for list of bus stops
        List<BusStopPoints> busStopsList = client.target("https://api.tfl.gov.uk")
                .path("/StopPoint?stopTypes=NaptanOnstreetBusCoachStopPair&lat=" + hm.get("latitude") + "&lon=" + hm.get("longitude"))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<BusStopPoints>>() {});
        
                for (BusStopPoints b: busStopsList) {
                    nc.put("naptanId", b.getNaptanId());
                }

        // Call to tfl api for list of next busses
        List<BusResponse> busList = client.target("https://api.tfl.gov.uk")
                .path("StopPoint/" + nc.get("naptanId") +"/Arrivals")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<BusResponse>>() {});

        List<BusResponse> sortedList = busList.stream()
            .sorted(Comparator.comparing(BusResponse::getTimeToStation))
            .limit(5)
            .collect(Collectors.toList());
        
            for(BusResponse id: sortedList) {
                System.out.println("Incoming Bus: " 
                + id.getLineId() 
                + " | Arriving in: " 
                + id.getTimeToStation()
                + " minutes:");
            }
    }
}
