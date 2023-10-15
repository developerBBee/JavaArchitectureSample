package jp.developer.bbee.javamvvmdemo.data.repository.source.remote;

import jp.developer.bbee.javamvvmdemo.data.model.tv.TvList;
import retrofit2.Call;

public interface TvRemoteDataSource {
    Call<TvList> getPopularTvs();
}
