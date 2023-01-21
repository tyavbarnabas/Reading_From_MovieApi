package com.codeafriac.webclientmovieapi.service;

import com.codeafriac.webclientmovieapi.model.MovieEvent;
import reactor.core.publisher.Flux;

public interface MovieEventService {
    Flux<MovieEvent>searchAMovieByEvent();
}
