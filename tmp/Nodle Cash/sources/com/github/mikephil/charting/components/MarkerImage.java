package com.github.mikephil.charting.components;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.MPPointF;
import java.lang.ref.WeakReference;

public class MarkerImage implements IMarker {
    private Context mContext;
    private Drawable mDrawable;
    private Rect mDrawableBoundsCache = new Rect();
    private MPPointF mOffset = new MPPointF();
    private MPPointF mOffset2 = new MPPointF();
    private FSize mSize = new FSize();
    private WeakReference<Chart> mWeakChart;

    public MarkerImage(Context context, int i3) {
        this.mContext = context;
        this.mDrawable = context.getResources().getDrawable(i3, (Resources.Theme) null);
    }

    public void draw(Canvas canvas, float f2, float f3) {
        if (this.mDrawable != null) {
            MPPointF offsetForDrawingAtPoint = getOffsetForDrawingAtPoint(f2, f3);
            FSize fSize = this.mSize;
            float f4 = fSize.width;
            float f5 = fSize.height;
            if (f4 == 0.0f) {
                f4 = (float) this.mDrawable.getIntrinsicWidth();
            }
            if (f5 == 0.0f) {
                f5 = (float) this.mDrawable.getIntrinsicHeight();
            }
            this.mDrawable.copyBounds(this.mDrawableBoundsCache);
            Drawable drawable = this.mDrawable;
            Rect rect = this.mDrawableBoundsCache;
            int i3 = rect.left;
            int i4 = rect.top;
            drawable.setBounds(i3, i4, ((int) f4) + i3, ((int) f5) + i4);
            int save = canvas.save();
            canvas.translate(f2 + offsetForDrawingAtPoint.f6574x, f3 + offsetForDrawingAtPoint.f6575y);
            this.mDrawable.draw(canvas);
            canvas.restoreToCount(save);
            this.mDrawable.setBounds(this.mDrawableBoundsCache);
        }
    }

    public Chart getChartView() {
        WeakReference<Chart> weakReference = this.mWeakChart;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public MPPointF getOffset() {
        return this.mOffset;
    }

    public MPPointF getOffsetForDrawingAtPoint(float f2, float f3) {
        Drawable drawable;
        Drawable drawable2;
        MPPointF offset = getOffset();
        MPPointF mPPointF = this.mOffset2;
        mPPointF.f6574x = offset.f6574x;
        mPPointF.f6575y = offset.f6575y;
        Chart chartView = getChartView();
        FSize fSize = this.mSize;
        float f4 = fSize.width;
        float f5 = fSize.height;
        if (f4 == 0.0f && (drawable2 = this.mDrawable) != null) {
            f4 = (float) drawable2.getIntrinsicWidth();
        }
        if (f5 == 0.0f && (drawable = this.mDrawable) != null) {
            f5 = (float) drawable.getIntrinsicHeight();
        }
        MPPointF mPPointF2 = this.mOffset2;
        float f6 = mPPointF2.f6574x;
        if (f2 + f6 < 0.0f) {
            mPPointF2.f6574x = -f2;
        } else if (chartView != null && f2 + f4 + f6 > ((float) chartView.getWidth())) {
            this.mOffset2.f6574x = (((float) chartView.getWidth()) - f2) - f4;
        }
        MPPointF mPPointF3 = this.mOffset2;
        float f7 = mPPointF3.f6575y;
        if (f3 + f7 < 0.0f) {
            mPPointF3.f6575y = -f3;
        } else if (chartView != null && f3 + f5 + f7 > ((float) chartView.getHeight())) {
            this.mOffset2.f6575y = (((float) chartView.getHeight()) - f3) - f5;
        }
        return this.mOffset2;
    }

    public FSize getSize() {
        return this.mSize;
    }

    public void refreshContent(Entry entry, Highlight highlight) {
    }

    public void setChartView(Chart chart) {
        this.mWeakChart = new WeakReference<>(chart);
    }

    public void setOffset(MPPointF mPPointF) {
        this.mOffset = mPPointF;
        if (mPPointF == null) {
            this.mOffset = new MPPointF();
        }
    }

    public void setSize(FSize fSize) {
        this.mSize = fSize;
        if (fSize == null) {
            this.mSize = new FSize();
        }
    }

    public void setOffset(float f2, float f3) {
        MPPointF mPPointF = this.mOffset;
        mPPointF.f6574x = f2;
        mPPointF.f6575y = f3;
    }
}
