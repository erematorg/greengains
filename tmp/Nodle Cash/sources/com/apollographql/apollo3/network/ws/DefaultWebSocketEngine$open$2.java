package com.apollographql.apollo3.network.ws;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import okhttp3.WebSocket;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 5, 1}, xi = 48)
public final class DefaultWebSocketEngine$open$2 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ WebSocket $webSocket;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DefaultWebSocketEngine$open$2(WebSocket webSocket) {
        super(1);
        this.$webSocket = webSocket;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@Nullable Throwable th) {
        this.$webSocket.close(1001, (String) null);
    }
}
