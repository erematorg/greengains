package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
interface Quantizer {
    QuantizerResult quantize(int[] iArr, int i3);
}
