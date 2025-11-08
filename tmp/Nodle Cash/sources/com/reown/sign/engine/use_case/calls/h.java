package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.model.Pairing;
import kotlin.jvm.functions.Function0;

public final /* synthetic */ class h implements Function0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7513a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7514b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Function0 f7515c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Object f7516d;

    public /* synthetic */ h(Object obj, Object obj2, Function0 function0, int i3) {
        this.f7513a = i3;
        this.f7516d = obj;
        this.f7514b = obj2;
        this.f7515c = function0;
    }

    public final Object invoke() {
        switch (this.f7513a) {
            case 0:
                return DisconnectSessionUseCase$disconnect$2.invokeSuspend$lambda$0((DisconnectSessionUseCase) this.f7516d, (String) this.f7514b, this.f7515c);
            case 1:
                return EmitEventUseCase$emit$2.invokeSuspend$lambda$3$lambda$1((EmitEventUseCase) this.f7516d, (String) this.f7514b, this.f7515c);
            case 2:
                return ExtendSessionUseCase$extend$2.invokeSuspend$lambda$0((ExtendSessionUseCase) this.f7516d, (String) this.f7514b, this.f7515c);
            case 3:
                return ProposeSessionUseCase$proposeSession$2.invokeSuspend$lambda$3$lambda$1((ProposeSessionUseCase) this.f7516d, (Pairing) this.f7514b, this.f7515c);
            default:
                return SessionUpdateUseCase$sessionUpdate$2.invokeSuspend$lambda$3$lambda$1((SessionUpdateUseCase) this.f7516d, (String) this.f7514b, this.f7515c);
        }
    }
}
