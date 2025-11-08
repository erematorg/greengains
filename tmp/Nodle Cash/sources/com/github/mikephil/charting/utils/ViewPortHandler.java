package com.github.mikephil.charting.utils;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.View;

public class ViewPortHandler {
    protected Matrix mCenterViewPortMatrixBuffer = new Matrix();
    protected float mChartHeight = 0.0f;
    protected float mChartWidth = 0.0f;
    protected RectF mContentRect = new RectF();
    protected final Matrix mMatrixTouch = new Matrix();
    private float mMaxScaleX = Float.MAX_VALUE;
    private float mMaxScaleY = Float.MAX_VALUE;
    private float mMinScaleX = 1.0f;
    private float mMinScaleY = 1.0f;
    private float mScaleX = 1.0f;
    private float mScaleY = 1.0f;
    private float mTransOffsetX = 0.0f;
    private float mTransOffsetY = 0.0f;
    private float mTransX = 0.0f;
    private float mTransY = 0.0f;
    protected final float[] matrixBuffer = new float[9];
    protected float[] valsBufferForFitScreen = new float[9];

    public boolean canZoomInMoreX() {
        return this.mScaleX < this.mMaxScaleX;
    }

    public boolean canZoomInMoreY() {
        return this.mScaleY < this.mMaxScaleY;
    }

    public boolean canZoomOutMoreX() {
        return this.mScaleX > this.mMinScaleX;
    }

    public boolean canZoomOutMoreY() {
        return this.mScaleY > this.mMinScaleY;
    }

    public void centerViewPort(float[] fArr, View view) {
        Matrix matrix = this.mCenterViewPortMatrixBuffer;
        matrix.reset();
        matrix.set(this.mMatrixTouch);
        matrix.postTranslate(-(fArr[0] - offsetLeft()), -(fArr[1] - offsetTop()));
        refresh(matrix, view, true);
    }

    public float contentBottom() {
        return this.mContentRect.bottom;
    }

    public float contentHeight() {
        return this.mContentRect.height();
    }

    public float contentLeft() {
        return this.mContentRect.left;
    }

    public float contentRight() {
        return this.mContentRect.right;
    }

    public float contentTop() {
        return this.mContentRect.top;
    }

    public float contentWidth() {
        return this.mContentRect.width();
    }

    public Matrix fitScreen() {
        Matrix matrix = new Matrix();
        fitScreen(matrix);
        return matrix;
    }

    public float getChartHeight() {
        return this.mChartHeight;
    }

    public float getChartWidth() {
        return this.mChartWidth;
    }

    public MPPointF getContentCenter() {
        return MPPointF.getInstance(this.mContentRect.centerX(), this.mContentRect.centerY());
    }

    public RectF getContentRect() {
        return this.mContentRect;
    }

    public Matrix getMatrixTouch() {
        return this.mMatrixTouch;
    }

    public float getMaxScaleX() {
        return this.mMaxScaleX;
    }

    public float getMaxScaleY() {
        return this.mMaxScaleY;
    }

    public float getMinScaleX() {
        return this.mMinScaleX;
    }

    public float getMinScaleY() {
        return this.mMinScaleY;
    }

    public float getScaleX() {
        return this.mScaleX;
    }

    public float getScaleY() {
        return this.mScaleY;
    }

    public float getSmallestContentExtension() {
        return Math.min(this.mContentRect.width(), this.mContentRect.height());
    }

    public float getTransX() {
        return this.mTransX;
    }

    public float getTransY() {
        return this.mTransY;
    }

    public boolean hasChartDimens() {
        return this.mChartHeight > 0.0f && this.mChartWidth > 0.0f;
    }

    public boolean hasNoDragOffset() {
        return this.mTransOffsetX <= 0.0f && this.mTransOffsetY <= 0.0f;
    }

    public boolean isFullyZoomedOut() {
        return isFullyZoomedOutX() && isFullyZoomedOutY();
    }

    public boolean isFullyZoomedOutX() {
        float f2 = this.mScaleX;
        float f3 = this.mMinScaleX;
        return f2 <= f3 && f3 <= 1.0f;
    }

    public boolean isFullyZoomedOutY() {
        float f2 = this.mScaleY;
        float f3 = this.mMinScaleY;
        return f2 <= f3 && f3 <= 1.0f;
    }

