package com.bumptech.glide.load.resource.gif;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;

public final class GifBitmapProvider implements GifDecoder.BitmapProvider {
    @Nullable
    private final ArrayPool arrayPool;
    private final BitmapPool bitmapPool;

    public GifBitmapProvider(BitmapPool bitmapPool2) {
        this(bitmapPool2, (ArrayPool) null);
    }

    @NonNull
    public Bitmap obtain(int i3, int i4, @NonNull Bitmap.Config config) {
        return this.bitmapPool.getDirty(i3, i4, config);
    }

    @NonNull
    public byte[] obtainByteArray(int i3) {
        ArrayPool arrayPool2 = this.arrayPool;
        return arrayPool2 == null ? new byte[i3] : (byte[]) arrayPool2.get(i3, byte[].class);
    }

    @NonNull
    public int[] obtainIntArray(int i3) {
        ArrayPool arrayPool2 = this.arrayPool;
        return arrayPool2 == null ? new int[i3] : (int[]) arrayPool2.get(i3, int[].class);
    }

    public void release(@NonNull Bitmap bitmap) {
        this.bitmapPool.put(bitmap);
    }

    public GifBitmapProvider(BitmapPool bitmapPool2, @Nullable ArrayPool arrayPool2) {
        this.bitmapPool = bitmapPool2;
        this.arrayPool = arrayPool2;
    }

    public void release(@NonNull byte[] bArr) {
        ArrayPool arrayPool2 = this.arrayPool;
        if (arrayPool2 != null) {
            arrayPool2.put(bArr);
        }
    }

    public void release(@NonNull int[] iArr) {
        ArrayPool arrayPool2 = this.arrayPool;
        if (arrayPool2 != null) {
            arrayPool2.put(iArr);
        }
    }
}
