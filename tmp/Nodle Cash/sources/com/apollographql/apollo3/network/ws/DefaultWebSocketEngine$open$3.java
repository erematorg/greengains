package com.apollographql.apollo3.network.ws;

import com.apollographql.apollo3.internal.ChannelWrapper;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.SendChannel;
import okhttp3.WebSocket;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u000e\u0010\u0004\u001a\u00020\u0005H@¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"com/apollographql/apollo3/network/ws/DefaultWebSocketEngine$open$3", "Lcom/apollographql/apollo3/network/ws/WebSocketConnection;", "close", "", "receive", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "send", "string", "data", "Lokio/ByteString;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class DefaultWebSocketEngine$open$3 implements WebSocketConnection {
    final /* synthetic */ ChannelWrapper<String> $messageChannel;
    final /* synthetic */ WebSocket $webSocket;

    public DefaultWebSocketEngine$open$3(ChannelWrapper<String> channelWrapper, WebSocket webSocket) {
        this.$messageChannel = channelWrapper;
        this.$webSocket = webSocket;
    }

    public void close() {
        this.$webSocket.close(1000, (String) null);
    }

    @Nullable
    public Object receive(@NotNull Continuation<? super String> continuation) {
        return this.$messageChannel.receive(continuation);
    }

    public void send(@NotNull ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "data");
        if (!this.$webSocket.send(byteString)) {
            SendChannel.DefaultImpls.close$default(this.$messageChannel, (Throwable) null, 1, (Object) null);
        }
    }

    public void send(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "string");
        if (!this.$webSocket.send(str)) {
            SendChannel.DefaultImpls.close$default(this.$messageChannel, (Throwable) null, 1, (Object) null);
        }
    }
}
