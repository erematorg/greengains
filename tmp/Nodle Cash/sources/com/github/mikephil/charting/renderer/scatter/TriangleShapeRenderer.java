package com.github.mikephil.charting.renderer.scatter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class TriangleShapeRenderer implements IShapeRenderer {
    protected Path mTrianglePathBuffer = new Path();

    public void renderShape(Canvas canvas, IScatterDataSet iScatterDataSet, ViewPortHandler viewPortHandler, float f2, float f3, Paint paint) {
        float scatterShapeSize = iScatterDataSet.getScatterShapeSize();
        float f4 = scatterShapeSize / 2.0f;
        float convertDpToPixel = (scatterShapeSize - (Utils.convertDpToPixel(iScatterDataSet.getScatterShapeHoleRadius()) * 2.0f)) / 2.0f;
        int scatterShapeHoleColor = iScatterDataSet.getScatterShapeHoleColor();
        paint.setStyle(Paint.Style.FILL);
        Path path = this.mTrianglePathBuffer;
        path.reset();
        float f5 = f3 - f4;
        path.moveTo(f2, f5);
        float f6 = f2 + f4;
        float f7 = f3 + f4;
        path.lineTo(f6, f7);
        float f8 = f2 - f4;
        path.lineTo(f8, f7);
        int i3 = (((double) scatterShapeSize) > 0.0d ? 1 : (((double) scatterShapeSize) == 0.0d ? 0 : -1));
        if (i3 > 0) {
            path.lineTo(f2, f5);
            float f9 = f8 + convertDpToPixel;
            float f10 = f7 - convertDpToPixel;
            path.moveTo(f9, f10);
            path.lineTo(f6 - convertDpToPixel, f10);
            path.lineTo(f2, f5 + convertDpToPixel);
            path.lineTo(f9, f10);
        }
        path.close();
        canvas.drawPath(path, paint);
        path.reset();
        if (i3 > 0 && scatterShapeHoleColor != 1122867) {
            paint.setColor(scatterShapeHoleColor);
            path.moveTo(f2, f5 + convertDpToPixel);
            float f11 = f7 - convertDpToPixel;
            path.lineTo(f6 - convertDpToPixel, f11);
            path.lineTo(f8 + convertDpToPixel, f11);
            path.close();
            canvas.drawPath(path, paint);
            path.reset();
        }
    }
}
