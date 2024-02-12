package com.markwebtechnologies.bulkurlshortner.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UrlDto {
    private Long id;
    private String url;
    private String location_url;
}
