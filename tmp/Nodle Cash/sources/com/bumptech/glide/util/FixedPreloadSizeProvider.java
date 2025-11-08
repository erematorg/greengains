package com.bumptech.glide.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.ListPreloader;

public class FixedPreloadSizeProvider<T> implements ListPreloader.PreloadSizeProvider<T> {
    private final int[] size;

    public FixedPreloadSizeProvider(int i3, int i4) {
        this.size = new int[]{i3, i4};
    }

    @Nullable
    public int[] getPreloadSize(@NonNull T t2, int i3, int i4) {
        return this.size;
    }
}
