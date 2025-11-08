package com.facebook.shimmer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.Px;
import androidx.compose.animation.core.a;
import androidx.core.view.ViewCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Shimmer {
    private static final int COMPONENT_COUNT = 4;
    boolean alphaShimmer = true;
    long animationDuration = 1000;
    boolean autoStart = true;
    @ColorInt
    int baseColor = 1291845631;
    final RectF bounds = new RectF();
    boolean clipToChildren = true;
    final int[] colors = new int[4];
    int direction = 0;
    float dropoff = 0.5f;
    int fixedHeight = 0;
    int fixedWidth = 0;
    float heightRatio = 1.0f;
    @ColorInt
    int highlightColor = -1;
    float intensity = 0.0f;
    final float[] positions = new float[4];
    int repeatCount = -1;
    long repeatDelay;
    int repeatMode = 1;
    int shape = 0;
    float tilt = 20.0f;
    float widthRatio = 1.0f;

    public static class AlphaHighlightBuilder extends Builder<AlphaHighlightBuilder> {
        public AlphaHighlightBuilder() {
            this.mShimmer.alphaShimmer = true;
        }

        public AlphaHighlightBuilder getThis() {
            return this;
        }
    }

    public static class ColorHighlightBuilder extends Builder<ColorHighlightBuilder> {
        public ColorHighlightBuilder() {
            this.mShimmer.alphaShimmer = false;
        }

        public ColorHighlightBuilder getThis() {
            return this;
        }

        public ColorHighlightBuilder setBaseColor(@ColorInt int i3) {
            Shimmer shimmer = this.mShimmer;
            shimmer.baseColor = (i3 & ViewCompat.MEASURED_SIZE_MASK) | (shimmer.baseColor & ViewCompat.MEASURED_STATE_MASK);
            return getThis();
        }

        public ColorHighlightBuilder setHighlightColor(@ColorInt int i3) {
            this.mShimmer.highlightColor = i3;
            return getThis();
        }

        public ColorHighlightBuilder consumeAttributes(TypedArray typedArray) {
            super.consumeAttributes(typedArray);
            int i3 = R.styleable.ShimmerFrameLayout_shimmer_base_color;
            if (typedArray.hasValue(i3)) {
                setBaseColor(typedArray.getColor(i3, this.mShimmer.baseColor));
            }
            int i4 = R.styleable.ShimmerFrameLayout_shimmer_highlight_color;
            if (typedArray.hasValue(i4)) {
                setHighlightColor(typedArray.getColor(i4, this.mShimmer.highlightColor));
            }
            return getThis();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Direction {
        public static final int BOTTOM_TO_TOP = 3;
        public static final int LEFT_TO_RIGHT = 0;
        public static final int RIGHT_TO_LEFT = 2;
        public static final int TOP_TO_BOTTOM = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Shape {
        public static final int LINEAR = 0;
        public static final int RADIAL = 1;
    }

    public int height(int i3) {
        int i4 = this.fixedHeight;
        return i4 > 0 ? i4 : Math.round(this.heightRatio * ((float) i3));
    }

    public void updateBounds(int i3, int i4) {
        double max = (double) Math.max(i3, i4);
        int round = Math.round(((float) ((max / Math.sin(1.5707963267948966d - Math.toRadians((double) (this.tilt % 90.0f)))) - max)) / 2.0f) * 3;
        float f2 = (float) (-round);
        this.bounds.set(f2, f2, (float) (width(i3) + round), (float) (height(i4) + round));
    }

    public void updateColors() {
        if (this.shape != 1) {
            int[] iArr = this.colors;
            int i3 = this.baseColor;
            iArr[0] = i3;
            int i4 = this.highlightColor;
            iArr[1] = i4;
            iArr[2] = i4;
            iArr[3] = i3;
            return;
        }
        int[] iArr2 = this.colors;
        int i5 = this.highlightColor;
        iArr2[0] = i5;
        iArr2[1] = i5;
        int i6 = this.baseColor;
        iArr2[2] = i6;
        iArr2[3] = i6;
    }

    public void updatePositions() {
        if (this.shape != 1) {
            this.positions[0] = Math.max(((1.0f - this.intensity) - this.dropoff) / 2.0f, 0.0f);
            this.positions[1] = Math.max(((1.0f - this.intensity) - 0.001f) / 2.0f, 0.0f);
            this.positions[2] = Math.min(((this.intensity + 1.0f) + 0.001f) / 2.0f, 1.0f);
            this.positions[3] = Math.min(((this.intensity + 1.0f) + this.dropoff) / 2.0f, 1.0f);
            return;
        }
        float[] fArr = this.positions;
        fArr[0] = 0.0f;
        fArr[1] = Math.min(this.intensity, 1.0f);
        this.positions[2] = Math.min(this.intensity + this.dropoff, 1.0f);
        this.positions[3] = 1.0f;
    }

    public int width(int i3) {
        int i4 = this.fixedWidth;
        return i4 > 0 ? i4 : Math.round(this.widthRatio * ((float) i3));
    }

    public static abstract class Builder<T extends Builder<T>> {
        final Shimmer mShimmer = new Shimmer();

        private static float clamp(float f2, float f3, float f4) {
            return Math.min(f3, Math.max(f2, f4));
        }

        public Shimmer build() {
            this.mShimmer.updateColors();
            this.mShimmer.updatePositions();
            return this.mShimmer;
        }

        public T consumeAttributes(Context context, AttributeSet attributeSet) {
            return consumeAttributes(context.obtainStyledAttributes(attributeSet, R.styleable.ShimmerFrameLayout, 0, 0));
        }

        public T copyFrom(Shimmer shimmer) {
            setDirection(shimmer.direction);
            setShape(shimmer.shape);
            setFixedWidth(shimmer.fixedWidth);
            setFixedHeight(shimmer.fixedHeight);
            setWidthRatio(shimmer.widthRatio);
            setHeightRatio(shimmer.heightRatio);
            setIntensity(shimmer.intensity);
            setDropoff(shimmer.dropoff);
            setTilt(shimmer.tilt);
            setClipToChildren(shimmer.clipToChildren);
            setAutoStart(shimmer.autoStart);
            setRepeatCount(shimmer.repeatCount);
            setRepeatMode(shimmer.repeatMode);
            setRepeatDelay(shimmer.repeatDelay);
            setDuration(shimmer.animationDuration);
            Shimmer shimmer2 = this.mShimmer;
            shimmer2.baseColor = shimmer.baseColor;
            shimmer2.highlightColor = shimmer.highlightColor;
            return getThis();
        }

        public abstract T getThis();

        public T setAutoStart(boolean z2) {
            this.mShimmer.autoStart = z2;
            return getThis();
        }

        public T setBaseAlpha(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
            Shimmer shimmer = this.mShimmer;
            shimmer.baseColor = (((int) (clamp(0.0f, 1.0f, f2) * 255.0f)) << 24) | (shimmer.baseColor & ViewCompat.MEASURED_SIZE_MASK);
            return getThis();
        }

        public T setClipToChildren(boolean z2) {
            this.mShimmer.clipToChildren = z2;
            return getThis();
        }

        public T setDirection(int i3) {
            this.mShimmer.direction = i3;
            return getThis();
        }

        public T setDropoff(float f2) {
            if (f2 >= 0.0f) {
                this.mShimmer.dropoff = f2;
                return getThis();
            }
            throw new IllegalArgumentException("Given invalid dropoff value: " + f2);
        }

        public T setDuration(long j2) {
            if (j2 >= 0) {
                this.mShimmer.animationDuration = j2;
                return getThis();
            }
            throw new IllegalArgumentException(a.s("Given a negative duration: ", j2));
        }

        public T setFixedHeight(@Px int i3) {
            if (i3 >= 0) {
                this.mShimmer.fixedHeight = i3;
                return getThis();
            }
            throw new IllegalArgumentException(A.a.k("Given invalid height: ", i3));
        }

        public T setFixedWidth(@Px int i3) {
            if (i3 >= 0) {
                this.mShimmer.fixedWidth = i3;
                return getThis();
            }
            throw new IllegalArgumentException(A.a.k("Given invalid width: ", i3));
        }

        public T setHeightRatio(float f2) {
            if (f2 >= 0.0f) {
                this.mShimmer.heightRatio = f2;
                return getThis();
            }
            throw new IllegalArgumentException("Given invalid height ratio: " + f2);
        }

        public T setHighlightAlpha(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
            Shimmer shimmer = this.mShimmer;
            shimmer.highlightColor = (((int) (clamp(0.0f, 1.0f, f2) * 255.0f)) << 24) | (shimmer.highlightColor & ViewCompat.MEASURED_SIZE_MASK);
            return getThis();
        }

        public T setIntensity(float f2) {
            if (f2 >= 0.0f) {
                this.mShimmer.intensity = f2;
                return getThis();
            }
            throw new IllegalArgumentException("Given invalid intensity value: " + f2);
        }

        public T setRepeatCount(int i3) {
            this.mShimmer.repeatCount = i3;
            return getThis();
        }

        public T setRepeatDelay(long j2) {
            if (j2 >= 0) {
                this.mShimmer.repeatDelay = j2;
                return getThis();
            }
            throw new IllegalArgumentException(a.s("Given a negative repeat delay: ", j2));
        }

        public T setRepeatMode(int i3) {
            this.mShimmer.repeatMode = i3;
            return getThis();
        }

        public T setShape(int i3) {
            this.mShimmer.shape = i3;
            return getThis();
        }

        public T setTilt(float f2) {
            this.mShimmer.tilt = f2;
            return getThis();
        }

        public T setWidthRatio(float f2) {
            if (f2 >= 0.0f) {
                this.mShimmer.widthRatio = f2;
                return getThis();
            }
            throw new IllegalArgumentException("Given invalid width ratio: " + f2);
        }

        public T consumeAttributes(TypedArray typedArray) {
            int i3 = R.styleable.ShimmerFrameLayout_shimmer_clip_to_children;
            if (typedArray.hasValue(i3)) {
                setClipToChildren(typedArray.getBoolean(i3, this.mShimmer.clipToChildren));
            }
            int i4 = R.styleable.ShimmerFrameLayout_shimmer_auto_start;
            if (typedArray.hasValue(i4)) {
                setAutoStart(typedArray.getBoolean(i4, this.mShimmer.autoStart));
            }
            int i5 = R.styleable.ShimmerFrameLayout_shimmer_base_alpha;
            if (typedArray.hasValue(i5)) {
                setBaseAlpha(typedArray.getFloat(i5, 0.3f));
            }
            int i6 = R.styleable.ShimmerFrameLayout_shimmer_highlight_alpha;
            if (typedArray.hasValue(i6)) {
                setHighlightAlpha(typedArray.getFloat(i6, 1.0f));
            }
            int i7 = R.styleable.ShimmerFrameLayout_shimmer_duration;
            if (typedArray.hasValue(i7)) {
                setDuration((long) typedArray.getInt(i7, (int) this.mShimmer.animationDuration));
            }
            int i8 = R.styleable.ShimmerFrameLayout_shimmer_repeat_count;
            if (typedArray.hasValue(i8)) {
                setRepeatCount(typedArray.getInt(i8, this.mShimmer.repeatCount));
            }
            int i9 = R.styleable.ShimmerFrameLayout_shimmer_repeat_delay;
            if (typedArray.hasValue(i9)) {
                setRepeatDelay((long) typedArray.getInt(i9, (int) this.mShimmer.repeatDelay));
            }
            int i10 = R.styleable.ShimmerFrameLayout_shimmer_repeat_mode;
            if (typedArray.hasValue(i10)) {
                setRepeatMode(typedArray.getInt(i10, this.mShimmer.repeatMode));
            }
            int i11 = R.styleable.ShimmerFrameLayout_shimmer_direction;
            if (typedArray.hasValue(i11)) {
                int i12 = typedArray.getInt(i11, this.mShimmer.direction);
                if (i12 == 1) {
                    setDirection(1);
                } else if (i12 == 2) {
                    setDirection(2);
                } else if (i12 != 3) {
                    setDirection(0);
                } else {
                    setDirection(3);
                }
            }
            int i13 = R.styleable.ShimmerFrameLayout_shimmer_shape;
            if (typedArray.hasValue(i13)) {
                if (typedArray.getInt(i13, this.mShimmer.shape) != 1) {
                    setShape(0);
                } else {
                    setShape(1);
                }
            }
            int i14 = R.styleable.ShimmerFrameLayout_shimmer_dropoff;
            if (typedArray.hasValue(i14)) {
                setDropoff(typedArray.getFloat(i14, this.mShimmer.dropoff));
            }
            int i15 = R.styleable.ShimmerFrameLayout_shimmer_fixed_width;
            if (typedArray.hasValue(i15)) {
                setFixedWidth(typedArray.getDimensionPixelSize(i15, this.mShimmer.fixedWidth));
            }
            int i16 = R.styleable.ShimmerFrameLayout_shimmer_fixed_height;
            if (typedArray.hasValue(i16)) {
                setFixedHeight(typedArray.getDimensionPixelSize(i16, this.mShimmer.fixedHeight));
            }
            int i17 = R.styleable.ShimmerFrameLayout_shimmer_intensity;
            if (typedArray.hasValue(i17)) {
                setIntensity(typedArray.getFloat(i17, this.mShimmer.intensity));
            }
            int i18 = R.styleable.ShimmerFrameLayout_shimmer_width_ratio;
            if (typedArray.hasValue(i18)) {
                setWidthRatio(typedArray.getFloat(i18, this.mShimmer.widthRatio));
            }
            int i19 = R.styleable.ShimmerFrameLayout_shimmer_height_ratio;
            if (typedArray.hasValue(i19)) {
                setHeightRatio(typedArray.getFloat(i19, this.mShimmer.heightRatio));
            }
            int i20 = R.styleable.ShimmerFrameLayout_shimmer_tilt;
            if (typedArray.hasValue(i20)) {
                setTilt(typedArray.getFloat(i20, this.mShimmer.tilt));
            }
            return getThis();
        }
    }
}
