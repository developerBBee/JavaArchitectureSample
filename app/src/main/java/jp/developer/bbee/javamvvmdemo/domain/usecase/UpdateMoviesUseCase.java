package jp.developer.bbee.javamvvmdemo.domain.usecase;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.movie.Movie;
import jp.developer.bbee.javamvvmdemo.domain.repository.MovieRepository;

public class UpdateMoviesUseCase {
    final private MovieRepository repository;
    public UpdateMoviesUseCase(MovieRepository repository) {
        this.repository = repository;
    }

    public void execute(List<Movie> movies) {
        repository.saveMovies(movies);
    }
}
