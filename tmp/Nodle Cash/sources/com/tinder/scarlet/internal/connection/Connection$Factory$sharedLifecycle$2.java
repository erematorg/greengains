package com.tinder.scarlet.internal.connection;

import com.tinder.scarlet.internal.connection.Connection;
import com.tinder.scarlet.lifecycle.LifecycleRegistry;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/tinder/scarlet/lifecycle/LifecycleRegistry;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class Connection$Factory$sharedLifecycle$2 extends Lambda implements Function0<LifecycleRegistry> {
    final /* synthetic */ Connection.Factory this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Connection$Factory$sharedLifecycle$2(Connection.Factory factory) {
        super(0);
        this.this$0 = factory;
    }

    @NotNull
    public final LifecycleRegistry invoke() {
        return this.this$0.createSharedLifecycle();
    }
}
