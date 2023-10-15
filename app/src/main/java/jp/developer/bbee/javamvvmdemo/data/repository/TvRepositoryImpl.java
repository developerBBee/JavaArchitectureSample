package jp.developer.bbee.javamvvmdemo.data.repository;

import android.util.Log;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.tv.Tv;
import jp.developer.bbee.javamvvmdemo.data.model.tv.TvList;
import jp.developer.bbee.javamvvmdemo.data.repository.source.cache.TvCacheDataSource;
import jp.developer.bbee.javamvvmdemo.data.repository.source.local.TvLocalDataSource;
import jp.developer.bbee.javamvvmdemo.data.repository.source.remote.TvRemoteDataSource;
import jp.developer.bbee.javamvvmdemo.domain.repository.TvRepository;

public class TvRepositoryImpl implements TvRepository {
    final private TvRemoteDataSource remote;
    final private TvLocalDataSource local;
    final private TvCacheDataSource cache;

    public TvRepositoryImpl(TvRemoteDataSource remote, TvLocalDataSource local, TvCacheDataSource cache) {
        this.remote = remote;
        this.local = local;
        this.cache = cache;
    }

    @Override
    public List<Tv> getTvs() {
        return getTvsByCache();
    }

    @Override
    public void saveTvs(List<Tv> tvs) {
        local.saveTvs(tvs);
        cache.setTvs(tvs);
    }

    private List<Tv> getTvsByRemote() {
        try {
//            TvList body = remote.getPopularTvs().body();
//            if (body != null) {
//                return body.tvs;
//            }
        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null) {
                Log.i("MyTag", message);
            }
            e.printStackTrace();
        }
        return null;
    }

    private List<Tv> getTvsByLocal() {
        try {
            List<Tv> data = local.getTvs();
            if (data == null || data.size() == 0) {
                throw new Exception("Local tvs are null or empty.");
            }
            return data;
        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null) {
                Log.i("MyTag", message);
            }
            e.printStackTrace();
        }
        Log.d("MyTag", "Failed to get local data, try to get remote data.");
        List<Tv> data = getTvsByRemote();
        if (data != null) {
            local.saveTvs(data);
        }
        return data;
    }

    private List<Tv> getTvsByCache() {
        List<Tv> data = cache.getTvs();
        if (data == null) {
            data = getTvsByLocal();
            cache.setTvs(data);
        }
        return data;
    }
}
