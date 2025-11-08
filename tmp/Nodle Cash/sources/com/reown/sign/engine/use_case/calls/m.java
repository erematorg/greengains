package com.reown.sign.engine.use_case.calls;

import com.reown.foundation.common.model.Topic;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class m implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ RejectSessionAuthenticateUseCase f7532a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Topic f7533b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Function1 f7534c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ long f7535d;

    public /* synthetic */ m(RejectSessionAuthenticateUseCase rejectSessionAuthenticateUseCase, Topic topic, Function1 function1, long j2) {
        this.f7532a = rejectSessionAuthenticateUseCase;
        this.f7533b = topic;
        this.f7534c = function1;
        this.f7535d = j2;
    }

    public final Object invoke(Object obj) {
        Topic topic = this.f7533b;
        Function1 function1 = this.f7534c;
        return RejectSessionAuthenticateUseCase$rejectSessionAuthenticate$2.invokeSuspend$lambda$2(this.f7532a, topic, function1, this.f7535d, (Throwable) obj);
    }
}
