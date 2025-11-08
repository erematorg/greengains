package com.squareup.moshi;

import androidx.exifinterface.media.ExifInterface;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.NonNullJsonAdapter;
import com.squareup.moshi.internal.NullSafeJsonAdapter;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001b\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\b\u001a \u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a#\u0010\u0006\u001a\u00020\u0007\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00072\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\b¨\u0006\b"}, d2 = {"adapter", "Lcom/squareup/moshi/JsonAdapter;", "T", "Lcom/squareup/moshi/Moshi;", "ktype", "Lkotlin/reflect/KType;", "addAdapter", "Lcom/squareup/moshi/Moshi$Builder;", "moshi"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class _MoshiKotlinExtensionsKt {
    @ExperimentalStdlibApi
    public static final /* synthetic */ <T> JsonAdapter<T> adapter(Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "<this>");
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        return adapter(moshi, (KType) null);
    }

    @ExperimentalStdlibApi
    public static final /* synthetic */ <T> Moshi.Builder addAdapter(Moshi.Builder builder, JsonAdapter<T> jsonAdapter) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(jsonAdapter, "adapter");
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        Moshi.Builder add = builder.add(TypesJVMKt.getJavaType((KType) null), jsonAdapter);
        Intrinsics.checkNotNullExpressionValue(add, "add(typeOf<T>().javaType, adapter)");
        return add;
    }

    @NotNull
    @ExperimentalStdlibApi
    public static final <T> JsonAdapter<T> adapter(@NotNull Moshi moshi, @NotNull KType kType) {
        Intrinsics.checkNotNullParameter(moshi, "<this>");
        Intrinsics.checkNotNullParameter(kType, "ktype");
        JsonAdapter<T> adapter = moshi.adapter(TypesJVMKt.getJavaType(kType));
        if ((adapter instanceof NullSafeJsonAdapter) || (adapter instanceof NonNullJsonAdapter)) {
            return adapter;
        }
        if (kType.isMarkedNullable()) {
            JsonAdapter<T> nullSafe = adapter.nullSafe();
            Intrinsics.checkNotNullExpressionValue(nullSafe, "{\n    adapter.nullSafe()\n  }");
            return nullSafe;
        }
        JsonAdapter<T> nonNull = adapter.nonNull();
        Intrinsics.checkNotNullExpressionValue(nonNull, "{\n    adapter.nonNull()\n  }");
        return nonNull;
    }
}
