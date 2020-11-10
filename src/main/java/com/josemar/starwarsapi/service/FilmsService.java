package com.josemar.starwarsapi.service;

import dev.swapi.vo.Film;
import reactor.core.publisher.Flux;

public interface FilmsService {

    Flux<Film> getFilmsByPlanetName(String planetName);

}
