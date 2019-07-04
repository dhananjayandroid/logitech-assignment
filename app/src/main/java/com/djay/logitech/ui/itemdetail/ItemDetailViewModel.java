package com.djay.logitech.ui.itemdetail;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.djay.logitech.service.model.Movie;
import com.djay.logitech.ui.BaseAndroidViewModel;

/**
 * ViewModel class for ItemDetailActivity
 */
public class ItemDetailViewModel extends BaseAndroidViewModel<ItemDetailNavigator> {

  /**
   * The argument representing the item that this activity represents.
   */
  public static final String ARG_ITEM = "item";

  public final ObservableField<Movie> movie = new ObservableField<>();

  ItemDetailViewModel(@NonNull Application application) {
    super(application);
  }

  public void setMovie(Movie movie) {
    this.movie.set(movie);
  }
}
