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
package io.github.lamtran.moviebooking.presentation.view.theatre;

import android.content.Intent;
import android.databinding.ObservableInt;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import dagger.Binds;
import io.github.lamtran.moviebooking.R;
import io.github.lamtran.moviebooking.data.model.theatre.Theatre;
import io.github.lamtran.moviebooking.data.model.theatre.seat.ChosenSeat;
import io.github.lamtran.moviebooking.data.model.theatre.seat.EmptySeat;
import io.github.lamtran.moviebooking.data.model.theatre.seat.ReservedSeat;
import io.github.lamtran.moviebooking.data.model.theatre.seat.Seat;
import io.github.lamtran.moviebooking.data.model.theatre.seat.UnChosenSeat;
import io.github.lamtran.moviebooking.databinding.ActivityTheatreBinding;
import io.github.lamtran.moviebooking.internal.injection.scope.ForActivity;
import io.github.lamtran.moviebooking.presentation.App;
import io.github.lamtran.moviebooking.presentation.util.TheatreUtils;
import io.github.lamtran.moviebooking.presentation.util.Toaster;
import io.github.lamtran.moviebooking.presentation.view.base.screen.BaseActivity;
import io.github.lamtran.moviebooking.presentation.view.theatre.seat.SeatAdapter;
import io.github.lamtran.moviebooking.presentation.view.theatre.seat.SeatChanger;
import java.util.List;
import javax.inject.Inject;

import static android.view.KeyEvent.KEYCODE_BACK;
import static io.github.lamtran.moviebooking.presentation.util.TheatreUtils.swapSeat;
import static io.github.lamtran.moviebooking.presentation.util.TheatreUtils.swapSeatAlt;

/**
 * Author: JONATHAN MERRITT
 * Year: 2017
 * Contact: 11R00TT00RR11@GMAIL.COM
 */
public class TheatreActivity extends BaseActivity<ActivityTheatreBinding, TheatreActivity.Controller> {

  @Override public int getLayout() {
    return R.layout.activity_theatre;
  }

  @Override public void setBindings(ActivityTheatreBinding binding) {
    binding.setController(controller);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_theatre, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.item_github:
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(App.GITHUB)));
        break;
      case R.id.item_reset:
        controller.reset();
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public boolean onKeyDown(int code, KeyEvent event) {
    if (code == KEYCODE_BACK && controller.hasChosen()) {
      controller.changeSeats(UnChosenSeat.class);
      return true;
    }
    return super.onKeyDown(code, event);
  }

  @ForActivity public static class Controller extends BaseActivity.Controller<Theatre> {

    public final ObservableInt unchosen = new ObservableInt(), chosen = new ObservableInt(), reserved =
        new ObservableInt(), empty = new ObservableInt();
    public final SeatAdapter adapter;
    private final Toaster toaster;

    @Inject public Controller(SeatAdapter adapter, Toaster toaster, SeatChanger changer) {
      super();
      this.adapter = adapter;
      this.toaster = toaster;
      changer.setRequester(this::changeSeat);
    }

    @Override public void onStart() {
      super.onStart();
      setModel(Theatre.idle(TheatreUtils.seats()));
      adapter.setModels(model.seats());
    }

    @Override public void onRestore(Bundle restoredState) {
      super.onRestore(restoredState);
      adapter.setModels(model.seats());
    }

    @Override public void setModel(Theatre model) {
      super.setModel(model);
      unchosen.set(model.unchosen());
      chosen.set(model.chosen());
      reserved.set(model.reserved());
      empty.set(model.empty());
    }

    @Override public void onClick(View view) {
      super.onClick(view);
      final int id = view.getId();
      switch (id) {
        case R.id.seat_click:
        case R.id.seat_long_click:
        case R.id.set_tickets:
          final PopupMenu popup = new PopupMenu(view.getContext(), view);
          for (int index = 0;
              index < (id != R.id.set_tickets ? model.seats().size() : unchosen.get() + chosen.get()); index++) {
            final Menu menu = popup.getMenu();
            final int number = index + 1;
            final String title = id == R.id.set_tickets && model.tickets() == number ? "Current " : "";
            menu.add(id, number, index, String.format("%s%s", title, number));
          }
          popup.setOnMenuItemClickListener(item -> {
            switch (id) {
              case R.id.seat_click:
              case R.id.seat_long_click:
                final Seat last = model.seats().get(item.getOrder());
                final Class<? extends Seat> lastClass = last.getClass();
                changeSeat(last, id == R.id.seat_click ? swapSeat(lastClass) : swapSeatAlt(lastClass));
                break;

              case R.id.set_tickets:
                setModel(Theatre.tickets(model, item.getItemId()));
                if (model.tickets() < chosen.get()) changeSeats(UnChosenSeat.class);
                break;
            }
            return true;
          });
          popup.show();
          break;

        case R.id.change_unchosen_to_chosen:
          if (model.tickets() == chosen.get()) toaster.show(R.string.no_tickets_left);
          else if (unchosen.get() > 0) changeSeats(ChosenSeat.class);
          else toaster.show(R.string.no_unchosen_seats);
          break;

        case R.id.change_chosen_to_unchosen:
        case R.id.change_chosen_to_reserved:
        case R.id.change_chosen_to_empty:
          if (hasChosen()) changeSeats(id == R.id.change_chosen_to_unchosen ? UnChosenSeat.class
              : id == R.id.change_chosen_to_reserved ? ReservedSeat.class : EmptySeat.class);
          else toaster.show(R.string.no_chosen_seats);
          break;
      }
    }

    private void changeSeat(Seat last, Class<? extends Seat> nextClass) {
      if (model.tickets() <= chosen.get() && ChosenSeat.check(nextClass)) toaster.show(R.string.no_tickets_left);
      else setModel(Theatre.seat(model, adapter.replace(last, nextClass), last.getClass(), nextClass));
    }

    private void changeSeats(Class<? extends Seat> nextClass) {
      final boolean nextChosen = ChosenSeat.check(nextClass);
      final Seat[] lasts = new Seat[nextChosen ? unchosen.get() : chosen.get()];
      int total = 0, changes = total;
      final List<Seat> seats = model.seats();
      final int size = seats.size();
      if (total < size) {
        do {
          final Seat last = seats.get(total);
          final Class<? extends Seat> lastClass = last.getClass();
          if ((nextChosen ? UnChosenSeat.check(lastClass) : ChosenSeat.check(lastClass)) && (!nextChosen
              || chosen.get() + changes < model.tickets())) {
            lasts[changes] = last;
            changes++;
          }
          total++;
        } while (total < size);
      }
      for (int index = 0; index < changes; index++) changeSeat(lasts[index], nextClass);
    }

    private void reset() {
      setModel(Theatre.reset(model, TheatreUtils.seats()));
      adapter.setModels(model.seats());
    }

    private boolean hasChosen() {
      return chosen.get() > 0;
    }
  }

  @dagger.Module(includes = { SeatAdapter.Module.class }) public abstract class Module {
    @ForActivity @Binds abstract BaseActivity baseActivity(TheatreActivity activity);
  }
}
