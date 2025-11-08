package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class RadarChartRenderer extends LineRadarRenderer {
    protected RadarChart mChart;
    protected Path mDrawDataSetSurfacePathBuffer = new Path();
    protected Path mDrawHighlightCirclePathBuffer = new Path();
    protected Paint mHighlightCirclePaint;
    protected Paint mWebPaint;

    public RadarChartRenderer(RadarChart radarChart, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mChart = radarChart;
        Paint paint = new Paint(1);
        this.mHighlightPaint = paint;
        Paint.Style style = Paint.Style.STROKE;
        paint.setStyle(style);
        this.mHighlightPaint.setStrokeWidth(2.0f);
        this.mHighlightPaint.setColor(Color.rgb(255, 187, 115));
        Paint paint2 = new Paint(1);
        this.mWebPaint = paint2;
        paint2.setStyle(style);
        this.mHighlightCirclePaint = new Paint(1);
    }

    public void drawData(Canvas canvas) {
        RadarData radarData = (RadarData) this.mChart.getData();
        int entryCount = ((IRadarDataSet) radarData.getMaxEntryCountSet()).getEntryCount();
        for (IRadarDataSet iRadarDataSet : radarData.getDataSets()) {
            if (iRadarDataSet.isVisible()) {
                drawDataSet(canvas, iRadarDataSet, entryCount);
            }
        }
    }

    public void drawDataSet(Canvas canvas, IRadarDataSet iRadarDataSet, int i3) {
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        float sliceAngle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        MPPointF centerOffsets = this.mChart.getCenterOffsets();
        MPPointF instance = MPPointF.getInstance(0.0f, 0.0f);
        Path path = this.mDrawDataSetSurfacePathBuffer;
        path.reset();
        boolean z2 = false;
        for (int i4 = 0; i4 < iRadarDataSet.getEntryCount(); i4++) {
            this.mRenderPaint.setColor(iRadarDataSet.getColor(i4));
            Utils.getPosition(centerOffsets, (((RadarEntry) iRadarDataSet.getEntryForIndex(i4)).getY() - this.mChart.getYChartMin()) * factor * phaseY, this.mChart.getRotationAngle() + (((float) i4) * sliceAngle * phaseX), instance);
            if (!Float.isNaN(instance.f6574x)) {
                if (!z2) {
                    path.moveTo(instance.f6574x, instance.f6575y);
                    z2 = true;
                } else {
                    path.lineTo(instance.f6574x, instance.f6575y);
                }
            }
        }
        if (iRadarDataSet.getEntryCount() > i3) {
            path.lineTo(centerOffsets.f6574x, centerOffsets.f6575y);
        }
        path.close();
        if (iRadarDataSet.isDrawFilledEnabled()) {
            Drawable fillDrawable = iRadarDataSet.getFillDrawable();
            if (fillDrawable != null) {
                drawFilledPath(canvas, path, fillDrawable);
            } else {
                drawFilledPath(canvas, path, iRadarDataSet.getFillColor(), iRadarDataSet.getFillAlpha());
            }
        }
        this.mRenderPaint.setStrokeWidth(iRadarDataSet.getLineWidth());
        this.mRenderPaint.setStyle(Paint.Style.STROKE);
        if (!iRadarDataSet.isDrawFilledEnabled() || iRadarDataSet.getFillAlpha() < 255) {
            canvas.drawPath(path, this.mRenderPaint);
        }
        MPPointF.recycleInstance(centerOffsets);
        MPPointF.recycleInstance(instance);
    }

    public void drawExtras(Canvas canvas) {
        drawWeb(canvas);
    }

    public void drawHighlightCircle(Canvas canvas, MPPointF mPPointF, float f2, float f3, int i3, int i4, float f4) {
        canvas.save();
        float convertDpToPixel = Utils.convertDpToPixel(f3);
        float convertDpToPixel2 = Utils.convertDpToPixel(f2);
        if (i3 != 1122867) {
            Path path = this.mDrawHighlightCirclePathBuffer;
            path.reset();
            path.addCircle(mPPointF.f6574x, mPPointF.f6575y, convertDpToPixel, Path.Direction.CW);
            if (convertDpToPixel2 > 0.0f) {
                path.addCircle(mPPointF.f6574x, mPPointF.f6575y, convertDpToPixel2, Path.Direction.CCW);
            }
            this.mHighlightCirclePaint.setColor(i3);
            this.mHighlightCirclePaint.setStyle(Paint.Style.FILL);
            canvas.drawPath(path, this.mHighlightCirclePaint);
        }
        if (i4 != 1122867) {
            this.mHighlightCirclePaint.setColor(i4);
            this.mHighlightCirclePaint.setStyle(Paint.Style.STROKE);
            this.mHighlightCirclePaint.setStrokeWidth(Utils.convertDpToPixel(f4));
            canvas.drawCircle(mPPointF.f6574x, mPPointF.f6575y, convertDpToPixel, this.mHighlightCirclePaint);
        }
        canvas.restore();
    }

    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        int i3;
        int i4;
        Highlight[] highlightArr2 = highlightArr;
        float sliceAngle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        MPPointF centerOffsets = this.mChart.getCenterOffsets();
        MPPointF instance = MPPointF.getInstance(0.0f, 0.0f);
        RadarData radarData = (RadarData) this.mChart.getData();
        int length = highlightArr2.length;
        int i5 = 0;
        int i6 = 0;
        while (i6 < length) {
            Highlight highlight = highlightArr2[i6];
            IRadarDataSet iRadarDataSet = (IRadarDataSet) radarData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iRadarDataSet != null && iRadarDataSet.isHighlightEnabled()) {
                RadarEntry radarEntry = (RadarEntry) iRadarDataSet.getEntryForIndex((int) highlight.getX());
                if (isInBoundsX(radarEntry, iRadarDataSet)) {
                    Utils.getPosition(centerOffsets, this.mAnimator.getPhaseY() * (radarEntry.getY() - this.mChart.getYChartMin()) * factor, this.mChart.getRotationAngle() + (this.mAnimator.getPhaseX() * highlight.getX() * sliceAngle), instance);
                    highlight.setDraw(instance.f6574x, instance.f6575y);
                    drawHighlightLines(canvas, instance.f6574x, instance.f6575y, iRadarDataSet);
                    if (iRadarDataSet.isDrawHighlightCircleEnabled() && !Float.isNaN(instance.f6574x) && !Float.isNaN(instance.f6575y)) {
                        int highlightCircleStrokeColor = iRadarDataSet.getHighlightCircleStrokeColor();
                        if (highlightCircleStrokeColor == 1122867) {
                            highlightCircleStrokeColor = iRadarDataSet.getColor(i5);
                        }
                        if (iRadarDataSet.getHighlightCircleStrokeAlpha() < 255) {
                            highlightCircleStrokeColor = ColorTemplate.colorWithAlpha(highlightCircleStrokeColor, iRadarDataSet.getHighlightCircleStrokeAlpha());
                        }
                        float highlightCircleInnerRadius = iRadarDataSet.getHighlightCircleInnerRadius();
                        float highlightCircleOuterRadius = iRadarDataSet.getHighlightCircleOuterRadius();
                        int highlightCircleFillColor = iRadarDataSet.getHighlightCircleFillColor();
                        i3 = i6;
                        i4 = i5;
                        drawHighlightCircle(canvas, instance, highlightCircleInnerRadius, highlightCircleOuterRadius, highlightCircleFillColor, highlightCircleStrokeColor, iRadarDataSet.getHighlightCircleStrokeWidth());
                        i6 = i3 + 1;
                        i5 = i4;
                    }
                }
            }
            i3 = i6;
            i4 = i5;
            i6 = i3 + 1;
            i5 = i4;
        }
        MPPointF.recycleInstance(centerOffsets);
        MPPointF.recycleInstance(instance);
    }

    public void drawValue(Canvas canvas, String str, float f2, float f3, int i3) {
        this.mValuePaint.setColor(i3);
        canvas.drawText(str, f2, f3, this.mValuePaint);
    }

    public void drawValues(Canvas canvas) {
        float f2;
        int i3;
        float f3;
        int i4;
        int i5;
        RadarEntry radarEntry;
        ValueFormatter valueFormatter;
        IRadarDataSet iRadarDataSet;
        MPPointF mPPointF;
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        float sliceAngle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        MPPointF centerOffsets = this.mChart.getCenterOffsets();
        MPPointF instance = MPPointF.getInstance(0.0f, 0.0f);
        MPPointF instance2 = MPPointF.getInstance(0.0f, 0.0f);
        float convertDpToPixel = Utils.convertDpToPixel(5.0f);
        int i6 = 0;
        while (i6 < ((RadarData) this.mChart.getData()).getDataSetCount()) {
            IRadarDataSet iRadarDataSet2 = (IRadarDataSet) ((RadarData) this.mChart.getData()).getDataSetByIndex(i6);
            if (!shouldDrawValues(iRadarDataSet2)) {
                i3 = i6;
                f2 = phaseX;
            } else {
                applyValueTextStyle(iRadarDataSet2);
                ValueFormatter valueFormatter2 = iRadarDataSet2.getValueFormatter();
                MPPointF instance3 = MPPointF.getInstance(iRadarDataSet2.getIconsOffset());
                instance3.f6574x = Utils.convertDpToPixel(instance3.f6574x);
                instance3.f6575y = Utils.convertDpToPixel(instance3.f6575y);
                int i7 = 0;
                while (i7 < iRadarDataSet2.getEntryCount()) {
                    RadarEntry radarEntry2 = (RadarEntry) iRadarDataSet2.getEntryForIndex(i7);
                    MPPointF mPPointF2 = instance3;
                    float f4 = ((float) i7) * sliceAngle * phaseX;
                    Utils.getPosition(centerOffsets, (radarEntry2.getY() - this.mChart.getYChartMin()) * factor * phaseY, this.mChart.getRotationAngle() + f4, instance);
                    if (iRadarDataSet2.isDrawValuesEnabled()) {
                        String radarLabel = valueFormatter2.getRadarLabel(radarEntry2);
                        float f5 = instance.f6574x;
                        radarEntry = radarEntry2;
                        float f6 = instance.f6575y - convertDpToPixel;
                        int valueTextColor = iRadarDataSet2.getValueTextColor(i7);
                        i4 = i7;
                        f3 = phaseX;
                        mPPointF = mPPointF2;
                        valueFormatter = valueFormatter2;
                        float f7 = f5;
                        iRadarDataSet = iRadarDataSet2;
                        float f8 = f6;
                        i5 = i6;
                        drawValue(canvas, radarLabel, f7, f8, valueTextColor);
                    } else {
                        radarEntry = radarEntry2;
                        i4 = i7;
                        iRadarDataSet = iRadarDataSet2;
                        i5 = i6;
                        f3 = phaseX;
                        mPPointF = mPPointF2;
                        valueFormatter = valueFormatter2;
                    }
                    if (radarEntry.getIcon() != null && iRadarDataSet.isDrawIconsEnabled()) {
                        Drawable icon = radarEntry.getIcon();
                        Utils.getPosition(centerOffsets, (radarEntry.getY() * factor * phaseY) + mPPointF.f6575y, this.mChart.getRotationAngle() + f4, instance2);
                        float f9 = instance2.f6575y + mPPointF.f6574x;
                        instance2.f6575y = f9;
                        Utils.drawImage(canvas, icon, (int) instance2.f6574x, (int) f9, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                    }
                    i7 = i4 + 1;
                    instance3 = mPPointF;
                    iRadarDataSet2 = iRadarDataSet;
                    valueFormatter2 = valueFormatter;
                    i6 = i5;
                    phaseX = f3;
                }
                i3 = i6;
                f2 = phaseX;
                MPPointF.recycleInstance(instance3);
            }
            i6 = i3 + 1;
            phaseX = f2;
        }
        MPPointF.recycleInstance(centerOffsets);
        MPPointF.recycleInstance(instance);
        MPPointF.recycleInstance(instance2);
    }

    public void drawWeb(Canvas canvas) {
        float sliceAngle = this.mChart.getSliceAngle();
        float factor = this.mChart.getFactor();
        float rotationAngle = this.mChart.getRotationAngle();
        MPPointF centerOffsets = this.mChart.getCenterOffsets();
        this.mWebPaint.setStrokeWidth(this.mChart.getWebLineWidth());
        this.mWebPaint.setColor(this.mChart.getWebColor());
        this.mWebPaint.setAlpha(this.mChart.getWebAlpha());
        int skipWebLineCount = this.mChart.getSkipWebLineCount() + 1;
        int entryCount = ((IRadarDataSet) ((RadarData) this.mChart.getData()).getMaxEntryCountSet()).getEntryCount();
        MPPointF instance = MPPointF.getInstance(0.0f, 0.0f);
        for (int i3 = 0; i3 < entryCount; i3 += skipWebLineCount) {
            Utils.getPosition(centerOffsets, this.mChart.getYRange() * factor, (((float) i3) * sliceAngle) + rotationAngle, instance);
            canvas.drawLine(centerOffsets.f6574x, centerOffsets.f6575y, instance.f6574x, instance.f6575y, this.mWebPaint);
        }
        MPPointF.recycleInstance(instance);
        this.mWebPaint.setStrokeWidth(this.mChart.getWebLineWidthInner());
        this.mWebPaint.setColor(this.mChart.getWebColorInner());
        this.mWebPaint.setAlpha(this.mChart.getWebAlpha());
        int i4 = this.mChart.getYAxis().mEntryCount;
        MPPointF instance2 = MPPointF.getInstance(0.0f, 0.0f);
        MPPointF instance3 = MPPointF.getInstance(0.0f, 0.0f);
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = 0;
            while (i6 < ((RadarData) this.mChart.getData()).getEntryCount()) {
                float yChartMin = (this.mChart.getYAxis().mEntries[i5] - this.mChart.getYChartMin()) * factor;
                Utils.getPosition(centerOffsets, yChartMin, (((float) i6) * sliceAngle) + rotationAngle, instance2);
                i6++;
                Utils.getPosition(centerOffsets, yChartMin, (((float) i6) * sliceAngle) + rotationAngle, instance3);
                canvas.drawLine(instance2.f6574x, instance2.f6575y, instance3.f6574x, instance3.f6575y, this.mWebPaint);
            }
        }
        MPPointF.recycleInstance(instance2);
        MPPointF.recycleInstance(instance3);
    }

    public Paint getWebPaint() {
        return this.mWebPaint;
    }

    public void initBuffers() {
    }
}
