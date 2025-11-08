package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.ScatterDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.renderer.BarLineScatterCandleBubbleRenderer;
import com.github.mikephil.charting.renderer.scatter.IShapeRenderer;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

public class ScatterChartRenderer extends LineScatterCandleRadarRenderer {
    protected ScatterDataProvider mChart;
    float[] mPixelBuffer = new float[2];

    public ScatterChartRenderer(ScatterDataProvider scatterDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mChart = scatterDataProvider;
    }

    public void drawData(Canvas canvas) {
        for (IScatterDataSet iScatterDataSet : this.mChart.getScatterData().getDataSets()) {
            if (iScatterDataSet.isVisible()) {
                drawDataSet(canvas, iScatterDataSet);
            }
        }
    }

    public void drawDataSet(Canvas canvas, IScatterDataSet iScatterDataSet) {
        int i3;
        IScatterDataSet iScatterDataSet2 = iScatterDataSet;
        if (iScatterDataSet.getEntryCount() >= 1) {
            ViewPortHandler viewPortHandler = this.mViewPortHandler;
            Transformer transformer = this.mChart.getTransformer(iScatterDataSet.getAxisDependency());
            float phaseY = this.mAnimator.getPhaseY();
            IShapeRenderer shapeRenderer = iScatterDataSet.getShapeRenderer();
            if (shapeRenderer == null) {
                Log.i("MISSING", "There's no IShapeRenderer specified for ScatterDataSet");
                return;
            }
            int min = (int) Math.min(Math.ceil((double) (this.mAnimator.getPhaseX() * ((float) iScatterDataSet.getEntryCount()))), (double) ((float) iScatterDataSet.getEntryCount()));
            int i4 = 0;
            while (i4 < min) {
                Entry entryForIndex = iScatterDataSet2.getEntryForIndex(i4);
                this.mPixelBuffer[0] = entryForIndex.getX();
                this.mPixelBuffer[1] = entryForIndex.getY() * phaseY;
                transformer.pointValuesToPixel(this.mPixelBuffer);
                if (viewPortHandler.isInBoundsRight(this.mPixelBuffer[0])) {
                    if (!viewPortHandler.isInBoundsLeft(this.mPixelBuffer[0]) || !viewPortHandler.isInBoundsY(this.mPixelBuffer[1])) {
                        i3 = i4;
                    } else {
                        this.mRenderPaint.setColor(iScatterDataSet2.getColor(i4 / 2));
                        ViewPortHandler viewPortHandler2 = this.mViewPortHandler;
                        float[] fArr = this.mPixelBuffer;
                        i3 = i4;
                        shapeRenderer.renderShape(canvas, iScatterDataSet, viewPortHandler2, fArr[0], fArr[1], this.mRenderPaint);
                    }
                    i4 = i3 + 1;
                } else {
                    return;
                }
            }
        }
    }

    public void drawExtras(Canvas canvas) {
    }

    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        ScatterData scatterData = this.mChart.getScatterData();
        for (Highlight highlight : highlightArr) {
            IScatterDataSet iScatterDataSet = (IScatterDataSet) scatterData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iScatterDataSet != null && iScatterDataSet.isHighlightEnabled()) {
                Entry entryForXValue = iScatterDataSet.getEntryForXValue(highlight.getX(), highlight.getY());
                if (isInBoundsX(entryForXValue, iScatterDataSet)) {
                    MPPointD pixelForValues = this.mChart.getTransformer(iScatterDataSet.getAxisDependency()).getPixelForValues(entryForXValue.getX(), this.mAnimator.getPhaseY() * entryForXValue.getY());
                    highlight.setDraw((float) pixelForValues.f6572x, (float) pixelForValues.f6573y);
                    drawHighlightLines(canvas, (float) pixelForValues.f6572x, (float) pixelForValues.f6573y, iScatterDataSet);
                }
            }
        }
    }

    public void drawValue(Canvas canvas, String str, float f2, float f3, int i3) {
        this.mValuePaint.setColor(i3);
        canvas.drawText(str, f2, f3, this.mValuePaint);
    }

    public void drawValues(Canvas canvas) {
        IScatterDataSet iScatterDataSet;
        Entry entry;
        if (isDrawingValuesAllowed(this.mChart)) {
            List dataSets = this.mChart.getScatterData().getDataSets();
            for (int i3 = 0; i3 < this.mChart.getScatterData().getDataSetCount(); i3++) {
                IScatterDataSet iScatterDataSet2 = (IScatterDataSet) dataSets.get(i3);
                if (shouldDrawValues(iScatterDataSet2) && iScatterDataSet2.getEntryCount() >= 1) {
                    applyValueTextStyle(iScatterDataSet2);
                    this.mXBounds.set(this.mChart, iScatterDataSet2);
                    Transformer transformer = this.mChart.getTransformer(iScatterDataSet2.getAxisDependency());
                    float phaseX = this.mAnimator.getPhaseX();
                    float phaseY = this.mAnimator.getPhaseY();
                    BarLineScatterCandleBubbleRenderer.XBounds xBounds = this.mXBounds;
                    float[] generateTransformedValuesScatter = transformer.generateTransformedValuesScatter(iScatterDataSet2, phaseX, phaseY, xBounds.min, xBounds.max);
                    float convertDpToPixel = Utils.convertDpToPixel(iScatterDataSet2.getScatterShapeSize());
                    ValueFormatter valueFormatter = iScatterDataSet2.getValueFormatter();
                    MPPointF instance = MPPointF.getInstance(iScatterDataSet2.getIconsOffset());
                    instance.f6574x = Utils.convertDpToPixel(instance.f6574x);
                    instance.f6575y = Utils.convertDpToPixel(instance.f6575y);
                    int i4 = 0;
                    while (i4 < generateTransformedValuesScatter.length && this.mViewPortHandler.isInBoundsRight(generateTransformedValuesScatter[i4])) {
                        if (this.mViewPortHandler.isInBoundsLeft(generateTransformedValuesScatter[i4])) {
                            int i5 = i4 + 1;
                            if (this.mViewPortHandler.isInBoundsY(generateTransformedValuesScatter[i5])) {
                                int i6 = i4 / 2;
                                Entry entryForIndex = iScatterDataSet2.getEntryForIndex(this.mXBounds.min + i6);
                                if (iScatterDataSet2.isDrawValuesEnabled()) {
                                    String pointLabel = valueFormatter.getPointLabel(entryForIndex);
                                    float f2 = generateTransformedValuesScatter[i4];
                                    entry = entryForIndex;
                                    float f3 = generateTransformedValuesScatter[i5] - convertDpToPixel;
                                    iScatterDataSet = iScatterDataSet2;
                                    drawValue(canvas, pointLabel, f2, f3, iScatterDataSet2.getValueTextColor(i6 + this.mXBounds.min));
                                } else {
                                    entry = entryForIndex;
                                    iScatterDataSet = iScatterDataSet2;
                                }
                                if (entry.getIcon() != null && iScatterDataSet.isDrawIconsEnabled()) {
                                    Drawable icon = entry.getIcon();
                                    Utils.drawImage(canvas, icon, (int) (generateTransformedValuesScatter[i4] + instance.f6574x), (int) (generateTransformedValuesScatter[i5] + instance.f6575y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                }
                                i4 += 2;
                                iScatterDataSet2 = iScatterDataSet;
                            }
                        }
                        iScatterDataSet = iScatterDataSet2;
                        i4 += 2;
                        iScatterDataSet2 = iScatterDataSet;
                    }
                    MPPointF.recycleInstance(instance);
                }
            }
        }
    }

    public void initBuffers() {
    }
}
