package jp.developer.bbee.javamvvmdemo.presentation.artist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import jp.developer.bbee.javamvvmdemo.BuildConfig;
import jp.developer.bbee.javamvvmdemo.R;
import jp.developer.bbee.javamvvmdemo.data.api.TMDBService;
import jp.developer.bbee.javamvvmdemo.data.api.TMDBServiceBuilder;
import jp.developer.bbee.javamvvmdemo.data.db.TMDBDatabase;
import jp.developer.bbee.javamvvmdemo.data.repository.ArtistRepositoryImpl;
import jp.developer.bbee.javamvvmdemo.data.repository.source.cache.ArtistCacheDataSource;
import jp.developer.bbee.javamvvmdemo.data.repository.source.cache.impl.ArtistCacheDataSourceImpl;
import jp.developer.bbee.javamvvmdemo.data.repository.source.local.ArtistLocalDataSource;
import jp.developer.bbee.javamvvmdemo.data.repository.source.local.impl.ArtistLocalDataSourceImpl;
import jp.developer.bbee.javamvvmdemo.data.repository.source.remote.ArtistRemoteDataSource;
import jp.developer.bbee.javamvvmdemo.data.repository.source.remote.impl.ArtistRemoteDataSourceImpl;
import jp.developer.bbee.javamvvmdemo.domain.repository.ArtistRepository;
import jp.developer.bbee.javamvvmdemo.domain.usecase.GetArtistsUseCase;
import jp.developer.bbee.javamvvmdemo.domain.usecase.UpdateArtistsUseCase;
import jp.developer.bbee.javamvvmdemo.MainActivity;
import jp.developer.bbee.javamvvmdemo.presentation.artist.fragment.ArtistTopFragment;

public class ArtistActivity extends AppCompatActivity {
//    private ActivityArtistBinding binding;
    private ArtistViewModel viewModel;
    public ArtistViewModel getViewModel() {
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = ActivityArtistBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
        setContentView(R.layout.activity_artist);

        TMDBService service = TMDBServiceBuilder.getTMDBService();
        TMDBDatabase db = TMDBDatabase.getInstance(this);

        ArtistRemoteDataSource remoteDataSource = new ArtistRemoteDataSourceImpl(service, BuildConfig.apiKey);
        ArtistLocalDataSource localDataSource = new ArtistLocalDataSourceImpl(db.getArtistDao());
        ArtistCacheDataSource cacheDataSource = new ArtistCacheDataSourceImpl();

        ArtistRepository repository = new ArtistRepositoryImpl(remoteDataSource, localDataSource, cacheDataSource);

        GetArtistsUseCase getArtists = new GetArtistsUseCase(repository);
        UpdateArtistsUseCase updateArtists = new UpdateArtistsUseCase(repository);

        ArtistViewModelFactory factory = new ArtistViewModelFactory(getArtists, updateArtists);
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, factory);
        viewModel = viewModelProvider.get(ArtistViewModel.class);
        Log.d("ArtistActivity", "viewModel: " + viewModel);

        if (viewModel.getCurrentFragment() == null) {
            ArtistTopFragment fragment = new ArtistTopFragment();
            transactionFragment(fragment, false);
        }
    }

    public void transactionFragment(Fragment fragment, boolean addStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        if (addStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
        viewModel.setCurrentFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            super.onBackPressed();
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}