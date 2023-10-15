package jp.developer.bbee.javamvvmdemo.presentation.artist.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.R;
import jp.developer.bbee.javamvvmdemo.data.model.artist.Artist;
import jp.developer.bbee.javamvvmdemo.presentation.artist.ArtistActivity;
import jp.developer.bbee.javamvvmdemo.presentation.artist.ArtistViewModel;

public class ArtistTopFragment extends Fragment {
    private ArtistViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_artist_top, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArtistActivity activity = (ArtistActivity) requireActivity();
        viewModel = activity.getViewModel();
        viewModel.reset();

        viewModel.artists.observe(activity, artists -> {
            if (artists == null || artists.isEmpty()) {
                if (!activity.isDestroyed()) {
                    Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            // artistsを取得できたらItems画面に遷移する
            activity.transactionFragment(new ArtistItemsFragment(), true);
        });

        view.findViewById(R.id.getButton).setOnClickListener(v -> getArtists());
    }

    private void getArtists() {
        new Thread(() -> {
            List<Artist> artists = viewModel.getArtists(); // blocking process

            // blocking process の後なので activity破棄に注意する
            ArtistActivity activity = (ArtistActivity) getActivity();
            if (artists != null && !artists.isEmpty() && activity != null && !activity.isDestroyed()) {
                activity.runOnUiThread(() -> viewModel.setArtistsLiveData(artists));
            }
        }).start();
    }
}