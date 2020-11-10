package com.josemar.starwarsapi.repository;

import com.josemar.starwarsapi.vo.PlanetVO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PlanetRepository extends ReactiveMongoRepository<PlanetVO, String> {

    Flux<PlanetVO> findAllByName(String name);

}
