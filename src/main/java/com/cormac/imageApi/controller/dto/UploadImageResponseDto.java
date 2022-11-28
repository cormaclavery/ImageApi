package com.cormac.imageApi.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class UploadImageResponseDto {
    private String id;
}
