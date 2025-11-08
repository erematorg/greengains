package com.tinder;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0006\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u0002H\u0003\"\b\b\u0001\u0010\u0003*\u00020\u0004\"\b\b\u0002\u0010\u0005*\u00020\u0004\"\b\b\u0003\u0010\u0006*\u00020\u0004\"\b\b\u0004\u0010\u0003*\u00020\u0004\"\b\b\u0005\u0010\u0005*\u00020\u0004\"\b\b\u0006\u0010\u0006*\u00020\u00042\u0006\u0010\u0007\u001a\u0002H\u00032\u0006\u0010\b\u001a\u0002H\u0005H\n¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"<anonymous>", "", "S", "STATE", "", "EVENT", "SIDE_EFFECT", "state", "cause", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)V", "com/tinder/StateMachine$GraphBuilder$StateDefinitionBuilder$onEnter$1$1"}, k = 3, mv = {1, 1, 13})
public final class StateMachine$GraphBuilder$StateDefinitionBuilder$onEnter$$inlined$with$lambda$1 extends Lambda implements Function2<STATE, EVENT, Unit> {
    final /* synthetic */ Function2 $listener$inlined;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StateMachine$GraphBuilder$StateDefinitionBuilder$onEnter$$inlined$with$lambda$1(Function2 function2) {
        super(2);
        this.$listener$inlined = function2;
    }

    public final void invoke(@NotNull STATE state, @NotNull EVENT event) {
        Intrinsics.checkParameterIsNotNull(state, RemoteConfigConstants.ResponseFieldKey.STATE);
        Intrinsics.checkParameterIsNotNull(event, "cause");
        this.$listener$inlined.invoke(state, event);
    }
}
