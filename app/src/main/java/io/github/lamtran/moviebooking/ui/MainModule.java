package io.github.lamtran.moviebooking.ui;

import java.util.Map;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntKey;
import dagger.multibindings.IntoMap;
import io.github.lamtran.moviebooking.Seat;
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
  @IntKey(Seat.TYPE_AVAILABLE)
  SeatViewHolderFactory provideAvailableSeatViewHolder() {
    return new AvailableSeatViewHolderFactory();
  }

  @Provides
  @IntoMap
  @IntKey(Seat.TYPE_EMPTY)
  SeatViewHolderFactory provideEmptySeatViewHolder() {
    return new EmptySeatViewHolderFactory();
  }

  @ActivityScope
  @Provides
  MainViewModel provideMainViewModel(Map<Integer, SeatViewHolderFactory> seatViewHolderFactoryMap) {
    return new MainViewModel(seatViewHolderFactoryMap);
  }

  @Provides
  @IntoMap
  @IntKey(Seat.TYPE_RESERVED)
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
  @IntKey(Seat.TYPE_SELECTED)
  SeatViewHolderFactory provideSelectedSeatViewHolder() {
    return new SelectedSeatViewHolderFactory();
  }
}
