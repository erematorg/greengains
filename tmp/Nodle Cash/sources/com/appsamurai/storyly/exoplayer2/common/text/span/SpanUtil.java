package com.appsamurai.storyly.exoplayer2.common.text.span;

import android.text.Spannable;

public final class SpanUtil {
    private SpanUtil() {
    }

    public static void addOrReplaceSpan(Spannable spannable, Object obj, int i3, int i4, int i5) {
        for (Object obj2 : spannable.getSpans(i3, i4, obj.getClass())) {
            if (spannable.getSpanStart(obj2) == i3 && spannable.getSpanEnd(obj2) == i4 && spannable.getSpanFlags(obj2) == i5) {
                spannable.removeSpan(obj2);
            }
        }
        spannable.setSpan(obj, i3, i4, i5);
    }
}
