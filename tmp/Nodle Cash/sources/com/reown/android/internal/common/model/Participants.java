package com.reown.android.internal.common.model;

import androidx.camera.camera2.internal.C0118y;
import com.reown.foundation.common.model.PublicKey;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u000b\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\f\u0010\bJ\u0010\u0010\r\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\u000e\u0010\bJ$\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\b¨\u0006\u0019"}, d2 = {"Lcom/reown/android/internal/common/model/Participants;", "", "senderPublicKey", "Lcom/reown/foundation/common/model/PublicKey;", "receiverPublicKey", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getSenderPublicKey-uN_RPug", "()Ljava/lang/String;", "Ljava/lang/String;", "getReceiverPublicKey-uN_RPug", "component1", "component1-uN_RPug", "component2", "component2-uN_RPug", "copy", "copy-V_lFtQw", "(Ljava/lang/String;Ljava/lang/String;)Lcom/reown/android/internal/common/model/Participants;", "equals", "", "other", "hashCode", "", "toString", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Participants {
    @NotNull
    private final String receiverPublicKey;
    @NotNull
    private final String senderPublicKey;

    public /* synthetic */ Participants(String str, String str2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2);
    }

    /* renamed from: copy-V_lFtQw$default  reason: not valid java name */
    public static /* synthetic */ Participants m8771copyV_lFtQw$default(Participants participants, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = participants.senderPublicKey;
        }
        if ((i3 & 2) != 0) {
            str2 = participants.receiverPublicKey;
        }
        return participants.m8774copyV_lFtQw(str, str2);
    }

    @NotNull
    /* renamed from: component1-uN_RPug  reason: not valid java name */
    public final String m8772component1uN_RPug() {
        return this.senderPublicKey;
    }

    @NotNull
    /* renamed from: component2-uN_RPug  reason: not valid java name */
    public final String m8773component2uN_RPug() {
        return this.receiverPublicKey;
    }

    @NotNull
    /* renamed from: copy-V_lFtQw  reason: not valid java name */
    public final Participants m8774copyV_lFtQw(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "senderPublicKey");
        Intrinsics.checkNotNullParameter(str2, "receiverPublicKey");
        return new Participants(str, str2, (DefaultConstructorMarker) null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Participants)) {
            return false;
        }
        Participants participants = (Participants) obj;
        return PublicKey.m8858equalsimpl0(this.senderPublicKey, participants.senderPublicKey) && PublicKey.m8858equalsimpl0(this.receiverPublicKey, participants.receiverPublicKey);
    }

    @NotNull
    /* renamed from: getReceiverPublicKey-uN_RPug  reason: not valid java name */
    public final String m8775getReceiverPublicKeyuN_RPug() {
        return this.receiverPublicKey;
    }

    @NotNull
    /* renamed from: getSenderPublicKey-uN_RPug  reason: not valid java name */
    public final String m8776getSenderPublicKeyuN_RPug() {
        return this.senderPublicKey;
    }

    public int hashCode() {
        return PublicKey.m8860hashCodeimpl(this.receiverPublicKey) + (PublicKey.m8860hashCodeimpl(this.senderPublicKey) * 31);
    }

    @NotNull
    public String toString() {
        return C0118y.g("Participants(senderPublicKey=", PublicKey.m8861toStringimpl(this.senderPublicKey), ", receiverPublicKey=", PublicKey.m8861toStringimpl(this.receiverPublicKey), ")");
    }

    private Participants(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "senderPublicKey");
        Intrinsics.checkNotNullParameter(str2, "receiverPublicKey");
        this.senderPublicKey = str;
        this.receiverPublicKey = str2;
    }
}
