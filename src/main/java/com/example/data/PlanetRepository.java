package com.example.data;

import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import org.bson.types.ObjectId;

import java.util.Optional;

@MongoRepository
public interface PlanetRepository extends CrudRepository<Planet, ObjectId> {

    Optional<Planet> findByName(String name);
}
