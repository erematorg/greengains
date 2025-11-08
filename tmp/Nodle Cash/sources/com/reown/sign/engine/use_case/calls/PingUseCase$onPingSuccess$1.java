package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.JsonRpcResponse;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.model.vo.clientsync.session.SignRpc;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.TimeoutKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.PingUseCase$onPingSuccess$1", f = "PingUseCase.kt", i = {}, l = {61}, m = "invokeSuspend", n = {}, s = {})
public final class PingUseCase$onPingSuccess$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Throwable, Unit> $onFailure;
    final /* synthetic */ Function1<String, Unit> $onSuccess;
    final /* synthetic */ SignRpc.SessionPing $pingPayload;
    final /* synthetic */ long $timeout;
    final /* synthetic */ String $topic;
    int label;
    final /* synthetic */ PingUseCase this$0;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.sign.engine.use_case.calls.PingUseCase$onPingSuccess$1$1", f = "PingUseCase.kt", i = {0}, l = {62}, m = "invokeSuspend", n = {"$this$withTimeout"}, s = {"L$0"})
    /* renamed from: com.reown.sign.engine.use_case.calls.PingUseCase$onPingSuccess$1$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        /* access modifiers changed from: private */
        public static final Unit invokeSuspend$lambda$2(CoroutineScope coroutineScope, PingUseCase pingUseCase, Function1 function1, String str, Function1 function12, Result result) {
            CoroutineScopeKt.cancel$default(coroutineScope, (CancellationException) null, 1, (Object) null);
            Object r2 = result.m8988unboximpl();
            Throwable r7 = Result.m8982exceptionOrNullimpl(r2);
            if (r7 == null) {
                JsonRpcResponse.JsonRpcResult jsonRpcResult = (JsonRpcResponse.JsonRpcResult) r2;
                pingUseCase.logger.log("Ping peer response success");
                function1.invoke(str);
            } else {
                Logger access$getLogger$p = pingUseCase.logger;
                access$getLogger$p.error("Ping peer response error: " + r7);
                function12.invoke(r7);
            }
            return Unit.INSTANCE;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r02 = new AnonymousClass1(pingUseCase, sessionPing, function1, str, function12, continuation);
            r02.L$0 = obj;
            return r02;
        }

        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                PingUseCase pingUseCase = pingUseCase;
                long id = sessionPing.getId();
                b bVar = new b(coroutineScope, pingUseCase, (Function1) function1, str, (Function1) function12);
                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.label = 1;
                if (pingUseCase.collectResponse(id, bVar, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i3 == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PingUseCase$onPingSuccess$1(long j2, Function1<? super Throwable, Unit> function1, PingUseCase pingUseCase, SignRpc.SessionPing sessionPing, Function1<? super String, Unit> function12, String str, Continuation<? super PingUseCase$onPingSuccess$1> continuation) {
        super(2, continuation);
        this.$timeout = j2;
        this.$onFailure = function1;
        this.this$0 = pingUseCase;
        this.$pingPayload = sessionPing;
        this.$onSuccess = function12;
        this.$topic = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PingUseCase$onPingSuccess$1(this.$timeout, this.$onFailure, this.this$0, this.$pingPayload, this.$onSuccess, this.$topic, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            long j2 = this.$timeout;
            final PingUseCase pingUseCase = this.this$0;
            final SignRpc.SessionPing sessionPing = this.$pingPayload;
            final Function1<String, Unit> function1 = this.$onSuccess;
            final String str = this.$topic;
            final Function1<Throwable, Unit> function12 = this.$onFailure;
            AnonymousClass1 r5 = new AnonymousClass1((Continuation<? super AnonymousClass1>) null);
            this.label = 1;
            if (TimeoutKt.m10466withTimeoutKLykuaI(j2, r5, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (TimeoutCancellationException e3) {
                this.$onFailure.invoke(e3);
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PingUseCase$onPingSuccess$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
