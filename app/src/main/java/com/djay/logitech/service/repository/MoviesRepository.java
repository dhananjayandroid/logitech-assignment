package com.djay.logitech.service.repository;

import android.util.Log;
import androidx.annotation.NonNull;
import com.djay.logitech.service.model.Item;
import javax.inject.Singleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class MoviesRepository {

  private final ApiService mApiService;

  public MoviesRepository(ApiService apiService) {
    mApiService = apiService;
  }

  /**
   * Provides items list
   */
  public void getMoviesList(@NonNull final MoviesRepositoryCallback callback) {
    mApiService.getMoviesList().enqueue(new Callback<Item>() {
      @Override
      public void onResponse(@NonNull Call<Item> call, @NonNull Response<Item> response) {
        callback.handleMoviesResponse(response);
      }

      @Override
      public void onFailure(@NonNull Call<Item> call, @NonNull Throwable t) {
        Log.e("", "onFailure", t);
        callback.handleMoviesError();
      }
    });
  }

  /**
   * Callback interface for Movies Repository
   */
  public interface MoviesRepositoryCallback {

    void handleMoviesResponse(Response<Item> response);

    void handleMoviesError();
  }
}