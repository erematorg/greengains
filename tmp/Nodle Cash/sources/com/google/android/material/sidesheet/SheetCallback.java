package com.google.android.material.sidesheet;

import android.view.View;
import androidx.annotation.NonNull;

interface SheetCallback {
    void onSlide(@NonNull View view, float f2);

    void onStateChanged(@NonNull View view, int i3);
}
