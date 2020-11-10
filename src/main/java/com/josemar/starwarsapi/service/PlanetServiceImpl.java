package com.josemar.starwarsapi.service;

import com.josemar.starwarsapi.client.SwapiClientService;
import com.josemar.starwarsapi.exception.PlanetNotFoundException;
import com.josemar.starwarsapi.repository.PlanetRepository;
import com.josemar.starwarsapi.vo.PlanetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlanetServiceImpl implements PlanetService {

    @Autowired
    PlanetRepository planetRepository;

    @Autowired
    SwapiClientService swapiClientService;

    @Autowired
    FilmsService filmsService;

    @Override
    public Flux<PlanetVO> findPlanet(String name)  {
        if(!StringUtils.isEmpty(name))
            return planetRepository.findAllByName(name).flatMap(planetVO ->
                    filmsService.getFilmsByPlanetName(planetVO.getName()).collectList().doOnNext(planetVO::setFilms).thenReturn(planetVO));
        else
            return planetRepository.findAll().flatMap(planetVO ->
                    filmsService.getFilmsByPlanetName(planetVO.getName()).collectList().doOnNext(planetVO::setFilms).thenReturn(planetVO));
    }

    @Override
    public Mono<PlanetVO> createPlanet(final PlanetVO planet) {
        return planetRepository.save(planet);
    }

    @Override
    public void deletePlanet(String id) {
        this.findById(id).flatMap(planetRepository::delete);
    }

    @Override
    public Mono<PlanetVO> findById(String id) {
        return planetRepository.findById(id).switchIfEmpty(Mono.error(PlanetNotFoundException::new)).flatMap(planetVO ->
            filmsService.getFilmsByPlanetName(planetVO.getName()).collectList().doOnNext(planetVO::setFilms).thenReturn(planetVO)
        );
    }
}
