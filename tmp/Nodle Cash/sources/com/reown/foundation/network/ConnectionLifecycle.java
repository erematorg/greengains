package com.reown.foundation.network;

import kotlin.Metadata;
import kotlinx.coroutines.flow.StateFlow;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0007\u001a\u00020\bH&R\u001a\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lcom/reown/foundation/network/ConnectionLifecycle;", "", "onResume", "Lkotlinx/coroutines/flow/StateFlow;", "", "getOnResume", "()Lkotlinx/coroutines/flow/StateFlow;", "reconnect", "", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface ConnectionLifecycle {
    @NotNull
    StateFlow<Boolean> getOnResume();

    void reconnect();
}
