package io.collegeplanner.my.EllucianDataApplication.model;

import lombok.Data;

@Data
public class CoursesRequestModel {
    private String college;
    private String term;
    private String subject;
}
