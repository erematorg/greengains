package com.reown.android.verify.model;

import A.a;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0010\u001a\u00020\nH\u0016J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/reown/android/verify/model/VerifyClaimsJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/verify/model/VerifyClaims;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "nullableBooleanAdapter", "", "longAdapter", "", "booleanAdapter", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VerifyClaimsJsonAdapter extends JsonAdapter<VerifyClaims> {
    @NotNull
    private final JsonAdapter<Boolean> booleanAdapter;
    @NotNull
    private final JsonAdapter<Long> longAdapter;
    @NotNull
    private final JsonAdapter<Boolean> nullableBooleanAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public VerifyClaimsJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("origin", TtmlNode.ATTR_ID, "isScam", "exp", "isVerified");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.stringAdapter = a.h(moshi, String.class, "origin", "adapter(...)");
        this.nullableBooleanAdapter = a.h(moshi, Boolean.class, "isScam", "adapter(...)");
        this.longAdapter = a.h(moshi, Long.TYPE, "expiration", "adapter(...)");
        this.booleanAdapter = a.h(moshi, Boolean.TYPE, "isVerified", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(34, "GeneratedJsonAdapter(VerifyClaims)");
    }

    @NotNull
    public VerifyClaims fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        Long l2 = null;
        Boolean bool = null;
        String str = null;
        String str2 = null;
        Boolean bool2 = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                str = this.stringAdapter.fromJson(jsonReader);
                if (str == null) {
                    throw Util.unexpectedNull("origin", "origin", jsonReader);
                }
            } else if (selectName == 1) {
                str2 = this.stringAdapter.fromJson(jsonReader);
                if (str2 == null) {
                    throw Util.unexpectedNull(TtmlNode.ATTR_ID, TtmlNode.ATTR_ID, jsonReader);
                }
            } else if (selectName == 2) {
                bool2 = this.nullableBooleanAdapter.fromJson(jsonReader);
            } else if (selectName == 3) {
                l2 = this.longAdapter.fromJson(jsonReader);
                if (l2 == null) {
                    throw Util.unexpectedNull("expiration", "exp", jsonReader);
                }
            } else if (selectName == 4 && (bool = this.booleanAdapter.fromJson(jsonReader)) == null) {
                throw Util.unexpectedNull("isVerified", "isVerified", jsonReader);
            }
        }
        jsonReader.endObject();
        if (str == null) {
            throw Util.missingProperty("origin", "origin", jsonReader);
        } else if (str2 == null) {
            throw Util.missingProperty(TtmlNode.ATTR_ID, TtmlNode.ATTR_ID, jsonReader);
        } else if (l2 != null) {
            long longValue = l2.longValue();
            if (bool != null) {
                return new VerifyClaims(str, str2, bool2, longValue, bool.booleanValue());
            }
            throw Util.missingProperty("isVerified", "isVerified", jsonReader);
        } else {
            throw Util.missingProperty("expiration", "exp", jsonReader);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable VerifyClaims verifyClaims) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (verifyClaims != null) {
            jsonWriter.beginObject();
            jsonWriter.name("origin");
            this.stringAdapter.toJson(jsonWriter, verifyClaims.getOrigin());
            jsonWriter.name(TtmlNode.ATTR_ID);
            this.stringAdapter.toJson(jsonWriter, verifyClaims.getId());
            jsonWriter.name("isScam");
            this.nullableBooleanAdapter.toJson(jsonWriter, verifyClaims.isScam());
            jsonWriter.name("exp");
            this.longAdapter.toJson(jsonWriter, Long.valueOf(verifyClaims.getExpiration()));
            jsonWriter.name("isVerified");
            this.booleanAdapter.toJson(jsonWriter, Boolean.valueOf(verifyClaims.isVerified()));
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
