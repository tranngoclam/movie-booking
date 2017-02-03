package io.github.lamtran.moviebooking;

import android.app.Application;

import io.github.lamtran.moviebooking.di.AppComponent;
import io.github.lamtran.moviebooking.di.AppModule;

/**
 * Created by lam on 2/3/17.
 */

public class App extends Application {

  private AppComponent mAppComponent;

  @Override
  public void onCreate() {
    super.onCreate();
  }

  public AppComponent getAppComponent() {
    if (mAppComponent == null) {
      mAppComponent = DaggerAppComponent.builder()
          .appModule(new AppModule(this))
          .build();
    }
    return mAppComponent;
  }
}
