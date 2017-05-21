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
import io.github.lamtran.moviebooking.data.model.theatre.seat.ReservedSeat;
import io.github.lamtran.moviebooking.databinding.HolderReservedSeatBinding;
import io.github.lamtran.moviebooking.presentation.view.theatre.seat.SeatAdapter.Holder;
import javax.inject.Inject;

/**
 * Author: JONATHAN MERRITT
 * Year: 2017
 * Contact: 11R00TT00RR11@GMAIL.COM
 */
public class ReservedSeatHolder
    extends Holder<ReservedSeat, HolderReservedSeatBinding, ReservedSeatHolder.Controller> {
  private ReservedSeatHolder(View view, Controller controller) {
    super(HolderReservedSeatBinding.bind(view), controller);
  }
  @Override public void setBindings(HolderReservedSeatBinding binding) {
    binding.setController(controller);
  }
  public static class Factory extends Holder.Factory<ReservedSeat, Controller> {
    @Inject public Factory(Controller controller) {
      super(controller);
    }
    @Override public Holder create(View view) {
      return new ReservedSeatHolder(view, controller);
    }
  }
  public static class Controller extends Holder.Controller<ReservedSeat> {
    @Inject Controller(SeatChanger changer) {
      super(changer);
    }
  }
}
