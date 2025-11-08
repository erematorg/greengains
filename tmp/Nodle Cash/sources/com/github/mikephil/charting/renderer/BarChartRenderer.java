package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.Range;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class BarChartRenderer extends BarLineScatterCandleBubbleRenderer {
    protected Paint mBarBorderPaint;
    protected BarBuffer[] mBarBuffers;
    protected RectF mBarRect = new RectF();
    private RectF mBarShadowRectBuffer = new RectF();
    protected BarDataProvider mChart;
    protected Paint mShadowPaint;

    public BarChartRenderer(BarDataProvider barDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mChart = barDataProvider;
        Paint paint = new Paint(1);
        this.mHighlightPaint = paint;
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        this.mHighlightPaint.setColor(Color.rgb(0, 0, 0));
        this.mHighlightPaint.setAlpha(120);
        Paint paint2 = new Paint(1);
        this.mShadowPaint = paint2;
        paint2.setStyle(style);
        Paint paint3 = new Paint(1);
        this.mBarBorderPaint = paint3;
        paint3.setStyle(Paint.Style.STROKE);
    }

    public void drawData(Canvas canvas) {
        BarData barData = this.mChart.getBarData();
        for (int i3 = 0; i3 < barData.getDataSetCount(); i3++) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(i3);
            if (iBarDataSet.isVisible()) {
                drawDataSet(canvas, iBarDataSet, i3);
            }
        }
    }

    public void drawDataSet(Canvas canvas, IBarDataSet iBarDataSet, int i3) {
        IBarDataSet iBarDataSet2 = iBarDataSet;
        int i4 = i3;
        Transformer transformer = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
        this.mBarBorderPaint.setColor(iBarDataSet.getBarBorderColor());
        this.mBarBorderPaint.setStrokeWidth(Utils.convertDpToPixel(iBarDataSet.getBarBorderWidth()));
        int i5 = 0;
        boolean z2 = true;
        boolean z3 = iBarDataSet.getBarBorderWidth() > 0.0f;
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        if (this.mChart.isDrawBarShadowEnabled()) {
            this.mShadowPaint.setColor(iBarDataSet.getBarShadowColor());
            float barWidth = this.mChart.getBarData().getBarWidth() / 2.0f;
            int min = Math.min((int) Math.ceil((double) (((float) iBarDataSet.getEntryCount()) * phaseX)), iBarDataSet.getEntryCount());
            for (int i6 = 0; i6 < min; i6++) {
                float x2 = ((BarEntry) iBarDataSet2.getEntryForIndex(i6)).getX();
                RectF rectF = this.mBarShadowRectBuffer;
                rectF.left = x2 - barWidth;
                rectF.right = x2 + barWidth;
                transformer.rectValueToPixel(rectF);
                if (!this.mViewPortHandler.isInBoundsLeft(this.mBarShadowRectBuffer.right)) {
                    Canvas canvas2 = canvas;
                } else if (!this.mViewPortHandler.isInBoundsRight(this.mBarShadowRectBuffer.left)) {
                    break;
                } else {
                    this.mBarShadowRectBuffer.top = this.mViewPortHandler.contentTop();
                    this.mBarShadowRectBuffer.bottom = this.mViewPortHandler.contentBottom();
                    canvas.drawRect(this.mBarShadowRectBuffer, this.mShadowPaint);
                }
            }
        }
        Canvas canvas3 = canvas;
        BarBuffer barBuffer = this.mBarBuffers[i4];
        barBuffer.setPhases(phaseX, phaseY);
        barBuffer.setDataSet(i4);
        barBuffer.setInverted(this.mChart.isInverted(iBarDataSet.getAxisDependency()));
        barBuffer.setBarWidth(this.mChart.getBarData().getBarWidth());
        barBuffer.feed(iBarDataSet2);
        transformer.pointValuesToPixel(barBuffer.buffer);
        if (iBarDataSet.getColors().size() != 1) {
            z2 = false;
        }
        if (z2) {
            this.mRenderPaint.setColor(iBarDataSet.getColor());
        }
        while (i5 < barBuffer.size()) {
            int i7 = i5 + 2;
            if (this.mViewPortHandler.isInBoundsLeft(barBuffer.buffer[i7])) {
                if (this.mViewPortHandler.isInBoundsRight(barBuffer.buffer[i5])) {
                    if (!z2) {
                        this.mRenderPaint.setColor(iBarDataSet2.getColor(i5 / 4));
                    }
                    if (iBarDataSet.getGradientColor() != null) {
                        GradientColor gradientColor = iBarDataSet.getGradientColor();
                        Paint paint = this.mRenderPaint;
                        float[] fArr = barBuffer.buffer;
                        float f2 = fArr[i5];
                        paint.setShader(new LinearGradient(f2, fArr[i5 + 3], f2, fArr[i5 + 1], gradientColor.getStartColor(), gradientColor.getEndColor(), Shader.TileMode.MIRROR));
                    }
                    if (iBarDataSet.getGradientColors() != null) {
                        Paint paint2 = this.mRenderPaint;
                        float[] fArr2 = barBuffer.buffer;
                        float f3 = fArr2[i5];
                        float f4 = fArr2[i5 + 3];
                        float f5 = fArr2[i5 + 1];
                        int i8 = i5 / 4;
                        paint2.setShader(new LinearGradient(f3, f4, f3, f5, iBarDataSet2.getGradientColor(i8).getStartColor(), iBarDataSet2.getGradientColor(i8).getEndColor(), Shader.TileMode.MIRROR));
                    }
                    float[] fArr3 = barBuffer.buffer;
                    int i9 = i5 + 1;
                    int i10 = i5 + 3;
                    canvas.drawRect(fArr3[i5], fArr3[i9], fArr3[i7], fArr3[i10], this.mRenderPaint);
                    if (z3) {
                        float[] fArr4 = barBuffer.buffer;
                        canvas.drawRect(fArr4[i5], fArr4[i9], fArr4[i7], fArr4[i10], this.mBarBorderPaint);
                    }
                } else {
                    return;
                }
            }
            i5 += 4;
            Canvas canvas4 = canvas;
        }
    }

    public void drawExtras(Canvas canvas) {
    }

    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        float f2;
        float f3;
        float y2;
        float f4;
        BarData barData = this.mChart.getBarData();
        for (Highlight highlight : highlightArr) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iBarDataSet != null && iBarDataSet.isHighlightEnabled()) {
                BarEntry barEntry = (BarEntry) iBarDataSet.getEntryForXValue(highlight.getX(), highlight.getY());
                if (isInBoundsX(barEntry, iBarDataSet)) {
                    Transformer transformer = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
                    this.mHighlightPaint.setColor(iBarDataSet.getHighLightColor());
                    this.mHighlightPaint.setAlpha(iBarDataSet.getHighLightAlpha());
                    if (highlight.getStackIndex() < 0 || !barEntry.isStacked()) {
                        y2 = barEntry.getY();
                        f4 = 0.0f;
                    } else if (this.mChart.isHighlightFullBarEnabled()) {
                        y2 = barEntry.getPositiveSum();
                        f4 = -barEntry.getNegativeSum();
                    } else {
                        Range range = barEntry.getRanges()[highlight.getStackIndex()];
                        f3 = range.from;
                        f2 = range.to;
                        prepareBarHighlight(barEntry.getX(), f3, f2, barData.getBarWidth() / 2.0f, transformer);
                        setHighlightDrawPos(highlight, this.mBarRect);
                        canvas.drawRect(this.mBarRect, this.mHighlightPaint);
                    }
                    f2 = f4;
                    f3 = y2;
                    prepareBarHighlight(barEntry.getX(), f3, f2, barData.getBarWidth() / 2.0f, transformer);
                    setHighlightDrawPos(highlight, this.mBarRect);
                    canvas.drawRect(this.mBarRect, this.mHighlightPaint);
                }
            }
        }
    }

    public void drawValue(Canvas canvas, String str, float f2, float f3, int i3) {
        this.mValuePaint.setColor(i3);
        canvas.drawText(str, f2, f3, this.mValuePaint);
    }

    public void drawValues(Canvas canvas) {
        boolean z2;
        float f2;
        float f3;
        List list;
        boolean z3;
        MPPointF mPPointF;
        int i3;
        float f4;
        boolean z4;
        Transformer transformer;
        float[] fArr;
        float f5;
        int i4;
        int i5;
        BarEntry barEntry;
        float[] fArr2;
        float f6;
        float f7;
        float f8;
        BarEntry barEntry2;
        List list2;
        int i6;
        ValueFormatter valueFormatter;
        MPPointF mPPointF2;
        float f9;
        BarEntry barEntry3;
        if (isDrawingValuesAllowed(this.mChart)) {
            List dataSets = this.mChart.getBarData().getDataSets();
            float convertDpToPixel = Utils.convertDpToPixel(4.5f);
            boolean isDrawValueAboveBarEnabled = this.mChart.isDrawValueAboveBarEnabled();
            int i7 = 0;
            while (i7 < this.mChart.getBarData().getDataSetCount()) {
                IBarDataSet iBarDataSet = (IBarDataSet) dataSets.get(i7);
                if (!shouldDrawValues(iBarDataSet)) {
                    list = dataSets;
                    f3 = f2;
                    z3 = z2;
                } else {
                    applyValueTextStyle(iBarDataSet);
                    boolean isInverted = this.mChart.isInverted(iBarDataSet.getAxisDependency());
                    float calcTextHeight = (float) Utils.calcTextHeight(this.mValuePaint, "8");
                    float f10 = z2 ? -f2 : calcTextHeight + f2;
                    float f11 = z2 ? calcTextHeight + f2 : -f2;
                    if (isInverted) {
                        f10 = (-f10) - calcTextHeight;
                        f11 = (-f11) - calcTextHeight;
                    }
                    float f12 = f10;
                    float f13 = f11;
                    BarBuffer barBuffer = this.mBarBuffers[i7];
                    float phaseY = this.mAnimator.getPhaseY();
                    ValueFormatter valueFormatter2 = iBarDataSet.getValueFormatter();
                    MPPointF instance = MPPointF.getInstance(iBarDataSet.getIconsOffset());
                    instance.f6574x = Utils.convertDpToPixel(instance.f6574x);
                    instance.f6575y = Utils.convertDpToPixel(instance.f6575y);
                    if (iBarDataSet.isStacked()) {
                        ValueFormatter valueFormatter3 = valueFormatter2;
                        list = dataSets;
                        mPPointF = instance;
                        Transformer transformer2 = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
                        int i8 = 0;
                        int i9 = 0;
                        while (true) {
                            if (((float) i8) >= this.mAnimator.getPhaseX() * ((float) iBarDataSet.getEntryCount())) {
                                break;
                            }
                            BarEntry barEntry4 = (BarEntry) iBarDataSet.getEntryForIndex(i8);
                            float[] yVals = barEntry4.getYVals();
                            float[] fArr3 = barBuffer.buffer;
                            float f14 = (fArr3[i9] + fArr3[i9 + 2]) / 2.0f;
                            int valueTextColor = iBarDataSet.getValueTextColor(i8);
                            if (yVals != null) {
                                BarEntry barEntry5 = barEntry4;
                                i3 = i8;
                                f4 = f2;
                                z4 = z2;
                                fArr = yVals;
                                transformer = transformer2;
                                float f15 = f14;
                                int length = fArr.length * 2;
                                float[] fArr4 = new float[length];
                                float f16 = -barEntry5.getNegativeSum();
                                float f17 = 0.0f;
                                int i10 = 0;
                                int i11 = 0;
                                while (i10 < length) {
                                    float f18 = fArr[i11];
                                    int i12 = (f18 > 0.0f ? 1 : (f18 == 0.0f ? 0 : -1));
                                    if (i12 == 0 && (f17 == 0.0f || f16 == 0.0f)) {
                                        float f19 = f16;
                                        f16 = f18;
                                        f7 = f19;
                                    } else if (i12 >= 0) {
                                        f17 += f18;
                                        f7 = f16;
                                        f16 = f17;
                                    } else {
                                        f7 = f16 - f18;
                                    }
                                    fArr4[i10 + 1] = f16 * phaseY;
                                    i10 += 2;
                                    i11++;
                                    f16 = f7;
                                }
                                transformer.pointValuesToPixel(fArr4);
                                int i13 = 0;
                                while (i13 < length) {
                                    float f20 = fArr[i13 / 2];
                                    float f21 = fArr4[i13 + 1] + (((f20 > 0.0f ? 1 : (f20 == 0.0f ? 0 : -1)) == 0 && (f16 > 0.0f ? 1 : (f16 == 0.0f ? 0 : -1)) == 0 && (f17 > 0.0f ? 1 : (f17 == 0.0f ? 0 : -1)) > 0) || (f20 > 0.0f ? 1 : (f20 == 0.0f ? 0 : -1)) < 0 ? f13 : f12);
                                    int i14 = i13;
                                    if (!this.mViewPortHandler.isInBoundsRight(f15)) {
                                        break;
                                    }
                                    if (!this.mViewPortHandler.isInBoundsY(f21) || !this.mViewPortHandler.isInBoundsLeft(f15)) {
                                        i4 = length;
                                        f5 = f15;
                                        i5 = i14;
                                        barEntry = barEntry5;
                                        fArr2 = fArr4;
                                    } else {
                                        if (iBarDataSet.isDrawValuesEnabled()) {
                                            BarEntry barEntry6 = barEntry5;
                                            f6 = f21;
                                            i5 = i14;
                                            barEntry = barEntry6;
                                            fArr2 = fArr4;
                                            i4 = length;
                                            f5 = f15;
                                            drawValue(canvas, valueFormatter3.getBarStackedLabel(f20, barEntry6), f15, f6, valueTextColor);
                                        } else {
                                            f6 = f21;
                                            i4 = length;
                                            f5 = f15;
                                            i5 = i14;
                                            barEntry = barEntry5;
                                            fArr2 = fArr4;
                                        }
                                        if (barEntry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                            Drawable icon = barEntry.getIcon();
                                            Utils.drawImage(canvas, icon, (int) (f5 + mPPointF.f6574x), (int) (f6 + mPPointF.f6575y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                        }
                                    }
                                    i13 = i5 + 2;
                                    fArr4 = fArr2;
                                    barEntry5 = barEntry;
                                    length = i4;
                                    f15 = f5;
                                }
                            } else if (!this.mViewPortHandler.isInBoundsRight(f14)) {
                                break;
                            } else {
                                float[] fArr5 = yVals;
                                int i15 = i9 + 1;
                                if (!this.mViewPortHandler.isInBoundsY(barBuffer.buffer[i15]) || !this.mViewPortHandler.isInBoundsLeft(f14)) {
                                    transformer2 = transformer2;
                                    z2 = z2;
                                    f2 = f2;
                                    i8 = i8;
                                } else {
                                    if (iBarDataSet.isDrawValuesEnabled()) {
                                        f8 = f14;
                                        f4 = f2;
                                        fArr = fArr5;
                                        barEntry2 = barEntry4;
                                        i3 = i8;
                                        z4 = z2;
                                        transformer = transformer2;
                                        drawValue(canvas, valueFormatter3.getBarLabel(barEntry4), f8, barBuffer.buffer[i15] + (barEntry4.getY() >= 0.0f ? f12 : f13), valueTextColor);
                                    } else {
                                        f8 = f14;
                                        i3 = i8;
                                        f4 = f2;
                                        z4 = z2;
                                        fArr = fArr5;
                                        barEntry2 = barEntry4;
                                        transformer = transformer2;
                                    }
                                    if (barEntry2.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                        Drawable icon2 = barEntry2.getIcon();
                                        Utils.drawImage(canvas, icon2, (int) (mPPointF.f6574x + f8), (int) (barBuffer.buffer[i15] + (barEntry2.getY() >= 0.0f ? f12 : f13) + mPPointF.f6575y), icon2.getIntrinsicWidth(), icon2.getIntrinsicHeight());
                                    }
                                }
                            }
                            i9 = fArr == null ? i9 + 4 : (fArr.length * 4) + i9;
                            i8 = i3 + 1;
                            transformer2 = transformer;
                            z2 = z4;
                            f2 = f4;
                        }
                    } else {
                        int i16 = 0;
                        while (true) {
                            if (((float) i16) >= this.mAnimator.getPhaseX() * ((float) barBuffer.buffer.length)) {
                                break;
                            }
                            float[] fArr6 = barBuffer.buffer;
                            float f22 = (fArr6[i16] + fArr6[i16 + 2]) / 2.0f;
                            if (!this.mViewPortHandler.isInBoundsRight(f22)) {
                                break;
                            }
                            int i17 = i16 + 1;
                            if (!this.mViewPortHandler.isInBoundsY(barBuffer.buffer[i17]) || !this.mViewPortHandler.isInBoundsLeft(f22)) {
                                i6 = i16;
                                valueFormatter = valueFormatter2;
                                list2 = dataSets;
                                mPPointF2 = instance;
                            } else {
                                int i18 = i16 / 4;
                                BarEntry barEntry7 = (BarEntry) iBarDataSet.getEntryForIndex(i18);
                                float y2 = barEntry7.getY();
                                if (iBarDataSet.isDrawValuesEnabled()) {
                                    String barLabel = valueFormatter2.getBarLabel(barEntry7);
                                    int i19 = (y2 > 0.0f ? 1 : (y2 == 0.0f ? 0 : -1));
                                    float[] fArr7 = barBuffer.buffer;
                                    barEntry3 = barEntry7;
                                    f9 = f22;
                                    String str = barLabel;
                                    i6 = i16;
                                    list2 = dataSets;
                                    mPPointF2 = instance;
                                    float f23 = i19 >= 0 ? fArr7[i17] + f12 : fArr7[i16 + 3] + f13;
                                    valueFormatter = valueFormatter2;
                                    drawValue(canvas, str, f9, f23, iBarDataSet.getValueTextColor(i18));
                                } else {
                                    barEntry3 = barEntry7;
                                    f9 = f22;
                                    i6 = i16;
                                    valueFormatter = valueFormatter2;
                                    list2 = dataSets;
                                    mPPointF2 = instance;
                                }
                                if (barEntry3.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                    Drawable icon3 = barEntry3.getIcon();
                                    Utils.drawImage(canvas, icon3, (int) (f9 + mPPointF2.f6574x), (int) ((y2 >= 0.0f ? barBuffer.buffer[i17] + f12 : barBuffer.buffer[i6 + 3] + f13) + mPPointF2.f6575y), icon3.getIntrinsicWidth(), icon3.getIntrinsicHeight());
                                }
                            }
                            i16 = i6 + 4;
                            instance = mPPointF2;
                            valueFormatter2 = valueFormatter;
                            dataSets = list2;
                        }
                        list = dataSets;
                        mPPointF = instance;
                    }
                    f3 = f2;
                    z3 = z2;
                    MPPointF.recycleInstance(mPPointF);
                }
                i7++;
                isDrawValueAboveBarEnabled = z3;
                dataSets = list;
                convertDpToPixel = f3;
            }
        }
    }

    public void initBuffers() {
        BarData barData = this.mChart.getBarData();
        this.mBarBuffers = new BarBuffer[barData.getDataSetCount()];
        for (int i3 = 0; i3 < this.mBarBuffers.length; i3++) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(i3);
            this.mBarBuffers[i3] = new BarBuffer(iBarDataSet.getEntryCount() * 4 * (iBarDataSet.isStacked() ? iBarDataSet.getStackSize() : 1), barData.getDataSetCount(), iBarDataSet.isStacked());
        }
    }

    public void prepareBarHighlight(float f2, float f3, float f4, float f5, Transformer transformer) {
        this.mBarRect.set(f2 - f5, f3, f2 + f5, f4);
        transformer.rectToPixelPhase(this.mBarRect, this.mAnimator.getPhaseY());
    }

    public void setHighlightDrawPos(Highlight highlight, RectF rectF) {
        highlight.setDraw(rectF.centerX(), rectF.top);
    }
}
