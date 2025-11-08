package com.google.maps.android.heatmaps;

import android.graphics.Color;
import android.support.v4.media.session.a;
import java.util.HashMap;

public class Gradient {
    private static final int DEFAULT_COLOR_MAP_SIZE = 1000;
    public final int mColorMapSize;
    public int[] mColors;
    public float[] mStartPoints;

    public class ColorInterval {
        /* access modifiers changed from: private */
        public final int color1;
        /* access modifiers changed from: private */
        public final int color2;
        /* access modifiers changed from: private */
        public final float duration;

        private ColorInterval(int i3, int i4, float f2) {
            this.color1 = i3;
            this.color2 = i4;
            this.duration = f2;
        }
    }

    public Gradient(int[] iArr, float[] fArr) {
        this(iArr, fArr, 1000);
    }

    private HashMap<Integer, ColorInterval> generateColorIntervals() {
        HashMap<Integer, ColorInterval> hashMap = new HashMap<>();
        if (this.mStartPoints[0] != 0.0f) {
            hashMap.put(0, new ColorInterval(Color.argb(0, Color.red(this.mColors[0]), Color.green(this.mColors[0]), Color.blue(this.mColors[0])), this.mColors[0], ((float) this.mColorMapSize) * this.mStartPoints[0]));
        }
        for (int i3 = 1; i3 < this.mColors.length; i3++) {
            int i4 = i3 - 1;
            Integer valueOf = Integer.valueOf((int) (((float) this.mColorMapSize) * this.mStartPoints[i4]));
            int[] iArr = this.mColors;
            int i5 = iArr[i4];
            int i6 = iArr[i3];
            float[] fArr = this.mStartPoints;
            hashMap.put(valueOf, new ColorInterval(i5, i6, (fArr[i3] - fArr[i4]) * ((float) this.mColorMapSize)));
        }
        float[] fArr2 = this.mStartPoints;
        if (fArr2[fArr2.length - 1] != 1.0f) {
            int length = fArr2.length - 1;
            Integer valueOf2 = Integer.valueOf((int) (((float) this.mColorMapSize) * fArr2[length]));
            int i7 = this.mColors[length];
            hashMap.put(valueOf2, new ColorInterval(i7, i7, (1.0f - this.mStartPoints[length]) * ((float) this.mColorMapSize)));
        }
        return hashMap;
    }

    public static int interpolateColor(int i3, int i4, float f2) {
        int alpha = (int) ((((float) (Color.alpha(i4) - Color.alpha(i3))) * f2) + ((float) Color.alpha(i3)));
        float[] fArr = new float[3];
        Color.RGBToHSV(Color.red(i3), Color.green(i3), Color.blue(i3), fArr);
        float[] fArr2 = new float[3];
        Color.RGBToHSV(Color.red(i4), Color.green(i4), Color.blue(i4), fArr2);
        float f3 = fArr[0];
        float f4 = fArr2[0];
        if (f3 - f4 > 180.0f) {
            fArr2[0] = f4 + 360.0f;
        } else if (f4 - f3 > 180.0f) {
            fArr[0] = f3 + 360.0f;
        }
        float[] fArr3 = new float[3];
        for (int i5 = 0; i5 < 3; i5++) {
            float f5 = fArr2[i5];
            float f6 = fArr[i5];
            fArr3[i5] = a.b(f5, f6, f2, f6);
        }
        return Color.HSVToColor(alpha, fArr3);
    }

    public int[] generateColorMap(double d2) {
        HashMap<Integer, ColorInterval> generateColorIntervals = generateColorIntervals();
        int[] iArr = new int[this.mColorMapSize];
        ColorInterval colorInterval = generateColorIntervals.get(0);
        int i3 = 0;
        for (int i4 = 0; i4 < this.mColorMapSize; i4++) {
            if (generateColorIntervals.containsKey(Integer.valueOf(i4))) {
                colorInterval = generateColorIntervals.get(Integer.valueOf(i4));
                i3 = i4;
            }
            iArr[i4] = interpolateColor(colorInterval.color1, colorInterval.color2, ((float) (i4 - i3)) / colorInterval.duration);
        }
        if (d2 != 1.0d) {
            for (int i5 = 0; i5 < this.mColorMapSize; i5++) {
                int i6 = iArr[i5];
                iArr[i5] = Color.argb((int) (((double) Color.alpha(i6)) * d2), Color.red(i6), Color.green(i6), Color.blue(i6));
            }
        }
        return iArr;
    }

    public Gradient(int[] iArr, float[] fArr, int i3) {
        if (iArr.length != fArr.length) {
            throw new IllegalArgumentException("colors and startPoints should be same length");
        } else if (iArr.length != 0) {
            int i4 = 1;
            while (i4 < fArr.length) {
                if (fArr[i4] > fArr[i4 - 1]) {
                    i4++;
                } else {
                    throw new IllegalArgumentException("startPoints should be in increasing order");
                }
            }
            this.mColorMapSize = i3;
            int[] iArr2 = new int[iArr.length];
            this.mColors = iArr2;
            this.mStartPoints = new float[fArr.length];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            System.arraycopy(fArr, 0, this.mStartPoints, 0, fArr.length);
        } else {
            throw new IllegalArgumentException("No colors have been defined");
        }
    }
}
