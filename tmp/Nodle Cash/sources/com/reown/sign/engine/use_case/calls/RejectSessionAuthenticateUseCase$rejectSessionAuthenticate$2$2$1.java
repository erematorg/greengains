package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.storage.verify.VerifyContextStorageRepository;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.SupervisorKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.RejectSessionAuthenticateUseCase$rejectSessionAuthenticate$2$2$1", f = "RejectSessionAuthenticateUseCase.kt", i = {}, l = {96}, m = "invokeSuspend", n = {}, s = {})
public final class RejectSessionAuthenticateUseCase$rejectSessionAuthenticate$2$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $id;
    int label;
    final /* synthetic */ RejectSessionAuthenticateUseCase this$0;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.sign.engine.use_case.calls.RejectSessionAuthenticateUseCase$rejectSessionAuthenticate$2$2$1$1", f = "RejectSessionAuthenticateUseCase.kt", i = {}, l = {96}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.reown.sign.engine.use_case.calls.RejectSessionAuthenticateUseCase$rejectSessionAuthenticate$2$2$1$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(rejectSessionAuthenticateUseCase, j2, continuation);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                VerifyContextStorageRepository access$getVerifyContextStorageRepository$p = rejectSessionAuthenticateUseCase.verifyContextStorageRepository;
                long j2 = j2;
                this.label = 1;
                if (access$getVerifyContextStorageRepository$p.delete(j2, this) == coroutine_suspended) {
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
    public RejectSessionAuthenticateUseCase$rejectSessionAuthenticate$2$2$1(RejectSessionAuthenticateUseCase rejectSessionAuthenticateUseCase, long j2, Continuation<? super RejectSessionAuthenticateUseCase$rejectSessionAuthenticate$2$2$1> continuation) {
        super(2, continuation);
        this.this$0 = rejectSessionAuthenticateUseCase;
        this.$id = j2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RejectSessionAuthenticateUseCase$rejectSessionAuthenticate$2$2$1(this.this$0, this.$id, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            final RejectSessionAuthenticateUseCase rejectSessionAuthenticateUseCase = this.this$0;
            final long j2 = this.$id;
            AnonymousClass1 r7 = new AnonymousClass1((Continuation<? super AnonymousClass1>) null);
            this.label = 1;
            if (SupervisorKt.supervisorScope(r7, this) == coroutine_suspended) {
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
        return ((RejectSessionAuthenticateUseCase$rejectSessionAuthenticate$2$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
