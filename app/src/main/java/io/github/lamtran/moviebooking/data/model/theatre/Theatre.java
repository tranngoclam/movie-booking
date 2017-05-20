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
package io.github.lamtran.moviebooking.data.model.theatre;

import com.google.auto.value.AutoValue;
import io.github.lamtran.moviebooking.data.model.theatre.seat.ChosenSeat;
import io.github.lamtran.moviebooking.data.model.theatre.seat.EmptySeat;
import io.github.lamtran.moviebooking.data.model.theatre.seat.ReservedSeat;
import io.github.lamtran.moviebooking.data.model.theatre.seat.Seat;
import io.github.lamtran.moviebooking.data.model.theatre.seat.UnChosenSeat;
import java.util.List;

/**
 * Author: JONATHAN MERRITT
 * Year: 2017
 * Contact: 11R00TT00RR11@GMAIL.COM
 */
@AutoValue public abstract class Theatre extends BaseTheatre {

  private static Theatre create(List<Seat> seats, int tickets, int unchosen, int chosen, int reserved, int empty) {
    return new AutoValue_Theatre(seats, tickets, unchosen, chosen, reserved, empty);
  }

  private static Theatre copy(Theatre old) {
    return create(old.seats(), old.tickets(), old.unchosen(), old.chosen(), old.reserved(), old.empty());
  }

  public static Theatre idle(List<Seat> seats) {
    int unchosen = 0, chosen = unchosen, reserved = chosen, empty = reserved;
    for (Seat seat : seats) {
      final Class<? extends Seat> cLass = seat.getClass();
      if (UnChosenSeat.check(cLass)) unchosen++;
      else if (ChosenSeat.check(cLass)) chosen++;
      else if (ReservedSeat.check(cLass)) reserved++;
      else if (EmptySeat.check(cLass)) empty++;
    }
    return create(seats, (unchosen + chosen) / 2, unchosen, chosen, reserved, empty);
  }

  public static Theatre reset(Theatre old, List<Seat> seats) {
    Theatre theatre = old;
    for (Seat seat : seats) {
      final Class<? extends Seat> cLass = seat.getClass();
      final Class<? extends Seat> lastClass = old.seats().get(seats.indexOf(seat)).getClass();
      if (!Seat.check(cLass, lastClass)) theatre = Theatre.seat(theatre, seats, lastClass, cLass);
    }
    return copy(theatre);
  }

  public static Theatre tickets(Theatre old, int tickets) {
    return create(old.seats(), tickets, old.unchosen(), old.chosen(), old.reserved(), old.empty());
  }

  public static Theatre seat(Theatre old, List<Seat> seats, Class<? extends Seat> lastClass,
      Class<? extends Seat> nextClass) {
    return create(seats, old.tickets(),
        adjust(old.unchosen(), UnChosenSeat.check(nextClass), UnChosenSeat.check(lastClass)),
        adjust(old.chosen(), ChosenSeat.check(nextClass), ChosenSeat.check(lastClass)),
        adjust(old.reserved(), ReservedSeat.check(nextClass), ReservedSeat.check(lastClass)),
        adjust(old.empty(), EmptySeat.check(nextClass), EmptySeat.check(lastClass)));
  }

  public abstract int unchosen();

  public abstract int chosen();

  public abstract int reserved();

  public abstract int empty();
}