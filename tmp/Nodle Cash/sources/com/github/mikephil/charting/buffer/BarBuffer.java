package com.github.mikephil.charting.buffer;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

public class BarBuffer extends AbstractBuffer<IBarDataSet> {
    protected float mBarWidth = 1.0f;
    protected boolean mContainsStacks;
    protected int mDataSetCount;
    protected int mDataSetIndex = 0;
    protected boolean mInverted = false;

    public BarBuffer(int i3, int i4, boolean z2) {
        super(i3);
        this.mDataSetCount = i4;
        this.mContainsStacks = z2;
    }

    public void addBar(float f2, float f3, float f4, float f5) {
        float[] fArr = this.buffer;
        int i3 = this.index;
        int i4 = i3 + 1;
        this.index = i4;
        fArr[i3] = f2;
        int i5 = i3 + 2;
        this.index = i5;
        fArr[i4] = f3;
        int i6 = i3 + 3;
        this.index = i6;
        fArr[i5] = f4;
        this.index = i3 + 4;
        fArr[i6] = f5;
    }

    public void setBarWidth(float f2) {
        this.mBarWidth = f2;
    }

    public void setDataSet(int i3) {
        this.mDataSetIndex = i3;
    }

    public void setInverted(boolean z2) {
        this.mInverted = z2;
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
                    addBar(f7, y2, f8, f2);
                } else {
                    float f11 = -barEntry.getNegativeSum();
                    float f12 = 0.0f;
                    int i4 = 0;
                    while (i4 < yVals.length) {
                        float f13 = yVals[i4];
                        int i5 = (f13 > 0.0f ? 1 : (f13 == 0.0f ? 0 : -1));
                        if (i5 == 0 && (f12 == 0.0f || f11 == 0.0f)) {
                            f3 = f13;
                            f4 = f11;
                            f11 = f3;
                        } else if (i5 >= 0) {
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
                        addBar(f14, f11 * f18, f15, f5 * f18);
                        i4++;
                        f11 = f4;
                    }
                }
            }
        }
        reset();
    }
}
