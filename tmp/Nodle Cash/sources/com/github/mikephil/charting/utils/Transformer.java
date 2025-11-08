package com.github.mikephil.charting.utils;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import java.util.List;

public class Transformer {
    private Matrix mMBuffer1 = new Matrix();
    private Matrix mMBuffer2 = new Matrix();
    protected Matrix mMatrixOffset = new Matrix();
    protected Matrix mMatrixValueToPx = new Matrix();
    protected Matrix mPixelToValueMatrixBuffer = new Matrix();
    protected ViewPortHandler mViewPortHandler;
    float[] ptsBuffer = new float[2];
    protected float[] valuePointsForGenerateTransformedValuesBubble = new float[1];
    protected float[] valuePointsForGenerateTransformedValuesCandle = new float[1];
    protected float[] valuePointsForGenerateTransformedValuesLine = new float[1];
    protected float[] valuePointsForGenerateTransformedValuesScatter = new float[1];

    public Transformer(ViewPortHandler viewPortHandler) {
        this.mViewPortHandler = viewPortHandler;
    }

    public float[] generateTransformedValuesBubble(IBubbleDataSet iBubbleDataSet, float f2, int i3, int i4) {
        int i5 = ((i4 - i3) + 1) * 2;
        if (this.valuePointsForGenerateTransformedValuesBubble.length != i5) {
            this.valuePointsForGenerateTransformedValuesBubble = new float[i5];
        }
        float[] fArr = this.valuePointsForGenerateTransformedValuesBubble;
        for (int i6 = 0; i6 < i5; i6 += 2) {
            Entry entryForIndex = iBubbleDataSet.getEntryForIndex((i6 / 2) + i3);
            if (entryForIndex != null) {
                fArr[i6] = entryForIndex.getX();
                fArr[i6 + 1] = entryForIndex.getY() * f2;
            } else {
                fArr[i6] = 0.0f;
                fArr[i6 + 1] = 0.0f;
            }
        }
        getValueToPixelMatrix().mapPoints(fArr);
        return fArr;
    }

    public float[] generateTransformedValuesCandle(ICandleDataSet iCandleDataSet, float f2, float f3, int i3, int i4) {
        int i5 = ((int) ((((float) (i4 - i3)) * f2) + 1.0f)) * 2;
        if (this.valuePointsForGenerateTransformedValuesCandle.length != i5) {
            this.valuePointsForGenerateTransformedValuesCandle = new float[i5];
        }
        float[] fArr = this.valuePointsForGenerateTransformedValuesCandle;
        for (int i6 = 0; i6 < i5; i6 += 2) {
            CandleEntry candleEntry = (CandleEntry) iCandleDataSet.getEntryForIndex((i6 / 2) + i3);
            if (candleEntry != null) {
                fArr[i6] = candleEntry.getX();
                fArr[i6 + 1] = candleEntry.getHigh() * f3;
            } else {
                fArr[i6] = 0.0f;
                fArr[i6 + 1] = 0.0f;
            }
        }
        getValueToPixelMatrix().mapPoints(fArr);
        return fArr;
    }

    public float[] generateTransformedValuesLine(ILineDataSet iLineDataSet, float f2, float f3, int i3, int i4) {
        int i5 = (((int) (((float) (i4 - i3)) * f2)) + 1) * 2;
        if (this.valuePointsForGenerateTransformedValuesLine.length != i5) {
            this.valuePointsForGenerateTransformedValuesLine = new float[i5];
        }
        float[] fArr = this.valuePointsForGenerateTransformedValuesLine;
        for (int i6 = 0; i6 < i5; i6 += 2) {
            Entry entryForIndex = iLineDataSet.getEntryForIndex((i6 / 2) + i3);
            if (entryForIndex != null) {
                fArr[i6] = entryForIndex.getX();
                fArr[i6 + 1] = entryForIndex.getY() * f3;
            } else {
                fArr[i6] = 0.0f;
                fArr[i6 + 1] = 0.0f;
            }
        }
        getValueToPixelMatrix().mapPoints(fArr);
        return fArr;
    }

    public float[] generateTransformedValuesScatter(IScatterDataSet iScatterDataSet, float f2, float f3, int i3, int i4) {
        int i5 = ((int) ((((float) (i4 - i3)) * f2) + 1.0f)) * 2;
        if (this.valuePointsForGenerateTransformedValuesScatter.length != i5) {
            this.valuePointsForGenerateTransformedValuesScatter = new float[i5];
        }
        float[] fArr = this.valuePointsForGenerateTransformedValuesScatter;
        for (int i6 = 0; i6 < i5; i6 += 2) {
            Entry entryForIndex = iScatterDataSet.getEntryForIndex((i6 / 2) + i3);
            if (entryForIndex != null) {
                fArr[i6] = entryForIndex.getX();
                fArr[i6 + 1] = entryForIndex.getY() * f3;
            } else {
                fArr[i6] = 0.0f;
                fArr[i6 + 1] = 0.0f;
            }
        }
        getValueToPixelMatrix().mapPoints(fArr);
        return fArr;
    }

    public Matrix getOffsetMatrix() {
        return this.mMatrixOffset;
    }

    public MPPointD getPixelForValues(float f2, float f3) {
        float[] fArr = this.ptsBuffer;
        fArr[0] = f2;
        fArr[1] = f3;
        pointValuesToPixel(fArr);
        float[] fArr2 = this.ptsBuffer;
        return MPPointD.getInstance((double) fArr2[0], (double) fArr2[1]);
    }

