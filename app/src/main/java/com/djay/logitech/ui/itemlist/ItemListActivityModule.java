package com.djay.logitech.ui.itemlist;

import android.app.Application;
import com.djay.logitech.service.repository.MoviesRepository;
import dagger.Module;
import dagger.Provides;

@Module
public class ItemListActivityModule {

  @Provides
  ItemListViewModel provideItemListActivityModule(Application application,
      MoviesRepository moviesRepository) {
    return new ItemListViewModel(application, moviesRepository);
  }
}
