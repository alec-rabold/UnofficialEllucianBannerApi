package io.collegeplanner.my.EllucianDataApplication.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.collegeplanner.my.EllucianDataApplication.util.Constants.Season;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Term {
    @JsonProperty("term_season")
    private Season season;
    @JsonProperty("term_year")
    private String year;
    @JsonProperty("term_code")
    private String termCode;
}
