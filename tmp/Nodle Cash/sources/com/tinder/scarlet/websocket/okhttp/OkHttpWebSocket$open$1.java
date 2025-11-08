package com.tinder.scarlet.websocket.okhttp;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.reactivestreams.Subscription;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lorg/reactivestreams/Subscription;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class OkHttpWebSocket$open$1 extends Lambda implements Function1<Subscription, Unit> {
    final /* synthetic */ OkHttpWebSocket this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OkHttpWebSocket$open$1(OkHttpWebSocket okHttpWebSocket) {
        super(1);
        this.this$0 = okHttpWebSocket;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Subscription) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Subscription subscription) {
        this.this$0.connectionEstablisher.establishConnection(this.this$0.okHttpWebSocketEventObserver);
    }
}
