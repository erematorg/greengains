package com.reown.sign.engine.use_case.calls;

import com.reown.android.Core;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.PairUseCase$pair$2", f = "PairUseCase.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class PairUseCase$pair$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Throwable, Unit> $onFailure;
    final /* synthetic */ Function0<Unit> $onSuccess;
    final /* synthetic */ String $uri;
    int label;
    final /* synthetic */ PairUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PairUseCase$pair$2(PairUseCase pairUseCase, String str, Function0<Unit> function0, Function1<? super Throwable, Unit> function1, Continuation<? super PairUseCase$pair$2> continuation) {
        super(2, continuation);
        this.this$0 = pairUseCase;
        this.$uri = str;
        this.$onSuccess = function0;
        this.$onFailure = function1;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$0(Function0 function0, Core.Params.Pair pair) {
        function0.invoke();
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$1(Function1 function1, Core.Model.Error error) {
        function1.invoke(error.getThrowable());
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PairUseCase$pair$2(this.this$0, this.$uri, this.$onSuccess, this.$onFailure, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.pairingInterface.pair(new Core.Params.Pair(this.$uri), new i(this.$onSuccess, 0), new i(this.$onFailure, 1));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PairUseCase$pair$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
