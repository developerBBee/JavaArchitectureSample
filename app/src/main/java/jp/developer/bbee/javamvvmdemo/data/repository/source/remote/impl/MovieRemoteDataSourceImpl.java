package jp.developer.bbee.javamvvmdemo.data.repository.source.remote.impl;

import jp.developer.bbee.javamvvmdemo.data.api.TMDBService;
import jp.developer.bbee.javamvvmdemo.data.model.movie.MovieList;
import jp.developer.bbee.javamvvmdemo.data.repository.source.remote.MovieRemoteDataSource;
import retrofit2.Call;

public class MovieRemoteDataSourceImpl implements MovieRemoteDataSource {
    final private TMDBService service;
    final private String apiKey;

    public MovieRemoteDataSourceImpl(TMDBService service, String apiKey) {
        this.service = service;
        this.apiKey = apiKey;
    }

    @Override
    public Call<MovieList> getPopularMovies() {
        return service.getPopularMovies(apiKey);
    }
}
