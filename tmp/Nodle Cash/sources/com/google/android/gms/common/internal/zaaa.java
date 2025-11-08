package com.google.android.gms.common.internal;

import A.a;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.android.gms.base.R;
import com.google.android.gms.common.util.DeviceProperties;

public final class zaaa extends Button {
    public zaaa(Context context, @Nullable AttributeSet attributeSet) {
        super(context, (AttributeSet) null, 16842824);
    }

    private static final int zab(int i3, int i4, int i5, int i6) {
        if (i3 == 0) {
            return i4;
        }
        if (i3 == 1) {
            return i5;
        }
        if (i3 == 2) {
            return i6;
        }
        throw new IllegalStateException(a.k("Unknown color scheme: ", i3));
    }

    public final void zaa(Resources resources, int i3, int i4) {
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(14.0f);
        int i5 = (int) ((resources.getDisplayMetrics().density * 48.0f) + 0.5f);
        setMinHeight(i5);
        setMinWidth(i5);
        int i6 = R.drawable.common_google_signin_btn_icon_dark;
        int i7 = R.drawable.common_google_signin_btn_icon_light;
        int zab = zab(i4, i6, i7, i7);
        int i8 = R.drawable.common_google_signin_btn_text_dark;
        int i9 = R.drawable.common_google_signin_btn_text_light;
        int zab2 = zab(i4, i8, i9, i9);
        if (i3 == 0 || i3 == 1) {
            zab = zab2;
        } else if (i3 != 2) {
            throw new IllegalStateException(a.k("Unknown button size: ", i3));
        }
        Drawable wrap = DrawableCompat.wrap(resources.getDrawable(zab));
        DrawableCompat.setTintList(wrap, resources.getColorStateList(R.color.common_google_signin_btn_tint));
        DrawableCompat.setTintMode(wrap, PorterDuff.Mode.SRC_ATOP);
        setBackgroundDrawable(wrap);
        int i10 = R.color.common_google_signin_btn_text_dark;
        int i11 = R.color.common_google_signin_btn_text_light;
        setTextColor((ColorStateList) Preconditions.checkNotNull(resources.getColorStateList(zab(i4, i10, i11, i11))));
        if (i3 == 0) {
            setText(resources.getString(R.string.common_signin_button_text));
        } else if (i3 == 1) {
            setText(resources.getString(R.string.common_signin_button_text_long));
        } else if (i3 == 2) {
            setText((CharSequence) null);
        } else {
            throw new IllegalStateException(a.k("Unknown button size: ", i3));
        }
        setTransformationMethod((TransformationMethod) null);
        if (DeviceProperties.isWearable(getContext())) {
            setGravity(19);
        }
    }
}
