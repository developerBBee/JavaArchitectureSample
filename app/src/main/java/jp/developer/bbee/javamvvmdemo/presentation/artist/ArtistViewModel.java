package jp.developer.bbee.javamvvmdemo.presentation.artist;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.developer.bbee.javamvvmdemo.data.model.artist.Artist;
import jp.developer.bbee.javamvvmdemo.data.model.artist.ArtistList;
import jp.developer.bbee.javamvvmdemo.domain.usecase.GetArtistsUseCase;
import jp.developer.bbee.javamvvmdemo.domain.usecase.UpdateArtistsUseCase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistViewModel extends ViewModel {
    private Fragment currentFragment;
    public Fragment getCurrentFragment() {
        return currentFragment;
    }
    public void setCurrentFragment(Fragment fragment) {
        this.currentFragment = fragment;
    }

    final private GetArtistsUseCase getArtists;
    final private UpdateArtistsUseCase updateArtists;
    private final MutableLiveData<List<Artist>> artistsLiveData = new MutableLiveData<>();
    public LiveData<List<Artist>> artists = artistsLiveData;

    final public Map<Integer, Boolean> checkedMap = new HashMap<>();
    private final MutableLiveData<Integer> checkedCountLiveData = new MutableLiveData<>(0);
    public LiveData<Integer> checkedCount = checkedCountLiveData;
    final public View.OnClickListener listener = v -> {
        int count = checkedCount.getValue() == null ? 0 : checkedCount.getValue();
        boolean isChecked = ((CheckBox) v).isChecked();
        if (isChecked) {
            setCheckedCountLiveData(count + 1);
        } else {
            setCheckedCountLiveData(count - 1);
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

    public void saveArtists(List<Artist> artists) {
        updateArtists.execute(artists);
    }

    @UiThread
    public void setCheckedCountLiveData(int count) {
        checkedCountLiveData.setValue(count);
    }

    @UiThread
    public void reset() {
        setArtistsLiveData(null);
        setCheckedCountLiveData(0);
        checkedMap.clear();
    }
}
