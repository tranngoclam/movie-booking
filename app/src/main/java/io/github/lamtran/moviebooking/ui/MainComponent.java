package io.github.lamtran.moviebooking.ui;

import dagger.Subcomponent;
import io.github.lamtran.moviebooking.di.ActivityScope;

/**
 * Created by lam on 2/3/17.
 */
@ActivityScope
@Subcomponent(modules = MainModule.class)
public interface MainComponent {

  MainViewModel mainViewModel();
}
