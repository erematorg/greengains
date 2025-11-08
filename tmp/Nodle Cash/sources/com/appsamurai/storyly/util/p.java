package com.appsamurai.storyly.util;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.widget.TextView;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class p {
    @NotNull
    public static final Rect a(@NotNull Context context, @NotNull String str, float f2, @NotNull Typeface typeface) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "text");
        Intrinsics.checkNotNullParameter(typeface, "textFont");
        TextView textView = new TextView(context);
        textView.setTypeface(typeface);
        TextPaint paint = textView.getPaint();
        paint.setTextSize(f2);
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect;
    }
}
