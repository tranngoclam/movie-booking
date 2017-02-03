package io.github.lamtran.moviebooking.di;

import dagger.Module;
import io.github.lamtran.moviebooking.App;

/**
 * Created by lam on 2/3/17.
 */
@Module
public class AppModule {

  private final App mApp;

  public AppModule(App app) {
    mApp = app;
  }
}
