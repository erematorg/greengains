package com.reown.sign.engine.model.tvf;

import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/sign/engine/model/tvf/TransactionResponse;", "", "result", "Lcom/reown/sign/engine/model/tvf/TransactionResult;", "<init>", "(Lcom/reown/sign/engine/model/tvf/TransactionResult;)V", "getResult", "()Lcom/reown/sign/engine/model/tvf/TransactionResult;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TransactionResponse {
    @NotNull
    private final TransactionResult result;

    public TransactionResponse(@NotNull TransactionResult transactionResult) {
        Intrinsics.checkNotNullParameter(transactionResult, "result");
        this.result = transactionResult;
    }

    public static /* synthetic */ TransactionResponse copy$default(TransactionResponse transactionResponse, TransactionResult transactionResult, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            transactionResult = transactionResponse.result;
        }
        return transactionResponse.copy(transactionResult);
    }

    @NotNull
    public final TransactionResult component1() {
        return this.result;
    }

    @NotNull
    public final TransactionResponse copy(@NotNull TransactionResult transactionResult) {
        Intrinsics.checkNotNullParameter(transactionResult, "result");
        return new TransactionResponse(transactionResult);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof TransactionResponse) && Intrinsics.areEqual((Object) this.result, (Object) ((TransactionResponse) obj).result);
    }

    @NotNull
    public final TransactionResult getResult() {
        return this.result;
    }

    public int hashCode() {
        return this.result.hashCode();
    }

    @NotNull
    public String toString() {
        TransactionResult transactionResult = this.result;
        return "TransactionResponse(result=" + transactionResult + ")";
    }
}
