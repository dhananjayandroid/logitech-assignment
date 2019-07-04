package com.djay.logitech.ui.itemdetail;

import static com.djay.logitech.ui.itemdetail.ItemDetailViewModel.ARG_ITEM;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import com.djay.logitech.BR;
import com.djay.logitech.R;
import com.djay.logitech.databinding.ActivityItemDetailBinding;
import com.djay.logitech.service.model.Movie;
import com.djay.logitech.ui.InjectionActivity;
import com.djay.logitech.ui.itemlist.ItemListActivity;
import javax.inject.Inject;

/**
 * An activity representing a single Item detail screen. This activity is used to show details of
 * item when user clicks on an item of {@link ItemListActivity}.
 */
public class ItemDetailActivity extends
    InjectionActivity<ActivityItemDetailBinding, ItemDetailViewModel> implements
    ItemDetailNavigator {

  @Inject
  ItemDetailViewModel mItemDetailViewModel;

  @Override
  public int getBindingVariable() {
    return BR.viewmodel;
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_item_detail;
  }

  @Override
  public ItemDetailViewModel getViewModel() {
    return mItemDetailViewModel;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setToolbar();

    mItemDetailViewModel.setMovie((Movie) getIntent().getSerializableExtra(ARG_ITEM));
  }

  private void setToolbar() {
    Toolbar toolbar = findViewById(R.id.detail_toolbar);
    setSupportActionBar(toolbar);
    // Show the Up button in the action bar.
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Respond to the action bar's Up/Home button
    if (item.getItemId() == android.R.id.home) {
      supportFinishAfterTransition();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
