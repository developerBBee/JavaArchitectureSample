package jp.developer.bbee.javamvvmdemo.domain.usecase;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.tv.Tv;
import jp.developer.bbee.javamvvmdemo.domain.repository.TvRepository;

public class GetTvsUseCase {
    final private TvRepository repository;
    public GetTvsUseCase(TvRepository repository) {
        this.repository = repository;
    }

    public List<Tv> execute() {
        return repository.getTvs();
    }
}
