package jp.developer.bbee.javamvvmdemo.data.model.artist;

import androidx.annotation.VisibleForTesting;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtistList {
    @SerializedName("results")
    public List<Artist> artists;

    @VisibleForTesting
    public ArtistList(List<Artist> artists) {
        this.artists = artists;
    }
}