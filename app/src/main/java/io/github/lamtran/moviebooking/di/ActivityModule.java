package io.github.lamtran.moviebooking.di;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lam on 2/3/17.
 */
@Module
public class ActivityModule {

  private final Activity mActivity;

  public ActivityModule(Activity activity) {
    mActivity = activity;
  }

  @ActivityScope
  @Provides
  Context provideContext() {
    return mActivity;
  }
}
