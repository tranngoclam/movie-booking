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

import android.app.Activity;
import android.support.annotation.StringRes;
import android.widget.Toast;

import javax.inject.Inject;

import io.github.lamtran.moviebooking.di.ActivityScope;
import io.github.lamtran.moviebooking.ui.BaseActivity;

/**
 * Created by lam on 2/4/17.
 */

@ActivityScope public class Toaster {

  private final Activity mActivity;

@Inject
public Toaster(BaseActivity activity) {
    mActivity = activity;
  }

  public void showLongToast(String text) {
    Toast.makeText(mActivity, text, Toast.LENGTH_LONG).show();
  }

  public void showLongToast(@StringRes int resId) {
    Toast.makeText(mActivity, resId, Toast.LENGTH_LONG).show();
  }

  public void showShortToast(@StringRes int resId) {
    Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
  }

  public void showShortToast(String text) {
    Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show();
  }

  public void showShortToast(@StringRes int resId, Object... formatArgs) {
    Toast.makeText(mActivity, mActivity.getString(resId, formatArgs), Toast.LENGTH_SHORT).show();
    ;
  }
}
