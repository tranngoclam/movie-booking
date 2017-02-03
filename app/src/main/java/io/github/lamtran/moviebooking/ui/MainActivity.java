package io.github.lamtran.moviebooking.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import javax.inject.Inject;

import io.github.lamtran.moviebooking.R;
import io.github.lamtran.moviebooking.SeatAdapter;
import io.github.lamtran.moviebooking.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

  private static final int COL_SIZE = 5;

  private static final int ROW_SIZE = 5;

  @Inject
  SeatAdapter mSeatAdapter;

  private ActivityMainBinding mBinding;

  private GridLayoutManager mLayoutManager;

  private MainViewModel mViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mViewModel = getActivityComponent().mainComponent(new MainModule()).mainViewModel();

    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    mBinding.setViewModel(mViewModel);

    mLayoutManager = new GridLayoutManager(this, COL_SIZE);
    mBinding.list.setLayoutManager(mLayoutManager);
    mBinding.list.setAdapter(mSeatAdapter);
  }
}
