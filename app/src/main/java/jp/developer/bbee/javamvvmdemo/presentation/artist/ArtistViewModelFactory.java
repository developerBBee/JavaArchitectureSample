package jp.developer.bbee.javamvvmdemo.presentation.artist;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import jp.developer.bbee.javamvvmdemo.domain.usecase.GetArtistsUseCase;
import jp.developer.bbee.javamvvmdemo.domain.usecase.UpdateArtistsUseCase;

public class ArtistViewModelFactory implements ViewModelProvider.Factory {
    final private GetArtistsUseCase getArtistsUseCase;
    final private UpdateArtistsUseCase updateArtistsUseCase;

    public ArtistViewModelFactory(GetArtistsUseCase getArtists, UpdateArtistsUseCase updateArtists) {
        this.getArtistsUseCase = getArtists;
        this.updateArtistsUseCase = updateArtists;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ArtistViewModel.class)) {
            return (T) new ArtistViewModel(getArtistsUseCase, updateArtistsUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
