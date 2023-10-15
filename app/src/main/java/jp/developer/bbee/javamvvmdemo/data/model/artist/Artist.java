package jp.developer.bbee.javamvvmdemo.data.model.artist;

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
}