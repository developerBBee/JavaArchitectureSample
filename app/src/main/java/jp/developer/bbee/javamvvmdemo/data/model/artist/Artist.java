package jp.developer.bbee.javamvvmdemo.data.model.artist;

import androidx.annotation.VisibleForTesting;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "popular_artists")
public class Artist {
    @PrimaryKey
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("popularity")
    public double popularity;
    @SerializedName("profile_path")
    public String profilePath;

    @VisibleForTesting
    public Artist(int id, String name, double popularity, String profilePath) {
        this.id = id;
        this.name = name;
        this.popularity = popularity;
        this.profilePath = profilePath;
    }
}