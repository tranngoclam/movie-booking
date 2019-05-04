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
package io.github.lamtran.moviebooking.presentation.view.base.screen

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import io.github.lamtran.moviebooking.data.model.BaseModel
import io.github.lamtran.moviebooking.presentation.view.base.HasBoundData

abstract class BaseActivity<B : ViewDataBinding, C : BaseActivity.Controller<*>> : AppCompatActivity(), IsScreen, HasBoundData<B> {

    var controller: C? = null

    private var binding: B? = null

    override fun onCreate(createdState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, layout)
        super.onCreate(createdState)
        if (createdState == null) controller!!.onStart()
        setBindings(binding)
    }

    public override fun onSaveInstanceState(savedState: Bundle) {
        controller!!.onSave(savedState)
        super.onSaveInstanceState(savedState)
    }

    public override fun onRestoreInstanceState(restoredState: Bundle?) {
        if (restoredState != null) controller!!.onRestore(restoredState)
        super.onRestoreInstanceState(restoredState)
    }

    override fun getBinding(): B? {
        return binding
    }

    abstract class Controller<M : BaseModel> : ScreenController<M>() {

        override fun onSave(savedState: Bundle) {
            super.onSave(savedState)
            model.save(savedState, javaClass.declaringClass!!.simpleName)
        }

        override fun onRestore(restoredState: Bundle) {
            super.onRestore(restoredState)
            setModel(BaseModel.restore(restoredState, javaClass.declaringClass!!.simpleName))
        }
    }
}
