package jp.developer.bbee.javamvvmdemo.data.repository.source.cache;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.artist.Artist;

public interface ArtistCacheDataSource {
    List<Artist> getArtists();
    void setArtists(List<Artist> artists);
}
