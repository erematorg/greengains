package com.google.android.material.color;

import androidx.annotation.ColorInt;

public final class ColorRoles {
    private final int accent;
    private final int accentContainer;
    private final int onAccent;
    private final int onAccentContainer;

    public ColorRoles(@ColorInt int i3, @ColorInt int i4, @ColorInt int i5, @ColorInt int i6) {
        this.accent = i3;
        this.onAccent = i4;
        this.accentContainer = i5;
        this.onAccentContainer = i6;
    }

    @ColorInt
    public int getAccent() {
        return this.accent;
    }

    @ColorInt
    public int getAccentContainer() {
        return this.accentContainer;
    }

    @ColorInt
    public int getOnAccent() {
        return this.onAccent;
    }

    @ColorInt
    public int getOnAccentContainer() {
        return this.onAccentContainer;
    }
}
