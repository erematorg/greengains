package com.google.android.material.carousel;

import androidx.annotation.NonNull;
import androidx.camera.camera2.internal.C0118y;
import androidx.core.math.MathUtils;

final class Arrangement {
    private static final float MEDIUM_ITEM_FLEX_PERCENTAGE = 0.1f;
    final float cost;
    final int largeCount;
    float largeSize;
    int mediumCount;
    float mediumSize;
    final int priority;
    int smallCount;
    float smallSize;

    public Arrangement(int i3, float f2, float f3, float f4, int i4, float f5, int i5, float f6, int i6, float f7) {
        this.priority = i3;
        this.smallSize = MathUtils.clamp(f2, f3, f4);
        this.smallCount = i4;
        this.mediumSize = f5;
        this.mediumCount = i5;
        this.largeSize = f6;
        this.largeCount = i6;
        fit(f7, f3, f4, f6);
        this.cost = cost(f6);
    }

    private float calculateLargeSize(float f2, int i3, float f3, int i4, int i5) {
        if (i3 <= 0) {
            f3 = 0.0f;
        }
        float f4 = (float) i3;
        float f5 = ((float) i4) / 2.0f;
        return (f2 - ((f4 + f5) * f3)) / (((float) i5) + f5);
    }

    private float cost(float f2) {
        if (!isValid()) {
            return Float.MAX_VALUE;
        }
        return Math.abs(f2 - this.largeSize) * ((float) this.priority);
    }

    public static Arrangement findLowestCostArrangement(float f2, float f3, float f4, float f5, int[] iArr, float f6, int[] iArr2, float f7, int[] iArr3) {
        int[] iArr4 = iArr;
        int[] iArr5 = iArr2;
        Arrangement arrangement = null;
        int i3 = 1;
        for (int i4 : iArr3) {
            int length = iArr5.length;
            int i5 = 0;
            while (i5 < length) {
                int i6 = iArr5[i5];
                int length2 = iArr4.length;
                int i7 = 0;
                while (i7 < length2) {
                    Arrangement arrangement2 = r8;
                    int i8 = i7;
                    int i9 = length2;
                    int i10 = i5;
                    int i11 = length;
                    Arrangement arrangement3 = new Arrangement(i3, f3, f4, f5, iArr4[i7], f6, i6, f7, i4, f2);
                    if (arrangement == null || arrangement2.cost < arrangement.cost) {
                        if (arrangement2.cost == 0.0f) {
                            return arrangement2;
                        }
                        arrangement = arrangement2;
                    }
                    i3++;
                    i7 = i8 + 1;
                    length2 = i9;
                    i5 = i10;
                    length = i11;
                }
                int i12 = length;
                i5++;
            }
        }
        return arrangement;
    }

    private void fit(float f2, float f3, float f4, float f5) {
        float space = f2 - getSpace();
        int i3 = this.smallCount;
        if (i3 > 0 && space > 0.0f) {
            float f6 = this.smallSize;
            this.smallSize = Math.min(space / ((float) i3), f4 - f6) + f6;
        } else if (i3 > 0 && space < 0.0f) {
            float f7 = this.smallSize;
            this.smallSize = Math.max(space / ((float) i3), f3 - f7) + f7;
        }
        int i4 = this.smallCount;
        float f8 = i4 > 0 ? this.smallSize : 0.0f;
        this.smallSize = f8;
        float calculateLargeSize = calculateLargeSize(f2, i4, f8, this.mediumCount, this.largeCount);
        this.largeSize = calculateLargeSize;
        float f9 = (this.smallSize + calculateLargeSize) / 2.0f;
        this.mediumSize = f9;
        int i5 = this.mediumCount;
        if (i5 > 0 && calculateLargeSize != f5) {
            float f10 = (f5 - calculateLargeSize) * ((float) this.largeCount);
            float min = Math.min(Math.abs(f10), f9 * 0.1f * ((float) i5));
            if (f10 > 0.0f) {
                this.mediumSize -= min / ((float) this.mediumCount);
                this.largeSize = (min / ((float) this.largeCount)) + this.largeSize;
                return;
            }
            this.mediumSize = (min / ((float) this.mediumCount)) + this.mediumSize;
            this.largeSize -= min / ((float) this.largeCount);
        }
    }

    private float getSpace() {
        return (this.smallSize * ((float) this.smallCount)) + (this.mediumSize * ((float) this.mediumCount)) + (this.largeSize * ((float) this.largeCount));
    }

    private boolean isValid() {
        int i3 = this.largeCount;
        if (i3 > 0 && this.smallCount > 0 && this.mediumCount > 0) {
            float f2 = this.largeSize;
            float f3 = this.mediumSize;
            return f2 > f3 && f3 > this.smallSize;
        } else if (i3 <= 0 || this.smallCount <= 0) {
            return true;
        } else {
            return this.largeSize > this.smallSize;
        }
    }

    public int getItemCount() {
        return this.smallCount + this.mediumCount + this.largeCount;
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder("Arrangement [priority=");
        sb.append(this.priority);
        sb.append(", smallCount=");
        sb.append(this.smallCount);
        sb.append(", smallSize=");
        sb.append(this.smallSize);
        sb.append(", mediumCount=");
        sb.append(this.mediumCount);
        sb.append(", mediumSize=");
        sb.append(this.mediumSize);
        sb.append(", largeCount=");
        sb.append(this.largeCount);
        sb.append(", largeSize=");
        sb.append(this.largeSize);
        sb.append(", cost=");
        return C0118y.i(sb, "]", this.cost);
    }
}
