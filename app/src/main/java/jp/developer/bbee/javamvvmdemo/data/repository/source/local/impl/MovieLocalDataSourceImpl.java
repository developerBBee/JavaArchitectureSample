package jp.developer.bbee.javamvvmdemo.data.repository.source.local.impl;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.db.dao.MovieDao;
import jp.developer.bbee.javamvvmdemo.data.model.movie.Movie;
import jp.developer.bbee.javamvvmdemo.data.repository.source.local.MovieLocalDataSource;

public class MovieLocalDataSourceImpl implements MovieLocalDataSource {
    final private MovieDao dao;

    public MovieLocalDataSourceImpl(MovieDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Movie> getMovies() {
        return dao.getMovies();
    }

    @Override
    public void saveMovies(List<Movie> movies) {
        dao.saveMovies(movies);
    }

    @Override
    public void deleteAllMovies() {
        dao.deleteAllMovies();
    }
}
