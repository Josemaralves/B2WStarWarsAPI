package com.josemar.starwarsapi.exception;

public class PlanetNotFoundException extends Exception {

    private static final String PLANET_NOT_FOUND_MESSAGE = "Planeta não encontrado";

    public PlanetNotFoundException() {
        super(PLANET_NOT_FOUND_MESSAGE);
    }
}
