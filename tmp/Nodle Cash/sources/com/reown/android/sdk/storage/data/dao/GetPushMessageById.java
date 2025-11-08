package com.reown.android.sdk.storage.data.dao;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import com.reown.android.push.notifications.PushMessagingService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/GetPushMessageById;", "", "id", "", "topic", "blob", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getTopic", "getBlob", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GetPushMessageById {
    @NotNull
    private final String blob;
    @NotNull
    private final String id;
    @NotNull
    private final String topic;

    public GetPushMessageById(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str3, PushMessagingService.KEY_BLOB);
        this.id = str;
        this.topic = str2;
        this.blob = str3;
    }

    public static /* synthetic */ GetPushMessageById copy$default(GetPushMessageById getPushMessageById, String str, String str2, String str3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = getPushMessageById.id;
        }
        if ((i3 & 2) != 0) {
            str2 = getPushMessageById.topic;
        }
        if ((i3 & 4) != 0) {
            str3 = getPushMessageById.blob;
        }
        return getPushMessageById.copy(str, str2, str3);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.topic;
    }

    @NotNull
    public final String component3() {
        return this.blob;
    }

    @NotNull
    public final GetPushMessageById copy(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str3, PushMessagingService.KEY_BLOB);
        return new GetPushMessageById(str, str2, str3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GetPushMessageById)) {
            return false;
        }
        GetPushMessageById getPushMessageById = (GetPushMessageById) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) getPushMessageById.id) && Intrinsics.areEqual((Object) this.topic, (Object) getPushMessageById.topic) && Intrinsics.areEqual((Object) this.blob, (Object) getPushMessageById.blob);
    }

    @NotNull
    public final String getBlob() {
        return this.blob;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @NotNull
    public final String getTopic() {
        return this.topic;
    }

    public int hashCode() {
        return this.blob.hashCode() + a.i(this.topic, this.id.hashCode() * 31, 31);
    }

    @NotNull
    public String toString() {
        String str = this.id;
        String str2 = this.topic;
        return A.a.n(C0118y.l("GetPushMessageById(id=", str, ", topic=", str2, ", blob="), this.blob, ")");
    }
}
