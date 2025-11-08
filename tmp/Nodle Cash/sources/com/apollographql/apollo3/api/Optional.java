package com.apollographql.apollo3.api;

import androidx.compose.runtime.b;
import com.apollographql.apollo3.exception.MissingValueException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \b*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0003\u0007\b\tB\u0007\b\u0004¢\u0006\u0002\u0010\u0003J\r\u0010\u0004\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u0005J\u000b\u0010\u0006\u001a\u00028\u0000¢\u0006\u0002\u0010\u0005\u0001\u0002\n\u000b¨\u0006\f"}, d2 = {"Lcom/apollographql/apollo3/api/Optional;", "V", "", "()V", "getOrNull", "()Ljava/lang/Object;", "getOrThrow", "Absent", "Companion", "Present", "Lcom/apollographql/apollo3/api/Optional$Absent;", "Lcom/apollographql/apollo3/api/Optional$Present;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public abstract class Optional<V> {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/apollographql/apollo3/api/Optional$Absent;", "Lcom/apollographql/apollo3/api/Optional;", "", "()V", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Absent extends Optional {
        @NotNull
        public static final Absent INSTANCE = new Absent();

        private Absent() {
            super((DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u0005H\u0007J!\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\u0007\u001a\u0002H\u0005H\u0007¢\u0006\u0002\u0010\bJ'\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\b\b\u0001\u0010\u0005*\u00020\u00012\b\u0010\u0007\u001a\u0004\u0018\u0001H\u0005H\u0007¢\u0006\u0002\u0010\b¨\u0006\n"}, d2 = {"Lcom/apollographql/apollo3/api/Optional$Companion;", "", "()V", "absent", "Lcom/apollographql/apollo3/api/Optional;", "V", "present", "value", "(Ljava/lang/Object;)Lcom/apollographql/apollo3/api/Optional;", "presentIfNotNull", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final <V> Optional<V> absent() {
            return Absent.INSTANCE;
        }

        @JvmStatic
        @NotNull
        public final <V> Optional<V> present(V v2) {
            return new Present(v2);
        }

        @JvmStatic
        @NotNull
        public final <V> Optional<V> presentIfNotNull(@Nullable V v2) {
            return v2 == null ? Absent.INSTANCE : new Present(v2);
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\u0006J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u0001HÆ\u0001¢\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0013\u0010\u0003\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, d2 = {"Lcom/apollographql/apollo3/api/Optional$Present;", "V", "Lcom/apollographql/apollo3/api/Optional;", "value", "(Ljava/lang/Object;)V", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lcom/apollographql/apollo3/api/Optional$Present;", "equals", "", "other", "", "hashCode", "", "toString", "", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Present<V> extends Optional<V> {
        private final V value;

        public Present(V v2) {
            super((DefaultConstructorMarker) null);
            this.value = v2;
        }

        public static /* synthetic */ Present copy$default(Present present, V v2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                v2 = present.value;
            }
            return present.copy(v2);
        }

        public final V component1() {
            return this.value;
        }

        @NotNull
        public final Present<V> copy(V v2) {
            return new Present<>(v2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Present) && Intrinsics.areEqual((Object) this.value, (Object) ((Present) obj).value);
        }

        public final V getValue() {
            return this.value;
        }

        public int hashCode() {
            V v2 = this.value;
            if (v2 == null) {
                return 0;
            }
            return v2.hashCode();
        }

        @NotNull
        public String toString() {
            return b.c(new StringBuilder("Present(value="), this.value, ')');
        }
    }

    public /* synthetic */ Optional(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @JvmStatic
    @NotNull
    public static final <V> Optional<V> absent() {
        return Companion.absent();
    }

    @JvmStatic
    @NotNull
    public static final <V> Optional<V> present(V v2) {
        return Companion.present(v2);
    }

    @JvmStatic
    @NotNull
    public static final <V> Optional<V> presentIfNotNull(@Nullable V v2) {
        return Companion.presentIfNotNull(v2);
    }

    @Nullable
    public final V getOrNull() {
        Present present = this instanceof Present ? (Present) this : null;
        if (present != null) {
            return present.getValue();
        }
        return null;
    }

    public final V getOrThrow() {
        V orNull = getOrNull();
        if (orNull != null) {
            return orNull;
        }
        throw new MissingValueException();
    }

    private Optional() {
    }
}
