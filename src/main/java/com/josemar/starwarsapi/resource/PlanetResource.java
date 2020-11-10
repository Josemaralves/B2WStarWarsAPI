package com.josemar.starwarsapi.resource;

import com.josemar.starwarsapi.service.PlanetService;
import com.josemar.starwarsapi.support.ResourceSupport;
import com.josemar.starwarsapi.vo.PlanetVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/planet")
public class PlanetResource extends ResourceSupport {

    @Autowired
    PlanetService planetService;

    @ApiOperation("Criar um novo Planeta")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Planeta Criado com Sucesso e Retorna o Planeta"),
            @ApiResponse(code = 400, message = "Erro na validação dos itens necessarios para Criação do Planeta")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PlanetVO> create(
            @ApiParam("Informaçoes do Planeta") @RequestBody @Valid PlanetVO planet
    ){
        return planetService.createPlanet(planet);
    }


    @ApiOperation("Procurar por Planetas")
    @ApiResponse(code = 200, message = "Retorna os planetas encontrados")
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public Flux<PlanetVO> find(
            @ApiParam("Nome do Planeta") @RequestParam(required = false) String name){
        return planetService.findPlanet(name);
    }


    @ApiOperation("Excluir um Planeta por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Planeta Excluido com sucesso"),
            @ApiResponse(code = 404, message = "ID do Planeta não encontrado")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @ApiParam("ID do Planeta") @PathVariable("id") String id){
        planetService.deletePlanet(id);
    }


    @ApiOperation("Procurar um Planeta por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Planeta Excluido com sucesso"),
            @ApiResponse(code = 404, message = "ID do Planeta não encontrado")
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PlanetVO> findById(
            @ApiParam("ID do Planeta") @PathVariable("id") String id){
        return planetService.findById(id);
    }


}
