package com.reown.sign.engine.use_case.calls;

import com.reown.android.Core;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CompletableDeferred;

public final /* synthetic */ class o implements Function0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7540a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ SessionAuthenticateUseCase f7541b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Core.Model.Pairing f7542c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ CompletableDeferred f7543d;

    public /* synthetic */ o(SessionAuthenticateUseCase sessionAuthenticateUseCase, Core.Model.Pairing pairing, CompletableDeferred completableDeferred, int i3) {
        this.f7540a = i3;
        this.f7541b = sessionAuthenticateUseCase;
        this.f7542c = pairing;
        this.f7543d = completableDeferred;
    }

    public final Object invoke() {
        switch (this.f7540a) {
            case 0:
                return SessionAuthenticateUseCase.publishSessionProposeDeferred_BWLJW6A$lambda$7(this.f7541b, this.f7542c, this.f7543d);
            default:
                return SessionAuthenticateUseCase.publishSessionAuthenticateDeferred_yxL6bBk$lambda$5(this.f7541b, this.f7542c, this.f7543d);
        }
    }
}
