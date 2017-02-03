package io.github.lamtran.moviebooking;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import javax.inject.Inject;

import io.github.lamtran.moviebooking.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

  @Inject
  SeatAdapter mSeatAdapter;

  private ActivityMainBinding mBinding;

  private MainViewModel mViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    mBinding.setViewModel(mViewModel);

    mBinding.list.setLayoutManager(new LinearLayoutManager(this));
    mBinding.list.setAdapter(mSeatAdapter);
  }
}
