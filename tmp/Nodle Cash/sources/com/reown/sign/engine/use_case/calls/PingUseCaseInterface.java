package com.reown.sign.engine.use_case.calls;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001JJ\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00030\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u00072\b\b\u0002\u0010\n\u001a\u00020\u000bH¦@¢\u0006\u0004\b\f\u0010\r¨\u0006\u000eÀ\u0006\u0003"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/PingUseCaseInterface;", "", "ping", "", "topic", "", "onSuccess", "Lkotlin/Function1;", "onFailure", "", "timeout", "Lkotlin/time/Duration;", "ping-zkXUZaI", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PingUseCaseInterface {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    /* renamed from: ping-zkXUZaI$default  reason: not valid java name */
    static /* synthetic */ Object m8888pingzkXUZaI$default(PingUseCaseInterface pingUseCaseInterface, String str, Function1 function1, Function1 function12, long j2, Continuation continuation, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 8) != 0) {
                j2 = PingUseCaseKt.THIRTY_SECONDS_TIMEOUT;
            }
            return pingUseCaseInterface.m8889pingzkXUZaI(str, function1, function12, j2, continuation);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: ping-zkXUZaI");
    }

    @Nullable
    /* renamed from: ping-zkXUZaI  reason: not valid java name */
    Object m8889pingzkXUZaI(@NotNull String str, @NotNull Function1<? super String, Unit> function1, @NotNull Function1<? super Throwable, Unit> function12, long j2, @NotNull Continuation<? super Unit> continuation);
}
