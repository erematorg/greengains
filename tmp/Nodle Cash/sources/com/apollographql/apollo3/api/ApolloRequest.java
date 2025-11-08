package com.apollographql.apollo3.api;

import com.apollographql.apollo3.annotations.ApolloExperimental;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.Operation.Data;
import com.apollographql.apollo3.api.http.HttpHeader;
import com.apollographql.apollo3.api.http.HttpMethod;
import java.util.List;
import java.util.UUID;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0001)Bk\b\u0002\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0015J\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000'J&\u0010&\u001a\b\u0012\u0004\u0012\u0002H(0'\"\b\b\u0001\u0010(*\u00020\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H(0\u0005H\u0007R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u0011X\u0004¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u0011X\u0004¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0019\u0010\u0017R\u0014\u0010\t\u001a\u00020\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001c\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0015\u0010\u0006\u001a\u00060\u0007j\u0002`\b¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0004¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b$\u0010\u0017R\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u0011X\u0004¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b%\u0010\u0017¨\u0006*"}, d2 = {"Lcom/apollographql/apollo3/api/ApolloRequest;", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "Lcom/apollographql/apollo3/api/ExecutionOptions;", "operation", "Lcom/apollographql/apollo3/api/Operation;", "requestUuid", "Ljava/util/UUID;", "Lcom/benasher44/uuid/Uuid;", "executionContext", "Lcom/apollographql/apollo3/api/ExecutionContext;", "httpMethod", "Lcom/apollographql/apollo3/api/http/HttpMethod;", "httpHeaders", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "sendApqExtensions", "", "sendDocument", "enableAutoPersistedQueries", "canBeBatched", "(Lcom/apollographql/apollo3/api/Operation;Ljava/util/UUID;Lcom/apollographql/apollo3/api/ExecutionContext;Lcom/apollographql/apollo3/api/http/HttpMethod;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "getCanBeBatched", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getEnableAutoPersistedQueries", "getExecutionContext", "()Lcom/apollographql/apollo3/api/ExecutionContext;", "getHttpHeaders", "()Ljava/util/List;", "getHttpMethod", "()Lcom/apollographql/apollo3/api/http/HttpMethod;", "getOperation", "()Lcom/apollographql/apollo3/api/Operation;", "getRequestUuid", "()Ljava/util/UUID;", "getSendApqExtensions", "getSendDocument", "newBuilder", "Lcom/apollographql/apollo3/api/ApolloRequest$Builder;", "E", "Builder", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ApolloRequest<D extends Operation.Data> implements ExecutionOptions {
    @Nullable
    private final Boolean canBeBatched;
    @Nullable
    private final Boolean enableAutoPersistedQueries;
    @NotNull
    private final ExecutionContext executionContext;
    @Nullable
    private final List<HttpHeader> httpHeaders;
    @Nullable
    private final HttpMethod httpMethod;
    @NotNull
    private final Operation<D> operation;
    @NotNull
    private final UUID requestUuid;
    @Nullable
    private final Boolean sendApqExtensions;
    @Nullable
    private final Boolean sendDocument;

    @SourceDebugExtension({"SMAP\nApolloRequest.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ApolloRequest.kt\ncom/apollographql/apollo3/api/ApolloRequest$Builder\n+ 2 uuid.kt\ncom/benasher44/uuid/UuidKt\n*L\n1#1,146:1\n96#2:147\n*S KotlinDebug\n*F\n+ 1 ApolloRequest.kt\ncom/apollographql/apollo3/api/ApolloRequest$Builder\n*L\n46#1:147\n*E\n"})
    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00000\u0003B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\u001e\u0010/\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u000201H\u0016J\f\u00103\u001a\b\u0012\u0004\u0012\u00028\u000104J\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0002\u00105J\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0002\u00105J\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u0013\u001a\u00020\u0012J\u001e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u000e\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018H\u0016J\u0018\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0016J\u0018\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\n\u0010%\u001a\u00060&j\u0002`'J\u001d\u0010(\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\u0010(\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0002\u00105J\u001d\u0010+\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\u0010+\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0002\u00105R*\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b@WX\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR*\u0010\u000f\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b@WX\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR$\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\u0012@WX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R4\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00182\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018@WX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR(\u0010 \u001a\u0004\u0018\u00010\u001f2\b\u0010\u0007\u001a\u0004\u0018\u00010\u001f@WX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010%\u001a\u00060&j\u0002`'X\u000e¢\u0006\u0002\n\u0000R*\u0010(\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b@WX\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b)\u0010\u000b\"\u0004\b*\u0010\rR*\u0010+\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b@WX\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b,\u0010\u000b\"\u0004\b-\u0010\r¨\u00066"}, d2 = {"Lcom/apollographql/apollo3/api/ApolloRequest$Builder;", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "Lcom/apollographql/apollo3/api/MutableExecutionOptions;", "operation", "Lcom/apollographql/apollo3/api/Operation;", "(Lcom/apollographql/apollo3/api/Operation;)V", "<set-?>", "", "canBeBatched", "getCanBeBatched", "()Ljava/lang/Boolean;", "setCanBeBatched", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "enableAutoPersistedQueries", "getEnableAutoPersistedQueries", "setEnableAutoPersistedQueries", "Lcom/apollographql/apollo3/api/ExecutionContext;", "executionContext", "getExecutionContext", "()Lcom/apollographql/apollo3/api/ExecutionContext;", "setExecutionContext", "(Lcom/apollographql/apollo3/api/ExecutionContext;)V", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "httpHeaders", "getHttpHeaders", "()Ljava/util/List;", "setHttpHeaders", "(Ljava/util/List;)V", "Lcom/apollographql/apollo3/api/http/HttpMethod;", "httpMethod", "getHttpMethod", "()Lcom/apollographql/apollo3/api/http/HttpMethod;", "setHttpMethod", "(Lcom/apollographql/apollo3/api/http/HttpMethod;)V", "requestUuid", "Ljava/util/UUID;", "Lcom/benasher44/uuid/Uuid;", "sendApqExtensions", "getSendApqExtensions", "setSendApqExtensions", "sendDocument", "getSendDocument", "setSendDocument", "addExecutionContext", "addHttpHeader", "name", "", "value", "build", "Lcom/apollographql/apollo3/api/ApolloRequest;", "(Ljava/lang/Boolean;)Lcom/apollographql/apollo3/api/ApolloRequest$Builder;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Builder<D extends Operation.Data> implements MutableExecutionOptions<Builder<D>> {
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
        @NotNull
        private Operation<D> operation;
        @NotNull
        private UUID requestUuid;
        @Nullable
        private Boolean sendApqExtensions;
        @Nullable
        private Boolean sendDocument;

        public Builder(@NotNull Operation<D> operation2) {
            Intrinsics.checkNotNullParameter(operation2, "operation");
            this.operation = operation2;
            UUID randomUUID = UUID.randomUUID();
            Intrinsics.checkNotNullExpressionValue(randomUUID, "randomUUID()");
            this.requestUuid = randomUUID;
        }

        @NotNull
        public final ApolloRequest<D> build() {
            return new ApolloRequest(this.operation, this.requestUuid, getExecutionContext(), getHttpMethod(), getHttpHeaders(), getSendApqExtensions(), getSendDocument(), getEnableAutoPersistedQueries(), getCanBeBatched(), (DefaultConstructorMarker) null);
        }

        @NotNull
        public final Builder<D> executionContext(@NotNull ExecutionContext executionContext2) {
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

        @Nullable
        public Boolean getSendApqExtensions() {
            return this.sendApqExtensions;
        }

        @Nullable
        public Boolean getSendDocument() {
            return this.sendDocument;
        }

        @NotNull
        public final Builder<D> requestUuid(@NotNull UUID uuid) {
            Intrinsics.checkNotNullParameter(uuid, "requestUuid");
            this.requestUuid = uuid;
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
        public Builder<D> addExecutionContext(@NotNull ExecutionContext executionContext2) {
            Intrinsics.checkNotNullParameter(executionContext2, "executionContext");
            setExecutionContext(getExecutionContext().plus(executionContext2));
            return this;
        }

        @NotNull
        public Builder<D> addHttpHeader(@NotNull String str, @NotNull String str2) {
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
        public Builder<D> canBeBatched(@Nullable Boolean bool) {
            setCanBeBatched(bool);
            return this;
        }

        @NotNull
        public Builder<D> enableAutoPersistedQueries(@Nullable Boolean bool) {
            setEnableAutoPersistedQueries(bool);
            return this;
        }

        @NotNull
        public Builder<D> httpHeaders(@Nullable List<HttpHeader> list) {
            setHttpHeaders(list);
            return this;
        }

        @NotNull
        public Builder<D> httpMethod(@Nullable HttpMethod httpMethod2) {
            setHttpMethod(httpMethod2);
            return this;
        }

        @NotNull
        public Builder<D> sendApqExtensions(@Nullable Boolean bool) {
            setSendApqExtensions(bool);
            return this;
        }

        @NotNull
        public Builder<D> sendDocument(@Nullable Boolean bool) {
            setSendDocument(bool);
            return this;
        }
    }

    public /* synthetic */ ApolloRequest(Operation operation2, UUID uuid, ExecutionContext executionContext2, HttpMethod httpMethod2, List list, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, DefaultConstructorMarker defaultConstructorMarker) {
        this(operation2, uuid, executionContext2, httpMethod2, list, bool, bool2, bool3, bool4);
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

    @NotNull
    public final UUID getRequestUuid() {
        return this.requestUuid;
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
    public final Builder<D> newBuilder() {
        return newBuilder(this.operation);
    }

    private ApolloRequest(Operation<D> operation2, UUID uuid, ExecutionContext executionContext2, HttpMethod httpMethod2, List<HttpHeader> list, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4) {
        this.operation = operation2;
        this.requestUuid = uuid;
        this.executionContext = executionContext2;
        this.httpMethod = httpMethod2;
        this.httpHeaders = list;
        this.sendApqExtensions = bool;
        this.sendDocument = bool2;
        this.enableAutoPersistedQueries = bool3;
        this.canBeBatched = bool4;
    }

    @ApolloExperimental
    @NotNull
    public final <E extends Operation.Data> Builder<E> newBuilder(@NotNull Operation<E> operation2) {
        Intrinsics.checkNotNullParameter(operation2, "operation");
        return new Builder(operation2).requestUuid(this.requestUuid).executionContext(getExecutionContext()).httpMethod(getHttpMethod()).httpHeaders(getHttpHeaders()).sendApqExtensions(getSendApqExtensions()).sendDocument(getSendDocument()).enableAutoPersistedQueries(getEnableAutoPersistedQueries()).canBeBatched(getCanBeBatched());
    }
}
