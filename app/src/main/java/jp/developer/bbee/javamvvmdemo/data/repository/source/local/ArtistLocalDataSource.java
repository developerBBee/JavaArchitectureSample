package jp.developer.bbee.javamvvmdemo.data.repository.source.local;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.artist.Artist;

public interface ArtistLocalDataSource {
    List<Artist> getArtists();
    void saveArtists(List<Artist> artists);
    void deleteAllArtists();

}
