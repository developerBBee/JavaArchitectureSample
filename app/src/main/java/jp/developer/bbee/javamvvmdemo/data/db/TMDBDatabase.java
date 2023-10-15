package jp.developer.bbee.javamvvmdemo.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import jp.developer.bbee.javamvvmdemo.data.db.dao.ArtistDao;
import jp.developer.bbee.javamvvmdemo.data.db.dao.MovieDao;
import jp.developer.bbee.javamvvmdemo.data.db.dao.TvDao;
import jp.developer.bbee.javamvvmdemo.data.model.artist.Artist;
import jp.developer.bbee.javamvvmdemo.data.model.movie.Movie;
import jp.developer.bbee.javamvvmdemo.data.model.tv.Tv;
import kotlin.jvm.Volatile;

@Database(
        entities = {Movie.class, Artist.class, Tv.class},
        version = 1,
        exportSchema = false
)
abstract public class TMDBDatabase extends RoomDatabase {
    abstract public MovieDao getMovieDao();
    abstract public ArtistDao getArtistDao();
    abstract public TvDao getTvDao();

    private static TMDBDatabase INSTANCE = null;
    private static final Object sLock = new Object();

    public static TMDBDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                        context, TMDBDatabase.class, "tmdb_database").build();
            }
        }
        return INSTANCE;
    }
}
