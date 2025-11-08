package com.reown.android.pairing.engine.model;

import androidx.camera.camera2.internal.C0118y;
import com.reown.android.internal.common.model.Pairing;
import com.reown.android.push.notifications.PushMessagingService;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/reown/android/pairing/engine/model/EngineDO;", "", "<init>", "()V", "PairingDelete", "PairingExpire", "PairingState", "Lcom/reown/android/pairing/engine/model/EngineDO$PairingDelete;", "Lcom/reown/android/pairing/engine/model/EngineDO$PairingExpire;", "Lcom/reown/android/pairing/engine/model/EngineDO$PairingState;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class EngineDO {

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/reown/android/pairing/engine/model/EngineDO$PairingDelete;", "Lcom/reown/android/pairing/engine/model/EngineDO;", "topic", "", "reason", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "getReason", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @Deprecated(message = "This data object has been deprecated. It will be removed soon.")
    public static final class PairingDelete extends EngineDO {
        @NotNull
        private final String reason;
        @NotNull
        private final String topic;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public PairingDelete(@NotNull String str, @NotNull String str2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(str2, "reason");
            this.topic = str;
            this.reason = str2;
        }

        public static /* synthetic */ PairingDelete copy$default(PairingDelete pairingDelete, String str, String str2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = pairingDelete.topic;
            }
            if ((i3 & 2) != 0) {
                str2 = pairingDelete.reason;
            }
            return pairingDelete.copy(str, str2);
        }

        @NotNull
        public final String component1() {
            return this.topic;
        }

        @NotNull
        public final String component2() {
            return this.reason;
        }

        @NotNull
        public final PairingDelete copy(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(str2, "reason");
            return new PairingDelete(str, str2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PairingDelete)) {
                return false;
            }
            PairingDelete pairingDelete = (PairingDelete) obj;
            return Intrinsics.areEqual((Object) this.topic, (Object) pairingDelete.topic) && Intrinsics.areEqual((Object) this.reason, (Object) pairingDelete.reason);
        }

        @NotNull
        public final String getReason() {
            return this.reason;
        }

        @NotNull
        public final String getTopic() {
            return this.topic;
        }

        public int hashCode() {
            return this.reason.hashCode() + (this.topic.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            return C0118y.g("PairingDelete(topic=", this.topic, ", reason=", this.reason, ")");
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/android/pairing/engine/model/EngineDO$PairingExpire;", "Lcom/reown/android/pairing/engine/model/EngineDO;", "pairing", "Lcom/reown/android/internal/common/model/Pairing;", "<init>", "(Lcom/reown/android/internal/common/model/Pairing;)V", "getPairing", "()Lcom/reown/android/internal/common/model/Pairing;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @Deprecated(message = "This data object has been deprecated. It will be removed soon.")
    public static final class PairingExpire extends EngineDO {
        @NotNull
        private final Pairing pairing;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public PairingExpire(@NotNull Pairing pairing2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(pairing2, "pairing");
            this.pairing = pairing2;
        }

        public static /* synthetic */ PairingExpire copy$default(PairingExpire pairingExpire, Pairing pairing2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                pairing2 = pairingExpire.pairing;
            }
            return pairingExpire.copy(pairing2);
        }

        @NotNull
        public final Pairing component1() {
            return this.pairing;
        }

        @NotNull
        public final PairingExpire copy(@NotNull Pairing pairing2) {
            Intrinsics.checkNotNullParameter(pairing2, "pairing");
            return new PairingExpire(pairing2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof PairingExpire) && Intrinsics.areEqual((Object) this.pairing, (Object) ((PairingExpire) obj).pairing);
        }

        @NotNull
        public final Pairing getPairing() {
            return this.pairing;
        }

        public int hashCode() {
            return this.pairing.hashCode();
        }

        @NotNull
        public String toString() {
            Pairing pairing2 = this.pairing;
            return "PairingExpire(pairing=" + pairing2 + ")";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/reown/android/pairing/engine/model/EngineDO$PairingState;", "Lcom/reown/android/pairing/engine/model/EngineDO;", "isPairingState", "", "<init>", "(Z)V", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class PairingState extends EngineDO {
        private final boolean isPairingState;

        public PairingState(boolean z2) {
            super((DefaultConstructorMarker) null);
            this.isPairingState = z2;
        }

        public static /* synthetic */ PairingState copy$default(PairingState pairingState, boolean z2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                z2 = pairingState.isPairingState;
            }
            return pairingState.copy(z2);
        }

        public final boolean component1() {
            return this.isPairingState;
        }

        @NotNull
        public final PairingState copy(boolean z2) {
            return new PairingState(z2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof PairingState) && this.isPairingState == ((PairingState) obj).isPairingState;
        }

        public int hashCode() {
            return Boolean.hashCode(this.isPairingState);
        }

        public final boolean isPairingState() {
            return this.isPairingState;
        }

        @NotNull
        public String toString() {
            boolean z2 = this.isPairingState;
            return "PairingState(isPairingState=" + z2 + ")";
        }
    }

    public /* synthetic */ EngineDO(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private EngineDO() {
    }
}
