package com.github.mikephil.charting.data;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.highlight.Range;

@SuppressLint({"ParcelCreator"})
public class BarEntry extends Entry {
    private float mNegativeSum;
    private float mPositiveSum;
    private Range[] mRanges;
    private float[] mYVals;

    public BarEntry(float f2, float f3) {
        super(f2, f3);
    }

    private void calcPosNegSum() {
        float[] fArr = this.mYVals;
        if (fArr == null) {
            this.mNegativeSum = 0.0f;
            this.mPositiveSum = 0.0f;
            return;
        }
        float f2 = 0.0f;
        float f3 = 0.0f;
        for (float f4 : fArr) {
            if (f4 <= 0.0f) {
                f2 += Math.abs(f4);
            } else {
                f3 += f4;
            }
        }
        this.mNegativeSum = f2;
        this.mPositiveSum = f3;
    }

    private static float calcSum(float[] fArr) {
        float f2 = 0.0f;
        if (fArr == null) {
            return 0.0f;
        }
        for (float f3 : fArr) {
            f2 += f3;
        }
        return f2;
    }

    public void calcRanges() {
        float[] yVals = getYVals();
        if (yVals != null && yVals.length != 0) {
            this.mRanges = new Range[yVals.length];
            float f2 = -getNegativeSum();
            int i3 = 0;
            float f3 = 0.0f;
            while (true) {
                Range[] rangeArr = this.mRanges;
                if (i3 < rangeArr.length) {
                    float f4 = yVals[i3];
                    if (f4 < 0.0f) {
                        float f5 = f2 - f4;
                        rangeArr[i3] = new Range(f2, f5);
                        f2 = f5;
                    } else {
                        float f6 = f4 + f3;
                        rangeArr[i3] = new Range(f3, f6);
                        f3 = f6;
                    }
                    i3++;
                } else {
                    return;
                }
            }
        }
    }

    @Deprecated
    public float getBelowSum(int i3) {
        return getSumBelow(i3);
    }

    public float getNegativeSum() {
        return this.mNegativeSum;
    }

    public float getPositiveSum() {
        return this.mPositiveSum;
    }

    public Range[] getRanges() {
        return this.mRanges;
    }

    public float getSumBelow(int i3) {
        float[] fArr = this.mYVals;
        float f2 = 0.0f;
        if (fArr == null) {
            return 0.0f;
        }
        int length = fArr.length - 1;
        while (length > i3 && length >= 0) {
            f2 += this.mYVals[length];
            length--;
        }
        return f2;
    }

    public float getY() {
        return super.getY();
    }

    public float[] getYVals() {
        return this.mYVals;
    }

    public boolean isStacked() {
        return this.mYVals != null;
    }

    public void setVals(float[] fArr) {
        setY(calcSum(fArr));
        this.mYVals = fArr;
        calcPosNegSum();
        calcRanges();
    }

    public BarEntry(float f2, float f3, Object obj) {
        super(f2, f3, obj);
    }

    public BarEntry copy() {
        BarEntry barEntry = new BarEntry(getX(), getY(), getData());
        barEntry.setVals(this.mYVals);
        return barEntry;
    }

    public BarEntry(float f2, float f3, Drawable drawable) {
        super(f2, f3, drawable);
    }

    public BarEntry(float f2, float f3, Drawable drawable, Object obj) {
        super(f2, f3, drawable, obj);
    }

    public BarEntry(float f2, float[] fArr) {
        super(f2, calcSum(fArr));
        this.mYVals = fArr;
        calcPosNegSum();
        calcRanges();
    }

    public BarEntry(float f2, float[] fArr, Object obj) {
        super(f2, calcSum(fArr), obj);
        this.mYVals = fArr;
        calcPosNegSum();
        calcRanges();
    }

    public BarEntry(float f2, float[] fArr, Drawable drawable) {
        super(f2, calcSum(fArr), drawable);
        this.mYVals = fArr;
        calcPosNegSum();
        calcRanges();
    }

    public BarEntry(float f2, float[] fArr, Drawable drawable, Object obj) {
        super(f2, calcSum(fArr), drawable, obj);
        this.mYVals = fArr;
        calcPosNegSum();
        calcRanges();
    }
}
