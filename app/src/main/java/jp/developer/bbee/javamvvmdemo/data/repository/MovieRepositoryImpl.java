package jp.developer.bbee.javamvvmdemo.data.repository;

import android.util.Log;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.movie.Movie;
import jp.developer.bbee.javamvvmdemo.data.model.movie.MovieList;
import jp.developer.bbee.javamvvmdemo.data.repository.source.cache.MovieCacheDataSource;
import jp.developer.bbee.javamvvmdemo.data.repository.source.local.MovieLocalDataSource;
import jp.developer.bbee.javamvvmdemo.data.repository.source.remote.MovieRemoteDataSource;
import jp.developer.bbee.javamvvmdemo.domain.repository.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {
    final private MovieRemoteDataSource remote;
    final private MovieLocalDataSource local;
    final private MovieCacheDataSource cache;

    public MovieRepositoryImpl(MovieRemoteDataSource remote, MovieLocalDataSource local, MovieCacheDataSource cache) {
        this.remote = remote;
        this.local = local;
        this.cache = cache;
    }

    @Override
    public List<Movie> getMovies() {
        return getMoviesByCache();
    }

    @Override
    public void saveMovies(List<Movie> movies) {
        local.saveMovies(movies);
        cache.setMovies(movies);
    }

    private List<Movie> getMoviesByRemote() {
        try {
//            MovieList body = remote.getPopularMovies().body();
//            if (body != null) {
//                return body.movies;
//            }
        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null) {
                Log.i("MyTag", message);
            }
            e.printStackTrace();
        }
        return null;
    }

    private List<Movie> getMoviesByLocal() {
        try {
            List<Movie> data = local.getMovies();
            if (data == null || data.size() == 0) {
                throw new Exception("Local movies are null or empty.");
            }
            return data;
        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null) {
                Log.i("MyTag", message);
            }
            e.printStackTrace();
        }
        Log.d("MyTag", "Failed to get local data, try to get remote data.");
        List<Movie> data = getMoviesByRemote();
        if (data != null) {
            local.saveMovies(data);
        }
        return data;
    }

    private List<Movie> getMoviesByCache() {
        List<Movie> data = cache.getMovies();
        if (data == null) {
            data = getMoviesByLocal();
            cache.setMovies(data);
        }
        return data;
    }
}
