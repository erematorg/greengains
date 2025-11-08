package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class DayViewDecorator implements Parcelable {
    @Nullable
    public ColorStateList getBackgroundColor(@NonNull Context context, int i3, int i4, int i5, boolean z2, boolean z3) {
        return null;
    }

    @Nullable
    public Drawable getCompoundDrawableBottom(@NonNull Context context, int i3, int i4, int i5, boolean z2, boolean z3) {
        return null;
    }

    @Nullable
    public Drawable getCompoundDrawableLeft(@NonNull Context context, int i3, int i4, int i5, boolean z2, boolean z3) {
        return null;
    }

    @Nullable
    public Drawable getCompoundDrawableRight(@NonNull Context context, int i3, int i4, int i5, boolean z2, boolean z3) {
        return null;
    }

    @Nullable
    public Drawable getCompoundDrawableTop(@NonNull Context context, int i3, int i4, int i5, boolean z2, boolean z3) {
        return null;
    }

    @Nullable
    public CharSequence getContentDescription(@NonNull Context context, int i3, int i4, int i5, boolean z2, boolean z3, @Nullable CharSequence charSequence) {
        return charSequence;
    }

    @Nullable
    public ColorStateList getTextColor(@NonNull Context context, int i3, int i4, int i5, boolean z2, boolean z3) {
        return null;
    }

    public void initialize(@NonNull Context context) {
    }
}
