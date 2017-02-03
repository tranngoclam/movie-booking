package io.github.lamtran.moviebooking;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;
import java.util.Map;

import io.github.lamtran.moviebooking.model.state.State;
import io.github.lamtran.moviebooking.ui.viewholder.SeatViewHolderFactory;

/**
 * Created by lam on 2/3/17.
 */

public final class BindingUtils {

  @BindingAdapter({"seats", "factory"})
  public static void setSeats(RecyclerView recyclerView, List<Seat> seats, Map<Integer, SeatViewHolderFactory> seatViewHolderFactoryMap) {
    SeatAdapter adapter = (SeatAdapter) recyclerView.getAdapter();
    if (adapter == null) {
      adapter = new SeatAdapter(seatViewHolderFactoryMap);
      recyclerView.setAdapter(adapter);
    }
    adapter.setSeats(seats);
  }

  @BindingAdapter("state")
  public static void setState(View view, State state) {

  }

  private BindingUtils() {
  }
}
