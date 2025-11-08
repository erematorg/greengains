package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;
import java.util.LinkedHashMap;
import java.util.Map;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class QuantizerMap implements Quantizer {
    Map<Integer, Integer> colorToCount;

    public Map<Integer, Integer> getColorToCount() {
        return this.colorToCount;
    }

    public QuantizerResult quantize(int[] iArr, int i3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i4 : iArr) {
            Integer num = (Integer) linkedHashMap.get(Integer.valueOf(i4));
            int i5 = 1;
            if (num != null) {
                i5 = 1 + num.intValue();
            }
            linkedHashMap.put(Integer.valueOf(i4), Integer.valueOf(i5));
        }
        this.colorToCount = linkedHashMap;
        return new QuantizerResult(linkedHashMap);
    }
}
