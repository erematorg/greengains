package com.appsamurai.storyly.storylypresenter;

import com.appsamurai.storyly.storylypresenter.storylyfooter.d;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class q0 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ o f5300a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public q0(o oVar) {
        super(0);
        this.f5300a = oVar;
    }

    public Object invoke() {
        this.f5300a.h();
        d j2 = this.f5300a.getStorylyReportView();
        ((BottomSheetDialog) j2.f5444o.getValue()).show();
        Function0<Unit> function0 = j2.f5432c;
        if (function0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onOptionsSheetPresent");
            function0 = null;
        }
        function0.invoke();
        j2.f5430a.setBackgroundColor(0);
        j2.f5430a.setVisibility(0);
        return Unit.INSTANCE;
    }
}
