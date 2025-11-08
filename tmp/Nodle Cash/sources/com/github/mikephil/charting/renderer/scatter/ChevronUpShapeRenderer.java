package com.github.mikephil.charting.renderer.scatter;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class ChevronUpShapeRenderer implements IShapeRenderer {
    public void renderShape(Canvas canvas, IScatterDataSet iScatterDataSet, ViewPortHandler viewPortHandler, float f2, float f3, Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.convertDpToPixel(1.0f));
        float scatterShapeSize = (iScatterDataSet.getScatterShapeSize() / 2.0f) * 2.0f;
        Canvas canvas2 = canvas;
        float f4 = f2;
        float f5 = f3 - scatterShapeSize;
        float f6 = f3;
        Paint paint2 = paint;
        canvas2.drawLine(f4, f5, f2 + scatterShapeSize, f6, paint2);
        canvas2.drawLine(f4, f5, f2 - scatterShapeSize, f6, paint2);
    }
}
