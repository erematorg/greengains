package com.reown.sign.engine.model.tvf;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/engine/model/tvf/HederaSignAndExecuteTransactionResult;", "", "nodeId", "", "transactionHash", "transactionId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getNodeId", "()Ljava/lang/String;", "getTransactionHash", "getTransactionId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class HederaSignAndExecuteTransactionResult {
    @Nullable
    private final String nodeId;
    @Nullable
    private final String transactionHash;
    @Nullable
    private final String transactionId;

    public HederaSignAndExecuteTransactionResult(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        this.nodeId = str;
        this.transactionHash = str2;
        this.transactionId = str3;
    }

    public static /* synthetic */ HederaSignAndExecuteTransactionResult copy$default(HederaSignAndExecuteTransactionResult hederaSignAndExecuteTransactionResult, String str, String str2, String str3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = hederaSignAndExecuteTransactionResult.nodeId;
        }
        if ((i3 & 2) != 0) {
            str2 = hederaSignAndExecuteTransactionResult.transactionHash;
        }
        if ((i3 & 4) != 0) {
            str3 = hederaSignAndExecuteTransactionResult.transactionId;
        }
        return hederaSignAndExecuteTransactionResult.copy(str, str2, str3);
    }

    @Nullable
    public final String component1() {
        return this.nodeId;
    }

    @Nullable
    public final String component2() {
        return this.transactionHash;
    }

    @Nullable
    public final String component3() {
        return this.transactionId;
    }

    @NotNull
    public final HederaSignAndExecuteTransactionResult copy(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        return new HederaSignAndExecuteTransactionResult(str, str2, str3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HederaSignAndExecuteTransactionResult)) {
            return false;
        }
        HederaSignAndExecuteTransactionResult hederaSignAndExecuteTransactionResult = (HederaSignAndExecuteTransactionResult) obj;
        return Intrinsics.areEqual((Object) this.nodeId, (Object) hederaSignAndExecuteTransactionResult.nodeId) && Intrinsics.areEqual((Object) this.transactionHash, (Object) hederaSignAndExecuteTransactionResult.transactionHash) && Intrinsics.areEqual((Object) this.transactionId, (Object) hederaSignAndExecuteTransactionResult.transactionId);
    }

    @Nullable
    public final String getNodeId() {
        return this.nodeId;
    }

    @Nullable
    public final String getTransactionHash() {
        return this.transactionHash;
    }

    @Nullable
    public final String getTransactionId() {
        return this.transactionId;
    }

    public int hashCode() {
        String str = this.nodeId;
        int i3 = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.transactionHash;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.transactionId;
        if (str3 != null) {
            i3 = str3.hashCode();
        }
        return hashCode2 + i3;
    }

    @NotNull
    public String toString() {
        String str = this.nodeId;
        String str2 = this.transactionHash;
        return a.n(C0118y.l("HederaSignAndExecuteTransactionResult(nodeId=", str, ", transactionHash=", str2, ", transactionId="), this.transactionId, ")");
    }
}
