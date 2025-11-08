package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.view.View;
import androidx.core.view.ViewGroupKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class y0 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ x f6238a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public y0(x xVar) {
        super(0);
        this.f6238a = xVar;
    }

    public Object invoke() {
        for (View next : ViewGroupKt.getChildren(this.f6238a.f6203b)) {
            if (next instanceof u1) {
                ((u1) next).g();
            }
        }
        return Unit.INSTANCE;
    }
}
