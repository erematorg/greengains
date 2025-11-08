package com.reown.android.internal.common.json_rpc.domain.relay;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.foundation.common.model.Topic;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0017\u001a\u00020\bHÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J=\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006 "}, d2 = {"Lcom/reown/android/internal/common/json_rpc/domain/relay/Subscription;", "", "decryptedMessage", "", "encryptedMessage", "topic", "Lcom/reown/foundation/common/model/Topic;", "publishedAt", "", "attestation", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/reown/foundation/common/model/Topic;JLjava/lang/String;)V", "getDecryptedMessage", "()Ljava/lang/String;", "getEncryptedMessage", "getTopic", "()Lcom/reown/foundation/common/model/Topic;", "getPublishedAt", "()J", "getAttestation", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Subscription {
    @Nullable
    private final String attestation;
    @NotNull
    private final String decryptedMessage;
    @NotNull
    private final String encryptedMessage;
    private final long publishedAt;
    @NotNull
    private final Topic topic;

    public Subscription(@NotNull String str, @NotNull String str2, @NotNull Topic topic2, long j2, @Nullable String str3) {
        Intrinsics.checkNotNullParameter(str, "decryptedMessage");
        Intrinsics.checkNotNullParameter(str2, "encryptedMessage");
        Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
        this.decryptedMessage = str;
        this.encryptedMessage = str2;
        this.topic = topic2;
        this.publishedAt = j2;
        this.attestation = str3;
    }

    public static /* synthetic */ Subscription copy$default(Subscription subscription, String str, String str2, Topic topic2, long j2, String str3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = subscription.decryptedMessage;
        }
        if ((i3 & 2) != 0) {
            str2 = subscription.encryptedMessage;
        }
        String str4 = str2;
        if ((i3 & 4) != 0) {
            topic2 = subscription.topic;
        }
        Topic topic3 = topic2;
        if ((i3 & 8) != 0) {
            j2 = subscription.publishedAt;
        }
        long j3 = j2;
        if ((i3 & 16) != 0) {
            str3 = subscription.attestation;
        }
        return subscription.copy(str, str4, topic3, j3, str3);
    }

    @NotNull
    public final String component1() {
        return this.decryptedMessage;
    }

    @NotNull
    public final String component2() {
        return this.encryptedMessage;
    }

    @NotNull
    public final Topic component3() {
        return this.topic;
    }

    public final long component4() {
        return this.publishedAt;
    }

    @Nullable
    public final String component5() {
        return this.attestation;
    }

    @NotNull
    public final Subscription copy(@NotNull String str, @NotNull String str2, @NotNull Topic topic2, long j2, @Nullable String str3) {
        Intrinsics.checkNotNullParameter(str, "decryptedMessage");
        Intrinsics.checkNotNullParameter(str2, "encryptedMessage");
        Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
        return new Subscription(str, str2, topic2, j2, str3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Subscription)) {
            return false;
        }
        Subscription subscription = (Subscription) obj;
        return Intrinsics.areEqual((Object) this.decryptedMessage, (Object) subscription.decryptedMessage) && Intrinsics.areEqual((Object) this.encryptedMessage, (Object) subscription.encryptedMessage) && Intrinsics.areEqual((Object) this.topic, (Object) subscription.topic) && this.publishedAt == subscription.publishedAt && Intrinsics.areEqual((Object) this.attestation, (Object) subscription.attestation);
    }

    @Nullable
    public final String getAttestation() {
        return this.attestation;
    }

    @NotNull
    public final String getDecryptedMessage() {
        return this.decryptedMessage;
    }

    @NotNull
    public final String getEncryptedMessage() {
        return this.encryptedMessage;
    }

    public final long getPublishedAt() {
        return this.publishedAt;
    }

    @NotNull
    public final Topic getTopic() {
        return this.topic;
    }

    public int hashCode() {
        int D2 = a.D(this.publishedAt, (this.topic.hashCode() + a.i(this.encryptedMessage, this.decryptedMessage.hashCode() * 31, 31)) * 31, 31);
        String str = this.attestation;
        return D2 + (str == null ? 0 : str.hashCode());
    }

    @NotNull
    public String toString() {
        String str = this.decryptedMessage;
        String str2 = this.encryptedMessage;
        Topic topic2 = this.topic;
        long j2 = this.publishedAt;
        String str3 = this.attestation;
        StringBuilder l2 = C0118y.l("Subscription(decryptedMessage=", str, ", encryptedMessage=", str2, ", topic=");
        l2.append(topic2);
        l2.append(", publishedAt=");
        l2.append(j2);
        return C0118y.j(l2, ", attestation=", str3, ")");
    }
}
