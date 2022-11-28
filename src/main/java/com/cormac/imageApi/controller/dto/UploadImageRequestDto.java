package com.cormac.imageApi.controller.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Builder
@Data
public class UploadImageRequestDto {
    private MultipartFile file;
    private String externalId;
    private String metadata;
}
