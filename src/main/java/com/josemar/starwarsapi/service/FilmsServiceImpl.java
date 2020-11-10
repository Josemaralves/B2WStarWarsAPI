package com.josemar.starwarsapi.service;

import com.josemar.starwarsapi.client.SwapiClientService;
import dev.swapi.vo.Film;
import dev.swapi.vo.Planet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class FilmsServiceImpl implements FilmsService {

    @Autowired
    SwapiClientService service;

    @Override
    public Flux<Film> getFilmsByPlanetName(String planetName) {

        return service.getPlanetByPlanetName(planetName).flatMapMany(paginacao ->{
            Flux<Film> fluxFilmes = Flux.empty();

            for(Planet planet :  paginacao.getResults())
                for(String urlFilm : planet.getFilms())
                    fluxFilmes = Flux.merge(fluxFilmes, service.getMovieByFilmUrl(urlFilm));

            return fluxFilmes;
        });
    }
}
