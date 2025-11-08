package com.reown.sign.engine.domain;

import com.reown.android.internal.common.model.Validation;
import com.reown.android.internal.common.storage.verify.VerifyContextStorageRepository;
import com.reown.android.verify.model.VerifyContext;
import com.reown.sign.engine.SessionRequestQueueKt;
import com.reown.sign.engine.model.EngineDO;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.SupervisorKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.domain.SignEngine$propagatePendingSessionRequestsQueue$1$4$1", f = "SignEngine.kt", i = {}, l = {395}, m = "invokeSuspend", n = {}, s = {})
public final class SignEngine$propagatePendingSessionRequestsQueue$1$4$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ EngineDO.SessionRequest $sessionRequest;
    int label;
    final /* synthetic */ SignEngine this$0;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.sign.engine.domain.SignEngine$propagatePendingSessionRequestsQueue$1$4$1$1", f = "SignEngine.kt", i = {}, l = {397}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.reown.sign.engine.domain.SignEngine$propagatePendingSessionRequestsQueue$1$4$1$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(signEngine, sessionRequest, continuation);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                VerifyContextStorageRepository access$getVerifyContextStorageRepository$p = signEngine.verifyContextStorageRepository;
                long id = sessionRequest.getRequest().getId();
                this.label = 1;
                obj = access$getVerifyContextStorageRepository$p.get(id, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i3 == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            VerifyContext verifyContext = (VerifyContext) obj;
            if (verifyContext == null) {
                long id2 = sessionRequest.getRequest().getId();
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                verifyContext = new VerifyContext(id2, Intrinsics.checkNotNullParameter(stringCompanionObject, "<this>"), Validation.UNKNOWN, Intrinsics.checkNotNullParameter(stringCompanionObject, "<this>"), (Boolean) null);
            }
            SessionRequestQueueKt.getSessionRequestEventsQueue().add(new EngineDO.SessionRequestEvent(sessionRequest, Intrinsics.checkNotNullParameter(verifyContext, "<this>")));
            return Unit.INSTANCE;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SignEngine$propagatePendingSessionRequestsQueue$1$4$1(SignEngine signEngine, EngineDO.SessionRequest sessionRequest, Continuation<? super SignEngine$propagatePendingSessionRequestsQueue$1$4$1> continuation) {
        super(2, continuation);
        this.this$0 = signEngine;
        this.$sessionRequest = sessionRequest;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SignEngine$propagatePendingSessionRequestsQueue$1$4$1(this.this$0, this.$sessionRequest, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            final SignEngine signEngine = this.this$0;
            final EngineDO.SessionRequest sessionRequest = this.$sessionRequest;
            AnonymousClass1 r6 = new AnonymousClass1((Continuation<? super AnonymousClass1>) null);
            this.label = 1;
            if (SupervisorKt.supervisorScope(r6, this) == coroutine_suspended) {
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
        return ((SignEngine$propagatePendingSessionRequestsQueue$1$4$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
