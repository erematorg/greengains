package com.apollographql.apollo3.network.ws;

import com.apollographql.apollo3.api.http.HttpHeader;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H¦@¢\u0006\u0002\u0010\tJ,\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\nH§@¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/apollographql/apollo3/network/ws/WebSocketEngine;", "", "open", "Lcom/apollographql/apollo3/network/ws/WebSocketConnection;", "url", "", "headers", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "(Ljava/lang/String;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface WebSocketEngine {
    @Nullable
    Object open(@NotNull String str, @NotNull List<HttpHeader> list, @NotNull Continuation<? super WebSocketConnection> continuation);

    @Deprecated(message = "Use open(String, List<HttpHeader>) instead.", replaceWith = @ReplaceWith(expression = "open(url, headers.map { HttpHeader(it.key, it.value })", imports = {"com.apollographql.apollo3.api.http.HttpHeader"}))
    @Nullable
    Object open(@NotNull String str, @NotNull Map<String, String> map, @NotNull Continuation<? super WebSocketConnection> continuation);

    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ Object open$default(WebSocketEngine webSocketEngine, String str, List list, Continuation continuation, int i3, Object obj) {
            if (obj == null) {
                if ((i3 & 2) != 0) {
                    list = CollectionsKt.emptyList();
                }
                return webSocketEngine.open(str, (List<HttpHeader>) list, (Continuation<? super WebSocketConnection>) continuation);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: open");
        }

        public static /* synthetic */ Object open$default(WebSocketEngine webSocketEngine, String str, Map map, Continuation continuation, int i3, Object obj) {
            if (obj == null) {
                if ((i3 & 2) != 0) {
                    map = MapsKt.emptyMap();
                }
                return webSocketEngine.open(str, (Map<String, String>) map, (Continuation<? super WebSocketConnection>) continuation);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: open");
        }
    }
}
