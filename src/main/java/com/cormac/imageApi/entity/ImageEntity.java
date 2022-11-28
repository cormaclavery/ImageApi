package com.cormac.imageApi.entity;

import com.cormac.imageApi.controller.dto.ImageDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.time.LocalDateTime;
import java.util.HashMap;

@Entity
@Table(name = "images")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = true, unique = true)
    private String externalId;

    @Column(nullable = true)
    private String metaData;

    @Column(nullable = false)
    private String imageType;
    @Column(nullable = false)
    @Lob
    private byte[] imageBlob;

    @Generated(GenerationTime.INSERT)
    private LocalDateTime timeCreated;

    @Generated(GenerationTime.ALWAYS)
    private LocalDateTime timeUpdated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public byte[] getImageBlob() {
        return imageBlob;
    }

    public void setImageBlob(byte[] imageBlob) {
        this.imageBlob = imageBlob;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public LocalDateTime getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(LocalDateTime timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
    public ImageDto toDto(){
        var imageDtoBuilder  = ImageDto.builder()
                .externalId(this.getExternalId())
                .id(this.getId())
                .imageBlob(this.getImageBlob())
                .imageType(this.getImageType());
        if (this.getMetaData() != null){
            imageDtoBuilder.metaData(new Gson().fromJson(this.metaData, new TypeToken<HashMap<String, String>>() {}.getType()));
        }
        return imageDtoBuilder.build();
    }
}
