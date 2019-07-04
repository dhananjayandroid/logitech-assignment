package com.djay.logitech.ui.itemlist;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.djay.logitech.service.model.Item;
import com.djay.logitech.service.model.Movie;
import com.djay.logitech.service.repository.MoviesRepository;
import com.djay.logitech.service.repository.MoviesRepository.MoviesRepositoryCallback;
import com.djay.logitech.ui.BaseAndroidViewModel;
import java.util.List;
import retrofit2.Response;

/**
 * ViewModel class for ItemListActivity
 */
public class ItemListViewModel extends BaseAndroidViewModel<ItemListNavigator> implements
    MoviesRepositoryCallback {

  private final MutableLiveData<List<Movie>> itemsListObservable = new MutableLiveData<>();
  private final MoviesRepository repository;

  ItemListViewModel(@NonNull Application application,
      @NonNull MoviesRepository moviesRepository) {
    super(application);
    repository = moviesRepository;
    retrieveMoviesList();
  }

  @VisibleForTesting
  void retrieveMoviesList() {
    repository.getMoviesList(this);
  }

  /**
   * Expose the LiveData Item query so the UI can observe it.
   */
  LiveData<List<Movie>> getItemsListObservable() {
    return itemsListObservable;
  }

  @Override
  public void handleMoviesResponse(@NonNull Response<Item> response) {
    if (response.isSuccessful()) {
      Item item = response.body();
      if (item != null && item.getMovies() != null) {
        updateItemsList(item);
      }
    }
  }

  @VisibleForTesting
  void updateItemsList(Item item) {
    itemsListObservable.setValue(item.getMovies());
  }

  @Override
  public void handleMoviesError() {
    Log.w("", "handleMoviesError");
  }
}
