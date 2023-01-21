package com.codeafriac.webclientmovieapi.serviceImpl;

import com.codeafriac.webclientmovieapi.model.Movie;
import com.codeafriac.webclientmovieapi.service.MovieClientService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
@Data
public class MovieClientServiceImpl implements MovieClientService {
    private final WebClient webClient;
    private static final String OMDB_MINE_TYPE = "application/json";
    private static final String OMDB_API_BASE_URL = "https://omdbapi.com";
    private static final String OMDB_USER_AGENT = "spring boot 5 webclient";
    private static final Logger logger = LoggerFactory.getLogger(MovieClientService.class);


    public MovieClientServiceImpl(){

        this.webClient = WebClient.builder()
                .baseUrl(OMDB_API_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, OMDB_MINE_TYPE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, OMDB_MINE_TYPE)
                .build();
    }

    @Override
    public Mono<Movie> searchAMovieByTitle(String apiKey,String movieTitle) {
        return webClient
                .post()
                .uri("/?apikey="+apiKey + "&t=t"+movieTitle)
                .retrieve()
                .bodyToMono(Movie.class);

    }

    @Override
    public Mono<Movie> searchAMovieId(String apiKey,String movieId) {
        return webClient
                .post()
                .uri("/?apikey="+apiKey+"&t=t"+movieId)
                .retrieve()
                .bodyToMono(Movie.class);
    }

}
