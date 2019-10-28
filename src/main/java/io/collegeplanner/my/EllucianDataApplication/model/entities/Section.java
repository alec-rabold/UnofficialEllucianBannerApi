package io.collegeplanner.my.EllucianDataApplication.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Section {
    @JsonProperty("course_id")
    private String courseId;
    @JsonProperty("section")
    private String courseSection;
    @JsonProperty("meeting_times")
    private SectionMeeting[] meetingTimes;
}
