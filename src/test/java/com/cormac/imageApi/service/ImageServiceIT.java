package com.cormac.imageApi.service;

import com.cormac.imageApi.controller.dto.UploadImageRequestDto;
import com.cormac.imageApi.entity.ImageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ImageServiceIT {
    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageRepository imageRepository;

    @Test
    public void uploadImageSucceedsPdf() throws Exception {
        // given an upload image request and an uploaded file
        URL url = Thread.currentThread().getContextClassLoader().getResource("images/pdf-test.pdf");
        File file = new File(url.getPath());
        MultipartFile multipartFile = new MockMultipartFile(
                "file", file.getName(), "application/pdf", file.getPath().getBytes()
        );
        UploadImageRequestDto uploadImageRequestDto = UploadImageRequestDto.builder()
                .file(multipartFile)
                .externalId("someExternalId")
                .metadata("\"key\", \"value\"")
                .build();


        var imageResponse = imageService.uploadImage(uploadImageRequestDto);
        assertNotEquals(imageResponse.getId(), null);
        assertNotEquals(imageResponse.getId(), "");
        System.out.println(imageResponse.getId());
    }

    @Test
    public void deleteImageSucceeds() throws Exception {
        // given an upload image request and an uploaded file
        URL url = Thread.currentThread().getContextClassLoader().getResource("images/pdf-test.pdf");
        File file = new File(url.getPath());
        MultipartFile multipartFile = new MockMultipartFile(
                "file", file.getName(), "application/pdf", file.getPath().getBytes()
        );
        UploadImageRequestDto uploadImageRequestDto = UploadImageRequestDto.builder()
                .file(multipartFile)
                .externalId("someExternalId")
                .metadata("\"key\", \"value\"")
                .build();


        var imageResponse = imageService.uploadImage(uploadImageRequestDto);
        var imageDeleted = imageService.deleteImage(imageResponse.getId(), null);
        assertEquals(imageDeleted, true);
    }

    @Test
    public void noImageToDeleteReturnsFalse() throws IOException {
        var imageDeleted = imageService.deleteImage("someId", null);
        assertEquals(imageDeleted, false);
    }

    @Test
    public void getImageSucceeds() throws Exception {
        // given an upload image request and an uploaded file
        URL url = Thread.currentThread().getContextClassLoader().getResource("images/pdf-test.pdf");
        File file = new File(url.getPath());
        MultipartFile multipartFile = new MockMultipartFile(
                "file", file.getName(), "application/pdf", file.getPath().getBytes()
        );
        UploadImageRequestDto uploadImageRequestDto = UploadImageRequestDto.builder()
                .file(multipartFile)
                .externalId("someExternalId")
                .metadata("\"key\", \"value\"")
                .build();


        var uploadedImage = imageService.uploadImage(uploadImageRequestDto);
        var retrievedImage = imageRepository.findById(uploadedImage.getId());
        var imageDto = imageService.getImage(uploadedImage.getId(), null);
        assertEquals(imageDto, retrievedImage.get().toDto());
        imageDto = imageService.getImage(null, "someExternalId");
        assertEquals(imageDto, retrievedImage.get().toDto());
    }




}