package com.google.zxing.oned;

import A.a;
import com.google.zxing.BarcodeFormat;
import java.util.Collection;
import java.util.Collections;

public final class ITFWriter extends OneDimensionalCodeWriter {
    private static final int[] END_PATTERN = {3, 1, 1};

    /* renamed from: N  reason: collision with root package name */
    private static final int f7207N = 1;
    private static final int[][] PATTERNS = {new int[]{1, 1, 3, 3, 1}, new int[]{3, 1, 1, 1, 3}, new int[]{1, 3, 1, 1, 3}, new int[]{3, 3, 1, 1, 1}, new int[]{1, 1, 3, 1, 3}, new int[]{3, 1, 3, 1, 1}, new int[]{1, 3, 3, 1, 1}, new int[]{1, 1, 1, 3, 3}, new int[]{3, 1, 1, 3, 1}, new int[]{1, 3, 1, 3, 1}};
    private static final int[] START_PATTERN = {1, 1, 1, 1};

    /* renamed from: W  reason: collision with root package name */
    private static final int f7208W = 3;

    public boolean[] encode(String str) {
        int length = str.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException("The length of the input should be even");
        } else if (length <= 80) {
            OneDimensionalCodeWriter.checkNumeric(str);
            boolean[] zArr = new boolean[((length * 9) + 9)];
            int appendPattern = OneDimensionalCodeWriter.appendPattern(zArr, 0, START_PATTERN, true);
            for (int i3 = 0; i3 < length; i3 += 2) {
                int digit = Character.digit(str.charAt(i3), 10);
                int digit2 = Character.digit(str.charAt(i3 + 1), 10);
                int[] iArr = new int[10];
                for (int i4 = 0; i4 < 5; i4++) {
                    int i5 = i4 * 2;
                    int[][] iArr2 = PATTERNS;
                    iArr[i5] = iArr2[digit][i4];
                    iArr[i5 + 1] = iArr2[digit2][i4];
                }
                appendPattern += OneDimensionalCodeWriter.appendPattern(zArr, appendPattern, iArr, true);
            }
            OneDimensionalCodeWriter.appendPattern(zArr, appendPattern, END_PATTERN, true);
            return zArr;
        } else {
            throw new IllegalArgumentException(a.k("Requested contents should be less than 80 digits long, but got ", length));
        }
    }

    public Collection<BarcodeFormat> getSupportedWriteFormats() {
        return Collections.singleton(BarcodeFormat.ITF);
    }
}
