package jp.developer.bbee.javamvvmdemo.data.repository.source.cache.impl;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.artist.Artist;
import jp.developer.bbee.javamvvmdemo.data.repository.source.cache.ArtistCacheDataSource;

public class ArtistCacheDataSourceImpl implements ArtistCacheDataSource {
    private List<Artist> cache;

    @Override
    public List<Artist> getArtists() {
        return cache;
    }

    @Override
    public void setArtists(List<Artist> artists) {
        this.cache = artists;
    }
}
