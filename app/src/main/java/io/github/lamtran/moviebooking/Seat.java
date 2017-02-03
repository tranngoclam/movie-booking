package io.github.lamtran.moviebooking;

import io.github.lamtran.moviebooking.model.state.State;

/**
 * Created by lam on 2/3/17.
 */

public class Seat {

  public static final int TYPE_AVAILABLE = 2;

  public static final int TYPE_EMPTY = 0;

  public static final int TYPE_RESERVED = 1;

  public static final int TYPE_SELECTED = 3;

  public static final int TYPE_UNKNOWN = -1;

  private final int mCol;

  private final int mRow;

  private final State mState;

  public Seat(int col, int row, State state) {
    mCol = col;
    mRow = row;
    mState = state;
  }

  public int getCol() {
    return mCol;
  }

  public int getRow() {
    return mRow;
  }

  public State getState() {
    return mState;
  }
}
