package jp.developer.bbee.javamvvmdemo.domain.repository;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.movie.Movie;

public interface MovieRepository {
    List<Movie> getMovies();
    void saveMovies(List<Movie> movies);
}
