package com.markwebtechnologies.bulkurlshortner.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Url extends BaseModel {
    private String url;
    private String targetUrl;

}
