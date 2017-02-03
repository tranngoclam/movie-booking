package io.github.lamtran.moviebooking;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import io.github.lamtran.moviebooking.model.state.State;

/**
 * Created by lam on 2/3/17.
 */

public final class BindingUtils {

  @BindingAdapter({"adapter", "seats"})
  public static void setSeats(RecyclerView recyclerView, SeatAdapter adapter, List<Seat> seats) {
    if (recyclerView.getAdapter() == null) {
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
