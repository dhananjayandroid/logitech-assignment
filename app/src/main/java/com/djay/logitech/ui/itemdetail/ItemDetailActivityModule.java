package com.djay.logitech.ui.itemdetail;

import android.app.Application;
import dagger.Module;
import dagger.Provides;

@Module
public class ItemDetailActivityModule {

  @Provides
  ItemDetailViewModel provideItemDetailActivityModule(Application application) {
    return new ItemDetailViewModel(application);
  }
}
