package com.reown.android.internal.common.modal.domain.usecase;

import com.reown.android.internal.common.modal.AppKitApiRepository;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.modal.domain.usecase.EnableAnalyticsUseCase$fetchAnalyticsConfig$1", f = "EnableAnalyticsUseCase.kt", i = {}, l = {14}, m = "invokeSuspend", n = {}, s = {})
public final class EnableAnalyticsUseCase$fetchAnalyticsConfig$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    int label;
    final /* synthetic */ EnableAnalyticsUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EnableAnalyticsUseCase$fetchAnalyticsConfig$1(EnableAnalyticsUseCase enableAnalyticsUseCase, Continuation<? super EnableAnalyticsUseCase$fetchAnalyticsConfig$1> continuation) {
        super(2, continuation);
        this.this$0 = enableAnalyticsUseCase;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new EnableAnalyticsUseCase$fetchAnalyticsConfig$1(this.this$0, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Boolean bool;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        boolean z2 = false;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            AppKitApiRepository access$getRepository$p = this.this$0.repository;
            this.label = 1;
            bool = AppKitApiRepository.m8743getAnalyticsConfiggIAlus$default(access$getRepository$p, (String) null, this, 1, (Object) null);
            if (bool == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            try {
                ResultKt.throwOnFailure(obj);
                bool = ((Result) obj).m8988unboximpl();
            } catch (Exception unused) {
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (Result.m8986isSuccessimpl(bool)) {
            Boolean boxBoolean = Boxing.boxBoolean(false);
            if (Result.m8985isFailureimpl(bool)) {
                bool = boxBoolean;
            }
            z2 = ((Boolean) bool).booleanValue();
        }
        return Boxing.boxBoolean(z2);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((EnableAnalyticsUseCase$fetchAnalyticsConfig$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
