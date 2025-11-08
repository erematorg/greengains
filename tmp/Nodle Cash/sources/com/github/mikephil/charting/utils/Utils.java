package com.github.mikephil.charting.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.ClassUtils;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.objectweb.asm.signature.SignatureVisitor;

public abstract class Utils {
    public static final double DEG2RAD = 0.017453292519943295d;
    public static final double DOUBLE_EPSILON = Double.longBitsToDouble(1);
    public static final float FDEG2RAD = 0.017453292f;
    public static final float FLOAT_EPSILON = Float.intBitsToFloat(1);
    private static final int[] POW_10 = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};
    private static Rect mCalcTextHeightRect = new Rect();
    private static Rect mCalcTextSizeRect = new Rect();
    private static ValueFormatter mDefaultValueFormatter = generateDefaultValueFormatter();
    private static Rect mDrawTextRectBuffer = new Rect();
    private static Rect mDrawableBoundsCache = new Rect();
    private static Paint.FontMetrics mFontMetrics = new Paint.FontMetrics();
    private static Paint.FontMetrics mFontMetricsBuffer = new Paint.FontMetrics();
    private static int mMaximumFlingVelocity = 8000;
    private static DisplayMetrics mMetrics = null;
    private static int mMinimumFlingVelocity = 50;

    public static int calcTextHeight(Paint paint, String str) {
        Rect rect = mCalcTextHeightRect;
        rect.set(0, 0, 0, 0);
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.height();
    }

    public static FSize calcTextSize(Paint paint, String str) {
        FSize instance = FSize.getInstance(0.0f, 0.0f);
        calcTextSize(paint, str, instance);
        return instance;
    }

    public static int calcTextWidth(Paint paint, String str) {
        return (int) paint.measureText(str);
    }

    public static float convertDpToPixel(float f2) {
        DisplayMetrics displayMetrics = mMetrics;
        if (displayMetrics != null) {
            return f2 * displayMetrics.density;
        }
        Log.e("MPChartLib-Utils", "Utils NOT INITIALIZED. You need to call Utils.init(...) at least once before calling Utils.convertDpToPixel(...). Otherwise conversion does not take place.");
        return f2;
    }

    public static int[] convertIntegers(List<Integer> list) {
        int[] iArr = new int[list.size()];
        copyIntegers(list, iArr);
        return iArr;
    }

    public static float convertPixelsToDp(float f2) {
        DisplayMetrics displayMetrics = mMetrics;
        if (displayMetrics != null) {
            return f2 / displayMetrics.density;
        }
        Log.e("MPChartLib-Utils", "Utils NOT INITIALIZED. You need to call Utils.init(...) at least once before calling Utils.convertPixelsToDp(...). Otherwise conversion does not take place.");
        return f2;
    }

    public static String[] convertStrings(List<String> list) {
        int size = list.size();
        String[] strArr = new String[size];
        for (int i3 = 0; i3 < size; i3++) {
            strArr[i3] = list.get(i3);
        }
        return strArr;
    }

    public static void copyIntegers(List<Integer> list, int[] iArr) {
        int length = iArr.length < list.size() ? iArr.length : list.size();
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = list.get(i3).intValue();
        }
    }

    public static void copyStrings(List<String> list, String[] strArr) {
        int length = strArr.length < list.size() ? strArr.length : list.size();
        for (int i3 = 0; i3 < length; i3++) {
            strArr[i3] = list.get(i3);
        }
    }

    public static void drawImage(Canvas canvas, Drawable drawable, int i3, int i4, int i5, int i6) {
        MPPointF instance = MPPointF.getInstance();
        instance.f6574x = (float) (i3 - (i5 / 2));
        instance.f6575y = (float) (i4 - (i6 / 2));
        drawable.copyBounds(mDrawableBoundsCache);
        Rect rect = mDrawableBoundsCache;
        int i7 = rect.left;
        int i8 = rect.top;
        drawable.setBounds(i7, i8, i7 + i5, i5 + i8);
        int save = canvas.save();
        canvas.translate(instance.f6574x, instance.f6575y);
        drawable.draw(canvas);
        canvas.restoreToCount(save);
    }

    public static void drawMultilineText(Canvas canvas, StaticLayout staticLayout, float f2, float f3, TextPaint textPaint, MPPointF mPPointF, float f4) {
        float fontMetrics = textPaint.getFontMetrics(mFontMetricsBuffer);
        float width = (float) staticLayout.getWidth();
        float lineCount = ((float) staticLayout.getLineCount()) * fontMetrics;
        float f5 = 0.0f - ((float) mDrawTextRectBuffer.left);
        float f6 = lineCount + 0.0f;
        Paint.Align textAlign = textPaint.getTextAlign();
        textPaint.setTextAlign(Paint.Align.LEFT);
        if (f4 != 0.0f) {
            float f7 = f5 - (width * 0.5f);
            float f8 = f6 - (lineCount * 0.5f);
            if (!(mPPointF.f6574x == 0.5f && mPPointF.f6575y == 0.5f)) {
                FSize sizeOfRotatedRectangleByDegrees = getSizeOfRotatedRectangleByDegrees(width, lineCount, f4);
                f2 -= (mPPointF.f6574x - 0.5f) * sizeOfRotatedRectangleByDegrees.width;
                f3 -= (mPPointF.f6575y - 0.5f) * sizeOfRotatedRectangleByDegrees.height;
                FSize.recycleInstance(sizeOfRotatedRectangleByDegrees);
            }
            canvas.save();
            canvas.translate(f2, f3);
            canvas.rotate(f4);
            canvas.translate(f7, f8);
            staticLayout.draw(canvas);
            canvas.restore();
        } else {
            float f9 = mPPointF.f6574x;
            if (!(f9 == 0.0f && mPPointF.f6575y == 0.0f)) {
                f5 -= width * f9;
                f6 -= lineCount * mPPointF.f6575y;
            }
            canvas.save();
            canvas.translate(f5 + f2, f6 + f3);
            staticLayout.draw(canvas);
            canvas.restore();
        }
        textPaint.setTextAlign(textAlign);
    }

    public static void drawXAxisValue(Canvas canvas, String str, float f2, float f3, Paint paint, MPPointF mPPointF, float f4) {
        float fontMetrics = paint.getFontMetrics(mFontMetricsBuffer);
        paint.getTextBounds(str, 0, str.length(), mDrawTextRectBuffer);
        float f5 = 0.0f - ((float) mDrawTextRectBuffer.left);
        float f6 = (-mFontMetricsBuffer.ascent) + 0.0f;
        Paint.Align textAlign = paint.getTextAlign();
        paint.setTextAlign(Paint.Align.LEFT);
        if (f4 != 0.0f) {
            float width = f5 - (((float) mDrawTextRectBuffer.width()) * 0.5f);
            float f7 = f6 - (fontMetrics * 0.5f);
            if (!(mPPointF.f6574x == 0.5f && mPPointF.f6575y == 0.5f)) {
                FSize sizeOfRotatedRectangleByDegrees = getSizeOfRotatedRectangleByDegrees((float) mDrawTextRectBuffer.width(), fontMetrics, f4);
                f2 -= (mPPointF.f6574x - 0.5f) * sizeOfRotatedRectangleByDegrees.width;
                f3 -= (mPPointF.f6575y - 0.5f) * sizeOfRotatedRectangleByDegrees.height;
                FSize.recycleInstance(sizeOfRotatedRectangleByDegrees);
            }
            canvas.save();
            canvas.translate(f2, f3);
            canvas.rotate(f4);
            canvas.drawText(str, width, f7, paint);
            canvas.restore();
        } else {
            if (!(mPPointF.f6574x == 0.0f && mPPointF.f6575y == 0.0f)) {
                f5 -= ((float) mDrawTextRectBuffer.width()) * mPPointF.f6574x;
                f6 -= fontMetrics * mPPointF.f6575y;
            }
            canvas.drawText(str, f5 + f2, f6 + f3, paint);
        }
        paint.setTextAlign(textAlign);
    }

    public static String formatNumber(float f2, int i3, boolean z2) {
        return formatNumber(f2, i3, z2, ClassUtils.PACKAGE_SEPARATOR_CHAR);
    }

    private static ValueFormatter generateDefaultValueFormatter() {
        return new DefaultValueFormatter(1);
    }

    public static int getDecimals(float f2) {
        float roundToNextSignificant = roundToNextSignificant((double) f2);
        if (Float.isInfinite(roundToNextSignificant)) {
            return 0;
        }
        return ((int) Math.ceil(-Math.log10((double) roundToNextSignificant))) + 2;
    }

    public static ValueFormatter getDefaultValueFormatter() {
        return mDefaultValueFormatter;
    }

    public static float getLineHeight(Paint paint) {
        return getLineHeight(paint, mFontMetrics);
    }

    public static float getLineSpacing(Paint paint) {
        return getLineSpacing(paint, mFontMetrics);
    }

    public static int getMaximumFlingVelocity() {
        return mMaximumFlingVelocity;
    }

    public static int getMinimumFlingVelocity() {
        return mMinimumFlingVelocity;
    }

    public static float getNormalizedAngle(float f2) {
        while (f2 < 0.0f) {
            f2 += 360.0f;
        }
        return f2 % 360.0f;
    }

    public static MPPointF getPosition(MPPointF mPPointF, float f2, float f3) {
        MPPointF instance = MPPointF.getInstance(0.0f, 0.0f);
        getPosition(mPPointF, f2, f3, instance);
        return instance;
    }

    public static int getSDKInt() {
        return Build.VERSION.SDK_INT;
    }

    public static FSize getSizeOfRotatedRectangleByDegrees(FSize fSize, float f2) {
        return getSizeOfRotatedRectangleByRadians(fSize.width, fSize.height, f2 * 0.017453292f);
    }

    public static FSize getSizeOfRotatedRectangleByRadians(FSize fSize, float f2) {
        return getSizeOfRotatedRectangleByRadians(fSize.width, fSize.height, f2);
    }

    public static void init(Context context) {
        if (context == null) {
            mMinimumFlingVelocity = ViewConfiguration.getMinimumFlingVelocity();
            mMaximumFlingVelocity = ViewConfiguration.getMaximumFlingVelocity();
            Log.e("MPChartLib-Utils", "Utils.init(...) PROVIDED CONTEXT OBJECT IS NULL");
            return;
        }
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        mMinimumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        mMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        mMetrics = context.getResources().getDisplayMetrics();
    }

    public static double nextUp(double d2) {
        if (d2 == Double.POSITIVE_INFINITY) {
            return d2;
        }
        double d3 = d2 + 0.0d;
        return Double.longBitsToDouble(Double.doubleToRawLongBits(d3) + (d3 >= 0.0d ? 1 : -1));
    }

    @SuppressLint({"NewApi"})
    public static void postInvalidateOnAnimation(View view) {
        view.postInvalidateOnAnimation();
    }

    public static float roundToNextSignificant(double d2) {
        if (Double.isInfinite(d2) || Double.isNaN(d2) || d2 == 0.0d) {
            return 0.0f;
        }
        float pow = (float) Math.pow(10.0d, (double) (1 - ((int) ((float) Math.ceil((double) ((float) Math.log10(d2 < 0.0d ? -d2 : d2)))))));
        return ((float) Math.round(d2 * ((double) pow))) / pow;
    }

    public static void velocityTrackerPointerUpCleanUpIfNecessary(MotionEvent motionEvent, VelocityTracker velocityTracker) {
        velocityTracker.computeCurrentVelocity(1000, (float) mMaximumFlingVelocity);
        int actionIndex = motionEvent.getActionIndex();
        int pointerId = motionEvent.getPointerId(actionIndex);
        float xVelocity = velocityTracker.getXVelocity(pointerId);
        float yVelocity = velocityTracker.getYVelocity(pointerId);
        int pointerCount = motionEvent.getPointerCount();
        for (int i3 = 0; i3 < pointerCount; i3++) {
            if (i3 != actionIndex) {
                int pointerId2 = motionEvent.getPointerId(i3);
                if ((velocityTracker.getYVelocity(pointerId2) * yVelocity) + (velocityTracker.getXVelocity(pointerId2) * xVelocity) < 0.0f) {
                    velocityTracker.clear();
                    return;
                }
            }
        }
    }

    public static String formatNumber(float f2, int i3, boolean z2, char c3) {
        boolean z3;
        float f3 = f2;
        char[] cArr = new char[35];
        if (f3 == 0.0f) {
            return SchemaSymbols.ATTVAL_FALSE_0;
        }
        int i4 = 0;
        boolean z4 = f3 < 1.0f && f3 > -1.0f;
        if (f3 < 0.0f) {
            f3 = -f3;
            z3 = true;
        } else {
            z3 = false;
        }
        int[] iArr = POW_10;
        int i5 = i3;
        int length = i5 > iArr.length ? iArr.length - 1 : i5;
        long round = (long) Math.round(f3 * ((float) iArr[length]));
        int i6 = 34;
        boolean z5 = false;
        while (true) {
            if (round == 0 && i4 >= length + 1) {
                break;
            }
            round /= 10;
            int i7 = i6 - 1;
            cArr[i6] = (char) (((int) (round % 10)) + 48);
            int i8 = i4 + 1;
            if (i8 == length) {
                i6 -= 2;
                cArr[i7] = AbstractJsonLexerKt.COMMA;
                i4 += 2;
                z5 = true;
            } else {
                if (z2 && round != 0 && i8 > length) {
                    if (z5) {
                        if ((i8 - length) % 4 == 0) {
                            i6 -= 2;
                            cArr[i7] = c3;
                        }
                    } else if ((i8 - length) % 4 == 3) {
                        i6 -= 2;
                        cArr[i7] = c3;
                    }
                    i4 += 2;
                }
                i4 = i8;
                i6 = i7;
            }
        }
        if (z4) {
            cArr[i6] = '0';
            i4++;
            i6--;
        }
        if (z3) {
            cArr[i6] = SignatureVisitor.SUPER;
            i4++;
        }
        int i9 = 35 - i4;
        return String.valueOf(cArr, i9, 35 - i9);
    }

    public static float getLineHeight(Paint paint, Paint.FontMetrics fontMetrics) {
        paint.getFontMetrics(fontMetrics);
        return fontMetrics.descent - fontMetrics.ascent;
    }

    public static float getLineSpacing(Paint paint, Paint.FontMetrics fontMetrics) {
        paint.getFontMetrics(fontMetrics);
        return (fontMetrics.ascent - fontMetrics.top) + fontMetrics.bottom;
    }

    public static FSize getSizeOfRotatedRectangleByDegrees(float f2, float f3, float f4) {
        return getSizeOfRotatedRectangleByRadians(f2, f3, f4 * 0.017453292f);
    }

    public static FSize getSizeOfRotatedRectangleByRadians(float f2, float f3, float f4) {
        double d2 = (double) f4;
        return FSize.getInstance(Math.abs(((float) Math.sin(d2)) * f3) + Math.abs(((float) Math.cos(d2)) * f2), Math.abs(f3 * ((float) Math.cos(d2))) + Math.abs(f2 * ((float) Math.sin(d2))));
    }

    public static void calcTextSize(Paint paint, String str, FSize fSize) {
        Rect rect = mCalcTextSizeRect;
        rect.set(0, 0, 0, 0);
        paint.getTextBounds(str, 0, str.length(), rect);
        fSize.width = (float) rect.width();
        fSize.height = (float) rect.height();
    }

    public static void getPosition(MPPointF mPPointF, float f2, float f3, MPPointF mPPointF2) {
        double d2 = (double) f2;
        double d3 = (double) f3;
        mPPointF2.f6574x = (float) ((Math.cos(Math.toRadians(d3)) * d2) + ((double) mPPointF.f6574x));
        mPPointF2.f6575y = (float) ((Math.sin(Math.toRadians(d3)) * d2) + ((double) mPPointF.f6575y));
    }

    @Deprecated
    public static void init(Resources resources) {
        mMetrics = resources.getDisplayMetrics();
        mMinimumFlingVelocity = ViewConfiguration.getMinimumFlingVelocity();
        mMaximumFlingVelocity = ViewConfiguration.getMaximumFlingVelocity();
    }

    public static void drawMultilineText(Canvas canvas, String str, float f2, float f3, TextPaint textPaint, FSize fSize, MPPointF mPPointF, float f4) {
        TextPaint textPaint2 = textPaint;
        drawMultilineText(canvas, new StaticLayout(str, 0, str.length(), textPaint2, (int) Math.max(Math.ceil((double) fSize.width), 1.0d), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false), f2, f3, textPaint2, mPPointF, f4);
    }
}
