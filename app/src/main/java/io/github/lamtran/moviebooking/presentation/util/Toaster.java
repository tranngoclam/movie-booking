package io.github.lamtran.moviebooking.presentation.util;

import android.content.Context;
import android.widget.Toast;

import io.github.lamtran.moviebooking.presentation.view.base.screen.BaseActivity;

/**
 * Author: JONATHAN MERRITT
 * Year: 2017
 * Contact: 11R00TT00RR11@GMAIL.COM
 */

public class Toaster {
    private final Context context;

    Toaster(BaseActivity activity) {
        context = activity;
    }

    public void show(int stringId) {
        Toast.makeText(context, context.getString(stringId), Toast.LENGTH_SHORT).show();
    }
}
