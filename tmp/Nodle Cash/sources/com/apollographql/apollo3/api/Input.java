package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.Optional;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/apollographql/apollo3/api/Input;", "", "()V", "Companion", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
@Deprecated(message = "Input is a helper class to help migrating to 3.x and will be removed in a future version")
public final class Input {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0005H\u0007J!\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0007\"\u0004\b\u0000\u0010\u00052\u0006\u0010\b\u001a\u0002H\u0005H\u0007¢\u0006\u0002\u0010\tJ!\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0007\"\u0004\b\u0000\u0010\u00052\u0006\u0010\b\u001a\u0002H\u0005H\u0007¢\u0006\u0002\u0010\t¨\u0006\u000b"}, d2 = {"Lcom/apollographql/apollo3/api/Input$Companion;", "", "()V", "absent", "Lcom/apollographql/apollo3/api/Optional$Absent;", "V", "fromNullable", "Lcom/apollographql/apollo3/api/Optional;", "value", "(Ljava/lang/Object;)Lcom/apollographql/apollo3/api/Optional;", "optional", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        @Deprecated(message = "absent() is a helper function to help migrating to 3.x and will be removed in a future version", replaceWith = @ReplaceWith(expression = "Optional.Absent", imports = {}))
        public final <V> Optional.Absent absent() {
            return Optional.Absent.INSTANCE;
        }

        @JvmStatic
        @NotNull
        @Deprecated(message = "fromNullable() is a helper function to help migrating to 3.x and will be removed in a future version", replaceWith = @ReplaceWith(expression = "Optional.Present(value)", imports = {}))
        public final <V> Optional<V> fromNullable(V v2) {
            return new Optional.Present(v2);
        }

        @JvmStatic
        @NotNull
        @Deprecated(message = "optional() is a helper function to help migrating to 3.x and will be removed in a future version", replaceWith = @ReplaceWith(expression = "Optional.presentIfNotNull(value)", imports = {}))
        public final <V> Optional<V> optional(V v2) {
            return Optional.Companion.presentIfNotNull(v2);
        }

        private Companion() {
        }
    }

    @JvmStatic
    @NotNull
    @Deprecated(message = "absent() is a helper function to help migrating to 3.x and will be removed in a future version", replaceWith = @ReplaceWith(expression = "Optional.Absent", imports = {}))
    public static final <V> Optional.Absent absent() {
        return Companion.absent();
    }

    @JvmStatic
    @NotNull
    @Deprecated(message = "fromNullable() is a helper function to help migrating to 3.x and will be removed in a future version", replaceWith = @ReplaceWith(expression = "Optional.Present(value)", imports = {}))
    public static final <V> Optional<V> fromNullable(V v2) {
        return Companion.fromNullable(v2);
    }

    @JvmStatic
    @NotNull
    @Deprecated(message = "optional() is a helper function to help migrating to 3.x and will be removed in a future version", replaceWith = @ReplaceWith(expression = "Optional.presentIfNotNull(value)", imports = {}))
    public static final <V> Optional<V> optional(V v2) {
        return Companion.optional(v2);
    }
}