    public boolean isInBounds(float f2, float f3) {
        return isInBoundsX(f2) && isInBoundsY(f3);
    }

    public boolean isInBoundsBottom(float f2) {
        return this.mContentRect.bottom >= ((float) ((int) (f2 * 100.0f))) / 100.0f;
    }

    public boolean isInBoundsLeft(float f2) {
        return this.mContentRect.left <= f2 + 1.0f;
    }

    public boolean isInBoundsRight(float f2) {
        return this.mContentRect.right >= (((float) ((int) (f2 * 100.0f))) / 100.0f) - 1.0f;
    }

    public boolean isInBoundsTop(float f2) {
        return this.mContentRect.top <= f2;
    }

    public boolean isInBoundsX(float f2) {
        return isInBoundsLeft(f2) && isInBoundsRight(f2);
    }

    public boolean isInBoundsY(float f2) {
        return isInBoundsTop(f2) && isInBoundsBottom(f2);
    }

    public void limitTransAndScale(Matrix matrix, RectF rectF) {
        float f2;
        float f3;
        matrix.getValues(this.matrixBuffer);
        float[] fArr = this.matrixBuffer;
        float f4 = fArr[2];
        float f5 = fArr[0];
        float f6 = fArr[5];
        float f7 = fArr[4];
        this.mScaleX = Math.min(Math.max(this.mMinScaleX, f5), this.mMaxScaleX);
        this.mScaleY = Math.min(Math.max(this.mMinScaleY, f7), this.mMaxScaleY);
        if (rectF != null) {
            f3 = rectF.width();
            f2 = rectF.height();
        } else {
            f3 = 0.0f;
            f2 = 0.0f;
        }
        this.mTransX = Math.min(Math.max(f4, ((this.mScaleX - 1.0f) * (-f3)) - this.mTransOffsetX), this.mTransOffsetX);
        float max = Math.max(Math.min(f6, ((this.mScaleY - 1.0f) * f2) + this.mTransOffsetY), -this.mTransOffsetY);
        this.mTransY = max;
        float[] fArr2 = this.matrixBuffer;
        fArr2[2] = this.mTransX;
        fArr2[0] = this.mScaleX;
        fArr2[5] = max;
        fArr2[4] = this.mScaleY;
        matrix.setValues(fArr2);
    }

    public float offsetBottom() {
        return this.mChartHeight - this.mContentRect.bottom;
    }

    public float offsetLeft() {
        return this.mContentRect.left;
    }

    public float offsetRight() {
        return this.mChartWidth - this.mContentRect.right;
    }

    public float offsetTop() {
        return this.mContentRect.top;
    }

    public Matrix refresh(Matrix matrix, View view, boolean z2) {
        this.mMatrixTouch.set(matrix);
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
        if (z2) {
            view.invalidate();
        }
        matrix.set(this.mMatrixTouch);
        return matrix;
    }

    public void resetZoom(Matrix matrix) {
        matrix.reset();
        matrix.set(this.mMatrixTouch);
        matrix.postScale(1.0f, 1.0f, 0.0f, 0.0f);
    }

    public void restrainViewPort(float f2, float f3, float f4, float f5) {
        this.mContentRect.set(f2, f3, this.mChartWidth - f4, this.mChartHeight - f5);
    }

    public void setChartDimens(float f2, float f3) {
        float offsetLeft = offsetLeft();
        float offsetTop = offsetTop();
        float offsetRight = offsetRight();
        float offsetBottom = offsetBottom();
        this.mChartHeight = f3;
        this.mChartWidth = f2;
        restrainViewPort(offsetLeft, offsetTop, offsetRight, offsetBottom);
    }

    public void setDragOffsetX(float f2) {
        this.mTransOffsetX = Utils.convertDpToPixel(f2);
    }

    public void setDragOffsetY(float f2) {
        this.mTransOffsetY = Utils.convertDpToPixel(f2);
    }

    public void setMaximumScaleX(float f2) {
        if (f2 == 0.0f) {
            f2 = Float.MAX_VALUE;
        }
        this.mMaxScaleX = f2;
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
    }

