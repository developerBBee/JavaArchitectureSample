package jp.developer.bbee.javamvvmdemo.data.repository.source.local.impl;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.db.dao.ArtistDao;
import jp.developer.bbee.javamvvmdemo.data.model.artist.Artist;
import jp.developer.bbee.javamvvmdemo.data.repository.source.local.ArtistLocalDataSource;

public class ArtistLocalDataSourceImpl implements ArtistLocalDataSource {
    final private ArtistDao dao;

    public ArtistLocalDataSourceImpl(ArtistDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Artist> getArtists() {
        return dao.getArtists();
    }

    @Override
    public void saveArtists(List<Artist> artists) {
        dao.saveArtists(artists);
    }

    @Override
    public void deleteAllArtists() {
        dao.deleteAllArtists();
    }
}
