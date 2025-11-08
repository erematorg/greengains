package com.reown.sign.engine.use_case.calls;

import com.reown.android.Core;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.DecryptSignMessageUseCase", f = "DecryptSignMessageUseCase.kt", i = {0, 0, 0, 0}, l = {30}, m = "decryptNotification", n = {"topic", "message", "onSuccess", "onFailure"}, s = {"L$0", "L$1", "L$2", "L$3"})
public final class DecryptSignMessageUseCase$decryptNotification$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DecryptSignMessageUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DecryptSignMessageUseCase$decryptNotification$1(DecryptSignMessageUseCase decryptSignMessageUseCase, Continuation<? super DecryptSignMessageUseCase$decryptNotification$1> continuation) {
        super(continuation);
        this.this$0 = decryptSignMessageUseCase;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.decryptNotification((String) null, (String) null, (Function1<? super Core.Model.Message, Unit>) null, (Function1<? super Throwable, Unit>) null, this);
    }
}
