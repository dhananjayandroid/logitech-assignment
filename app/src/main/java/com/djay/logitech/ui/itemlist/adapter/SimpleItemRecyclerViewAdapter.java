package com.djay.logitech.ui.itemlist.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.djay.logitech.BR;
import com.djay.logitech.R;
import com.djay.logitech.service.model.Movie;
import com.djay.logitech.ui.ViewHolder;
import com.djay.logitech.ui.itemlist.ItemListNavigator;
import java.util.ArrayList;
import java.util.List;

public class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

  private List<Movie> mValues;
  private final ItemListNavigator mNavigator;

  public SimpleItemRecyclerViewAdapter(ItemListNavigator navigator) {
    this.mValues = new ArrayList<>();
    mNavigator = navigator;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new ViewHolder<>(DataBindingUtil
        .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_list_content, parent,
            false));
  }

  @Override
  public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
    holder.getBinding().setVariable(BR.movie, mValues.get(position));
    holder.getBinding().setVariable(BR.navigator, mNavigator);
  }

  @Override
  public int getItemCount() {
    return mValues.size();
  }

  public void setMoviesList(List<Movie> pMovies) {
    if (mValues != null) {
      PostDiffCallback postDiffCallback = new PostDiffCallback(mValues, pMovies);
      DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(postDiffCallback);
      mValues.clear();
      mValues.addAll(pMovies);
      diffResult.dispatchUpdatesTo(this);
    } else {
      // first initialization
      mValues = pMovies;
    }
  }

  class PostDiffCallback extends DiffUtil.Callback {

    private final List<Movie> oldMovies, newMovies;

    PostDiffCallback(List<Movie> oldMovies, List<Movie> newMovies) {
      this.oldMovies = oldMovies;
      this.newMovies = newMovies;
    }

    @Override
    public int getOldListSize() {
      return oldMovies.size();
    }

    @Override
    public int getNewListSize() {
      return newMovies.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
      return oldMovies.get(oldItemPosition).getTitle()
          .equalsIgnoreCase(newMovies.get(newItemPosition).getTitle());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
      return oldMovies.get(oldItemPosition).equals(newMovies.get(newItemPosition));
    }
  }
}