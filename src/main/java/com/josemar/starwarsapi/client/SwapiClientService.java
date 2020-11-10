package com.josemar.starwarsapi.client;

import dev.swapi.vo.Film;
import dev.swapi.vo.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SwapiClientService {

    @Autowired
    private WebClient webClientStarWarsPlanet;

    @Autowired
    private WebClient webClientStarWarsFilm;

    public Mono<Pagination> getPlanetByPlanetName(String namePlanet){
       return webClientStarWarsPlanet.get().uri("/api/planets/?search={namePlanet}", namePlanet)
                .retrieve().bodyToMono(Pagination.class);
    }

    public Mono<Film> getMovieByFilmUrl(String filmUrl){
        return webClientStarWarsFilm.get().uri(filmUrl).accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(Film.class);
    }

}
