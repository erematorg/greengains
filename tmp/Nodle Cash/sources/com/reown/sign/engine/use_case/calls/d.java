package com.reown.sign.engine.use_case.calls;

import com.reown.foundation.common.model.Topic;
import java.util.ArrayList;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScope;

public final /* synthetic */ class d implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CoroutineScope f7496a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ ApproveSessionAuthenticateUseCase f7497b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Topic f7498c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Function1 f7499d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ ArrayList f7500e;

    /* renamed from: f  reason: collision with root package name */
    public final /* synthetic */ Topic f7501f;

    public /* synthetic */ d(CoroutineScope coroutineScope, ApproveSessionAuthenticateUseCase approveSessionAuthenticateUseCase, Topic topic, Function1 function1, ArrayList arrayList, Topic topic2) {
        this.f7496a = coroutineScope;
        this.f7497b = approveSessionAuthenticateUseCase;
        this.f7498c = topic;
        this.f7499d = function1;
        this.f7500e = arrayList;
        this.f7501f = topic2;
    }

    public final Object invoke(Object obj) {
        Topic topic = this.f7498c;
        ArrayList arrayList = this.f7500e;
        return ApproveSessionAuthenticateUseCase$approveSessionAuthenticate$2.invokeSuspend$lambda$23(this.f7496a, this.f7497b, topic, this.f7499d, arrayList, this.f7501f, (Throwable) obj);
    }
}
