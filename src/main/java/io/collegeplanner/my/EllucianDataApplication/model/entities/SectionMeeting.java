package io.collegeplanner.my.EllucianDataApplication.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonPropertyOrder({"type", "days", "time", "location", "instructors", "date_range", "schedule_type"})
public class SectionMeeting {
    @JsonProperty("type")
    private String type;
    @JsonProperty("time")
    private String time;
    @JsonProperty("days")
    private String days;
    @JsonProperty("location")
    private String location;
    @JsonProperty("date_range")
    private String dateRange;
    @JsonProperty("schedule_type")
    private String scheduleType;
    @JsonProperty("instructors")
    private String instructors;
}
