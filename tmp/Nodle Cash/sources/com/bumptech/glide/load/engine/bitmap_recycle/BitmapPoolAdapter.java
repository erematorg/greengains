package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;

public class BitmapPoolAdapter implements BitmapPool {
    public void clearMemory() {
    }

    @NonNull
    public Bitmap get(int i3, int i4, Bitmap.Config config) {
        return Bitmap.createBitmap(i3, i4, config);
    }

    @NonNull
    public Bitmap getDirty(int i3, int i4, Bitmap.Config config) {
        return get(i3, i4, config);
    }

    public long getMaxSize() {
        return 0;
    }

    public void put(Bitmap bitmap) {
        bitmap.recycle();
    }

    public void setSizeMultiplier(float f2) {
    }

    public void trimMemory(int i3) {
    }
}
