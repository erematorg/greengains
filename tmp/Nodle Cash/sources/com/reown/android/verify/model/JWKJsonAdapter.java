package com.reown.android.verify.model;

import A.a;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.moshi.internal.Util;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000f\u001a\u00020\nH\u0016J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/reown/android/verify/model/JWKJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/verify/model/JWK;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "booleanAdapter", "", "listOfStringAdapter", "", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JWKJsonAdapter extends JsonAdapter<JWK> {
    @NotNull
    private final JsonAdapter<Boolean> booleanAdapter;
    @NotNull
    private final JsonAdapter<List<String>> listOfStringAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public JWKJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("crv", "ext", "key_ops", "kty", "x", "y");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        Class<String> cls = String.class;
        this.stringAdapter = a.h(moshi, cls, "crv", "adapter(...)");
        this.booleanAdapter = a.h(moshi, Boolean.TYPE, "ext", "adapter(...)");
        this.listOfStringAdapter = a.i(moshi, Types.newParameterizedType(List.class, cls), "keyOps", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(25, "GeneratedJsonAdapter(JWK)");
    }

    @NotNull
    public JWK fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        Boolean bool = null;
        String str = null;
        List list = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader.selectName(this.options)) {
                case -1:
                    jsonReader.skipName();
                    jsonReader.skipValue();
                    break;
                case 0:
                    str = this.stringAdapter.fromJson(jsonReader);
                    if (str != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull("crv", "crv", jsonReader);
                    }
                case 1:
                    bool = this.booleanAdapter.fromJson(jsonReader);
                    if (bool != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull("ext", "ext", jsonReader);
                    }
                case 2:
                    list = this.listOfStringAdapter.fromJson(jsonReader);
                    if (list != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull("keyOps", "key_ops", jsonReader);
                    }
                case 3:
                    str2 = this.stringAdapter.fromJson(jsonReader);
                    if (str2 != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull("kty", "kty", jsonReader);
                    }
                case 4:
                    str3 = this.stringAdapter.fromJson(jsonReader);
                    if (str3 != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull("x", "x", jsonReader);
                    }
                case 5:
                    str4 = this.stringAdapter.fromJson(jsonReader);
                    if (str4 != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull("y", "y", jsonReader);
                    }
            }
        }
        jsonReader.endObject();
        if (str == null) {
            throw Util.missingProperty("crv", "crv", jsonReader);
        } else if (bool != null) {
            boolean booleanValue = bool.booleanValue();
            if (list == null) {
                throw Util.missingProperty("keyOps", "key_ops", jsonReader);
            } else if (str2 == null) {
                throw Util.missingProperty("kty", "kty", jsonReader);
            } else if (str3 == null) {
                throw Util.missingProperty("x", "x", jsonReader);
            } else if (str4 != null) {
                return new JWK(str, booleanValue, list, str2, str3, str4);
            } else {
                throw Util.missingProperty("y", "y", jsonReader);
            }
        } else {
            throw Util.missingProperty("ext", "ext", jsonReader);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable JWK jwk) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (jwk != null) {
            jsonWriter.beginObject();
            jsonWriter.name("crv");
            this.stringAdapter.toJson(jsonWriter, jwk.getCrv());
            jsonWriter.name("ext");
            this.booleanAdapter.toJson(jsonWriter, Boolean.valueOf(jwk.getExt()));
            jsonWriter.name("key_ops");
            this.listOfStringAdapter.toJson(jsonWriter, jwk.getKeyOps());
            jsonWriter.name("kty");
            this.stringAdapter.toJson(jsonWriter, jwk.getKty());
            jsonWriter.name("x");
            this.stringAdapter.toJson(jsonWriter, jwk.getX());
            jsonWriter.name("y");
            this.stringAdapter.toJson(jsonWriter, jwk.getY());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
