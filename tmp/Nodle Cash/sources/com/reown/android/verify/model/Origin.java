package com.reown.android.verify.model;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\fJ.\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u0005\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/reown/android/verify/model/Origin;", "", "attestationId", "", "origin", "isScam", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getAttestationId", "()Ljava/lang/String;", "getOrigin", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/reown/android/verify/model/Origin;", "equals", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Origin {
    @NotNull
    private final String attestationId;
    @Nullable
    private final Boolean isScam;
    @NotNull
    private final String origin;

    public Origin(@NotNull String str, @NotNull String str2, @Nullable Boolean bool) {
        Intrinsics.checkNotNullParameter(str, "attestationId");
        Intrinsics.checkNotNullParameter(str2, "origin");
        this.attestationId = str;
        this.origin = str2;
        this.isScam = bool;
    }

    public static /* synthetic */ Origin copy$default(Origin origin2, String str, String str2, Boolean bool, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = origin2.attestationId;
        }
        if ((i3 & 2) != 0) {
            str2 = origin2.origin;
        }
        if ((i3 & 4) != 0) {
            bool = origin2.isScam;
        }
        return origin2.copy(str, str2, bool);
    }

    @NotNull
    public final String component1() {
        return this.attestationId;
    }

    @NotNull
    public final String component2() {
        return this.origin;
    }

    @Nullable
    public final Boolean component3() {
        return this.isScam;
    }

    @NotNull
    public final Origin copy(@NotNull String str, @NotNull String str2, @Nullable Boolean bool) {
        Intrinsics.checkNotNullParameter(str, "attestationId");
        Intrinsics.checkNotNullParameter(str2, "origin");
        return new Origin(str, str2, bool);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Origin)) {
            return false;
        }
        Origin origin2 = (Origin) obj;
        return Intrinsics.areEqual((Object) this.attestationId, (Object) origin2.attestationId) && Intrinsics.areEqual((Object) this.origin, (Object) origin2.origin) && Intrinsics.areEqual((Object) this.isScam, (Object) origin2.isScam);
    }

    @NotNull
    public final String getAttestationId() {
        return this.attestationId;
    }

    @NotNull
    public final String getOrigin() {
        return this.origin;
    }

    public int hashCode() {
        int i3 = a.i(this.origin, this.attestationId.hashCode() * 31, 31);
        Boolean bool = this.isScam;
        return i3 + (bool == null ? 0 : bool.hashCode());
    }

    @Nullable
    public final Boolean isScam() {
        return this.isScam;
    }

    @NotNull
    public String toString() {
        String str = this.attestationId;
        String str2 = this.origin;
        Boolean bool = this.isScam;
        StringBuilder l2 = C0118y.l("Origin(attestationId=", str, ", origin=", str2, ", isScam=");
        l2.append(bool);
        l2.append(")");
        return l2.toString();
    }
}
