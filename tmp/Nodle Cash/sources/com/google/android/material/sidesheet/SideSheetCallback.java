package com.google.android.material.sidesheet;

import android.view.View;
import androidx.annotation.NonNull;

public abstract class SideSheetCallback implements SheetCallback {
    public void onLayout(@NonNull View view) {
    }

    public abstract void onSlide(@NonNull View view, float f2);

    public abstract void onStateChanged(@NonNull View view, int i3);
}
