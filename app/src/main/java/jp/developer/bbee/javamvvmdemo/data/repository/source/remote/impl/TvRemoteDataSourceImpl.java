package jp.developer.bbee.javamvvmdemo.data.repository.source.remote.impl;

import jp.developer.bbee.javamvvmdemo.data.api.TMDBService;
import jp.developer.bbee.javamvvmdemo.data.model.tv.TvList;
import jp.developer.bbee.javamvvmdemo.data.repository.source.remote.TvRemoteDataSource;
import retrofit2.Call;

public class TvRemoteDataSourceImpl implements TvRemoteDataSource {
    final private TMDBService service;
    final private String apiKey;

    public TvRemoteDataSourceImpl(TMDBService service, String apiKey) {
        this.service = service;
        this.apiKey = apiKey;
    }

    @Override
    public Call<TvList> getPopularTvs() {
        return service.getPopularTvs(apiKey);
    }
}
