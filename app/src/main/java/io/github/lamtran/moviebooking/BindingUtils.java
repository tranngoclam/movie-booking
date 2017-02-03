package io.github.lamtran.moviebooking;

import android.databinding.BindingAdapter;
import android.view.View;

import io.github.lamtran.moviebooking.model.state.State;

/**
 * Created by lam on 2/3/17.
 */

public final class BindingUtils {

  @BindingAdapter("state")
  public static void setState(View view, State state) {

  }

  private BindingUtils() {
  }
}
