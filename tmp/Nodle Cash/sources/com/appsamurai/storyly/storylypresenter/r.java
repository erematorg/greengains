package com.appsamurai.storyly.storylypresenter;

import android.view.View;
import com.appsamurai.storyly.util.l;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

public final /* synthetic */ class r extends FunctionReferenceImpl implements Function0<Boolean> {
    public r(Object obj) {
        super(0, obj, l.class, "isLayoutDirectionLtr", "isLayoutDirectionLtr(Landroid/view/View;)Z", 1);
    }

    public Object invoke() {
        return Boolean.valueOf(l.a((View) this.receiver));
    }
}
