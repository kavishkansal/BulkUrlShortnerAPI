package com.markwebtechnologies.bulkurlshortner.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.markwebtechnologies.bulkurlshortner.models.Url;

import java.io.IOException;
import java.util.List;

public interface UrlService {
    Url getUrlByid() throws JsonProcessingException;
    String getUrlByTargetUrl();
    String getTargetUrlByid();

    Url getTargetUrlByShortner(Long id) throws IOException;

    List<Url> getAllUrls();

    Long createUrl();

    void deleteUrlbyId();

}
