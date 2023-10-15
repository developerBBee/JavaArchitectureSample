package jp.developer.bbee.javamvvmdemo.data.repository.source.local;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.tv.Tv;

public interface TvLocalDataSource {
    List<Tv> getTvs();
    void saveTvs(List<Tv> tvs);
    void deleteAllTvs();
}
