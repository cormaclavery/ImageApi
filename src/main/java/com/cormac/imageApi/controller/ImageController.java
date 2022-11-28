package com.cormac.imageApi.controller;

import com.cormac.imageApi.controller.dto.DeleteImageResponseDto;
import com.cormac.imageApi.controller.dto.ImageDto;
import com.cormac.imageApi.controller.dto.UploadImageRequestDto;
import com.cormac.imageApi.controller.dto.UploadImageResponseDto;
import com.cormac.imageApi.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/images")
public class ImageController {

    @Autowired
    private ImageService imageService;


    @PostMapping
    ResponseEntity<UploadImageResponseDto> uploadImage(
            @ModelAttribute UploadImageRequestDto model
            ) throws Exception {
        // TODO add validators for file
        var dto = imageService.uploadImage(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping
    ResponseEntity<ImageDto> getImage(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String externalId
            ) {
        var dto = imageService.getImage(id, externalId);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping
    ResponseEntity<DeleteImageResponseDto> deleteImage(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String externalId
    ) {
        var deleted = imageService.deleteImage(id, externalId);
        return ResponseEntity.status(HttpStatus.OK).body(DeleteImageResponseDto.builder().deleted(deleted).build());
    }
}
