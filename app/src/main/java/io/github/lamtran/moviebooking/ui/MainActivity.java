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
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import io.github.lamtran.moviebooking.R;
import io.github.lamtran.moviebooking.databinding.ActivityMainBinding;
import io.github.lamtran.moviebooking.util.AppUtils;
import io.github.lamtran.moviebooking.util.ItemOffsetDecoration;

public class MainActivity extends BaseActivity {

  private ActivityMainBinding mBinding;

  private GridLayoutManager mLayoutManager;

  private MainViewModel mViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mViewModel = getActivityComponent().mainComponent(new MainModule(AppUtils.MAX_SELECTION)).mainViewModel();

    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    mBinding.setViewModel(mViewModel);

    mLayoutManager = new GridLayoutManager(this, AppUtils.SIZE);
    mBinding.list.addItemDecoration(new ItemOffsetDecoration(this, R.dimen.paddingOffset));
    mBinding.list.setLayoutManager(mLayoutManager);
  }
}
