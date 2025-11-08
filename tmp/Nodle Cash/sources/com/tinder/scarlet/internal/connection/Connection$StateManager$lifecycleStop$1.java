package com.tinder.scarlet.internal.connection;

import com.tinder.scarlet.Event;
import com.tinder.scarlet.Lifecycle;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lcom/tinder/scarlet/Event$OnLifecycle$StateChange;", "invoke", "(Lcom/tinder/scarlet/Event$OnLifecycle$StateChange;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class Connection$StateManager$lifecycleStop$1 extends Lambda implements Function1<Event.OnLifecycle.StateChange<?>, Boolean> {
    public static final Connection$StateManager$lifecycleStop$1 INSTANCE = new Connection$StateManager$lifecycleStop$1();

    public Connection$StateManager$lifecycleStop$1() {
        super(1);
    }

    @NotNull
    public final Boolean invoke(@NotNull Event.OnLifecycle.StateChange<?> stateChange) {
        Intrinsics.checkNotNullParameter(stateChange, "$this$where");
        return Boolean.valueOf(stateChange.getState() instanceof Lifecycle.State.Stopped);
    }
}
