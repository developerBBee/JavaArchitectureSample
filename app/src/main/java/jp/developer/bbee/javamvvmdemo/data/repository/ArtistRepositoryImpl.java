package jp.developer.bbee.javamvvmdemo.data.repository;

import android.util.Log;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.artist.Artist;
import jp.developer.bbee.javamvvmdemo.data.model.artist.ArtistList;
import jp.developer.bbee.javamvvmdemo.data.repository.source.cache.ArtistCacheDataSource;
import jp.developer.bbee.javamvvmdemo.data.repository.source.local.ArtistLocalDataSource;
import jp.developer.bbee.javamvvmdemo.data.repository.source.remote.ArtistRemoteDataSource;
import jp.developer.bbee.javamvvmdemo.domain.repository.ArtistRepository;
import retrofit2.Callback;

public class ArtistRepositoryImpl implements ArtistRepository {
    final private ArtistRemoteDataSource remote;
    final private ArtistLocalDataSource local;
    final private ArtistCacheDataSource cache;
    private Callback<ArtistList> callback;

    public ArtistRepositoryImpl(ArtistRemoteDataSource remote, ArtistLocalDataSource local, ArtistCacheDataSource cache) {
        this.remote = remote;
        this.local = local;
        this.cache = cache;
    }

    @Override
    public void setCallback(Callback<ArtistList> callback) {
        this.callback = callback;
    }

    @Override
    public List<Artist> getArtists() {
        return getArtistsByCache();
    }

    @Override
    public void saveArtists(List<Artist> artists) {
        local.saveArtists(artists);
        cache.setArtists(artists);
    }

    private List<Artist> getArtistsByRemote() {
        try {
            remote.getPopularArtists().enqueue(callback);
        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null) {
                Log.i("MyTag", message);
            }
            e.printStackTrace();
        }
        return null;
    }

    private List<Artist> getArtistsByLocal() {
        try {
            List<Artist> data = local.getArtists();
            if (data == null || data.size() == 0) {
                throw new Exception("Local artists are null or empty.");
            }
            return data;
        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null) {
                Log.i("MyTag", message);
            }
            e.printStackTrace();
        }
        Log.d("MyTag", "Failed to get local data, try to get remote data.");
        List<Artist> data = getArtistsByRemote();
        if (data != null) {
            local.saveArtists(data);
        }
        return data;
    }

    private List<Artist> getArtistsByCache() {
        List<Artist> data = cache.getArtists();
        if (data == null) {
            data = getArtistsByLocal();
            cache.setArtists(data);
        }
        return data;
    }
}
