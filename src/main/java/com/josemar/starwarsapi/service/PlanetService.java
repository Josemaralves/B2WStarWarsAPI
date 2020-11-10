package com.josemar.starwarsapi.service;

import com.josemar.starwarsapi.vo.PlanetVO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlanetService {

    Flux<PlanetVO> findPlanet(String nome);
    Mono<PlanetVO> createPlanet(PlanetVO planeta);
    void deletePlanet(String id);
    Mono<PlanetVO> findById(String id);

}
