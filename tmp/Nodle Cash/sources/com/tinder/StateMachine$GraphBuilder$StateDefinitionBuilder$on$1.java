package com.tinder;

import androidx.core.app.NotificationCompat;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.tinder.StateMachine;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0004\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0004*\u0002H\u0005\"\b\b\u0001\u0010\u0006*\u0002H\u0002\"\b\b\u0002\u0010\u0002*\u00020\u0007\"\b\b\u0003\u0010\u0005*\u00020\u0007\"\b\b\u0004\u0010\u0003*\u00020\u0007\"\b\b\u0005\u0010\u0002*\u00020\u0007\"\b\b\u0006\u0010\u0005*\u00020\u0007\"\b\b\u0007\u0010\u0003*\u00020\u00072\u0006\u0010\b\u001a\u0002H\u00022\u0006\u0010\t\u001a\u0002H\u0005H\n¢\u0006\u0004\b\n\u0010\u000b"}, d2 = {"<anonymous>", "Lcom/tinder/StateMachine$Graph$State$TransitionTo;", "STATE", "SIDE_EFFECT", "E", "EVENT", "S", "", "state", "event", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Lcom/tinder/StateMachine$Graph$State$TransitionTo;"}, k = 3, mv = {1, 1, 13})
public final class StateMachine$GraphBuilder$StateDefinitionBuilder$on$1 extends Lambda implements Function2<STATE, EVENT, StateMachine.Graph.State.TransitionTo<? extends STATE, ? extends SIDE_EFFECT>> {
    final /* synthetic */ Function2 $createTransitionTo;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StateMachine$GraphBuilder$StateDefinitionBuilder$on$1(Function2 function2) {
        super(2);
        this.$createTransitionTo = function2;
    }

    @NotNull
    public final StateMachine.Graph.State.TransitionTo<STATE, SIDE_EFFECT> invoke(@NotNull STATE state, @NotNull EVENT event) {
        Intrinsics.checkParameterIsNotNull(state, RemoteConfigConstants.ResponseFieldKey.STATE);
        Intrinsics.checkParameterIsNotNull(event, NotificationCompat.CATEGORY_EVENT);
        return (StateMachine.Graph.State.TransitionTo) this.$createTransitionTo.invoke(state, event);
    }
}
