package jp.developer.bbee.javamvvmdemo.presentation.artist.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import jp.developer.bbee.javamvvmdemo.R;
import jp.developer.bbee.javamvvmdemo.data.model.artist.Artist;
import jp.developer.bbee.javamvvmdemo.presentation.artist.ArtistActivity;
import jp.developer.bbee.javamvvmdemo.presentation.artist.ArtistViewModel;
import jp.developer.bbee.javamvvmdemo.presentation.artist.adapter.ArtistAdapter;

public class ArtistItemsFragment extends Fragment {
    private ArtistViewModel viewModel;
    private Button okButton;

    public ArtistItemsFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_artist_items, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArtistActivity activity = (ArtistActivity) requireActivity();
        viewModel = activity.getViewModel();
        ArtistAdapter adapter = new ArtistAdapter(viewModel);
        adapter.setCheckBoxClickListener(viewModel.listener);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        Button cancelButton = view.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(v -> activity.onBackPressed());

        okButton = view.findViewById(R.id.okButton);
        okButton.setOnClickListener(v -> {
            Toast.makeText(activity, "OK", Toast.LENGTH_SHORT).show();
        });

        viewModel.checkedCount.observe(getViewLifecycleOwner(), count -> {
            List<Artist> artists = viewModel.artists.getValue();
            if (artists == null) {
                throw new IllegalStateException("artists is null.");
            }

            TextView itemCounter = view.findViewById(R.id.itemCounter);
            String text = String.format(Locale.getDefault(), "%d / %d", count, artists.size());
            itemCounter.setText(text);

            okButton.setEnabled(count == artists.size());
        });
    }
}