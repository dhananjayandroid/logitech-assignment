package com.djay.logitech.service.repository;

import com.djay.logitech.service.model.Item;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

  @GET("bins/18buhu")
  Call<Item> getMoviesList();
}