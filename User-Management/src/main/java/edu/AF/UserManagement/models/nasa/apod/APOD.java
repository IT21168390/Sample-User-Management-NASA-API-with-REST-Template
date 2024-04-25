package edu.AF.UserManagement.models.nasa.apod;

import lombok.Data;

import java.util.Date;

@Data
public class APOD {
    private String date;
    private String explanation;
    private String hdurl;
    private String media_type;
    private String service_version;
    private String title;
    private String url;
}
