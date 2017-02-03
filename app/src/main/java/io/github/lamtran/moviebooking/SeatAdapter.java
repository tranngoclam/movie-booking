package io.github.lamtran.moviebooking;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.github.lamtran.moviebooking.model.state.AvailableState;
import io.github.lamtran.moviebooking.model.state.EmptyState;
import io.github.lamtran.moviebooking.model.state.ReservedState;
import io.github.lamtran.moviebooking.model.state.SelectedState;
import io.github.lamtran.moviebooking.model.state.State;
import io.github.lamtran.moviebooking.ui.viewholder.SeatViewHolder;
import io.github.lamtran.moviebooking.ui.viewholder.SeatViewHolderFactory;

/**
 * Created by lam on 2/3/17.
 */

public class SeatAdapter extends RecyclerView.Adapter<SeatViewHolder> {

  private final Map<Integer, SeatViewHolderFactory> mSeatViewHolderFactoryMap;

  private List<Seat> mSeats;

  public SeatAdapter(Map<Integer, SeatViewHolderFactory> seatViewHolderFactoryMap) {
    mSeats = new ArrayList<>();
    mSeatViewHolderFactoryMap = seatViewHolderFactoryMap;
  }

  @Override
  public int getItemCount() {
    return mSeats.size();
  }

  @Override
  public int getItemViewType(int position) {
    Seat seat = mSeats.get(position);
    State state = seat.getState();
    if (state instanceof EmptyState) {
      return Seat.TYPE_EMPTY;
    } else if (state instanceof ReservedState) {
      return Seat.TYPE_RESERVED;
    } else if (state instanceof AvailableState) {
      return Seat.TYPE_AVAILABLE;
    } else if (state instanceof SelectedState) {
      return Seat.TYPE_SELECTED;
    } else {
      return Seat.TYPE_UNKNOWN;
    }
  }

  @Override
  public void onBindViewHolder(SeatViewHolder holder, int position) {
    holder.bind(mSeats.get(position));
  }

  @Override
  public SeatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return mSeatViewHolderFactoryMap.get(viewType).createViewHolder(parent);
  }

  public void setSeats(List<Seat> seats) {
    mSeats = seats;
    notifyDataSetChanged();
  }
}
