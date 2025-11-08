package com.appsamurai.storyly.storylypresenter.cart.sheet;

import com.appsamurai.storyly.data.managers.product.STRCartEventResult;
import com.appsamurai.storyly.storylypresenter.cart.list.f;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

public final class e extends Lambda implements Function1<STRCartEventResult, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ f f4900a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ d f4901b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Function0<Unit> f4902c;

    public static final class a extends Lambda implements Function0<Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ d f4903a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(d dVar) {
            super(0);
            this.f4903a = dVar;
        }

        public Object invoke() {
            this.f4903a.getMessageContainer().setVisibility(0);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public e(f fVar, d dVar, Function0<Unit> function0) {
        super(1);
        this.f4900a = fVar;
        this.f4901b = dVar;
        this.f4902c = function0;
    }

    public final void a(@NotNull STRCartEventResult sTRCartEventResult) {
        Intrinsics.checkNotNullParameter(sTRCartEventResult, "result");
        this.f4900a.post(new T1.a(this.f4901b, (Function0) this.f4902c, sTRCartEventResult));
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        a((STRCartEventResult) obj);
        return Unit.INSTANCE;
    }

    public static final void a(d dVar, Function0 function0, STRCartEventResult sTRCartEventResult) {
        Intrinsics.checkNotNullParameter(dVar, "this$0");
        Intrinsics.checkNotNullParameter(function0, "$onComplete");
        Intrinsics.checkNotNullParameter(sTRCartEventResult, "$result");
        dVar.getBottomIndicator().setState$storyly_release(a.Default);
        function0.invoke();
        dVar.getScrollView().fullScroll(33);
        dVar.getMessageText().setText(sTRCartEventResult.getMessage());
        if (dVar.getMessageContainer().getVisibility() != 0) {
            dVar.a((Function0<Unit>) new a(dVar));
        }
    }
}
