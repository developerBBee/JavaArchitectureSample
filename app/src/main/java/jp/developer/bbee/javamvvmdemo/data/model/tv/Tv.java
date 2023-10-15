package jp.developer.bbee.javamvvmdemo.data.model.tv;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "popular_tvs")
public class Tv {
    @SerializedName("first_air_date")
    public String firstAirDate;
    @PrimaryKey
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("overview")
    public String overview;
    @SerializedName("poster_path")
    public String posterPath;
}