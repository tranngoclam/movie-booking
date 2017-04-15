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

package io.github.lamtran.moviebooking.util;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lam on 2/4/17.
 */

public abstract class SelectableAdapter<VH extends RecyclerView.ViewHolder>
    extends RecyclerView.Adapter<VH> implements Selectable {

  private final SparseBooleanArray mSelectedItems;

  protected OnMaxSelectionReachedListener mOnMaxSelectionReachedListener;

  private int mMaxSelection = Integer.MAX_VALUE;

  protected SelectableAdapter() {
    mSelectedItems = new SparseBooleanArray();
  }

  @Override public void clearSelection() {
    List<Integer> items = getSelectedItems();
    mSelectedItems.clear();
    for (Integer item : items) {
      notifyItemChanged(item);
    }
  }

  @Override public int getSelectedItemCount() {
    return mSelectedItems.size();
  }

  @Override public List<Integer> getSelectedItems() {
    int size = getSelectedItemCount();
    List<Integer> items = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      items.add(mSelectedItems.keyAt(i));
    }
    return items;
  }

  @Override public boolean isMaxSelectionReached() {
    return getSelectedItemCount() == mMaxSelection;
  }

  @Override public boolean isSelected(int position) {
    return getSelectedItems().contains(position);
  }

  @Override public boolean toggleSelection(int position) {
    if (mSelectedItems.get(position, false)) {
      mSelectedItems.delete(position);
      return true;
    } else {
      if (getSelectedItemCount() == mMaxSelection && mOnMaxSelectionReachedListener != null) {
        return false;
      } else {
        mSelectedItems.put(position, true);
        return true;
      }
    }
  }

  public void setMaxSelection(int maxSelection) {
    mMaxSelection = maxSelection;
  }

  public void setOnMaxSelectionReachedListener(
      OnMaxSelectionReachedListener onMaxSelectionReachedListener) {
    mOnMaxSelectionReachedListener = onMaxSelectionReachedListener;
  }
}
