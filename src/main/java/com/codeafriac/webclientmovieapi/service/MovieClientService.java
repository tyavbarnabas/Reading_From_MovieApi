package com.codeafriac.webclientmovieapi.service;

import com.codeafriac.webclientmovieapi.model.Movie;
import reactor.core.publisher.Mono;

public interface MovieClientService {

    Mono<Movie>searchAMovieByTitle(String apiKey,String movieTitle);
    Mono<Movie>searchAMovieId(String apiKey,String movieId);
}
