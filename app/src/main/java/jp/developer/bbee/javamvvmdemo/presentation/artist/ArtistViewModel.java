package jp.developer.bbee.javamvvmdemo.presentation.artist;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.artist.Artist;
import jp.developer.bbee.javamvvmdemo.data.model.artist.ArtistList;
import jp.developer.bbee.javamvvmdemo.domain.usecase.GetArtistsUseCase;
import jp.developer.bbee.javamvvmdemo.domain.usecase.UpdateArtistsUseCase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistViewModel extends ViewModel {
    final private GetArtistsUseCase getArtists;
    final private UpdateArtistsUseCase updateArtists;
    private final MutableLiveData<List<Artist>> artistsLiveData = new MutableLiveData<>();
    public LiveData<List<Artist>> artists = artistsLiveData;

    private final MutableLiveData<Integer> checkedCountLiveData = new MutableLiveData<>(0);
    public LiveData<Integer> checkedCount = checkedCountLiveData;
    final public View.OnClickListener listener = v -> {
        int count = checkedCount.getValue() == null ? 0 : checkedCount.getValue();
        boolean isChecked = ((CheckBox) v).isChecked();
        if (isChecked) {
            checkedCountLiveData.setValue(count + 1);
        } else {
            checkedCountLiveData.setValue(count - 1);
        }
    };

    final private Callback<ArtistList> callback = new Callback<ArtistList>() {
        @Override
        public void onResponse(@NonNull Call<ArtistList> call, Response<ArtistList> response) {
            ArtistList body = response.body();
            if (response.isSuccessful() && body != null) {
                List<Artist> artists = body.artists;
                setArtistsLiveData(artists);
            }
        }

        @Override
        public void onFailure(@NonNull Call<ArtistList> call, Throwable t) {
            if (t.getMessage() != null) {
                Log.i("MyTag", t.getMessage());
            }
        }
    };

    public ArtistViewModel(GetArtistsUseCase getArtists, UpdateArtistsUseCase updateArtists) {
        this.getArtists = getArtists;
        this.updateArtists = updateArtists;
    }

    @WorkerThread
    public List<Artist> getArtists() {
        return getArtists.execute(callback);
    }

    @UiThread
    public void setArtistsLiveData(List<Artist> artists) {
        artistsLiveData.setValue(artists);
    }

    public void setArtists(List<Artist> artists) {
        updateArtists.execute(artists);
    }

    public void reset() {
        setArtistsLiveData(null);
    }
}
