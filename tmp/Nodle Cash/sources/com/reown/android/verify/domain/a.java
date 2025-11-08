package com.reown.android.verify.domain;

import com.reown.android.internal.common.model.WCRequest;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class a implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7431a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ ResolveAttestationIdUseCase f7432b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ WCRequest f7433c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Function1 f7434d;

    public /* synthetic */ a(ResolveAttestationIdUseCase resolveAttestationIdUseCase, WCRequest wCRequest, Function1 function1, int i3) {
        this.f7431a = i3;
        this.f7432b = resolveAttestationIdUseCase;
        this.f7433c = wCRequest;
        this.f7434d = function1;
    }

    public final Object invoke(Object obj) {
        switch (this.f7431a) {
            case 0:
                return ResolveAttestationIdUseCase.resolveVerifyV1$lambda$7(this.f7432b, this.f7433c, this.f7434d, (VerifyResult) obj);
            case 1:
                return ResolveAttestationIdUseCase.resolveVerifyV1$lambda$9(this.f7432b, this.f7433c, this.f7434d, (Throwable) obj);
            case 2:
                return ResolveAttestationIdUseCase.resolveVerifyV2$lambda$3(this.f7432b, this.f7433c, this.f7434d, (VerifyResult) obj);
            default:
                return ResolveAttestationIdUseCase.resolveVerifyV2$lambda$5(this.f7432b, this.f7433c, this.f7434d, (Throwable) obj);
        }
    }
}
