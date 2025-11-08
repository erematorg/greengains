package com.reown.sign.engine.domain;

import com.reown.foundation.common.model.Topic;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class a implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7478a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ SignEngine f7479b;

    public /* synthetic */ a(SignEngine signEngine, int i3) {
        this.f7478a = i3;
        this.f7479b = signEngine;
    }

    public final Object invoke(Object obj) {
        int i3 = this.f7478a;
        SignEngine signEngine = this.f7479b;
        switch (i3) {
            case 0:
                return SignEngine$resubscribeToPendingAuthenticateTopics$1.invokeSuspend$lambda$1(signEngine, (Throwable) obj);
            case 1:
                return SignEngine.setupSequenceExpiration$lambda$14(signEngine, (Topic) obj);
            default:
                return SignEngine.resubscribeToSession$lambda$10(signEngine, (Throwable) obj);
        }
    }
}
