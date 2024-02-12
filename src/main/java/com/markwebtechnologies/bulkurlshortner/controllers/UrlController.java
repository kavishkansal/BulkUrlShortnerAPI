package com.markwebtechnologies.bulkurlshortner.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.markwebtechnologies.bulkurlshortner.dtos.UrlDto;
import com.markwebtechnologies.bulkurlshortner.models.Url;
import com.markwebtechnologies.bulkurlshortner.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/urls")
public class UrlController {

    private UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService){
        this.urlService = urlService;
    }
    @GetMapping
    public List<Url> getAllUrls(String targetUrl){
        return urlService.getAllUrls();
    }

    @GetMapping("/{id}")
    public Url getTargetUrlByShortner(@PathVariable Long id) throws IOException{
        return urlService.getTargetUrlByShortner(id);
    }



}
