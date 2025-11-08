package com.apollographql.apollo3.api.json;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"readTypename", "", "Lcom/apollographql/apollo3/api/json/JsonReader;", "apollo-api"}, k = 2, mv = {1, 5, 1}, xi = 48)
@JvmName(name = "JsonReaders")
/* renamed from: com.apollographql.apollo3.api.json.JsonReaders  reason: case insensitive filesystem */
public final class C0217JsonReaders {
    @NotNull
    public static final String readTypename(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "<this>");
        if (jsonReader.selectName(CollectionsKt.listOf("__typename")) == 0) {
            String nextString = jsonReader.nextString();
            if (nextString != null) {
                return nextString;
            }
            throw new IllegalStateException("__typename is null");
        }
        throw new IllegalStateException("__typename not found");
    }
}
