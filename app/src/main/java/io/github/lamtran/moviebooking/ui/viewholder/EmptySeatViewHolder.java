package io.github.lamtran.moviebooking.ui.viewholder;

import com.google.auto.factory.AutoFactory;

import android.databinding.ViewDataBinding;
import android.util.Log;

import io.github.lamtran.moviebooking.Seat;

/**
 * Created by lam on 2/3/17.
 */
@AutoFactory(implementing = SeatViewHolderFactory.class)
public class EmptySeatViewHolder extends SeatViewHolder {

  public EmptySeatViewHolder(ViewDataBinding binding) {
    super(binding);
  }

  @Override
  public void bind(Seat seat) {
    if (seat != null) {
      Log.d("TAG", seat.getCol() + ", " + seat.getRow());
    }
  }
}
