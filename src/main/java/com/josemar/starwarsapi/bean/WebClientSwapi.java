package com.josemar.starwarsapi.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Component
public class WebClientSwapi {

    @Bean
    public WebClient webClientStarWarsPlanet(WebClient.Builder builder){
        return builder.baseUrl("https://swapi.dev/").build();
    }

    @Bean
    public WebClient webClientStarWarsFilm(WebClient.Builder builder){
        return builder.clientConnector(new ReactorClientHttpConnector(
                HttpClient.create().followRedirect(true)
        )).build();
    }

}
