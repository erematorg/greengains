package com.reown.android.internal.common.model;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/reown/android/internal/common/model/SessionProposer;", "", "publicKey", "", "metadata", "Lcom/reown/android/internal/common/model/AppMetaData;", "<init>", "(Ljava/lang/String;Lcom/reown/android/internal/common/model/AppMetaData;)V", "getPublicKey", "()Ljava/lang/String;", "getMetadata", "()Lcom/reown/android/internal/common/model/AppMetaData;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SessionProposer {
    @NotNull
    private final AppMetaData metadata;
    @NotNull
    private final String publicKey;

    public SessionProposer(@NotNull @Json(name = "publicKey") String str, @NotNull @Json(name = "metadata") AppMetaData appMetaData) {
        Intrinsics.checkNotNullParameter(str, "publicKey");
        Intrinsics.checkNotNullParameter(appMetaData, TtmlNode.TAG_METADATA);
        this.publicKey = str;
        this.metadata = appMetaData;
    }

    public static /* synthetic */ SessionProposer copy$default(SessionProposer sessionProposer, String str, AppMetaData appMetaData, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = sessionProposer.publicKey;
        }
        if ((i3 & 2) != 0) {
            appMetaData = sessionProposer.metadata;
        }
        return sessionProposer.copy(str, appMetaData);
    }

    @NotNull
    public final String component1() {
        return this.publicKey;
    }

    @NotNull
    public final AppMetaData component2() {
        return this.metadata;
    }

    @NotNull
    public final SessionProposer copy(@NotNull @Json(name = "publicKey") String str, @NotNull @Json(name = "metadata") AppMetaData appMetaData) {
        Intrinsics.checkNotNullParameter(str, "publicKey");
        Intrinsics.checkNotNullParameter(appMetaData, TtmlNode.TAG_METADATA);
        return new SessionProposer(str, appMetaData);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SessionProposer)) {
            return false;
        }
        SessionProposer sessionProposer = (SessionProposer) obj;
        return Intrinsics.areEqual((Object) this.publicKey, (Object) sessionProposer.publicKey) && Intrinsics.areEqual((Object) this.metadata, (Object) sessionProposer.metadata);
    }

    @NotNull
    public final AppMetaData getMetadata() {
        return this.metadata;
    }

    @NotNull
    public final String getPublicKey() {
        return this.publicKey;
    }

    public int hashCode() {
        return this.metadata.hashCode() + (this.publicKey.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        String str = this.publicKey;
        AppMetaData appMetaData = this.metadata;
        return "SessionProposer(publicKey=" + str + ", metadata=" + appMetaData + ")";
    }
}
