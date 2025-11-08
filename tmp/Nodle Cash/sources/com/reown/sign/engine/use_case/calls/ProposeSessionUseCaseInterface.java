package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.model.Pairing;
import com.reown.sign.engine.model.EngineDO;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\u0001\u0010\u0002\u001a\u00020\u00032\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00052\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00052\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00030\u0010H¦@¢\u0006\u0002\u0010\u0012¨\u0006\u0013À\u0006\u0003"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/ProposeSessionUseCaseInterface;", "", "proposeSession", "", "requiredNamespaces", "", "", "Lcom/reown/sign/engine/model/EngineDO$Namespace$Proposal;", "optionalNamespaces", "properties", "scopedProperties", "pairing", "Lcom/reown/android/internal/common/model/Pairing;", "onSuccess", "Lkotlin/Function0;", "onFailure", "Lkotlin/Function1;", "", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Lcom/reown/android/internal/common/model/Pairing;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface ProposeSessionUseCaseInterface {
    @Nullable
    Object proposeSession(@Nullable Map<String, EngineDO.Namespace.Proposal> map, @Nullable Map<String, EngineDO.Namespace.Proposal> map2, @Nullable Map<String, String> map3, @Nullable Map<String, String> map4, @NotNull Pairing pairing, @NotNull Function0<Unit> function0, @NotNull Function1<? super Throwable, Unit> function1, @NotNull Continuation<? super Unit> continuation);
}
