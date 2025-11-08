package com.nodle.wallet.utils;

import com.nodle.wallet.token.GenericToken;
import com.nodle.wallet.token.NodleToken;
import java.math.BigInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0016\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n¨\u0006\r"}, d2 = {"Lcom/nodle/wallet/utils/TokenUtils;", "", "<init>", "()V", "amountInNodleToken", "Ljava/math/BigInteger;", "amount", "", "amountInToken", "token", "Lcom/nodle/wallet/token/GenericToken;", "amountToDouble", "", "wallet_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TokenUtils {
    @NotNull
    public static final TokenUtils INSTANCE = new TokenUtils();

    private TokenUtils() {
    }

    @NotNull
    public final BigInteger amountInNodleToken(int i3) {
        return amountInToken(i3, NodleToken.INSTANCE);
    }

    @NotNull
    public final BigInteger amountInToken(int i3, @NotNull GenericToken genericToken) {
        Intrinsics.checkNotNullParameter(genericToken, SchemaSymbols.ATTVAL_TOKEN);
        BigInteger pow = BigInteger.TEN.pow(genericToken.getDecimals());
        BigInteger valueOf = BigInteger.valueOf((long) i3);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
        Intrinsics.checkNotNull(pow);
        BigInteger multiply = valueOf.multiply(pow);
        Intrinsics.checkNotNullExpressionValue(multiply, "multiply(...)");
        return multiply;
    }

    public final double amountToDouble(@NotNull BigInteger bigInteger, @NotNull GenericToken genericToken) {
        Intrinsics.checkNotNullParameter(bigInteger, "amount");
        Intrinsics.checkNotNullParameter(genericToken, SchemaSymbols.ATTVAL_TOKEN);
        return bigInteger.doubleValue() / BigInteger.TEN.pow(genericToken.getDecimals()).doubleValue();
    }
}
