package com.cormac.imageApi.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ImageRepositoryIT{

    @Autowired
    private ImageRepository imageRepository;

    @Test
    public void saveSucceeds() {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setId(UUID.randomUUID().toString());
        imageEntity.setExternalId("someExternalId");
        imageEntity.setImageBlob(new byte[]{1});
        imageEntity.setImageType("application/pdf");
        imageEntity.setMetaData("someMetaData");

        ImageEntity savedImage = imageRepository.save(imageEntity);
        assertEquals(savedImage.getExternalId(), imageEntity.getExternalId());
    }

    @Test
    public void getByExternalIdSucceeds() {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setId(UUID.randomUUID().toString());
        imageEntity.setExternalId("someExternalId");
        imageEntity.setImageBlob(new byte[]{1});
        imageEntity.setImageType("application/pdf");
        imageEntity.setMetaData("someMetaData");

        imageRepository.save(imageEntity);
        ImageEntity retrievedImage = imageRepository.getByExternalId(imageEntity.getExternalId()).get();
        assertEquals(retrievedImage.getExternalId(), imageEntity.getExternalId());
    }

    @Test
    public void getByExternalIdWithNoItemReturnsNull() {
        Optional<ImageEntity> retrievedImage = imageRepository.getByExternalId("simeId");
        assertEquals(retrievedImage.isPresent(), false);
    }

    @Test
    public void getByIdSucceeds() {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setId(UUID.randomUUID().toString());
        imageEntity.setExternalId("someExternalId");
        imageEntity.setImageBlob(new byte[]{1});
        imageEntity.setImageType("application/pdf");
        imageEntity.setMetaData("someMetaData");

        ImageEntity savedImage = imageRepository.save(imageEntity);
        ImageEntity retrievedImage = imageRepository.findById(savedImage.getId()).get();
        assertEquals(retrievedImage.getExternalId(), imageEntity.getExternalId());
    }



}