package io.collegeplanner.my.EllucianDataApplication.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Data
@AllArgsConstructor
public class Course {
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
    public int hashCode() {
        return new HashCodeBuilder()
                .append(courseName)
                .append(courseTitle)
                .toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Course) {
            final Course other = (Course) obj;
            return new EqualsBuilder()
                    .append(courseName, other.courseName)
                    .append(courseTitle, other.courseTitle)
                    .isEquals();
        } else {
            return false;
        }
    }
}