    public Matrix getPixelToValueMatrix() {
        getValueToPixelMatrix().invert(this.mMBuffer2);
        return this.mMBuffer2;
    }

    public Matrix getValueMatrix() {
        return this.mMatrixValueToPx;
    }

    public Matrix getValueToPixelMatrix() {
        this.mMBuffer1.set(this.mMatrixValueToPx);
        this.mMBuffer1.postConcat(this.mViewPortHandler.mMatrixTouch);
        this.mMBuffer1.postConcat(this.mMatrixOffset);
        return this.mMBuffer1;
    }

    public MPPointD getValuesByTouchPoint(float f2, float f3) {
        MPPointD instance = MPPointD.getInstance(0.0d, 0.0d);
        getValuesByTouchPoint(f2, f3, instance);
        return instance;
    }

    public void pathValueToPixel(Path path) {
        path.transform(this.mMatrixValueToPx);
        path.transform(this.mViewPortHandler.getMatrixTouch());
        path.transform(this.mMatrixOffset);
    }

    public void pathValuesToPixel(List<Path> list) {
        for (int i3 = 0; i3 < list.size(); i3++) {
            pathValueToPixel(list.get(i3));
        }
    }

    public void pixelsToValue(float[] fArr) {
        Matrix matrix = this.mPixelToValueMatrixBuffer;
        matrix.reset();
        this.mMatrixOffset.invert(matrix);
        matrix.mapPoints(fArr);
        this.mViewPortHandler.getMatrixTouch().invert(matrix);
        matrix.mapPoints(fArr);
        this.mMatrixValueToPx.invert(matrix);
        matrix.mapPoints(fArr);
    }

    public void pointValuesToPixel(float[] fArr) {
        this.mMatrixValueToPx.mapPoints(fArr);
        this.mViewPortHandler.getMatrixTouch().mapPoints(fArr);
        this.mMatrixOffset.mapPoints(fArr);
    }

    public void prepareMatrixOffset(boolean z2) {
        this.mMatrixOffset.reset();
        if (!z2) {
            this.mMatrixOffset.postTranslate(this.mViewPortHandler.offsetLeft(), this.mViewPortHandler.getChartHeight() - this.mViewPortHandler.offsetBottom());
            return;
        }
        this.mMatrixOffset.setTranslate(this.mViewPortHandler.offsetLeft(), -this.mViewPortHandler.offsetTop());
        this.mMatrixOffset.postScale(1.0f, -1.0f);
    }

    public void prepareMatrixValuePx(float f2, float f3, float f4, float f5) {
        float contentWidth = this.mViewPortHandler.contentWidth() / f3;
        float contentHeight = this.mViewPortHandler.contentHeight() / f4;
        if (Float.isInfinite(contentWidth)) {
            contentWidth = 0.0f;
        }
        if (Float.isInfinite(contentHeight)) {
            contentHeight = 0.0f;
        }
        this.mMatrixValueToPx.reset();
        this.mMatrixValueToPx.postTranslate(-f2, -f5);
        this.mMatrixValueToPx.postScale(contentWidth, -contentHeight);
    }

    public void rectToPixelPhase(RectF rectF, float f2) {
        rectF.top *= f2;
        rectF.bottom *= f2;
        this.mMatrixValueToPx.mapRect(rectF);
        this.mViewPortHandler.getMatrixTouch().mapRect(rectF);
        this.mMatrixOffset.mapRect(rectF);
    }

    public void rectToPixelPhaseHorizontal(RectF rectF, float f2) {
        rectF.left *= f2;
        rectF.right *= f2;
        this.mMatrixValueToPx.mapRect(rectF);
        this.mViewPortHandler.getMatrixTouch().mapRect(rectF);
        this.mMatrixOffset.mapRect(rectF);
    }

    public void rectValueToPixel(RectF rectF) {
        this.mMatrixValueToPx.mapRect(rectF);
        this.mViewPortHandler.getMatrixTouch().mapRect(rectF);
        this.mMatrixOffset.mapRect(rectF);
    }

    public void rectValueToPixelHorizontal(RectF rectF) {
        this.mMatrixValueToPx.mapRect(rectF);
        this.mViewPortHandler.getMatrixTouch().mapRect(rectF);
        this.mMatrixOffset.mapRect(rectF);
    }

    public void rectValuesToPixel(List<RectF> list) {
        Matrix valueToPixelMatrix = getValueToPixelMatrix();
        for (int i3 = 0; i3 < list.size(); i3++) {
            valueToPixelMatrix.mapRect(list.get(i3));
        }
    }

    public void getValuesByTouchPoint(float f2, float f3, MPPointD mPPointD) {
        float[] fArr = this.ptsBuffer;
        fArr[0] = f2;
        fArr[1] = f3;
        pixelsToValue(fArr);
        float[] fArr2 = this.ptsBuffer;
        mPPointD.f6572x = (double) fArr2[0];
        mPPointD.f6573y = (double) fArr2[1];
    }

    public void rectValueToPixelHorizontal(RectF rectF, float f2) {
        rectF.left *= f2;
        rectF.right *= f2;
        this.mMatrixValueToPx.mapRect(rectF);
        this.mViewPortHandler.getMatrixTouch().mapRect(rectF);
        this.mMatrixOffset.mapRect(rectF);
    }
}
