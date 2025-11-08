package com.nodle.wallet.utils;

import com.nodle.wallet.token.GenericToken;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ-\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\rH\u0000¢\u0006\u0002\b\u000fJ\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/nodle/wallet/utils/NodlFormatter;", "", "<init>", "()V", "DEFAULT_BALANCE", "", "formatShortNodlForDisplay", "rawNodle", "Ljava/math/BigInteger;", "token", "Lcom/nodle/wallet/token/GenericToken;", "formatRawNodleForDisplay", "minFractionDigits", "", "maxFractionDigits", "formatRawNodleForDisplay$wallet_release", "getNodlFromRawNodl", "Ljava/math/BigDecimal;", "wallet_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NodlFormatter {
    @NotNull
    public static final String DEFAULT_BALANCE = "0.00";
    @NotNull
    public static final NodlFormatter INSTANCE = new NodlFormatter();

    private NodlFormatter() {
    }

    private final BigDecimal getNodlFromRawNodl(BigInteger bigInteger, GenericToken genericToken) {
        BigDecimal divide = new BigDecimal(bigInteger).divide(BigDecimal.TEN.pow(genericToken.getDecimals()));
        Intrinsics.checkNotNullExpressionValue(divide, "divide(...)");
        return divide;
    }

    @NotNull
    public final String formatRawNodleForDisplay$wallet_release(@NotNull BigInteger bigInteger, int i3, @NotNull GenericToken genericToken, int i4) {
        Intrinsics.checkNotNullParameter(bigInteger, "rawNodle");
        Intrinsics.checkNotNullParameter(genericToken, SchemaSymbols.ATTVAL_TOKEN);
        DecimalFormat decimalFormat = new DecimalFormat(SchemaSymbols.ATTVAL_FALSE_0);
        decimalFormat.setMaximumFractionDigits(i4);
        decimalFormat.setMinimumFractionDigits(i3);
        String format = decimalFormat.format(getNodlFromRawNodl(bigInteger, genericToken));
        return format == null ? "0.00" : format;
    }

    @NotNull
    public final String formatShortNodlForDisplay(@NotNull BigInteger bigInteger, @NotNull GenericToken genericToken) {
        Intrinsics.checkNotNullParameter(bigInteger, "rawNodle");
        Intrinsics.checkNotNullParameter(genericToken, SchemaSymbols.ATTVAL_TOKEN);
        DecimalFormat decimalFormat = new DecimalFormat(SchemaSymbols.ATTVAL_FALSE_0);
        decimalFormat.setMaximumFractionDigits(4);
        decimalFormat.setMinimumFractionDigits(2);
        String format = decimalFormat.format(getNodlFromRawNodl(bigInteger, genericToken));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }
}
