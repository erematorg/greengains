package com.reown.android.internal.common.json_rpc.data;

import V0.a;
import androidx.camera.camera2.internal.C0118y;
import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.reown.android.internal.common.JsonRpcResponse;
import com.reown.android.internal.common.model.type.ClientParams;
import com.reown.android.internal.common.model.type.JsonRpcClientSync;
import com.reown.android.internal.common.model.type.SerializableJsonRpc;
import com.reown.utils.JsonAdapterEntry;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import kotlin.reflect.KClasses;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001BK\u0012\u0010\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003\u0012\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0006\u0012\u0010\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u0007J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001e\u001a\u00020\u001fJ \u0010 \u001a\u0004\u0018\u0001H!\"\u0006\b\u0000\u0010!\u0018\u00012\u0006\u0010\u001c\u001a\u00020\u0007H\b¢\u0006\u0002\u0010\"J\u001c\u0010 \u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001c\u001a\u00020\u00072\n\u0010#\u001a\u0006\u0012\u0002\b\u00030\u0004J\u001e\u0010$\u001a\u00020\u0007\"\u0006\b\u0000\u0010!\u0018\u00012\u0006\u0010#\u001a\u0002H!H\b¢\u0006\u0002\u0010%J\u001a\u0010$\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00012\n\u0010#\u001a\u0006\u0012\u0002\b\u00030\u0004R\u001b\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR!\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006&"}, d2 = {"Lcom/reown/android/internal/common/json_rpc/data/JsonRpcSerializer;", "", "serializerEntries", "", "Lkotlin/reflect/KClass;", "deserializerEntries", "", "", "jsonAdapterEntries", "Lcom/reown/utils/JsonAdapterEntry;", "moshiBuilder", "Lcom/squareup/moshi/Moshi$Builder;", "<init>", "(Ljava/util/Set;Ljava/util/Map;Ljava/util/Set;Lcom/squareup/moshi/Moshi$Builder;)V", "getSerializerEntries", "()Ljava/util/Set;", "getDeserializerEntries", "()Ljava/util/Map;", "getJsonAdapterEntries", "getMoshiBuilder", "()Lcom/squareup/moshi/Moshi$Builder;", "moshi", "Lcom/squareup/moshi/Moshi;", "getMoshi", "()Lcom/squareup/moshi/Moshi;", "deserialize", "Lcom/reown/android/internal/common/model/type/ClientParams;", "method", "json", "serialize", "payload", "Lcom/reown/android/internal/common/model/type/SerializableJsonRpc;", "tryDeserialize", "T", "(Ljava/lang/String;)Ljava/lang/Object;", "type", "trySerialize", "(Ljava/lang/Object;)Ljava/lang/String;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nJsonRpcSerializer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonRpcSerializer.kt\ncom/reown/android/internal/common/json_rpc/data/JsonRpcSerializer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,66:1\n59#1,3:67\n59#1,3:71\n1#2:70\n1#2:74\n1#2:78\n1761#3,3:75\n295#3,2:79\n*S KotlinDebug\n*F\n+ 1 JsonRpcSerializer.kt\ncom/reown/android/internal/common/json_rpc/data/JsonRpcSerializer\n*L\n46#1:67,3\n47#1:71,3\n46#1:70\n47#1:74\n48#1:75,3\n22#1:79,2\n*E\n"})
public final class JsonRpcSerializer {
    @NotNull
    private final Map<String, KClass<?>> deserializerEntries;
    @NotNull
    private final Set<JsonAdapterEntry<?>> jsonAdapterEntries;
    @NotNull
    private final Moshi.Builder moshiBuilder;
    @NotNull
    private final Set<KClass<?>> serializerEntries;

