package io.github.lamtran.moviebooking.ui;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import io.github.lamtran.moviebooking.Seat;
import io.github.lamtran.moviebooking.SeatAdapter;
import io.github.lamtran.moviebooking.util.AppUtils;

/**
 * Created by lam on 2/3/17.
 */

public class MainViewModel extends BaseObservable {

  public final ObservableField<SeatAdapter> adapter = new ObservableField<>();

  public final ObservableList<Seat> seats = new ObservableArrayList<>();

  public MainViewModel(SeatAdapter adapter) {
    this.adapter.set(adapter);
    this.seats.clear();
    this.seats.addAll(AppUtils.fakeSeats());
  }
}
