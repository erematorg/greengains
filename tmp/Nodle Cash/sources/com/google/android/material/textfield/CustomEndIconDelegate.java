package com.google.android.material.textfield;

import android.view.View;
import androidx.annotation.NonNull;

class CustomEndIconDelegate extends EndIconDelegate {
    public CustomEndIconDelegate(@NonNull EndCompoundLayout endCompoundLayout) {
        super(endCompoundLayout);
    }

    public void setUp() {
        this.endLayout.setEndIconOnLongClickListener((View.OnLongClickListener) null);
    }
}
