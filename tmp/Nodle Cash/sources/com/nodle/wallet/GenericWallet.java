package com.nodle.wallet;

import com.nodle.wallet.fee.FeeInToken;
import com.nodle.wallet.token.GenericToken;
import java.math.BigInteger;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0005H&J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH¦@¢\u0006\u0002\u0010\rJ&\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0005H¦@¢\u0006\u0002\u0010\u0011J&\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0005H¦@¢\u0006\u0002\u0010\u0011J0\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\fH¦@¢\u0006\u0002\u0010\u0016J0\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\fH¦@¢\u0006\u0002\u0010\u0016J\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH¦@¢\u0006\u0002\u0010\u0019J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001b\u001a\u00020\u001cH&¨\u0006\u001dÀ\u0006\u0003"}, d2 = {"Lcom/nodle/wallet/GenericWallet;", "", "init", "", "mnemonic", "", "chainType", "Lcom/nodle/wallet/ChainType;", "getAddress", "getBalance", "Ljava/math/BigInteger;", "token", "Lcom/nodle/wallet/token/GenericToken;", "(Lcom/nodle/wallet/token/GenericToken;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "transfer", "amount", "to", "(Lcom/nodle/wallet/token/GenericToken;Ljava/math/BigInteger;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTransferFees", "Lcom/nodle/wallet/fee/FeeInToken;", "transferWithPaymaster", "feeToken", "(Lcom/nodle/wallet/token/GenericToken;Ljava/math/BigInteger;Ljava/lang/String;Lcom/nodle/wallet/token/GenericToken;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTransferWithPaymasterFee", "address", "(Ljava/lang/String;Lcom/nodle/wallet/token/GenericToken;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "buildSignature", "transactionParams", "Lcom/nodle/wallet/TransactionParams;", "wallet_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface GenericWallet {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ Object getTransferWithPaymasterFee$default(GenericWallet genericWallet, GenericToken genericToken, BigInteger bigInteger, String str, GenericToken genericToken2, Continuation continuation, int i3, Object obj) {
        if (obj == null) {
            return genericWallet.getTransferWithPaymasterFee(genericToken, bigInteger, str, (i3 & 8) != 0 ? genericToken : genericToken2, continuation);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getTransferWithPaymasterFee");
    }

    static /* synthetic */ Object transferWithPaymaster$default(GenericWallet genericWallet, GenericToken genericToken, BigInteger bigInteger, String str, GenericToken genericToken2, Continuation continuation, int i3, Object obj) {
        if (obj == null) {
            return genericWallet.transferWithPaymaster(genericToken, bigInteger, str, (i3 & 8) != 0 ? genericToken : genericToken2, continuation);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: transferWithPaymaster");
    }

    @Nullable
    String buildSignature(@NotNull TransactionParams transactionParams);

    @NotNull
    String getAddress();

    @Nullable
    Object getBalance(@NotNull GenericToken genericToken, @NotNull Continuation<? super BigInteger> continuation);

    @Nullable
    Object getBalance(@NotNull String str, @NotNull GenericToken genericToken, @NotNull Continuation<? super BigInteger> continuation);

    @Nullable
    Object getTransferFees(@NotNull GenericToken genericToken, @NotNull BigInteger bigInteger, @NotNull String str, @NotNull Continuation<? super FeeInToken> continuation);

    @Nullable
    Object getTransferWithPaymasterFee(@NotNull GenericToken genericToken, @NotNull BigInteger bigInteger, @NotNull String str, @NotNull GenericToken genericToken2, @NotNull Continuation<? super FeeInToken> continuation);

    void init(@NotNull String str, @NotNull ChainType chainType);

    @Nullable
    Object transfer(@NotNull GenericToken genericToken, @NotNull BigInteger bigInteger, @NotNull String str, @NotNull Continuation<? super String> continuation);

    @Nullable
    Object transferWithPaymaster(@NotNull GenericToken genericToken, @NotNull BigInteger bigInteger, @NotNull String str, @NotNull GenericToken genericToken2, @NotNull Continuation<? super String> continuation);
}
