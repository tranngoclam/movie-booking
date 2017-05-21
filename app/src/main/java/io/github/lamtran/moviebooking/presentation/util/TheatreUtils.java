package io.github.lamtran.moviebooking.presentation.util;

import io.github.lamtran.moviebooking.R;
import io.github.lamtran.moviebooking.data.model.theatre.seat.ChosenSeat;
import io.github.lamtran.moviebooking.data.model.theatre.seat.EmptySeat;
import io.github.lamtran.moviebooking.data.model.theatre.seat.ReservedSeat;
import io.github.lamtran.moviebooking.data.model.theatre.seat.Seat;
import io.github.lamtran.moviebooking.data.model.theatre.seat.UnChosenSeat;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: JONATHAN MERRITT
 * Year: 2017
 * Contact: 11R00TT00RR11@GMAIL.COM
 */

public class TheatreUtils {
  public static List<Seat> seats() {
    final List<Seat> seats = new ArrayList<>();
    int number = 1, total = 0;
    do {
      number = number + 4;
      seats.add(UnChosenSeat.create(number - 4));
      seats.add(UnChosenSeat.create(number - 3));
      seats.add(EmptySeat.create(number - 2));
      seats.add(UnChosenSeat.create(number - 1));
      total++;
    } while (total < 8);
    return seats;
  }
  public static Seat resolveSeat(Seat last, Class<? extends Seat> cLass) {
    return UnChosenSeat.check(cLass) ? UnChosenSeat.copy(last) : ChosenSeat.check(cLass) ? ChosenSeat.copy(last)
        : ReservedSeat.check(cLass) ? ReservedSeat.copy(last) : EmptySeat.copy(last);
  }
  public static int resolveLayout(Class<? extends Seat> cLass) {
    return UnChosenSeat.check(cLass) ? R.layout.holder_unchosen_seat
        : ChosenSeat.check(cLass) ? R.layout.holder_chosen_seat
            : ReservedSeat.check(cLass) ? R.layout.holder_reserved_seat : R.layout.holder_empty_seat;
  }
  public static Class<? extends Seat> swapSeat(Class<? extends Seat> cLass) {
    return UnChosenSeat.check(cLass) ? ChosenSeat.class : ChosenSeat.check(cLass) ? UnChosenSeat.class
        : ReservedSeat.check(cLass) ? EmptySeat.class
            : EmptySeat.check(cLass) ? ReservedSeat.class : EmptySeat.class;
  }
  public static Class<? extends Seat> swapSeatAlt(Class<? extends Seat> cLass) {
    return UnChosenSeat.check(cLass) ? ReservedSeat.class : ChosenSeat.check(cLass) ? EmptySeat.class
        : ReservedSeat.check(cLass) ? UnChosenSeat.class
            : EmptySeat.check(cLass) ? ChosenSeat.class : EmptySeat.class;
  }
}
