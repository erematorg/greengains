package com.apollographql.apollo3.network.ws;

import com.apollographql.apollo3.exception.ApolloWebSocketClosedException;
import com.apollographql.apollo3.internal.ChannelWrapper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.channels.SendChannel;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000;\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J \u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\"\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\tH\u0016J\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¨\u0006\u0015"}, d2 = {"com/apollographql/apollo3/network/ws/DefaultWebSocketEngine$open$webSocket$1", "Lokhttp3/WebSocketListener;", "onClosed", "", "webSocket", "Lokhttp3/WebSocket;", "code", "", "reason", "", "onClosing", "onFailure", "t", "", "response", "Lokhttp3/Response;", "onMessage", "text", "bytes", "Lokio/ByteString;", "onOpen", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class DefaultWebSocketEngine$open$webSocket$1 extends WebSocketListener {
    final /* synthetic */ ChannelWrapper<String> $messageChannel;
    final /* synthetic */ CompletableDeferred<Unit> $webSocketOpenResult;

    public DefaultWebSocketEngine$open$webSocket$1(CompletableDeferred<Unit> completableDeferred, ChannelWrapper<String> channelWrapper) {
        this.$webSocketOpenResult = completableDeferred;
        this.$messageChannel = channelWrapper;
    }

    public void onClosed(@NotNull WebSocket webSocket, int i3, @NotNull String str) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(str, "reason");
        SendChannel.DefaultImpls.close$default(this.$messageChannel, (Throwable) null, 1, (Object) null);
    }

    public void onClosing(@NotNull WebSocket webSocket, int i3, @NotNull String str) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(str, "reason");
        this.$webSocketOpenResult.complete(Unit.INSTANCE);
        this.$messageChannel.close(new ApolloWebSocketClosedException(i3, str, (Throwable) null, 4, (DefaultConstructorMarker) null));
    }

    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable th, @Nullable Response response) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(th, "t");
        this.$webSocketOpenResult.complete(Unit.INSTANCE);
        this.$messageChannel.close(th);
    }

    public void onMessage(@NotNull WebSocket webSocket, @NotNull String str) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(str, "text");
        this.$messageChannel.m8220trySendJP2dKIU(str);
    }

    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(response, "response");
        this.$webSocketOpenResult.complete(Unit.INSTANCE);
    }

    public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString byteString) {
        Intrinsics.checkNotNullParameter(webSocket, "webSocket");
        Intrinsics.checkNotNullParameter(byteString, "bytes");
        this.$messageChannel.m8220trySendJP2dKIU(byteString.utf8());
    }
}
