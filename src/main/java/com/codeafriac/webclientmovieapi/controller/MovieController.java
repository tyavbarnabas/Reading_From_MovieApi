package com.codeafriac.webclientmovieapi.controller;


import com.codeafriac.webclientmovieapi.model.Movie;
import com.codeafriac.webclientmovieapi.model.MovieEvent;
import com.codeafriac.webclientmovieapi.service.MovieClientService;
import com.codeafriac.webclientmovieapi.service.MovieEventService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.core.env.Environment;

import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class MovieController {

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    private final MovieClientService movieClientService;
    private final MovieEventService movieEventService;
    private Environment environment;



    @GetMapping("/movie/title/{name}")
    public Mono<Movie> getMovieByTitle(@PathVariable String name){
        String apiKey =environment.getProperty("app.api.key");
        return movieClientService.searchAMovieByTitle(apiKey,name);
    }

    @GetMapping("/movies/id/{imdbId}")
    public Mono<Movie>getMovieById(@PathVariable("imdbId") String imdbId){
        return movieClientService.searchAMovieId(environment.getProperty("app.api.key"), imdbId);
    }

    @GetMapping(value = "/movies/events",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MovieEvent> getEvents() {
        return movieEventService.searchAMovieByEvent();
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<String> handleWebClientResponseException(WebClientResponseException ex) {
        logger.error("Error from WebClient - Status {}, Body {}", ex.getRawStatusCode(),
                ex.getResponseBodyAsString(), ex);
        return ResponseEntity.status(ex.getRawStatusCode()).body(ex.getResponseBodyAsString());
    }


}
