package com.apollographql.apollo3.network.ws;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u000e\u0010\u0004\u001a\u00020\u0005H¦@¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lcom/apollographql/apollo3/network/ws/WebSocketConnection;", "", "close", "", "receive", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "send", "string", "data", "Lokio/ByteString;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface WebSocketConnection {
    void close();

    @Nullable
    Object receive(@NotNull Continuation<? super String> continuation);

    void send(@NotNull String str);

    void send(@NotNull ByteString byteString);
}
