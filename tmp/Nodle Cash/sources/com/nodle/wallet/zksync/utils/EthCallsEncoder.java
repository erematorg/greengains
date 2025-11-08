package com.nodle.wallet.zksync.utils;

import androidx.compose.animation.core.a;
import io.nodle.cash.core.android.utils.NodleLogger;
import io.zksync.wrappers.ZkSyncContract;
import java.math.BigInteger;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.utils.Numeric;
import timber.log.Timber;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\b¨\u0006\u000b"}, d2 = {"Lcom/nodle/wallet/zksync/utils/EthCallsEncoder;", "", "<init>", "()V", "encodeMintRewardCall", "", "recipientPk", "amount", "Ljava/math/BigInteger;", "signature", "sequence", "zksync_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nEthCallsEncoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EthCallsEncoder.kt\ncom/nodle/wallet/zksync/utils/EthCallsEncoder\n+ 2 NodleLogger.kt\nio/nodle/cash/core/android/utils/NodleLogger\n*L\n1#1,40:1\n20#2,2:41\n20#2,2:43\n*S KotlinDebug\n*F\n+ 1 EthCallsEncoder.kt\ncom/nodle/wallet/zksync/utils/EthCallsEncoder\n*L\n16#1:41,2\n18#1:43,2\n*E\n"})
public final class EthCallsEncoder {
    @NotNull
    public static final EthCallsEncoder INSTANCE = new EthCallsEncoder();

    private EthCallsEncoder() {
    }

    @NotNull
    public final String encodeMintRewardCall(@NotNull String str, @NotNull BigInteger bigInteger, @NotNull String str2, @NotNull BigInteger bigInteger2) {
        Intrinsics.checkNotNullParameter(str, "recipientPk");
        Intrinsics.checkNotNullParameter(bigInteger, "amount");
        Intrinsics.checkNotNullParameter(str2, "signature");
        Intrinsics.checkNotNullParameter(bigInteger2, "sequence");
        NodleLogger nodleLogger = NodleLogger.INSTANCE;
        Timber.Forest forest = Timber.Forest;
        forest.d("Encode reward call recipientPk: " + str + ", amount: " + bigInteger + ", signature: " + str2 + ", sequence: " + bigInteger2, new Object[0]);
        byte[] hexStringToByteArray = Numeric.hexStringToByteArray(str2);
        forest.d(a.r(" sig len = ", str2.length(), ", sigBytes len = ", hexStringToByteArray.length), new Object[0]);
        String encode = FunctionEncoder.encode(new Function("mintReward", CollectionsKt.listOf(new StaticStruct(new Address(str), new Uint256(bigInteger), new Uint256(bigInteger2)), new DynamicBytes(hexStringToByteArray)), CollectionsKt.emptyList()));
        String M = StringsKt__StringsKt.removePrefix(str2, (CharSequence) ZkSyncContract.BINARY);
        Intrinsics.checkNotNull(encode);
        return android.support.v4.media.session.a.n(StringsKt__StringsKt.removeSuffix(StringsKt__StringsKt.removeSuffix(encode, (CharSequence) M), (CharSequence) "00000000000000000000000000000000000000000000000000000000000000"), M, "00000000000000000000000000000000000000000000000000000000000000");
    }
}
