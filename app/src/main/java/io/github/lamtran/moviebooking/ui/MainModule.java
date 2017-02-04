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

import java.util.Map;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntKey;
import dagger.multibindings.IntoMap;
import io.github.lamtran.moviebooking.model.Seat;
import io.github.lamtran.moviebooking.SeatAdapter;
import io.github.lamtran.moviebooking.di.ActivityScope;
import io.github.lamtran.moviebooking.ui.viewholder.AvailableSeatViewHolderFactory;
import io.github.lamtran.moviebooking.ui.viewholder.EmptySeatViewHolderFactory;
import io.github.lamtran.moviebooking.ui.viewholder.ReservedSeatViewHolderFactory;
import io.github.lamtran.moviebooking.ui.viewholder.SeatViewHolderFactory;
import io.github.lamtran.moviebooking.ui.viewholder.SelectedSeatViewHolderFactory;

/**
 * Created by lam on 2/3/17.
 */
@Module
public class MainModule {

  @Provides
  @IntoMap
  @IntKey(Seat.Type.AVAILABLE)
  SeatViewHolderFactory provideAvailableSeatViewHolder() {
    return new AvailableSeatViewHolderFactory();
  }

  @Provides
  @IntoMap
  @IntKey(Seat.Type.EMPTY)
  SeatViewHolderFactory provideEmptySeatViewHolder() {
    return new EmptySeatViewHolderFactory();
  }

  @ActivityScope
  @Provides
  MainViewModel provideMainViewModel(SeatAdapter seatAdapter) {
    return new MainViewModel(seatAdapter);
  }

  @Provides
  @IntoMap
  @IntKey(Seat.Type.RESERVED)
  SeatViewHolderFactory provideReservedSeatViewHolder() {
    return new ReservedSeatViewHolderFactory();
  }

  @ActivityScope
  @Provides
  SeatAdapter provideSeatAdapter(Map<Integer, SeatViewHolderFactory> seatViewHolderFactoryMap) {
    return new SeatAdapter(seatViewHolderFactoryMap);
  }

  @Provides
  @IntoMap
  @IntKey(Seat.Type.SELECTED)
  SeatViewHolderFactory provideSelectedSeatViewHolder() {
    return new SelectedSeatViewHolderFactory();
  }
}
