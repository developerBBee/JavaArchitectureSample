package jp.developer.bbee.javamvvmdemo.data.repository.source.cache;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.movie.Movie;

public interface MovieCacheDataSource {
    List<Movie> getMovies();
    void setMovies(List<Movie> movies);
}
