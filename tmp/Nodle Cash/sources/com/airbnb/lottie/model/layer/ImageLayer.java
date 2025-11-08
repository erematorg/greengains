package com.airbnb.lottie.model.layer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieImageAsset;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;

public class ImageLayer extends BaseLayer {
    @Nullable
    private BaseKeyframeAnimation<ColorFilter, ColorFilter> colorFilterAnimation;
    private final Rect dst = new Rect();
    @Nullable
    private BaseKeyframeAnimation<Bitmap, Bitmap> imageAnimation;
    @Nullable
    private final LottieImageAsset lottieImageAsset;
    private final Paint paint = new LPaint(3);
    private final Rect src = new Rect();

    public ImageLayer(LottieDrawable lottieDrawable, Layer layer) {
        super(lottieDrawable, layer);
        this.lottieImageAsset = lottieDrawable.getLottieImageAssetForId(layer.getRefId());
    }

    @Nullable
    private Bitmap getBitmap() {
        Bitmap value;
        BaseKeyframeAnimation<Bitmap, Bitmap> baseKeyframeAnimation = this.imageAnimation;
        if (baseKeyframeAnimation != null && (value = baseKeyframeAnimation.getValue()) != null) {
            return value;
        }
        Bitmap bitmapForId = this.lottieDrawable.getBitmapForId(this.layerModel.getRefId());
        if (bitmapForId != null) {
            return bitmapForId;
        }
        LottieImageAsset lottieImageAsset2 = this.lottieImageAsset;
        if (lottieImageAsset2 != null) {
            return lottieImageAsset2.getBitmap();
        }
        return null;
    }

    public <T> void addValueCallback(T t2, @Nullable LottieValueCallback<T> lottieValueCallback) {
        super.addValueCallback(t2, lottieValueCallback);
        if (t2 == LottieProperty.COLOR_FILTER) {
            if (lottieValueCallback == null) {
                this.colorFilterAnimation = null;
            } else {
                this.colorFilterAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            }
        } else if (t2 != LottieProperty.IMAGE) {
        } else {
            if (lottieValueCallback == null) {
                this.imageAnimation = null;
            } else {
                this.imageAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            }
        }
    }

    public void drawLayer(@NonNull Canvas canvas, Matrix matrix, int i3) {
        Bitmap bitmap = getBitmap();
        if (bitmap != null && !bitmap.isRecycled() && this.lottieImageAsset != null) {
            float dpScale = Utils.dpScale();
            this.paint.setAlpha(i3);
            BaseKeyframeAnimation<ColorFilter, ColorFilter> baseKeyframeAnimation = this.colorFilterAnimation;
            if (baseKeyframeAnimation != null) {
                this.paint.setColorFilter(baseKeyframeAnimation.getValue());
            }
            canvas.save();
            canvas.concat(matrix);
            this.src.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
            if (this.lottieDrawable.getMaintainOriginalImageBounds()) {
                this.dst.set(0, 0, (int) (((float) this.lottieImageAsset.getWidth()) * dpScale), (int) (((float) this.lottieImageAsset.getHeight()) * dpScale));
            } else {
                this.dst.set(0, 0, (int) (((float) bitmap.getWidth()) * dpScale), (int) (((float) bitmap.getHeight()) * dpScale));
            }
            canvas.drawBitmap(bitmap, this.src, this.dst, this.paint);
            canvas.restore();
        }
    }

    public void getBounds(RectF rectF, Matrix matrix, boolean z2) {
        super.getBounds(rectF, matrix, z2);
        if (this.lottieImageAsset != null) {
            float dpScale = Utils.dpScale();
            rectF.set(0.0f, 0.0f, ((float) this.lottieImageAsset.getWidth()) * dpScale, ((float) this.lottieImageAsset.getHeight()) * dpScale);
            this.boundsMatrix.mapRect(rectF);
        }
    }
}
