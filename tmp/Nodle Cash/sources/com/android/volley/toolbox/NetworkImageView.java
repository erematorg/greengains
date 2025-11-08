package com.android.volley.toolbox;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

public class NetworkImageView extends ImageView {
    /* access modifiers changed from: private */
    @Nullable
    public Bitmap mDefaultImageBitmap;
    /* access modifiers changed from: private */
    @Nullable
    public Drawable mDefaultImageDrawable;
    /* access modifiers changed from: private */
    public int mDefaultImageId;
    /* access modifiers changed from: private */
    @Nullable
    public Bitmap mErrorImageBitmap;
    /* access modifiers changed from: private */
    @Nullable
    public Drawable mErrorImageDrawable;
    /* access modifiers changed from: private */
    public int mErrorImageId;
    private ImageLoader.ImageContainer mImageContainer;
    private ImageLoader mImageLoader;
    private String mUrl;

    public NetworkImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    private void setDefaultImageOrNull() {
        int i3 = this.mDefaultImageId;
        if (i3 != 0) {
            setImageResource(i3);
            return;
        }
        Drawable drawable = this.mDefaultImageDrawable;
        if (drawable != null) {
            setImageDrawable(drawable);
            return;
        }
        Bitmap bitmap = this.mDefaultImageBitmap;
        if (bitmap != null) {
            setImageBitmap(bitmap);
        } else {
            setImageBitmap((Bitmap) null);
        }
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    public void loadImageIfNecessary(final boolean z2) {
        boolean z3;
        boolean z4;
        int width = getWidth();
        int height = getHeight();
        ImageView.ScaleType scaleType = getScaleType();
        boolean z5 = true;
        if (getLayoutParams() != null) {
            z4 = getLayoutParams().width == -2;
            z3 = getLayoutParams().height == -2;
        } else {
            z4 = false;
            z3 = false;
        }
        if (!z4 || !z3) {
            z5 = false;
        }
        if (width != 0 || height != 0 || z5) {
            if (TextUtils.isEmpty(this.mUrl)) {
                ImageLoader.ImageContainer imageContainer = this.mImageContainer;
                if (imageContainer != null) {
                    imageContainer.cancelRequest();
                    this.mImageContainer = null;
                }
                setDefaultImageOrNull();
                return;
            }
            ImageLoader.ImageContainer imageContainer2 = this.mImageContainer;
            if (!(imageContainer2 == null || imageContainer2.getRequestUrl() == null)) {
                if (!this.mImageContainer.getRequestUrl().equals(this.mUrl)) {
                    this.mImageContainer.cancelRequest();
                    setDefaultImageOrNull();
                } else {
                    return;
                }
            }
            if (z4) {
                width = 0;
            }
            this.mImageContainer = this.mImageLoader.get(this.mUrl, new ImageLoader.ImageListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    if (NetworkImageView.this.mErrorImageId != 0) {
                        NetworkImageView networkImageView = NetworkImageView.this;
                        networkImageView.setImageResource(networkImageView.mErrorImageId);
                    } else if (NetworkImageView.this.mErrorImageDrawable != null) {
                        NetworkImageView networkImageView2 = NetworkImageView.this;
                        networkImageView2.setImageDrawable(networkImageView2.mErrorImageDrawable);
                    } else if (NetworkImageView.this.mErrorImageBitmap != null) {
                        NetworkImageView networkImageView3 = NetworkImageView.this;
                        networkImageView3.setImageBitmap(networkImageView3.mErrorImageBitmap);
                    }
                }

                public void onResponse(final ImageLoader.ImageContainer imageContainer, boolean z2) {
                    if (z2 && z2) {
                        NetworkImageView.this.post(new Runnable() {
                            public void run() {
                                AnonymousClass1.this.onResponse(imageContainer, false);
                            }
                        });
                    } else if (imageContainer.getBitmap() != null) {
                        NetworkImageView.this.setImageBitmap(imageContainer.getBitmap());
                    } else if (NetworkImageView.this.mDefaultImageId != 0) {
                        NetworkImageView networkImageView = NetworkImageView.this;
                        networkImageView.setImageResource(networkImageView.mDefaultImageId);
                    } else if (NetworkImageView.this.mDefaultImageDrawable != null) {
                        NetworkImageView networkImageView2 = NetworkImageView.this;
                        networkImageView2.setImageDrawable(networkImageView2.mDefaultImageDrawable);
                    } else if (NetworkImageView.this.mDefaultImageBitmap != null) {
                        NetworkImageView networkImageView3 = NetworkImageView.this;
                        networkImageView3.setImageBitmap(networkImageView3.mDefaultImageBitmap);
                    }
                }
            }, width, z3 ? 0 : height, scaleType);
        }
    }

    public void onDetachedFromWindow() {
        ImageLoader.ImageContainer imageContainer = this.mImageContainer;
        if (imageContainer != null) {
            imageContainer.cancelRequest();
            setImageBitmap((Bitmap) null);
            this.mImageContainer = null;
        }
        super.onDetachedFromWindow();
    }

    public void onLayout(boolean z2, int i3, int i4, int i5, int i6) {
        super.onLayout(z2, i3, i4, i5, i6);
        loadImageIfNecessary(true);
    }

    public void setDefaultImageBitmap(Bitmap bitmap) {
        this.mDefaultImageId = 0;
        this.mDefaultImageDrawable = null;
        this.mDefaultImageBitmap = bitmap;
    }

    public void setDefaultImageDrawable(@Nullable Drawable drawable) {
        this.mDefaultImageId = 0;
        this.mDefaultImageBitmap = null;
        this.mDefaultImageDrawable = drawable;
    }

    public void setDefaultImageResId(int i3) {
        this.mDefaultImageBitmap = null;
        this.mDefaultImageDrawable = null;
        this.mDefaultImageId = i3;
    }

    public void setErrorImageBitmap(Bitmap bitmap) {
        this.mErrorImageId = 0;
        this.mErrorImageDrawable = null;
        this.mErrorImageBitmap = bitmap;
    }

    public void setErrorImageDrawable(@Nullable Drawable drawable) {
        this.mErrorImageId = 0;
        this.mErrorImageBitmap = null;
        this.mErrorImageDrawable = drawable;
    }

    public void setErrorImageResId(int i3) {
        this.mErrorImageBitmap = null;
        this.mErrorImageDrawable = null;
        this.mErrorImageId = i3;
    }

    @MainThread
    public void setImageUrl(String str, ImageLoader imageLoader) {
        Threads.throwIfNotOnMainThread();
        this.mUrl = str;
        this.mImageLoader = imageLoader;
        loadImageIfNecessary(false);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
    }
}
