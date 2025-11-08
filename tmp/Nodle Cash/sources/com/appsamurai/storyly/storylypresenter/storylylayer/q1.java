package com.appsamurai.storyly.storylypresenter.storylylayer;

import B0.a;
import G0.m;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public final class q1 {
    public static final void a(View view, Function1<? super Point, Unit> function1) {
        Point point = new Point();
        view.setOnTouchListener(new m(view, point));
        view.setOnClickListener(new a(function1, point, 4));
    }

    public static final boolean a(View view, Point point, View view2, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(view, "$this_setOnClickListenerWithPoint");
        Intrinsics.checkNotNullParameter(point, "$coordinates");
        if (motionEvent.getAction() != 0) {
            return false;
        }
        PointF pointF = new PointF(view.getX() + ((float) (view.getWidth() / 2)), view.getY() + ((float) (view.getHeight() / 2)));
        double radians = Math.toRadians((double) view.getRotation());
        float x2 = motionEvent.getX();
        float y2 = motionEvent.getY();
        double x3 = (double) ((view.getX() + x2) - pointF.x);
        double y3 = (double) ((view.getY() + y2) - pointF.y);
        double cos = (Math.cos(radians) * x3) - (Math.sin(radians) * y3);
        double cos2 = Math.cos(radians) * y3;
        PointF pointF2 = new PointF((float) (((double) pointF.x) + cos), (float) (((double) pointF.y) + cos2 + (Math.sin(radians) * x3)));
        point.set((int) pointF2.x, (int) pointF2.y);
        return false;
    }

    public static final void a(Function1 function1, Point point, View view) {
        Intrinsics.checkNotNullParameter(function1, "$action");
        Intrinsics.checkNotNullParameter(point, "$coordinates");
        function1.invoke(point);
    }
}
