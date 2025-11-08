package com.apollographql.apollo3.network;

import com.apollographql.apollo3.ApolloClient;
import com.apollographql.apollo3.api.http.HttpHeader;
import com.apollographql.apollo3.network.http.DefaultHttpEngine;
import com.apollographql.apollo3.network.http.HttpNetworkTransport;
import com.apollographql.apollo3.network.ws.DefaultWebSocketEngine;
import com.apollographql.apollo3.network.ws.WebSocketNetworkTransport;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0012\u0010\u0000\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0000\u001a\u00020\u0003\u001a\u0012\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0012\u0010\u0005\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0012\u0010\u0005\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0012\u0010\b\u001a\u00020\t*\b\u0012\u0004\u0012\u00020\u000b0\nH\u0000¨\u0006\f"}, d2 = {"okHttpCallFactory", "Lcom/apollographql/apollo3/ApolloClient$Builder;", "callFactory", "Lokhttp3/Call$Factory;", "Lcom/apollographql/apollo3/network/http/HttpNetworkTransport$Builder;", "okHttpClient", "Lokhttp3/OkHttpClient;", "Lcom/apollographql/apollo3/network/ws/WebSocketNetworkTransport$Builder;", "toOkHttpHeaders", "Lokhttp3/Headers;", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "apollo-runtime"}, k = 2, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nOkHttpExtensions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OkHttpExtensions.kt\ncom/apollographql/apollo3/network/OkHttpExtensionsKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,57:1\n1855#2,2:58\n*S KotlinDebug\n*F\n+ 1 OkHttpExtensions.kt\ncom/apollographql/apollo3/network/OkHttpExtensionsKt\n*L\n54#1:58,2\n*E\n"})
public final class OkHttpExtensionsKt {
    @NotNull
    public static final ApolloClient.Builder okHttpCallFactory(@NotNull ApolloClient.Builder builder, @NotNull Call.Factory factory) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(factory, "callFactory");
        builder.httpEngine(new DefaultHttpEngine(factory));
        return builder;
    }

    @NotNull
    public static final ApolloClient.Builder okHttpClient(@NotNull ApolloClient.Builder builder, @NotNull OkHttpClient okHttpClient) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(okHttpClient, "okHttpClient");
        builder.httpEngine(new DefaultHttpEngine(okHttpClient));
        builder.webSocketEngine(new DefaultWebSocketEngine(okHttpClient));
        return builder;
    }

    @NotNull
    public static final Headers toOkHttpHeaders(@NotNull List<HttpHeader> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Headers.Builder builder = new Headers.Builder();
        for (HttpHeader httpHeader : list) {
            builder.add(httpHeader.getName(), httpHeader.getValue());
        }
        return builder.build();
    }

    @NotNull
    public static final HttpNetworkTransport.Builder okHttpCallFactory(@NotNull HttpNetworkTransport.Builder builder, @NotNull Call.Factory factory) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(factory, "okHttpCallFactory");
        builder.httpEngine(new DefaultHttpEngine(factory));
        return builder;
    }

    @NotNull
    public static final HttpNetworkTransport.Builder okHttpClient(@NotNull HttpNetworkTransport.Builder builder, @NotNull OkHttpClient okHttpClient) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(okHttpClient, "okHttpClient");
        builder.httpEngine(new DefaultHttpEngine(okHttpClient));
        return builder;
    }

    @NotNull
    public static final WebSocketNetworkTransport.Builder okHttpClient(@NotNull WebSocketNetworkTransport.Builder builder, @NotNull OkHttpClient okHttpClient) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(okHttpClient, "okHttpClient");
        builder.webSocketEngine(new DefaultWebSocketEngine(okHttpClient));
        return builder;
    }
}