    public void setMaximumScaleY(float f2) {
        if (f2 == 0.0f) {
            f2 = Float.MAX_VALUE;
        }
        this.mMaxScaleY = f2;
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
    }

    public void setMinMaxScaleX(float f2, float f3) {
        if (f2 < 1.0f) {
            f2 = 1.0f;
        }
        if (f3 == 0.0f) {
            f3 = Float.MAX_VALUE;
        }
        this.mMinScaleX = f2;
        this.mMaxScaleX = f3;
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
    }

    public void setMinMaxScaleY(float f2, float f3) {
        if (f2 < 1.0f) {
            f2 = 1.0f;
        }
        if (f3 == 0.0f) {
            f3 = Float.MAX_VALUE;
        }
        this.mMinScaleY = f2;
        this.mMaxScaleY = f3;
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
    }

    public void setMinimumScaleX(float f2) {
        if (f2 < 1.0f) {
            f2 = 1.0f;
        }
        this.mMinScaleX = f2;
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
    }

    public void setMinimumScaleY(float f2) {
        if (f2 < 1.0f) {
            f2 = 1.0f;
        }
        this.mMinScaleY = f2;
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
    }

    public Matrix setZoom(float f2, float f3) {
        Matrix matrix = new Matrix();
        setZoom(f2, f3, matrix);
        return matrix;
    }

    public Matrix translate(float[] fArr) {
        Matrix matrix = new Matrix();
        translate(fArr, matrix);
        return matrix;
    }

    public Matrix zoom(float f2, float f3) {
        Matrix matrix = new Matrix();
        zoom(f2, f3, matrix);
        return matrix;
    }

    public Matrix zoomIn(float f2, float f3) {
        Matrix matrix = new Matrix();
        zoomIn(f2, f3, matrix);
        return matrix;
    }

    public Matrix zoomOut(float f2, float f3) {
        Matrix matrix = new Matrix();
        zoomOut(f2, f3, matrix);
        return matrix;
    }

    public void fitScreen(Matrix matrix) {
        this.mMinScaleX = 1.0f;
        this.mMinScaleY = 1.0f;
        matrix.set(this.mMatrixTouch);
        float[] fArr = this.valsBufferForFitScreen;
        for (int i3 = 0; i3 < 9; i3++) {
            fArr[i3] = 0.0f;
        }
        matrix.getValues(fArr);
        fArr[2] = 0.0f;
        fArr[5] = 0.0f;
        fArr[0] = 1.0f;
        fArr[4] = 1.0f;
        matrix.setValues(fArr);
    }

    public void setZoom(float f2, float f3, Matrix matrix) {
        matrix.reset();
        matrix.set(this.mMatrixTouch);
        matrix.setScale(f2, f3);
    }

    public void translate(float[] fArr, Matrix matrix) {
        matrix.reset();
        matrix.set(this.mMatrixTouch);
        matrix.postTranslate(-(fArr[0] - offsetLeft()), -(fArr[1] - offsetTop()));
    }

    public void zoom(float f2, float f3, Matrix matrix) {
        matrix.reset();
        matrix.set(this.mMatrixTouch);
        matrix.postScale(f2, f3);
    }

    public void zoomIn(float f2, float f3, Matrix matrix) {
        matrix.reset();
        matrix.set(this.mMatrixTouch);
        matrix.postScale(1.4f, 1.4f, f2, f3);
    }

    public void zoomOut(float f2, float f3, Matrix matrix) {
        matrix.reset();
        matrix.set(this.mMatrixTouch);
        matrix.postScale(0.7f, 0.7f, f2, f3);
    }

    public Matrix setZoom(float f2, float f3, float f4, float f5) {
        Matrix matrix = new Matrix();
        matrix.set(this.mMatrixTouch);
        matrix.setScale(f2, f3, f4, f5);
        return matrix;
    }

    public Matrix zoom(float f2, float f3, float f4, float f5) {
        Matrix matrix = new Matrix();
        zoom(f2, f3, f4, f5, matrix);
        return matrix;
    }

    public void zoom(float f2, float f3, float f4, float f5, Matrix matrix) {
        matrix.reset();
        matrix.set(this.mMatrixTouch);
        matrix.postScale(f2, f3, f4, f5);
    }
}
