package com.reown.sign.engine.use_case.calls;

import com.reown.sign.engine.model.EngineDO;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001JL\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00030\rH¦@¢\u0006\u0002\u0010\u000f¨\u0006\u0010À\u0006\u0003"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/EmitEventUseCaseInterface;", "", "emit", "", "topic", "", "event", "Lcom/reown/sign/engine/model/EngineDO$Event;", "id", "", "onSuccess", "Lkotlin/Function0;", "onFailure", "Lkotlin/Function1;", "", "(Ljava/lang/String;Lcom/reown/sign/engine/model/EngineDO$Event;Ljava/lang/Long;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface EmitEventUseCaseInterface {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ Object emit$default(EmitEventUseCaseInterface emitEventUseCaseInterface, String str, EngineDO.Event event, Long l2, Function0 function0, Function1 function1, Continuation continuation, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 4) != 0) {
                l2 = null;
            }
            return emitEventUseCaseInterface.emit(str, event, l2, function0, function1, continuation);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: emit");
    }

    @Nullable
    Object emit(@NotNull String str, @NotNull EngineDO.Event event, @Nullable Long l2, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Continuation<? super Unit> continuation);
}
