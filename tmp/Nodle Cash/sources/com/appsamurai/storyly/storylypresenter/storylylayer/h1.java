package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.ViewGroup;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class h1 extends Lambda implements Function1<List<o1>, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Canvas f5758a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public h1(Canvas canvas) {
        super(1);
        this.f5758a = canvas;
    }

    public Object invoke(Object obj) {
        List<o1> list = (List) obj;
        Intrinsics.checkNotNullParameter(list, "it");
        Canvas canvas = this.f5758a;
        for (o1 o1Var : list) {
            Bitmap currentBitmap$storyly_release = o1Var.getCurrentBitmap$storyly_release();
            if (currentBitmap$storyly_release != null) {
                ViewGroup.LayoutParams layoutParams = o1Var.getLayoutParams();
                if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                    layoutParams = null;
                }
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                int i3 = 0;
                float f2 = (float) (marginLayoutParams != null ? marginLayoutParams.leftMargin : 0);
                ViewGroup.LayoutParams layoutParams2 = o1Var.getLayoutParams();
                if (!(layoutParams2 instanceof ViewGroup.MarginLayoutParams)) {
                    layoutParams2 = null;
                }
                ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams2;
                if (marginLayoutParams2 != null) {
                    i3 = marginLayoutParams2.topMargin;
                }
                canvas.drawBitmap(currentBitmap$storyly_release, f2, (float) i3, (Paint) null);
            }
        }
        return Unit.INSTANCE;
    }
}
