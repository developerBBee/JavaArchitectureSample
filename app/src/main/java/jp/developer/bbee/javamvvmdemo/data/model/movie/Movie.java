package jp.developer.bbee.javamvvmdemo.data.model.movie;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "popular_movies")
public class Movie {
    @PrimaryKey
    @SerializedName("id")
    public int id;
    @SerializedName("overview")
    public String overview;
    @SerializedName("popularity")
    public double popularity;
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("release_date")
    public String releaseDate;
    @SerializedName("title")
    public String title;
}