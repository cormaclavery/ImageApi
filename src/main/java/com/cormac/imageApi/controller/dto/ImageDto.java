package com.cormac.imageApi.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class ImageDto {
    private String id;
    private String externalId;
    private Map<String, String> metaData;
    private String imageType;
    private byte[] imageBlob;
}
