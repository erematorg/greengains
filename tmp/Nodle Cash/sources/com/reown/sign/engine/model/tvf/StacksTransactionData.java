package com.reown.sign.engine.model.tvf;

import androidx.camera.camera2.internal.C0118y;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/reown/sign/engine/model/tvf/StacksTransactionData;", "", "txid", "", "transaction", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getTxid", "()Ljava/lang/String;", "getTransaction", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class StacksTransactionData {
    @Nullable
    private final String transaction;
    @Nullable
    private final String txid;

    public StacksTransactionData(@Nullable String str, @Nullable String str2) {
        this.txid = str;
        this.transaction = str2;
    }

    public static /* synthetic */ StacksTransactionData copy$default(StacksTransactionData stacksTransactionData, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = stacksTransactionData.txid;
        }
        if ((i3 & 2) != 0) {
            str2 = stacksTransactionData.transaction;
        }
        return stacksTransactionData.copy(str, str2);
    }

    @Nullable
    public final String component1() {
        return this.txid;
    }

    @Nullable
    public final String component2() {
        return this.transaction;
    }

    @NotNull
    public final StacksTransactionData copy(@Nullable String str, @Nullable String str2) {
        return new StacksTransactionData(str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StacksTransactionData)) {
            return false;
        }
        StacksTransactionData stacksTransactionData = (StacksTransactionData) obj;
        return Intrinsics.areEqual((Object) this.txid, (Object) stacksTransactionData.txid) && Intrinsics.areEqual((Object) this.transaction, (Object) stacksTransactionData.transaction);
    }

    @Nullable
    public final String getTransaction() {
        return this.transaction;
    }

    @Nullable
    public final String getTxid() {
        return this.txid;
    }

    public int hashCode() {
        String str = this.txid;
        int i3 = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.transaction;
        if (str2 != null) {
            i3 = str2.hashCode();
        }
        return hashCode + i3;
    }

    @NotNull
    public String toString() {
        return C0118y.g("StacksTransactionData(txid=", this.txid, ", transaction=", this.transaction, ")");
    }
}
