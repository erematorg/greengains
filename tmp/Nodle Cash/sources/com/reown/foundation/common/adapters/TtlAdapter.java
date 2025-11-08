package com.reown.foundation.common.adapters;

import com.reown.foundation.common.model.Ttl;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonQualifier;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.ToJson;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.Retention;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\rB\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0017J\u001c\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0017¨\u0006\u000e"}, d2 = {"Lcom/reown/foundation/common/adapters/TtlAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/foundation/common/model/Ttl;", "<init>", "()V", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value", "Qualifier", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TtlAdapter extends JsonAdapter<Ttl> {
    @NotNull
    public static final TtlAdapter INSTANCE = new TtlAdapter();

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Lcom/reown/foundation/common/adapters/TtlAdapter$Qualifier;", "", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @JsonQualifier
    @Retention(AnnotationRetention.RUNTIME)
    @java.lang.annotation.Retention(RetentionPolicy.RUNTIME)
    public @interface Qualifier {
    }

    private TtlAdapter() {
    }

    @ToJson
    public /* synthetic */ void toJson(JsonWriter jsonWriter, @Qualifier Ttl ttl) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (ttl != null) {
            jsonWriter.value(ttl.getSeconds());
        } else {
            jsonWriter.value(0);
        }
    }
}
