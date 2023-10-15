package jp.developer.bbee.javamvvmdemo.data.repository.source.cache.impl;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.movie.Movie;
import jp.developer.bbee.javamvvmdemo.data.repository.source.cache.MovieCacheDataSource;

public class MovieCacheDataSourceImpl implements MovieCacheDataSource {
    private List<Movie> cache;

    @Override
    public List<Movie> getMovies() {
        return cache;
    }

    @Override
    public void setMovies(List<Movie> movies) {
        this.cache = movies;
    }

}
