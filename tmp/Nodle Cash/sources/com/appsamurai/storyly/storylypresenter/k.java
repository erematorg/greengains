package com.appsamurai.storyly.storylypresenter;

import androidx.core.view.ViewCompat;
import com.appsamurai.storyly.util.f;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

public final class k extends Lambda implements Function1<Float, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ d f5026a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public k(d dVar) {
        super(1);
        this.f5026a = dVar;
    }

    public Object invoke(Object obj) {
        float floatValue = ((Number) obj).floatValue();
        this.f5026a.getBackgroundLayout().setBackgroundColor(f.a((int) ViewCompat.MEASURED_STATE_MASK, 255 - ((int) ((floatValue / ((float) this.f5026a.getHeight())) * ((float) 255)))));
        return Unit.INSTANCE;
    }
}
