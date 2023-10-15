package jp.developer.bbee.javamvvmdemo.domain.usecase;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.tv.Tv;
import jp.developer.bbee.javamvvmdemo.domain.repository.TvRepository;

public class UpdateTvsUseCase {
    final private TvRepository repository;
    public UpdateTvsUseCase(TvRepository repository) {
        this.repository = repository;
    }

    public void execute(List<Tv> tvs) {
        repository.saveTvs(tvs);
    }
}
