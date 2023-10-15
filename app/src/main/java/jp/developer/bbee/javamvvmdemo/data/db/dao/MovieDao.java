package jp.developer.bbee.javamvvmdemo.data.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.movie.Movie;

@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMovies(List<Movie> movies);

    @Query("DELETE FROM popular_movies")
    void deleteAllMovies();

    @Query("SELECT * FROM popular_movies")
    List<Movie> getMovies();
}
