package jp.developer.bbee.javamvvmdemo.domain.repository;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.tv.Tv;

public interface TvRepository {
    List<Tv> getTvs();
    void saveTvs(List<Tv> tvs);
}
