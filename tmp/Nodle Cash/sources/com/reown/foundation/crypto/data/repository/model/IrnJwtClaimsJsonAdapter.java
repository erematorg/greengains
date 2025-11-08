package com.reown.foundation.crypto.data.repository.model;

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

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\r\u001a\u00020\nH\u0016J\u0010\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/reown/foundation/crypto/data/repository/model/IrnJwtClaimsJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/foundation/crypto/data/repository/model/IrnJwtClaims;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "longAdapter", "", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class IrnJwtClaimsJsonAdapter extends JsonAdapter<IrnJwtClaims> {
    @NotNull
    private final JsonAdapter<Long> longAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public IrnJwtClaimsJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("iss", "sub", "aud", "iat", "exp");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.stringAdapter = a.h(moshi, String.class, "issuer", "adapter(...)");
        this.longAdapter = a.h(moshi, Long.TYPE, "issuedAt", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(34, "GeneratedJsonAdapter(IrnJwtClaims)");
    }

    @NotNull
    public IrnJwtClaims fromJson(@NotNull JsonReader jsonReader) {
        JsonReader jsonReader2 = jsonReader;
        Intrinsics.checkNotNullParameter(jsonReader2, "reader");
        jsonReader.beginObject();
        Long l2 = null;
        Long l3 = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        while (true) {
            Long l4 = l3;
            Long l5 = l2;
            if (jsonReader.hasNext()) {
                int selectName = jsonReader2.selectName(this.options);
                String str4 = str3;
                if (selectName != -1) {
                    if (selectName == 0) {
                        str = this.stringAdapter.fromJson(jsonReader2);
                        if (str == null) {
                            throw Util.unexpectedNull("issuer", "iss", jsonReader2);
                        }
                    } else if (selectName == 1) {
                        str2 = this.stringAdapter.fromJson(jsonReader2);
                        if (str2 == null) {
                            throw Util.unexpectedNull("subject", "sub", jsonReader2);
                        }
                    } else if (selectName == 2) {
                        str3 = this.stringAdapter.fromJson(jsonReader2);
                        if (str3 != null) {
                            l3 = l4;
                            l2 = l5;
                        } else {
                            throw Util.unexpectedNull("audience", "aud", jsonReader2);
                        }
                    } else if (selectName == 3) {
                        l2 = this.longAdapter.fromJson(jsonReader2);
                        if (l2 != null) {
                            l3 = l4;
                            str3 = str4;
                        } else {
                            throw Util.unexpectedNull("issuedAt", "iat", jsonReader2);
                        }
                    } else if (selectName == 4) {
                        Long fromJson = this.longAdapter.fromJson(jsonReader2);
                        if (fromJson != null) {
                            l3 = fromJson;
                        } else {
                            throw Util.unexpectedNull("expiration", "exp", jsonReader2);
                        }
                    }
                    l3 = l4;
                } else {
                    jsonReader.skipName();
                    jsonReader.skipValue();
                    l3 = l4;
                }
                l2 = l5;
                str3 = str4;
            } else {
                String str5 = str3;
                jsonReader.endObject();
                if (str == null) {
                    throw Util.missingProperty("issuer", "iss", jsonReader2);
                } else if (str2 == null) {
                    throw Util.missingProperty("subject", "sub", jsonReader2);
                } else if (str5 == null) {
                    throw Util.missingProperty("audience", "aud", jsonReader2);
                } else if (l5 != null) {
                    long longValue = l5.longValue();
                    if (l4 != null) {
                        return new IrnJwtClaims(str, str2, str5, longValue, l4.longValue());
                    }
                    throw Util.missingProperty("expiration", "exp", jsonReader2);
                } else {
                    throw Util.missingProperty("issuedAt", "iat", jsonReader2);
                }
            }
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable IrnJwtClaims irnJwtClaims) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (irnJwtClaims != null) {
            jsonWriter.beginObject();
            jsonWriter.name("iss");
            this.stringAdapter.toJson(jsonWriter, irnJwtClaims.getIssuer());
            jsonWriter.name("sub");
            this.stringAdapter.toJson(jsonWriter, irnJwtClaims.getSubject());
            jsonWriter.name("aud");
            this.stringAdapter.toJson(jsonWriter, irnJwtClaims.getAudience());
            jsonWriter.name("iat");
            this.longAdapter.toJson(jsonWriter, Long.valueOf(irnJwtClaims.getIssuedAt()));
            jsonWriter.name("exp");
            this.longAdapter.toJson(jsonWriter, Long.valueOf(irnJwtClaims.getExpiration()));
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
