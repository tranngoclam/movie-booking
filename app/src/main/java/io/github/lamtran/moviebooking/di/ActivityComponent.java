package io.github.lamtran.moviebooking.di;

import dagger.Subcomponent;
import io.github.lamtran.moviebooking.ui.MainComponent;
import io.github.lamtran.moviebooking.ui.MainModule;

/**
 * Created by lam on 2/3/17.
 */
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

  MainComponent mainComponent(MainModule mainModule);
}
