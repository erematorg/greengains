package com.nodle.wallet;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.state.b;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u000bHÆ\u0003Jc\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bHÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006'"}, d2 = {"Lcom/nodle/wallet/TransactionParams;", "", "data", "", "from", "to", "gasLimit", "maxFeePerGas", "value", "gasPerPubdata", "paymasterParams", "Lcom/nodle/wallet/PaymasterParams;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/nodle/wallet/PaymasterParams;)V", "getData", "()Ljava/lang/String;", "getFrom", "getTo", "getGasLimit", "getMaxFeePerGas", "getValue", "getGasPerPubdata", "getPaymasterParams", "()Lcom/nodle/wallet/PaymasterParams;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "wallet_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TransactionParams {
    @NotNull
    private final String data;
    @NotNull
    private final String from;
    @Nullable
    private final String gasLimit;
    @Nullable
    private final String gasPerPubdata;
    @Nullable
    private final String maxFeePerGas;
    @Nullable
    private final PaymasterParams paymasterParams;
    @NotNull
    private final String to;
    @Nullable
    private final String value;

    public TransactionParams(@NotNull String str, @NotNull String str2, @NotNull String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable PaymasterParams paymasterParams2) {
        Intrinsics.checkNotNullParameter(str, "data");
        Intrinsics.checkNotNullParameter(str2, "from");
        Intrinsics.checkNotNullParameter(str3, TypedValues.TransitionType.S_TO);
        this.data = str;
        this.from = str2;
        this.to = str3;
        this.gasLimit = str4;
        this.maxFeePerGas = str5;
        this.value = str6;
        this.gasPerPubdata = str7;
        this.paymasterParams = paymasterParams2;
    }

    public static /* synthetic */ TransactionParams copy$default(TransactionParams transactionParams, String str, String str2, String str3, String str4, String str5, String str6, String str7, PaymasterParams paymasterParams2, int i3, Object obj) {
        TransactionParams transactionParams2 = transactionParams;
        int i4 = i3;
        return transactionParams.copy((i4 & 1) != 0 ? transactionParams2.data : str, (i4 & 2) != 0 ? transactionParams2.from : str2, (i4 & 4) != 0 ? transactionParams2.to : str3, (i4 & 8) != 0 ? transactionParams2.gasLimit : str4, (i4 & 16) != 0 ? transactionParams2.maxFeePerGas : str5, (i4 & 32) != 0 ? transactionParams2.value : str6, (i4 & 64) != 0 ? transactionParams2.gasPerPubdata : str7, (i4 & 128) != 0 ? transactionParams2.paymasterParams : paymasterParams2);
    }

    @NotNull
    public final String component1() {
        return this.data;
    }

    @NotNull
    public final String component2() {
        return this.from;
    }

    @NotNull
    public final String component3() {
        return this.to;
    }

    @Nullable
    public final String component4() {
        return this.gasLimit;
    }

    @Nullable
    public final String component5() {
        return this.maxFeePerGas;
    }

    @Nullable
    public final String component6() {
        return this.value;
    }

    @Nullable
    public final String component7() {
        return this.gasPerPubdata;
    }

    @Nullable
    public final PaymasterParams component8() {
        return this.paymasterParams;
    }

    @NotNull
    public final TransactionParams copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable PaymasterParams paymasterParams2) {
        Intrinsics.checkNotNullParameter(str, "data");
        Intrinsics.checkNotNullParameter(str2, "from");
        Intrinsics.checkNotNullParameter(str3, TypedValues.TransitionType.S_TO);
        return new TransactionParams(str, str2, str3, str4, str5, str6, str7, paymasterParams2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TransactionParams)) {
            return false;
        }
        TransactionParams transactionParams = (TransactionParams) obj;
        return Intrinsics.areEqual((Object) this.data, (Object) transactionParams.data) && Intrinsics.areEqual((Object) this.from, (Object) transactionParams.from) && Intrinsics.areEqual((Object) this.to, (Object) transactionParams.to) && Intrinsics.areEqual((Object) this.gasLimit, (Object) transactionParams.gasLimit) && Intrinsics.areEqual((Object) this.maxFeePerGas, (Object) transactionParams.maxFeePerGas) && Intrinsics.areEqual((Object) this.value, (Object) transactionParams.value) && Intrinsics.areEqual((Object) this.gasPerPubdata, (Object) transactionParams.gasPerPubdata) && Intrinsics.areEqual((Object) this.paymasterParams, (Object) transactionParams.paymasterParams);
    }

    @NotNull
    public final String getData() {
        return this.data;
    }

    @NotNull
    public final String getFrom() {
        return this.from;
    }

    @Nullable
    public final String getGasLimit() {
        return this.gasLimit;
    }

    @Nullable
    public final String getGasPerPubdata() {
        return this.gasPerPubdata;
    }

    @Nullable
    public final String getMaxFeePerGas() {
        return this.maxFeePerGas;
    }

    @Nullable
    public final PaymasterParams getPaymasterParams() {
        return this.paymasterParams;
    }

    @NotNull
    public final String getTo() {
        return this.to;
    }

    @Nullable
    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        int i3 = a.i(this.to, a.i(this.from, this.data.hashCode() * 31, 31), 31);
        String str = this.gasLimit;
        int i4 = 0;
        int hashCode = (i3 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.maxFeePerGas;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.value;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.gasPerPubdata;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        PaymasterParams paymasterParams2 = this.paymasterParams;
        if (paymasterParams2 != null) {
            i4 = paymasterParams2.hashCode();
        }
        return hashCode4 + i4;
    }

    @NotNull
    public String toString() {
        String str = this.data;
        String str2 = this.from;
        String str3 = this.to;
        String str4 = this.gasLimit;
        String str5 = this.maxFeePerGas;
        String str6 = this.value;
        String str7 = this.gasPerPubdata;
        PaymasterParams paymasterParams2 = this.paymasterParams;
        StringBuilder l2 = C0118y.l("TransactionParams(data=", str, ", from=", str2, ", to=");
        b.w(l2, str3, ", gasLimit=", str4, ", maxFeePerGas=");
        b.w(l2, str5, ", value=", str6, ", gasPerPubdata=");
        l2.append(str7);
        l2.append(", paymasterParams=");
        l2.append(paymasterParams2);
        l2.append(")");
        return l2.toString();
    }
}
