/*
 * MIT License
 *
 * Copyright (c) 2017 Lam Tran (tranngoclam288@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.github.lamtran.moviebooking.internal.binding;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.view.View;
import io.github.lamtran.moviebooking.R;
import io.github.lamtran.moviebooking.data.model.state.AvailableState;
import io.github.lamtran.moviebooking.data.model.state.EmptyState;
import io.github.lamtran.moviebooking.data.model.state.ReservedState;
import io.github.lamtran.moviebooking.data.model.state.SelectedState;
import io.github.lamtran.moviebooking.data.model.state.State;

/**
 * Created by lam on 2/3/17.
 */

public final class ViewBinding {

  @BindingAdapter("state") public static void setState(@NonNull
  final View view, @NonNull
  final State state) {
    int colorRes;
    if (state instanceof EmptyState) {
      colorRes = R.color.colorStateEmpty;
    } else if (state instanceof ReservedState) {
      colorRes = R.color.colorStateReserved;
    } else if (state instanceof AvailableState) {
      colorRes = R.color.colorStateAvailable;
    } else if (state instanceof SelectedState) {
      colorRes = R.color.colorStateSelected;
    } else {
      colorRes = R.color.colorStateUnknown;
    }
    view.setBackgroundColor(view.getResources().getColor(colorRes));
  }
}