    public JsonRpcSerializer(@NotNull Set<? extends KClass<?>> set, @NotNull Map<String, ? extends KClass<?>> map, @NotNull Set<? extends JsonAdapterEntry<?>> set2, @NotNull Moshi.Builder builder) {
        Intrinsics.checkNotNullParameter(set, "serializerEntries");
        Intrinsics.checkNotNullParameter(map, "deserializerEntries");
        Intrinsics.checkNotNullParameter(set2, "jsonAdapterEntries");
        Intrinsics.checkNotNullParameter(builder, "moshiBuilder");
        this.serializerEntries = set;
        this.deserializerEntries = map;
        this.jsonAdapterEntries = set2;
        this.moshiBuilder = builder;
    }

    /* access modifiers changed from: private */
    public static final JsonAdapter _get_moshi_$lambda$1(JsonRpcSerializer jsonRpcSerializer, Type type, Set set, Moshi moshi) {
        Object obj;
        Function1 adapter;
        try {
            Iterator it = jsonRpcSerializer.jsonAdapterEntries.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.areEqual((Object) ((JsonAdapterEntry) obj).getType(), (Object) type)) {
                    break;
                }
            }
            JsonAdapterEntry jsonAdapterEntry = (JsonAdapterEntry) obj;
            if (jsonAdapterEntry == null || (adapter = jsonAdapterEntry.getAdapter()) == null) {
                return null;
            }
            Intrinsics.checkNotNull(moshi);
            return (JsonAdapter) adapter.invoke(moshi);
        } catch (Exception unused) {
            return null;
        }
    }

    private final /* synthetic */ <T> String trySerialize(T t2) {
        Object obj;
        Class<Object> cls = Object.class;
        try {
            Result.Companion companion = Result.Companion;
            Moshi moshi = getMoshi();
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            obj = Result.m8979constructorimpl(moshi.adapter(cls).toJson(t2));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m8979constructorimpl(ResultKt.createFailure(th));
        }
        Throwable r5 = Result.m8982exceptionOrNullimpl(obj);
        if (r5 == null) {
            Intrinsics.checkNotNullExpressionValue(obj, "getOrElse(...)");
            return (String) obj;
        }
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        throw new RuntimeException(C0118y.f("Failed to serialize ", cls.getSimpleName(), ": ", r5.getMessage()), r5);
    }

    @Nullable
    public final ClientParams deserialize(@NotNull String str, @NotNull String str2) {
        Object tryDeserialize;
        Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
        Intrinsics.checkNotNullParameter(str2, "json");
        KClass kClass = this.deserializerEntries.get(str);
        if (kClass != null && (tryDeserialize = tryDeserialize(str2, kClass)) != null && Intrinsics.areEqual((Object) Reflection.getOrCreateKotlinClass(tryDeserialize.getClass()), (Object) kClass) && (tryDeserialize instanceof JsonRpcClientSync)) {
            return ((JsonRpcClientSync) tryDeserialize).getParams();
        }
        return null;
    }

    @NotNull
    public final Map<String, KClass<?>> getDeserializerEntries() {
        return this.deserializerEntries;
    }

    @NotNull
    public final Set<JsonAdapterEntry<?>> getJsonAdapterEntries() {
        return this.jsonAdapterEntries;
    }

    @NotNull
    public final Moshi getMoshi() {
        Moshi build = this.moshiBuilder.add((JsonAdapter.Factory) new a(this)).addLast((JsonAdapter.Factory) new KotlinJsonAdapterFactory()).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    @NotNull
    public final Moshi.Builder getMoshiBuilder() {
        return this.moshiBuilder;
    }

    @NotNull
    public final Set<KClass<?>> getSerializerEntries() {
        return this.serializerEntries;
    }

    @Nullable
    public final String serialize(@NotNull SerializableJsonRpc serializableJsonRpc) {
        KClass kClass;
        Object obj;
        Object obj2;
        Class<JsonRpcResponse.JsonRpcError> cls = JsonRpcResponse.JsonRpcError.class;
        Class<JsonRpcResponse.JsonRpcResult> cls2 = JsonRpcResponse.JsonRpcResult.class;
        Intrinsics.checkNotNullParameter(serializableJsonRpc, "payload");
        if (serializableJsonRpc instanceof JsonRpcResponse.JsonRpcResult) {
            try {
                Result.Companion companion = Result.Companion;
                obj2 = Result.m8979constructorimpl(getMoshi().adapter(cls2).toJson(serializableJsonRpc));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj2 = Result.m8979constructorimpl(ResultKt.createFailure(th));
            }
            Throwable r7 = Result.m8982exceptionOrNullimpl(obj2);
            if (r7 == null) {
                Intrinsics.checkNotNullExpressionValue(obj2, "getOrElse(...)");
                return (String) obj2;
            }
            throw new RuntimeException(C0118y.f("Failed to serialize ", cls2.getSimpleName(), ": ", r7.getMessage()), r7);
        } else if (serializableJsonRpc instanceof JsonRpcResponse.JsonRpcError) {
            try {
                Result.Companion companion3 = Result.Companion;
                obj = Result.m8979constructorimpl(getMoshi().adapter(cls).toJson(serializableJsonRpc));
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.Companion;
                obj = Result.m8979constructorimpl(ResultKt.createFailure(th2));
            }
            Throwable r72 = Result.m8982exceptionOrNullimpl(obj);
            if (r72 == null) {
                Intrinsics.checkNotNullExpressionValue(obj, "getOrElse(...)");
                return (String) obj;
            }
            throw new RuntimeException(C0118y.f("Failed to serialize ", cls.getSimpleName(), ": ", r72.getMessage()), r72);
        } else {
            Iterable iterable = this.serializerEntries;
            boolean z2 = false;
            KClass kClass2 = null;
            if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                Iterator it = iterable.iterator();
                kClass = null;
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    kClass = (KClass) it.next();
                    if (KClasses.safeCast(kClass, serializableJsonRpc) != null) {
                        z2 = true;
                        break;
                    }
                }
            } else {
                kClass = null;
            }
            if (!z2) {
                return null;
            }
            if (kClass == null) {
                Intrinsics.throwUninitializedPropertyAccessException("payloadType");
            } else {
                kClass2 = kClass;
            }
            return trySerialize(serializableJsonRpc, kClass2);
        }
    }

    public final /* synthetic */ <T> T tryDeserialize(String str) {
        T t2;
        Intrinsics.checkNotNullParameter(str, "json");
        try {
            Result.Companion companion = Result.Companion;
            Moshi moshi = getMoshi();
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            t2 = Result.m8979constructorimpl(moshi.adapter(Object.class).fromJson(str));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            t2 = Result.m8979constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8985isFailureimpl(t2)) {
            return null;
        }
        return t2;
    }

    @Nullable
    public final Object tryDeserialize(@NotNull String str, @NotNull KClass<?> kClass) {
        Object obj;
        Intrinsics.checkNotNullParameter(str, "json");
        Intrinsics.checkNotNullParameter(kClass, "type");
        try {
            Result.Companion companion = Result.Companion;
            obj = Result.m8979constructorimpl(getMoshi().adapter(JvmClassMappingKt.getJavaClass(kClass)).fromJson(str));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m8979constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8985isFailureimpl(obj)) {
            return null;
        }
        return obj;
    }

    @NotNull
    public final String trySerialize(@NotNull Object obj, @NotNull KClass<?> kClass) {
        Object obj2;
        Intrinsics.checkNotNullParameter(obj, "payload");
        Intrinsics.checkNotNullParameter(kClass, "type");
        try {
            Result.Companion companion = Result.Companion;
            obj2 = Result.m8979constructorimpl(getMoshi().adapter((Type) JvmClassMappingKt.getJavaClass(kClass)).toJson(obj));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj2 = Result.m8979constructorimpl(ResultKt.createFailure(th));
        }
        Throwable r4 = Result.m8982exceptionOrNullimpl(obj2);
        if (r4 == null) {
            Intrinsics.checkNotNullExpressionValue(obj2, "getOrElse(...)");
            return (String) obj2;
        }
        throw new RuntimeException(C0118y.f("Failed to serialize ", kClass.getSimpleName(), ": ", r4.getMessage()), r4);
    }
}
