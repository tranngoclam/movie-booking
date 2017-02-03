package io.github.lamtran.moviebooking.ui;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import io.github.lamtran.moviebooking.App;
import io.github.lamtran.moviebooking.di.ActivityComponent;
import io.github.lamtran.moviebooking.di.ActivityModule;

/**
 * Created by lam on 2/3/17.
 */

public abstract class BaseActivity extends RxAppCompatActivity {

  private ActivityComponent mActivityComponent;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mActivityComponent = ((App) getApplication()).getAppComponent().activityComponent(new ActivityModule(this));
  }

  public ActivityComponent getActivityComponent() {
    return mActivityComponent;
  }
}
