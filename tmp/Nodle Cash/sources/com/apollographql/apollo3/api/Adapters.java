package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.json.BufferedSinkJsonWriter;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000h\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a#\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u001f0\u001e\"\u0004\b\u0000\u0010\u001f*\b\u0012\u0004\u0012\u0002H\u001f0\u0001H\u0007¢\u0006\u0002\b \u001a'\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u001f0\u0015\"\b\b\u0000\u0010\u001f*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u001f0\u0001H\u0007¢\u0006\u0002\b\"\u001a-\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u001f0$\"\u0004\b\u0000\u0010\u001f*\b\u0012\u0004\u0012\u0002H\u001f0\u00012\b\b\u0002\u0010%\u001a\u00020\u0006H\u0007¢\u0006\u0002\b&\u001a#\u0010'\u001a\b\u0012\u0004\u0012\u0002H\u001f0(\"\u0004\b\u0000\u0010\u001f*\b\u0012\u0004\u0012\u0002H\u001f0\u0001H\u0007¢\u0006\u0002\b)\u001a#\u0010*\u001a\b\u0012\u0004\u0012\u0002H\u001f0(\"\u0004\b\u0000\u0010\u001f*\b\u0012\u0004\u0012\u0002H\u001f0\u0001H\u0007¢\u0006\u0002\b+\u001a=\u0010,\u001a\u00020\f\"\u0004\b\u0000\u0010\u001f*\b\u0012\u0004\u0012\u0002H\u001f0\u00012\u0006\u0010-\u001a\u0002H\u001f2\b\b\u0002\u0010.\u001a\u00020/2\n\b\u0002\u00100\u001a\u0004\u0018\u00010\fH\u0007¢\u0006\u0004\b1\u00102\"\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00048\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00048\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00048\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00048\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\n0\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u00158\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u00158\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u00158\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00158\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\f0\u00158\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\f0\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00018\u0006X\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"AnyAdapter", "Lcom/apollographql/apollo3/api/Adapter;", "", "ApolloOptionalAnyAdapter", "Lcom/apollographql/apollo3/api/ApolloOptionalAdapter;", "ApolloOptionalBooleanAdapter", "", "ApolloOptionalDoubleAdapter", "", "ApolloOptionalIntAdapter", "", "ApolloOptionalStringAdapter", "", "BooleanAdapter", "DoubleAdapter", "FloatAdapter", "", "IntAdapter", "LongAdapter", "", "NullableAnyAdapter", "Lcom/apollographql/apollo3/api/NullableAdapter;", "NullableBooleanAdapter", "NullableDoubleAdapter", "NullableIntAdapter", "NullableStringAdapter", "StringAdapter", "UploadAdapter", "Lcom/apollographql/apollo3/api/Upload;", "list", "Lcom/apollographql/apollo3/api/ListAdapter;", "T", "-list", "nullable", "-nullable", "obj", "Lcom/apollographql/apollo3/api/ObjectAdapter;", "buffered", "-obj", "optional", "Lcom/apollographql/apollo3/api/PresentAdapter;", "-optional", "present", "-present", "toJsonString", "value", "customScalarAdapters", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "indent", "-toJson", "(Lcom/apollographql/apollo3/api/Adapter;Ljava/lang/Object;Lcom/apollographql/apollo3/api/CustomScalarAdapters;Ljava/lang/String;)Ljava/lang/String;", "apollo-api"}, k = 2, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nAdapters.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Adapters.kt\ncom/apollographql/apollo3/api/Adapters\n+ 2 JsonWriters.kt\ncom/apollographql/apollo3/api/json/-JsonWriters\n*L\n1#1,345:1\n68#2,7:346\n*S KotlinDebug\n*F\n+ 1 Adapters.kt\ncom/apollographql/apollo3/api/Adapters\n*L\n342#1:346,7\n*E\n"})
@JvmName(name = "Adapters")
public final class Adapters {
    @NotNull
    @JvmField
    public static final Adapter<Object> AnyAdapter;
    @NotNull
    @JvmField
    public static final ApolloOptionalAdapter<Object> ApolloOptionalAnyAdapter;
    @NotNull
    @JvmField
    public static final ApolloOptionalAdapter<Boolean> ApolloOptionalBooleanAdapter;
    @NotNull
    @JvmField
    public static final ApolloOptionalAdapter<Double> ApolloOptionalDoubleAdapter;
    @NotNull
    @JvmField
    public static final ApolloOptionalAdapter<Integer> ApolloOptionalIntAdapter;
    @NotNull
    @JvmField
    public static final ApolloOptionalAdapter<String> ApolloOptionalStringAdapter;
    @NotNull
    @JvmField
    public static final Adapter<Boolean> BooleanAdapter;
    @NotNull
    @JvmField
    public static final Adapter<Double> DoubleAdapter;
    @NotNull
    @JvmField
    public static final Adapter<Float> FloatAdapter = new Adapters$FloatAdapter$1();
    @NotNull
    @JvmField
    public static final Adapter<Integer> IntAdapter;
    @NotNull
    @JvmField
    public static final Adapter<Long> LongAdapter = new Adapters$LongAdapter$1();
    @NotNull
    @JvmField
    public static final NullableAdapter<Object> NullableAnyAdapter;
    @NotNull
    @JvmField
    public static final NullableAdapter<Boolean> NullableBooleanAdapter;
    @NotNull
    @JvmField
    public static final NullableAdapter<Double> NullableDoubleAdapter;
    @NotNull
    @JvmField
    public static final NullableAdapter<Integer> NullableIntAdapter;
    @NotNull
    @JvmField
    public static final NullableAdapter<String> NullableStringAdapter;
    @NotNull
    @JvmField
    public static final Adapter<String> StringAdapter;
    @NotNull
    @JvmField
    public static final Adapter<Upload> UploadAdapter = new Adapters$UploadAdapter$1();

