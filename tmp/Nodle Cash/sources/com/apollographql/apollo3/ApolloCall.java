package com.apollographql.apollo3;

import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.ExecutionContext;
import com.apollographql.apollo3.api.MutableExecutionOptions;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.Operation.Data;
import com.apollographql.apollo3.api.http.HttpHeader;
import com.apollographql.apollo3.api.http.HttpMethod;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00000\u0003B\u001d\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\bJ\u0016\u00102\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0017\u001a\u00020\u0016H\u0016J\u001e\u00103\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u000205H\u0016J\u001d\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0002\u00107J\f\u00108\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000J\u001d\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\u0010\u0013\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0002\u00107J\u0014\u00109\u001a\b\u0012\u0004\u0012\u00028\u00000:H@¢\u0006\u0002\u0010;J\u001e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001cH\u0016J\u0018\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\u0010$\u001a\u0004\u0018\u00010#H\u0016J\u001d\u0010)\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\u0010)\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0002\u00107J\u001d\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\u0010,\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0002\u00107J\u001d\u0010/\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\u0010/\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0002\u00107J\u0012\u0010<\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000:0=R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR*\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f@WX\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R*\u0010\u0013\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f@WX\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R$\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\u0016@WX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR4\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c@WX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R(\u0010$\u001a\u0004\u0018\u00010#2\b\u0010\u000b\u001a\u0004\u0018\u00010#@WX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u0012\u0010)\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0004\n\u0002\u0010\u0012R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R*\u0010,\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f@WX\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b-\u0010\u000f\"\u0004\b.\u0010\u0011R*\u0010/\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f@WX\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b0\u0010\u000f\"\u0004\b1\u0010\u0011¨\u0006>"}, d2 = {"Lcom/apollographql/apollo3/ApolloCall;", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "Lcom/apollographql/apollo3/api/MutableExecutionOptions;", "apolloClient", "Lcom/apollographql/apollo3/ApolloClient;", "operation", "Lcom/apollographql/apollo3/api/Operation;", "(Lcom/apollographql/apollo3/ApolloClient;Lcom/apollographql/apollo3/api/Operation;)V", "getApolloClient$apollo_runtime", "()Lcom/apollographql/apollo3/ApolloClient;", "<set-?>", "", "canBeBatched", "getCanBeBatched", "()Ljava/lang/Boolean;", "setCanBeBatched", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "enableAutoPersistedQueries", "getEnableAutoPersistedQueries", "setEnableAutoPersistedQueries", "Lcom/apollographql/apollo3/api/ExecutionContext;", "executionContext", "getExecutionContext", "()Lcom/apollographql/apollo3/api/ExecutionContext;", "setExecutionContext", "(Lcom/apollographql/apollo3/api/ExecutionContext;)V", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "httpHeaders", "getHttpHeaders", "()Ljava/util/List;", "setHttpHeaders", "(Ljava/util/List;)V", "Lcom/apollographql/apollo3/api/http/HttpMethod;", "httpMethod", "getHttpMethod", "()Lcom/apollographql/apollo3/api/http/HttpMethod;", "setHttpMethod", "(Lcom/apollographql/apollo3/api/http/HttpMethod;)V", "ignoreApolloClientHttpHeaders", "getOperation", "()Lcom/apollographql/apollo3/api/Operation;", "sendApqExtensions", "getSendApqExtensions", "setSendApqExtensions", "sendDocument", "getSendDocument", "setSendDocument", "addExecutionContext", "addHttpHeader", "name", "", "value", "(Ljava/lang/Boolean;)Lcom/apollographql/apollo3/ApolloCall;", "copy", "execute", "Lcom/apollographql/apollo3/api/ApolloResponse;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toFlow", "Lkotlinx/coroutines/flow/Flow;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ApolloCall<D extends Operation.Data> implements MutableExecutionOptions<ApolloCall<D>> {
    @NotNull
    private final ApolloClient apolloClient;
    @Nullable
    private Boolean canBeBatched;
    @Nullable
    private Boolean enableAutoPersistedQueries;
    @NotNull
    private ExecutionContext executionContext = ExecutionContext.Empty;
    @Nullable
    private List<HttpHeader> httpHeaders;
    @Nullable
    private HttpMethod httpMethod;
    @Nullable
    private Boolean ignoreApolloClientHttpHeaders;
    @NotNull
    private final Operation<D> operation;
    @Nullable
    private Boolean sendApqExtensions;
    @Nullable
    private Boolean sendDocument;

    public ApolloCall(@NotNull ApolloClient apolloClient2, @NotNull Operation<D> operation2) {
        Intrinsics.checkNotNullParameter(apolloClient2, "apolloClient");
        Intrinsics.checkNotNullParameter(operation2, "operation");
        this.apolloClient = apolloClient2;
        this.operation = operation2;
    }

    private final ApolloCall<D> ignoreApolloClientHttpHeaders(Boolean bool) {
        this.ignoreApolloClientHttpHeaders = bool;
        return this;
    }

    @NotNull
    public final ApolloCall<D> copy() {
        return new ApolloCall(this.apolloClient, this.operation).addExecutionContext(getExecutionContext()).httpMethod(getHttpMethod()).httpHeaders(getHttpHeaders()).ignoreApolloClientHttpHeaders(this.ignoreApolloClientHttpHeaders).sendApqExtensions(getSendApqExtensions()).sendDocument(getSendDocument()).enableAutoPersistedQueries(getEnableAutoPersistedQueries()).canBeBatched(getCanBeBatched());
    }

    @Nullable
    public final Object execute(@NotNull Continuation<? super ApolloResponse<D>> continuation) {
        return FlowKt.single(toFlow(), continuation);
    }

    @NotNull
    public final ApolloClient getApolloClient$apollo_runtime() {
        return this.apolloClient;
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
    public final Operation<D> getOperation() {
        return this.operation;
    }

    @Nullable
    public Boolean getSendApqExtensions() {
        return this.sendApqExtensions;
    }

    @Nullable
    public Boolean getSendDocument() {
        return this.sendDocument;
    }

    @Deprecated(message = "Use canBeBatched() instead")
    public void setCanBeBatched(@Nullable Boolean bool) {
        this.canBeBatched = bool;
    }

    @Deprecated(message = "Use enableAutoPersistedQueries() instead")
    public void setEnableAutoPersistedQueries(@Nullable Boolean bool) {
        this.enableAutoPersistedQueries = bool;
    }

    @Deprecated(message = "Use addExecutionContext() instead")
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
    public final Flow<ApolloResponse<D>> toFlow() {
        ApolloRequest<D> build = new ApolloRequest.Builder(this.operation).executionContext(getExecutionContext()).httpMethod(getHttpMethod()).httpHeaders(getHttpHeaders()).sendApqExtensions(getSendApqExtensions()).sendDocument(getSendDocument()).enableAutoPersistedQueries(getEnableAutoPersistedQueries()).canBeBatched(getCanBeBatched()).build();
        ApolloClient apolloClient2 = this.apolloClient;
        Boolean bool = this.ignoreApolloClientHttpHeaders;
        return apolloClient2.executeAsFlow$apollo_runtime(build, bool == null || Intrinsics.areEqual((Object) bool, (Object) Boolean.TRUE));
    }

    @NotNull
    public ApolloCall<D> addExecutionContext(@NotNull ExecutionContext executionContext2) {
        Intrinsics.checkNotNullParameter(executionContext2, "executionContext");
        setExecutionContext(getExecutionContext().plus(executionContext2));
        return this;
    }

    @NotNull
    public ApolloCall<D> addHttpHeader(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, "value");
        if (getHttpHeaders() == null || Intrinsics.areEqual((Object) this.ignoreApolloClientHttpHeaders, (Object) Boolean.FALSE)) {
            this.ignoreApolloClientHttpHeaders = Boolean.FALSE;
            List<HttpHeader> httpHeaders2 = getHttpHeaders();
            if (httpHeaders2 == null) {
                httpHeaders2 = CollectionsKt.emptyList();
            }
            setHttpHeaders(CollectionsKt.plus(httpHeaders2, new HttpHeader(str, str2)));
            return this;
        }
        throw new IllegalStateException("Apollo: it is an error to call both .headers() and .addHeader() or .additionalHeaders() at the same time");
    }

    @NotNull
    public ApolloCall<D> canBeBatched(@Nullable Boolean bool) {
        setCanBeBatched(bool);
        return this;
    }

    @NotNull
    public ApolloCall<D> enableAutoPersistedQueries(@Nullable Boolean bool) {
        setEnableAutoPersistedQueries(bool);
        return this;
    }

    @NotNull
    public ApolloCall<D> httpHeaders(@Nullable List<HttpHeader> list) {
        if (this.ignoreApolloClientHttpHeaders == null) {
            setHttpHeaders(list);
            return this;
        }
        throw new IllegalStateException("Apollo: it is an error to call both .headers() and .addHeader() or .additionalHeaders() at the same time");
    }

    @NotNull
    public ApolloCall<D> httpMethod(@Nullable HttpMethod httpMethod2) {
        setHttpMethod(httpMethod2);
        return this;
    }

    @NotNull
    public ApolloCall<D> sendApqExtensions(@Nullable Boolean bool) {
        setSendApqExtensions(bool);
        return this;
    }

    @NotNull
    public ApolloCall<D> sendDocument(@Nullable Boolean bool) {
        setSendDocument(bool);
        return this;
    }
}
