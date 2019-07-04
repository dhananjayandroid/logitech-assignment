package com.djay.logitech.ui.itemlist;

import android.app.Application;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import com.djay.logitech.service.model.Item;
import com.djay.logitech.service.model.Movie;
import com.djay.logitech.service.repository.MoviesRepository;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import retrofit2.Response;

public class ItemListViewModelTest {

  private ItemListViewModel viewModel;

  @Mock
  private MoviesRepository repository;

  @Rule
  public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    viewModel = Mockito.spy(new ItemListViewModel(new Application(), repository));
  }

  @Test
  public void retrieveMoviesList() {
    // Trigger
    viewModel.retrieveMoviesList();

    // Validation
    Mockito.verify(repository, Mockito.times(1)).getMoviesList(viewModel);
  }

  @Test
  public void handleMoviesResponse_Success() {
    Response response = Mockito.mock(Response.class);
    Item item = Mockito.mock(Item.class);
    Mockito.when(response.isSuccessful()).thenReturn(true);
    Mockito.when(response.body()).thenReturn(item);
    List<Movie> moviesResults = Collections.singletonList(new Movie());
    Mockito.when(item.getMovies()).thenReturn(moviesResults);

    // Trigger
    viewModel.handleMoviesResponse(response);

    // Validation
    Mockito.verify(viewModel, Mockito.times(1)).updateItemsList(item);
  }

  @Test
  public void updateItemsList() {
    Response response = Mockito.mock(Response.class);
    Item item = Mockito.mock(Item.class);
    Mockito.when(response.isSuccessful()).thenReturn(true);
    Mockito.when(response.body()).thenReturn(item);
    List<Movie> moviesResults = Collections.singletonList(new Movie());
    Mockito.when(item.getMovies()).thenReturn(moviesResults);

    // Trigger
    viewModel.handleMoviesResponse(response);

    // Validation
    assert viewModel.getItemsListObservable().getValue() != null;
    Assert.assertEquals(moviesResults.size(), viewModel.getItemsListObservable().getValue().size());
  }
}
