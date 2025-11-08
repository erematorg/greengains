package com.reown.sign.engine.use_case.calls;

import com.reown.android.push.client.a;
import com.reown.sign.engine.model.EngineDO;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\u0001\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u00072\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00030\u000eH¦@¢\u0006\u0002\u0010\u0010¨\u0006\u0011À\u0006\u0003"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/ApproveSessionUseCaseInterface;", "", "approve", "", "proposerPublicKey", "", "sessionNamespaces", "", "Lcom/reown/sign/engine/model/EngineDO$Namespace$Session;", "sessionProperties", "scopedProperties", "onSuccess", "Lkotlin/Function0;", "onFailure", "Lkotlin/Function1;", "", "(Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface ApproveSessionUseCaseInterface {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ Object approve$default(ApproveSessionUseCaseInterface approveSessionUseCaseInterface, String str, Map map, Map map2, Map map3, Function0 function0, Function1 function1, Continuation continuation, int i3, Object obj) {
        if (obj == null) {
            return approveSessionUseCaseInterface.approve(str, map, (i3 & 4) != 0 ? null : map2, (i3 & 8) != 0 ? null : map3, (i3 & 16) != 0 ? new a(6) : function0, (i3 & 32) != 0 ? new g(0) : function1, continuation);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: approve");
    }

    /* access modifiers changed from: private */
    static Unit approve$lambda$0() {
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    static Unit approve$lambda$1(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "it");
        return Unit.INSTANCE;
    }

    @Nullable
    Object approve(@NotNull String str, @NotNull Map<String, EngineDO.Namespace.Session> map, @Nullable Map<String, String> map2, @Nullable Map<String, String> map3, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Continuation<? super Unit> continuation);
}
