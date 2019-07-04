package com.djay.logitech.utils;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.djay.logitech.R;
import com.djay.logitech.service.model.Movie;

/**
 * Utility class related to binding. Contains binding-adapter methods
 */
public class BindingUtils {

  /**
   * Load Images with Glide
   *
   * @param imageView ImageView
   * @param movie Movie
   */
  @BindingAdapter({"loadImage"})
  public static void loadImage(ImageView imageView, Movie movie) {
    Drawable drawable = new ColorDrawable(
        imageView.getResources().getColor(R.color.colorPrimaryDark));
    if (movie != null && movie.getPoster() != null) {
      Glide.with(imageView.getContext())
          .load(movie.getPoster().trim())
          .transition(DrawableTransitionOptions.withCrossFade())
          .apply(new RequestOptions()
              .placeholder(drawable)
              .error(drawable)
              .dontTransform()
              .diskCacheStrategy(DiskCacheStrategy.ALL))
          .into(imageView);
    } else {
      imageView.setImageDrawable(drawable);
    }
  }
}
