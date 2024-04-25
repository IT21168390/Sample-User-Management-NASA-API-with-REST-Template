package edu.AF.UserManagement.models.nasa.epic;

import lombok.Data;

@Data
public class EPIC {
    private String identifier;
    private String caption;
    private String image;
    private String version;
    private CentroidCoordinates centroid_coordinates;
    private J2000Position dscovr_j2000_position;
    private J2000Position lunar_j2000_position;
    private J2000Position sun_j2000_position;
    private AttitudeQuaternions attitude_quaternions;
    private String date;
    private Coords coords;
}
