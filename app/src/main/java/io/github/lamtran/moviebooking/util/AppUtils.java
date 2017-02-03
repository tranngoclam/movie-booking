package io.github.lamtran.moviebooking.util;

import java.util.ArrayList;
import java.util.List;

import io.github.lamtran.moviebooking.Seat;
import io.github.lamtran.moviebooking.model.state.EmptyState;

/**
 * Created by lam on 2/3/17.
 */

public final class AppUtils {

  public static final int SIZE = 4;

  public static List<Seat> fakeSeats() {
    List<Seat> seats = new ArrayList<>(SIZE * SIZE);
    seats.add(new Seat(0, 0, new EmptyState()));
    seats.add(new Seat(0, 1, new EmptyState()));
    seats.add(new Seat(0, 2, new EmptyState()));
    seats.add(new Seat(0, 3, new EmptyState()));

    seats.add(new Seat(1, 0, new EmptyState()));
    seats.add(new Seat(1, 1, new EmptyState()));
    seats.add(new Seat(1, 2, new EmptyState()));
    seats.add(new Seat(1, 3, new EmptyState()));

    seats.add(new Seat(2, 0, new EmptyState()));
    seats.add(new Seat(2, 1, new EmptyState()));
    seats.add(new Seat(2, 2, new EmptyState()));
    seats.add(new Seat(2, 3, new EmptyState()));

    seats.add(new Seat(3, 0, new EmptyState()));
    seats.add(new Seat(3, 1, new EmptyState()));
    seats.add(new Seat(3, 2, new EmptyState()));
    seats.add(new Seat(3, 3, new EmptyState()));

    return seats;
  }
}
