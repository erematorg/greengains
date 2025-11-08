package com.reown.sign.engine.use_case.calls;

import com.reown.sign.engine.model.EngineDO;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.SupervisorKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H@¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/FormatAuthenticateMessageUseCase;", "Lcom/reown/sign/engine/use_case/calls/FormatAuthenticateMessageUseCaseInterface;", "<init>", "()V", "formatMessage", "", "payloadParams", "Lcom/reown/sign/engine/model/EngineDO$PayloadParams;", "iss", "(Lcom/reown/sign/engine/model/EngineDO$PayloadParams;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FormatAuthenticateMessageUseCase implements FormatAuthenticateMessageUseCaseInterface {
    @Nullable
    public Object formatMessage(@NotNull EngineDO.PayloadParams payloadParams, @NotNull String str, @NotNull Continuation<? super String> continuation) throws Exception {
        return SupervisorKt.supervisorScope(new FormatAuthenticateMessageUseCase$formatMessage$2(str, payloadParams, (Continuation<? super FormatAuthenticateMessageUseCase$formatMessage$2>) null), continuation);
    }
}
