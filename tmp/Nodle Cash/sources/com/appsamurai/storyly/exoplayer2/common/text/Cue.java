package com.appsamurai.storyly.exoplayer2.common.text;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spanned;
import android.text.SpannedString;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.appsamurai.storyly.exoplayer2.common.Bundleable;
import com.appsamurai.storyly.exoplayer2.common.a;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.google.common.base.Objects;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.dataflow.qual.Pure;

public final class Cue implements Bundleable {
    public static final int ANCHOR_TYPE_END = 2;
    public static final int ANCHOR_TYPE_MIDDLE = 1;
    public static final int ANCHOR_TYPE_START = 0;
    public static final Bundleable.Creator<Cue> CREATOR = new a(19);
    public static final float DIMEN_UNSET = -3.4028235E38f;
    public static final Cue EMPTY = new Builder().setText("").build();
    private static final int FIELD_BITMAP = 3;
    private static final int FIELD_BITMAP_HEIGHT = 12;
    private static final int FIELD_LINE = 4;
    private static final int FIELD_LINE_ANCHOR = 6;
    private static final int FIELD_LINE_TYPE = 5;
    private static final int FIELD_MULTI_ROW_ALIGNMENT = 2;
    private static final int FIELD_POSITION = 7;
    private static final int FIELD_POSITION_ANCHOR = 8;
    private static final int FIELD_SHEAR_DEGREES = 16;
    private static final int FIELD_SIZE = 11;
    private static final int FIELD_TEXT = 0;
    private static final int FIELD_TEXT_ALIGNMENT = 1;
    private static final int FIELD_TEXT_SIZE = 10;
    private static final int FIELD_TEXT_SIZE_TYPE = 9;
    private static final int FIELD_VERTICAL_TYPE = 15;
    private static final int FIELD_WINDOW_COLOR = 13;
    private static final int FIELD_WINDOW_COLOR_SET = 14;
    public static final int LINE_TYPE_FRACTION = 0;
    public static final int LINE_TYPE_NUMBER = 1;
    public static final int TEXT_SIZE_TYPE_ABSOLUTE = 2;
    public static final int TEXT_SIZE_TYPE_FRACTIONAL = 0;
    public static final int TEXT_SIZE_TYPE_FRACTIONAL_IGNORE_PADDING = 1;
    public static final int TYPE_UNSET = Integer.MIN_VALUE;
    public static final int VERTICAL_TYPE_LR = 2;
    public static final int VERTICAL_TYPE_RL = 1;
    @Nullable
    public final Bitmap bitmap;
    public final float bitmapHeight;
    public final float line;
    public final int lineAnchor;
    public final int lineType;
    @Nullable
    public final Layout.Alignment multiRowAlignment;
    public final float position;
    public final int positionAnchor;
    public final float shearDegrees;
    public final float size;
    @Nullable
    public final CharSequence text;
    @Nullable
    public final Layout.Alignment textAlignment;
    public final float textSize;
    public final int textSizeType;
    public final int verticalType;
    public final int windowColor;
    public final boolean windowColorSet;

    @Documented
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AnchorType {
    }

    public static final class Builder {
        @Nullable
        private Bitmap bitmap;
        private float bitmapHeight;
        private float line;
        private int lineAnchor;
        private int lineType;
        @Nullable
        private Layout.Alignment multiRowAlignment;
        private float position;
        private int positionAnchor;
        private float shearDegrees;
        private float size;
        @Nullable
        private CharSequence text;
        @Nullable
        private Layout.Alignment textAlignment;
        private float textSize;
        private int textSizeType;
        private int verticalType;
        @ColorInt
        private int windowColor;
        private boolean windowColorSet;

        public Cue build() {
            return new Cue(this.text, this.textAlignment, this.multiRowAlignment, this.bitmap, this.line, this.lineType, this.lineAnchor, this.position, this.positionAnchor, this.textSizeType, this.textSize, this.size, this.bitmapHeight, this.windowColorSet, this.windowColor, this.verticalType, this.shearDegrees);
        }

        public Builder clearWindowColor() {
            this.windowColorSet = false;
            return this;
        }

        @Nullable
        @Pure
        public Bitmap getBitmap() {
            return this.bitmap;
        }

        @Pure
        public float getBitmapHeight() {
            return this.bitmapHeight;
        }

        @Pure
        public float getLine() {
            return this.line;
        }

        @Pure
        public int getLineAnchor() {
            return this.lineAnchor;
        }

        @Pure
        public int getLineType() {
            return this.lineType;
        }

        @Pure
        public float getPosition() {
            return this.position;
        }

        @Pure
        public int getPositionAnchor() {
            return this.positionAnchor;
        }

        @Pure
        public float getSize() {
            return this.size;
        }

        @Nullable
        @Pure
        public CharSequence getText() {
            return this.text;
        }

