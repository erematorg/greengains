package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.Operation.Data;
import com.apollographql.apollo3.exception.ApolloException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0001\u001bBa\b\u0002\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b\u0012\b\u0010\t\u001a\u0004\u0018\u00018\u0000\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b\u0012\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\u0006\u0010\u0018\u001a\u00020\u0013J\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u001aR\u0014\u0010\t\u001a\u0004\u0018\u00018\u00008\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\u0015R\u0011\u0010\u0016\u001a\u00028\u00008G¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0018\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00118\u0006X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u000e8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u00138\u0006X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00060\u0005j\u0002`\u00068\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/apollographql/apollo3/api/ApolloResponse;", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "", "requestUuid", "Ljava/util/UUID;", "Lcom/benasher44/uuid/Uuid;", "operation", "Lcom/apollographql/apollo3/api/Operation;", "data", "errors", "", "Lcom/apollographql/apollo3/api/Error;", "extensions", "", "", "executionContext", "Lcom/apollographql/apollo3/api/ExecutionContext;", "isLast", "", "(Ljava/util/UUID;Lcom/apollographql/apollo3/api/Operation;Lcom/apollographql/apollo3/api/Operation$Data;Ljava/util/List;Ljava/util/Map;Lcom/apollographql/apollo3/api/ExecutionContext;Z)V", "Lcom/apollographql/apollo3/api/Operation$Data;", "dataAssertNoErrors", "()Lcom/apollographql/apollo3/api/Operation$Data;", "hasErrors", "newBuilder", "Lcom/apollographql/apollo3/api/ApolloResponse$Builder;", "Builder", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ApolloResponse<D extends Operation.Data> {
    @Nullable
    @JvmField
    public final D data;
    @Nullable
    @JvmField
    public final List<Error> errors;
    @NotNull
    @JvmField
    public final ExecutionContext executionContext;
    @NotNull
    @JvmField
    public final Map<String, Object> extensions;
    @JvmField
    public final boolean isLast;
    @NotNull
    @JvmField
    public final Operation<D> operation;
    @NotNull
    @JvmField
    public final UUID requestUuid;

    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\u00020\u0003B)\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005\u0012\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b\u0012\b\u0010\t\u001a\u0004\u0018\u00018\u0001¢\u0006\u0002\u0010\nJ\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u000f\u001a\u00020\u0010J\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00010\u0018J\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rJ$\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u0016\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0012J\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u0014\u001a\u00020\u0015J\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\n\u0010\u0006\u001a\u00060\u0007j\u0002`\bR\u0012\u0010\t\u001a\u0004\u0018\u00018\u0001X\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0012X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00060\u0007j\u0002`\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/apollographql/apollo3/api/ApolloResponse$Builder;", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "", "operation", "Lcom/apollographql/apollo3/api/Operation;", "requestUuid", "Ljava/util/UUID;", "Lcom/benasher44/uuid/Uuid;", "data", "(Lcom/apollographql/apollo3/api/Operation;Ljava/util/UUID;Lcom/apollographql/apollo3/api/Operation$Data;)V", "Lcom/apollographql/apollo3/api/Operation$Data;", "errors", "", "Lcom/apollographql/apollo3/api/Error;", "executionContext", "Lcom/apollographql/apollo3/api/ExecutionContext;", "extensions", "", "", "isLast", "", "addExecutionContext", "build", "Lcom/apollographql/apollo3/api/ApolloResponse;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Builder<D extends Operation.Data> {
        @Nullable
        private final D data;
        @Nullable
        private List<Error> errors;
        @NotNull
        private ExecutionContext executionContext = ExecutionContext.Empty;
        @Nullable
        private Map<String, ? extends Object> extensions;
        private boolean isLast;
        @NotNull
        private final Operation<D> operation;
        @NotNull
        private UUID requestUuid;

        public Builder(@NotNull Operation<D> operation2, @NotNull UUID uuid, @Nullable D d2) {
            Intrinsics.checkNotNullParameter(operation2, "operation");
            Intrinsics.checkNotNullParameter(uuid, "requestUuid");
            this.operation = operation2;
            this.requestUuid = uuid;
            this.data = d2;
        }

        @NotNull
        public final Builder<D> addExecutionContext(@NotNull ExecutionContext executionContext2) {
            Intrinsics.checkNotNullParameter(executionContext2, "executionContext");
            this.executionContext = this.executionContext.plus(executionContext2);
            return this;
        }

        @NotNull
        public final ApolloResponse<D> build() {
            Operation<D> operation2 = this.operation;
            UUID uuid = this.requestUuid;
            D d2 = this.data;
            ExecutionContext executionContext2 = this.executionContext;
            Map<String, ? extends Object> map = this.extensions;
            if (map == null) {
                map = MapsKt.emptyMap();
            }
            return new ApolloResponse(uuid, operation2, d2, this.errors, map, executionContext2, this.isLast, (DefaultConstructorMarker) null);
        }

        @NotNull
        public final Builder<D> errors(@Nullable List<Error> list) {
            this.errors = list;
            return this;
        }

        @NotNull
        public final Builder<D> extensions(@Nullable Map<String, ? extends Object> map) {
            this.extensions = map;
            return this;
        }

        @NotNull
        public final Builder<D> isLast(boolean z2) {
            this.isLast = z2;
            return this;
        }

        @NotNull
        public final Builder<D> requestUuid(@NotNull UUID uuid) {
            Intrinsics.checkNotNullParameter(uuid, "requestUuid");
            this.requestUuid = uuid;
            return this;
        }
    }

    public /* synthetic */ ApolloResponse(UUID uuid, Operation operation2, Operation.Data data2, List list, Map map, ExecutionContext executionContext2, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
        this(uuid, operation2, data2, list, map, executionContext2, z2);
    }

    @NotNull
    @JvmName(name = "dataAssertNoErrors")
    public final D dataAssertNoErrors() {
        if (!hasErrors()) {
            D d2 = this.data;
            if (d2 != null) {
                return d2;
            }
            throw new ApolloException("The server did not return any data", (Throwable) null, 2, (DefaultConstructorMarker) null);
        }
        throw new ApolloException("The response has errors: " + this.errors, (Throwable) null, 2, (DefaultConstructorMarker) null);
    }

    public final boolean hasErrors() {
        Collection collection = this.errors;
        return !(collection == null || collection.isEmpty());
    }

    @NotNull
    public final Builder<D> newBuilder() {
        return new Builder(this.operation, this.requestUuid, this.data).errors(this.errors).extensions(this.extensions).addExecutionContext(this.executionContext).isLast(this.isLast);
    }

    private ApolloResponse(UUID uuid, Operation<D> operation2, D d2, List<Error> list, Map<String, ? extends Object> map, ExecutionContext executionContext2, boolean z2) {
        this.requestUuid = uuid;
        this.operation = operation2;
        this.data = d2;
        this.errors = list;
        this.extensions = map;
        this.executionContext = executionContext2;
        this.isLast = z2;
    }
}
