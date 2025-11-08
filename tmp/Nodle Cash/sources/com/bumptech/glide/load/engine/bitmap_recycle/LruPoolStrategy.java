package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import androidx.annotation.Nullable;

interface LruPoolStrategy {
    @Nullable
    Bitmap get(int i3, int i4, Bitmap.Config config);

    int getSize(Bitmap bitmap);

    String logBitmap(int i3, int i4, Bitmap.Config config);

    String logBitmap(Bitmap bitmap);

    void put(Bitmap bitmap);

    @Nullable
    Bitmap removeLast();
}