        @Nullable
        @Pure
        public Layout.Alignment getTextAlignment() {
            return this.textAlignment;
        }

        @Pure
        public float getTextSize() {
            return this.textSize;
        }

        @Pure
        public int getTextSizeType() {
            return this.textSizeType;
        }

        @Pure
        public int getVerticalType() {
            return this.verticalType;
        }

        @ColorInt
        @Pure
        public int getWindowColor() {
            return this.windowColor;
        }

        public boolean isWindowColorSet() {
            return this.windowColorSet;
        }

        public Builder setBitmap(Bitmap bitmap2) {
            this.bitmap = bitmap2;
            return this;
        }

        public Builder setBitmapHeight(float f2) {
            this.bitmapHeight = f2;
            return this;
        }

        public Builder setLine(float f2, int i3) {
            this.line = f2;
            this.lineType = i3;
            return this;
        }

        public Builder setLineAnchor(int i3) {
            this.lineAnchor = i3;
            return this;
        }

        public Builder setMultiRowAlignment(@Nullable Layout.Alignment alignment) {
            this.multiRowAlignment = alignment;
            return this;
        }

        public Builder setPosition(float f2) {
            this.position = f2;
            return this;
        }

        public Builder setPositionAnchor(int i3) {
            this.positionAnchor = i3;
            return this;
        }

        public Builder setShearDegrees(float f2) {
            this.shearDegrees = f2;
            return this;
        }

        public Builder setSize(float f2) {
            this.size = f2;
            return this;
        }

        public Builder setText(CharSequence charSequence) {
            this.text = charSequence;
            return this;
        }

        public Builder setTextAlignment(@Nullable Layout.Alignment alignment) {
            this.textAlignment = alignment;
            return this;
        }

        public Builder setTextSize(float f2, int i3) {
            this.textSize = f2;
            this.textSizeType = i3;
            return this;
        }

        public Builder setVerticalType(int i3) {
            this.verticalType = i3;
            return this;
        }

        public Builder setWindowColor(@ColorInt int i3) {
            this.windowColor = i3;
            this.windowColorSet = true;
            return this;
        }

        public Builder() {
            this.text = null;
            this.bitmap = null;
            this.textAlignment = null;
            this.multiRowAlignment = null;
            this.line = -3.4028235E38f;
            this.lineType = Integer.MIN_VALUE;
            this.lineAnchor = Integer.MIN_VALUE;
            this.position = -3.4028235E38f;
            this.positionAnchor = Integer.MIN_VALUE;
            this.textSizeType = Integer.MIN_VALUE;
            this.textSize = -3.4028235E38f;
            this.size = -3.4028235E38f;
            this.bitmapHeight = -3.4028235E38f;
            this.windowColorSet = false;
            this.windowColor = ViewCompat.MEASURED_STATE_MASK;
            this.verticalType = Integer.MIN_VALUE;
        }

        private Builder(Cue cue) {
            this.text = cue.text;
            this.bitmap = cue.bitmap;
            this.textAlignment = cue.textAlignment;
            this.multiRowAlignment = cue.multiRowAlignment;
            this.line = cue.line;
            this.lineType = cue.lineType;
            this.lineAnchor = cue.lineAnchor;
            this.position = cue.position;
            this.positionAnchor = cue.positionAnchor;
            this.textSizeType = cue.textSizeType;
            this.textSize = cue.textSize;
            this.size = cue.size;
            this.bitmapHeight = cue.bitmapHeight;
            this.windowColorSet = cue.windowColorSet;
            this.windowColor = cue.windowColor;
            this.verticalType = cue.verticalType;
            this.shearDegrees = cue.shearDegrees;
        }
    }

    @Documented
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LineType {
    }

    @Documented
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TextSizeType {
    }

    @Documented
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface VerticalType {
    }

