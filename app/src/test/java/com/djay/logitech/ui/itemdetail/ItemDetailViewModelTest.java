package com.djay.logitech.ui.itemdetail;

import android.app.Application;
import com.djay.logitech.service.model.Movie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ItemDetailViewModelTest {

  private ItemDetailViewModel viewModel;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    viewModel = Mockito.spy(new ItemDetailViewModel(new Application()));
  }

  @Test
  public void setMovie() {
    Movie movie = Mockito.mock(Movie.class);

    // Trigger
    viewModel.setMovie(movie);

    // Validation
    Assert.assertEquals(movie, viewModel.movie.get());
  }
}
