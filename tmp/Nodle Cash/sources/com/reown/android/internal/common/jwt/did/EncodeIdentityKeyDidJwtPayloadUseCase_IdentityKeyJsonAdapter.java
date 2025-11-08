package com.reown.android.internal.common.jwt.did;

import A.a;
import com.reown.android.internal.common.jwt.did.EncodeIdentityKeyDidJwtPayloadUseCase;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import java.lang.reflect.Constructor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000f\u001a\u00020\nH\u0016J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/reown/android/internal/common/jwt/did/EncodeIdentityKeyDidJwtPayloadUseCase_IdentityKeyJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/internal/common/jwt/did/EncodeIdentityKeyDidJwtPayloadUseCase$IdentityKey;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "longAdapter", "", "constructorRef", "Ljava/lang/reflect/Constructor;", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nEncodeIdentityKeyDidJwtPayloadUseCase_IdentityKeyJsonAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EncodeIdentityKeyDidJwtPayloadUseCase_IdentityKeyJsonAdapter.kt\ncom/reown/android/internal/common/jwt/did/EncodeIdentityKeyDidJwtPayloadUseCase_IdentityKeyJsonAdapter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,130:1\n1#2:131\n*E\n"})
public final class EncodeIdentityKeyDidJwtPayloadUseCase_IdentityKeyJsonAdapter extends JsonAdapter<EncodeIdentityKeyDidJwtPayloadUseCase.IdentityKey> {
    @Nullable
    private volatile Constructor<EncodeIdentityKeyDidJwtPayloadUseCase.IdentityKey> constructorRef;
    @NotNull
    private final JsonAdapter<Long> longAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public EncodeIdentityKeyDidJwtPayloadUseCase_IdentityKeyJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("iss", "aud", "iat", "exp", JwtUtilsKt.DID_METHOD_PKH, "act");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.stringAdapter = a.h(moshi, String.class, "issuer", "adapter(...)");
        this.longAdapter = a.h(moshi, Long.TYPE, "issuedAt", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(71, "GeneratedJsonAdapter(EncodeIdentityKeyDidJwtPayloadUseCase.IdentityKey)");
    }

