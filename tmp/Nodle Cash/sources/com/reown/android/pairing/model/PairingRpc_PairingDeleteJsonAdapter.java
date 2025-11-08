package com.reown.android.pairing.model;

import A.a;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.reown.android.pairing.model.PairingParams;
import com.reown.android.pairing.model.PairingRpc;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import java.lang.reflect.Constructor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0011\u001a\u00020\fH\u0016J\u0010\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u001a\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0010X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/reown/android/pairing/model/PairingRpc_PairingDeleteJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/pairing/model/PairingRpc$PairingDelete;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "longAdapter", "", "stringAdapter", "", "deleteParamsAdapter", "Lcom/reown/android/pairing/model/PairingParams$DeleteParams;", "constructorRef", "Ljava/lang/reflect/Constructor;", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PairingRpc_PairingDeleteJsonAdapter extends JsonAdapter<PairingRpc.PairingDelete> {
    @Nullable
    private volatile Constructor<PairingRpc.PairingDelete> constructorRef;
    @NotNull
    private final JsonAdapter<PairingParams.DeleteParams> deleteParamsAdapter;
    @NotNull
    private final JsonAdapter<Long> longAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public PairingRpc_PairingDeleteJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of(TtmlNode.ATTR_ID, "jsonrpc", FirebaseAnalytics.Param.METHOD, "params");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.longAdapter = a.h(moshi, Long.TYPE, TtmlNode.ATTR_ID, "adapter(...)");
        this.stringAdapter = a.h(moshi, String.class, "jsonrpc", "adapter(...)");
        this.deleteParamsAdapter = a.h(moshi, PairingParams.DeleteParams.class, "params", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(46, "GeneratedJsonAdapter(PairingRpc.PairingDelete)");
    }

    @NotNull
    public PairingRpc.PairingDelete fromJson(@NotNull JsonReader jsonReader) {
        JsonReader jsonReader2 = jsonReader;
        Intrinsics.checkNotNullParameter(jsonReader2, "reader");
        jsonReader.beginObject();
        Long l2 = 0L;
        String str = null;
        String str2 = null;
        PairingParams.DeleteParams deleteParams = null;
        int i3 = -1;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader2.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                l2 = this.longAdapter.fromJson(jsonReader2);
                if (l2 != null) {
                    i3 &= -2;
                } else {
                    throw Util.unexpectedNull(TtmlNode.ATTR_ID, TtmlNode.ATTR_ID, jsonReader2);
                }
            } else if (selectName == 1) {
                str = this.stringAdapter.fromJson(jsonReader2);
                if (str != null) {
                    i3 &= -3;
                } else {
                    throw Util.unexpectedNull("jsonrpc", "jsonrpc", jsonReader2);
                }
            } else if (selectName == 2) {
                str2 = this.stringAdapter.fromJson(jsonReader2);
                if (str2 != null) {
                    i3 &= -5;
                } else {
                    throw Util.unexpectedNull(FirebaseAnalytics.Param.METHOD, FirebaseAnalytics.Param.METHOD, jsonReader2);
                }
            } else if (selectName == 3 && (deleteParams = this.deleteParamsAdapter.fromJson(jsonReader2)) == null) {
                throw Util.unexpectedNull("params", "params", jsonReader2);
            }
        }
        jsonReader.endObject();
        if (i3 == -8) {
            long longValue = l2.longValue();
            Intrinsics.checkNotNull(str, "null cannot be cast to non-null type kotlin.String");
            Intrinsics.checkNotNull(str2, "null cannot be cast to non-null type kotlin.String");
            if (deleteParams != null) {
                return new PairingRpc.PairingDelete(longValue, str, str2, deleteParams);
            }
            throw Util.missingProperty("params", "params", jsonReader2);
        }
        Constructor<PairingRpc.PairingDelete> constructor = this.constructorRef;
        if (constructor == null) {
            constructor = PairingRpc.PairingDelete.class.getDeclaredConstructor(new Class[]{Long.TYPE, String.class, String.class, PairingParams.DeleteParams.class, Integer.TYPE, Util.DEFAULT_CONSTRUCTOR_MARKER});
            this.constructorRef = constructor;
            Intrinsics.checkNotNullExpressionValue(constructor, "also(...)");
        }
        if (deleteParams != null) {
            PairingRpc.PairingDelete newInstance = constructor.newInstance(new Object[]{l2, str, str2, deleteParams, Integer.valueOf(i3), null});
            Intrinsics.checkNotNullExpressionValue(newInstance, "newInstance(...)");
            return newInstance;
        }
        throw Util.missingProperty("params", "params", jsonReader2);
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable PairingRpc.PairingDelete pairingDelete) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (pairingDelete != null) {
            jsonWriter.beginObject();
            jsonWriter.name(TtmlNode.ATTR_ID);
            this.longAdapter.toJson(jsonWriter, Long.valueOf(pairingDelete.getId()));
            jsonWriter.name("jsonrpc");
            this.stringAdapter.toJson(jsonWriter, pairingDelete.getJsonrpc());
            jsonWriter.name(FirebaseAnalytics.Param.METHOD);
            this.stringAdapter.toJson(jsonWriter, pairingDelete.getMethod());
            jsonWriter.name("params");
            this.deleteParamsAdapter.toJson(jsonWriter, pairingDelete.getParams());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
