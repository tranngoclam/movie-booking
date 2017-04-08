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

import dagger.Binds;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntKey;
import dagger.multibindings.IntoMap;
import io.github.lamtran.moviebooking.di.ActivityScope;
import io.github.lamtran.moviebooking.model.Seat;
import io.github.lamtran.moviebooking.ui.viewholder.AvailableSeatViewHolder;
import io.github.lamtran.moviebooking.ui.viewholder.EmptySeatViewHolder;
import io.github.lamtran.moviebooking.ui.viewholder.ReservedSeatViewHolder;
import io.github.lamtran.moviebooking.ui.viewholder.SeatViewHolderFactory;
import io.github.lamtran.moviebooking.ui.viewholder.SelectedSeatViewHolder;

/**
 * Created by lam on 2/3/17.
 */
@ActivityScope @Subcomponent(modules = {MainComponent.Module.class}) public interface MainComponent extends AndroidInjector<MainActivity> {
  @Subcomponent.Builder abstract class Builder extends AndroidInjector.Builder<MainActivity> {}

  @dagger.Module abstract class Module {

    @ActivityScope @Binds abstract BaseActivity activity(MainActivity activity);

    @Binds @IntoMap @IntKey(Seat.Type.AVAILABLE)
    abstract SeatViewHolderFactory availableSeatViewHolder(AvailableSeatViewHolder.Factory factory);

    @Binds @IntoMap @IntKey(Seat.Type.EMPTY)
    abstract SeatViewHolderFactory emptySeatViewHolder(EmptySeatViewHolder.Factory factory);

    @Binds @IntoMap @IntKey(Seat.Type.RESERVED)
    abstract SeatViewHolderFactory reservedSeatViewHolder(ReservedSeatViewHolder.Factory factory);

    @Binds @IntoMap @IntKey(Seat.Type.SELECTED)
    abstract SeatViewHolderFactory selectedSeatViewHolder(SelectedSeatViewHolder.Factory factory);

  }
}