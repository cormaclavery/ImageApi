package com.cormac.imageApi.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, String> {
    Optional<ImageEntity> getByExternalId(String externalId);
}
