package com.reown.sign.engine.model.tvf;

import androidx.camera.camera2.internal.C0118y;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/reown/sign/engine/model/tvf/SolanaSignTransactionResult;", "", "signature", "", "transaction", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getSignature", "()Ljava/lang/String;", "getTransaction", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SolanaSignTransactionResult {
    @NotNull
    private final String signature;
    @Nullable
    private final String transaction;

    public SolanaSignTransactionResult(@NotNull String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(str, "signature");
        this.signature = str;
        this.transaction = str2;
    }

    public static /* synthetic */ SolanaSignTransactionResult copy$default(SolanaSignTransactionResult solanaSignTransactionResult, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = solanaSignTransactionResult.signature;
        }
        if ((i3 & 2) != 0) {
            str2 = solanaSignTransactionResult.transaction;
        }
        return solanaSignTransactionResult.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.signature;
    }

    @Nullable
    public final String component2() {
        return this.transaction;
    }

    @NotNull
    public final SolanaSignTransactionResult copy(@NotNull String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(str, "signature");
        return new SolanaSignTransactionResult(str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SolanaSignTransactionResult)) {
            return false;
        }
        SolanaSignTransactionResult solanaSignTransactionResult = (SolanaSignTransactionResult) obj;
        return Intrinsics.areEqual((Object) this.signature, (Object) solanaSignTransactionResult.signature) && Intrinsics.areEqual((Object) this.transaction, (Object) solanaSignTransactionResult.transaction);
    }

    @NotNull
    public final String getSignature() {
        return this.signature;
    }

    @Nullable
    public final String getTransaction() {
        return this.transaction;
    }

    public int hashCode() {
        int hashCode = this.signature.hashCode() * 31;
        String str = this.transaction;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    @NotNull
    public String toString() {
        return C0118y.g("SolanaSignTransactionResult(signature=", this.signature, ", transaction=", this.transaction, ")");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SolanaSignTransactionResult(String str, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i3 & 2) != 0 ? null : str2);
    }
}
