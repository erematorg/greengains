package com.appsamurai.storyly.storylypresenter.product.productdetail;

import A0.C0201b;
import com.appsamurai.storyly.data.managers.product.STRCartEventResult;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

public final class e extends Lambda implements Function1<STRCartEventResult, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ b f5201a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ h f5202b;

    public static final class a extends Lambda implements Function0<Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ h f5203a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(h hVar) {
            super(0);
            this.f5203a = hVar;
        }

        public Object invoke() {
            this.f5203a.getMessageContainer().setVisibility(0);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public e(b bVar, h hVar) {
        super(1);
        this.f5201a = bVar;
        this.f5202b = hVar;
    }

    public final void a(@NotNull STRCartEventResult sTRCartEventResult) {
        Intrinsics.checkNotNullParameter(sTRCartEventResult, "result");
        this.f5201a.post(new C0201b(this.f5202b, sTRCartEventResult, 0));
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        a((STRCartEventResult) obj);
        return Unit.INSTANCE;
    }

    public static final void a(h hVar, STRCartEventResult sTRCartEventResult) {
        Intrinsics.checkNotNullParameter(hVar, "this$0");
        Intrinsics.checkNotNullParameter(sTRCartEventResult, "$result");
        hVar.getVariantStackView().setSelectionState(true);
        hVar.getScrollView().fullScroll(33);
        hVar.getBottomIndicator().setState$storyly_release(c.Default);
        hVar.getMessageText().setText(sTRCartEventResult.getMessage());
        if (hVar.getMessageContainer().getVisibility() != 0) {
            h.a(hVar, (Function0) new a(hVar));
        }
    }
}
