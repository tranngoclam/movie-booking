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
