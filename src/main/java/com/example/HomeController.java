package com.example;

import com.example.data.Planet;
import com.example.data.PlanetRepository;
import com.example.data.Views;
import com.fasterxml.jackson.annotation.JsonView;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

import java.util.Map;
import java.util.Optional;

@Controller
public class HomeController {

    private final PlanetRepository planetRepository;

    @Inject
    public HomeController(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    @Get
    public Map<String, String> hello(){
        return Map.of("greeting", "Hello world");
    }

    @Get("/all/{name}")
    public Optional<Planet> getAllPlanetData(String name){
        return this.planetRepository.findByName(name);
    }

    @Get("/public/{name}")
    @JsonView(Views.Public.class)
    public Optional<Planet> getPublicPlanetData(String name){
        return this.planetRepository.findByName(name);
    }

    @Get("/create/{name}")
    public Planet createPlanet(String name){
        Planet planet = new Planet();
        planet.setName(name);
        planet.setInternalName("Internal " + name);
        return this.planetRepository.save(planet);
    }
}
