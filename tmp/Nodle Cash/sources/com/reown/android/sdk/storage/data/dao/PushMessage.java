package com.reown.android.sdk.storage.data.dao;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import com.reown.android.push.notifications.PushMessagingService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/PushMessage;", "", "id", "", "topic", "blob", "tag", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getId", "()Ljava/lang/String;", "getTopic", "getBlob", "getTag", "()J", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PushMessage {
    @NotNull
    private final String blob;
    @NotNull
    private final String id;
    private final long tag;
    @NotNull
    private final String topic;

    public PushMessage(@NotNull String str, @NotNull String str2, @NotNull String str3, long j2) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str3, PushMessagingService.KEY_BLOB);
        this.id = str;
        this.topic = str2;
        this.blob = str3;
        this.tag = j2;
    }

    public static /* synthetic */ PushMessage copy$default(PushMessage pushMessage, String str, String str2, String str3, long j2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = pushMessage.id;
        }
        if ((i3 & 2) != 0) {
            str2 = pushMessage.topic;
        }
        String str4 = str2;
        if ((i3 & 4) != 0) {
            str3 = pushMessage.blob;
        }
        String str5 = str3;
        if ((i3 & 8) != 0) {
            j2 = pushMessage.tag;
        }
        return pushMessage.copy(str, str4, str5, j2);
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

    public final long component4() {
        return this.tag;
    }

    @NotNull
    public final PushMessage copy(@NotNull String str, @NotNull String str2, @NotNull String str3, long j2) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str3, PushMessagingService.KEY_BLOB);
        return new PushMessage(str, str2, str3, j2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PushMessage)) {
            return false;
        }
        PushMessage pushMessage = (PushMessage) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) pushMessage.id) && Intrinsics.areEqual((Object) this.topic, (Object) pushMessage.topic) && Intrinsics.areEqual((Object) this.blob, (Object) pushMessage.blob) && this.tag == pushMessage.tag;
    }

    @NotNull
    public final String getBlob() {
        return this.blob;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    public final long getTag() {
        return this.tag;
    }

    @NotNull
    public final String getTopic() {
        return this.topic;
    }

    public int hashCode() {
        return Long.hashCode(this.tag) + a.i(this.blob, a.i(this.topic, this.id.hashCode() * 31, 31), 31);
    }

    @NotNull
    public String toString() {
        String str = this.id;
        String str2 = this.topic;
        String str3 = this.blob;
        long j2 = this.tag;
        StringBuilder l2 = C0118y.l("PushMessage(id=", str, ", topic=", str2, ", blob=");
        l2.append(str3);
        l2.append(", tag=");
        l2.append(j2);
        l2.append(")");
        return l2.toString();
    }
}
