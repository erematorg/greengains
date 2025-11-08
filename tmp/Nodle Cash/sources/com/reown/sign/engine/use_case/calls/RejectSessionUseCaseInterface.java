package com.reown.sign.engine.use_case.calls;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001JB\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\nH¦@¢\u0006\u0002\u0010\f¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/RejectSessionUseCaseInterface;", "", "reject", "", "proposerPublicKey", "", "reason", "onSuccess", "Lkotlin/Function0;", "onFailure", "Lkotlin/Function1;", "", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface RejectSessionUseCaseInterface {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ Object reject$default(RejectSessionUseCaseInterface rejectSessionUseCaseInterface, String str, String str2, Function0 function0, Function1 function1, Continuation continuation, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 8) != 0) {
                function1 = new g(3);
            }
            return rejectSessionUseCaseInterface.reject(str, str2, function0, function1, continuation);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: reject");
    }

    /* access modifiers changed from: private */
    static Unit reject$lambda$0(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "it");
        return Unit.INSTANCE;
    }

    @Nullable
    Object reject(@NotNull String str, @NotNull String str2, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Continuation<? super Unit> continuation);
}
