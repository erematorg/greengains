package com.nodle.wallet.utils;

import com.nodle.wallet.token.GenericToken;
import com.nodle.wallet.token.NodlePolkadotToken;
import java.math.BigInteger;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\bf\u0018\u0000 \r2\u00020\u0001:\u0001\rJ$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&¨\u0006\u000eÀ\u0006\u0003"}, d2 = {"Lcom/nodle/wallet/utils/NodleTokenFormatter;", "", "formatRawNodleforDisplay", "", "rawNodle", "Ljava/math/BigInteger;", "token", "Lcom/nodle/wallet/token/GenericToken;", "maxFractionDigits", "", "format", "double", "", "Companion", "wallet_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface NodleTokenFormatter {
    @NotNull
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final int NODL_MAX_DECIMALS = 11;
    public static final int NODL_MIN_DECIMALS = 2;
    @NotNull
    public static final String ZERO = "0.00";

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/nodle/wallet/utils/NodleTokenFormatter$Companion;", "", "<init>", "()V", "ZERO", "", "NODL_MAX_DECIMALS", "", "NODL_MIN_DECIMALS", "wallet_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final int NODL_MAX_DECIMALS = 11;
        public static final int NODL_MIN_DECIMALS = 2;
        @NotNull
        public static final String ZERO = "0.00";

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ String formatRawNodleforDisplay$default(NodleTokenFormatter nodleTokenFormatter, BigInteger bigInteger, GenericToken genericToken, int i3, int i4, Object obj) {
        if (obj == null) {
            if ((i4 & 2) != 0) {
                genericToken = NodlePolkadotToken.INSTANCE;
            }
            if ((i4 & 4) != 0) {
                i3 = genericToken.getDecimals();
            }
            return nodleTokenFormatter.formatRawNodleforDisplay(bigInteger, genericToken, i3);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: formatRawNodleforDisplay");
    }

    @NotNull
    String format(double d2);

    @NotNull
    String formatRawNodleforDisplay(@NotNull BigInteger bigInteger, @NotNull GenericToken genericToken, int i3);
}
