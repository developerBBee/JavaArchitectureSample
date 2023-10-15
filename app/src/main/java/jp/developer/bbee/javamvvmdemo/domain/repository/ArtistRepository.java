package jp.developer.bbee.javamvvmdemo.domain.repository;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.artist.Artist;
import jp.developer.bbee.javamvvmdemo.data.model.artist.ArtistList;
import retrofit2.Callback;

public interface ArtistRepository {
    void setCallback(Callback<ArtistList> callback);
    List<Artist> getArtists();
    void saveArtists(List<Artist> artists);
}
