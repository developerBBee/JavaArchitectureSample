package jp.developer.bbee.javamvvmdemo.data.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.artist.Artist;

@Dao
public interface ArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveArtists(List<Artist> artists);

    @Query("DELETE FROM popular_artists")
    void deleteAllArtists();

    @Query("SELECT * FROM popular_artists")
    List<Artist> getArtists();
}
