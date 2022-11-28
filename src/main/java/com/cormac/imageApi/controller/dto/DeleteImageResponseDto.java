package com.cormac.imageApi.controller.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DeleteImageResponseDto {
    private boolean deleted;
}
