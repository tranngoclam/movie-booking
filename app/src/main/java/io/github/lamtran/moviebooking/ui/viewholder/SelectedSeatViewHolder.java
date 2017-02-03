package io.github.lamtran.moviebooking.ui.viewholder;

import com.google.auto.factory.AutoFactory;

import android.view.View;

import io.github.lamtran.moviebooking.Seat;

/**
 * Created by lam on 2/3/17.
 */
@AutoFactory(implementing = SeatViewHolderFactory.class)
public class SelectedSeatViewHolder extends SeatViewHolder {

  public SelectedSeatViewHolder(View itemView) {
    super(itemView);
  }

  @Override
  public void bind(Seat seat) {

  }
}
