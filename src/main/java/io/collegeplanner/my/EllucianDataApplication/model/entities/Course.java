package io.collegeplanner.my.EllucianDataApplication.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course implements Comparable<Course> {
    @JsonProperty("course_name")
    private String courseName;
    @JsonProperty("course_title")
    private String courseTitle;
    @JsonProperty("department")
    private String department;
    @JsonProperty("course_number")
    private String courseNumber;
    @JsonProperty("course_id")
    private String courseId;
    @JsonProperty("course_section")
    private String courseSection;

    @Override
    public int compareTo(final Course other) {
        return this.courseName.compareTo(other.courseName);
    }
}
