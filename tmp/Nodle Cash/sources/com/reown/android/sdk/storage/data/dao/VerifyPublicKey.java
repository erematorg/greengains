package com.reown.android.sdk.storage.data.dao;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/VerifyPublicKey;", "", "key", "", "expires_at", "", "<init>", "(Ljava/lang/String;J)V", "getKey", "()Ljava/lang/String;", "getExpires_at", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VerifyPublicKey {
    private final long expires_at;
    @NotNull
    private final String key;

    public VerifyPublicKey(@NotNull String str, long j2) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        this.key = str;
        this.expires_at = j2;
    }

    public static /* synthetic */ VerifyPublicKey copy$default(VerifyPublicKey verifyPublicKey, String str, long j2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = verifyPublicKey.key;
        }
        if ((i3 & 2) != 0) {
            j2 = verifyPublicKey.expires_at;
        }
        return verifyPublicKey.copy(str, j2);
    }

    @NotNull
    public final String component1() {
        return this.key;
    }

    public final long component2() {
        return this.expires_at;
    }

    @NotNull
    public final VerifyPublicKey copy(@NotNull String str, long j2) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        return new VerifyPublicKey(str, j2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VerifyPublicKey)) {
            return false;
        }
        VerifyPublicKey verifyPublicKey = (VerifyPublicKey) obj;
        return Intrinsics.areEqual((Object) this.key, (Object) verifyPublicKey.key) && this.expires_at == verifyPublicKey.expires_at;
    }

    public final long getExpires_at() {
        return this.expires_at;
    }

    @NotNull
    public final String getKey() {
        return this.key;
    }

    public int hashCode() {
        return Long.hashCode(this.expires_at) + (this.key.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        StringBuilder i3 = a.i(this.expires_at, "VerifyPublicKey(key=", this.key, ", expires_at=");
        i3.append(")");
        return i3.toString();
    }
}
