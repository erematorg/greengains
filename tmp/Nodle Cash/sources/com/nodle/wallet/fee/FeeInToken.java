package com.nodle.wallet.fee;

import A.a;
import com.nodle.wallet.token.GenericToken;
import java.math.BigInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/nodle/wallet/fee/FeeInToken;", "", "token", "Lcom/nodle/wallet/token/GenericToken;", "maxFee", "Ljava/math/BigInteger;", "estimatedFee", "<init>", "(Lcom/nodle/wallet/token/GenericToken;Ljava/math/BigInteger;Ljava/math/BigInteger;)V", "getToken", "()Lcom/nodle/wallet/token/GenericToken;", "getMaxFee", "()Ljava/math/BigInteger;", "getEstimatedFee", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "wallet_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FeeInToken {
    @NotNull
    private final BigInteger estimatedFee;
    @NotNull
    private final BigInteger maxFee;
    @NotNull
    private final GenericToken token;

    public FeeInToken(@NotNull GenericToken genericToken, @NotNull BigInteger bigInteger, @NotNull BigInteger bigInteger2) {
        Intrinsics.checkNotNullParameter(genericToken, SchemaSymbols.ATTVAL_TOKEN);
        Intrinsics.checkNotNullParameter(bigInteger, "maxFee");
        Intrinsics.checkNotNullParameter(bigInteger2, "estimatedFee");
        this.token = genericToken;
        this.maxFee = bigInteger;
        this.estimatedFee = bigInteger2;
    }

    public static /* synthetic */ FeeInToken copy$default(FeeInToken feeInToken, GenericToken genericToken, BigInteger bigInteger, BigInteger bigInteger2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            genericToken = feeInToken.token;
        }
        if ((i3 & 2) != 0) {
            bigInteger = feeInToken.maxFee;
        }
        if ((i3 & 4) != 0) {
            bigInteger2 = feeInToken.estimatedFee;
        }
        return feeInToken.copy(genericToken, bigInteger, bigInteger2);
    }

    @NotNull
    public final GenericToken component1() {
        return this.token;
    }

    @NotNull
    public final BigInteger component2() {
        return this.maxFee;
    }

    @NotNull
    public final BigInteger component3() {
        return this.estimatedFee;
    }

    @NotNull
    public final FeeInToken copy(@NotNull GenericToken genericToken, @NotNull BigInteger bigInteger, @NotNull BigInteger bigInteger2) {
        Intrinsics.checkNotNullParameter(genericToken, SchemaSymbols.ATTVAL_TOKEN);
        Intrinsics.checkNotNullParameter(bigInteger, "maxFee");
        Intrinsics.checkNotNullParameter(bigInteger2, "estimatedFee");
        return new FeeInToken(genericToken, bigInteger, bigInteger2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FeeInToken)) {
            return false;
        }
        FeeInToken feeInToken = (FeeInToken) obj;
        return Intrinsics.areEqual((Object) this.token, (Object) feeInToken.token) && Intrinsics.areEqual((Object) this.maxFee, (Object) feeInToken.maxFee) && Intrinsics.areEqual((Object) this.estimatedFee, (Object) feeInToken.estimatedFee);
    }

    @NotNull
    public final BigInteger getEstimatedFee() {
        return this.estimatedFee;
    }

    @NotNull
    public final BigInteger getMaxFee() {
        return this.maxFee;
    }

    @NotNull
    public final GenericToken getToken() {
        return this.token;
    }

    public int hashCode() {
        return this.estimatedFee.hashCode() + a.c(this.maxFee, this.token.hashCode() * 31, 31);
    }

    @NotNull
    public String toString() {
        GenericToken genericToken = this.token;
        BigInteger bigInteger = this.maxFee;
        BigInteger bigInteger2 = this.estimatedFee;
        return "FeeInToken(token=" + genericToken + ", maxFee=" + bigInteger + ", estimatedFee=" + bigInteger2 + ")";
    }
}
