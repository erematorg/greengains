package com.github.mikephil.charting.renderer.scatter;

import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.compose.animation.core.a;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class SquareShapeRenderer implements IShapeRenderer {
    public void renderShape(Canvas canvas, IScatterDataSet iScatterDataSet, ViewPortHandler viewPortHandler, float f2, float f3, Paint paint) {
        Paint paint2 = paint;
        float scatterShapeSize = iScatterDataSet.getScatterShapeSize();
        float f4 = scatterShapeSize / 2.0f;
        float convertDpToPixel = Utils.convertDpToPixel(iScatterDataSet.getScatterShapeHoleRadius());
        float a2 = a.a(convertDpToPixel, 2.0f, scatterShapeSize, 2.0f);
        float f5 = a2 / 2.0f;
        int scatterShapeHoleColor = iScatterDataSet.getScatterShapeHoleColor();
        if (((double) scatterShapeSize) > 0.0d) {
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setStrokeWidth(a2);
            float f6 = f2 - convertDpToPixel;
            float f7 = f3 - convertDpToPixel;
            float f8 = f2 + convertDpToPixel;
            float f9 = f3 + convertDpToPixel;
            canvas.drawRect(f6 - f5, f7 - f5, f8 + f5, f9 + f5, paint);
            if (scatterShapeHoleColor != 1122867) {
                paint2.setStyle(Paint.Style.FILL);
                paint2.setColor(scatterShapeHoleColor);
                canvas.drawRect(f6, f7, f8, f9, paint);
                return;
            }
            return;
        }
        paint2.setStyle(Paint.Style.FILL);
        canvas.drawRect(f2 - f4, f3 - f4, f2 + f4, f3 + f4, paint);
    }
}
