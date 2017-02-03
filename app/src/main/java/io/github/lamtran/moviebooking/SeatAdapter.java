package io.github.lamtran.moviebooking;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.github.lamtran.moviebooking.ui.viewholder.SeatViewHolder;

/**
 * Created by lam on 2/3/17.
 */

public class SeatAdapter extends RecyclerView.Adapter<SeatViewHolder> {

  private List<Seat> mSeats;

  public SeatAdapter() {
    mSeats = new ArrayList<>();
  }

  @Override
  public int getItemCount() {
    return mSeats.size();
  }

  @Override
  public void onBindViewHolder(SeatViewHolder holder, int position) {

  }

  @Override
  public SeatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override
  public int getItemViewType(int position) {
    Seat seat = mSeats.get(position);


    return super.getItemViewType(position);
  }
}
