package com.reown.android.verify.domain;

import com.reown.android.internal.common.storage.verify.VerifyContextStorageRepository;
import com.reown.android.verify.model.VerifyContext;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.SupervisorKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.verify.domain.ResolveAttestationIdUseCase$insertContext$1", f = "ResolveAttestationIdUseCase.kt", i = {}, l = {66}, m = "invokeSuspend", n = {}, s = {})
public final class ResolveAttestationIdUseCase$insertContext$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ VerifyContext $context;
    final /* synthetic */ Function1<VerifyContext, Unit> $onResolve;
    int label;
    final /* synthetic */ ResolveAttestationIdUseCase this$0;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.android.verify.domain.ResolveAttestationIdUseCase$insertContext$1$1", f = "ResolveAttestationIdUseCase.kt", i = {}, l = {68}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.reown.android.verify.domain.ResolveAttestationIdUseCase$insertContext$1$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(resolveAttestationIdUseCase, verifyContext, function1, continuation);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                VerifyContextStorageRepository access$getRepository$p = resolveAttestationIdUseCase.repository;
                VerifyContext verifyContext = verifyContext;
                this.label = 1;
                if (access$getRepository$p.insertOrAbort(verifyContext, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i3 == 1) {
                try {
                    ResultKt.throwOnFailure(obj);
                } catch (Exception unused) {
                    function1.invoke(verifyContext);
                }
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            function1.invoke(verifyContext);
            return Unit.INSTANCE;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ResolveAttestationIdUseCase$insertContext$1(ResolveAttestationIdUseCase resolveAttestationIdUseCase, VerifyContext verifyContext, Function1<? super VerifyContext, Unit> function1, Continuation<? super ResolveAttestationIdUseCase$insertContext$1> continuation) {
        super(2, continuation);
        this.this$0 = resolveAttestationIdUseCase;
        this.$context = verifyContext;
        this.$onResolve = function1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ResolveAttestationIdUseCase$insertContext$1(this.this$0, this.$context, this.$onResolve, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            final ResolveAttestationIdUseCase resolveAttestationIdUseCase = this.this$0;
            final VerifyContext verifyContext = this.$context;
            final Function1<VerifyContext, Unit> function1 = this.$onResolve;
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
        return ((ResolveAttestationIdUseCase$insertContext$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
