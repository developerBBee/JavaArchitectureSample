package jp.developer.bbee.javamvvmdemo.data.repository.artist;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.artist.Artist;
import jp.developer.bbee.javamvvmdemo.data.model.artist.ArtistList;
import jp.developer.bbee.javamvvmdemo.data.repository.source.remote.ArtistRemoteDataSource;
import jp.developer.bbee.javamvvmdemo.domain.repository.ArtistRepository;
import retrofit2.Callback;

public class FakeArtistRepository implements ArtistRepository {
    final private ArtistRemoteDataSource remote;
    private Callback<ArtistList> callback;

    public FakeArtistRepository(ArtistRemoteDataSource remote) {
        this.remote = remote;
    }

    @Override
    public void setCallback(Callback<ArtistList> callback) {
        this.callback = callback;
    }

    @Override
    public List<Artist> getArtists() {
        remote.getPopularArtists().enqueue(callback);
        return null;
    }

    @Override
    public void saveArtists(List<Artist> artists) {

    }
}
