package com.markwebtechnologies.bulkurlshortner.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BulkListDto {
    private List<UrlDto> data;
}
