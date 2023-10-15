package jp.developer.bbee.javamvvmdemo.data.api;

import com.google.gson.GsonBuilder;

import jp.developer.bbee.javamvvmdemo.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TMDBServiceBuilder {
    final static private String BASE_URL = BuildConfig.baseUrl;

    public static TMDBService getTMDBService() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build()
                .create(TMDBService.class);
    }
}
