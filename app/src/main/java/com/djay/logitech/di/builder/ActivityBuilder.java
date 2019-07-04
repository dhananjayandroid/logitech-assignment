package com.djay.logitech.di.builder;

import com.djay.logitech.ui.itemdetail.ItemDetailActivity;
import com.djay.logitech.ui.itemdetail.ItemDetailActivityModule;
import com.djay.logitech.ui.itemlist.ItemListActivity;
import com.djay.logitech.ui.itemlist.ItemListActivityModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
@SuppressWarnings("unused")
public abstract class ActivityBuilder {

  /**
   * This abstract method binds ItemListActivity to provide ItemDetailViewModel later
   *
   * @return ItemListActivity
   */
  @ContributesAndroidInjector(modules = ItemListActivityModule.class)
  abstract ItemListActivity bindItemListActivity();


  /**
   * This abstract method binds ItemDetailActivity to provide ItemDetailViewModel later
   *
   * @return ItemDetailActivity
   */
  @ContributesAndroidInjector(modules = ItemDetailActivityModule.class)
  abstract ItemDetailActivity bindItemDetailActivity();
}
