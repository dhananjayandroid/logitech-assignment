package com.djay.logitech.service.repository;

import com.djay.logitech.service.model.Item;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepositoryTest {

  private MoviesRepository repository;

  @Mock
  private ApiService apiService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    repository = Mockito.spy(new MoviesRepository(apiService));
  }

  @Test
  public void getMoviesList() {
    MoviesRepository.MoviesRepositoryCallback callback = new MoviesRepository.MoviesRepositoryCallback() {
      @Override
      public void handleMoviesResponse(Response<Item> response) {
      }

      @Override
      public void handleMoviesError() {
      }
    };
    Call call = Mockito.mock(Call.class);
    Mockito.when(apiService.getMoviesList()).thenReturn(call);
    Mockito.doNothing().when(call).enqueue(Mockito.any(Callback.class));

    // trigger
    repository.getMoviesList(callback);
    // validation
    Mockito.verify(apiService, Mockito.times(1)).getMoviesList();
  }
}
