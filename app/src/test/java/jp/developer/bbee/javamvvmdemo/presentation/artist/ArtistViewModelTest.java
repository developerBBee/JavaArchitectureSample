package jp.developer.bbee.javamvvmdemo.presentation.artist;

import static com.google.common.truth.Truth.assertThat;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.developer.bbee.javamvvmdemo.data.model.artist.Artist;
import jp.developer.bbee.javamvvmdemo.data.model.artist.ArtistList;
import jp.developer.bbee.javamvvmdemo.data.repository.artist.FakeArtistRepository;
import jp.developer.bbee.javamvvmdemo.data.repository.source.remote.ArtistRemoteDataSource;
import jp.developer.bbee.javamvvmdemo.domain.repository.ArtistRepository;
import jp.developer.bbee.javamvvmdemo.domain.usecase.GetArtistsUseCase;
import jp.developer.bbee.javamvvmdemo.domain.usecase.UpdateArtistsUseCase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("unchecked")
@RunWith(AndroidJUnit4.class)
public class ArtistViewModelTest {
    private ArtistViewModel viewModel;
    private ArtistRemoteDataSource remote;
    private Call<ArtistList> call;
    private ArtistRepository repository;

    Artist artist1 = new Artist(1, "name1", 1.0, "profilePath1");
    Artist artist2 = new Artist(2, "name2", 2.0, "profilePath2");
    Artist artist3 = new Artist(3, "name3", 3.0, "profilePath3");

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        // remoteモックオブジェクトのgetPopularArtistsメソッドが呼ばれたら、callモックオブジェクトを返す
        remote = Mockito.mock(ArtistRemoteDataSource.class);
        call = Mockito.mock(Call.class);
        Mockito.when(remote.getPopularArtists()).thenReturn(call);

        repository = new FakeArtistRepository(remote);

        GetArtistsUseCase getUseCase = new GetArtistsUseCase(repository);
        UpdateArtistsUseCase updateUseCase = new UpdateArtistsUseCase(repository);

        viewModel = new ArtistViewModel(getUseCase, updateUseCase);
    }

    @Test
    public void success_getArtists() {
        // test用 response body
        List<Artist> artists = new ArrayList<>();
        artists.add(artist1);
        artists.add(artist2);
        artists.add(artist3);
        ArtistList artistList = new ArtistList(artists);

        // callモックオブジェクトのenqueueメソッドが呼ばれたら、doAnswerのラムダ式を実行する
        Mockito.doAnswer(invocation -> {
            // enqueueメソッドの0番目引数に渡されたCallbackオブジェクトを取得する
            Callback<ArtistList> callback = invocation.getArgument(0, Callback.class);
            callback.onResponse(call, Response.success(artistList));

            return null;
        }).when(call).enqueue(Mockito.any());

        viewModel.getArtists();
        List<Artist> actual = viewModel.artists.getValue();

        assertThat(actual).isEqualTo(artists);
    }

    @Test
    public void error_getArtists() {
        // callモックオブジェクトのenqueueメソッドが呼ばれたら、doAnswerのラムダ式を実行する
        Mockito.doAnswer(invocation -> {
            // enqueueメソッドの0番目引数に渡されたCallbackオブジェクトを取得する
            Callback<ArtistList> callback = invocation.getArgument(0, Callback.class);

            callback.onResponse(call, Response.error(404, Mockito.mock(okhttp3.ResponseBody.class)));
            return null;
        }).when(call).enqueue(Mockito.any());

        viewModel.getArtists();
        String actual = viewModel.errorMessage.getValue();

        assertThat(actual).isEqualTo("getArtists() response error");
    }

    @Test
    public void exception_getArtists() {
        // callモックオブジェクトのenqueueメソッドが呼ばれたら、doAnswerのラムダ式を実行する
        Mockito.doAnswer(invocation -> {
            // enqueueメソッドの0番目引数に渡されたCallbackオブジェクトを取得する
            Callback<ArtistList> callback = (Callback<ArtistList>) invocation.getArgument(0, Callback.class);

            callback.onFailure(call, new IOException("network error"));
            return null;
        }).when(call).enqueue(Mockito.any());

        viewModel.getArtists();
        String actual = viewModel.errorMessage.getValue();

        assertThat(actual).isEqualTo("network error");
    }
}