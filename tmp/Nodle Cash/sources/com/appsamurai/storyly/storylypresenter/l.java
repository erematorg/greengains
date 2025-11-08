package com.appsamurai.storyly.storylypresenter;

import androidx.core.view.ViewCompat;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

public final class l extends Lambda implements Function1<Boolean, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ d f5028a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public l(d dVar) {
        super(1);
        this.f5028a = dVar;
    }

    public Object invoke(Object obj) {
        if (((Boolean) obj).booleanValue()) {
            this.f5028a.getBackgroundLayout().setBackgroundColor(0);
        } else {
            this.f5028a.getBackgroundLayout().setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        }
        return Unit.INSTANCE;
    }
}
