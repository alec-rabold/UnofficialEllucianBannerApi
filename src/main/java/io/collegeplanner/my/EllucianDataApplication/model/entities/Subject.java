package io.collegeplanner.my.EllucianDataApplication.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Subject {
    @JsonProperty("subj_abbr")
    private String abbreviation;
    @JsonProperty("subj_name")
    private String completeName;
}
