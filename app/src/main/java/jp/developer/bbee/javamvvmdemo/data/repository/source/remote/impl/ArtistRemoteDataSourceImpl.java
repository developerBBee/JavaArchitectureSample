package jp.developer.bbee.javamvvmdemo.data.repository.source.remote.impl;

import jp.developer.bbee.javamvvmdemo.data.api.TMDBService;
import jp.developer.bbee.javamvvmdemo.data.model.artist.ArtistList;
import jp.developer.bbee.javamvvmdemo.data.repository.source.remote.ArtistRemoteDataSource;
import retrofit2.Call;

public class ArtistRemoteDataSourceImpl implements ArtistRemoteDataSource {
    final private TMDBService service;
    final private String apiKey;

    public ArtistRemoteDataSourceImpl(TMDBService service, String apiKey) {
        this.service = service;
        this.apiKey = apiKey;
    }

    @Override
    public Call<ArtistList> getPopularArtists() {
        return service.getPopularArtists(apiKey);
    }
}
