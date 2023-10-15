package jp.developer.bbee.javamvvmdemo.domain.usecase;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.artist.Artist;
import jp.developer.bbee.javamvvmdemo.data.model.artist.ArtistList;
import jp.developer.bbee.javamvvmdemo.domain.repository.ArtistRepository;
import retrofit2.Callback;

public class GetArtistsUseCase {
    final private ArtistRepository repository;
    public GetArtistsUseCase(ArtistRepository repository) {
        this.repository = repository;
    }

    public List<Artist> execute(Callback<ArtistList> callback) {
        repository.setCallback(callback);
        return repository.getArtists();
    }
}
