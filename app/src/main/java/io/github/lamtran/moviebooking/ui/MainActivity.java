package io.github.lamtran.moviebooking.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import io.github.lamtran.moviebooking.R;
import io.github.lamtran.moviebooking.databinding.ActivityMainBinding;
import io.github.lamtran.moviebooking.util.AppUtils;

public class MainActivity extends BaseActivity {

  private ActivityMainBinding mBinding;

  private GridLayoutManager mLayoutManager;

  private MainViewModel mViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mViewModel = getActivityComponent().mainComponent(new MainModule()).mainViewModel();

    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    mBinding.setViewModel(mViewModel);

    mLayoutManager = new GridLayoutManager(this, AppUtils.SIZE);
    mBinding.list.setLayoutManager(mLayoutManager);
  }
}