    @NotNull
    @JvmName(name = "-list")
    /* renamed from: -list  reason: not valid java name */
    public static final <T> ListAdapter<T> m8200list(@NotNull Adapter<T> adapter) {
        Intrinsics.checkNotNullParameter(adapter, "<this>");
        return new ListAdapter<>(adapter);
    }

    @NotNull
    @JvmName(name = "-nullable")
    /* renamed from: -nullable  reason: not valid java name */
    public static final <T> NullableAdapter<T> m8201nullable(@NotNull Adapter<T> adapter) {
        Intrinsics.checkNotNullParameter(adapter, "<this>");
        return new NullableAdapter<>(adapter);
    }

    @NotNull
    @JvmName(name = "-obj")
    /* renamed from: -obj  reason: not valid java name */
    public static final <T> ObjectAdapter<T> m8202obj(@NotNull Adapter<T> adapter, boolean z2) {
        Intrinsics.checkNotNullParameter(adapter, "<this>");
        return new ObjectAdapter<>(adapter, z2);
    }

    /* renamed from: -obj$default  reason: not valid java name */
    public static /* synthetic */ ObjectAdapter m8203obj$default(Adapter adapter, boolean z2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            z2 = false;
        }
        return m8202obj(adapter, z2);
    }

    @NotNull
    @Deprecated(message = "Use present instead", replaceWith = @ReplaceWith(expression = "present()", imports = {}))
    @JvmName(name = "-optional")
    /* renamed from: -optional  reason: not valid java name */
    public static final <T> PresentAdapter<T> m8204optional(@NotNull Adapter<T> adapter) {
        Intrinsics.checkNotNullParameter(adapter, "<this>");
        return new PresentAdapter<>(adapter);
    }

    @NotNull
    @JvmName(name = "-present")
    /* renamed from: -present  reason: not valid java name */
    public static final <T> PresentAdapter<T> m8205present(@NotNull Adapter<T> adapter) {
        Intrinsics.checkNotNullParameter(adapter, "<this>");
        return new PresentAdapter<>(adapter);
    }

    @NotNull
    @JvmOverloads
    @JvmName(name = "-toJson")
    /* renamed from: -toJson  reason: not valid java name */
    public static final <T> String m8206toJson(@NotNull Adapter<T> adapter, T t2) {
        Intrinsics.checkNotNullParameter(adapter, "<this>");
        return m8209toJson$default(adapter, t2, (CustomScalarAdapters) null, (String) null, 6, (Object) null);
    }

    /* renamed from: -toJson$default  reason: not valid java name */
    public static /* synthetic */ String m8209toJson$default(Adapter adapter, Object obj, CustomScalarAdapters customScalarAdapters, String str, int i3, Object obj2) {
        if ((i3 & 2) != 0) {
            customScalarAdapters = CustomScalarAdapters.Empty;
        }
        if ((i3 & 4) != 0) {
            str = null;
        }
        return m8208toJson(adapter, obj, customScalarAdapters, str);
    }

    static {
        Adapters$StringAdapter$1 adapters$StringAdapter$1 = new Adapters$StringAdapter$1();
        StringAdapter = adapters$StringAdapter$1;
        Adapters$IntAdapter$1 adapters$IntAdapter$1 = new Adapters$IntAdapter$1();
        IntAdapter = adapters$IntAdapter$1;
        Adapters$DoubleAdapter$1 adapters$DoubleAdapter$1 = new Adapters$DoubleAdapter$1();
        DoubleAdapter = adapters$DoubleAdapter$1;
        Adapters$BooleanAdapter$1 adapters$BooleanAdapter$1 = new Adapters$BooleanAdapter$1();
        BooleanAdapter = adapters$BooleanAdapter$1;
        Adapters$AnyAdapter$1 adapters$AnyAdapter$1 = new Adapters$AnyAdapter$1();
        AnyAdapter = adapters$AnyAdapter$1;
        NullableStringAdapter = m8201nullable(adapters$StringAdapter$1);
        NullableDoubleAdapter = m8201nullable(adapters$DoubleAdapter$1);
        NullableIntAdapter = m8201nullable(adapters$IntAdapter$1);
        NullableBooleanAdapter = m8201nullable(adapters$BooleanAdapter$1);
        NullableAnyAdapter = m8201nullable(adapters$AnyAdapter$1);
        ApolloOptionalStringAdapter = new ApolloOptionalAdapter<>(adapters$StringAdapter$1);
        ApolloOptionalDoubleAdapter = new ApolloOptionalAdapter<>(adapters$DoubleAdapter$1);
        ApolloOptionalIntAdapter = new ApolloOptionalAdapter<>(adapters$IntAdapter$1);
        ApolloOptionalBooleanAdapter = new ApolloOptionalAdapter<>(adapters$BooleanAdapter$1);
        ApolloOptionalAnyAdapter = new ApolloOptionalAdapter<>(adapters$AnyAdapter$1);
    }

    @NotNull
    @JvmOverloads
    @JvmName(name = "-toJson")
    /* renamed from: -toJson  reason: not valid java name */
    public static final <T> String m8207toJson(@NotNull Adapter<T> adapter, T t2, @NotNull CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(adapter, "<this>");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        return m8209toJson$default(adapter, t2, customScalarAdapters, (String) null, 4, (Object) null);
    }

    @NotNull
    @JvmOverloads
    @JvmName(name = "-toJson")
    /* renamed from: -toJson  reason: not valid java name */
    public static final <T> String m8208toJson(@NotNull Adapter<T> adapter, T t2, @NotNull CustomScalarAdapters customScalarAdapters, @Nullable String str) {
        Intrinsics.checkNotNullParameter(adapter, "<this>");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        Buffer buffer = new Buffer();
        adapter.toJson(new BufferedSinkJsonWriter(buffer, str), customScalarAdapters, t2);
        return buffer.readUtf8();
    }
}
