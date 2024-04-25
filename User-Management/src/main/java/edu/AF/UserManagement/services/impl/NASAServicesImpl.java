package edu.AF.UserManagement.services.impl;

import edu.AF.UserManagement.models.nasa.apod.APOD;
import edu.AF.UserManagement.models.nasa.earth.EarthImagery;
import edu.AF.UserManagement.models.nasa.epic.EPIC;
import edu.AF.UserManagement.models.nasa.marsRover.MarsRoverPhotoResponse;
import edu.AF.UserManagement.services.NASAServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class NASAServicesImpl implements NASAServices {
    @Value("${nasa.api.key}")
    private String apiKey;
    @Value("${nasa.api.uri.earth}")
    private String apiURL_EarthImagery;
    @Value("${nasa.api.uri.epic}")
    private String apiURL_EPIC;
    @Value("${nasa.api.uri.mars}")
    private String apiURL_Mars;
    @Value("${nasa.api.uri.apod}")
    private String apiURL_APOD;
    @Autowired
    private RestTemplate restTemplate;

    public MarsRoverPhotoResponse retrieveMarsRoverPhotos() throws Exception {
        //System.out.println(apiKey+"----------"+apiURL_Mars);

        ResponseEntity<MarsRoverPhotoResponse> responseEntity;

        HttpEntity<String> requestEntity = new HttpEntity<>(null, null);

        //UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(apiURL+"/mars-photos/api/v1/rovers/curiosity/photos")
        UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(apiURL_Mars)
                .queryParam("sol", 100)
                .queryParam("api_key", apiKey)
                .build();

        System.out.println("Called API (URL): "+uriBuilder.toUriString()+"\n\n\n");

        try {
            responseEntity = restTemplate
                    .exchange(uriBuilder.toUriString(),
                            HttpMethod.GET,
                            requestEntity,
                            MarsRoverPhotoResponse.class);
        } catch (HttpStatusCodeException e){
            throw new Exception(e.getMessage());
        }

        System.out.println(responseEntity.getBody().getPhotos().size()+"\n\n\n");
        //System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @Override
    public APOD retrieveAPOD() {
        ResponseEntity<APOD> responseEntity;

        HttpEntity<String> requestEntity = new HttpEntity<>(null, null);

        UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(apiURL_APOD)
                .queryParam("api_key", apiKey)
                .build();

        System.out.println("Called API (URL): "+uriBuilder.toUriString()+"\n\n\n");

        try {
            responseEntity = restTemplate
                    .exchange(uriBuilder.toUriString(),
                            HttpMethod.GET,
                            requestEntity,
                            APOD.class);
        } catch (HttpStatusCodeException e){
            System.out.println(e.getMessage());
            return null;
        }

        return responseEntity.getBody();
    }

    @Override
    public EarthImagery retrieveEarthSpecificImage() {
        UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(apiURL_EarthImagery)
                .queryParam("lon", 100.75)
                .queryParam("lat",1.5)
                .queryParam("date","2014-09-01")
                .queryParam("dim",0.15)
                .queryParam("api_key", apiKey)
                .build();
        /*
        .queryParam("lon", 95.33)
                .queryParam("lat",29.78)
                .queryParam("date","2018-01-01")
                .queryParam("dim",0.15)
                .queryParam("api_key", apiKey)
                .build();
         */

        // lon=-95.33&lat=29.78&date=2018-01-01&dim=0.15&api_key=DEMO_KEY

        System.out.println("Called API (URL): "+uriBuilder.toUriString()+"\n\n\n");

        try {
            ResponseEntity<EarthImagery> responseEntity = restTemplate
                    .exchange(uriBuilder.toUriString(),
                            HttpMethod.GET,
                            HttpEntity.EMPTY,
                            EarthImagery.class);

            System.out.println(responseEntity.getBody());
            return responseEntity.getBody();
        } catch (HttpStatusCodeException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<EPIC> retrieveEPICData() {
        UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(apiURL_EPIC+"/images")
                .queryParam("api_key", apiKey)
                .build();

        System.out.println("Called API (URL): "+uriBuilder.toUriString()+"\n\n\n");

        try {
            ResponseEntity<List<EPIC>> responseEntity = restTemplate
                    .exchange(uriBuilder.toUriString(),
                            HttpMethod.GET,
                            HttpEntity.EMPTY,
                            new ParameterizedTypeReference<List<EPIC>>() {});

            System.out.println(responseEntity.getBody());
            return responseEntity.getBody();
        } catch (HttpStatusCodeException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
