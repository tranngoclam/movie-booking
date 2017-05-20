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
package io.github.lamtran.moviebooking.presentation.view.theatre.seat;

import android.databinding.ObservableInt;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import dagger.Binds;
import dagger.multibindings.IntKey;
import dagger.multibindings.IntoMap;
import io.github.lamtran.moviebooking.R;
import io.github.lamtran.moviebooking.data.model.theatre.seat.Seat;
import io.github.lamtran.moviebooking.internal.injection.scope.ForActivity;
import io.github.lamtran.moviebooking.presentation.util.TheatreUtils;
import io.github.lamtran.moviebooking.presentation.view.base.adapter.BaseAdapter;
import io.github.lamtran.moviebooking.presentation.view.base.adapter.IsHolderFactory;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Author: JONATHAN MERRITT
 * Year: 2017
 * Contact: 11R00TT00RR11@GMAIL.COM
 */
@ForActivity public class SeatAdapter extends BaseAdapter<Seat, SeatAdapter.Holder.Controller<Seat>> {

  @Inject public SeatAdapter(Map<Integer, Provider<IsHolderFactory>> factories) {
    super(factories);
  }
  public List<Seat> replace(Seat last, Class<? extends Seat> nextClass) {
    return replace(last.number() - 1, last, TheatreUtils.resolveSeat(last, nextClass));
  }
  @Override public int getItemViewType(int position) {
    return TheatreUtils.resolveLayout(getModel(position).getClass());
  }
  @Override public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    final RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
    animator.setChangeDuration(0);
    animator.setAddDuration(0);
    animator.setRemoveDuration(0);
    animator.setMoveDuration(0);
    super.onAttachedToRecyclerView(recyclerView);
  }

  public abstract static class Holder<S extends Seat, B extends ViewDataBinding, C extends Holder.Controller<S>>
      extends BaseAdapter.Holder<S, B, C> {
    Holder(B binding, C controller) {
      super(binding, controller);
    }

    static abstract class Factory<S extends Seat, C extends Controller<S>>
        extends BaseAdapter.Holder.Factory<S, C> {
      Factory(C controller) {
        super(controller);
      }
    }

    public abstract static class Controller<S extends Seat> extends BaseAdapter.Holder.Controller<S> {
      public final ObservableInt number = new ObservableInt();
      private final SeatChanger changer;

      public Controller(SeatChanger changer) {
        super();
        this.changer = changer;
      }

      @Override public void setModel(S seat) {
        super.setModel(seat);
        number.set(seat.number());
      }

      @Override public void onClick(View view) {
        super.onClick(view);
        changer.request(model, TheatreUtils.swapSeat(model.getClass()));
      }

      @Override public void onLongClick(View view) {
        super.onLongClick(view);
        changer.request(model, TheatreUtils.swapSeatAlt(model.getClass()));
      }
    }
  }
  @dagger.Module public abstract class Module {
    @Binds @IntoMap @IntKey(R.layout.holder_unchosen_seat)
    abstract IsHolderFactory unchosenSeatFactory(UnchosenSeatHolder.Factory holderFactory);

    @Binds @IntoMap @IntKey(R.layout.holder_chosen_seat)
    abstract IsHolderFactory chosenSeatFactory(ChosenSeatHolder.Factory holderFactory);

    @Binds @IntoMap @IntKey(R.layout.holder_reserved_seat)
    abstract IsHolderFactory reservedSeatFactory(ReservedSeatHolder.Factory holderFactory);

    @Binds @IntoMap @IntKey(R.layout.holder_empty_seat)
    abstract IsHolderFactory emptySeatFactory(EmptySeatHolder.Factory holderFactory);
  }
}
