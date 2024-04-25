package edu.AF.UserManagement.services;

import edu.AF.UserManagement.models.nasa.apod.APOD;
import edu.AF.UserManagement.models.nasa.earth.EarthImagery;
import edu.AF.UserManagement.models.nasa.epic.EPIC;
import edu.AF.UserManagement.models.nasa.marsRover.MarsRoverPhotoResponse;

import java.util.List;

public interface NASAServices {
    MarsRoverPhotoResponse retrieveMarsRoverPhotos() throws Exception;
    APOD retrieveAPOD();
    EarthImagery retrieveEarthSpecificImage();
    List<EPIC> retrieveEPICData();
}
