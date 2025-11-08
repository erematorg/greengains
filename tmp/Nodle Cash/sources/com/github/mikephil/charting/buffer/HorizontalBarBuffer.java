package com.github.mikephil.charting.buffer;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

public class HorizontalBarBuffer extends BarBuffer {
    public HorizontalBarBuffer(int i3, int i4, boolean z2) {
        super(i3, i4, z2);
    }

    public void feed(IBarDataSet iBarDataSet) {
        float f2;
        float f3;
        float f4;
        float f5;
        float entryCount = ((float) iBarDataSet.getEntryCount()) * this.phaseX;
        float f6 = this.mBarWidth / 2.0f;
        for (int i3 = 0; ((float) i3) < entryCount; i3++) {
            BarEntry barEntry = (BarEntry) iBarDataSet.getEntryForIndex(i3);
            if (barEntry != null) {
                float x2 = barEntry.getX();
                float y2 = barEntry.getY();
                float[] yVals = barEntry.getYVals();
                if (!this.mContainsStacks || yVals == null) {
                    float f7 = x2 - f6;
                    float f8 = x2 + f6;
                    if (this.mInverted) {
                        f2 = y2 >= 0.0f ? y2 : 0.0f;
                        if (y2 > 0.0f) {
                            y2 = 0.0f;
                        }
                    } else {
                        float f9 = y2 >= 0.0f ? y2 : 0.0f;
                        if (y2 > 0.0f) {
                            y2 = 0.0f;
                        }
                        float f10 = y2;
                        y2 = f9;
                        f2 = f10;
                    }
                    if (y2 > 0.0f) {
                        y2 *= this.phaseY;
                    } else {
                        f2 *= this.phaseY;
                    }
                    addBar(f2, f8, y2, f7);
                } else {
                    float f11 = -barEntry.getNegativeSum();
                    float f12 = 0.0f;
                    int i4 = 0;
                    while (i4 < yVals.length) {
                        float f13 = yVals[i4];
                        if (f13 >= 0.0f) {
                            f3 = f13 + f12;
                            f4 = f11;
                            f11 = f12;
                            f12 = f3;
                        } else {
                            f3 = Math.abs(f13) + f11;
                            f4 = Math.abs(f13) + f11;
                        }
                        float f14 = x2 - f6;
                        float f15 = x2 + f6;
                        if (this.mInverted) {
                            f5 = f11 >= f3 ? f11 : f3;
                            if (f11 > f3) {
                                f11 = f3;
                            }
                        } else {
                            float f16 = f11 >= f3 ? f11 : f3;
                            if (f11 > f3) {
                                f11 = f3;
                            }
                            float f17 = f11;
                            f11 = f16;
                            f5 = f17;
                        }
                        float f18 = this.phaseY;
                        addBar(f5 * f18, f15, f11 * f18, f14);
                        i4++;
                        f11 = f4;
                    }
                }
            }
        }
        reset();
    }
}
