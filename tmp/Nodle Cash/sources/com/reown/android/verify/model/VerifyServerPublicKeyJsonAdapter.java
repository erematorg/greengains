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

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u001a\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/reown/android/verify/model/VerifyServerPublicKeyJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/verify/model/VerifyServerPublicKey;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "jWKAdapter", "Lcom/reown/android/verify/model/JWK;", "longAdapter", "", "toString", "", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VerifyServerPublicKeyJsonAdapter extends JsonAdapter<VerifyServerPublicKey> {
    @NotNull
    private final JsonAdapter<JWK> jWKAdapter;
    @NotNull
    private final JsonAdapter<Long> longAdapter;
    @NotNull
    private final JsonReader.Options options;

    public VerifyServerPublicKeyJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("publicKey", "expiresAt");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.jWKAdapter = a.h(moshi, JWK.class, "jwk", "adapter(...)");
        this.longAdapter = a.h(moshi, Long.TYPE, "expiresAt", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(43, "GeneratedJsonAdapter(VerifyServerPublicKey)");
    }

    @NotNull
    public VerifyServerPublicKey fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        JWK jwk = null;
        Long l2 = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                jwk = this.jWKAdapter.fromJson(jsonReader);
                if (jwk == null) {
                    throw Util.unexpectedNull("jwk", "publicKey", jsonReader);
                }
            } else if (selectName == 1 && (l2 = this.longAdapter.fromJson(jsonReader)) == null) {
                throw Util.unexpectedNull("expiresAt", "expiresAt", jsonReader);
            }
        }
        jsonReader.endObject();
        if (jwk == null) {
            throw Util.missingProperty("jwk", "publicKey", jsonReader);
        } else if (l2 != null) {
            return new VerifyServerPublicKey(jwk, l2.longValue());
        } else {
            throw Util.missingProperty("expiresAt", "expiresAt", jsonReader);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable VerifyServerPublicKey verifyServerPublicKey) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (verifyServerPublicKey != null) {
            jsonWriter.beginObject();
            jsonWriter.name("publicKey");
            this.jWKAdapter.toJson(jsonWriter, verifyServerPublicKey.getJwk());
            jsonWriter.name("expiresAt");
            this.longAdapter.toJson(jsonWriter, Long.valueOf(verifyServerPublicKey.getExpiresAt()));
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
