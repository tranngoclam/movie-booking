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
package io.github.lamtran.moviebooking.presentation.view.base.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import io.github.lamtran.moviebooking.data.model.BaseModel;
import io.github.lamtran.moviebooking.presentation.view.base.HasBoundData;
import io.github.lamtran.moviebooking.presentation.view.base.controller.BaseController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;

import static android.view.LayoutInflater.from;

/**
 * Author: JONATHAN MERRITT
 * Year: 2017
 * Contact: 11R00TT00RR11@GMAIL.COM
 */
public abstract class BaseAdapter<M extends BaseModel, C extends BaseAdapter.Holder.Controller<M>>
    extends Adapter<BaseAdapter.Holder<M, ? extends ViewDataBinding, C>> {
  private final Map<Integer, Provider<IsHolderFactory>> factories;
  private final List<M> models;

  protected BaseAdapter(Map<Integer, Provider<IsHolderFactory>> factories) {
    this.factories = factories;
    models = new ArrayList<>();
  }

  protected M getModel(int position) {
    return models.get(position);
  }

  @Override public int getItemCount() {
    return models.size();
  }

  @Override public Holder<M, ? extends ViewDataBinding, C> onCreateViewHolder(ViewGroup group, int type) {
    //noinspection unchecked
    return factories.get(type).get().create(from(group.getContext()).inflate(type, group, false));
  }

  @Override public void onBindViewHolder(Holder<M, ? extends ViewDataBinding, C> holder, int position) {
    holder.onBind(models.get(position));
  }

  public void setModels(List<M> models) {
    this.models.clear();
    this.models.addAll(models);
    notifyDataSetChanged();
  }

  protected List<M> replace(int index, M remove, M add) {
    if (models.contains(remove)) models.remove(remove);
    if (!models.contains(add)) models.add(index, add);
    if (getModel(index).equals(add)) notifyItemChanged(index);
    return models;
  }

  public static abstract class Holder<M extends BaseModel, B extends ViewDataBinding, C extends Holder.Controller<M>>
      extends ViewHolder implements HasBoundData<B> {
    protected final C controller;
    private final B binding;

    public Holder(B binding, C controller) {
      super(binding.getRoot());
      this.binding = binding;
      this.controller = controller;
    }

    void onBind(M model) {
      controller.setModel(model);
      setBindings(binding);
      binding.executePendingBindings();
    }

    @Override public B getBinding() {
      return binding;
    }

    public abstract static class Factory<M extends BaseModel, C extends Controller<M>> implements IsHolderFactory {
      protected final C controller;
      protected Factory(C controller) {
        this.controller = controller;
      }
    }

    public abstract static class Controller<M extends BaseModel> extends BaseController<M> {
    }
  }
}