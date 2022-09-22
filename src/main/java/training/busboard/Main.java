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
       

        // Response response = client.target("https://api.tfl.gov.uk")
        //     // .path("StopPoint?stopTypes=NaptanOnstreetBusCoachStopPair&lat=" + latitude + "&lon=" + longitude)
        //     .path(String.format("StopPoint?stopTypes=NaptanOnstreetBusCoachStopPair&lat=%s&lon=%s", latitude, longitude))
        //     .request(MediaType.APPLICATION_JSON_TYPE)
        //     .get(Response.class);

        Response response = client.target("https://api.tfl.gov.uk")
            .path("{type}")
            .resolveTemplate("type","StopPoint")
            .queryParam("stopTypes", "NaptanPublicBusCoachTram")
            .queryParam("lat",latitude)
            .queryParam("lon",longitude)
            .request(MediaType.APPLICATION_JSON_TYPE)
            .get(Response.class);

                
        List<MyStopPoints> stopCodeList = response.getStopPoints();
        List<String> stopCodes = stopCodeList.stream()
                                            .map(code -> code.getNaptanId())
                                            .collect(Collectors.toList());
        // stopCodes.forEach(System.out::println);
        List<String> stopNames = stopCodeList.stream()
                                            .map(code -> code.getCommonName())
                                            .collect(Collectors.toList());
        // stopNames.forEach(System.out::println);


        for(int i = 0; i < 2; i++){
            String stopCode = stopCodes.get(i);
            String stopName = stopNames.get(i);

            List<BusResponse> list = client.target("https://api.tfl.gov.uk")
                    .path("stoppoint/" + stopCode + "/arrivals")
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get(new GenericType<List<BusResponse>>(){});

            List<BusResponse> nextBuses = list.stream()
                .sorted(Comparator.comparing(BusResponse::getTimeToStation))
                .limit(5)
                .collect(Collectors.toList());
            
            System.out.println(stopName);
                
            for(BusResponse id: nextBuses){
                // format string
                System.out.println(String.format("Bus: %s ----- Arriving in: %s mins", id.getLineId(), id.getTimeToStation()));
                
                // string concatenation
                // System.out.println(stopName + "\nBus: " + id.getLineId() + "\nArriving in: " + id.getTimeToStation() + " mins");
            }
        }

       






        




        


        





        




    }
}	
