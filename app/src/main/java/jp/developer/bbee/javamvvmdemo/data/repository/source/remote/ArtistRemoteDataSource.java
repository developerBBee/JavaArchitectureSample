package jp.developer.bbee.javamvvmdemo.data.repository.source.remote;

import jp.developer.bbee.javamvvmdemo.data.model.artist.ArtistList;
import retrofit2.Call;

public interface ArtistRemoteDataSource {
    Call<ArtistList> getPopularArtists();
}
