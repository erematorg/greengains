package com.google.android.material.transition.platform;

import androidx.annotation.RequiresApi;

@RequiresApi(21)
class FadeModeResult {
    final int endAlpha;
    final boolean endOnTop;
    final int startAlpha;

    private FadeModeResult(int i3, int i4, boolean z2) {
        this.startAlpha = i3;
        this.endAlpha = i4;
        this.endOnTop = z2;
    }

    public static FadeModeResult endOnTop(int i3, int i4) {
        return new FadeModeResult(i3, i4, true);
    }

    public static FadeModeResult startOnTop(int i3, int i4) {
        return new FadeModeResult(i3, i4, false);
    }
}
