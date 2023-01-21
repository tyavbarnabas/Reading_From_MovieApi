package com.codeafriac.webclientmovieapi.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {

    @JsonProperty("Title")
    private String movieTitle;
    @JsonProperty("Year")
    private String releaseYear;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Url")
    private String posterUrl;


}
