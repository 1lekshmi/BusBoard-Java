package training.busboard;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import training.busboard.Response.PostCodeGetResult;
import training.busboard.Response.BusIdResponse;
import training.busboard.Response.BusIdStopPoints;

import org.glassfish.jersey.jackson.JacksonFeature;

import java.util.ArrayList;
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
        HashMap<String, String> nc = new HashMap<>();

        String testStopId = "490008660N";

        // Call to postcode API
        PostCodeGetResult postCodeList = client.target("https://api.postcodes.io")
                .path("postcodes/" + postCode)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(PostCodeGetResult.class);
        
        // use hashmap to store long/lat KEY: String VALUE: Int
        hm.put("longitude", postCodeList.getResult().getLongitude());
        hm.put("latitude", postCodeList.getResult().getLatitude());


        String lat = String.valueOf(hm.get("latitude"));
        String lon = String.valueOf(hm.get("longitude"));
        
        // Call to tfl api for list of bus stops
        BusIdStopPoints busStopsList = client.target("https://api.tfl.gov.uk")
                .path("{type}")
                .resolveTemplate("type","StopPoint")
                .queryParam("stopTypes", "NaptanPublicBusCoachTram")
                .queryParam("lat",lat)
                .queryParam("lon",lon)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(BusIdStopPoints.class);
      
        List<BusIdResponse> getStopPoints = busStopsList.getStopPoint();
        

        List<String> naptanId = getStopPoints.stream()
                .map(x -> x.getNaptanId())
                .collect(Collectors.toList());
        for (String id: naptanId) {
                System.out.println(id);
        }

        List<String> busStops = getStopPoints.stream()
                .map(x -> x.getCommonName())
                .collect(Collectors.toList());
        
        for(int i = 0; i < 2; i++) {
                System.out.println("---------- " + busStops.get(i) + " ----------");

                //Call to tfl api for list of next busses
                List<BusResponse> busList = client.target("https://api.tfl.gov.uk")
                .path("StopPoint/" + naptanId.get(i)+"/Arrivals")
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
}