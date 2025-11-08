package com.reown.utils;

import A.a;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B/\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b0\u0006¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004HÆ\u0003J\u001b\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b0\u0006HÆ\u0003J;\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u001a\b\u0002\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b0\u0006HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR#\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/reown/utils/JsonAdapterEntry;", "T", "", "type", "Ljava/lang/Class;", "adapter", "Lkotlin/Function1;", "Lcom/squareup/moshi/Moshi;", "Lcom/squareup/moshi/JsonAdapter;", "<init>", "(Ljava/lang/Class;Lkotlin/jvm/functions/Function1;)V", "getType", "()Ljava/lang/Class;", "getAdapter", "()Lkotlin/jvm/functions/Function1;", "toString", "", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JsonAdapterEntry<T> {
    @NotNull
    private final Function1<Moshi, JsonAdapter<T>> adapter;
    @NotNull
    private final Class<T> type;

    public JsonAdapterEntry(@NotNull Class<T> cls, @NotNull Function1<? super Moshi, ? extends JsonAdapter<T>> function1) {
        Intrinsics.checkNotNullParameter(cls, "type");
        Intrinsics.checkNotNullParameter(function1, "adapter");
        this.type = cls;
        this.adapter = function1;
    }

    public static /* synthetic */ JsonAdapterEntry copy$default(JsonAdapterEntry jsonAdapterEntry, Class<T> cls, Function1<Moshi, JsonAdapter<T>> function1, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            cls = jsonAdapterEntry.type;
        }
        if ((i3 & 2) != 0) {
            function1 = jsonAdapterEntry.adapter;
        }
        return jsonAdapterEntry.copy(cls, function1);
    }

    @NotNull
    public final Class<T> component1() {
        return this.type;
    }

    @NotNull
    public final Function1<Moshi, JsonAdapter<T>> component2() {
        return this.adapter;
    }

    @NotNull
    public final JsonAdapterEntry<T> copy(@NotNull Class<T> cls, @NotNull Function1<? super Moshi, ? extends JsonAdapter<T>> function1) {
        Intrinsics.checkNotNullParameter(cls, "type");
        Intrinsics.checkNotNullParameter(function1, "adapter");
        return new JsonAdapterEntry<>(cls, function1);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof JsonAdapterEntry)) {
            return false;
        }
        JsonAdapterEntry jsonAdapterEntry = (JsonAdapterEntry) obj;
        return Intrinsics.areEqual((Object) this.type, (Object) jsonAdapterEntry.type) && Intrinsics.areEqual((Object) this.adapter, (Object) jsonAdapterEntry.adapter);
    }

    @NotNull
    public final Function1<Moshi, JsonAdapter<T>> getAdapter() {
        return this.adapter;
    }

    @NotNull
    public final Class<T> getType() {
        return this.type;
    }

    public int hashCode() {
        return this.adapter.hashCode() + (this.type.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        return a.l("JsonAdapterEntry(type=", this.type.getName(), ")");
    }
}
