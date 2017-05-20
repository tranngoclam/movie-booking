package io.github.lamtran.moviebooking.presentation.view.base;

import android.databinding.ViewDataBinding;

/**
 * Author: JONATHAN MERRITT
 * Year: 2017
 * Contact: 11R00TT00RR11@GMAIL.COM
 */

public interface HasBoundData<B extends ViewDataBinding> {
  void setBindings(B binding);

  @SuppressWarnings("unused") B getBinding();
}
