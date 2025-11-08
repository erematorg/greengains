package com.airbnb.lottie.manager;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.state.b;
import com.airbnb.lottie.ImageAssetDelegate;
import com.airbnb.lottie.LottieImageAsset;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import java.io.IOException;
import java.util.Map;

public class ImageAssetManager {
    private static final Object bitmapHashLock = new Object();
    @Nullable
    private final Context context;
    @Nullable
    private ImageAssetDelegate delegate;
    private final Map<String, LottieImageAsset> imageAssets;
    private final String imagesFolder;

    public ImageAssetManager(Drawable.Callback callback, String str, ImageAssetDelegate imageAssetDelegate, Map<String, LottieImageAsset> map) {
        if (TextUtils.isEmpty(str) || b.a(1, str) == '/') {
            this.imagesFolder = str;
        } else {
            this.imagesFolder = str.concat("/");
        }
        this.imageAssets = map;
        setDelegate(imageAssetDelegate);
        if (!(callback instanceof View)) {
            this.context = null;
        } else {
            this.context = ((View) callback).getContext().getApplicationContext();
        }
    }

    private Bitmap putBitmap(String str, @Nullable Bitmap bitmap) {
        synchronized (bitmapHashLock) {
            this.imageAssets.get(str).setBitmap(bitmap);
        }
        return bitmap;
    }

    @Nullable
    public Bitmap bitmapForId(String str) {
        LottieImageAsset lottieImageAsset = this.imageAssets.get(str);
        if (lottieImageAsset == null) {
            return null;
        }
        Bitmap bitmap = lottieImageAsset.getBitmap();
        if (bitmap != null) {
            return bitmap;
        }
        ImageAssetDelegate imageAssetDelegate = this.delegate;
        if (imageAssetDelegate != null) {
            Bitmap fetchBitmap = imageAssetDelegate.fetchBitmap(lottieImageAsset);
            if (fetchBitmap != null) {
                putBitmap(str, fetchBitmap);
            }
            return fetchBitmap;
        }
        Context context2 = this.context;
        if (context2 == null) {
            return null;
        }
        String fileName = lottieImageAsset.getFileName();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = true;
        options.inDensity = 160;
        if (!fileName.startsWith("data:") || fileName.indexOf("base64,") <= 0) {
            try {
                if (!TextUtils.isEmpty(this.imagesFolder)) {
                    AssetManager assets = context2.getAssets();
                    try {
                        Bitmap decodeStream = BitmapFactory.decodeStream(assets.open(this.imagesFolder + fileName), (Rect) null, options);
                        if (decodeStream != null) {
                            return putBitmap(str, Utils.resizeBitmapIfNeeded(decodeStream, lottieImageAsset.getWidth(), lottieImageAsset.getHeight()));
                        }
                        Logger.warning("Decoded image `" + str + "` is null.");
                        return null;
                    } catch (IllegalArgumentException e3) {
                        Logger.warning("Unable to decode image `" + str + "`.", e3);
                        return null;
                    }
                } else {
                    throw new IllegalStateException("You must set an images folder before loading an image. Set it with LottieComposition#setImagesFolder or LottieDrawable#setImagesFolder");
                }
            } catch (IOException e4) {
                Logger.warning("Unable to open asset.", e4);
                return null;
            }
        } else {
            try {
                byte[] decode = Base64.decode(fileName.substring(fileName.indexOf(44) + 1), 0);
                return putBitmap(str, BitmapFactory.decodeByteArray(decode, 0, decode.length, options));
            } catch (IllegalArgumentException e5) {
                Logger.warning("data URL did not have correct base64 format.", e5);
                return null;
            }
        }
    }

    @Nullable
    public LottieImageAsset getImageAssetById(String str) {
        return this.imageAssets.get(str);
    }

    public boolean hasSameContext(Context context2) {
        if (this.context instanceof Application) {
            context2 = context2.getApplicationContext();
        }
        return context2 == this.context;
    }

    public void setDelegate(@Nullable ImageAssetDelegate imageAssetDelegate) {
        this.delegate = imageAssetDelegate;
    }

    @Nullable
    public Bitmap updateBitmap(String str, @Nullable Bitmap bitmap) {
        if (bitmap == null) {
            LottieImageAsset lottieImageAsset = this.imageAssets.get(str);
            Bitmap bitmap2 = lottieImageAsset.getBitmap();
            lottieImageAsset.setBitmap((Bitmap) null);
            return bitmap2;
        }
        Bitmap bitmap3 = this.imageAssets.get(str).getBitmap();
        putBitmap(str, bitmap);
        return bitmap3;
    }
}
