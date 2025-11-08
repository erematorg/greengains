package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.buffer.HorizontalBarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class HorizontalBarChartRenderer extends BarChartRenderer {
    private RectF mBarShadowRectBuffer = new RectF();

    public HorizontalBarChartRenderer(BarDataProvider barDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(barDataProvider, chartAnimator, viewPortHandler);
        this.mValuePaint.setTextAlign(Paint.Align.LEFT);
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
                rectF.top = x2 - barWidth;
                rectF.bottom = x2 + barWidth;
                transformer.rectValueToPixel(rectF);
                if (!this.mViewPortHandler.isInBoundsTop(this.mBarShadowRectBuffer.bottom)) {
                    Canvas canvas2 = canvas;
                } else if (!this.mViewPortHandler.isInBoundsBottom(this.mBarShadowRectBuffer.top)) {
                    break;
                } else {
                    this.mBarShadowRectBuffer.left = this.mViewPortHandler.contentLeft();
                    this.mBarShadowRectBuffer.right = this.mViewPortHandler.contentRight();
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
            int i7 = i5 + 3;
            if (this.mViewPortHandler.isInBoundsTop(barBuffer.buffer[i7])) {
                int i8 = i5 + 1;
                if (this.mViewPortHandler.isInBoundsBottom(barBuffer.buffer[i8])) {
                    if (!z2) {
                        this.mRenderPaint.setColor(iBarDataSet2.getColor(i5 / 4));
                    }
                    float[] fArr = barBuffer.buffer;
                    int i9 = i5 + 2;
                    canvas.drawRect(fArr[i5], fArr[i8], fArr[i9], fArr[i7], this.mRenderPaint);
                    if (z3) {
                        float[] fArr2 = barBuffer.buffer;
                        canvas.drawRect(fArr2[i5], fArr2[i8], fArr2[i9], fArr2[i7], this.mBarBorderPaint);
                    }
                }
                i5 += 4;
                Canvas canvas4 = canvas;
            } else {
                return;
            }
        }
    }

    public void drawValue(Canvas canvas, String str, float f2, float f3, int i3) {
        this.mValuePaint.setColor(i3);
        canvas.drawText(str, f2, f3, this.mValuePaint);
    }

    /* JADX WARNING: Removed duplicated region for block: B:142:0x03b2  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x03b5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void drawValues(android.graphics.Canvas r41) {
        /*
            r40 = this;
            r6 = r40
            com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider r0 = r6.mChart
            boolean r0 = r6.isDrawingValuesAllowed(r0)
            if (r0 == 0) goto L_0x03cb
            com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider r0 = r6.mChart
            com.github.mikephil.charting.data.BarData r0 = r0.getBarData()
            java.util.List r7 = r0.getDataSets()
            r0 = 1084227584(0x40a00000, float:5.0)
            float r8 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r0)
            com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider r0 = r6.mChart
            boolean r9 = r0.isDrawValueAboveBarEnabled()
            r11 = 0
        L_0x0021:
            com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider r0 = r6.mChart
            com.github.mikephil.charting.data.BarData r0 = r0.getBarData()
            int r0 = r0.getDataSetCount()
            if (r11 >= r0) goto L_0x03cb
            java.lang.Object r0 = r7.get(r11)
            r12 = r0
            com.github.mikephil.charting.interfaces.datasets.IBarDataSet r12 = (com.github.mikephil.charting.interfaces.datasets.IBarDataSet) r12
            boolean r0 = r6.shouldDrawValues(r12)
            if (r0 != 0) goto L_0x0040
            r20 = r7
            r23 = r11
            goto L_0x03c5
        L_0x0040:
            com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider r0 = r6.mChart
            com.github.mikephil.charting.components.YAxis$AxisDependency r1 = r12.getAxisDependency()
            boolean r13 = r0.isInverted(r1)
            r6.applyValueTextStyle(r12)
            android.graphics.Paint r0 = r6.mValuePaint
            java.lang.String r1 = "10"
            int r0 = com.github.mikephil.charting.utils.Utils.calcTextHeight(r0, r1)
            float r0 = (float) r0
            r14 = 1073741824(0x40000000, float:2.0)
            float r15 = r0 / r14
            com.github.mikephil.charting.formatter.ValueFormatter r5 = r12.getValueFormatter()
            com.github.mikephil.charting.buffer.BarBuffer[] r0 = r6.mBarBuffers
            r4 = r0[r11]
            com.github.mikephil.charting.animation.ChartAnimator r0 = r6.mAnimator
            float r16 = r0.getPhaseY()
            com.github.mikephil.charting.utils.MPPointF r0 = r12.getIconsOffset()
            com.github.mikephil.charting.utils.MPPointF r3 = com.github.mikephil.charting.utils.MPPointF.getInstance(r0)
            float r0 = r3.f6574x
            float r0 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r0)
            r3.f6574x = r0
            float r0 = r3.f6575y
            float r0 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r0)
            r3.f6575y = r0
            boolean r0 = r12.isStacked()
            r17 = 0
            if (r0 != 0) goto L_0x0194
            r2 = 0
        L_0x0089:
            float r0 = (float) r2
            float[] r1 = r4.buffer
            int r1 = r1.length
            float r1 = (float) r1
            com.github.mikephil.charting.animation.ChartAnimator r10 = r6.mAnimator
            float r10 = r10.getPhaseX()
            float r10 = r10 * r1
            int r0 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            if (r0 >= 0) goto L_0x00ae
            float[] r0 = r4.buffer
            int r1 = r2 + 1
            r10 = r0[r1]
            int r16 = r2 + 3
            r0 = r0[r16]
            float r0 = r0 + r10
            float r16 = r0 / r14
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r6.mViewPortHandler
            boolean r0 = r0.isInBoundsTop(r10)
            if (r0 != 0) goto L_0x00b5
        L_0x00ae:
            r20 = r7
            r23 = r11
            r7 = r3
            goto L_0x03c2
        L_0x00b5:
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r6.mViewPortHandler
            float[] r10 = r4.buffer
            r10 = r10[r2]
            boolean r0 = r0.isInBoundsX(r10)
            if (r0 != 0) goto L_0x00cc
        L_0x00c1:
            r25 = r2
            r14 = r4
            r20 = r7
            r23 = r11
            r7 = r3
            r11 = r5
            goto L_0x0187
        L_0x00cc:
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r6.mViewPortHandler
            float[] r10 = r4.buffer
            r1 = r10[r1]
            boolean r0 = r0.isInBoundsBottom(r1)
            if (r0 != 0) goto L_0x00d9
            goto L_0x00c1
        L_0x00d9:
            int r0 = r2 / 4
            com.github.mikephil.charting.data.Entry r0 = r12.getEntryForIndex(r0)
            r10 = r0
            com.github.mikephil.charting.data.BarEntry r10 = (com.github.mikephil.charting.data.BarEntry) r10
            float r18 = r10.getY()
            java.lang.String r1 = r5.getBarLabel(r10)
            android.graphics.Paint r0 = r6.mValuePaint
            int r0 = com.github.mikephil.charting.utils.Utils.calcTextWidth(r0, r1)
            float r0 = (float) r0
            if (r9 == 0) goto L_0x00f5
            r14 = r8
            goto L_0x00f8
        L_0x00f5:
            float r14 = r0 + r8
            float r14 = -r14
        L_0x00f8:
            r20 = r1
            if (r9 == 0) goto L_0x0100
            float r1 = r0 + r8
            float r1 = -r1
            goto L_0x0101
        L_0x0100:
            r1 = r8
        L_0x0101:
            if (r13 == 0) goto L_0x0107
            float r14 = -r14
            float r14 = r14 - r0
            float r1 = -r1
            float r1 = r1 - r0
        L_0x0107:
            r21 = r14
            r14 = r1
            boolean r0 = r12.isDrawValuesEnabled()
            if (r0 == 0) goto L_0x0143
            float[] r0 = r4.buffer
            int r1 = r2 + 2
            r0 = r0[r1]
            int r1 = (r18 > r17 ? 1 : (r18 == r17 ? 0 : -1))
            if (r1 < 0) goto L_0x011d
            r1 = r21
            goto L_0x011e
        L_0x011d:
            r1 = r14
        L_0x011e:
            float r22 = r0 + r1
            float r23 = r16 + r15
            int r0 = r2 / 2
            int r24 = r12.getValueTextColor(r0)
            r0 = r40
            r1 = r41
            r25 = r2
            r2 = r20
            r20 = r7
            r7 = r3
            r3 = r22
            r22 = r14
            r14 = r4
            r4 = r23
            r23 = r11
            r11 = r5
            r5 = r24
            r0.drawValue(r1, r2, r3, r4, r5)
            goto L_0x014e
        L_0x0143:
            r25 = r2
            r20 = r7
            r23 = r11
            r22 = r14
            r7 = r3
            r14 = r4
            r11 = r5
        L_0x014e:
            android.graphics.drawable.Drawable r0 = r10.getIcon()
            if (r0 == 0) goto L_0x0187
            boolean r0 = r12.isDrawIconsEnabled()
            if (r0 == 0) goto L_0x0187
            android.graphics.drawable.Drawable r27 = r10.getIcon()
            float[] r0 = r14.buffer
            int r2 = r25 + 2
            r0 = r0[r2]
            int r1 = (r18 > r17 ? 1 : (r18 == r17 ? 0 : -1))
            if (r1 < 0) goto L_0x0169
            goto L_0x016b
        L_0x0169:
            r21 = r22
        L_0x016b:
            float r0 = r0 + r21
            float r1 = r7.f6574x
            float r0 = r0 + r1
            float r1 = r7.f6575y
            float r1 = r16 + r1
            int r0 = (int) r0
            int r1 = (int) r1
            int r30 = r27.getIntrinsicWidth()
            int r31 = r27.getIntrinsicHeight()
            r26 = r41
            r28 = r0
            r29 = r1
            com.github.mikephil.charting.utils.Utils.drawImage(r26, r27, r28, r29, r30, r31)
        L_0x0187:
            int r2 = r25 + 4
            r3 = r7
            r5 = r11
            r4 = r14
            r7 = r20
            r11 = r23
            r14 = 1073741824(0x40000000, float:2.0)
            goto L_0x0089
        L_0x0194:
            r14 = r4
            r20 = r7
            r23 = r11
            r7 = r3
            r11 = r5
            com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider r0 = r6.mChart
            com.github.mikephil.charting.components.YAxis$AxisDependency r1 = r12.getAxisDependency()
            com.github.mikephil.charting.utils.Transformer r10 = r0.getTransformer(r1)
            r5 = 0
            r18 = 0
        L_0x01a8:
            float r0 = (float) r5
            int r1 = r12.getEntryCount()
            float r1 = (float) r1
            com.github.mikephil.charting.animation.ChartAnimator r2 = r6.mAnimator
            float r2 = r2.getPhaseX()
            float r2 = r2 * r1
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 >= 0) goto L_0x03c2
            com.github.mikephil.charting.data.Entry r0 = r12.getEntryForIndex(r5)
            r4 = r0
            com.github.mikephil.charting.data.BarEntry r4 = (com.github.mikephil.charting.data.BarEntry) r4
            int r21 = r12.getValueTextColor(r5)
            float[] r3 = r4.getYVals()
            if (r3 != 0) goto L_0x029a
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r6.mViewPortHandler
            float[] r1 = r14.buffer
            int r22 = r18 + 1
            r1 = r1[r22]
            boolean r0 = r0.isInBoundsTop(r1)
            if (r0 != 0) goto L_0x01da
            goto L_0x03c2
        L_0x01da:
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r6.mViewPortHandler
            float[] r1 = r14.buffer
            r1 = r1[r18]
            boolean r0 = r0.isInBoundsX(r1)
            if (r0 != 0) goto L_0x01e7
            goto L_0x01a8
        L_0x01e7:
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r6.mViewPortHandler
            float[] r1 = r14.buffer
            r1 = r1[r22]
            boolean r0 = r0.isInBoundsBottom(r1)
            if (r0 != 0) goto L_0x01f4
            goto L_0x01a8
        L_0x01f4:
            java.lang.String r2 = r11.getBarLabel(r4)
            android.graphics.Paint r0 = r6.mValuePaint
            int r0 = com.github.mikephil.charting.utils.Utils.calcTextWidth(r0, r2)
            float r0 = (float) r0
            if (r9 == 0) goto L_0x0203
            r1 = r8
            goto L_0x0206
        L_0x0203:
            float r1 = r0 + r8
            float r1 = -r1
        L_0x0206:
            r24 = r3
            if (r9 == 0) goto L_0x020e
            float r3 = r0 + r8
            float r3 = -r3
            goto L_0x020f
        L_0x020e:
            r3 = r8
        L_0x020f:
            if (r13 == 0) goto L_0x0215
            float r1 = -r1
            float r1 = r1 - r0
            float r3 = -r3
            float r3 = r3 - r0
        L_0x0215:
            r25 = r1
            r26 = r3
            boolean r0 = r12.isDrawValuesEnabled()
            if (r0 == 0) goto L_0x024e
            float[] r0 = r14.buffer
            int r1 = r18 + 2
            r0 = r0[r1]
            float r1 = r4.getY()
            int r1 = (r1 > r17 ? 1 : (r1 == r17 ? 0 : -1))
            if (r1 < 0) goto L_0x0230
            r1 = r25
            goto L_0x0232
        L_0x0230:
            r1 = r26
        L_0x0232:
            float r3 = r0 + r1
            float[] r0 = r14.buffer
            r0 = r0[r22]
            float r27 = r0 + r15
            r0 = r40
            r1 = r41
            r28 = r15
            r15 = r24
            r24 = r4
            r4 = r27
            r27 = r5
            r5 = r21
            r0.drawValue(r1, r2, r3, r4, r5)
            goto L_0x0256
        L_0x024e:
            r27 = r5
            r28 = r15
            r15 = r24
            r24 = r4
        L_0x0256:
            android.graphics.drawable.Drawable r0 = r24.getIcon()
            if (r0 == 0) goto L_0x0296
            boolean r0 = r12.isDrawIconsEnabled()
            if (r0 == 0) goto L_0x0296
            android.graphics.drawable.Drawable r30 = r24.getIcon()
            float[] r0 = r14.buffer
            int r1 = r18 + 2
            r0 = r0[r1]
            float r1 = r24.getY()
            int r1 = (r1 > r17 ? 1 : (r1 == r17 ? 0 : -1))
            if (r1 < 0) goto L_0x0275
            goto L_0x0277
        L_0x0275:
            r25 = r26
        L_0x0277:
            float r0 = r0 + r25
            float[] r1 = r14.buffer
            r1 = r1[r22]
            float r2 = r7.f6574x
            float r0 = r0 + r2
            float r2 = r7.f6575y
            float r1 = r1 + r2
            int r0 = (int) r0
            int r1 = (int) r1
            int r33 = r30.getIntrinsicWidth()
            int r34 = r30.getIntrinsicHeight()
            r29 = r41
            r31 = r0
            r32 = r1
            com.github.mikephil.charting.utils.Utils.drawImage(r29, r30, r31, r32, r33, r34)
        L_0x0296:
            r19 = 1073741824(0x40000000, float:2.0)
            goto L_0x03b0
        L_0x029a:
            r24 = r4
            r27 = r5
            r28 = r15
            r15 = r3
            int r0 = r15.length
            int r5 = r0 * 2
            float[] r4 = new float[r5]
            float r0 = r24.getNegativeSum()
            float r0 = -r0
            r22 = r0
            r25 = r17
            r0 = 0
            r1 = 0
        L_0x02b1:
            if (r0 >= r5) goto L_0x02de
            r2 = r15[r1]
            int r3 = (r2 > r17 ? 1 : (r2 == r17 ? 0 : -1))
            if (r3 != 0) goto L_0x02c8
            int r26 = (r25 > r17 ? 1 : (r25 == r17 ? 0 : -1))
            if (r26 == 0) goto L_0x02c1
            int r26 = (r22 > r17 ? 1 : (r22 == r17 ? 0 : -1))
            if (r26 != 0) goto L_0x02c8
        L_0x02c1:
            r39 = r22
            r22 = r2
            r2 = r39
            goto L_0x02d3
        L_0x02c8:
            if (r3 < 0) goto L_0x02d1
            float r25 = r25 + r2
            r2 = r22
            r22 = r25
            goto L_0x02d3
        L_0x02d1:
            float r2 = r22 - r2
        L_0x02d3:
            float r22 = r22 * r16
            r4[r0] = r22
            int r0 = r0 + 2
            int r1 = r1 + 1
            r22 = r2
            goto L_0x02b1
        L_0x02de:
            r10.pointValuesToPixel(r4)
            r3 = 0
        L_0x02e2:
            if (r3 >= r5) goto L_0x0296
            int r0 = r3 / 2
            r0 = r15[r0]
            r2 = r24
            java.lang.String r1 = r11.getBarStackedLabel(r0, r2)
            android.graphics.Paint r2 = r6.mValuePaint
            int r2 = com.github.mikephil.charting.utils.Utils.calcTextWidth(r2, r1)
            float r2 = (float) r2
            r26 = r1
            if (r9 == 0) goto L_0x02fb
            r1 = r8
            goto L_0x02fe
        L_0x02fb:
            float r1 = r2 + r8
            float r1 = -r1
        L_0x02fe:
            r29 = r5
            if (r9 == 0) goto L_0x0306
            float r5 = r2 + r8
            float r5 = -r5
            goto L_0x0307
        L_0x0306:
            r5 = r8
        L_0x0307:
            if (r13 == 0) goto L_0x030d
            float r1 = -r1
            float r1 = r1 - r2
            float r5 = -r5
            float r5 = r5 - r2
        L_0x030d:
            int r2 = (r0 > r17 ? 1 : (r0 == r17 ? 0 : -1))
            if (r2 != 0) goto L_0x0319
            int r2 = (r22 > r17 ? 1 : (r22 == r17 ? 0 : -1))
            if (r2 != 0) goto L_0x0319
            int r2 = (r25 > r17 ? 1 : (r25 == r17 ? 0 : -1))
            if (r2 > 0) goto L_0x031d
        L_0x0319:
            int r0 = (r0 > r17 ? 1 : (r0 == r17 ? 0 : -1))
            if (r0 >= 0) goto L_0x031f
        L_0x031d:
            r0 = 1
            goto L_0x0320
        L_0x031f:
            r0 = 0
        L_0x0320:
            r2 = r4[r3]
            if (r0 == 0) goto L_0x0325
            r1 = r5
        L_0x0325:
            float r5 = r2 + r1
            float[] r0 = r14.buffer
            int r1 = r18 + 1
            r1 = r0[r1]
            int r2 = r18 + 3
            r0 = r0[r2]
            float r1 = r1 + r0
            r19 = 1073741824(0x40000000, float:2.0)
            float r2 = r1 / r19
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r6.mViewPortHandler
            boolean r0 = r0.isInBoundsTop(r2)
            if (r0 != 0) goto L_0x0340
            goto L_0x03b0
        L_0x0340:
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r6.mViewPortHandler
            boolean r0 = r0.isInBoundsX(r5)
            if (r0 != 0) goto L_0x034d
        L_0x0348:
            r26 = r3
            r32 = r4
            goto L_0x03a8
        L_0x034d:
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r6.mViewPortHandler
            boolean r0 = r0.isInBoundsBottom(r2)
            if (r0 != 0) goto L_0x0356
            goto L_0x0348
        L_0x0356:
            boolean r0 = r12.isDrawValuesEnabled()
            if (r0 == 0) goto L_0x0375
            float r30 = r2 + r28
            r0 = r40
            r1 = r41
            r31 = r2
            r2 = r26
            r26 = r3
            r3 = r5
            r32 = r4
            r4 = r30
            r30 = r5
            r5 = r21
            r0.drawValue(r1, r2, r3, r4, r5)
            goto L_0x037d
        L_0x0375:
            r31 = r2
            r26 = r3
            r32 = r4
            r30 = r5
        L_0x037d:
            android.graphics.drawable.Drawable r0 = r24.getIcon()
            if (r0 == 0) goto L_0x03a8
            boolean r0 = r12.isDrawIconsEnabled()
            if (r0 == 0) goto L_0x03a8
            android.graphics.drawable.Drawable r34 = r24.getIcon()
            float r0 = r7.f6574x
            float r5 = r30 + r0
            int r0 = (int) r5
            float r1 = r7.f6575y
            float r2 = r31 + r1
            int r1 = (int) r2
            int r37 = r34.getIntrinsicWidth()
            int r38 = r34.getIntrinsicHeight()
            r33 = r41
            r35 = r0
            r36 = r1
            com.github.mikephil.charting.utils.Utils.drawImage(r33, r34, r35, r36, r37, r38)
        L_0x03a8:
            int r3 = r26 + 2
            r5 = r29
            r4 = r32
            goto L_0x02e2
        L_0x03b0:
            if (r15 != 0) goto L_0x03b5
            int r18 = r18 + 4
            goto L_0x03bc
        L_0x03b5:
            int r0 = r15.length
            int r0 = r0 * 4
            int r0 = r0 + r18
            r18 = r0
        L_0x03bc:
            int r5 = r27 + 1
            r15 = r28
            goto L_0x01a8
        L_0x03c2:
            com.github.mikephil.charting.utils.MPPointF.recycleInstance(r7)
        L_0x03c5:
            int r11 = r23 + 1
            r7 = r20
            goto L_0x0021
        L_0x03cb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.renderer.HorizontalBarChartRenderer.drawValues(android.graphics.Canvas):void");
    }

    public void initBuffers() {
        BarData barData = this.mChart.getBarData();
        this.mBarBuffers = new HorizontalBarBuffer[barData.getDataSetCount()];
        for (int i3 = 0; i3 < this.mBarBuffers.length; i3++) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(i3);
            this.mBarBuffers[i3] = new HorizontalBarBuffer(iBarDataSet.getEntryCount() * 4 * (iBarDataSet.isStacked() ? iBarDataSet.getStackSize() : 1), barData.getDataSetCount(), iBarDataSet.isStacked());
        }
    }

    public boolean isDrawingValuesAllowed(ChartInterface chartInterface) {
        return ((float) chartInterface.getData().getEntryCount()) < this.mViewPortHandler.getScaleY() * ((float) chartInterface.getMaxVisibleCount());
    }

    public void prepareBarHighlight(float f2, float f3, float f4, float f5, Transformer transformer) {
        this.mBarRect.set(f3, f2 - f5, f4, f2 + f5);
        transformer.rectToPixelPhaseHorizontal(this.mBarRect, this.mAnimator.getPhaseY());
    }

    public void setHighlightDrawPos(Highlight highlight, RectF rectF) {
        highlight.setDraw(rectF.centerY(), rectF.right);
    }
}
