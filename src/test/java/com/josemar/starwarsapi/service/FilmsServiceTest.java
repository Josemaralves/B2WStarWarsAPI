package com.josemar.starwarsapi.service;

import com.josemar.starwarsapi.client.SwapiClientService;
import dev.swapi.vo.Film;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
@Import(SwapiClientService.class)
public class FilmsServiceTest {

    @Autowired
    private FilmsService filmsService;

    @Test
    void getFilmsByPlanetName(){

        Flux<Film> film = filmsService.getFilmsByPlanetName("Planet Invalid");

        StepVerifier.create(film)
                .expectNextCount(0)
                .verifyComplete();

    }

    @Test
    void getFilmsByPlanetNameInvalid(){
        Flux<Film> film = filmsService.getFilmsByPlanetName("Tatooine");

        StepVerifier.create(film)
                .expectNextCount(5)
                .verifyComplete();
    }
}