    /* access modifiers changed from: private */
    public static final Cue fromBundle(Bundle bundle) {
        Builder builder = new Builder();
        CharSequence charSequence = bundle.getCharSequence(keyForField(0));
        if (charSequence != null) {
            builder.setText(charSequence);
        }
        Layout.Alignment alignment = (Layout.Alignment) bundle.getSerializable(keyForField(1));
        if (alignment != null) {
            builder.setTextAlignment(alignment);
        }
        Layout.Alignment alignment2 = (Layout.Alignment) bundle.getSerializable(keyForField(2));
        if (alignment2 != null) {
            builder.setMultiRowAlignment(alignment2);
        }
        Bitmap bitmap2 = (Bitmap) bundle.getParcelable(keyForField(3));
        if (bitmap2 != null) {
            builder.setBitmap(bitmap2);
        }
        if (bundle.containsKey(keyForField(4)) && bundle.containsKey(keyForField(5))) {
            builder.setLine(bundle.getFloat(keyForField(4)), bundle.getInt(keyForField(5)));
        }
        if (bundle.containsKey(keyForField(6))) {
            builder.setLineAnchor(bundle.getInt(keyForField(6)));
        }
        if (bundle.containsKey(keyForField(7))) {
            builder.setPosition(bundle.getFloat(keyForField(7)));
        }
        if (bundle.containsKey(keyForField(8))) {
            builder.setPositionAnchor(bundle.getInt(keyForField(8)));
        }
        if (bundle.containsKey(keyForField(10)) && bundle.containsKey(keyForField(9))) {
            builder.setTextSize(bundle.getFloat(keyForField(10)), bundle.getInt(keyForField(9)));
        }
        if (bundle.containsKey(keyForField(11))) {
            builder.setSize(bundle.getFloat(keyForField(11)));
        }
        if (bundle.containsKey(keyForField(12))) {
            builder.setBitmapHeight(bundle.getFloat(keyForField(12)));
        }
        if (bundle.containsKey(keyForField(13))) {
            builder.setWindowColor(bundle.getInt(keyForField(13)));
        }
        if (!bundle.getBoolean(keyForField(14), false)) {
            builder.clearWindowColor();
        }
        if (bundle.containsKey(keyForField(15))) {
            builder.setVerticalType(bundle.getInt(keyForField(15)));
        }
        if (bundle.containsKey(keyForField(16))) {
            builder.setShearDegrees(bundle.getFloat(keyForField(16)));
        }
        return builder.build();
    }

    private static String keyForField(int i3) {
        return Integer.toString(i3, 36);
    }

