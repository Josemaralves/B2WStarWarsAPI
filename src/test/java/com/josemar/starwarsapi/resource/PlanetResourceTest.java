package com.josemar.starwarsapi.resource;

import com.josemar.starwarsapi.client.SwapiClientService;
import com.josemar.starwarsapi.repository.PlanetRepository;
import com.josemar.starwarsapi.service.FilmsService;
import com.josemar.starwarsapi.service.PlanetServiceImpl;
import com.josemar.starwarsapi.vo.PlanetVO;
import dev.swapi.vo.Film;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@WebFluxTest(controllers = PlanetResource.class)
@ExtendWith(SpringExtension.class)
@Import(PlanetServiceImpl.class)
public class PlanetResourceTest {

    @MockBean
    private PlanetRepository planetRepository;

    @MockBean
    private SwapiClientService swapiClientService;

    @MockBean
    private FilmsService filmsService;

    @Autowired
    private WebTestClient webTestClient;

    private PlanetVO getPlanet(){
        PlanetVO planetVO = new PlanetVO();
        planetVO.setName("Teste");
        planetVO.setClime("Chuvoso");
        planetVO.setTerrain("Arenoso");
        planetVO.setFilms(List.of(new Film()));

        return planetVO;
    }

    @Test
    void create(){
        Mockito.when(planetRepository.save(getPlanet())).thenReturn(Mono.just(getPlanet()));

        webTestClient.post().uri("/planet")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(getPlanet()), PlanetVO.class)
                .exchange()
                .expectStatus().isCreated();

        Mockito.verify(planetRepository, Mockito.times(1)).save(getPlanet());
    }

    @Test
    void createInvalid(){
        PlanetVO planetVO = new PlanetVO();
        planetVO.setName("Teste");
        planetVO.setTerrain("Montanhoso");

        webTestClient.post().uri("/planet")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(planetVO), PlanetVO.class)
                .exchange()
                .expectStatus().isBadRequest();

    }

    @Test
    void find(){
        Mockito.when(planetRepository.findAll()).thenReturn(Flux.just(getPlanet()));
        Mockito.when(filmsService.getFilmsByPlanetName(getPlanet().getName())).thenReturn(Flux.just(new Film()));

        webTestClient.get().uri("/planet/search")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(PlanetVO.class).contains(getPlanet());

        Mockito.verify(planetRepository, Mockito.times(1)).findAll();
        Mockito.verify(filmsService, Mockito.times(1)).getFilmsByPlanetName(getPlanet().getName());
    }

    @Test
    void findByName(){

        Mockito.when(planetRepository.findAllByName(getPlanet().getName())).thenReturn(Flux.just(getPlanet()));

        webTestClient.get().uri(uriBuilder -> uriBuilder.path("/planet/search").queryParam("name", getPlanet().getName()).build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(PlanetVO.class).contains(getPlanet());
    }

    @Test
    void findByIdInvalid(){
        Mockito.when(planetRepository.findById("0")).thenReturn(Mono.just(getPlanet()));

        webTestClient.get().uri("/planet/{id}", "0")
                .header(HttpHeaders.ACCEPT,"application/json")
                .exchange()
                .expectStatus().isNotFound();

        Mockito.verify(planetRepository, Mockito.times(1)).findById("0");
    }
}
