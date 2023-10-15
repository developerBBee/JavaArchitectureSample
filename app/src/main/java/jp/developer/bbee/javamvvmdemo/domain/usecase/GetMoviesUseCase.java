package jp.developer.bbee.javamvvmdemo.domain.usecase;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.movie.Movie;
import jp.developer.bbee.javamvvmdemo.domain.repository.MovieRepository;

public class GetMoviesUseCase {
    final private MovieRepository repository;
    public GetMoviesUseCase(MovieRepository repository) {
        this.repository = repository;
    }

    public List<Movie> execute() {
        return repository.getMovies();
    }
}
