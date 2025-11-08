package com.reown.sign.engine.use_case.calls;

import com.reown.foundation.common.model.Topic;
import com.reown.sign.common.model.vo.clientsync.session.SignRpc;
import com.reown.sign.engine.model.EngineDO;
import java.util.ArrayList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class c implements Function0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7490a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ long f7491b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f7492c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Object f7493d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ Object f7494e;

    /* renamed from: f  reason: collision with root package name */
    public final /* synthetic */ Object f7495f;

    public /* synthetic */ c(Object obj, Object obj2, Object obj3, Object obj4, long j2, int i3) {
        this.f7490a = i3;
        this.f7492c = obj;
        this.f7493d = obj2;
        this.f7494e = obj3;
        this.f7495f = obj4;
        this.f7491b = j2;
    }

    public final Object invoke() {
        switch (this.f7490a) {
            case 0:
                return ApproveSessionAuthenticateUseCase$approveSessionAuthenticate$2.invokeSuspend$lambda$19((ArrayList) this.f7492c, (Function0) this.f7493d, (ApproveSessionAuthenticateUseCase) this.f7494e, (Topic) this.f7495f, this.f7491b);
            default:
                return SessionRequestUseCase.triggerRelayRequest$lambda$3((SessionRequestUseCase) this.f7492c, (EngineDO.Request) this.f7493d, (Function1) this.f7494e, (SignRpc.SessionRequest) this.f7495f, this.f7491b);
        }
    }
}
