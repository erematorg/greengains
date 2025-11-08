package com.reown.sign.engine.use_case.calls;

import com.reown.foundation.common.model.Topic;
import com.reown.sign.engine.use_case.calls.PingUseCase$onPingSuccess$1;
import java.util.ArrayList;
import kotlin.Result;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScope;

public final /* synthetic */ class b implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7484a = 0;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Function1 f7485b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f7486c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Object f7487d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ Object f7488e;

    /* renamed from: f  reason: collision with root package name */
    public final /* synthetic */ Object f7489f;

    public /* synthetic */ b(Function1 function1, ApproveSessionAuthenticateUseCase approveSessionAuthenticateUseCase, ArrayList arrayList, Topic topic, Topic topic2) {
        this.f7485b = function1;
        this.f7486c = approveSessionAuthenticateUseCase;
        this.f7487d = arrayList;
        this.f7488e = topic;
        this.f7489f = topic2;
    }

    public final Object invoke(Object obj) {
        switch (this.f7484a) {
            case 0:
                ArrayList arrayList = (ArrayList) this.f7487d;
                Topic topic = (Topic) this.f7488e;
                return ApproveSessionAuthenticateUseCase$approveSessionAuthenticate$2.invokeSuspend$lambda$16(this.f7485b, (ApproveSessionAuthenticateUseCase) this.f7486c, arrayList, topic, (Topic) this.f7489f, (Throwable) obj);
            default:
                Function1 function1 = this.f7485b;
                String str = (String) this.f7488e;
                return PingUseCase$onPingSuccess$1.AnonymousClass1.invokeSuspend$lambda$2((CoroutineScope) this.f7486c, (PingUseCase) this.f7487d, function1, str, (Function1) this.f7489f, (Result) obj);
        }
    }

    public /* synthetic */ b(CoroutineScope coroutineScope, PingUseCase pingUseCase, Function1 function1, String str, Function1 function12) {
        this.f7486c = coroutineScope;
        this.f7487d = pingUseCase;
        this.f7485b = function1;
        this.f7488e = str;
        this.f7489f = function12;
    }
}
