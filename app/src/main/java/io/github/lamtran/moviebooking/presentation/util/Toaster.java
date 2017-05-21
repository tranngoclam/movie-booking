package io.github.lamtran.moviebooking.presentation.util;

import android.content.Context;
import android.widget.Toast;
import io.github.lamtran.moviebooking.internal.injection.scope.ForActivity;
import io.github.lamtran.moviebooking.presentation.view.base.screen.BaseActivity;
import javax.inject.Inject;

/**
 * Author: JONATHAN MERRITT
 * Year: 2017
 * Contact: 11R00TT00RR11@GMAIL.COM
 */

@ForActivity public class Toaster {
  private final Context context;

  @Inject Toaster(BaseActivity activity) {
    context = activity;
  }

  public void show(int stringId) {
    Toast.makeText(context, context.getString(stringId), Toast.LENGTH_SHORT).show();
  }
}
