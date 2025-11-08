package com.google.android.material.color.utilities;

import android.support.v4.media.session.a;
import androidx.annotation.RestrictTo;
import androidx.constraintlayout.core.state.b;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class QuantizerWu implements Quantizer {
    private static final int INDEX_BITS = 5;
    private static final int INDEX_COUNT = 33;
    private static final int TOTAL_SIZE = 35937;
    Box[] cubes;
    double[] moments;
    int[] momentsB;
    int[] momentsG;
    int[] momentsR;
    int[] weights;

    /* renamed from: com.google.android.material.color.utilities.QuantizerWu$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.google.android.material.color.utilities.QuantizerWu$Direction[] r0 = com.google.android.material.color.utilities.QuantizerWu.Direction.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction = r0
                com.google.android.material.color.utilities.QuantizerWu$Direction r1 = com.google.android.material.color.utilities.QuantizerWu.Direction.RED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.android.material.color.utilities.QuantizerWu$Direction r1 = com.google.android.material.color.utilities.QuantizerWu.Direction.GREEN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.android.material.color.utilities.QuantizerWu$Direction r1 = com.google.android.material.color.utilities.QuantizerWu.Direction.BLUE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.color.utilities.QuantizerWu.AnonymousClass1.<clinit>():void");
        }
    }

    public static final class CreateBoxesResult {
        int resultCount;

        public CreateBoxesResult(int i3, int i4) {
            this.resultCount = i4;
        }
    }

    public enum Direction {
        RED,
        GREEN,
        BLUE
    }

    public static final class MaximizeResult {
        int cutLocation;
        double maximum;

        public MaximizeResult(int i3, double d2) {
            this.cutLocation = i3;
            this.maximum = d2;
        }
    }

    public static int bottom(Box box, Direction direction, int[] iArr) {
        int i3;
        int i4;
        int i5 = AnonymousClass1.$SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction[direction.ordinal()];
        if (i5 == 1) {
            i3 = (-iArr[getIndex(box.f6638r0, box.f6637g1, box.f6635b1)]) + iArr[getIndex(box.f6638r0, box.f6637g1, box.f6634b0)] + iArr[getIndex(box.f6638r0, box.f6636g0, box.f6635b1)];
            i4 = iArr[getIndex(box.f6638r0, box.f6636g0, box.f6634b0)];
        } else if (i5 == 2) {
            i3 = (-iArr[getIndex(box.f6639r1, box.f6636g0, box.f6635b1)]) + iArr[getIndex(box.f6639r1, box.f6636g0, box.f6634b0)] + iArr[getIndex(box.f6638r0, box.f6636g0, box.f6635b1)];
            i4 = iArr[getIndex(box.f6638r0, box.f6636g0, box.f6634b0)];
        } else if (i5 == 3) {
            i3 = (-iArr[getIndex(box.f6639r1, box.f6637g1, box.f6634b0)]) + iArr[getIndex(box.f6639r1, box.f6636g0, box.f6634b0)] + iArr[getIndex(box.f6638r0, box.f6637g1, box.f6634b0)];
            i4 = iArr[getIndex(box.f6638r0, box.f6636g0, box.f6634b0)];
        } else {
            throw new IllegalArgumentException("unexpected direction " + direction);
        }
        return i3 - i4;
    }

    public static int getIndex(int i3, int i4, int i5) {
        return a.D((i3 << 10) + (i3 << 6) + i3, i4 << 5, i4, i5);
    }

    public static int top(Box box, Direction direction, int i3, int[] iArr) {
        int i4;
        int i5;
        int i6 = AnonymousClass1.$SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction[direction.ordinal()];
        if (i6 == 1) {
            i4 = (iArr[getIndex(i3, box.f6637g1, box.f6635b1)] - iArr[getIndex(i3, box.f6637g1, box.f6634b0)]) - iArr[getIndex(i3, box.f6636g0, box.f6635b1)];
            i5 = iArr[getIndex(i3, box.f6636g0, box.f6634b0)];
        } else if (i6 == 2) {
            i4 = (iArr[getIndex(box.f6639r1, i3, box.f6635b1)] - iArr[getIndex(box.f6639r1, i3, box.f6634b0)]) - iArr[getIndex(box.f6638r0, i3, box.f6635b1)];
            i5 = iArr[getIndex(box.f6638r0, i3, box.f6634b0)];
        } else if (i6 == 3) {
            i4 = (iArr[getIndex(box.f6639r1, box.f6637g1, i3)] - iArr[getIndex(box.f6639r1, box.f6636g0, i3)]) - iArr[getIndex(box.f6638r0, box.f6637g1, i3)];
            i5 = iArr[getIndex(box.f6638r0, box.f6636g0, i3)];
        } else {
            throw new IllegalArgumentException("unexpected direction " + direction);
        }
        return i4 + i5;
    }

    public static int volume(Box box, int[] iArr) {
        return ((((((iArr[getIndex(box.f6639r1, box.f6637g1, box.f6635b1)] - iArr[getIndex(box.f6639r1, box.f6637g1, box.f6634b0)]) - iArr[getIndex(box.f6639r1, box.f6636g0, box.f6635b1)]) + iArr[getIndex(box.f6639r1, box.f6636g0, box.f6634b0)]) - iArr[getIndex(box.f6638r0, box.f6637g1, box.f6635b1)]) + iArr[getIndex(box.f6638r0, box.f6637g1, box.f6634b0)]) + iArr[getIndex(box.f6638r0, box.f6636g0, box.f6635b1)]) - iArr[getIndex(box.f6638r0, box.f6636g0, box.f6634b0)];
    }

    public void constructHistogram(Map<Integer, Integer> map) {
        this.weights = new int[TOTAL_SIZE];
        this.momentsR = new int[TOTAL_SIZE];
        this.momentsG = new int[TOTAL_SIZE];
        this.momentsB = new int[TOTAL_SIZE];
        this.moments = new double[TOTAL_SIZE];
        for (Map.Entry next : map.entrySet()) {
            int intValue = ((Integer) next.getKey()).intValue();
            int intValue2 = ((Integer) next.getValue()).intValue();
            int redFromArgb = ColorUtils.redFromArgb(intValue);
            int greenFromArgb = ColorUtils.greenFromArgb(intValue);
            int blueFromArgb = ColorUtils.blueFromArgb(intValue);
            int index = getIndex((redFromArgb >> 3) + 1, (greenFromArgb >> 3) + 1, (blueFromArgb >> 3) + 1);
            int[] iArr = this.weights;
            iArr[index] = iArr[index] + intValue2;
            int[] iArr2 = this.momentsR;
            iArr2[index] = (redFromArgb * intValue2) + iArr2[index];
            int[] iArr3 = this.momentsG;
            iArr3[index] = (greenFromArgb * intValue2) + iArr3[index];
            int[] iArr4 = this.momentsB;
            iArr4[index] = (blueFromArgb * intValue2) + iArr4[index];
            double[] dArr = this.moments;
            dArr[index] = dArr[index] + ((double) b.A(blueFromArgb, blueFromArgb, (greenFromArgb * greenFromArgb) + (redFromArgb * redFromArgb), intValue2));
        }
    }

    public CreateBoxesResult createBoxes(int i3) {
        int i4;
        this.cubes = new Box[i3];
        for (int i5 = 0; i5 < i3; i5++) {
            this.cubes[i5] = new Box((AnonymousClass1) null);
        }
        double[] dArr = new double[i3];
        Box box = this.cubes[0];
        box.f6639r1 = 32;
        box.f6637g1 = 32;
        box.f6635b1 = 32;
        int i6 = 0;
        int i7 = 1;
        while (true) {
            if (i7 >= i3) {
                i4 = i3;
                break;
            }
            Box[] boxArr = this.cubes;
            if (cut(boxArr[i6], boxArr[i7]).booleanValue()) {
                Box box2 = this.cubes[i6];
                dArr[i6] = box2.vol > 1 ? variance(box2) : 0.0d;
                Box box3 = this.cubes[i7];
                dArr[i7] = box3.vol > 1 ? variance(box3) : 0.0d;
            } else {
                dArr[i6] = 0.0d;
                i7--;
            }
            double d2 = dArr[0];
            int i8 = 0;
            for (int i9 = 1; i9 <= i7; i9++) {
                double d3 = dArr[i9];
                if (d3 > d2) {
                    i8 = i9;
                    d2 = d3;
                }
            }
            if (d2 <= 0.0d) {
                i4 = i7 + 1;
                break;
            }
            i7++;
            i6 = i8;
        }
        return new CreateBoxesResult(i3, i4);
    }

    public void createMoments() {
        int i3 = 1;
        while (true) {
            int i4 = 33;
            if (i3 < 33) {
                int[] iArr = new int[33];
                int[] iArr2 = new int[33];
                int[] iArr3 = new int[33];
                int[] iArr4 = new int[33];
                double[] dArr = new double[33];
                int i5 = 1;
                while (i5 < i4) {
                    int i6 = 0;
                    int i7 = 0;
                    double d2 = 0.0d;
                    int i8 = 1;
                    int i9 = 0;
                    int i10 = 0;
                    while (i8 < i4) {
                        int index = getIndex(i3, i5, i8);
                        int i11 = i6 + this.weights[index];
                        i9 += this.momentsR[index];
                        i10 += this.momentsG[index];
                        i7 += this.momentsB[index];
                        d2 += this.moments[index];
                        iArr[i8] = iArr[i8] + i11;
                        iArr2[i8] = iArr2[i8] + i9;
                        iArr3[i8] = iArr3[i8] + i10;
                        iArr4[i8] = iArr4[i8] + i7;
                        dArr[i8] = dArr[i8] + d2;
                        int index2 = getIndex(i3 - 1, i5, i8);
                        int i12 = i11;
                        int[] iArr5 = this.weights;
                        iArr5[index] = iArr5[index2] + iArr[i8];
                        int[] iArr6 = this.momentsR;
                        iArr6[index] = iArr6[index2] + iArr2[i8];
                        int[] iArr7 = this.momentsG;
                        iArr7[index] = iArr7[index2] + iArr3[i8];
                        int[] iArr8 = this.momentsB;
                        iArr8[index] = iArr8[index2] + iArr4[i8];
                        double[] dArr2 = this.moments;
                        dArr2[index] = dArr2[index2] + dArr[i8];
                        i8++;
                        i6 = i12;
                        i4 = 33;
                    }
                    i5++;
                    i4 = 33;
                }
                i3++;
            } else {
                return;
            }
        }
    }

    public List<Integer> createResult(int i3) {
        ArrayList arrayList = new ArrayList();
        for (int i4 = 0; i4 < i3; i4++) {
            Box box = this.cubes[i4];
            int volume = volume(box, this.weights);
            if (volume > 0) {
                int volume2 = volume(box, this.momentsG) / volume;
                arrayList.add(Integer.valueOf(((volume(box, this.momentsB) / volume) & 255) | (((volume(box, this.momentsR) / volume) & 255) << 16) | ViewCompat.MEASURED_STATE_MASK | ((volume2 & 255) << 8)));
            }
        }
        return arrayList;
    }

    public Boolean cut(Box box, Box box2) {
        Box box3 = box;
        Box box4 = box2;
        int volume = volume(box3, this.momentsR);
        int volume2 = volume(box3, this.momentsG);
        int volume3 = volume(box3, this.momentsB);
        int volume4 = volume(box3, this.weights);
        Direction direction = Direction.RED;
        Box box5 = box;
        int i3 = volume;
        int i4 = volume2;
        int i5 = volume3;
        MaximizeResult maximize = maximize(box5, direction, box3.f6638r0 + 1, box3.f6639r1, i3, i4, i5, volume4);
        Direction direction2 = Direction.GREEN;
        MaximizeResult maximizeResult = maximize;
        MaximizeResult maximize2 = maximize(box5, direction2, box3.f6636g0 + 1, box3.f6637g1, i3, i4, i5, volume4);
        Direction direction3 = Direction.BLUE;
        MaximizeResult maximizeResult2 = maximize2;
        MaximizeResult maximize3 = maximize(box5, direction3, box3.f6634b0 + 1, box3.f6635b1, i3, i4, i5, volume4);
        MaximizeResult maximizeResult3 = maximizeResult;
        double d2 = maximizeResult3.maximum;
        double d3 = maximizeResult2.maximum;
        double d4 = maximize3.maximum;
        if (d2 < d3 || d2 < d4) {
            direction = (d3 < d2 || d3 < d4) ? direction3 : direction2;
        } else if (maximizeResult3.cutLocation < 0) {
            return Boolean.FALSE;
        }
        box4.f6639r1 = box3.f6639r1;
        box4.f6637g1 = box3.f6637g1;
        box4.f6635b1 = box3.f6635b1;
        int i6 = AnonymousClass1.$SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction[direction.ordinal()];
        if (i6 == 1) {
            int i7 = maximizeResult3.cutLocation;
            box3.f6639r1 = i7;
            box4.f6638r0 = i7;
            box4.f6636g0 = box3.f6636g0;
            box4.f6634b0 = box3.f6634b0;
        } else if (i6 == 2) {
            int i8 = maximizeResult2.cutLocation;
            box3.f6637g1 = i8;
            box4.f6638r0 = box3.f6638r0;
            box4.f6636g0 = i8;
            box4.f6634b0 = box3.f6634b0;
        } else if (i6 == 3) {
            int i9 = maximize3.cutLocation;
            box3.f6635b1 = i9;
            box4.f6638r0 = box3.f6638r0;
            box4.f6636g0 = box3.f6636g0;
            box4.f6634b0 = i9;
        }
        box3.vol = (box3.f6635b1 - box3.f6634b0) * (box3.f6637g1 - box3.f6636g0) * (box3.f6639r1 - box3.f6638r0);
        box4.vol = (box4.f6635b1 - box4.f6634b0) * (box4.f6637g1 - box4.f6636g0) * (box4.f6639r1 - box4.f6638r0);
        return Boolean.TRUE;
    }

    public MaximizeResult maximize(Box box, Direction direction, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9;
        QuantizerWu quantizerWu = this;
        Box box2 = box;
        Direction direction2 = direction;
        int bottom = bottom(box2, direction2, quantizerWu.momentsR);
        int bottom2 = bottom(box2, direction2, quantizerWu.momentsG);
        int bottom3 = bottom(box2, direction2, quantizerWu.momentsB);
        int bottom4 = bottom(box2, direction2, quantizerWu.weights);
        int i10 = i4;
        int i11 = -1;
        double d2 = 0.0d;
        int i12 = i3;
        while (i12 < i10) {
            int pVar = top(box2, direction2, i12, quantizerWu.momentsR) + bottom;
            int pVar2 = top(box2, direction2, i12, quantizerWu.momentsG) + bottom2;
            int pVar3 = top(box2, direction2, i12, quantizerWu.momentsB) + bottom3;
            int pVar4 = top(box2, direction2, i12, quantizerWu.weights) + bottom4;
            if (pVar4 == 0) {
                i9 = bottom;
            } else {
                i9 = bottom;
                double d3 = ((double) ((pVar3 * pVar3) + ((pVar2 * pVar2) + (pVar * pVar)))) / ((double) pVar4);
                int i13 = i5 - pVar;
                int i14 = i6 - pVar2;
                int i15 = i7 - pVar3;
                int i16 = i8 - pVar4;
                if (i16 != 0) {
                    double d4 = (((double) ((i15 * i15) + ((i14 * i14) + (i13 * i13)))) / ((double) i16)) + d3;
                    if (d4 > d2) {
                        d2 = d4;
                        i11 = i12;
                    }
                }
            }
            i12++;
            quantizerWu = this;
            box2 = box;
            direction2 = direction;
            bottom = i9;
        }
        return new MaximizeResult(i11, d2);
    }

    public QuantizerResult quantize(int[] iArr, int i3) {
        constructHistogram(new QuantizerMap().quantize(iArr, i3).colorToCount);
        createMoments();
        List<Integer> createResult = createResult(createBoxes(i3).resultCount);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Integer next : createResult) {
            next.intValue();
            linkedHashMap.put(next, 0);
        }
        return new QuantizerResult(linkedHashMap);
    }

    public double variance(Box box) {
        int volume = volume(box, this.momentsR);
        int volume2 = volume(box, this.momentsG);
        int volume3 = volume(box, this.momentsB);
        return (((((((this.moments[getIndex(box.f6639r1, box.f6637g1, box.f6635b1)] - this.moments[getIndex(box.f6639r1, box.f6637g1, box.f6634b0)]) - this.moments[getIndex(box.f6639r1, box.f6636g0, box.f6635b1)]) + this.moments[getIndex(box.f6639r1, box.f6636g0, box.f6634b0)]) - this.moments[getIndex(box.f6638r0, box.f6637g1, box.f6635b1)]) + this.moments[getIndex(box.f6638r0, box.f6637g1, box.f6634b0)]) + this.moments[getIndex(box.f6638r0, box.f6636g0, box.f6635b1)]) - this.moments[getIndex(box.f6638r0, box.f6636g0, box.f6634b0)]) - (((double) ((volume3 * volume3) + ((volume2 * volume2) + (volume * volume)))) / ((double) volume(box, this.weights)));
    }

    public static final class Box {

        /* renamed from: b0  reason: collision with root package name */
        int f6634b0;

        /* renamed from: b1  reason: collision with root package name */
        int f6635b1;

        /* renamed from: g0  reason: collision with root package name */
        int f6636g0;

        /* renamed from: g1  reason: collision with root package name */
        int f6637g1;

        /* renamed from: r0  reason: collision with root package name */
        int f6638r0;

        /* renamed from: r1  reason: collision with root package name */
        int f6639r1;
        int vol;

        private Box() {
            this.f6638r0 = 0;
            this.f6639r1 = 0;
            this.f6636g0 = 0;
            this.f6637g1 = 0;
            this.f6634b0 = 0;
            this.f6635b1 = 0;
            this.vol = 0;
        }

        public /* synthetic */ Box(AnonymousClass1 r12) {
            this();
        }
    }
}
