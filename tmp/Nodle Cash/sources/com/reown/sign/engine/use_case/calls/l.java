package com.reown.sign.engine.use_case.calls;

import com.reown.foundation.common.model.Topic;
import kotlin.jvm.functions.Function0;

public final /* synthetic */ class l implements Function0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ RejectSessionAuthenticateUseCase f7528a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Topic f7529b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Function0 f7530c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ long f7531d;

    public /* synthetic */ l(RejectSessionAuthenticateUseCase rejectSessionAuthenticateUseCase, Topic topic, Function0 function0, long j2) {
        this.f7528a = rejectSessionAuthenticateUseCase;
        this.f7529b = topic;
        this.f7530c = function0;
        this.f7531d = j2;
    }

    public final Object invoke() {
        return RejectSessionAuthenticateUseCase$rejectSessionAuthenticate$2.invokeSuspend$lambda$1(this.f7528a, this.f7529b, this.f7530c, this.f7531d);
    }
}
