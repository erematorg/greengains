package com.apollographql.apollo3;

import com.apollographql.apollo3.api.Adapter;
import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.CustomScalarType;
import com.apollographql.apollo3.api.CustomTypeAdapter;
import com.apollographql.apollo3.api.ExecutionContext;
import com.apollographql.apollo3.api.ExecutionOptions;
import com.apollographql.apollo3.api.MutableExecutionOptions;
import com.apollographql.apollo3.api.Mutation;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.Query;
import com.apollographql.apollo3.api.Subscription;
import com.apollographql.apollo3.api.http.HttpHeader;
import com.apollographql.apollo3.api.http.HttpMethod;
import com.apollographql.apollo3.api.internal.Version2CustomTypeAdapterToAdapter;
import com.apollographql.apollo3.interceptor.ApolloInterceptor;
import com.apollographql.apollo3.interceptor.AutoPersistedQueryInterceptor;
import com.apollographql.apollo3.interceptor.DefaultInterceptorChain;
import com.apollographql.apollo3.interceptor.NetworkInterceptor;
import com.apollographql.apollo3.internal.DispatchersKt;
import com.apollographql.apollo3.network.NetworkTransport;
import com.apollographql.apollo3.network.http.BatchingHttpInterceptor;
import com.apollographql.apollo3.network.http.HttpEngine;
import com.apollographql.apollo3.network.http.HttpInterceptor;
import com.apollographql.apollo3.network.http.HttpNetworkTransport;
import com.apollographql.apollo3.network.ws.WebSocketEngine;
import com.apollographql.apollo3.network.ws.WebSocketNetworkTransport;
import com.apollographql.apollo3.network.ws.WsProtocol;
import com.google.android.gms.actions.SearchIntents;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.flow.Flow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000¸\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 P2\u00020\u00012\u00060\u0002j\u0002`\u0003:\u0002OPB\u0001\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\n\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0015\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0015\u0012\u0006\u0010\u0019\u001a\u00020\u001a¢\u0006\u0002\u0010\u001bJ\b\u00102\u001a\u000203H\u0016J\b\u00104\u001a\u000203H\u0007J*\u00105\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H80706\"\b\b\u0000\u00108*\u0002092\f\u0010:\u001a\b\u0012\u0004\u0012\u0002H80;J9\u00105\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H80706\"\b\b\u0000\u00108*\u0002092\f\u0010:\u001a\b\u0012\u0004\u0012\u0002H80;2\u0006\u0010<\u001a\u00020\u0015H\u0000¢\u0006\u0002\b=J&\u0010>\u001a\b\u0012\u0004\u0012\u0002H80?\"\b\b\u0000\u00108*\u00020@2\f\u0010A\u001a\b\u0012\u0004\u0012\u0002H80BH\u0007J$\u0010A\u001a\b\u0012\u0004\u0012\u0002H80?\"\b\b\u0000\u00108*\u00020@2\f\u0010A\u001a\b\u0012\u0004\u0012\u0002H80BJ\u0006\u0010C\u001a\u00020\u001aJ \u0010D\u001a\u00020E\"\b\b\u0000\u00108*\u0002092\f\u0010F\u001a\b\u0012\u0004\u0012\u0002H80GH\u0007J$\u0010H\u001a\b\u0012\u0004\u0012\u0002H80?\"\b\b\u0000\u00108*\u00020I2\f\u0010H\u001a\b\u0012\u0004\u0012\u0002H80JJ&\u0010K\u001a\b\u0012\u0004\u0012\u0002H80?\"\b\b\u0000\u00108*\u00020L2\f\u0010M\u001a\b\u0012\u0004\u0012\u0002H80NH\u0007J$\u0010M\u001a\b\u0012\u0004\u0012\u0002H80?\"\b\b\u0000\u00108*\u00020L2\f\u0010M\u001a\b\u0012\u0004\u0012\u0002H80NR\u000e\u0010\u0019\u001a\u00020\u001aX\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u0015X\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001f\u001a\u00020 X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u0015X\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b#\u0010\u001dR\u0014\u0010\f\u001a\u00020\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u001c\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b*\u0010'R\u000e\u0010+\u001a\u00020,X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b/\u0010\u001dR\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u0015X\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b0\u0010\u001dR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b1\u0010.¨\u0006Q"}, d2 = {"Lcom/apollographql/apollo3/ApolloClient;", "Lcom/apollographql/apollo3/api/ExecutionOptions;", "Ljava/io/Closeable;", "Lokio/Closeable;", "networkTransport", "Lcom/apollographql/apollo3/network/NetworkTransport;", "customScalarAdapters", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "subscriptionNetworkTransport", "interceptors", "", "Lcom/apollographql/apollo3/interceptor/ApolloInterceptor;", "executionContext", "Lcom/apollographql/apollo3/api/ExecutionContext;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "httpMethod", "Lcom/apollographql/apollo3/api/http/HttpMethod;", "httpHeaders", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "sendApqExtensions", "", "sendDocument", "enableAutoPersistedQueries", "canBeBatched", "builder", "Lcom/apollographql/apollo3/ApolloClient$Builder;", "(Lcom/apollographql/apollo3/network/NetworkTransport;Lcom/apollographql/apollo3/api/CustomScalarAdapters;Lcom/apollographql/apollo3/network/NetworkTransport;Ljava/util/List;Lcom/apollographql/apollo3/api/ExecutionContext;Lkotlinx/coroutines/CoroutineDispatcher;Lcom/apollographql/apollo3/api/http/HttpMethod;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/apollographql/apollo3/ApolloClient$Builder;)V", "getCanBeBatched", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "concurrencyInfo", "Lcom/apollographql/apollo3/ConcurrencyInfo;", "getCustomScalarAdapters", "()Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "getEnableAutoPersistedQueries", "getExecutionContext", "()Lcom/apollographql/apollo3/api/ExecutionContext;", "getHttpHeaders", "()Ljava/util/List;", "getHttpMethod", "()Lcom/apollographql/apollo3/api/http/HttpMethod;", "getInterceptors", "networkInterceptor", "Lcom/apollographql/apollo3/interceptor/NetworkInterceptor;", "getNetworkTransport", "()Lcom/apollographql/apollo3/network/NetworkTransport;", "getSendApqExtensions", "getSendDocument", "getSubscriptionNetworkTransport", "close", "", "dispose", "executeAsFlow", "Lkotlinx/coroutines/flow/Flow;", "Lcom/apollographql/apollo3/api/ApolloResponse;", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "apolloRequest", "Lcom/apollographql/apollo3/api/ApolloRequest;", "ignoreApolloClientHttpHeaders", "executeAsFlow$apollo_runtime", "mutate", "Lcom/apollographql/apollo3/ApolloCall;", "Lcom/apollographql/apollo3/api/Mutation$Data;", "mutation", "Lcom/apollographql/apollo3/api/Mutation;", "newBuilder", "prefetch", "", "operation", "Lcom/apollographql/apollo3/api/Operation;", "query", "Lcom/apollographql/apollo3/api/Query$Data;", "Lcom/apollographql/apollo3/api/Query;", "subscribe", "Lcom/apollographql/apollo3/api/Subscription$Data;", "subscription", "Lcom/apollographql/apollo3/api/Subscription;", "Builder", "Companion", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ApolloClient implements ExecutionOptions, Closeable {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    private final Builder builder;
    @Nullable
    private final Boolean canBeBatched;
    @NotNull
    private final ConcurrencyInfo concurrencyInfo;
    @NotNull
    private final CustomScalarAdapters customScalarAdapters;
    @Nullable
    private final CoroutineDispatcher dispatcher;
    @Nullable
    private final Boolean enableAutoPersistedQueries;
    @NotNull
    private final ExecutionContext executionContext;
    @Nullable
    private final List<HttpHeader> httpHeaders;
    @Nullable
    private final HttpMethod httpMethod;
    @NotNull
    private final List<ApolloInterceptor> interceptors;
    @NotNull
    private final NetworkInterceptor networkInterceptor;
    @NotNull
    private final NetworkTransport networkTransport;
    @Nullable
    private final Boolean sendApqExtensions;
    @Nullable
    private final Boolean sendDocument;
    @NotNull
    private final NetworkTransport subscriptionNetworkTransport;

    @SourceDebugExtension({"SMAP\nApolloClient.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ApolloClient.kt\ncom/apollographql/apollo3/ApolloClient$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,697:1\n1#2:698\n*E\n"})
    @Metadata(d1 = {"\u0000Æ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010O\u001a\u00020\u0000\"\u0004\b\u0000\u0010P2\u0006\u0010Q\u001a\u00020R2\f\u0010S\u001a\b\u0012\u0004\u0012\u0002HP0TJ$\u0010U\u001a\u00020\u0000\"\u0004\b\u0000\u0010P2\u0006\u0010Q\u001a\u00020R2\f\u0010V\u001a\b\u0012\u0004\u0012\u0002HP0WH\u0007J\u0010\u0010X\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0018\u0010Y\u001a\u00020\u00002\u0006\u0010I\u001a\u0002012\u0006\u0010Z\u001a\u000201H\u0016J\u000e\u0010[\u001a\u00020\u00002\u0006\u0010\\\u001a\u00020)J\u000e\u0010]\u001a\u00020\u00002\u0006\u0010^\u001a\u00020\u0005J\u0014\u0010_\u001a\u00020\u00002\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00050!J&\u0010`\u001a\u00020\u00002\b\b\u0002\u0010a\u001a\u00020*2\b\b\u0002\u0010b\u001a\u00020*2\b\b\u0002\u0010c\u001a\u00020\nH\u0007J\u0006\u0010d\u001a\u00020eJ\u0017\u0010\u000b\u001a\u00020\u00002\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0002\u0010fJ\u0006\u0010g\u001a\u00020\u0000J\u000e\u0010h\u001a\u00020\u00002\u0006\u0010h\u001a\u00020iJ\u0010\u0010\u0013\u001a\u00020\u00002\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\u0017\u0010\u0015\u001a\u00020\u00002\b\u0010\u0015\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0002\u0010fJ\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0019J&\u0010j\u001a\u00020\u00002\b\b\u0002\u0010k\u001a\u00020>2\b\b\u0002\u0010l\u001a\u00020m2\b\b\u0002\u0010c\u001a\u00020\nH\u0007J\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u00002\u0006\u0010 \u001a\u00020\nJ\u0018\u0010#\u001a\u00020\u00002\u000e\u0010#\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010!H\u0016J\u0012\u0010+\u001a\u00020\u00002\b\u0010+\u001a\u0004\u0018\u00010*H\u0016J\u000e\u00100\u001a\u00020\u00002\u0006\u00100\u001a\u000201J\u0014\u00102\u001a\u00020\u00002\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00050!J\u000e\u0010n\u001a\u00020\u00002\u0006\u0010n\u001a\u00020\u0007J\u0012\u0010o\u001a\u00020\u00002\b\u0010o\u001a\u0004\u0018\u00010\u0014H\u0007J\u0017\u00104\u001a\u00020\u00002\b\u00104\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0002\u0010fJ\u0017\u00107\u001a\u00020\u00002\b\u00107\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0002\u0010fJ\u000e\u0010p\u001a\u00020\u00002\u0006\u0010p\u001a\u000201J\u000e\u0010:\u001a\u00020\u00002\u0006\u0010:\u001a\u00020\u0007J\u0010\u0010q\u001a\u00020\u00002\u0006\u0010r\u001a\u00020\nH\u0007J\u0010\u0010r\u001a\u00020\u00002\u0006\u0010r\u001a\u00020\nH\u0007J\u000e\u0010;\u001a\u00020\u00002\u0006\u0010;\u001a\u00020<J\u000e\u0010=\u001a\u00020\u00002\u0006\u0010=\u001a\u00020>J\u001e\u0010s\u001a\u00020\u00002\u0014\u0010t\u001a\u0010\u0012\u0004\u0012\u00020G\u0012\u0004\u0012\u00020\n\u0018\u00010AH\u0007JD\u0010E\u001a\u00020\u000027\u0010E\u001a3\b\u0001\u0012\u0004\u0012\u00020G\u0012\u0013\u0012\u00110>¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(J\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0B\u0012\u0006\u0012\u0004\u0018\u00010C0F¢\u0006\u0002\u0010uJ\u000e\u0010L\u001a\u00020\u00002\u0006\u0010L\u001a\u000201J)\u0010L\u001a\u00020\u00002\u001c\u0010L\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002010B\u0012\u0006\u0012\u0004\u0018\u00010C0A¢\u0006\u0002\u0010vJ\u000e\u0010w\u001a\u00020\u00002\u0006\u0010M\u001a\u00020NR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R*\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n@WX\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u0002\n\u0000R*\u0010\u0015\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n@WX\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\u0016\u0010\r\"\u0004\b\u0017\u0010\u000fR\u001a\u0010\u0018\u001a\u00020\u0019X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010 \u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0004\n\u0002\u0010\u0010R4\u0010#\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010!2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010!@WX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u0004X\u0004¢\u0006\u0002\n\u0000R(\u0010+\u001a\u0004\u0018\u00010*2\b\u0010\t\u001a\u0004\u0018\u00010*@WX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u0010\u00100\u001a\u0004\u0018\u000101X\u000e¢\u0006\u0002\n\u0000R\u0017\u00102\u001a\b\u0012\u0004\u0012\u00020\u00050!¢\u0006\b\n\u0000\u001a\u0004\b3\u0010%R*\u00104\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n@WX\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b5\u0010\r\"\u0004\b6\u0010\u000fR*\u00107\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n@WX\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b8\u0010\r\"\u0004\b9\u0010\u000fR\u0010\u0010:\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010;\u001a\u0004\u0018\u00010<X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010=\u001a\u0004\u0018\u00010>X\u000e¢\u0006\u0004\n\u0002\u0010?R(\u0010@\u001a\u001a\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002010B\u0012\u0006\u0012\u0004\u0018\u00010C\u0018\u00010AX\u000e¢\u0006\u0004\n\u0002\u0010DRC\u0010E\u001a5\b\u0001\u0012\u0004\u0012\u00020G\u0012\u0013\u0012\u00110>¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(J\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0B\u0012\u0006\u0012\u0004\u0018\u00010C\u0018\u00010FX\u000e¢\u0006\u0004\n\u0002\u0010KR\u0010\u0010L\u001a\u0004\u0018\u000101X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010M\u001a\u0004\u0018\u00010NX\u000e¢\u0006\u0002\n\u0000¨\u0006x"}, d2 = {"Lcom/apollographql/apollo3/ApolloClient$Builder;", "Lcom/apollographql/apollo3/api/MutableExecutionOptions;", "()V", "_interceptors", "", "Lcom/apollographql/apollo3/interceptor/ApolloInterceptor;", "_networkTransport", "Lcom/apollographql/apollo3/network/NetworkTransport;", "apqInterceptor", "<set-?>", "", "canBeBatched", "getCanBeBatched", "()Ljava/lang/Boolean;", "setCanBeBatched", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "customScalarAdaptersBuilder", "Lcom/apollographql/apollo3/api/CustomScalarAdapters$Builder;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "enableAutoPersistedQueries", "getEnableAutoPersistedQueries", "setEnableAutoPersistedQueries", "executionContext", "Lcom/apollographql/apollo3/api/ExecutionContext;", "getExecutionContext", "()Lcom/apollographql/apollo3/api/ExecutionContext;", "setExecutionContext", "(Lcom/apollographql/apollo3/api/ExecutionContext;)V", "httpEngine", "Lcom/apollographql/apollo3/network/http/HttpEngine;", "httpExposeErrorBody", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "httpHeaders", "getHttpHeaders", "()Ljava/util/List;", "setHttpHeaders", "(Ljava/util/List;)V", "httpInterceptors", "Lcom/apollographql/apollo3/network/http/HttpInterceptor;", "Lcom/apollographql/apollo3/api/http/HttpMethod;", "httpMethod", "getHttpMethod", "()Lcom/apollographql/apollo3/api/http/HttpMethod;", "setHttpMethod", "(Lcom/apollographql/apollo3/api/http/HttpMethod;)V", "httpServerUrl", "", "interceptors", "getInterceptors", "sendApqExtensions", "getSendApqExtensions", "setSendApqExtensions", "sendDocument", "getSendDocument", "setSendDocument", "subscriptionNetworkTransport", "webSocketEngine", "Lcom/apollographql/apollo3/network/ws/WebSocketEngine;", "webSocketIdleTimeoutMillis", "", "Ljava/lang/Long;", "webSocketReopenServerUrl", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/jvm/functions/Function1;", "webSocketReopenWhen", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "attempt", "Lkotlin/jvm/functions/Function3;", "webSocketServerUrl", "wsProtocolFactory", "Lcom/apollographql/apollo3/network/ws/WsProtocol$Factory;", "addCustomScalarAdapter", "T", "customScalarType", "Lcom/apollographql/apollo3/api/CustomScalarType;", "customScalarAdapter", "Lcom/apollographql/apollo3/api/Adapter;", "addCustomTypeAdapter", "customTypeAdapter", "Lcom/apollographql/apollo3/api/CustomTypeAdapter;", "addExecutionContext", "addHttpHeader", "value", "addHttpInterceptor", "httpInterceptor", "addInterceptor", "interceptor", "addInterceptors", "autoPersistedQueries", "httpMethodForHashedQueries", "httpMethodForDocumentQueries", "enableByDefault", "build", "Lcom/apollographql/apollo3/ApolloClient;", "(Ljava/lang/Boolean;)Lcom/apollographql/apollo3/ApolloClient$Builder;", "copy", "customScalarAdapters", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "httpBatching", "batchIntervalMillis", "maxBatchSize", "", "networkTransport", "requestedDispatcher", "serverUrl", "useHttpGetMethodForPersistedQueries", "useHttpGetMethodForQueries", "webSocketReconnectWhen", "reconnectWhen", "(Lkotlin/jvm/functions/Function3;)Lcom/apollographql/apollo3/ApolloClient$Builder;", "(Lkotlin/jvm/functions/Function1;)Lcom/apollographql/apollo3/ApolloClient$Builder;", "wsProtocol", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Builder implements MutableExecutionOptions<Builder> {
        @NotNull
        private final List<ApolloInterceptor> _interceptors;
        @Nullable
        private NetworkTransport _networkTransport;
        @Nullable
        private ApolloInterceptor apqInterceptor;
        @Nullable
        private Boolean canBeBatched;
        @NotNull
        private final CustomScalarAdapters.Builder customScalarAdaptersBuilder = new CustomScalarAdapters.Builder();
        @Nullable
        private CoroutineDispatcher dispatcher;
        @Nullable
        private Boolean enableAutoPersistedQueries;
        @NotNull
        private ExecutionContext executionContext;
        @Nullable
        private HttpEngine httpEngine;
        @Nullable
        private Boolean httpExposeErrorBody;
        @Nullable
        private List<HttpHeader> httpHeaders;
        @NotNull
        private final List<HttpInterceptor> httpInterceptors;
        @Nullable
        private HttpMethod httpMethod;
        @Nullable
        private String httpServerUrl;
        @NotNull
        private final List<ApolloInterceptor> interceptors;
        @Nullable
        private Boolean sendApqExtensions;
        @Nullable
        private Boolean sendDocument;
        @Nullable
        private NetworkTransport subscriptionNetworkTransport;
        @Nullable
        private WebSocketEngine webSocketEngine;
        @Nullable
        private Long webSocketIdleTimeoutMillis;
        @Nullable
        private Function1<? super Continuation<? super String>, ? extends Object> webSocketReopenServerUrl;
        @Nullable
        private Function3<? super Throwable, ? super Long, ? super Continuation<? super Boolean>, ? extends Object> webSocketReopenWhen;
        @Nullable
        private String webSocketServerUrl;
        @Nullable
        private WsProtocol.Factory wsProtocolFactory;

        public Builder() {
            ArrayList arrayList = new ArrayList();
            this._interceptors = arrayList;
            this.interceptors = arrayList;
            this.httpInterceptors = new ArrayList();
            this.executionContext = ExecutionContext.Empty;
            DispatchersKt.failOnNativeIfLegacyMemoryManager();
        }

        public static /* synthetic */ Builder autoPersistedQueries$default(Builder builder, HttpMethod httpMethod2, HttpMethod httpMethod3, boolean z2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                httpMethod2 = HttpMethod.Get;
            }
            if ((i3 & 2) != 0) {
                httpMethod3 = HttpMethod.Post;
            }
            if ((i3 & 4) != 0) {
                z2 = true;
            }
            return builder.autoPersistedQueries(httpMethod2, httpMethod3, z2);
        }

        public static /* synthetic */ Builder httpBatching$default(Builder builder, long j2, int i3, boolean z2, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                j2 = 10;
            }
            if ((i4 & 2) != 0) {
                i3 = 10;
            }
            if ((i4 & 4) != 0) {
                z2 = true;
            }
            return builder.httpBatching(j2, i3, z2);
        }

        @NotNull
        public final <T> Builder addCustomScalarAdapter(@NotNull CustomScalarType customScalarType, @NotNull Adapter<T> adapter) {
            Intrinsics.checkNotNullParameter(customScalarType, "customScalarType");
            Intrinsics.checkNotNullParameter(adapter, "customScalarAdapter");
            this.customScalarAdaptersBuilder.add(customScalarType, adapter);
            return this;
        }

        @NotNull
        @Deprecated(message = "Used for backward compatibility with 2.x", replaceWith = @ReplaceWith(expression = "addCustomScalarAdapter", imports = {}))
        public final <T> Builder addCustomTypeAdapter(@NotNull CustomScalarType customScalarType, @NotNull CustomTypeAdapter<T> customTypeAdapter) {
            Intrinsics.checkNotNullParameter(customScalarType, "customScalarType");
            Intrinsics.checkNotNullParameter(customTypeAdapter, "customTypeAdapter");
            return addCustomScalarAdapter(customScalarType, new Version2CustomTypeAdapterToAdapter(customTypeAdapter));
        }

        @NotNull
        public final Builder addHttpInterceptor(@NotNull HttpInterceptor httpInterceptor) {
            Intrinsics.checkNotNullParameter(httpInterceptor, "httpInterceptor");
            this.httpInterceptors.add(httpInterceptor);
            return this;
        }

        @NotNull
        public final Builder addInterceptor(@NotNull ApolloInterceptor apolloInterceptor) {
            Intrinsics.checkNotNullParameter(apolloInterceptor, "interceptor");
            this._interceptors.add(apolloInterceptor);
            return this;
        }

        @NotNull
        public final Builder addInterceptors(@NotNull List<? extends ApolloInterceptor> list) {
            Intrinsics.checkNotNullParameter(list, "interceptors");
            CollectionsKt__MutableCollectionsKt.addAll(this._interceptors, list);
            return this;
        }

        @NotNull
        @JvmOverloads
        public final Builder autoPersistedQueries() {
            return autoPersistedQueries$default(this, (HttpMethod) null, (HttpMethod) null, false, 7, (Object) null);
        }

        @NotNull
        public final ApolloClient build() {
            NetworkTransport build;
            NetworkTransport networkTransport;
            if (this._networkTransport != null) {
                if (this.httpServerUrl != null) {
                    throw new IllegalStateException("Apollo: 'httpServerUrl' has no effect if 'networkTransport' is set");
                } else if (this.httpEngine != null) {
                    throw new IllegalStateException("Apollo: 'httpEngine' has no effect if 'networkTransport' is set");
                } else if (!this.httpInterceptors.isEmpty()) {
                    throw new IllegalStateException("Apollo: 'addHttpInterceptor' has no effect if 'networkTransport' is set");
                } else if (this.httpExposeErrorBody == null) {
                    build = this._networkTransport;
                    Intrinsics.checkNotNull(build);
                } else {
                    throw new IllegalStateException("Apollo: 'httpExposeErrorBody' has no effect if 'networkTransport' is set");
                }
            } else if (this.httpServerUrl != null) {
                HttpNetworkTransport.Builder builder = new HttpNetworkTransport.Builder();
                String str = this.httpServerUrl;
                Intrinsics.checkNotNull(str);
                HttpNetworkTransport.Builder serverUrl = builder.serverUrl(str);
                HttpEngine httpEngine2 = this.httpEngine;
                if (httpEngine2 != null) {
                    Intrinsics.checkNotNull(httpEngine2);
                    serverUrl.httpEngine(httpEngine2);
                }
                Boolean bool = this.httpExposeErrorBody;
                if (bool != null) {
                    Intrinsics.checkNotNull(bool);
                    serverUrl.exposeErrorBody(bool.booleanValue());
                }
                build = serverUrl.interceptors(this.httpInterceptors).build();
            } else {
                throw new IllegalStateException("Apollo: 'serverUrl' is required");
            }
            NetworkTransport networkTransport2 = build;
            NetworkTransport networkTransport3 = this.subscriptionNetworkTransport;
            if (networkTransport3 == null) {
                String str2 = this.webSocketServerUrl;
                if (str2 == null) {
                    str2 = this.httpServerUrl;
                }
                if (str2 == null) {
                    networkTransport = networkTransport2;
                    return new ApolloClient(networkTransport2, this.customScalarAdaptersBuilder.build(), networkTransport, CollectionsKt.plus(this._interceptors, CollectionsKt.listOfNotNull(this.apqInterceptor)), getExecutionContext(), this.dispatcher, getHttpMethod(), getHttpHeaders(), getSendApqExtensions(), getSendDocument(), getEnableAutoPersistedQueries(), getCanBeBatched(), this, (DefaultConstructorMarker) null);
                }
                WebSocketNetworkTransport.Builder serverUrl2 = new WebSocketNetworkTransport.Builder().serverUrl(str2);
                WebSocketEngine webSocketEngine2 = this.webSocketEngine;
                if (webSocketEngine2 != null) {
                    Intrinsics.checkNotNull(webSocketEngine2);
                    serverUrl2.webSocketEngine(webSocketEngine2);
                }
                Long l2 = this.webSocketIdleTimeoutMillis;
                if (l2 != null) {
                    Intrinsics.checkNotNull(l2);
                    serverUrl2.idleTimeoutMillis(l2.longValue());
                }
                WsProtocol.Factory factory = this.wsProtocolFactory;
                if (factory != null) {
                    Intrinsics.checkNotNull(factory);
                    serverUrl2.protocol(factory);
                }
                Function3<? super Throwable, ? super Long, ? super Continuation<? super Boolean>, ? extends Object> function3 = this.webSocketReopenWhen;
                if (function3 != null) {
                    serverUrl2.reopenWhen(function3);
                }
                Function1<? super Continuation<? super String>, ? extends Object> function1 = this.webSocketReopenServerUrl;
                if (function1 != null) {
                    serverUrl2.serverUrl(function1);
                }
                networkTransport3 = serverUrl2.build();
            } else if (this.webSocketServerUrl != null) {
                throw new IllegalStateException("Apollo: 'webSocketServerUrl' has no effect if 'subscriptionNetworkTransport' is set");
            } else if (this.webSocketEngine != null) {
                throw new IllegalStateException("Apollo: 'webSocketEngine' has no effect if 'subscriptionNetworkTransport' is set");
            } else if (this.webSocketIdleTimeoutMillis != null) {
                throw new IllegalStateException("Apollo: 'webSocketIdleTimeoutMillis' has no effect if 'subscriptionNetworkTransport' is set");
            } else if (this.wsProtocolFactory != null) {
                throw new IllegalStateException("Apollo: 'wsProtocolFactory' has no effect if 'subscriptionNetworkTransport' is set");
            } else if (this.webSocketReopenWhen != null) {
                throw new IllegalStateException("Apollo: 'webSocketReopenWhen' has no effect if 'subscriptionNetworkTransport' is set");
            } else if (this.webSocketReopenServerUrl == null) {
                Intrinsics.checkNotNull(networkTransport3);
            } else {
                throw new IllegalStateException("Apollo: 'webSocketReopenServerUrl' has no effect if 'subscriptionNetworkTransport' is set");
            }
            networkTransport = networkTransport3;
            return new ApolloClient(networkTransport2, this.customScalarAdaptersBuilder.build(), networkTransport, CollectionsKt.plus(this._interceptors, CollectionsKt.listOfNotNull(this.apqInterceptor)), getExecutionContext(), this.dispatcher, getHttpMethod(), getHttpHeaders(), getSendApqExtensions(), getSendDocument(), getEnableAutoPersistedQueries(), getCanBeBatched(), this, (DefaultConstructorMarker) null);
        }

        @NotNull
        public final Builder copy() {
            Builder canBeBatched2 = new Builder().customScalarAdapters(this.customScalarAdaptersBuilder.build()).interceptors(this.interceptors).dispatcher(this.dispatcher).executionContext(getExecutionContext()).httpMethod(getHttpMethod()).httpHeaders(getHttpHeaders()).sendApqExtensions(getSendApqExtensions()).sendDocument(getSendDocument()).enableAutoPersistedQueries(getEnableAutoPersistedQueries()).canBeBatched(getCanBeBatched());
            NetworkTransport networkTransport = this._networkTransport;
            if (networkTransport != null) {
                canBeBatched2.networkTransport(networkTransport);
            }
            String str = this.httpServerUrl;
            if (str != null) {
                canBeBatched2.httpServerUrl(str);
            }
            HttpEngine httpEngine2 = this.httpEngine;
            if (httpEngine2 != null) {
                canBeBatched2.httpEngine(httpEngine2);
            }
            Boolean bool = this.httpExposeErrorBody;
            if (bool != null) {
                canBeBatched2.httpExposeErrorBody(bool.booleanValue());
            }
            for (HttpInterceptor addHttpInterceptor : this.httpInterceptors) {
                canBeBatched2.addHttpInterceptor(addHttpInterceptor);
            }
            NetworkTransport networkTransport2 = this.subscriptionNetworkTransport;
            if (networkTransport2 != null) {
                canBeBatched2.subscriptionNetworkTransport(networkTransport2);
            }
            String str2 = this.webSocketServerUrl;
            if (str2 != null) {
                canBeBatched2.webSocketServerUrl(str2);
            }
            Function1<? super Continuation<? super String>, ? extends Object> function1 = this.webSocketReopenServerUrl;
            if (function1 != null) {
                canBeBatched2.webSocketServerUrl(function1);
            }
            WebSocketEngine webSocketEngine2 = this.webSocketEngine;
            if (webSocketEngine2 != null) {
                canBeBatched2.webSocketEngine(webSocketEngine2);
            }
            Function3<? super Throwable, ? super Long, ? super Continuation<? super Boolean>, ? extends Object> function3 = this.webSocketReopenWhen;
            if (function3 != null) {
                canBeBatched2.webSocketReopenWhen(function3);
            }
            Long l2 = this.webSocketIdleTimeoutMillis;
            if (l2 != null) {
                canBeBatched2.webSocketIdleTimeoutMillis(l2.longValue());
            }
            WsProtocol.Factory factory = this.wsProtocolFactory;
            if (factory != null) {
                canBeBatched2.wsProtocol(factory);
            }
            return canBeBatched2;
        }

        @NotNull
        public final Builder customScalarAdapters(@NotNull CustomScalarAdapters customScalarAdapters) {
            Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
            this.customScalarAdaptersBuilder.clear();
            this.customScalarAdaptersBuilder.addAll(customScalarAdapters);
            return this;
        }

        @NotNull
        public final Builder dispatcher(@Nullable CoroutineDispatcher coroutineDispatcher) {
            this.dispatcher = coroutineDispatcher;
            return this;
        }

        @NotNull
        public final Builder executionContext(@NotNull ExecutionContext executionContext2) {
            Intrinsics.checkNotNullParameter(executionContext2, "executionContext");
            setExecutionContext(executionContext2);
            return this;
        }

        @Nullable
        public Boolean getCanBeBatched() {
            return this.canBeBatched;
        }

        @Nullable
        public Boolean getEnableAutoPersistedQueries() {
            return this.enableAutoPersistedQueries;
        }

        @NotNull
        public ExecutionContext getExecutionContext() {
            return this.executionContext;
        }

        @Nullable
        public List<HttpHeader> getHttpHeaders() {
            return this.httpHeaders;
        }

        @Nullable
        public HttpMethod getHttpMethod() {
            return this.httpMethod;
        }

        @NotNull
        public final List<ApolloInterceptor> getInterceptors() {
            return this.interceptors;
        }

        @Nullable
        public Boolean getSendApqExtensions() {
            return this.sendApqExtensions;
        }

        @Nullable
        public Boolean getSendDocument() {
            return this.sendDocument;
        }

        @NotNull
        @JvmOverloads
        public final Builder httpBatching() {
            return httpBatching$default(this, 0, 0, false, 7, (Object) null);
        }

        @NotNull
        public final Builder httpEngine(@NotNull HttpEngine httpEngine2) {
            Intrinsics.checkNotNullParameter(httpEngine2, "httpEngine");
            this.httpEngine = httpEngine2;
            return this;
        }

        @NotNull
        public final Builder httpExposeErrorBody(boolean z2) {
            this.httpExposeErrorBody = Boolean.valueOf(z2);
            return this;
        }

        @NotNull
        public final Builder httpServerUrl(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "httpServerUrl");
            this.httpServerUrl = str;
            return this;
        }

        @NotNull
        public final Builder interceptors(@NotNull List<? extends ApolloInterceptor> list) {
            Intrinsics.checkNotNullParameter(list, "interceptors");
            this._interceptors.clear();
            CollectionsKt__MutableCollectionsKt.addAll(this._interceptors, list);
            return this;
        }

        @NotNull
        public final Builder networkTransport(@NotNull NetworkTransport networkTransport) {
            Intrinsics.checkNotNullParameter(networkTransport, "networkTransport");
            this._networkTransport = networkTransport;
            return this;
        }

        @NotNull
        @Deprecated(message = "Use dispatcher instead", replaceWith = @ReplaceWith(expression = "dispatcher(requestedDispatcher)", imports = {}))
        public final Builder requestedDispatcher(@Nullable CoroutineDispatcher coroutineDispatcher) {
            dispatcher(coroutineDispatcher);
            return this;
        }

        @NotNull
        public final Builder serverUrl(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "serverUrl");
            this.httpServerUrl = str;
            return this;
        }

        @Deprecated(message = "Use canBeBatched() instead")
        public void setCanBeBatched(@Nullable Boolean bool) {
            this.canBeBatched = bool;
        }

        @Deprecated(message = "Use enableAutoPersistedQueries() instead")
        public void setEnableAutoPersistedQueries(@Nullable Boolean bool) {
            this.enableAutoPersistedQueries = bool;
        }

        public void setExecutionContext(@NotNull ExecutionContext executionContext2) {
            Intrinsics.checkNotNullParameter(executionContext2, "<set-?>");
            this.executionContext = executionContext2;
        }

        @Deprecated(message = "Use httpHeaders() instead")
        public void setHttpHeaders(@Nullable List<HttpHeader> list) {
            this.httpHeaders = list;
        }

        @Deprecated(message = "Use httpMethod() instead")
        public void setHttpMethod(@Nullable HttpMethod httpMethod2) {
            this.httpMethod = httpMethod2;
        }

        @Deprecated(message = "Use sendApqExtensions() instead")
        public void setSendApqExtensions(@Nullable Boolean bool) {
            this.sendApqExtensions = bool;
        }

        @Deprecated(message = "Use sendDocument() instead")
        public void setSendDocument(@Nullable Boolean bool) {
            this.sendDocument = bool;
        }

        @NotNull
        public final Builder subscriptionNetworkTransport(@NotNull NetworkTransport networkTransport) {
            Intrinsics.checkNotNullParameter(networkTransport, "subscriptionNetworkTransport");
            this.subscriptionNetworkTransport = networkTransport;
            return this;
        }

        @NotNull
        @Deprecated(message = "Used for backward compatibility with 2.x. This method throws immediately", replaceWith = @ReplaceWith(expression = "autoPersistedQueries(httpMethodForHashedQueries = HttpMethod.Get)", imports = {"com.apollographql.apollo3.api.http.HttpMethod", "com.apollographql.apollo3.api.http.HttpMethod"}))
        public final Builder useHttpGetMethodForPersistedQueries(boolean z2) {
            throw new NotImplementedError("useHttpGetMethodForPersistedQueries is now configured at the same time as auto persisted queries. Use autoPersistedQueries(httpMethodForHashedQueries = HttpMethod.GET) instead.");
        }

        @NotNull
        @Deprecated(message = "Used for backward compatibility with 2.x", replaceWith = @ReplaceWith(expression = "httpMethod(HttpMethod.Get)", imports = {"com.apollographql.apollo3.api.http.httpMethod", "com.apollographql.apollo3.api.http.HttpMethod"}))
        public final Builder useHttpGetMethodForQueries(boolean z2) {
            return httpMethod(z2 ? HttpMethod.Get : HttpMethod.Post);
        }

        @NotNull
        public final Builder webSocketEngine(@NotNull WebSocketEngine webSocketEngine2) {
            Intrinsics.checkNotNullParameter(webSocketEngine2, "webSocketEngine");
            this.webSocketEngine = webSocketEngine2;
            return this;
        }

        @NotNull
        public final Builder webSocketIdleTimeoutMillis(long j2) {
            this.webSocketIdleTimeoutMillis = Long.valueOf(j2);
            return this;
        }

        @NotNull
        @Deprecated(message = "Use webSocketReopenWhen(webSocketReopenWhen: (suspend (Throwable, attempt: Long) -> Boolean))")
        public final Builder webSocketReconnectWhen(@Nullable Function1<? super Throwable, Boolean> function1) {
            ApolloClient$Builder$webSocketReconnectWhen$1$1$adaptedLambda$1 apolloClient$Builder$webSocketReconnectWhen$1$1$adaptedLambda$1 = null;
            if (function1 != null) {
                apolloClient$Builder$webSocketReconnectWhen$1$1$adaptedLambda$1 = new ApolloClient$Builder$webSocketReconnectWhen$1$1$adaptedLambda$1(function1, (Continuation<? super ApolloClient$Builder$webSocketReconnectWhen$1$1$adaptedLambda$1>) null);
            }
            this.webSocketReopenWhen = apolloClient$Builder$webSocketReconnectWhen$1$1$adaptedLambda$1;
            return this;
        }

        @NotNull
        public final Builder webSocketReopenWhen(@NotNull Function3<? super Throwable, ? super Long, ? super Continuation<? super Boolean>, ? extends Object> function3) {
            Intrinsics.checkNotNullParameter(function3, "webSocketReopenWhen");
            this.webSocketReopenWhen = function3;
            return this;
        }

        @NotNull
        public final Builder webSocketServerUrl(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "webSocketServerUrl");
            this.webSocketServerUrl = str;
            return this;
        }

        @NotNull
        public final Builder wsProtocol(@NotNull WsProtocol.Factory factory) {
            Intrinsics.checkNotNullParameter(factory, "wsProtocolFactory");
            this.wsProtocolFactory = factory;
            return this;
        }

        @NotNull
        public Builder addExecutionContext(@NotNull ExecutionContext executionContext2) {
            Intrinsics.checkNotNullParameter(executionContext2, "executionContext");
            setExecutionContext(getExecutionContext().plus(executionContext2));
            return this;
        }

        @NotNull
        public Builder addHttpHeader(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(str2, "value");
            List<HttpHeader> httpHeaders2 = getHttpHeaders();
            if (httpHeaders2 == null) {
                httpHeaders2 = CollectionsKt.emptyList();
            }
            setHttpHeaders(CollectionsKt.plus(httpHeaders2, new HttpHeader(str, str2)));
            return this;
        }

        @NotNull
        @JvmOverloads
        public final Builder autoPersistedQueries(@NotNull HttpMethod httpMethod2) {
            Intrinsics.checkNotNullParameter(httpMethod2, "httpMethodForHashedQueries");
            return autoPersistedQueries$default(this, httpMethod2, (HttpMethod) null, false, 6, (Object) null);
        }

        @NotNull
        public Builder canBeBatched(@Nullable Boolean bool) {
            setCanBeBatched(bool);
            return this;
        }

        @NotNull
        public Builder enableAutoPersistedQueries(@Nullable Boolean bool) {
            setEnableAutoPersistedQueries(bool);
            return this;
        }

        @NotNull
        @JvmOverloads
        public final Builder httpBatching(long j2) {
            return httpBatching$default(this, j2, 0, false, 6, (Object) null);
        }

        @NotNull
        public Builder httpHeaders(@Nullable List<HttpHeader> list) {
            setHttpHeaders(list);
            return this;
        }

        @NotNull
        public Builder httpMethod(@Nullable HttpMethod httpMethod2) {
            setHttpMethod(httpMethod2);
            return this;
        }

        @NotNull
        public Builder sendApqExtensions(@Nullable Boolean bool) {
            setSendApqExtensions(bool);
            return this;
        }

        @NotNull
        public Builder sendDocument(@Nullable Boolean bool) {
            setSendDocument(bool);
            return this;
        }

        @NotNull
        public final Builder webSocketServerUrl(@NotNull Function1<? super Continuation<? super String>, ? extends Object> function1) {
            Intrinsics.checkNotNullParameter(function1, "webSocketServerUrl");
            this.webSocketReopenServerUrl = function1;
            return this;
        }

        @NotNull
        @JvmOverloads
        public final Builder autoPersistedQueries(@NotNull HttpMethod httpMethod2, @NotNull HttpMethod httpMethod3) {
            Intrinsics.checkNotNullParameter(httpMethod2, "httpMethodForHashedQueries");
            Intrinsics.checkNotNullParameter(httpMethod3, "httpMethodForDocumentQueries");
            return autoPersistedQueries$default(this, httpMethod2, httpMethod3, false, 4, (Object) null);
        }

        @NotNull
        @JvmOverloads
        public final Builder httpBatching(long j2, int i3) {
            return httpBatching$default(this, j2, i3, false, 4, (Object) null);
        }

        @NotNull
        @JvmOverloads
        public final Builder autoPersistedQueries(@NotNull HttpMethod httpMethod2, @NotNull HttpMethod httpMethod3, boolean z2) {
            Intrinsics.checkNotNullParameter(httpMethod2, "httpMethodForHashedQueries");
            Intrinsics.checkNotNullParameter(httpMethod3, "httpMethodForDocumentQueries");
            this.apqInterceptor = new AutoPersistedQueryInterceptor(httpMethod2, httpMethod3);
            enableAutoPersistedQueries(Boolean.valueOf(z2));
            return this;
        }

        @NotNull
        @JvmOverloads
        public final Builder httpBatching(long j2, int i3, boolean z2) {
            addHttpInterceptor(new BatchingHttpInterceptor(j2, i3, false, 4, (DefaultConstructorMarker) null));
            canBeBatched(Boolean.valueOf(z2));
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lcom/apollographql/apollo3/ApolloClient$Companion;", "", "()V", "builder", "Lcom/apollographql/apollo3/ApolloClient$Builder;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        @Deprecated(message = "Used for backward compatibility with 2.x", replaceWith = @ReplaceWith(expression = "ApolloClient.Builder()", imports = {}))
        public final Builder builder() {
            return new Builder();
        }

        private Companion() {
        }
    }

    public /* synthetic */ ApolloClient(NetworkTransport networkTransport2, CustomScalarAdapters customScalarAdapters2, NetworkTransport networkTransport3, List list, ExecutionContext executionContext2, CoroutineDispatcher coroutineDispatcher, HttpMethod httpMethod2, List list2, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Builder builder2, DefaultConstructorMarker defaultConstructorMarker) {
        this(networkTransport2, customScalarAdapters2, networkTransport3, list, executionContext2, coroutineDispatcher, httpMethod2, list2, bool, bool2, bool3, bool4, builder2);
    }

    @JvmStatic
    @NotNull
    @Deprecated(message = "Used for backward compatibility with 2.x", replaceWith = @ReplaceWith(expression = "ApolloClient.Builder()", imports = {}))
    public static final Builder builder() {
        return Companion.builder();
    }

    public void close() {
        CoroutineScopeKt.cancel$default(this.concurrencyInfo.getCoroutineScope(), (CancellationException) null, 1, (Object) null);
        this.networkTransport.dispose();
        this.subscriptionNetworkTransport.dispose();
    }

    @Deprecated(message = "Use close() instead or call okio.use { }", replaceWith = @ReplaceWith(expression = "close()", imports = {}))
    public final void dispose() {
        close();
    }

    @NotNull
    public final <D extends Operation.Data> Flow<ApolloResponse<D>> executeAsFlow(@NotNull ApolloRequest<D> apolloRequest) {
        Intrinsics.checkNotNullParameter(apolloRequest, "apolloRequest");
        return executeAsFlow$apollo_runtime(apolloRequest, true);
    }

    @NotNull
    public final <D extends Operation.Data> Flow<ApolloResponse<D>> executeAsFlow$apollo_runtime(@NotNull ApolloRequest<D> apolloRequest, boolean z2) {
        List<HttpHeader> list;
        Intrinsics.checkNotNullParameter(apolloRequest, "apolloRequest");
        ApolloRequest.Builder enableAutoPersistedQueries2 = new ApolloRequest.Builder(apolloRequest.getOperation()).addExecutionContext((ExecutionContext) this.concurrencyInfo).addExecutionContext((ExecutionContext) this.customScalarAdapters).addExecutionContext(this.concurrencyInfo.plus(this.customScalarAdapters).plus(getExecutionContext()).plus(apolloRequest.getExecutionContext())).addExecutionContext(apolloRequest.getExecutionContext()).httpMethod(getHttpMethod()).sendApqExtensions(getSendApqExtensions()).sendDocument(getSendDocument()).enableAutoPersistedQueries(getEnableAutoPersistedQueries());
        if (apolloRequest.getHttpHeaders() == null) {
            list = getHttpHeaders();
        } else if (z2) {
            list = apolloRequest.getHttpHeaders();
        } else {
            List<HttpHeader> httpHeaders2 = getHttpHeaders();
            if (httpHeaders2 == null) {
                httpHeaders2 = CollectionsKt.emptyList();
            }
            List<HttpHeader> httpHeaders3 = apolloRequest.getHttpHeaders();
            Intrinsics.checkNotNull(httpHeaders3);
            list = CollectionsKt.plus(httpHeaders2, httpHeaders3);
        }
        ApolloRequest.Builder<D> httpHeaders4 = enableAutoPersistedQueries2.httpHeaders(list);
        if (apolloRequest.getHttpMethod() != null) {
            httpHeaders4.httpMethod(apolloRequest.getHttpMethod());
        }
        if (apolloRequest.getSendApqExtensions() != null) {
            httpHeaders4.sendApqExtensions(apolloRequest.getSendApqExtensions());
        }
        if (apolloRequest.getSendDocument() != null) {
            httpHeaders4.sendDocument(apolloRequest.getSendDocument());
        }
        if (apolloRequest.getEnableAutoPersistedQueries() != null) {
            httpHeaders4.enableAutoPersistedQueries(apolloRequest.getEnableAutoPersistedQueries());
        }
        if (apolloRequest.getCanBeBatched() != null) {
            httpHeaders4.addHttpHeader("X-APOLLO-CAN-BE-BATCHED", String.valueOf(apolloRequest.getCanBeBatched()));
        }
        return new DefaultInterceptorChain(CollectionsKt.plus(this.interceptors, this.networkInterceptor), 0).proceed(httpHeaders4.build());
    }

    @Nullable
    public Boolean getCanBeBatched() {
        return this.canBeBatched;
    }

    @NotNull
    public final CustomScalarAdapters getCustomScalarAdapters() {
        return this.customScalarAdapters;
    }

    @Nullable
    public Boolean getEnableAutoPersistedQueries() {
        return this.enableAutoPersistedQueries;
    }

    @NotNull
    public ExecutionContext getExecutionContext() {
        return this.executionContext;
    }

    @Nullable
    public List<HttpHeader> getHttpHeaders() {
        return this.httpHeaders;
    }

    @Nullable
    public HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    @NotNull
    public final List<ApolloInterceptor> getInterceptors() {
        return this.interceptors;
    }

    @NotNull
    public final NetworkTransport getNetworkTransport() {
        return this.networkTransport;
    }

    @Nullable
    public Boolean getSendApqExtensions() {
        return this.sendApqExtensions;
    }

    @Nullable
    public Boolean getSendDocument() {
        return this.sendDocument;
    }

    @NotNull
    public final NetworkTransport getSubscriptionNetworkTransport() {
        return this.subscriptionNetworkTransport;
    }

    @NotNull
    @Deprecated(message = "Used for backward compatibility with 2.x", replaceWith = @ReplaceWith(expression = "mutation(mutation)", imports = {}))
    public final <D extends Mutation.Data> ApolloCall<D> mutate(@NotNull Mutation<D> mutation) {
        Intrinsics.checkNotNullParameter(mutation, "mutation");
        return mutation(mutation);
    }

    @NotNull
    public final <D extends Mutation.Data> ApolloCall<D> mutation(@NotNull Mutation<D> mutation) {
        Intrinsics.checkNotNullParameter(mutation, "mutation");
        return new ApolloCall<>(this, mutation);
    }

    @NotNull
    public final Builder newBuilder() {
        return this.builder.copy();
    }

    @NotNull
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use a query and ignore the result")
    public final <D extends Operation.Data> Void prefetch(@NotNull Operation<D> operation) {
        Intrinsics.checkNotNullParameter(operation, "operation");
        throw new NotImplementedError((String) null, 1, (DefaultConstructorMarker) null);
    }

    @NotNull
    public final <D extends Query.Data> ApolloCall<D> query(@NotNull Query<D> query) {
        Intrinsics.checkNotNullParameter(query, SearchIntents.EXTRA_QUERY);
        return new ApolloCall<>(this, query);
    }

    @NotNull
    @Deprecated(message = "Used for backward compatibility with 2.x", replaceWith = @ReplaceWith(expression = "subscription(subscription)", imports = {}))
    public final <D extends Subscription.Data> ApolloCall<D> subscribe(@NotNull Subscription<D> subscription) {
        Intrinsics.checkNotNullParameter(subscription, "subscription");
        return subscription(subscription);
    }

    @NotNull
    public final <D extends Subscription.Data> ApolloCall<D> subscription(@NotNull Subscription<D> subscription) {
        Intrinsics.checkNotNullParameter(subscription, "subscription");
        return new ApolloCall<>(this, subscription);
    }

    private ApolloClient(NetworkTransport networkTransport2, CustomScalarAdapters customScalarAdapters2, NetworkTransport networkTransport3, List<? extends ApolloInterceptor> list, ExecutionContext executionContext2, CoroutineDispatcher coroutineDispatcher, HttpMethod httpMethod2, List<HttpHeader> list2, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Builder builder2) {
        this.networkTransport = networkTransport2;
        this.customScalarAdapters = customScalarAdapters2;
        this.subscriptionNetworkTransport = networkTransport3;
        this.interceptors = list;
        this.executionContext = executionContext2;
        this.dispatcher = coroutineDispatcher;
        this.httpMethod = httpMethod2;
        this.httpHeaders = list2;
        this.sendApqExtensions = bool;
        this.sendDocument = bool2;
        this.enableAutoPersistedQueries = bool3;
        this.canBeBatched = bool4;
        this.builder = builder2;
        coroutineDispatcher = coroutineDispatcher == null ? DispatchersKt.getDefaultDispatcher() : coroutineDispatcher;
        ConcurrencyInfo concurrencyInfo2 = new ConcurrencyInfo(coroutineDispatcher, CoroutineScopeKt.CoroutineScope(coroutineDispatcher));
        this.concurrencyInfo = concurrencyInfo2;
        this.networkInterceptor = new NetworkInterceptor(networkTransport2, networkTransport3, concurrencyInfo2.getDispatcher());
    }
}
