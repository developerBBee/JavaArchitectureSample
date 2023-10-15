package jp.developer.bbee.javamvvmdemo.data.repository.source.remote;

import jp.developer.bbee.javamvvmdemo.data.model.movie.MovieList;
import retrofit2.Call;

public interface MovieRemoteDataSource {
    Call<MovieList> getPopularMovies();
}
