package com.reown.foundation.network;

import com.reown.foundation.network.model.RelayDTO;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SourceDebugExtension({"SMAP\nCollect.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Collect.kt\nkotlinx/coroutines/flow/FlowKt__CollectKt$collect$3\n+ 2 BaseRelayClient.kt\ncom/reown/foundation/network/BaseRelayClient$observeResults$1\n*L\n1#1,132:1\n86#2,6:133\n*E\n"})
@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0})
public final class BaseRelayClient$observeResults$1$invokeSuspend$$inlined$collect$1 implements FlowCollector<RelayDTO> {
    final /* synthetic */ BaseRelayClient this$0;

    public BaseRelayClient$observeResults$1$invokeSuspend$$inlined$collect$1(BaseRelayClient baseRelayClient) {
        this.this$0 = baseRelayClient;
    }

    @Nullable
    public Object emit(Object obj, @NotNull Continuation continuation) {
        RelayDTO relayDTO = (RelayDTO) obj;
        if (this.this$0.isLoggingEnabled()) {
            long currentTimeMillis = System.currentTimeMillis();
            System.out.println("Result: " + relayDTO + "; timestamp: " + currentTimeMillis);
        }
        Object emit = this.this$0.resultState.emit(relayDTO, continuation);
        return emit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? emit : Unit.INSTANCE;
    }
}
