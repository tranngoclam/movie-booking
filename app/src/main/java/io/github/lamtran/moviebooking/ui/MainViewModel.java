package io.github.lamtran.moviebooking.ui;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.github.lamtran.moviebooking.Seat;
import io.github.lamtran.moviebooking.model.state.EmptyState;
import io.github.lamtran.moviebooking.ui.viewholder.SeatViewHolderFactory;

/**
 * Created by lam on 2/3/17.
 */

public class MainViewModel extends BaseObservable {

  public final ObservableMap<Integer, SeatViewHolderFactory> factory = new ObservableArrayMap<>();

  public final ObservableArrayList<Seat> seats = new ObservableArrayList<>();

  public MainViewModel(Map<Integer, SeatViewHolderFactory> seatViewHolderFactoryMap) {
    factory.clear();
    factory.putAll(seatViewHolderFactoryMap);
  }

  private List<Seat> fakeData(int size) {
    List<Seat> seats = new ArrayList<>(size * size);
    seats.add(new Seat(0, 0, new EmptyState()));

    return seats;
  }
}
