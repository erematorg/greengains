package com.github.mikephil.charting.data.filter;

import android.annotation.TargetApi;
import java.util.Arrays;

public class Approximator {

    public class Line {
        private float dx;
        private float dy;
        private float exsy;
        private float length;
        private float[] points;
        private float sxey;

        public Line(float f2, float f3, float f4, float f5) {
            float f6 = f2 - f4;
            this.dx = f6;
            float f7 = f3 - f5;
            this.dy = f7;
            this.sxey = f2 * f5;
            this.exsy = f4 * f3;
            this.length = (float) Math.sqrt((double) ((f7 * f7) + (f6 * f6)));
            this.points = new float[]{f2, f3, f4, f5};
        }

        public float distance(float f2, float f3) {
            return Math.abs((((this.dy * f2) - (this.dx * f3)) + this.sxey) - this.exsy) / this.length;
        }

        public float[] getPoints() {
            return this.points;
        }
    }

    public float[] concat(float[]... fArr) {
        int i3 = 0;
        for (float[] length : fArr) {
            i3 += length.length;
        }
        float[] fArr2 = new float[i3];
        int i4 = 0;
        for (float[] fArr3 : fArr) {
            for (float f2 : fArr[r2]) {
                fArr2[i4] = f2;
                i4++;
            }
        }
        return fArr2;
    }

    @TargetApi(9)
    public float[] reduceWithDouglasPeucker(float[] fArr, float f2) {
        Line line = new Line(fArr[0], fArr[1], fArr[fArr.length - 2], fArr[fArr.length - 1]);
        float f3 = 0.0f;
        int i3 = 0;
        for (int i4 = 2; i4 < fArr.length - 2; i4 += 2) {
            float distance = line.distance(fArr[i4], fArr[i4 + 1]);
            if (distance > f3) {
                i3 = i4;
                f3 = distance;
            }
        }
        if (f3 <= f2) {
            return line.getPoints();
        }
        float[] reduceWithDouglasPeucker = reduceWithDouglasPeucker(Arrays.copyOfRange(fArr, 0, i3 + 2), f2);
        float[] reduceWithDouglasPeucker2 = reduceWithDouglasPeucker(Arrays.copyOfRange(fArr, i3, fArr.length), f2);
        return concat(reduceWithDouglasPeucker, Arrays.copyOfRange(reduceWithDouglasPeucker2, 2, reduceWithDouglasPeucker2.length));
    }
}
