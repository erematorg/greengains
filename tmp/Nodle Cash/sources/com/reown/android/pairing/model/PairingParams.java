package com.reown.android.pairing.model;

import com.reown.android.internal.common.model.type.ClientParams;
import com.reown.android.push.notifications.PushMessagingService;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.IntCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/android/pairing/model/PairingParams;", "Lcom/reown/android/internal/common/model/type/ClientParams;", "<init>", "()V", "DeleteParams", "PingParams", "Lcom/reown/android/pairing/model/PairingParams$DeleteParams;", "Lcom/reown/android/pairing/model/PairingParams$PingParams;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class PairingParams implements ClientParams {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/reown/android/pairing/model/PairingParams$PingParams;", "Lcom/reown/android/pairing/model/PairingParams;", "<init>", "()V", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class PingParams extends PairingParams {
        public PingParams() {
            super((DefaultConstructorMarker) null);
        }
    }

    public /* synthetic */ PairingParams(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/reown/android/pairing/model/PairingParams$DeleteParams;", "Lcom/reown/android/pairing/model/PairingParams;", "code", "", "message", "", "<init>", "(ILjava/lang/String;)V", "getCode", "()I", "getMessage", "()Ljava/lang/String;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class DeleteParams extends PairingParams {
        private final int code;
        @NotNull
        private final String message;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ DeleteParams(int i3, String str, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this((i4 & 1) != 0 ? Intrinsics.checkNotNullParameter(IntCompanionObject.INSTANCE, "<this>") : i3, str);
        }

        public final int getCode() {
            return this.code;
        }

        @NotNull
        public final String getMessage() {
            return this.message;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public DeleteParams(@Json(name = "code") int i3, @NotNull @Json(name = "message") String str) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
            this.code = i3;
            this.message = str;
        }
    }

    private PairingParams() {
    }
}
