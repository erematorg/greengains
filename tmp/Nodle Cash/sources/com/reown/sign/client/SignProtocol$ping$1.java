package com.reown.sign.client;

import com.reown.sign.client.Sign;
import com.reown.sign.engine.domain.SignEngine;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.client.SignProtocol$ping$1", f = "SignProtocol.kt", i = {}, l = {388}, m = "invokeSuspend", n = {}, s = {})
public final class SignProtocol$ping$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Sign.Params.Ping $ping;
    final /* synthetic */ Sign.Listeners.SessionPing $sessionPing;
    int label;
    final /* synthetic */ SignProtocol this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SignProtocol$ping$1(SignProtocol signProtocol, Sign.Params.Ping ping, Sign.Listeners.SessionPing sessionPing, Continuation<? super SignProtocol$ping$1> continuation) {
        super(2, continuation);
        this.this$0 = signProtocol;
        this.$ping = ping;
        this.$sessionPing = sessionPing;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$0(Sign.Listeners.SessionPing sessionPing, String str) {
        if (sessionPing != null) {
            sessionPing.onSuccess(new Sign.Model.Ping.Success(str));
        }
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$1(Sign.Listeners.SessionPing sessionPing, Throwable th) {
        if (sessionPing != null) {
            sessionPing.onError(new Sign.Model.Ping.Error(th));
        }
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SignProtocol$ping$1(this.this$0, this.$ping, this.$sessionPing, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            SignEngine access$getSignEngine$p = this.this$0.signEngine;
            if (access$getSignEngine$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("signEngine");
                access$getSignEngine$p = null;
            }
            SignEngine signEngine = access$getSignEngine$p;
            String topic = this.$ping.getTopic();
            Sign.Listeners.SessionPing sessionPing = this.$sessionPing;
            e eVar = new e(sessionPing, 0);
            e eVar2 = new e(sessionPing, 1);
            long r6 = this.$ping.m8870getTimeoutUwyO8pc();
            this.label = 1;
            if (signEngine.m8881pingzkXUZaI(topic, eVar, eVar2, r6, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e3) {
                Sign.Listeners.SessionPing sessionPing2 = this.$sessionPing;
                if (sessionPing2 != null) {
                    sessionPing2.onError(new Sign.Model.Ping.Error(e3));
                }
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SignProtocol$ping$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
