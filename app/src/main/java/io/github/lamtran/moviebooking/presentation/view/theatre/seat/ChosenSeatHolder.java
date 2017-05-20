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
package io.github.lamtran.moviebooking.presentation.view.theatre.seat;

import android.view.View;
import io.github.lamtran.moviebooking.data.model.theatre.seat.ChosenSeat;
import io.github.lamtran.moviebooking.databinding.HolderChosenSeatBinding;
import io.github.lamtran.moviebooking.presentation.view.theatre.seat.SeatAdapter.Holder;
import javax.inject.Inject;

/**
 * Author: JONATHAN MERRITT
 * Year: 2017
 * Contact: 11R00TT00RR11@GMAIL.COM
 */
public class ChosenSeatHolder extends Holder<ChosenSeat, HolderChosenSeatBinding, ChosenSeatHolder.Controller> {
  private ChosenSeatHolder(View view, Controller controller) {
    super(HolderChosenSeatBinding.bind(view), controller);
  }
  @Override public void setBindings(HolderChosenSeatBinding binding) {
    binding.setController(controller);
  }
  public static class Factory extends Holder.Factory<ChosenSeat, Controller> {
    @Inject public Factory(Controller controller) {
      super(controller);
    }
    @Override public Holder create(View view) {
      return new ChosenSeatHolder(view, controller);
    }
  }
  public static class Controller extends Holder.Controller<ChosenSeat> {
    @Inject Controller(SeatChanger changer) {
      super(changer);
    }
  }
}
