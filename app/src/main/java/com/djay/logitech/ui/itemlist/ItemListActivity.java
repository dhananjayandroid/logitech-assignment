package com.djay.logitech.ui.itemlist;

import static com.djay.logitech.ui.itemdetail.ItemDetailViewModel.ARG_ITEM;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.idling.CountingIdlingResource;
import com.djay.logitech.BR;
import com.djay.logitech.R;
import com.djay.logitech.databinding.ActivityItemListBinding;
import com.djay.logitech.service.model.Movie;
import com.djay.logitech.ui.InjectionActivity;
import com.djay.logitech.ui.itemdetail.ItemDetailActivity;
import com.djay.logitech.ui.itemlist.adapter.SimpleItemRecyclerViewAdapter;
import com.djay.logitech.utils.CommonUtils;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * An activity representing a list of Items. This activity has different presentations for handset
 * and tablet-size devices. On handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing item details. On tablets, the activity presents
 * the list of items and item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends
    InjectionActivity<ActivityItemListBinding, ItemListViewModel> implements ItemListNavigator {

  private CountingIdlingResource espressoTestIdlingResource = new CountingIdlingResource(
      "Network_Call");
  private SimpleItemRecyclerViewAdapter mAdapter;

  @Inject
  ItemListViewModel mItemListViewModel;

  @Override
  public int getBindingVariable() {
    return BR.viewmodel;
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_item_list;
  }

  @Override
  public ItemListViewModel getViewModel() {
    return mItemListViewModel;
  }

  @Override
  protected void onCreate(Bundle pBundle) {
    super.onCreate(pBundle);
    init();
    setViewModel();
    getViewModel().getItemsListObservable().observe(this, this::setMovies);
  }

  private void setViewModel() {
    espressoTestIdlingResource.increment();
    mItemListViewModel.setNavigator(this);
  }

  private void setMovies(List<Movie> movies) {
    getViewDataBinding().shimmerViewContainer.stopShimmer();
    getViewDataBinding().shimmerViewContainer.setVisibility(View.GONE);
    if (movies != null) {
      mAdapter.setMoviesList(movies);
    } else {
      Toast.makeText(this, R.string.error_in_response, Toast.LENGTH_SHORT).show();
      mAdapter.setMoviesList(new ArrayList<>());
    }
    espressoTestIdlingResource.decrement();
  }

  /**
   * Init initial ui
   */
  private void init() {
    getViewDataBinding().shimmerViewContainer.startShimmer();

    Toolbar toolbar = getViewDataBinding().toolbar;
    setSupportActionBar(toolbar);
    toolbar.setTitle(getTitle());

    RecyclerView recyclerView = getViewDataBinding().itemList;
    recyclerView.setHasFixedSize(true);
    GridLayoutManager layoutManager = new GridLayoutManager(this,
        CommonUtils.calculateNoOfColumns(this, 182));
    layoutManager.setOrientation(RecyclerView.VERTICAL);
    recyclerView.setLayoutManager(layoutManager);
    mAdapter = new SimpleItemRecyclerViewAdapter(this);
    recyclerView.setAdapter(mAdapter);
  }

  @Override
  public void moveToDetailScreen(Movie movie, View... views) {
    Intent intent = new Intent(this, ItemDetailActivity.class);
    intent.putExtra(ARG_ITEM, movie);
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
      Pair<View, String> p1 = Pair.create(views[0], views[0].getTransitionName());
      ActivityOptionsCompat options = ActivityOptionsCompat.
          makeSceneTransitionAnimation(this, p1);
      startActivity(intent, options.toBundle());
    } else {
      startActivity(intent);
    }
  }

  /**
   * @return ItemList's idling resource for Espresso testing
   */
  @VisibleForTesting
  CountingIdlingResource getEspressoIdlingResourceForMainActivity() {
    return espressoTestIdlingResource;
  }
}
