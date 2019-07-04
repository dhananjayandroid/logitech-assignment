
package com.djay.logitech.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Item {

  @Expose
  private List<Movie> movies;

  public List<Movie> getMovies() {
    return movies;
  }

  public void setMovies(ArrayList<Movie> movies) {
    this.movies = movies;
  }
}
