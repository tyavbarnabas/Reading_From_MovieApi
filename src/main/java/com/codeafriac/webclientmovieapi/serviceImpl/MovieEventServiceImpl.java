package com.codeafriac.webclientmovieapi.serviceImpl;


import com.codeafriac.webclientmovieapi.model.Movie;
import com.codeafriac.webclientmovieapi.model.MovieEvent;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class MovieEventServiceImpl implements com.codeafriac.webclientmovieapi.service.MovieEventService {


    private static final String API_MINE_TYPE = "application/json";
    private static final String API_BASE_URL = "https://localhost9666";
    private static final String USER_AGENT = "spring boot 5 webclient";
    private static final Logger logger = LoggerFactory.getLogger(Movie.class);

    private final WebClient webClient;

    public MovieEventServiceImpl(){

        this.webClient= WebClient.builder()
                .baseUrl(API_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, API_MINE_TYPE)
                .defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT)
                .build();
    }

    @Override
    public Flux<MovieEvent> searchAMovieByEvent() {
      return webClient
              .get()
              .uri("/api/v1/movies/events")
              .exchange()
              .flatMapMany(clientResponse -> clientResponse.bodyToFlux(MovieEvent.class));
    }
}
