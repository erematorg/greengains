package com.reown.sign.engine.use_case.calls;

import com.reown.foundation.common.model.Topic;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CompletableDeferred;

public final /* synthetic */ class p implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7544a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ SessionAuthenticateUseCase f7545b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Topic f7546c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ CompletableDeferred f7547d;

    public /* synthetic */ p(SessionAuthenticateUseCase sessionAuthenticateUseCase, Topic topic, CompletableDeferred completableDeferred, int i3) {
        this.f7544a = i3;
        this.f7545b = sessionAuthenticateUseCase;
        this.f7546c = topic;
        this.f7547d = completableDeferred;
    }

    public final Object invoke(Object obj) {
        Throwable th = (Throwable) obj;
        switch (this.f7544a) {
            case 0:
                return SessionAuthenticateUseCase.publishSessionProposeDeferred_BWLJW6A$lambda$8(this.f7545b, this.f7546c, this.f7547d, th);
            default:
                return SessionAuthenticateUseCase.publishSessionAuthenticateDeferred_yxL6bBk$lambda$6(this.f7545b, this.f7546c, this.f7547d, th);
        }
    }
}
