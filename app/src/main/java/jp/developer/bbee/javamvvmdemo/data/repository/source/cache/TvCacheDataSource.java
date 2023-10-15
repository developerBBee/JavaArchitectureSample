package jp.developer.bbee.javamvvmdemo.data.repository.source.cache;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.tv.Tv;

public interface TvCacheDataSource {
    List<Tv> getTvs();
    void setTvs(List<Tv> tvs);
}
