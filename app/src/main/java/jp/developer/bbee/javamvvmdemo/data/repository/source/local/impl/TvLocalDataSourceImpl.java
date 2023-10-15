package jp.developer.bbee.javamvvmdemo.data.repository.source.local.impl;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.db.dao.TvDao;
import jp.developer.bbee.javamvvmdemo.data.model.tv.Tv;
import jp.developer.bbee.javamvvmdemo.data.repository.source.local.TvLocalDataSource;

public class TvLocalDataSourceImpl implements TvLocalDataSource {
    final private TvDao dao;

    TvLocalDataSourceImpl(TvDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Tv> getTvs() {
        return dao.getTvs();
    }

    @Override
    public void saveTvs(List<Tv> tvs) {
        dao.saveTvs(tvs);
    }

    @Override
    public void deleteAllTvs() {
        dao.deleteAllTvs();
    }
}
