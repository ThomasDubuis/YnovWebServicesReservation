package com.tdubuis.reservationapp.service.movie;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MovieApp", url = "${movie-app.url}")
public interface MovieService {

    @GetMapping(value = "movies/{uid}")
    ResponseEntity<MovieResponse> getMovieById(@PathVariable String uid);
}
