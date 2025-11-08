package com.github.mikephil.charting.data;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.util.Log;
import com.github.mikephil.charting.formatter.DefaultFillFormatter;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class LineDataSet extends LineRadarDataSet<Entry> implements ILineDataSet {
    private List<Integer> mCircleColors = null;
    private int mCircleHoleColor = -1;
    private float mCircleHoleRadius = 4.0f;
    private float mCircleRadius = 8.0f;
    private float mCubicIntensity = 0.2f;
    private DashPathEffect mDashPathEffect = null;
    private boolean mDrawCircleHole = true;
    private boolean mDrawCircles = true;
    private IFillFormatter mFillFormatter = new DefaultFillFormatter();
    private Mode mMode = Mode.LINEAR;

    public enum Mode {
        LINEAR,
        STEPPED,
        CUBIC_BEZIER,
        HORIZONTAL_BEZIER
    }

    public LineDataSet(List<Entry> list, String str) {
        super(list, str);
        if (this.mCircleColors == null) {
            this.mCircleColors = new ArrayList();
        }
        this.mCircleColors.clear();
        this.mCircleColors.add(Integer.valueOf(Color.rgb(140, 234, 255)));
    }

    public DataSet<Entry> copy() {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < this.mValues.size(); i3++) {
            arrayList.add(((Entry) this.mValues.get(i3)).copy());
        }
        LineDataSet lineDataSet = new LineDataSet(arrayList, getLabel());
        copy(lineDataSet);
        return lineDataSet;
    }

    public void disableDashedLine() {
        this.mDashPathEffect = null;
    }

    public void enableDashedLine(float f2, float f3, float f4) {
        this.mDashPathEffect = new DashPathEffect(new float[]{f2, f3}, f4);
    }

    public int getCircleColor(int i3) {
        return this.mCircleColors.get(i3).intValue();
    }

    public int getCircleColorCount() {
        return this.mCircleColors.size();
    }

    public List<Integer> getCircleColors() {
        return this.mCircleColors;
    }

    public int getCircleHoleColor() {
        return this.mCircleHoleColor;
    }

    public float getCircleHoleRadius() {
        return this.mCircleHoleRadius;
    }

    public float getCircleRadius() {
        return this.mCircleRadius;
    }

    @Deprecated
    public float getCircleSize() {
        return getCircleRadius();
    }

    public float getCubicIntensity() {
        return this.mCubicIntensity;
    }

    public DashPathEffect getDashPathEffect() {
        return this.mDashPathEffect;
    }

    public IFillFormatter getFillFormatter() {
        return this.mFillFormatter;
    }

    public Mode getMode() {
        return this.mMode;
    }

    public boolean isDashedLineEnabled() {
        return this.mDashPathEffect != null;
    }

    public boolean isDrawCircleHoleEnabled() {
        return this.mDrawCircleHole;
    }

    public boolean isDrawCirclesEnabled() {
        return this.mDrawCircles;
    }

    @Deprecated
    public boolean isDrawCubicEnabled() {
        return this.mMode == Mode.CUBIC_BEZIER;
    }

    @Deprecated
    public boolean isDrawSteppedEnabled() {
        return this.mMode == Mode.STEPPED;
    }

    public void resetCircleColors() {
        if (this.mCircleColors == null) {
            this.mCircleColors = new ArrayList();
        }
        this.mCircleColors.clear();
    }

    public void setCircleColor(int i3) {
        resetCircleColors();
        this.mCircleColors.add(Integer.valueOf(i3));
    }

    public void setCircleColors(List<Integer> list) {
        this.mCircleColors = list;
    }

    public void setCircleHoleColor(int i3) {
        this.mCircleHoleColor = i3;
    }

    public void setCircleHoleRadius(float f2) {
        if (f2 >= 0.5f) {
            this.mCircleHoleRadius = Utils.convertDpToPixel(f2);
        } else {
            Log.e("LineDataSet", "Circle radius cannot be < 0.5");
        }
    }

    public void setCircleRadius(float f2) {
        if (f2 >= 1.0f) {
            this.mCircleRadius = Utils.convertDpToPixel(f2);
        } else {
            Log.e("LineDataSet", "Circle radius cannot be < 1");
        }
    }

    @Deprecated
    public void setCircleSize(float f2) {
        setCircleRadius(f2);
    }

    public void setCubicIntensity(float f2) {
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        if (f2 < 0.05f) {
            f2 = 0.05f;
        }
        this.mCubicIntensity = f2;
    }

    public void setDrawCircleHole(boolean z2) {
        this.mDrawCircleHole = z2;
    }

    public void setDrawCircles(boolean z2) {
        this.mDrawCircles = z2;
    }

    public void setFillFormatter(IFillFormatter iFillFormatter) {
        if (iFillFormatter == null) {
            this.mFillFormatter = new DefaultFillFormatter();
        } else {
            this.mFillFormatter = iFillFormatter;
        }
    }

    public void setMode(Mode mode) {
        this.mMode = mode;
    }

    public void setCircleColors(int... iArr) {
        this.mCircleColors = ColorTemplate.createColors(iArr);
    }

    public void setCircleColors(int[] iArr, Context context) {
        List<Integer> list = this.mCircleColors;
        if (list == null) {
            list = new ArrayList<>();
        }
        list.clear();
        for (int color : iArr) {
            list.add(Integer.valueOf(context.getResources().getColor(color)));
        }
        this.mCircleColors = list;
    }

    public void copy(LineDataSet lineDataSet) {
        super.copy(lineDataSet);
        lineDataSet.mCircleColors = this.mCircleColors;
        lineDataSet.mCircleHoleColor = this.mCircleHoleColor;
        lineDataSet.mCircleHoleRadius = this.mCircleHoleRadius;
        lineDataSet.mCircleRadius = this.mCircleRadius;
        lineDataSet.mCubicIntensity = this.mCubicIntensity;
        lineDataSet.mDashPathEffect = this.mDashPathEffect;
        lineDataSet.mDrawCircleHole = this.mDrawCircleHole;
        lineDataSet.mDrawCircles = this.mDrawCircleHole;
        lineDataSet.mFillFormatter = this.mFillFormatter;
        lineDataSet.mMode = this.mMode;
    }
}
