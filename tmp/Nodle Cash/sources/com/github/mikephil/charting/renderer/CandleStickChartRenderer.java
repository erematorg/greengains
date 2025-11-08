package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.CandleDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.renderer.BarLineScatterCandleBubbleRenderer;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class CandleStickChartRenderer extends LineScatterCandleRadarRenderer {
    private float[] mBodyBuffers = new float[4];
    protected CandleDataProvider mChart;
    private float[] mCloseBuffers = new float[4];
    private float[] mOpenBuffers = new float[4];
    private float[] mRangeBuffers = new float[4];
    private float[] mShadowBuffers = new float[8];

    public CandleStickChartRenderer(CandleDataProvider candleDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mChart = candleDataProvider;
    }

    public void drawData(Canvas canvas) {
        for (ICandleDataSet iCandleDataSet : this.mChart.getCandleData().getDataSets()) {
            if (iCandleDataSet.isVisible()) {
                drawDataSet(canvas, iCandleDataSet);
            }
        }
    }

    public void drawDataSet(Canvas canvas, ICandleDataSet iCandleDataSet) {
        ICandleDataSet iCandleDataSet2 = iCandleDataSet;
        Transformer transformer = this.mChart.getTransformer(iCandleDataSet.getAxisDependency());
        float phaseY = this.mAnimator.getPhaseY();
        float barSpace = iCandleDataSet.getBarSpace();
        boolean showCandleBar = iCandleDataSet.getShowCandleBar();
        this.mXBounds.set(this.mChart, iCandleDataSet2);
        this.mRenderPaint.setStrokeWidth(iCandleDataSet.getShadowWidth());
        int i3 = this.mXBounds.min;
        while (true) {
            BarLineScatterCandleBubbleRenderer.XBounds xBounds = this.mXBounds;
            if (i3 <= xBounds.range + xBounds.min) {
                CandleEntry candleEntry = (CandleEntry) iCandleDataSet2.getEntryForIndex(i3);
                if (candleEntry == null) {
                    Canvas canvas2 = canvas;
                } else {
                    float x2 = candleEntry.getX();
                    float open = candleEntry.getOpen();
                    float close = candleEntry.getClose();
                    float high = candleEntry.getHigh();
                    float low = candleEntry.getLow();
                    if (showCandleBar) {
                        float[] fArr = this.mShadowBuffers;
                        fArr[0] = x2;
                        fArr[2] = x2;
                        fArr[4] = x2;
                        fArr[6] = x2;
                        int i4 = (open > close ? 1 : (open == close ? 0 : -1));
                        if (i4 > 0) {
                            fArr[1] = high * phaseY;
                            fArr[3] = open * phaseY;
                            fArr[5] = low * phaseY;
                            fArr[7] = close * phaseY;
                        } else if (open < close) {
                            fArr[1] = high * phaseY;
                            fArr[3] = close * phaseY;
                            fArr[5] = low * phaseY;
                            fArr[7] = open * phaseY;
                        } else {
                            fArr[1] = high * phaseY;
                            float f2 = open * phaseY;
                            fArr[3] = f2;
                            fArr[5] = low * phaseY;
                            fArr[7] = f2;
                        }
                        transformer.pointValuesToPixel(fArr);
                        if (!iCandleDataSet.getShadowColorSameAsCandle()) {
                            this.mRenderPaint.setColor(iCandleDataSet.getShadowColor() == 1122867 ? iCandleDataSet2.getColor(i3) : iCandleDataSet.getShadowColor());
                        } else if (i4 > 0) {
                            this.mRenderPaint.setColor(iCandleDataSet.getDecreasingColor() == 1122867 ? iCandleDataSet2.getColor(i3) : iCandleDataSet.getDecreasingColor());
                        } else if (open < close) {
                            this.mRenderPaint.setColor(iCandleDataSet.getIncreasingColor() == 1122867 ? iCandleDataSet2.getColor(i3) : iCandleDataSet.getIncreasingColor());
                        } else {
                            this.mRenderPaint.setColor(iCandleDataSet.getNeutralColor() == 1122867 ? iCandleDataSet2.getColor(i3) : iCandleDataSet.getNeutralColor());
                        }
                        this.mRenderPaint.setStyle(Paint.Style.STROKE);
                        canvas.drawLines(this.mShadowBuffers, this.mRenderPaint);
                        float[] fArr2 = this.mBodyBuffers;
                        fArr2[0] = (x2 - 0.5f) + barSpace;
                        fArr2[1] = close * phaseY;
                        fArr2[2] = (x2 + 0.5f) - barSpace;
                        fArr2[3] = open * phaseY;
                        transformer.pointValuesToPixel(fArr2);
                        if (i4 > 0) {
                            if (iCandleDataSet.getDecreasingColor() == 1122867) {
                                this.mRenderPaint.setColor(iCandleDataSet2.getColor(i3));
                            } else {
                                this.mRenderPaint.setColor(iCandleDataSet.getDecreasingColor());
                            }
                            this.mRenderPaint.setStyle(iCandleDataSet.getDecreasingPaintStyle());
                            float[] fArr3 = this.mBodyBuffers;
                            canvas.drawRect(fArr3[0], fArr3[3], fArr3[2], fArr3[1], this.mRenderPaint);
                        } else if (open < close) {
                            if (iCandleDataSet.getIncreasingColor() == 1122867) {
                                this.mRenderPaint.setColor(iCandleDataSet2.getColor(i3));
                            } else {
                                this.mRenderPaint.setColor(iCandleDataSet.getIncreasingColor());
                            }
                            this.mRenderPaint.setStyle(iCandleDataSet.getIncreasingPaintStyle());
                            float[] fArr4 = this.mBodyBuffers;
                            canvas.drawRect(fArr4[0], fArr4[1], fArr4[2], fArr4[3], this.mRenderPaint);
                        } else {
                            if (iCandleDataSet.getNeutralColor() == 1122867) {
                                this.mRenderPaint.setColor(iCandleDataSet2.getColor(i3));
                            } else {
                                this.mRenderPaint.setColor(iCandleDataSet.getNeutralColor());
                            }
                            float[] fArr5 = this.mBodyBuffers;
                            canvas.drawLine(fArr5[0], fArr5[1], fArr5[2], fArr5[3], this.mRenderPaint);
                        }
                    } else {
                        Canvas canvas3 = canvas;
                        float[] fArr6 = this.mRangeBuffers;
                        fArr6[0] = x2;
                        fArr6[1] = high * phaseY;
                        fArr6[2] = x2;
                        fArr6[3] = low * phaseY;
                        float[] fArr7 = this.mOpenBuffers;
                        fArr7[0] = (x2 - 0.5f) + barSpace;
                        float f3 = open * phaseY;
                        fArr7[1] = f3;
                        fArr7[2] = x2;
                        fArr7[3] = f3;
                        float[] fArr8 = this.mCloseBuffers;
                        fArr8[0] = (0.5f + x2) - barSpace;
                        float f4 = close * phaseY;
                        fArr8[1] = f4;
                        fArr8[2] = x2;
                        fArr8[3] = f4;
                        transformer.pointValuesToPixel(fArr6);
                        transformer.pointValuesToPixel(this.mOpenBuffers);
                        transformer.pointValuesToPixel(this.mCloseBuffers);
                        this.mRenderPaint.setColor(open > close ? iCandleDataSet.getDecreasingColor() == 1122867 ? iCandleDataSet2.getColor(i3) : iCandleDataSet.getDecreasingColor() : open < close ? iCandleDataSet.getIncreasingColor() == 1122867 ? iCandleDataSet2.getColor(i3) : iCandleDataSet.getIncreasingColor() : iCandleDataSet.getNeutralColor() == 1122867 ? iCandleDataSet2.getColor(i3) : iCandleDataSet.getNeutralColor());
                        float[] fArr9 = this.mRangeBuffers;
                        Canvas canvas4 = canvas;
                        canvas4.drawLine(fArr9[0], fArr9[1], fArr9[2], fArr9[3], this.mRenderPaint);
                        float[] fArr10 = this.mOpenBuffers;
                        canvas4.drawLine(fArr10[0], fArr10[1], fArr10[2], fArr10[3], this.mRenderPaint);
                        float[] fArr11 = this.mCloseBuffers;
                        canvas4.drawLine(fArr11[0], fArr11[1], fArr11[2], fArr11[3], this.mRenderPaint);
                    }
                }
                i3++;
            } else {
                return;
            }
        }
    }

    public void drawExtras(Canvas canvas) {
    }

    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        CandleData candleData = this.mChart.getCandleData();
        for (Highlight highlight : highlightArr) {
            ICandleDataSet iCandleDataSet = (ICandleDataSet) candleData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iCandleDataSet != null && iCandleDataSet.isHighlightEnabled()) {
                CandleEntry candleEntry = (CandleEntry) iCandleDataSet.getEntryForXValue(highlight.getX(), highlight.getY());
                if (isInBoundsX(candleEntry, iCandleDataSet)) {
                    MPPointD pixelForValues = this.mChart.getTransformer(iCandleDataSet.getAxisDependency()).getPixelForValues(candleEntry.getX(), ((this.mAnimator.getPhaseY() * candleEntry.getHigh()) + (this.mAnimator.getPhaseY() * candleEntry.getLow())) / 2.0f);
                    highlight.setDraw((float) pixelForValues.f6572x, (float) pixelForValues.f6573y);
                    drawHighlightLines(canvas, (float) pixelForValues.f6572x, (float) pixelForValues.f6573y, iCandleDataSet);
                }
            }
        }
    }

    public void drawValue(Canvas canvas, String str, float f2, float f3, int i3) {
        this.mValuePaint.setColor(i3);
        canvas.drawText(str, f2, f3, this.mValuePaint);
    }

    public void drawValues(Canvas canvas) {
        ICandleDataSet iCandleDataSet;
        float f2;
        CandleEntry candleEntry;
        if (isDrawingValuesAllowed(this.mChart)) {
            List dataSets = this.mChart.getCandleData().getDataSets();
            for (int i3 = 0; i3 < dataSets.size(); i3++) {
                ICandleDataSet iCandleDataSet2 = (ICandleDataSet) dataSets.get(i3);
                if (shouldDrawValues(iCandleDataSet2) && iCandleDataSet2.getEntryCount() >= 1) {
                    applyValueTextStyle(iCandleDataSet2);
                    Transformer transformer = this.mChart.getTransformer(iCandleDataSet2.getAxisDependency());
                    this.mXBounds.set(this.mChart, iCandleDataSet2);
                    float phaseX = this.mAnimator.getPhaseX();
                    float phaseY = this.mAnimator.getPhaseY();
                    BarLineScatterCandleBubbleRenderer.XBounds xBounds = this.mXBounds;
                    float[] generateTransformedValuesCandle = transformer.generateTransformedValuesCandle(iCandleDataSet2, phaseX, phaseY, xBounds.min, xBounds.max);
                    float convertDpToPixel = Utils.convertDpToPixel(5.0f);
                    ValueFormatter valueFormatter = iCandleDataSet2.getValueFormatter();
                    MPPointF instance = MPPointF.getInstance(iCandleDataSet2.getIconsOffset());
                    instance.f6574x = Utils.convertDpToPixel(instance.f6574x);
                    instance.f6575y = Utils.convertDpToPixel(instance.f6575y);
                    int i4 = 0;
                    while (i4 < generateTransformedValuesCandle.length) {
                        float f3 = generateTransformedValuesCandle[i4];
                        float f4 = generateTransformedValuesCandle[i4 + 1];
                        if (!this.mViewPortHandler.isInBoundsRight(f3)) {
                            break;
                        }
                        if (!this.mViewPortHandler.isInBoundsLeft(f3) || !this.mViewPortHandler.isInBoundsY(f4)) {
                            iCandleDataSet = iCandleDataSet2;
                        } else {
                            int i5 = i4 / 2;
                            CandleEntry candleEntry2 = (CandleEntry) iCandleDataSet2.getEntryForIndex(this.mXBounds.min + i5);
                            if (iCandleDataSet2.isDrawValuesEnabled()) {
                                candleEntry = candleEntry2;
                                f2 = f4;
                                iCandleDataSet = iCandleDataSet2;
                                drawValue(canvas, valueFormatter.getCandleLabel(candleEntry2), f3, f4 - convertDpToPixel, iCandleDataSet2.getValueTextColor(i5));
                            } else {
                                candleEntry = candleEntry2;
                                f2 = f4;
                                iCandleDataSet = iCandleDataSet2;
                            }
                            if (candleEntry.getIcon() != null && iCandleDataSet.isDrawIconsEnabled()) {
                                Drawable icon = candleEntry.getIcon();
                                Utils.drawImage(canvas, icon, (int) (f3 + instance.f6574x), (int) (f2 + instance.f6575y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                            }
                        }
                        i4 += 2;
                        iCandleDataSet2 = iCandleDataSet;
                    }
                    MPPointF.recycleInstance(instance);
                }
            }
        }
    }

    public void initBuffers() {
    }
}
