package jp.developer.bbee.javamvvmdemo.data.model.tv;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvList {
    @SerializedName("results")
    public List<Tv> tvs;
}