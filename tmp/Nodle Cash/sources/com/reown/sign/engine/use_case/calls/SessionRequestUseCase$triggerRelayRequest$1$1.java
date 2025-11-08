package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.model.SDKError;
import com.reown.sign.common.model.vo.clientsync.session.SignRpc;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.TimeoutKt;
import kotlinx.coroutines.flow.MutableSharedFlow;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.SessionRequestUseCase$triggerRelayRequest$1$1", f = "SessionRequestUseCase.kt", i = {1}, l = {166, 170}, m = "invokeSuspend", n = {"e"}, s = {"L$0"})
public final class SessionRequestUseCase$triggerRelayRequest$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $requestTtlInSeconds;
    final /* synthetic */ SignRpc.SessionRequest $sessionPayload;
    Object L$0;
    int label;
    final /* synthetic */ SessionRequestUseCase this$0;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.sign.engine.use_case.calls.SessionRequestUseCase$triggerRelayRequest$1$1$1", f = "SessionRequestUseCase.kt", i = {0}, l = {167}, m = "invokeSuspend", n = {"$this$withTimeout"}, s = {"L$0"})
    /* renamed from: com.reown.sign.engine.use_case.calls.SessionRequestUseCase$triggerRelayRequest$1$1$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        /* access modifiers changed from: private */
        public static final Unit invokeSuspend$lambda$0(CoroutineScope coroutineScope, Result result) {
            CoroutineScopeKt.cancel$default(coroutineScope, (CancellationException) null, 1, (Object) null);
            return Unit.INSTANCE;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r02 = new AnonymousClass1(sessionRequestUseCase, sessionRequest, continuation);
            r02.L$0 = obj;
            return r02;
        }

        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                SessionRequestUseCase sessionRequestUseCase = sessionRequestUseCase;
                long id = sessionRequest.getId();
                i iVar = new i(coroutineScope, 2);
                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.label = 1;
                if (sessionRequestUseCase.collectResponse(id, iVar, this) == coroutine_suspended) {
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
    public SessionRequestUseCase$triggerRelayRequest$1$1(long j2, SessionRequestUseCase sessionRequestUseCase, SignRpc.SessionRequest sessionRequest, Continuation<? super SessionRequestUseCase$triggerRelayRequest$1$1> continuation) {
        super(2, continuation);
        this.$requestTtlInSeconds = j2;
        this.this$0 = sessionRequestUseCase;
        this.$sessionPayload = sessionRequest;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SessionRequestUseCase$triggerRelayRequest$1$1(this.$requestTtlInSeconds, this.this$0, this.$sessionPayload, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            long millis = TimeUnit.SECONDS.toMillis(this.$requestTtlInSeconds);
            final SessionRequestUseCase sessionRequestUseCase = this.this$0;
            final SignRpc.SessionRequest sessionRequest = this.$sessionPayload;
            AnonymousClass1 r9 = new AnonymousClass1((Continuation<? super AnonymousClass1>) null);
            this.label = 1;
            if (TimeoutKt.withTimeout(millis, r9, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (TimeoutCancellationException e3) {
                MutableSharedFlow access$get_errors$p = this.this$0._errors;
                SDKError sDKError = new SDKError(e3);
                this.L$0 = SpillingKt.nullOutSpilledVariable(e3);
                this.label = 2;
                if (access$get_errors$p.emit(sDKError, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i3 == 2) {
            TimeoutCancellationException timeoutCancellationException = (TimeoutCancellationException) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SessionRequestUseCase$triggerRelayRequest$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
