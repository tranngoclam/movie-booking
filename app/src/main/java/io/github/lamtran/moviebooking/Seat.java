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
