package com.reown.sign.engine.use_case.calls;

import com.reown.sign.common.model.vo.clientsync.session.SignRpc;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class j implements Function0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ PingUseCase f7519a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ String f7520b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ long f7521c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ SignRpc.SessionPing f7522d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ Function1 f7523e;

    /* renamed from: f  reason: collision with root package name */
    public final /* synthetic */ Function1 f7524f;

    public /* synthetic */ j(PingUseCase pingUseCase, String str, long j2, SignRpc.SessionPing sessionPing, Function1 function1, Function1 function12) {
        this.f7519a = pingUseCase;
        this.f7520b = str;
        this.f7521c = j2;
        this.f7522d = sessionPing;
        this.f7523e = function1;
        this.f7524f = function12;
    }

    public final Object invoke() {
        return PingUseCase$ping$2.invokeSuspend$lambda$0(this.f7519a, this.f7520b, this.f7521c, this.f7522d, this.f7523e, this.f7524f);
    }
}
