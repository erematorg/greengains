package com.apollographql.apollo3.api;

import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000 \u0006*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0007\u0006\u0007\b\t\n\u000b\fB\u000f\b\u0004\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0002\u0010\u0004R\u0012\u0010\u0003\u001a\u00028\u00008\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\u0005\u0001\u0006\r\u000e\u000f\u0010\u0011\u0012¨\u0006\u0013"}, d2 = {"Lcom/apollographql/apollo3/api/CustomTypeValue;", "T", "", "value", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "Companion", "GraphQLBoolean", "GraphQLJsonList", "GraphQLJsonObject", "GraphQLNull", "GraphQLNumber", "GraphQLString", "Lcom/apollographql/apollo3/api/CustomTypeValue$GraphQLBoolean;", "Lcom/apollographql/apollo3/api/CustomTypeValue$GraphQLJsonList;", "Lcom/apollographql/apollo3/api/CustomTypeValue$GraphQLJsonObject;", "Lcom/apollographql/apollo3/api/CustomTypeValue$GraphQLNull;", "Lcom/apollographql/apollo3/api/CustomTypeValue$GraphQLNumber;", "Lcom/apollographql/apollo3/api/CustomTypeValue$GraphQLString;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
@Deprecated(message = "Used for backward compatibility with 2.x, use Adapter instead")
public abstract class CustomTypeValue<T> {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @JvmField
    public final T value;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0001H\u0007¨\u0006\u0006"}, d2 = {"Lcom/apollographql/apollo3/api/CustomTypeValue$Companion;", "", "()V", "fromRawValue", "Lcom/apollographql/apollo3/api/CustomTypeValue;", "value", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final CustomTypeValue<?> fromRawValue(@Nullable Object obj) {
            if (obj instanceof Map) {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any>");
                return new GraphQLJsonObject((Map) obj);
            } else if (!(obj instanceof List)) {
                return obj instanceof Boolean ? new GraphQLBoolean(((Boolean) obj).booleanValue()) : obj instanceof Number ? new GraphQLNumber((Number) obj) : obj == null ? GraphQLNull.INSTANCE : new GraphQLString(obj.toString());
            } else {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
                return new GraphQLJsonList((List) obj);
            }
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/apollographql/apollo3/api/CustomTypeValue$GraphQLBoolean;", "Lcom/apollographql/apollo3/api/CustomTypeValue;", "", "value", "(Z)V", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class GraphQLBoolean extends CustomTypeValue<Boolean> {
        public GraphQLBoolean(boolean z2) {
            super(Boolean.valueOf(z2), (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/apollographql/apollo3/api/CustomTypeValue$GraphQLJsonList;", "Lcom/apollographql/apollo3/api/CustomTypeValue;", "", "", "value", "(Ljava/util/List;)V", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class GraphQLJsonList extends CustomTypeValue<List<? extends Object>> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GraphQLJsonList(@NotNull List<? extends Object> list) {
            super(list, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(list, "value");
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001B\u0019\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/apollographql/apollo3/api/CustomTypeValue$GraphQLJsonObject;", "Lcom/apollographql/apollo3/api/CustomTypeValue;", "", "", "", "value", "(Ljava/util/Map;)V", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class GraphQLJsonObject extends CustomTypeValue<Map<String, ? extends Object>> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GraphQLJsonObject(@NotNull Map<String, ? extends Object> map) {
            super(map, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(map, "value");
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/apollographql/apollo3/api/CustomTypeValue$GraphQLNull;", "Lcom/apollographql/apollo3/api/CustomTypeValue;", "", "()V", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class GraphQLNull extends CustomTypeValue<Unit> {
        @NotNull
        public static final GraphQLNull INSTANCE = new GraphQLNull();

        private GraphQLNull() {
            super(Unit.INSTANCE, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0004\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/apollographql/apollo3/api/CustomTypeValue$GraphQLNumber;", "Lcom/apollographql/apollo3/api/CustomTypeValue;", "", "value", "(Ljava/lang/Number;)V", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class GraphQLNumber extends CustomTypeValue<Number> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GraphQLNumber(@NotNull Number number) {
            super(number, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(number, "value");
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/apollographql/apollo3/api/CustomTypeValue$GraphQLString;", "Lcom/apollographql/apollo3/api/CustomTypeValue;", "", "value", "(Ljava/lang/String;)V", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class GraphQLString extends CustomTypeValue<String> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GraphQLString(@NotNull String str) {
            super(str, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "value");
        }
    }

    public /* synthetic */ CustomTypeValue(Object obj, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj);
    }

    @JvmStatic
    @NotNull
    public static final CustomTypeValue<?> fromRawValue(@Nullable Object obj) {
        return Companion.fromRawValue(obj);
    }

    private CustomTypeValue(T t2) {
        this.value = t2;
    }
}
