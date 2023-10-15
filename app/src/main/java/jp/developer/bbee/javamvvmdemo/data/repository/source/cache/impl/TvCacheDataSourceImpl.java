package jp.developer.bbee.javamvvmdemo.data.repository.source.cache.impl;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.tv.Tv;
import jp.developer.bbee.javamvvmdemo.data.repository.source.cache.TvCacheDataSource;

public class TvCacheDataSourceImpl implements TvCacheDataSource {
    private List<Tv> cache;

    @Override
    public List<Tv> getTvs() {
        return cache;
    }

    @Override
    public void setTvs(List<Tv> tvs) {
        this.cache = tvs;
    }
}
