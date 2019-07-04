package com.djay.logitech.ui;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Generic ViewModel class
 */
public class ViewHolder<V extends ViewDataBinding> extends RecyclerView.ViewHolder {

  private final V v;   // ViewDataBinding

  /**
   * ViewHolder constructor with ViewDataBinding
   */
  public ViewHolder(V v) {
    super(v.getRoot());
    this.v = v;
  }

  /**
   * Provides current binding
   *
   * @return ViewDataBinding
   */
  public V getBinding() {
    return v;
  }
}