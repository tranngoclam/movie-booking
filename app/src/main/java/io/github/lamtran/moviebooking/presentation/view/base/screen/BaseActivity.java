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
package io.github.lamtran.moviebooking.presentation.view.base.screen;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import dagger.android.support.DaggerAppCompatActivity;
import io.github.lamtran.moviebooking.data.model.BaseModel;
import io.github.lamtran.moviebooking.presentation.view.base.HasBoundData;
import javax.inject.Inject;

/**
 * Author: JONATHAN MERRITT
 * Year: 2017
 * Contact: 11R00TT00RR11@GMAIL.COM
 */
public abstract class BaseActivity<B extends ViewDataBinding, C extends BaseActivity.Controller>
    extends DaggerAppCompatActivity implements IsScreen, HasBoundData<B> {
  @Inject protected C controller;
  private B binding;
  @Override protected void onCreate(Bundle createdState) {
    binding = DataBindingUtil.setContentView(this, getLayout());
    super.onCreate(createdState);
    if (createdState == null) controller.onStart();
    setBindings(binding);
  }

  @Override public void onSaveInstanceState(Bundle savedState) {
    controller.onSave(savedState);
    super.onSaveInstanceState(savedState);
  }

  @Override public void onRestoreInstanceState(Bundle restoredState) {
    if (restoredState != null) controller.onRestore(restoredState);
    super.onRestoreInstanceState(restoredState);
  }

  @Override public B getBinding() {
    return binding;
  }

  public abstract static class Controller<M extends BaseModel> extends ScreenController<M> {

    @Override public void onSave(Bundle savedState) {
      super.onSave(savedState);
      model.save(savedState, getClass().getDeclaringClass().getSimpleName());
    }

    @Override public void onRestore(Bundle restoredState) {
      super.onRestore(restoredState);
      setModel(BaseModel.restore(restoredState, getClass().getDeclaringClass().getSimpleName()));
    }
  }
}
