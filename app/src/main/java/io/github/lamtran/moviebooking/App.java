package io.github.lamtran.moviebooking;

import com.facebook.drawee.backends.pipeline.Fresco;

import android.app.Application;

import io.github.lamtran.moviebooking.di.AppComponent;
import io.github.lamtran.moviebooking.di.AppModule;
import io.github.lamtran.moviebooking.di.DaggerAppComponent;

/**
 * Created by lam on 2/3/17.
 */

public class App extends Application {

  private AppComponent mAppComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    mAppComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .build();

    Fresco.initialize(this);
  }

  public AppComponent getAppComponent() {
    return mAppComponent;
  }
}
