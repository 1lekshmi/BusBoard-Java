package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String args[]) {
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        
        Scanner input = new Scanner(System.in);
        String postCode = input.nextLine();
        // user inputs the postcode

        Postcode postCodeInfo = client.target("https://api.postcodes.io")
            .path("postcodes/" + postCode)
            .request(MediaType.APPLICATION_JSON_TYPE)
            .get(Postcode.class);

        Double latitude = postCodeInfo.getResult().getLatitude();
        Double longitude = postCodeInfo.getResult().getLongitude();
       

        Response response = client.target("https://api.tfl.gov.uk")
            .path("StopPoint?stopTypes=NaptanOnstreetBusCoachStopPair&lat=" + latitude + "&lon=" + longitude)
            .request(MediaType.APPLICATION_JSON_TYPE)
            .get(Response.class);
        
        // String stopName = res.getCommonName();
        String stopCode = response.getStopPoints().getNaptanId();
        // System.out.println(stopCode);

        // List<BusResponse> list = client.target("https://api.tfl.gov.uk")
        //     .path("stoppoint/" + stopCode + "/arrivals")
        //     .request(MediaType.APPLICATION_JSON_TYPE)
        //     .get(new GenericType<List<BusResponse>>(){});

        // List<BusResponse> nextBuses = list.stream()
        //     .sorted(Comparator.comparing(BusResponse::getTimeToStation))
        //     .limit(5)
        //     .collect(Collectors.toList());

        // for(BusResponse id: nextBuses){
        //     System.out.println(stopName + "\nBus: " + id.getLineId() + "\nArriving in: " + id.getTimeToStation() + " mins");
        // }



        


        





        




    }
}	
