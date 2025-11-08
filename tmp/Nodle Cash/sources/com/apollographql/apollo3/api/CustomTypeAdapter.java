package com.apollographql.apollo3.api;

import kotlin.Deprecated;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0019\u0010\u0003\u001a\u00028\u00002\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H&¢\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\u0004\u001a\u00028\u0000H&¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lcom/apollographql/apollo3/api/CustomTypeAdapter;", "T", "", "decode", "value", "Lcom/apollographql/apollo3/api/CustomTypeValue;", "(Lcom/apollographql/apollo3/api/CustomTypeValue;)Ljava/lang/Object;", "encode", "(Ljava/lang/Object;)Lcom/apollographql/apollo3/api/CustomTypeValue;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
@Deprecated(message = "Used for backward compatibility with 2.x, use Adapter instead")
public interface CustomTypeAdapter<T> {
    T decode(@NotNull CustomTypeValue<?> customTypeValue);

    @NotNull
    CustomTypeValue<?> encode(T t2);
}
