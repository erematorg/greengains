package com.reown.android.internal.common.model.params;

import A.a;
import com.reown.android.internal.common.model.type.ClientParams;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0001\u0002\u0001\u0001\u0003¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lcom/reown/android/internal/common/model/params/CoreChatParams;", "Lcom/reown/android/internal/common/model/type/ClientParams;", "ReceiptParams", "Lcom/reown/android/internal/common/model/params/CoreChatParams$ReceiptParams;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface CoreChatParams extends ClientParams {

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/android/internal/common/model/params/CoreChatParams$ReceiptParams;", "Lcom/reown/android/internal/common/model/params/CoreChatParams;", "receiptAuth", "", "<init>", "(Ljava/lang/String;)V", "getReceiptAuth", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ReceiptParams implements CoreChatParams {
        @NotNull
        private final String receiptAuth;

        public ReceiptParams(@NotNull @Json(name = "receiptAuth") String str) {
            Intrinsics.checkNotNullParameter(str, "receiptAuth");
            this.receiptAuth = str;
        }

        public static /* synthetic */ ReceiptParams copy$default(ReceiptParams receiptParams, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = receiptParams.receiptAuth;
            }
            return receiptParams.copy(str);
        }

        @NotNull
        public final String component1() {
            return this.receiptAuth;
        }

        @NotNull
        public final ReceiptParams copy(@NotNull @Json(name = "receiptAuth") String str) {
            Intrinsics.checkNotNullParameter(str, "receiptAuth");
            return new ReceiptParams(str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ReceiptParams) && Intrinsics.areEqual((Object) this.receiptAuth, (Object) ((ReceiptParams) obj).receiptAuth);
        }

        @NotNull
        public final String getReceiptAuth() {
            return this.receiptAuth;
        }

        public int hashCode() {
            return this.receiptAuth.hashCode();
        }

        @NotNull
        public String toString() {
            return a.l("ReceiptParams(receiptAuth=", this.receiptAuth, ")");
        }
    }
}
