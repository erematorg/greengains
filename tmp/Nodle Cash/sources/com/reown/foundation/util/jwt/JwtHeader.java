package com.reown.foundation.util.jwt;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0007\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u001c\u0010\n\u001a\u00020\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/reown/foundation/util/jwt/JwtHeader;", "", "algorithm", "", "type", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getAlgorithm", "()Ljava/lang/String;", "getType", "encoded", "getEncoded$annotations", "()V", "getEncoded", "Companion", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JwtHeader {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    @NotNull
    public static final JwtHeader EdDSA = new JwtHeader("EdDSA", "JWT");
    @NotNull
    private final String algorithm;
    @NotNull
    private final String encoded = JwtUtilsKt.encodeJSON(this);
    @NotNull
    private final String type;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/reown/foundation/util/jwt/JwtHeader$Companion;", "", "<init>", "()V", "EdDSA", "Lcom/reown/foundation/util/jwt/JwtHeader;", "getEdDSA", "()Lcom/reown/foundation/util/jwt/JwtHeader;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final JwtHeader getEdDSA() {
            return JwtHeader.EdDSA;
        }

        private Companion() {
        }
    }

    public JwtHeader(@NotNull @Json(name = "alg") String str, @NotNull @Json(name = "typ") String str2) {
        Intrinsics.checkNotNullParameter(str, "algorithm");
        Intrinsics.checkNotNullParameter(str2, "type");
        this.algorithm = str;
        this.type = str2;
    }

    @Json(ignore = true)
    public static /* synthetic */ void getEncoded$annotations() {
    }

    @NotNull
    public final String getAlgorithm() {
        return this.algorithm;
    }

    @NotNull
    public final String getEncoded() {
        return this.encoded;
    }

    @NotNull
    public final String getType() {
        return this.type;
    }
}
