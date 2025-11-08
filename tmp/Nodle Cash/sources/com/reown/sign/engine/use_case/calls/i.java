package com.reown.sign.engine.use_case.calls;

import com.reown.android.Core;
import com.reown.sign.engine.use_case.calls.SessionRequestUseCase$triggerRelayRequest$1$1;
import kotlin.Result;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScope;

public final /* synthetic */ class i implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7517a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7518b;

    public /* synthetic */ i(Object obj, int i3) {
        this.f7517a = i3;
        this.f7518b = obj;
    }

    public final Object invoke(Object obj) {
        int i3 = this.f7517a;
        Object obj2 = this.f7518b;
        switch (i3) {
            case 0:
                return PairUseCase$pair$2.invokeSuspend$lambda$0((Function0) obj2, (Core.Params.Pair) obj);
            case 1:
                return PairUseCase$pair$2.invokeSuspend$lambda$1((Function1) obj2, (Core.Model.Error) obj);
            default:
                return SessionRequestUseCase$triggerRelayRequest$1$1.AnonymousClass1.invokeSuspend$lambda$0((CoroutineScope) obj2, (Result) obj);
        }
    }
}
