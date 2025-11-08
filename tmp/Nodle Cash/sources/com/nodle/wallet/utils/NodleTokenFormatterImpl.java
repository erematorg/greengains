package com.nodle.wallet.utils;

import com.nodle.wallet.token.GenericToken;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/nodle/wallet/utils/NodleTokenFormatterImpl;", "Lcom/nodle/wallet/utils/NodleTokenFormatter;", "<init>", "()V", "df", "Ljava/text/DecimalFormat;", "formatRawNodleforDisplay", "", "rawNodle", "Ljava/math/BigInteger;", "token", "Lcom/nodle/wallet/token/GenericToken;", "maxFractionDigits", "", "format", "double", "", "wallet_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NodleTokenFormatterImpl implements NodleTokenFormatter {
    @NotNull
    private final DecimalFormat df;

    @Inject
    public NodleTokenFormatterImpl() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        decimalFormat.setMaximumFractionDigits(11);
        decimalFormat.setMinimumFractionDigits(2);
        this.df = decimalFormat;
    }

    @NotNull
    public String format(double d2) {
        String format = this.df.format(d2);
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    @NotNull
    public String formatRawNodleforDisplay(@NotNull BigInteger bigInteger, @NotNull GenericToken genericToken, int i3) {
        Intrinsics.checkNotNullParameter(bigInteger, "rawNodle");
        Intrinsics.checkNotNullParameter(genericToken, SchemaSymbols.ATTVAL_TOKEN);
        return NodlFormatter.INSTANCE.formatRawNodleForDisplay$wallet_release(bigInteger, 2, genericToken, i3);
    }
}
