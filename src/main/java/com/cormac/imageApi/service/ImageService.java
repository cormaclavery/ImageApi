package com.cormac.imageApi.service;

import com.cormac.imageApi.controller.dto.ImageDto;
import com.cormac.imageApi.controller.dto.UploadImageRequestDto;
import com.cormac.imageApi.controller.dto.UploadImageResponseDto;
import com.cormac.imageApi.entity.ImageEntity;
import com.cormac.imageApi.entity.ImageRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public ImageDto getImage(String id, String externalId){
        if (id != null){
            Optional<ImageEntity> image = imageRepository.findById(id);
            if (image.isPresent()){
                return image.get().toDto();
            }
        } else if (externalId != null){
            Optional<ImageEntity> image = imageRepository.getByExternalId(externalId);
            if (image.isPresent()){
                return image.get().toDto();
            }
        }

        return null;
    }

    public UploadImageResponseDto uploadImage(UploadImageRequestDto uploadImageRequestDto) throws Exception {
        if (!isValidFile(uploadImageRequestDto.getFile())){
            throw new Exception();
        }
        byte[] bytes = uploadImageRequestDto.getFile().getBytes();
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setImageBlob(bytes);
        imageEntity.setImageType(uploadImageRequestDto.getFile().getContentType());
        if (uploadImageRequestDto.getExternalId() != null){
            imageEntity.setExternalId(uploadImageRequestDto.getExternalId());
        }
        if (isValidMetaData(uploadImageRequestDto.getMetadata())){
            imageEntity.setMetaData(uploadImageRequestDto.getMetadata());
        }

        ImageEntity savedEntity = imageRepository.save(imageEntity);
        return UploadImageResponseDto
                .builder()
                .id(savedEntity.getId())
                .build();
    }

    public boolean deleteImage(String id, String externalId){
        if (id != null){
            Optional<ImageEntity> image = imageRepository.findById(id);
            if (image.isPresent()){
                imageRepository.delete(image.get());
                return true;
            }

        } else if (externalId != null){
            Optional<ImageEntity> image = imageRepository.getByExternalId(externalId);
            if (image.isPresent()){
                imageRepository.delete(image.get());
                return true;
            }
        }
        return false;
    }

    private boolean isValidMetaData(String json) {
        try {
            new Gson().fromJson(json, new TypeToken<HashMap<String, String>>() {}.getType());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean isValidFile(MultipartFile file) {
        if (file.isEmpty() || file.getSize() == 0){
            return false;
        }
        return Arrays.asList(new String[]{"application/pdf", "image/png", "image/jpeg", "image/svg"}).contains(file.getContentType());
    }

}