    public Builder buildUpon() {
        return new Builder();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0029, code lost:
        r2 = r4.bitmap;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0032, code lost:
        r3 = r5.bitmap;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@androidx.annotation.Nullable java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r4 != r5) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 0
            if (r5 == 0) goto L_0x0099
            java.lang.Class r2 = r5.getClass()
            java.lang.Class<com.appsamurai.storyly.exoplayer2.common.text.Cue> r3 = com.appsamurai.storyly.exoplayer2.common.text.Cue.class
            if (r3 == r2) goto L_0x0011
            goto L_0x0099
        L_0x0011:
            com.appsamurai.storyly.exoplayer2.common.text.Cue r5 = (com.appsamurai.storyly.exoplayer2.common.text.Cue) r5
            java.lang.CharSequence r2 = r4.text
            java.lang.CharSequence r3 = r5.text
            boolean r2 = android.text.TextUtils.equals(r2, r3)
            if (r2 == 0) goto L_0x0097
            android.text.Layout$Alignment r2 = r4.textAlignment
            android.text.Layout$Alignment r3 = r5.textAlignment
            if (r2 != r3) goto L_0x0097
            android.text.Layout$Alignment r2 = r4.multiRowAlignment
            android.text.Layout$Alignment r3 = r5.multiRowAlignment
            if (r2 != r3) goto L_0x0097
            android.graphics.Bitmap r2 = r4.bitmap
            if (r2 != 0) goto L_0x0032
            android.graphics.Bitmap r2 = r5.bitmap
            if (r2 != 0) goto L_0x0097
            goto L_0x003c
        L_0x0032:
            android.graphics.Bitmap r3 = r5.bitmap
            if (r3 == 0) goto L_0x0097
            boolean r2 = r2.sameAs(r3)
            if (r2 == 0) goto L_0x0097
        L_0x003c:
            float r2 = r4.line
            float r3 = r5.line
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 != 0) goto L_0x0097
            int r2 = r4.lineType
            int r3 = r5.lineType
            if (r2 != r3) goto L_0x0097
            int r2 = r4.lineAnchor
            int r3 = r5.lineAnchor
            if (r2 != r3) goto L_0x0097
            float r2 = r4.position
            float r3 = r5.position
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 != 0) goto L_0x0097
            int r2 = r4.positionAnchor
            int r3 = r5.positionAnchor
            if (r2 != r3) goto L_0x0097
            float r2 = r4.size
            float r3 = r5.size
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 != 0) goto L_0x0097
            float r2 = r4.bitmapHeight
            float r3 = r5.bitmapHeight
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 != 0) goto L_0x0097
            boolean r2 = r4.windowColorSet
            boolean r3 = r5.windowColorSet
            if (r2 != r3) goto L_0x0097
            int r2 = r4.windowColor
            int r3 = r5.windowColor
            if (r2 != r3) goto L_0x0097
            int r2 = r4.textSizeType
            int r3 = r5.textSizeType
            if (r2 != r3) goto L_0x0097
            float r2 = r4.textSize
            float r3 = r5.textSize
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 != 0) goto L_0x0097
            int r2 = r4.verticalType
            int r3 = r5.verticalType
            if (r2 != r3) goto L_0x0097
            float r4 = r4.shearDegrees
            float r5 = r5.shearDegrees
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 != 0) goto L_0x0097
            goto L_0x0098
        L_0x0097:
            r0 = r1
        L_0x0098:
            return r0
        L_0x0099:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.common.text.Cue.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return Objects.hashCode(this.text, this.textAlignment, this.multiRowAlignment, this.bitmap, Float.valueOf(this.line), Integer.valueOf(this.lineType), Integer.valueOf(this.lineAnchor), Float.valueOf(this.position), Integer.valueOf(this.positionAnchor), Float.valueOf(this.size), Float.valueOf(this.bitmapHeight), Boolean.valueOf(this.windowColorSet), Integer.valueOf(this.windowColor), Integer.valueOf(this.textSizeType), Float.valueOf(this.textSize), Integer.valueOf(this.verticalType), Float.valueOf(this.shearDegrees));
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putCharSequence(keyForField(0), this.text);
        bundle.putSerializable(keyForField(1), this.textAlignment);
        bundle.putSerializable(keyForField(2), this.multiRowAlignment);
        bundle.putParcelable(keyForField(3), this.bitmap);
        bundle.putFloat(keyForField(4), this.line);
        bundle.putInt(keyForField(5), this.lineType);
        bundle.putInt(keyForField(6), this.lineAnchor);
        bundle.putFloat(keyForField(7), this.position);
        bundle.putInt(keyForField(8), this.positionAnchor);
        bundle.putInt(keyForField(9), this.textSizeType);
        bundle.putFloat(keyForField(10), this.textSize);
        bundle.putFloat(keyForField(11), this.size);
        bundle.putFloat(keyForField(12), this.bitmapHeight);
        bundle.putBoolean(keyForField(14), this.windowColorSet);
        bundle.putInt(keyForField(13), this.windowColor);
        bundle.putInt(keyForField(15), this.verticalType);
        bundle.putFloat(keyForField(16), this.shearDegrees);
        return bundle;
    }

    @Deprecated
    public Cue(CharSequence charSequence) {
        this(charSequence, (Layout.Alignment) null, -3.4028235E38f, Integer.MIN_VALUE, Integer.MIN_VALUE, -3.4028235E38f, Integer.MIN_VALUE, -3.4028235E38f);
    }

    @Deprecated
    public Cue(CharSequence charSequence, @Nullable Layout.Alignment alignment, float f2, int i3, int i4, float f3, int i5, float f4) {
        this(charSequence, alignment, f2, i3, i4, f3, i5, f4, false, (int) ViewCompat.MEASURED_STATE_MASK);
    }

    @Deprecated
    public Cue(CharSequence charSequence, @Nullable Layout.Alignment alignment, float f2, int i3, int i4, float f3, int i5, float f4, int i6, float f5) {
        this(charSequence, alignment, (Layout.Alignment) null, (Bitmap) null, f2, i3, i4, f3, i5, i6, f5, f4, -3.4028235E38f, false, ViewCompat.MEASURED_STATE_MASK, Integer.MIN_VALUE, 0.0f);
    }

    @Deprecated
    public Cue(CharSequence charSequence, @Nullable Layout.Alignment alignment, float f2, int i3, int i4, float f3, int i5, float f4, boolean z2, int i6) {
        this(charSequence, alignment, (Layout.Alignment) null, (Bitmap) null, f2, i3, i4, f3, i5, Integer.MIN_VALUE, -3.4028235E38f, f4, -3.4028235E38f, z2, i6, Integer.MIN_VALUE, 0.0f);
    }

    private Cue(@Nullable CharSequence charSequence, @Nullable Layout.Alignment alignment, @Nullable Layout.Alignment alignment2, @Nullable Bitmap bitmap2, float f2, int i3, int i4, float f3, int i5, int i6, float f4, float f5, float f6, boolean z2, int i7, int i8, float f7) {
        CharSequence charSequence2 = charSequence;
        Bitmap bitmap3 = bitmap2;
        if (charSequence2 == null) {
            Assertions.checkNotNull(bitmap2);
        } else {
            Assertions.checkArgument(bitmap3 == null);
        }
        if (charSequence2 instanceof Spanned) {
            this.text = SpannedString.valueOf(charSequence);
        } else if (charSequence2 != null) {
            this.text = charSequence.toString();
        } else {
            this.text = null;
        }
        this.textAlignment = alignment;
        this.multiRowAlignment = alignment2;
        this.bitmap = bitmap3;
        this.line = f2;
        this.lineType = i3;
        this.lineAnchor = i4;
        this.position = f3;
        this.positionAnchor = i5;
        this.size = f5;
        this.bitmapHeight = f6;
        this.windowColorSet = z2;
        this.windowColor = i7;
        this.textSizeType = i6;
        this.textSize = f4;
        this.verticalType = i8;
        this.shearDegrees = f7;
    }
}
