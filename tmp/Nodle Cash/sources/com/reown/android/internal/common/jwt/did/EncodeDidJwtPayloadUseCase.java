package com.reown.android.internal.common.jwt.did;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import com.reown.foundation.common.model.PublicKey;
import com.reown.foundation.util.jwt.JwtClaims;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0001\bJ\u0016\u0010\u0004\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00020\u0006H¦\u0002¢\u0006\u0002\u0010\u0007¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lcom/reown/android/internal/common/jwt/did/EncodeDidJwtPayloadUseCase;", "R", "Lcom/reown/foundation/util/jwt/JwtClaims;", "", "invoke", "params", "Lcom/reown/android/internal/common/jwt/did/EncodeDidJwtPayloadUseCase$Params;", "(Lcom/reown/android/internal/common/jwt/did/EncodeDidJwtPayloadUseCase$Params;)Lcom/reown/foundation/util/jwt/JwtClaims;", "Params", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface EncodeDidJwtPayloadUseCase<R extends JwtClaims> {

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u001c\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\u001d\u0010\rJ\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0007HÆ\u0003J\t\u0010 \u001a\u00020\tHÆ\u0003J8\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001¢\u0006\u0004\b\"\u0010#J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020(HÖ\u0001J\t\u0010)\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0015X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0016\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0011R\u0011\u0010\u0018\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0011R\u0011\u0010\u001a\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\r¨\u0006*"}, d2 = {"Lcom/reown/android/internal/common/jwt/did/EncodeDidJwtPayloadUseCase$Params;", "", "identityPublicKey", "Lcom/reown/foundation/common/model/PublicKey;", "keyserverUrl", "", "expirySourceDuration", "", "expiryTimeUnit", "Ljava/util/concurrent/TimeUnit;", "<init>", "(Ljava/lang/String;Ljava/lang/String;JLjava/util/concurrent/TimeUnit;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getIdentityPublicKey-uN_RPug", "()Ljava/lang/String;", "Ljava/lang/String;", "getKeyserverUrl", "getExpirySourceDuration", "()J", "getExpiryTimeUnit", "()Ljava/util/concurrent/TimeUnit;", "iatAndExp", "Lkotlin/Pair;", "issuedAt", "getIssuedAt", "expiration", "getExpiration", "issuer", "getIssuer", "component1", "component1-uN_RPug", "component2", "component3", "component4", "copy", "copy-B2nhYrw", "(Ljava/lang/String;Ljava/lang/String;JLjava/util/concurrent/TimeUnit;)Lcom/reown/android/internal/common/jwt/did/EncodeDidJwtPayloadUseCase$Params;", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params {
        private final long expirySourceDuration;
        @NotNull
        private final TimeUnit expiryTimeUnit;
        @NotNull
        private final Pair<Long, Long> iatAndExp;
        @NotNull
        private final String identityPublicKey;
        @NotNull
        private final String keyserverUrl;

        public /* synthetic */ Params(String str, String str2, long j2, TimeUnit timeUnit, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, j2, timeUnit);
        }

        /* renamed from: copy-B2nhYrw$default  reason: not valid java name */
        public static /* synthetic */ Params m8739copyB2nhYrw$default(Params params, String str, String str2, long j2, TimeUnit timeUnit, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = params.identityPublicKey;
            }
            if ((i3 & 2) != 0) {
                str2 = params.keyserverUrl;
            }
            String str3 = str2;
            if ((i3 & 4) != 0) {
                j2 = params.expirySourceDuration;
            }
            long j3 = j2;
            if ((i3 & 8) != 0) {
                timeUnit = params.expiryTimeUnit;
            }
            return params.m8741copyB2nhYrw(str, str3, j3, timeUnit);
        }

        @NotNull
        /* renamed from: component1-uN_RPug  reason: not valid java name */
        public final String m8740component1uN_RPug() {
            return this.identityPublicKey;
        }

        @NotNull
        public final String component2() {
            return this.keyserverUrl;
        }

        public final long component3() {
            return this.expirySourceDuration;
        }

        @NotNull
        public final TimeUnit component4() {
            return this.expiryTimeUnit;
        }

        @NotNull
        /* renamed from: copy-B2nhYrw  reason: not valid java name */
        public final Params m8741copyB2nhYrw(@NotNull String str, @NotNull String str2, long j2, @NotNull TimeUnit timeUnit) {
            Intrinsics.checkNotNullParameter(str, "identityPublicKey");
            Intrinsics.checkNotNullParameter(str2, "keyserverUrl");
            Intrinsics.checkNotNullParameter(timeUnit, "expiryTimeUnit");
            return new Params(str, str2, j2, timeUnit, (DefaultConstructorMarker) null);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Params)) {
                return false;
            }
            Params params = (Params) obj;
            return PublicKey.m8858equalsimpl0(this.identityPublicKey, params.identityPublicKey) && Intrinsics.areEqual((Object) this.keyserverUrl, (Object) params.keyserverUrl) && this.expirySourceDuration == params.expirySourceDuration && this.expiryTimeUnit == params.expiryTimeUnit;
        }

        public final long getExpiration() {
            return this.iatAndExp.getSecond().longValue();
        }

        public final long getExpirySourceDuration() {
            return this.expirySourceDuration;
        }

        @NotNull
        public final TimeUnit getExpiryTimeUnit() {
            return this.expiryTimeUnit;
        }

        @NotNull
        /* renamed from: getIdentityPublicKey-uN_RPug  reason: not valid java name */
        public final String m8742getIdentityPublicKeyuN_RPug() {
            return this.identityPublicKey;
        }

        public final long getIssuedAt() {
            return this.iatAndExp.getFirst().longValue();
        }

        @NotNull
        public final String getIssuer() {
            return JwtUtilsKt.encodeEd25519DidKey(PublicKey.m8859getKeyAsBytesimpl(this.identityPublicKey));
        }

        @NotNull
        public final String getKeyserverUrl() {
            return this.keyserverUrl;
        }

        public int hashCode() {
            return this.expiryTimeUnit.hashCode() + a.D(this.expirySourceDuration, a.i(this.keyserverUrl, PublicKey.m8860hashCodeimpl(this.identityPublicKey) * 31, 31), 31);
        }

        @NotNull
        public String toString() {
            String r02 = PublicKey.m8861toStringimpl(this.identityPublicKey);
            String str = this.keyserverUrl;
            long j2 = this.expirySourceDuration;
            TimeUnit timeUnit = this.expiryTimeUnit;
            StringBuilder l2 = C0118y.l("Params(identityPublicKey=", r02, ", keyserverUrl=", str, ", expirySourceDuration=");
            l2.append(j2);
            l2.append(", expiryTimeUnit=");
            l2.append(timeUnit);
            l2.append(")");
            return l2.toString();
        }

        private Params(String str, String str2, long j2, TimeUnit timeUnit) {
            Intrinsics.checkNotNullParameter(str, "identityPublicKey");
            Intrinsics.checkNotNullParameter(str2, "keyserverUrl");
            Intrinsics.checkNotNullParameter(timeUnit, "expiryTimeUnit");
            this.identityPublicKey = str;
            this.keyserverUrl = str2;
            this.expirySourceDuration = j2;
            this.expiryTimeUnit = timeUnit;
            this.iatAndExp = JwtUtilsKt.jwtIatAndExp$default(TimeUnit.SECONDS, j2, timeUnit, 0, 8, (Object) null);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Params(String str, String str2, long j2, TimeUnit timeUnit, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i3 & 4) != 0 ? 30 : j2, (i3 & 8) != 0 ? TimeUnit.DAYS : timeUnit, (DefaultConstructorMarker) null);
        }
    }

    @NotNull
    R invoke(@NotNull Params params);
}
