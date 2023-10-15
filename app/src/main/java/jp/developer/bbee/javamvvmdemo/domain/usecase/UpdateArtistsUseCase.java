package jp.developer.bbee.javamvvmdemo.domain.usecase;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.artist.Artist;
import jp.developer.bbee.javamvvmdemo.domain.repository.ArtistRepository;

public class UpdateArtistsUseCase {
    final private ArtistRepository repository;
    public UpdateArtistsUseCase(ArtistRepository repository) {
        this.repository = repository;
    }

    public void execute(List<Artist> artists) {
        repository.saveArtists(artists);
    }
}
