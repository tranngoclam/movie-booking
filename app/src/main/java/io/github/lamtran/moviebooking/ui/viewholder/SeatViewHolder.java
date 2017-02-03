package io.github.lamtran.moviebooking.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.github.lamtran.moviebooking.Seat;

/**
 * Created by lam on 2/3/17.
 */

public abstract class SeatViewHolder extends RecyclerView.ViewHolder {

  public abstract void bind(Seat seat);

  public SeatViewHolder(View itemView) {
    super(itemView);
  }
}
