package com.apollographql.apollo3.network.http;

import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.ExecutionContext;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.Operations;
import com.apollographql.apollo3.api.http.DefaultHttpRequestComposer;
import com.apollographql.apollo3.api.http.HttpHeader;
import com.apollographql.apollo3.api.http.HttpRequest;
import com.apollographql.apollo3.api.http.HttpRequestComposer;
import com.apollographql.apollo3.api.http.HttpResponse;
import com.apollographql.apollo3.api.json.JsonReaders;
import com.apollographql.apollo3.exception.ApolloException;
import com.apollographql.apollo3.exception.ApolloParseException;
import com.apollographql.apollo3.internal.MultipartKt;
import com.apollographql.apollo3.mpp.UtilsKt;
import com.apollographql.apollo3.network.NetworkTransport;
import com.apollographql.apollo3.network.http.HttpInterceptor;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u0000 02\u00020\u0001:\u0003/01B-\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u0010\u0014\u001a\u00020\u0015H\u0016J,\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00190\u00180\u0017\"\b\b\u0000\u0010\u0019*\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00190\u001cH\u0016J:\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00190\u00180\u0017\"\b\b\u0000\u0010\u0019*\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00190\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J<\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00190\u00180\u0017\"\b\b\u0000\u0010\u0019*\u00020\u001a2\f\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u00190#2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010$\u001a\u00020%H\u0002J\u0006\u0010&\u001a\u00020'J6\u0010(\u001a\b\u0012\u0004\u0012\u0002H\u00190\u0018\"\b\b\u0000\u0010\u0019*\u00020\u001a2\f\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u00190#2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010$\u001a\u00020%H\u0002J>\u0010)\u001a\b\u0012\u0004\u0012\u0002H\u00190\u0018\"\b\b\u0000\u0010\u0019*\u00020\u001a*\b\u0012\u0004\u0012\u0002H\u00190\u00182\n\u0010*\u001a\u00060+j\u0002`,2\u0006\u0010$\u001a\u00020%2\u0006\u0010-\u001a\u00020.H\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00060\u000fR\u00020\u0000X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u00062"}, d2 = {"Lcom/apollographql/apollo3/network/http/HttpNetworkTransport;", "Lcom/apollographql/apollo3/network/NetworkTransport;", "httpRequestComposer", "Lcom/apollographql/apollo3/api/http/HttpRequestComposer;", "engine", "Lcom/apollographql/apollo3/network/http/HttpEngine;", "interceptors", "", "Lcom/apollographql/apollo3/network/http/HttpInterceptor;", "exposeErrorBody", "", "(Lcom/apollographql/apollo3/api/http/HttpRequestComposer;Lcom/apollographql/apollo3/network/http/HttpEngine;Ljava/util/List;Z)V", "getEngine", "()Lcom/apollographql/apollo3/network/http/HttpEngine;", "engineInterceptor", "Lcom/apollographql/apollo3/network/http/HttpNetworkTransport$EngineInterceptor;", "getExposeErrorBody", "()Z", "getInterceptors", "()Ljava/util/List;", "dispose", "", "execute", "Lkotlinx/coroutines/flow/Flow;", "Lcom/apollographql/apollo3/api/ApolloResponse;", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "request", "Lcom/apollographql/apollo3/api/ApolloRequest;", "httpRequest", "Lcom/apollographql/apollo3/api/http/HttpRequest;", "customScalarAdapters", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "multipleResponses", "operation", "Lcom/apollographql/apollo3/api/Operation;", "httpResponse", "Lcom/apollographql/apollo3/api/http/HttpResponse;", "newBuilder", "Lcom/apollographql/apollo3/network/http/HttpNetworkTransport$Builder;", "singleResponse", "withHttpInfo", "requestUuid", "Ljava/util/UUID;", "Lcom/benasher44/uuid/Uuid;", "millisStart", "", "Builder", "Companion", "EngineInterceptor", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nHttpNetworkTransport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpNetworkTransport.kt\ncom/apollographql/apollo3/network/http/HttpNetworkTransport\n+ 2 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 3 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 4 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,316:1\n54#2:317\n57#2:321\n50#3:318\n55#3:320\n106#4:319\n1855#5,2:322\n*S KotlinDebug\n*F\n+ 1 HttpNetworkTransport.kt\ncom/apollographql/apollo3/network/http/HttpNetworkTransport\n*L\n122#1:317\n122#1:321\n122#1:318\n122#1:320\n122#1:319\n216#1:322,2\n*E\n"})
public final class HttpNetworkTransport implements NetworkTransport {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    private final HttpEngine engine;
    /* access modifiers changed from: private */
    @NotNull
    public final EngineInterceptor engineInterceptor;
    private final boolean exposeErrorBody;
    @NotNull
    private final HttpRequestComposer httpRequestComposer;
    @NotNull
    private final List<HttpInterceptor> interceptors;

    @SourceDebugExtension({"SMAP\nHttpNetworkTransport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpNetworkTransport.kt\ncom/apollographql/apollo3/network/http/HttpNetworkTransport$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,316:1\n1#2:317\n*E\n"})
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u000bJ\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0004J\u0014\u0010\u0013\u001a\u00020\u00002\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u0014\u0010\t\u001a\u00020\u00002\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0015J\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\rR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/apollographql/apollo3/network/http/HttpNetworkTransport$Builder;", "", "()V", "engine", "Lcom/apollographql/apollo3/network/http/HttpEngine;", "exposeErrorBody", "", "httpRequestComposer", "Lcom/apollographql/apollo3/api/http/HttpRequestComposer;", "interceptors", "", "Lcom/apollographql/apollo3/network/http/HttpInterceptor;", "serverUrl", "", "addInterceptor", "interceptor", "build", "Lcom/apollographql/apollo3/network/http/HttpNetworkTransport;", "httpEngine", "httpHeaders", "headers", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Builder {
        @Nullable
        private HttpEngine engine;
        private boolean exposeErrorBody;
        @Nullable
        private HttpRequestComposer httpRequestComposer;
        @NotNull
        private final List<HttpInterceptor> interceptors = new ArrayList();
        @Nullable
        private String serverUrl;

        @NotNull
        public final Builder addInterceptor(@NotNull HttpInterceptor httpInterceptor) {
            Intrinsics.checkNotNullParameter(httpInterceptor, "interceptor");
            this.interceptors.add(httpInterceptor);
            return this;
        }

        @NotNull
        public final HttpNetworkTransport build() {
            HttpRequestComposer httpRequestComposer2 = this.httpRequestComposer;
            if (httpRequestComposer2 == null || this.serverUrl == null) {
                if (httpRequestComposer2 == null) {
                    String str = this.serverUrl;
                    httpRequestComposer2 = str != null ? new DefaultHttpRequestComposer(str) : null;
                    if (httpRequestComposer2 == null) {
                        throw new IllegalStateException("No HttpRequestComposer found. Use 'httpRequestComposer' or 'serverUrl'");
                    }
                }
                HttpRequestComposer httpRequestComposer3 = httpRequestComposer2;
                HttpEngine httpEngine = this.engine;
                if (httpEngine == null) {
                    httpEngine = new DefaultHttpEngine(0, 1, (DefaultConstructorMarker) null);
                }
                return new HttpNetworkTransport(httpRequestComposer3, httpEngine, this.interceptors, this.exposeErrorBody, (DefaultConstructorMarker) null);
            }
            throw new IllegalStateException("It is an error to set both 'httpRequestComposer' and 'serverUrl'");
        }

        @NotNull
        public final Builder exposeErrorBody(boolean z2) {
            this.exposeErrorBody = z2;
            return this;
        }

        @NotNull
        public final Builder httpEngine(@NotNull HttpEngine httpEngine) {
            Intrinsics.checkNotNullParameter(httpEngine, "httpEngine");
            this.engine = httpEngine;
            return this;
        }

        @NotNull
        public final Builder httpHeaders(@NotNull List<HttpHeader> list) {
            Intrinsics.checkNotNullParameter(list, "headers");
            this.interceptors.add(new HeadersInterceptor(list));
            return this;
        }

        @NotNull
        public final Builder httpRequestComposer(@NotNull HttpRequestComposer httpRequestComposer2) {
            Intrinsics.checkNotNullParameter(httpRequestComposer2, "httpRequestComposer");
            this.httpRequestComposer = httpRequestComposer2;
            return this;
        }

        @NotNull
        public final Builder interceptors(@NotNull List<? extends HttpInterceptor> list) {
            Intrinsics.checkNotNullParameter(list, "interceptors");
            this.interceptors.clear();
            this.interceptors.addAll(list);
            return this;
        }

        @NotNull
        public final Builder serverUrl(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "serverUrl");
            this.serverUrl = str;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\b"}, d2 = {"Lcom/apollographql/apollo3/network/http/HttpNetworkTransport$Companion;", "", "()V", "wrapThrowableIfNeeded", "Lcom/apollographql/apollo3/exception/ApolloException;", "throwable", "", "Kind", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/apollographql/apollo3/network/http/HttpNetworkTransport$Companion$Kind;", "", "(Ljava/lang/String;I)V", "EMPTY", "PAYLOAD", "OTHER", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
        public enum Kind {
            EMPTY,
            PAYLOAD,
            OTHER
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final ApolloException wrapThrowableIfNeeded(Throwable th) {
            return th instanceof ApolloException ? (ApolloException) th : new ApolloParseException("Failed to parse GraphQL http network response", th);
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH@¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/apollographql/apollo3/network/http/HttpNetworkTransport$EngineInterceptor;", "Lcom/apollographql/apollo3/network/http/HttpInterceptor;", "(Lcom/apollographql/apollo3/network/http/HttpNetworkTransport;)V", "intercept", "Lcom/apollographql/apollo3/api/http/HttpResponse;", "request", "Lcom/apollographql/apollo3/api/http/HttpRequest;", "chain", "Lcom/apollographql/apollo3/network/http/HttpInterceptorChain;", "(Lcom/apollographql/apollo3/api/http/HttpRequest;Lcom/apollographql/apollo3/network/http/HttpInterceptorChain;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public final class EngineInterceptor implements HttpInterceptor {
        public EngineInterceptor() {
        }

        public void dispose() {
            HttpInterceptor.DefaultImpls.dispose(this);
        }

        @Nullable
        public Object intercept(@NotNull HttpRequest httpRequest, @NotNull HttpInterceptorChain httpInterceptorChain, @NotNull Continuation<? super HttpResponse> continuation) {
            return HttpNetworkTransport.this.getEngine().execute(httpRequest, continuation);
        }
    }

    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                com.apollographql.apollo3.network.http.HttpNetworkTransport$Companion$Kind[] r0 = com.apollographql.apollo3.network.http.HttpNetworkTransport.Companion.Kind.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.apollographql.apollo3.network.http.HttpNetworkTransport$Companion$Kind r1 = com.apollographql.apollo3.network.http.HttpNetworkTransport.Companion.Kind.EMPTY     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.apollographql.apollo3.network.http.HttpNetworkTransport$Companion$Kind r1 = com.apollographql.apollo3.network.http.HttpNetworkTransport.Companion.Kind.PAYLOAD     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.apollographql.apollo3.network.http.HttpNetworkTransport$Companion$Kind r1 = com.apollographql.apollo3.network.http.HttpNetworkTransport.Companion.Kind.OTHER     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.http.HttpNetworkTransport.WhenMappings.<clinit>():void");
        }
    }

    public /* synthetic */ HttpNetworkTransport(HttpRequestComposer httpRequestComposer2, HttpEngine httpEngine, List list, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
        this(httpRequestComposer2, httpEngine, list, z2);
    }

    /* access modifiers changed from: private */
    public final <D extends Operation.Data> Flow<ApolloResponse<D>> multipleResponses(Operation<D> operation, CustomScalarAdapters customScalarAdapters, HttpResponse httpResponse) {
        return FlowKt.m10511catch(new HttpNetworkTransport$multipleResponses$$inlined$mapNotNull$1(MultipartKt.multipartBodyFlow(httpResponse), operation, customScalarAdapters, new Ref.ObjectRef()), new HttpNetworkTransport$multipleResponses$2((Continuation<? super HttpNetworkTransport$multipleResponses$2>) null));
    }

    /* access modifiers changed from: private */
    public final <D extends Operation.Data> ApolloResponse<D> singleResponse(Operation<D> operation, CustomScalarAdapters customScalarAdapters, HttpResponse httpResponse) {
        try {
            BufferedSource body = httpResponse.getBody();
            Intrinsics.checkNotNull(body);
            return Operations.parseJsonResponse(operation, JsonReaders.jsonReader(body), customScalarAdapters).newBuilder().isLast(true).build();
        } catch (Exception e3) {
            throw Companion.wrapThrowableIfNeeded(e3);
        }
    }

    /* access modifiers changed from: private */
    public final <D extends Operation.Data> ApolloResponse<D> withHttpInfo(ApolloResponse<D> apolloResponse, UUID uuid, HttpResponse httpResponse, long j2) {
        return apolloResponse.newBuilder().requestUuid(uuid).addExecutionContext(new HttpInfo(j2, UtilsKt.currentTimeMillis(), httpResponse.getStatusCode(), httpResponse.getHeaders())).build();
    }

    public void dispose() {
        for (HttpInterceptor dispose : this.interceptors) {
            dispose.dispose();
        }
        this.engine.dispose();
    }

    @NotNull
    public <D extends Operation.Data> Flow<ApolloResponse<D>> execute(@NotNull ApolloRequest<D> apolloRequest) {
        Intrinsics.checkNotNullParameter(apolloRequest, "request");
        ExecutionContext.Element element = apolloRequest.getExecutionContext().get(CustomScalarAdapters.Key);
        Intrinsics.checkNotNull(element);
        return execute(apolloRequest, this.httpRequestComposer.compose(apolloRequest), (CustomScalarAdapters) element);
    }

    @NotNull
    public final HttpEngine getEngine() {
        return this.engine;
    }

    public final boolean getExposeErrorBody() {
        return this.exposeErrorBody;
    }

    @NotNull
    public final List<HttpInterceptor> getInterceptors() {
        return this.interceptors;
    }

    @NotNull
    public final Builder newBuilder() {
        return new Builder().httpEngine(this.engine).interceptors(this.interceptors).httpRequestComposer(this.httpRequestComposer);
    }

    private HttpNetworkTransport(HttpRequestComposer httpRequestComposer2, HttpEngine httpEngine, List<? extends HttpInterceptor> list, boolean z2) {
        this.httpRequestComposer = httpRequestComposer2;
        this.engine = httpEngine;
        this.interceptors = list;
        this.exposeErrorBody = z2;
        this.engineInterceptor = new EngineInterceptor();
    }

    @NotNull
    public final <D extends Operation.Data> Flow<ApolloResponse<D>> execute(@NotNull ApolloRequest<D> apolloRequest, @NotNull HttpRequest httpRequest, @NotNull CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(apolloRequest, "request");
        Intrinsics.checkNotNullParameter(httpRequest, "httpRequest");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        return FlowKt.flow(new HttpNetworkTransport$execute$1(this, httpRequest, apolloRequest, customScalarAdapters, (Continuation<? super HttpNetworkTransport$execute$1>) null));
    }
}
