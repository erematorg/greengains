package com.android.volley.toolbox;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;

public class ImageRequest extends Request<Bitmap> {
    public static final float DEFAULT_IMAGE_BACKOFF_MULT = 2.0f;
    public static final int DEFAULT_IMAGE_MAX_RETRIES = 2;
    public static final int DEFAULT_IMAGE_TIMEOUT_MS = 1000;
    private static final Object sDecodeLock = new Object();
    private final Bitmap.Config mDecodeConfig;
    @GuardedBy("mLock")
    @Nullable
    private Response.Listener<Bitmap> mListener;
    private final Object mLock;
    private final int mMaxHeight;
    private final int mMaxWidth;
    private final ImageView.ScaleType mScaleType;

    public ImageRequest(String str, Response.Listener<Bitmap> listener, int i3, int i4, ImageView.ScaleType scaleType, Bitmap.Config config, @Nullable Response.ErrorListener errorListener) {
        super(0, str, errorListener);
        this.mLock = new Object();
        setRetryPolicy(new DefaultRetryPolicy(1000, 2, 2.0f));
        this.mListener = listener;
        this.mDecodeConfig = config;
        this.mMaxWidth = i3;
        this.mMaxHeight = i4;
        this.mScaleType = scaleType;
    }

    private Response<Bitmap> doParse(NetworkResponse networkResponse) {
        Bitmap bitmap;
        byte[] bArr = networkResponse.data;
        BitmapFactory.Options options = new BitmapFactory.Options();
        if (this.mMaxWidth == 0 && this.mMaxHeight == 0) {
            options.inPreferredConfig = this.mDecodeConfig;
            bitmap = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        } else {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            int i3 = options.outWidth;
            int i4 = options.outHeight;
            int resizedDimension = getResizedDimension(this.mMaxWidth, this.mMaxHeight, i3, i4, this.mScaleType);
            int resizedDimension2 = getResizedDimension(this.mMaxHeight, this.mMaxWidth, i4, i3, this.mScaleType);
            options.inJustDecodeBounds = false;
            options.inSampleSize = findBestSampleSize(i3, i4, resizedDimension, resizedDimension2);
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            if (decodeByteArray == null || (decodeByteArray.getWidth() <= resizedDimension && decodeByteArray.getHeight() <= resizedDimension2)) {
                bitmap = decodeByteArray;
            } else {
                bitmap = Bitmap.createScaledBitmap(decodeByteArray, resizedDimension, resizedDimension2, true);
                decodeByteArray.recycle();
            }
        }
        return bitmap == null ? Response.error(new ParseError(networkResponse)) : Response.success(bitmap, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }

    @VisibleForTesting
    public static int findBestSampleSize(int i3, int i4, int i5, int i6) {
        double min = Math.min(((double) i3) / ((double) i5), ((double) i4) / ((double) i6));
        float f2 = 1.0f;
        while (true) {
            float f3 = 2.0f * f2;
            if (((double) f3) > min) {
                return (int) f2;
            }
            f2 = f3;
        }
    }

    private static int getResizedDimension(int i3, int i4, int i5, int i6, ImageView.ScaleType scaleType) {
        if (i3 == 0 && i4 == 0) {
            return i5;
        }
        if (scaleType == ImageView.ScaleType.FIT_XY) {
            return i3 == 0 ? i5 : i3;
        }
        if (i3 == 0) {
            return (int) (((double) i5) * (((double) i4) / ((double) i6)));
        } else if (i4 == 0) {
            return i3;
        } else {
            double d2 = ((double) i6) / ((double) i5);
            if (scaleType == ImageView.ScaleType.CENTER_CROP) {
                double d3 = (double) i4;
                return ((double) i3) * d2 < d3 ? (int) (d3 / d2) : i3;
            }
            double d4 = (double) i4;
            return ((double) i3) * d2 > d4 ? (int) (d4 / d2) : i3;
        }
    }

    public void cancel() {
        super.cancel();
        synchronized (this.mLock) {
            this.mListener = null;
        }
    }

    public Request.Priority getPriority() {
        return Request.Priority.LOW;
    }

    public Response<Bitmap> parseNetworkResponse(NetworkResponse networkResponse) {
        Response<Bitmap> doParse;
        synchronized (sDecodeLock) {
            try {
                doParse = doParse(networkResponse);
            } catch (OutOfMemoryError e3) {
                VolleyLog.e("Caught OOM for %d byte image, url=%s", Integer.valueOf(networkResponse.data.length), getUrl());
                return Response.error(new ParseError((Throwable) e3));
            } catch (Throwable th) {
                throw th;
            }
        }
        return doParse;
    }

    public void deliverResponse(Bitmap bitmap) {
        Response.Listener<Bitmap> listener;
        synchronized (this.mLock) {
            listener = this.mListener;
        }
        if (listener != null) {
            listener.onResponse(bitmap);
        }
    }

    @Deprecated
    public ImageRequest(String str, Response.Listener<Bitmap> listener, int i3, int i4, Bitmap.Config config, Response.ErrorListener errorListener) {
        this(str, listener, i3, i4, ImageView.ScaleType.CENTER_INSIDE, config, errorListener);
    }
}
