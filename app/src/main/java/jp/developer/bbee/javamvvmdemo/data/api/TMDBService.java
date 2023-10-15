package jp.developer.bbee.javamvvmdemo.data.api;

import jp.developer.bbee.javamvvmdemo.data.model.artist.ArtistList;
import jp.developer.bbee.javamvvmdemo.data.model.movie.MovieList;
import jp.developer.bbee.javamvvmdemo.data.model.tv.TvList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDBService {
    @GET("movie/popular")
    Call<MovieList> getPopularMovies(@Query("api_key") String apiKey);

    @GET("person/popular")
    Call<ArtistList> getPopularArtists(@Query("api_key") String apiKey);

    @GET("tv/popular")
    Call<TvList> getPopularTvs(@Query("api_key") String apiKey);
}