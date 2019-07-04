package com.djay.logitech.di.module;

import com.djay.logitech.service.repository.ApiService;
import com.djay.logitech.service.repository.MoviesRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

  /**
   * The method returns the Gson object
   */
  @Provides
  @Singleton
  Gson provideGson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    return gsonBuilder.create();
  }

  /**
   * The method returns the Okhttp object
   */
  @Provides
  @Singleton
  OkHttpClient provideOkHttpClient() {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    builder.connectTimeout(60, TimeUnit.SECONDS);
    builder.readTimeout(60, TimeUnit.SECONDS);
    builder.writeTimeout(60, TimeUnit.SECONDS);
    return builder.addInterceptor(logging).build();
  }

  /**
   * The method returns the Retrofit object
   */
  @Provides
  @Singleton
  Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
    return new Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl("https://api.myjson.com/")
        .client(okHttpClient)
        .build();
  }

  /**
   * We need the ApiService module. For this, We need the Retrofit object, Gson and
   * OkHttpClient . So we will define the providers for these objects here in this module.
   */
  @Provides
  @Singleton
  ApiService provideApiService(Retrofit retrofit) {
    return retrofit.create(ApiService.class);
  }

  /**
   * The method returns the MoviesRepository object
   */
  @Provides
  @Singleton
  MoviesRepository provideItemRepository(ApiService apiService) {
    return new MoviesRepository(apiService);
  }
}
