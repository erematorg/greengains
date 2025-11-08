package com.google.android.gms.common.data;

import androidx.annotation.NonNull;

public interface Freezable<T> {
    @NonNull
    T freeze();

    boolean isDataValid();
}
