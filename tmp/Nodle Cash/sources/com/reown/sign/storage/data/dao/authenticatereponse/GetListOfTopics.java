package com.reown.sign.storage.data.dao.authenticatereponse;

import androidx.camera.camera2.internal.C0118y;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/reown/sign/storage/data/dao/authenticatereponse/GetListOfTopics;", "", "pairingTopic", "", "responseTopic", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getPairingTopic", "()Ljava/lang/String;", "getResponseTopic", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GetListOfTopics {
    @NotNull
    private final String pairingTopic;
    @NotNull
    private final String responseTopic;

    public GetListOfTopics(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "pairingTopic");
        Intrinsics.checkNotNullParameter(str2, "responseTopic");
        this.pairingTopic = str;
        this.responseTopic = str2;
    }

    public static /* synthetic */ GetListOfTopics copy$default(GetListOfTopics getListOfTopics, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = getListOfTopics.pairingTopic;
        }
        if ((i3 & 2) != 0) {
            str2 = getListOfTopics.responseTopic;
        }
        return getListOfTopics.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.pairingTopic;
    }

    @NotNull
    public final String component2() {
        return this.responseTopic;
    }

    @NotNull
    public final GetListOfTopics copy(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "pairingTopic");
        Intrinsics.checkNotNullParameter(str2, "responseTopic");
        return new GetListOfTopics(str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GetListOfTopics)) {
            return false;
        }
        GetListOfTopics getListOfTopics = (GetListOfTopics) obj;
        return Intrinsics.areEqual((Object) this.pairingTopic, (Object) getListOfTopics.pairingTopic) && Intrinsics.areEqual((Object) this.responseTopic, (Object) getListOfTopics.responseTopic);
    }

    @NotNull
    public final String getPairingTopic() {
        return this.pairingTopic;
    }

    @NotNull
    public final String getResponseTopic() {
        return this.responseTopic;
    }

    public int hashCode() {
        return this.responseTopic.hashCode() + (this.pairingTopic.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        return C0118y.g("GetListOfTopics(pairingTopic=", this.pairingTopic, ", responseTopic=", this.responseTopic, ")");
    }
}