    @NotNull
    public EncodeIdentityKeyDidJwtPayloadUseCase.IdentityKey fromJson(@NotNull JsonReader jsonReader) {
        String str;
        JsonReader jsonReader2 = jsonReader;
        Intrinsics.checkNotNullParameter(jsonReader2, "reader");
        jsonReader.beginObject();
        String str2 = null;
        int i3 = -1;
        String str3 = null;
        Long l2 = null;
        Long l3 = null;
        String str4 = null;
        String str5 = null;
        while (true) {
            String str6 = str5;
            String str7 = str4;
            Long l4 = l3;
            if (jsonReader.hasNext()) {
                switch (jsonReader2.selectName(this.options)) {
                    case -1:
                        jsonReader.skipName();
                        jsonReader.skipValue();
                        break;
                    case 0:
                        str2 = this.stringAdapter.fromJson(jsonReader2);
                        if (str2 == null) {
                            throw Util.unexpectedNull("issuer", "iss", jsonReader2);
                        }
                        break;
                    case 1:
                        str3 = this.stringAdapter.fromJson(jsonReader2);
                        if (str3 == null) {
                            throw Util.unexpectedNull("audience", "aud", jsonReader2);
                        }
                        break;
                    case 2:
                        l2 = this.longAdapter.fromJson(jsonReader2);
                        if (l2 == null) {
                            throw Util.unexpectedNull("issuedAt", "iat", jsonReader2);
                        }
                        break;
                    case 3:
                        l3 = this.longAdapter.fromJson(jsonReader2);
                        if (l3 != null) {
                            str5 = str6;
                            str4 = str7;
                            continue;
                        } else {
                            throw Util.unexpectedNull("expiration", "exp", jsonReader2);
                        }
                    case 4:
                        str4 = this.stringAdapter.fromJson(jsonReader2);
                        if (str4 != null) {
                            str5 = str6;
                            break;
                        } else {
                            throw Util.unexpectedNull(JwtUtilsKt.DID_METHOD_PKH, JwtUtilsKt.DID_METHOD_PKH, jsonReader2);
                        }
                    case 5:
                        str5 = this.stringAdapter.fromJson(jsonReader2);
                        if (str5 != null) {
                            str4 = str7;
                            l3 = l4;
                            i3 = -33;
                            continue;
                        } else {
                            throw Util.unexpectedNull("act", "act", jsonReader2);
                        }
                }
                str5 = str6;
                str4 = str7;
                l3 = l4;
            } else {
                jsonReader.endObject();
                if (i3 != -33) {
                    Constructor<EncodeIdentityKeyDidJwtPayloadUseCase.IdentityKey> constructor = this.constructorRef;
                    if (constructor == null) {
                        Class cls = Long.TYPE;
                        str = "iss";
                        Class<EncodeIdentityKeyDidJwtPayloadUseCase.IdentityKey> cls2 = EncodeIdentityKeyDidJwtPayloadUseCase.IdentityKey.class;
                        constructor = cls2.getDeclaredConstructor(new Class[]{String.class, String.class, cls, cls, String.class, String.class, Integer.TYPE, Util.DEFAULT_CONSTRUCTOR_MARKER});
                        this.constructorRef = constructor;
                        Intrinsics.checkNotNullExpressionValue(constructor, "also(...)");
                    } else {
                        str = "iss";
                    }
                    Constructor<EncodeIdentityKeyDidJwtPayloadUseCase.IdentityKey> constructor2 = constructor;
                    if (str2 == null) {
                        throw Util.missingProperty("issuer", str, jsonReader2);
                    } else if (str3 == null) {
                        throw Util.missingProperty("audience", "aud", jsonReader2);
                    } else if (l2 == null) {
                        throw Util.missingProperty("issuedAt", "iat", jsonReader2);
                    } else if (l4 == null) {
                        throw Util.missingProperty("expiration", "exp", jsonReader2);
                    } else if (str7 != null) {
                        EncodeIdentityKeyDidJwtPayloadUseCase.IdentityKey newInstance = constructor2.newInstance(new Object[]{str2, str3, l2, l4, str7, str6, Integer.valueOf(i3), null});
                        Intrinsics.checkNotNullExpressionValue(newInstance, "newInstance(...)");
                        return newInstance;
                    } else {
                        throw Util.missingProperty(JwtUtilsKt.DID_METHOD_PKH, JwtUtilsKt.DID_METHOD_PKH, jsonReader2);
                    }
                } else if (str2 == null) {
                    throw Util.missingProperty("issuer", "iss", jsonReader2);
                } else if (str3 == null) {
                    throw Util.missingProperty("audience", "aud", jsonReader2);
                } else if (l2 != null) {
                    long longValue = l2.longValue();
                    if (l4 != null) {
                        long longValue2 = l4.longValue();
                        if (str7 != null) {
                            String str8 = str6;
                            Intrinsics.checkNotNull(str8, "null cannot be cast to non-null type kotlin.String");
                            return new EncodeIdentityKeyDidJwtPayloadUseCase.IdentityKey(str2, str3, longValue, longValue2, str7, str8);
                        }
                        throw Util.missingProperty(JwtUtilsKt.DID_METHOD_PKH, JwtUtilsKt.DID_METHOD_PKH, jsonReader2);
                    }
                    throw Util.missingProperty("expiration", "exp", jsonReader2);
                } else {
                    throw Util.missingProperty("issuedAt", "iat", jsonReader2);
                }
            }
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable EncodeIdentityKeyDidJwtPayloadUseCase.IdentityKey identityKey) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (identityKey != null) {
            jsonWriter.beginObject();
            jsonWriter.name("iss");
            this.stringAdapter.toJson(jsonWriter, identityKey.getIssuer());
            jsonWriter.name("aud");
            this.stringAdapter.toJson(jsonWriter, identityKey.getAudience());
            jsonWriter.name("iat");
            this.longAdapter.toJson(jsonWriter, Long.valueOf(identityKey.getIssuedAt()));
            jsonWriter.name("exp");
            this.longAdapter.toJson(jsonWriter, Long.valueOf(identityKey.getExpiration()));
            jsonWriter.name(JwtUtilsKt.DID_METHOD_PKH);
            this.stringAdapter.toJson(jsonWriter, identityKey.getPkh());
            jsonWriter.name("act");
            this.stringAdapter.toJson(jsonWriter, identityKey.getAct());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
