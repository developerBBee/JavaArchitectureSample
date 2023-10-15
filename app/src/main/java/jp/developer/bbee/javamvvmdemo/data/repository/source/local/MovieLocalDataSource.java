package jp.developer.bbee.javamvvmdemo.data.repository.source.local;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.movie.Movie;

public interface MovieLocalDataSource {
    List<Movie> getMovies();
    void saveMovies(List<Movie> movies);
    void deleteAllMovies();

}
