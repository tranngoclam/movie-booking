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

package io.github.lamtran.moviebooking.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import io.github.lamtran.moviebooking.BR;
import io.github.lamtran.moviebooking.model.state.AvailableState;
import io.github.lamtran.moviebooking.model.state.EmptyState;
import io.github.lamtran.moviebooking.model.state.ReservedState;
import io.github.lamtran.moviebooking.model.state.SelectedState;
import io.github.lamtran.moviebooking.model.state.State;
import io.github.lamtran.moviebooking.model.state.UnknownState;

import static io.github.lamtran.moviebooking.model.Seat.Type.AVAILABLE;
import static io.github.lamtran.moviebooking.model.Seat.Type.EMPTY;
import static io.github.lamtran.moviebooking.model.Seat.Type.RESERVED;
import static io.github.lamtran.moviebooking.model.Seat.Type.SELECTED;

/**
 * Created by lam on 2/3/17.
 */

public class Seat extends BaseObservable {

  @Bindable
  private State mState;

  public Seat(int type) {
    switch (type) {
      case EMPTY:
        mState = new EmptyState();
        break;
      case RESERVED:
        mState = new ReservedState();
        break;
      case AVAILABLE:
        mState = new AvailableState();
        break;
      case SELECTED:
        mState = new SelectedState();
        break;
      default:
        mState = new UnknownState();
        break;
    }
  }

  public void changeState() {
    if (mState instanceof AvailableState) {
      mState = new SelectedState();
      notifyPropertyChanged(BR.state);
    } else if (mState instanceof SelectedState) {
      mState = new AvailableState();
      notifyPropertyChanged(BR.state);
    }
  }

  public State getState() {
    return mState;
  }

  public boolean isStateSelectable() {
    return mState.isSelectable();
  }

  public interface Type {

    int AVAILABLE = 2;
    int EMPTY = 0;
    int RESERVED = 1;
    int SELECTED = 3;
    int UNKNOWN = -1;
  }
}
