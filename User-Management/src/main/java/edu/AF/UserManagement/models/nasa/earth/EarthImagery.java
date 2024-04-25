package edu.AF.UserManagement.models.nasa.earth;

import lombok.Data;

@Data
public class EarthImagery {
    private String date;
    private String id;
    private EarthImageryResource resource;
    private String service_version;
    private String url;
}
