package com.nodle.wallet.zksync.utils;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Hash;
import org.web3j.utils.Numeric;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u0005H\u0002J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u0005H\u0002J\u000e\u0010\r\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u0005J\u0016\u0010\u000e\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/nodle/wallet/zksync/utils/EthAddressValidator;", "", "<init>", "()V", "ADDRESS_PREFIX", "", "ethAddressRegex", "Lkotlin/text/Regex;", "toChecksumAddress", "address", "isEthereumAddress", "", "hasChecksum", "isValidAddress", "toChecksumWithChainId", "chainId", "", "zksync_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class EthAddressValidator {
    @NotNull
    private static final String ADDRESS_PREFIX = "0x";
    @NotNull
    public static final EthAddressValidator INSTANCE = new EthAddressValidator();
    @NotNull
    private static final Regex ethAddressRegex = new Regex("^0x[0-9a-fA-F]{40}$");

    private EthAddressValidator() {
    }

    private final boolean hasChecksum(String str) {
        String lowerCase = str.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return !Intrinsics.areEqual((Object) lowerCase, (Object) str);
    }

    private final boolean isEthereumAddress(String str) {
        return ethAddressRegex.matches(str);
    }

    public final boolean isValidAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, Address.TYPE_NAME);
        if (!isEthereumAddress(str)) {
            return false;
        }
        if (!hasChecksum(str)) {
            return true;
        }
        return Intrinsics.areEqual((Object) str, (Object) toChecksumAddress(str));
    }

    @NotNull
    public final String toChecksumAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, Address.TYPE_NAME);
        String lowerCase = str.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String M = StringsKt__StringsKt.removePrefix(lowerCase, (CharSequence) "0x");
        byte[] bytes = M.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        String hexStringNoPrefix = Numeric.toHexStringNoPrefix(Hash.sha3(bytes));
        Intrinsics.checkNotNull(hexStringNoPrefix);
        char[] charArray = hexStringNoPrefix.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "toCharArray(...)");
        char[] charArray2 = M.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray2, "toCharArray(...)");
        StringBuilder sb = new StringBuilder("0x");
        int length = charArray2.length;
        for (int i3 = 0; i3 < length; i3++) {
            char c3 = charArray2[i3];
            if (Character.digit(charArray[i3], 16) >= 8) {
                sb.append(Character.toUpperCase(c3));
            } else {
                sb.append(c3);
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    @NotNull
    public final String toChecksumWithChainId(@NotNull String str, long j2) {
        Intrinsics.checkNotNullParameter(str, Address.TYPE_NAME);
        String lowerCase = str.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String M = StringsKt__StringsKt.removePrefix(lowerCase, (CharSequence) "0x");
        byte[] bytes = (j2 + "0x" + M).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        String hexStringNoPrefix = Numeric.toHexStringNoPrefix(Hash.sha3(bytes));
        Intrinsics.checkNotNull(hexStringNoPrefix);
        char[] charArray = hexStringNoPrefix.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "toCharArray(...)");
        char[] charArray2 = M.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray2, "toCharArray(...)");
        StringBuilder sb = new StringBuilder("0x");
        int length = charArray2.length;
        for (int i3 = 0; i3 < length; i3++) {
            char c3 = charArray2[i3];
            if (Character.digit(charArray[i3], 16) >= 8) {
                sb.append(Character.toUpperCase(c3));
            } else {
                sb.append(c3);
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }
}
