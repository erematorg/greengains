package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.camera.core.impl.i;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Util;

public abstract class BitmapTransformation implements Transformation<Bitmap> {
    public abstract Bitmap transform(@NonNull BitmapPool bitmapPool, @NonNull Bitmap bitmap, int i3, int i4);

    @NonNull
    public final Resource<Bitmap> transform(@NonNull Context context, @NonNull Resource<Bitmap> resource, int i3, int i4) {
        if (Util.isValidDimensions(i3, i4)) {
            BitmapPool bitmapPool = Glide.get(context).getBitmapPool();
            Bitmap bitmap = resource.get();
            if (i3 == Integer.MIN_VALUE) {
                i3 = bitmap.getWidth();
            }
            if (i4 == Integer.MIN_VALUE) {
                i4 = bitmap.getHeight();
            }
            Bitmap transform = transform(bitmapPool, bitmap, i3, i4);
            return bitmap.equals(transform) ? resource : BitmapResource.obtain(transform, bitmapPool);
        }
        throw new IllegalArgumentException(i.a(i3, i4, "Cannot apply transformation on width: ", " or height: ", " less than or equal to zero and not Target.SIZE_ORIGINAL"));
    }
}
