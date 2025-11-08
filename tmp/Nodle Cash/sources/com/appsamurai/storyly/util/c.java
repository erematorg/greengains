package com.appsamurai.storyly.util;

import android.widget.TextView;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class c {
    public static final void a(@NotNull TextView textView, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        if (z2 && z3) {
            textView.setTypeface(textView.getTypeface(), 3);
        } else if (z2) {
            textView.setTypeface(textView.getTypeface(), 1);
        } else if (z3) {
            textView.setTypeface(textView.getTypeface(), 2);
        } else {
            textView.setTypeface(textView.getTypeface(), 0);
        }
    }
}
