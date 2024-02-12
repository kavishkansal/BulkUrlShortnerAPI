package com.markwebtechnologies.bulkurlshortner.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.markwebtechnologies.bulkurlshortner.dtos.BulkListDto;
import com.markwebtechnologies.bulkurlshortner.dtos.SingleUrlDto;
import com.markwebtechnologies.bulkurlshortner.dtos.UrlDto;
import com.markwebtechnologies.bulkurlshortner.models.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Service("UrlService")
public class UrlServiceImpl implements UrlService{

    private RestTemplateBuilder restTemplateBuilder;
    private String genericUrlLinks = "https://assassincreedcodenamejade.co/api/links/";
    private String specificUrlLinksShortner = "https://assassincreedcodenamejade.co/api/links/{id}";
    private String header = "Authorization: Bearer {api_key}";

    @Autowired
    private UrlServiceImpl(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public Url getUrlByid() throws JsonProcessingException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Bearer 90117d57412bda840fc81ae3c6baa10c");
        HttpEntity<String> httpEntity = new HttpEntity<String>("parameters",httpHeaders);
        UrlDto responseEntity = restTemplate.getForObject(genericUrlLinks,UrlDto.class,httpEntity);
        List<Url> urlList = new LinkedList<>();
//        for(UrlDto urlDto: getDatafromAPI(responseEntity.getBody())){
//            urlList.add(getUrlFromUrlDto(urlDto));
//        }
        return getUrlFromUrlDto(responseEntity);
    }

    @Override
    public String getUrlByTargetUrl() {
        return null;
    }

    @Override
    public String getTargetUrlByid() {
        return null;
    }

    @Override
    public Url getTargetUrlByShortner(Long id) throws IOException{
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Bearer 90117d57412bda840fc81ae3c6baa10c");
        HttpEntity<String> httpEntity = new HttpEntity<String>("parameters",httpHeaders);
        ResponseEntity<SingleUrlDto> responseEntity =
                restTemplate.exchange(specificUrlLinksShortner, HttpMethod.GET, httpEntity, SingleUrlDto.class,id);
        return getUrlFromUrlDto(responseEntity.getBody().getData());
    }

//    @Override
//    public List<Url> getAllUrls() {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Authorization","Bearer 90117d57412bda840fc81ae3c6baa10c");
//        HttpEntity<String> httpEntity = new HttpEntity<String>("parameters",httpHeaders);
//        ResponseEntity<String> responseEntity = restTemplate.exchange(genericUrlLinks, HttpMethod.GET, httpEntity, String.class);
//        List<Url> urlList = new LinkedList<>();
////        for(UrlDto urlDto: responseEntity.getBody()){
////            urlList.add(getUrlFromUrlDto(urlDto));
////        }
//        return urlList;
//
//    }

    @Override
    public List<Url> getAllUrls() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Bearer 90117d57412bda840fc81ae3c6baa10c");
        HttpEntity<String> httpEntity = new HttpEntity<String>("parameters",httpHeaders);
        ResponseEntity<BulkListDto> responseEntity = restTemplate.exchange(genericUrlLinks, HttpMethod.GET, httpEntity, BulkListDto.class);
        List<Url> urls = new LinkedList<>();
        for(UrlDto urlDto: responseEntity.getBody().getData()){
            urls.add(getUrlFromUrlDto(urlDto));
        }
        return urls;
    }

    @Override
    public Long createUrl() {
        return null;
    }

    @Override
    public void deleteUrlbyId() {

    }

    private List<String> getDatafromAPI(String data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(data);
        JsonNode name = root.path("data");
        List<String> listUrls = new LinkedList<>();
        if(name.isArray()){
            for(JsonNode item: name){
                String itemRoot = mapper.writeValueAsString(item);
                listUrls.add(itemRoot);
            }
        }
        return listUrls;

    }

    private void getUrlDtoFromArray(){

    }
    private Url getUrlFromUrlDto(UrlDto urlDto){
        Url url = new Url();
        url.setId(urlDto.getId());
        url.setUrl(urlDto.getUrl());
        url.setTargetUrl(urlDto.getLocation_url());
        return url;
    }
}
