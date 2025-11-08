package com.github.mikephil.charting.components;

import android.content.Context;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import java.lang.ref.WeakReference;

public class MarkerView extends RelativeLayout implements IMarker {
    private MPPointF mOffset = new MPPointF();
    private MPPointF mOffset2 = new MPPointF();
    private WeakReference<Chart> mWeakChart;

    public MarkerView(Context context, int i3) {
        super(context);
        setupLayoutResource(i3);
    }

    private void setupLayoutResource(int i3) {
        View inflate = LayoutInflater.from(getContext()).inflate(i3, this);
        inflate.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        inflate.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        inflate.layout(0, 0, inflate.getMeasuredWidth(), inflate.getMeasuredHeight());
    }

    public void draw(Canvas canvas, float f2, float f3) {
        MPPointF offsetForDrawingAtPoint = getOffsetForDrawingAtPoint(f2, f3);
        int save = canvas.save();
        canvas.translate(f2 + offsetForDrawingAtPoint.f6574x, f3 + offsetForDrawingAtPoint.f6575y);
        draw(canvas);
        canvas.restoreToCount(save);
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
        MPPointF offset = getOffset();
        MPPointF mPPointF = this.mOffset2;
        mPPointF.f6574x = offset.f6574x;
        mPPointF.f6575y = offset.f6575y;
        Chart chartView = getChartView();
        float width = (float) getWidth();
        float height = (float) getHeight();
        MPPointF mPPointF2 = this.mOffset2;
        float f4 = mPPointF2.f6574x;
        if (f2 + f4 < 0.0f) {
            mPPointF2.f6574x = -f2;
        } else if (chartView != null && f2 + width + f4 > ((float) chartView.getWidth())) {
            this.mOffset2.f6574x = (((float) chartView.getWidth()) - f2) - width;
        }
        MPPointF mPPointF3 = this.mOffset2;
        float f5 = mPPointF3.f6575y;
        if (f3 + f5 < 0.0f) {
            mPPointF3.f6575y = -f3;
        } else if (chartView != null && f3 + height + f5 > ((float) chartView.getHeight())) {
            this.mOffset2.f6575y = (((float) chartView.getHeight()) - f3) - height;
        }
        return this.mOffset2;
    }

    public void refreshContent(Entry entry, Highlight highlight) {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
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

    public void setOffset(float f2, float f3) {
        MPPointF mPPointF = this.mOffset;
        mPPointF.f6574x = f2;
        mPPointF.f6575y = f3;
    }
}
