package com.nodle.wallet.utils;

import com.nodle.wallet.token.GenericToken;
import com.nodle.wallet.token.NodlePolkadotToken;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000bJ\u000e\u0010\u000e\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\tJ\u001e\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007¨\u0006\u0013"}, d2 = {"Lcom/nodle/wallet/utils/NodleTokenConverter;", "", "<init>", "()V", "getMultiplier", "Ljava/math/BigDecimal;", "token", "Lcom/nodle/wallet/token/GenericToken;", "fromDecimal", "Ljava/math/BigInteger;", "amountString", "", "fromInteger", "nodle", "fromPolkadotToZkSync", "convertAmount", "amount", "fromToken", "toToken", "wallet_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nNodlFormatter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NodlFormatter.kt\ncom/nodle/wallet/utils/NodleTokenConverter\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,102:1\n434#2:103\n507#2,5:104\n*S KotlinDebug\n*F\n+ 1 NodlFormatter.kt\ncom/nodle/wallet/utils/NodleTokenConverter\n*L\n20#1:103\n20#1:104,5\n*E\n"})
public final class NodleTokenConverter {
    public static /* synthetic */ BigInteger fromDecimal$default(NodleTokenConverter nodleTokenConverter, String str, GenericToken genericToken, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            genericToken = NodlePolkadotToken.INSTANCE;
        }
        return nodleTokenConverter.fromDecimal(str, genericToken);
    }

    private final BigDecimal getMultiplier(GenericToken genericToken) {
        BigDecimal pow = BigDecimal.TEN.pow(genericToken.getDecimals());
        Intrinsics.checkNotNullExpressionValue(pow, "pow(...)");
        return pow;
    }

    @NotNull
    public final BigInteger convertAmount(@NotNull BigInteger bigInteger, @NotNull GenericToken genericToken, @NotNull GenericToken genericToken2) {
        Intrinsics.checkNotNullParameter(bigInteger, "amount");
        Intrinsics.checkNotNullParameter(genericToken, "fromToken");
        Intrinsics.checkNotNullParameter(genericToken2, "toToken");
        BigDecimal bigDecimal = BigDecimal.TEN;
        BigInteger bigInteger2 = new BigDecimal(bigInteger).multiply(bigDecimal.pow(genericToken2.getDecimals())).divide(bigDecimal.pow(genericToken.getDecimals()), RoundingMode.HALF_DOWN).toBigInteger();
        Intrinsics.checkNotNullExpressionValue(bigInteger2, "toBigInteger(...)");
        return bigInteger2;
    }

    @NotNull
    public final BigInteger fromDecimal(@NotNull String str, @NotNull GenericToken genericToken) {
        Intrinsics.checkNotNullParameter(str, "amountString");
        Intrinsics.checkNotNullParameter(genericToken, SchemaSymbols.ATTVAL_TOKEN);
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = str.charAt(i3);
            if (Character.isDigit(charAt) || charAt == '.') {
                sb.append(charAt);
            }
        }
        String sb2 = sb.toString();
        if (StringsKt.isBlank(sb2)) {
            BigInteger bigInteger = BigInteger.ZERO;
            Intrinsics.checkNotNullExpressionValue(bigInteger, "ZERO");
            return bigInteger;
        }
        BigDecimal multiply = new BigDecimal(sb2).multiply(getMultiplier(genericToken));
        Intrinsics.checkNotNullExpressionValue(multiply, "multiply(...)");
        BigInteger bigInteger2 = multiply.toBigInteger();
        Intrinsics.checkNotNullExpressionValue(bigInteger2, "toBigInteger(...)");
        return bigInteger2;
    }

    @NotNull
    public final BigInteger fromInteger(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "nodle");
        return new BigInteger(str);
    }

    @NotNull
    public final BigInteger fromPolkadotToZkSync(@NotNull BigInteger bigInteger) {
        Intrinsics.checkNotNullParameter(bigInteger, "nodle");
        BigInteger multiply = bigInteger.multiply(BigDecimal.TEN.pow(7).toBigInteger());
        Intrinsics.checkNotNullExpressionValue(multiply, "multiply(...)");
        return multiply;
    }
}
