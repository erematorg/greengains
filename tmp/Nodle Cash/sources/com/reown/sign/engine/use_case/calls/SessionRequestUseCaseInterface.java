package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.model.SDKError;
import com.reown.android.internal.common.model.type.EngineEvent;
import com.reown.sign.engine.model.EngineDO;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.flow.SharedFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J>\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u000b0\u000f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000b0\u000fH¦@¢\u0006\u0002\u0010\u0013R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0006¨\u0006\u0014À\u0006\u0003"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/SessionRequestUseCaseInterface;", "", "errors", "Lkotlinx/coroutines/flow/SharedFlow;", "Lcom/reown/android/internal/common/model/SDKError;", "getErrors", "()Lkotlinx/coroutines/flow/SharedFlow;", "requestEvents", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "getRequestEvents", "sessionRequest", "", "request", "Lcom/reown/sign/engine/model/EngineDO$Request;", "onSuccess", "Lkotlin/Function1;", "", "onFailure", "", "(Lcom/reown/sign/engine/model/EngineDO$Request;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface SessionRequestUseCaseInterface {
    @NotNull
    SharedFlow<SDKError> getErrors();

    @NotNull
    SharedFlow<EngineEvent> getRequestEvents();

    @Nullable
    Object sessionRequest(@NotNull EngineDO.Request request, @NotNull Function1<? super Long, Unit> function1, @NotNull Function1<? super Throwable, Unit> function12, @NotNull Continuation<? super Unit> continuation);
}
