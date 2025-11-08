package com.airbnb.lottie.parser;

import android.graphics.Color;
import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.GammaEvaluator;
import com.airbnb.lottie.utils.MiscUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GradientColorParser implements ValueParser<GradientColor> {
    private int colorPoints;

    public GradientColorParser(int i3) {
        this.colorPoints = i3;
    }

    private GradientColor addOpacityStopsToGradientIfNeeded(GradientColor gradientColor, List<Float> list) {
        int i3 = this.colorPoints * 4;
        if (list.size() <= i3) {
            return gradientColor;
        }
        float[] positions = gradientColor.getPositions();
        int[] colors = gradientColor.getColors();
        int size = (list.size() - i3) / 2;
        float[] fArr = new float[size];
        float[] fArr2 = new float[size];
        int i4 = 0;
        while (i3 < list.size()) {
            if (i3 % 2 == 0) {
                fArr[i4] = list.get(i3).floatValue();
            } else {
                fArr2[i4] = list.get(i3).floatValue();
                i4++;
            }
            i3++;
        }
        float[] mergeUniqueElements = mergeUniqueElements(gradientColor.getPositions(), fArr);
        int length = mergeUniqueElements.length;
        int[] iArr = new int[length];
        for (int i5 = 0; i5 < length; i5++) {
            float f2 = mergeUniqueElements[i5];
            int binarySearch = Arrays.binarySearch(positions, f2);
            int binarySearch2 = Arrays.binarySearch(fArr, f2);
            if (binarySearch < 0 || binarySearch2 > 0) {
                if (binarySearch2 < 0) {
                    binarySearch2 = -(binarySearch2 + 1);
                }
                iArr[i5] = getColorInBetweenColorStops(f2, fArr2[binarySearch2], positions, colors);
            } else {
                iArr[i5] = getColorInBetweenOpacityStops(f2, colors[binarySearch], fArr, fArr2);
            }
        }
        return new GradientColor(mergeUniqueElements, iArr);
    }

    private int getColorInBetweenOpacityStops(float f2, int i3, float[] fArr, float[] fArr2) {
        float lerp;
        if (fArr2.length < 2 || f2 <= fArr[0]) {
            return Color.argb((int) (fArr2[0] * 255.0f), Color.red(i3), Color.green(i3), Color.blue(i3));
        }
        for (int i4 = 1; i4 < fArr.length; i4++) {
            float f3 = fArr[i4];
            int i5 = (f3 > f2 ? 1 : (f3 == f2 ? 0 : -1));
            if (i5 >= 0 || i4 == fArr.length - 1) {
                if (i5 <= 0) {
                    lerp = fArr2[i4];
                } else {
                    int i6 = i4 - 1;
                    float f4 = fArr[i6];
                    lerp = MiscUtils.lerp(fArr2[i6], fArr2[i4], (f2 - f4) / (f3 - f4));
                }
                return Color.argb((int) (lerp * 255.0f), Color.red(i3), Color.green(i3), Color.blue(i3));
            }
        }
        throw new IllegalArgumentException("Unreachable code.");
    }

    public static float[] mergeUniqueElements(float[] fArr, float[] fArr2) {
        if (fArr.length == 0) {
            return fArr2;
        }
        if (fArr2.length == 0) {
            return fArr;
        }
        int length = fArr.length + fArr2.length;
        float[] fArr3 = new float[length];
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < length; i6++) {
            float f2 = Float.NaN;
            float f3 = i4 < fArr.length ? fArr[i4] : Float.NaN;
            if (i5 < fArr2.length) {
                f2 = fArr2[i5];
            }
            if (Float.isNaN(f2) || f3 < f2) {
                fArr3[i6] = f3;
                i4++;
            } else if (Float.isNaN(f3) || f2 < f3) {
                fArr3[i6] = f2;
                i5++;
            } else {
                fArr3[i6] = f3;
                i4++;
                i5++;
                i3++;
            }
        }
        return i3 == 0 ? fArr3 : Arrays.copyOf(fArr3, length - i3);
    }

    public int getColorInBetweenColorStops(float f2, float f3, float[] fArr, int[] iArr) {
        if (iArr.length < 2 || f2 == fArr[0]) {
            return iArr[0];
        }
        int i3 = 1;
        while (i3 < fArr.length) {
            float f4 = fArr[i3];
            if (f4 < f2 && i3 != fArr.length - 1) {
                i3++;
            } else if (i3 == fArr.length - 1 && f2 >= f4) {
                return Color.argb((int) (f3 * 255.0f), Color.red(iArr[i3]), Color.green(iArr[i3]), Color.blue(iArr[i3]));
            } else {
                int i4 = i3 - 1;
                float f5 = fArr[i4];
                float f6 = (f2 - f5) / (f4 - f5);
                int i5 = iArr[i3];
                int i6 = iArr[i4];
                return Color.argb((int) (f3 * 255.0f), GammaEvaluator.evaluate(f6, Color.red(i6), Color.red(i5)), GammaEvaluator.evaluate(f6, Color.green(i6), Color.green(i5)), GammaEvaluator.evaluate(f6, Color.blue(i6), Color.blue(i5)));
            }
        }
        throw new IllegalArgumentException("Unreachable code.");
    }

    public GradientColor parse(JsonReader jsonReader, float f2) throws IOException {
        ArrayList arrayList = new ArrayList();
        boolean z2 = jsonReader.peek() == JsonReader.Token.BEGIN_ARRAY;
        if (z2) {
            jsonReader.beginArray();
        }
        while (jsonReader.hasNext()) {
            arrayList.add(Float.valueOf((float) jsonReader.nextDouble()));
        }
        if (arrayList.size() == 4 && ((Float) arrayList.get(0)).floatValue() == 1.0f) {
            arrayList.set(0, Float.valueOf(0.0f));
            arrayList.add(Float.valueOf(1.0f));
            arrayList.add((Float) arrayList.get(1));
            arrayList.add((Float) arrayList.get(2));
            arrayList.add((Float) arrayList.get(3));
            this.colorPoints = 2;
        }
        if (z2) {
            jsonReader.endArray();
        }
        if (this.colorPoints == -1) {
            this.colorPoints = arrayList.size() / 4;
        }
        int i3 = this.colorPoints;
        float[] fArr = new float[i3];
        int[] iArr = new int[i3];
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < this.colorPoints * 4; i6++) {
            int i7 = i6 / 4;
            double floatValue = (double) ((Float) arrayList.get(i6)).floatValue();
            int i8 = i6 % 4;
            if (i8 == 0) {
                if (i7 > 0) {
                    float f3 = (float) floatValue;
                    if (fArr[i7 - 1] >= f3) {
                        fArr[i7] = f3 + 0.01f;
                    }
                }
                fArr[i7] = (float) floatValue;
            } else if (i8 == 1) {
                i4 = (int) (floatValue * 255.0d);
            } else if (i8 == 2) {
                i5 = (int) (floatValue * 255.0d);
            } else if (i8 == 3) {
                iArr[i7] = Color.argb(255, i4, i5, (int) (floatValue * 255.0d));
            }
        }
        return addOpacityStopsToGradientIfNeeded(new GradientColor(fArr, iArr), arrayList);
    }
}
