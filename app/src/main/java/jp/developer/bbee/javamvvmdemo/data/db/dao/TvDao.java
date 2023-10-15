package jp.developer.bbee.javamvvmdemo.data.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.tv.Tv;

@Dao
public interface TvDao {
    @Insert
    void saveTvs(List<Tv> tvs);

    @Query("DELETE FROM popular_tvs")
    void deleteAllTvs();

    @Query("SELECT * FROM popular_tvs")
    List<Tv> getTvs();
}
