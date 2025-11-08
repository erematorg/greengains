package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPoolAdapter;
import com.bumptech.glide.load.resource.DefaultOnHeaderDecodedListener;
import java.io.IOException;

@RequiresApi(api = 28)
public final class BitmapImageDecoderResourceDecoder implements ResourceDecoder<ImageDecoder.Source, Bitmap> {
    private static final String TAG = "BitmapImageDecoder";
    private final BitmapPool bitmapPool = new BitmapPoolAdapter();

    public boolean handles(@NonNull ImageDecoder.Source source, @NonNull Options options) throws IOException {
        return true;
    }

    public Resource<Bitmap> decode(@NonNull ImageDecoder.Source source, int i3, int i4, @NonNull Options options) throws IOException {
        Bitmap decodeBitmap = ImageDecoder.decodeBitmap(source, new DefaultOnHeaderDecodedListener(i3, i4, options));
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "Decoded [" + decodeBitmap.getWidth() + "x" + decodeBitmap.getHeight() + "] for [" + i3 + "x" + i4 + "]");
        }
        return new BitmapResource(decodeBitmap, this.bitmapPool);
    }
}
