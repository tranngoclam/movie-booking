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

package io.github.lamtran.moviebooking.ui;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.github.lamtran.moviebooking.R;
import io.github.lamtran.moviebooking.databinding.ItemSeatBinding;
import io.github.lamtran.moviebooking.model.Seat;
import io.github.lamtran.moviebooking.model.state.AvailableState;
import io.github.lamtran.moviebooking.model.state.EmptyState;
import io.github.lamtran.moviebooking.model.state.ReservedState;
import io.github.lamtran.moviebooking.model.state.SelectedState;
import io.github.lamtran.moviebooking.model.state.State;
import io.github.lamtran.moviebooking.ui.viewholder.SeatViewHolder;
import io.github.lamtran.moviebooking.ui.viewholder.SeatViewHolderFactory;
import io.github.lamtran.moviebooking.util.SelectableAdapter;

/**
 * Created by lam on 2/3/17.
 */

public class SeatAdapter extends SelectableAdapter<SeatViewHolder> {

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
      return Seat.Type.EMPTY;
    } else if (state instanceof ReservedState) {
      return Seat.Type.RESERVED;
    } else if (state instanceof AvailableState) {
      return Seat.Type.AVAILABLE;
    } else if (state instanceof SelectedState) {
      return Seat.Type.SELECTED;
    } else {
      return Seat.Type.UNKNOWN;
    }
  }

  @Override
  public void onBindViewHolder(SeatViewHolder holder, int position) {
    holder.binding.setData(mSeats.get(position));
    holder.binding.setAction(seat -> {
      if (seat.isStateSelectable()) {
        if (toggleSelection(position)) {
          seat.changeState();
        } else {
          mOnMaxSelectionReachedListener.onMaxSelectionReached();
        }
      }
    });
  }

  @Override
  public SeatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    ItemSeatBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_seat, parent, false);
    return mSeatViewHolderFactoryMap.get(viewType).createViewHolder(binding);
  }

  public void setSeats(List<Seat> seats) {
    mSeats.clear();
    mSeats.addAll(seats);
    notifyDataSetChanged();
  }
}
