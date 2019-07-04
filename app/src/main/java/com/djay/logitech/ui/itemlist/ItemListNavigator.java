package com.djay.logitech.ui.itemlist;


import android.view.View;
import com.djay.logitech.service.model.Movie;

/**
 * Navigator interface to perform different action based on events
 */
public interface ItemListNavigator {

  void moveToDetailScreen(Movie movie, View... view);
}
