package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.model.Expiry;
import com.reown.sign.engine.model.EngineDO;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001Jn\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00072\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00030\rH¦@¢\u0006\u0002\u0010\u0010¨\u0006\u0011À\u0006\u0003"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/SessionAuthenticateUseCaseInterface;", "", "authenticate", "", "Lcom/reown/sign/engine/model/EngineDO$Authenticate;", "methods", "", "", "pairingTopic", "expiry", "Lcom/reown/android/internal/common/model/Expiry;", "walletAppLink", "onSuccess", "Lkotlin/Function1;", "onFailure", "", "(Lcom/reown/sign/engine/model/EngineDO$Authenticate;Ljava/util/List;Ljava/lang/String;Lcom/reown/android/internal/common/model/Expiry;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface SessionAuthenticateUseCaseInterface {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ Object authenticate$default(SessionAuthenticateUseCaseInterface sessionAuthenticateUseCaseInterface, EngineDO.Authenticate authenticate, List list, String str, Expiry expiry, String str2, Function1 function1, Function1 function12, Continuation continuation, int i3, Object obj) {
        if (obj == null) {
            return sessionAuthenticateUseCaseInterface.authenticate(authenticate, list, str, expiry, (i3 & 16) != 0 ? null : str2, function1, function12, continuation);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authenticate");
    }

    @Nullable
    Object authenticate(@NotNull EngineDO.Authenticate authenticate, @Nullable List<String> list, @Nullable String str, @Nullable Expiry expiry, @Nullable String str2, @NotNull Function1<? super String, Unit> function1, @NotNull Function1<? super Throwable, Unit> function12, @NotNull Continuation<? super Unit> continuation);
}
