package com.google.android.material.drawable;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ColorStateListDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import androidx.annotation.ColorInt;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.DrawableCompat;
import java.util.Arrays;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class DrawableUtils {
    public static final int INTRINSIC_SIZE = -1;
    private static final int UNSPECIFIED_HEIGHT = -1;
    private static final int UNSPECIFIED_WIDTH = -1;

    @RequiresApi(21)
    public static class OutlineCompatL {
        private OutlineCompatL() {
        }

        @DoNotInline
        public static void setConvexPath(@NonNull Outline outline, @NonNull Path path) {
            outline.setConvexPath(path);
        }
    }

    @RequiresApi(30)
    public static class OutlineCompatR {
        private OutlineCompatR() {
        }

        @DoNotInline
        public static void setPath(@NonNull Outline outline, @NonNull Path path) {
            outline.setPath(path);
        }
    }

    private DrawableUtils() {
    }

    @Nullable
    public static Drawable compositeTwoLayeredDrawable(@Nullable Drawable drawable, @Nullable Drawable drawable2) {
        return compositeTwoLayeredDrawable(drawable, drawable2, -1, -1);
    }

    @Nullable
    public static Drawable createTintableDrawableIfNeeded(@Nullable Drawable drawable, @Nullable ColorStateList colorStateList, @Nullable PorterDuff.Mode mode) {
        return createTintableMutatedDrawableIfNeeded(drawable, colorStateList, mode, false);
    }

    @Nullable
    public static Drawable createTintableMutatedDrawableIfNeeded(@Nullable Drawable drawable, @Nullable ColorStateList colorStateList, @Nullable PorterDuff.Mode mode) {
        return createTintableMutatedDrawableIfNeeded(drawable, colorStateList, mode, false);
    }

    @NonNull
    public static int[] getCheckedState(@NonNull int[] iArr) {
        for (int i3 = 0; i3 < iArr.length; i3++) {
            int i4 = iArr[i3];
            if (i4 == 16842912) {
                return iArr;
            }
            if (i4 == 0) {
                int[] iArr2 = (int[]) iArr.clone();
                iArr2[i3] = 16842912;
                return iArr2;
            }
        }
        int[] copyOf = Arrays.copyOf(iArr, iArr.length + 1);
        copyOf[iArr.length] = 16842912;
        return copyOf;
    }

    @Nullable
    public static ColorStateList getColorStateListOrNull(@Nullable Drawable drawable) {
        if (drawable instanceof ColorDrawable) {
            return ColorStateList.valueOf(((ColorDrawable) drawable).getColor());
        }
        if (drawable instanceof ColorStateListDrawable) {
            return ((ColorStateListDrawable) drawable).getColorStateList();
        }
        return null;
    }

    private static int getTopLayerIntrinsicHeight(@NonNull Drawable drawable, @NonNull Drawable drawable2) {
        int intrinsicHeight = drawable2.getIntrinsicHeight();
        return intrinsicHeight != -1 ? intrinsicHeight : drawable.getIntrinsicHeight();
    }

    private static int getTopLayerIntrinsicWidth(@NonNull Drawable drawable, @NonNull Drawable drawable2) {
        int intrinsicWidth = drawable2.getIntrinsicWidth();
        return intrinsicWidth != -1 ? intrinsicWidth : drawable.getIntrinsicWidth();
    }

    @NonNull
    public static int[] getUncheckedState(@NonNull int[] iArr) {
        int[] iArr2 = new int[iArr.length];
        int i3 = 0;
        for (int i4 : iArr) {
            if (i4 != 16842912) {
                iArr2[i3] = i4;
                i3++;
            }
        }
        return iArr2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0041 A[Catch:{ IOException | XmlPullParserException -> 0x0023 }] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0014 A[Catch:{ IOException | XmlPullParserException -> 0x0023 }] */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.util.AttributeSet parseDrawableXml(@androidx.annotation.NonNull android.content.Context r3, @androidx.annotation.XmlRes int r4, @androidx.annotation.NonNull java.lang.CharSequence r5) {
        /*
            android.content.res.Resources r3 = r3.getResources()     // Catch:{ IOException | XmlPullParserException -> 0x0023 }
            android.content.res.XmlResourceParser r3 = r3.getXml(r4)     // Catch:{ IOException | XmlPullParserException -> 0x0023 }
        L_0x0008:
            int r0 = r3.next()     // Catch:{ IOException | XmlPullParserException -> 0x0023 }
            r1 = 2
            if (r0 == r1) goto L_0x0012
            r2 = 1
            if (r0 != r2) goto L_0x0008
        L_0x0012:
            if (r0 != r1) goto L_0x0041
            java.lang.String r0 = r3.getName()     // Catch:{ IOException | XmlPullParserException -> 0x0023 }
            boolean r0 = android.text.TextUtils.equals(r0, r5)     // Catch:{ IOException | XmlPullParserException -> 0x0023 }
            if (r0 == 0) goto L_0x0025
            android.util.AttributeSet r3 = android.util.Xml.asAttributeSet(r3)     // Catch:{ IOException | XmlPullParserException -> 0x0023 }
            return r3
        L_0x0023:
            r3 = move-exception
            goto L_0x0049
        L_0x0025:
            org.xmlpull.v1.XmlPullParserException r3 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ IOException | XmlPullParserException -> 0x0023 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException | XmlPullParserException -> 0x0023 }
            r0.<init>()     // Catch:{ IOException | XmlPullParserException -> 0x0023 }
            java.lang.String r1 = "Must have a <"
            r0.append(r1)     // Catch:{ IOException | XmlPullParserException -> 0x0023 }
            r0.append(r5)     // Catch:{ IOException | XmlPullParserException -> 0x0023 }
            java.lang.String r5 = "> start tag"
            r0.append(r5)     // Catch:{ IOException | XmlPullParserException -> 0x0023 }
            java.lang.String r5 = r0.toString()     // Catch:{ IOException | XmlPullParserException -> 0x0023 }
            r3.<init>(r5)     // Catch:{ IOException | XmlPullParserException -> 0x0023 }
            throw r3     // Catch:{ IOException | XmlPullParserException -> 0x0023 }
        L_0x0041:
            org.xmlpull.v1.XmlPullParserException r3 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ IOException | XmlPullParserException -> 0x0023 }
            java.lang.String r5 = "No start tag found"
            r3.<init>(r5)     // Catch:{ IOException | XmlPullParserException -> 0x0023 }
            throw r3     // Catch:{ IOException | XmlPullParserException -> 0x0023 }
        L_0x0049:
            android.content.res.Resources$NotFoundException r5 = new android.content.res.Resources$NotFoundException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Can't load badge resource ID #0x"
            r0.<init>(r1)
            java.lang.String r4 = android.support.v4.media.session.a.j(r4, r0)
            r5.<init>(r4)
            r5.initCause(r3)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.drawable.DrawableUtils.parseDrawableXml(android.content.Context, int, java.lang.CharSequence):android.util.AttributeSet");
    }

    public static void setOutlineToPath(@NonNull Outline outline, @NonNull Path path) {
        if (Build.VERSION.SDK_INT >= 30) {
            OutlineCompatR.setPath(outline, path);
            return;
        }
        try {
            OutlineCompatL.setConvexPath(outline, path);
        } catch (IllegalArgumentException unused) {
        }
    }

    @TargetApi(21)
    public static void setRippleDrawableRadius(@Nullable RippleDrawable rippleDrawable, int i3) {
        rippleDrawable.setRadius(i3);
    }

    public static void setTint(@NonNull Drawable drawable, @ColorInt int i3) {
        if (i3 != 0) {
            DrawableCompat.setTint(drawable, i3);
        } else {
            DrawableCompat.setTintList(drawable, (ColorStateList) null);
        }
    }

    @Nullable
    public static PorterDuffColorFilter updateTintFilter(@NonNull Drawable drawable, @Nullable ColorStateList colorStateList, @Nullable PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(drawable.getState(), 0), mode);
    }

    @Nullable
    public static Drawable compositeTwoLayeredDrawable(@Nullable Drawable drawable, @Nullable Drawable drawable2, @Px int i3, @Px int i4) {
        if (drawable == null) {
            return drawable2;
        }
        if (drawable2 == null) {
            return drawable;
        }
        if (i3 == -1) {
            i3 = getTopLayerIntrinsicWidth(drawable, drawable2);
        }
        if (i4 == -1) {
            i4 = getTopLayerIntrinsicHeight(drawable, drawable2);
        }
        if (i3 > drawable.getIntrinsicWidth() || i4 > drawable.getIntrinsicHeight()) {
            float f2 = ((float) i3) / ((float) i4);
            if (f2 >= ((float) drawable.getIntrinsicWidth()) / ((float) drawable.getIntrinsicHeight())) {
                int intrinsicWidth = drawable.getIntrinsicWidth();
                int i5 = intrinsicWidth;
                i4 = (int) (((float) intrinsicWidth) / f2);
                i3 = i5;
            } else {
                i4 = drawable.getIntrinsicHeight();
                i3 = (int) (f2 * ((float) i4));
            }
        }
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{drawable, drawable2});
        layerDrawable.setLayerSize(1, i3, i4);
        layerDrawable.setLayerGravity(1, 17);
        return layerDrawable;
    }

    @Nullable
    private static Drawable createTintableMutatedDrawableIfNeeded(@Nullable Drawable drawable, @Nullable ColorStateList colorStateList, @Nullable PorterDuff.Mode mode, boolean z2) {
        if (drawable == null) {
            return null;
        }
        if (colorStateList != null) {
            drawable = DrawableCompat.wrap(drawable).mutate();
            if (mode != null) {
                DrawableCompat.setTintMode(drawable, mode);
            }
        } else if (z2) {
            drawable.mutate();
        }
        return drawable;
    }
}
