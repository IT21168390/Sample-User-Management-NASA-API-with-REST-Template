package edu.AF.UserManagement.controllers;

import edu.AF.UserManagement.models.nasa.apod.APOD;
import edu.AF.UserManagement.models.nasa.earth.EarthImagery;
import edu.AF.UserManagement.models.nasa.epic.EPIC;
import edu.AF.UserManagement.models.nasa.marsRover.MarsRoverPhotoResponse;
import edu.AF.UserManagement.services.NASAServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/nasa/common")
@CrossOrigin
public class NasaAPIController {
    @Autowired
    private NASAServices nasaServices;

    @GetMapping("/mars_rover")
    public ResponseEntity<MarsRoverPhotoResponse> getMarsRoverPhotos(){
        try {
            MarsRoverPhotoResponse marsRoverPhotoResponse = nasaServices.retrieveMarsRoverPhotos();
            return new ResponseEntity<>(marsRoverPhotoResponse, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/apod")
    public ResponseEntity getAPOD(){
        try {
            APOD astronomyPictureOfTheDay = nasaServices.retrieveAPOD();
            if (astronomyPictureOfTheDay == null)
                return new ResponseEntity<>("Astronomy Picture of the Day is 'null'!", HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(astronomyPictureOfTheDay, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/earthImagery")
    public ResponseEntity getEarthImagery(){
        try {
            EarthImagery earthImage = nasaServices.retrieveEarthSpecificImage();
            if (earthImage == null)
                return new ResponseEntity<>("Astronomy Picture of the Day is 'null'!", HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(earthImage, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/epic")
    public ResponseEntity getEPICData(){
        try {
            List<EPIC> earthImage = nasaServices.retrieveEPICData();
            if (earthImage == null)
                return new ResponseEntity<>("Astronomy Picture of the Day is 'null'!", HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(earthImage, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

}
