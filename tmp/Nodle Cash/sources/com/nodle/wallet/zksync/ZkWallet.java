package com.nodle.wallet.zksync;

import android.support.v4.media.session.a;
import androidx.annotation.VisibleForTesting;
import androidx.browser.trusted.c;
import androidx.camera.camera2.internal.C0118y;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.state.b;
import com.nodle.wallet.ChainType;
import com.nodle.wallet.ChainTypeKt;
import com.nodle.wallet.GenericWallet;
import com.nodle.wallet.TransactionParams;
import com.nodle.wallet.fee.FeeInToken;
import com.nodle.wallet.token.EthToken;
import com.nodle.wallet.token.GenericToken;
import com.nodle.wallet.zksync.data.ZkPaymasterData;
import com.nodle.wallet.zksync.data.ZkPaymasterRepository;
import com.nodle.wallet.zksync.data.ZkRewardsRepository;
import com.nodle.wallet.zksync.data.ZkSyncConfig;
import com.nodle.wallet.zksync.data.ZkSyncConfigProvider;
import com.nodle.wallet.zksync.data.api.model.ApiZkRewardsIssueResponse;
import com.nodle.wallet.zksync.data.api.types.BridgeTransactionStatus;
import com.nodle.wallet.zksync.utils.BridgeProposalProvider;
import com.nodle.wallet.zksync.utils.EthAddressValidator;
import io.nodle.cash.core.android.utils.CoroutineDispatchers;
import io.nodle.cash.core.android.utils.NodleLogger;
import io.nodle.cash.domain.utils.BlockExplorerUrlProvider;
import io.zksync.ZkSyncWallet;
import io.zksync.abi.TransactionEncoder;
import io.zksync.crypto.eip712.Eip712Domain;
import io.zksync.methods.request.Eip712Meta;
import io.zksync.methods.request.PaymasterParams;
import io.zksync.methods.request.Transaction;
import io.zksync.methods.response.ZksEstimateFee;
import io.zksync.protocol.ZkSync;
import io.zksync.protocol.core.Token;
import io.zksync.transaction.fee.Fee;
import io.zksync.transaction.type.Transaction712;
import java.math.BigInteger;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Regex;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.web3j.crypto.Bip32ECKeyPair;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.MnemonicUtils;
import org.web3j.crypto.Sign;
import org.web3j.crypto.StructuredDataEncoder;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;
import timber.log.Timber;

@Singleton
@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B)\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0016\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH@¢\u0006\u0002\u0010\u001bJ\u001e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001aH@¢\u0006\u0002\u0010\u001dJ&\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020\u0013H@¢\u0006\u0002\u0010!J&\u0010\"\u001a\u00020#2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020\u0013H@¢\u0006\u0002\u0010!J.\u0010$\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u001aH@¢\u0006\u0002\u0010&J.\u0010'\u001a\u00020#2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u001aH@¢\u0006\u0002\u0010&J\u0006\u0010(\u001a\u00020\u000fJ:\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020+0*2\u0006\u0010 \u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020\u001aH@¢\u0006\u0002\u0010,J\u001d\u0010-\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u000fH\u0001¢\u0006\u0002\b.J\b\u0010/\u001a\u00020\u0013H\u0016J\f\u00100\u001a\u000201*\u00020\u001aH\u0002J \u00102\u001a\u00020\u00182\u0006\u00103\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u00132\u0006\u00104\u001a\u00020\u0013H\u0002J.\u00105\u001a\u00020\u00132\u0006\u00103\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u00132\u0006\u00106\u001a\u00020\u00182\u0006\u00104\u001a\u00020\u00132\u0006\u00107\u001a\u00020+J\u0016\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\u0013H@¢\u0006\u0002\u0010;J\u000e\u0010<\u001a\u00020\u0018H@¢\u0006\u0002\u0010=J\u0016\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020\u0013H@¢\u0006\u0002\u0010;J\u000e\u0010A\u001a\u00020\u0018H@¢\u0006\u0002\u0010=J\u0016\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020\u0018H@¢\u0006\u0002\u0010EJ\u0012\u0010F\u001a\u0004\u0018\u00010\u00132\u0006\u0010G\u001a\u00020HH\u0016J\u000e\u0010F\u001a\u00020\u00132\u0006\u0010I\u001a\u00020\u0013J\u000e\u0010J\u001a\u00020\u00132\u0006\u0010K\u001a\u00020\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013XD¢\u0006\u0002\n\u0000¨\u0006L"}, d2 = {"Lcom/nodle/wallet/zksync/ZkWallet;", "Lcom/nodle/wallet/GenericWallet;", "coroutineDispatchers", "Lio/nodle/cash/core/android/utils/CoroutineDispatchers;", "bridgeProposalProvider", "Lcom/nodle/wallet/zksync/utils/BridgeProposalProvider;", "zkPaymasterRepository", "Lcom/nodle/wallet/zksync/data/ZkPaymasterRepository;", "zkRewardsRepository", "Lcom/nodle/wallet/zksync/data/ZkRewardsRepository;", "<init>", "(Lio/nodle/cash/core/android/utils/CoroutineDispatchers;Lcom/nodle/wallet/zksync/utils/BridgeProposalProvider;Lcom/nodle/wallet/zksync/data/ZkPaymasterRepository;Lcom/nodle/wallet/zksync/data/ZkRewardsRepository;)V", "wallet", "Lio/zksync/ZkSyncWallet;", "config", "Lcom/nodle/wallet/zksync/data/ZkSyncConfig;", "chainType", "Lcom/nodle/wallet/ChainType;", "mnemonic", "", "ZKSYNC_MAINNET_EXPLORER", "init", "", "getBalance", "Ljava/math/BigInteger;", "token", "Lcom/nodle/wallet/token/GenericToken;", "(Lcom/nodle/wallet/token/GenericToken;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "address", "(Ljava/lang/String;Lcom/nodle/wallet/token/GenericToken;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "transfer", "amount", "to", "(Lcom/nodle/wallet/token/GenericToken;Ljava/math/BigInteger;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTransferFees", "Lcom/nodle/wallet/fee/FeeInToken;", "transferWithPaymaster", "feeToken", "(Lcom/nodle/wallet/token/GenericToken;Ljava/math/BigInteger;Ljava/lang/String;Lcom/nodle/wallet/token/GenericToken;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTransferWithPaymasterFee", "getChainConfig", "getPaymasterTransactionData", "Lkotlin/Pair;", "Lcom/nodle/wallet/zksync/data/ZkPaymasterData;", "(Ljava/lang/String;Ljava/math/BigInteger;Lcom/nodle/wallet/token/GenericToken;Lcom/nodle/wallet/token/GenericToken;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createWallet", "createWallet$zksync_release", "getAddress", "toZkToken", "Lio/zksync/protocol/core/Token;", "getGasLimit", "from", "callData", "executeWithPaymaster", "value", "paymasterData", "getBridgeTransactionStatus", "Lcom/nodle/wallet/zksync/data/api/types/BridgeTransactionStatus;", "transactionId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBridgeTransactionDelay", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryProposalByHash", "Lcom/nodle/wallet/zksync/data/api/types/Proposal;", "proposalHash", "getRewardsSequence", "rewardsIssue", "Lcom/nodle/wallet/zksync/data/api/model/ApiZkRewardsIssueResponse;", "sequence", "(Ljava/math/BigInteger;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "buildSignature", "transactionParams", "Lcom/nodle/wallet/TransactionParams;", "jsonData", "buildEIP712Signature", "typedJson", "zksync_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nZkWallet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ZkWallet.kt\ncom/nodle/wallet/zksync/ZkWallet\n+ 2 NodleLogger.kt\nio/nodle/cash/core/android/utils/NodleLogger\n*L\n1#1,547:1\n20#2,2:548\n20#2,2:550\n20#2,2:552\n20#2,2:554\n20#2,2:556\n20#2,2:558\n20#2,2:560\n20#2,2:562\n20#2,2:564\n20#2,2:566\n20#2,2:568\n20#2,2:570\n20#2,2:572\n*S KotlinDebug\n*F\n+ 1 ZkWallet.kt\ncom/nodle/wallet/zksync/ZkWallet\n*L\n81#1:548,2\n191#1:550,2\n193#1:552,2\n251#1:554,2\n265#1:556,2\n268#1:558,2\n282#1:560,2\n297#1:562,2\n429#1:564,2\n442#1:566,2\n466#1:568,2\n478#1:570,2\n510#1:572,2\n*E\n"})
public final class ZkWallet implements GenericWallet {
    /* access modifiers changed from: private */
    @NotNull
    public final String ZKSYNC_MAINNET_EXPLORER = BlockExplorerUrlProvider.ZKSYNC_MAINNET_URL;
    /* access modifiers changed from: private */
    @NotNull
    public final BridgeProposalProvider bridgeProposalProvider;
    private ChainType chainType;
    /* access modifiers changed from: private */
    public ZkSyncConfig config;
    @NotNull
    private final CoroutineDispatchers coroutineDispatchers;
    private String mnemonic;
    /* access modifiers changed from: private */
    public ZkSyncWallet wallet;
    @NotNull
    private final ZkPaymasterRepository zkPaymasterRepository;
    /* access modifiers changed from: private */
    @NotNull
    public final ZkRewardsRepository zkRewardsRepository;

    @Inject
    public ZkWallet(@NotNull CoroutineDispatchers coroutineDispatchers2, @NotNull BridgeProposalProvider bridgeProposalProvider2, @NotNull ZkPaymasterRepository zkPaymasterRepository2, @NotNull ZkRewardsRepository zkRewardsRepository2) {
        Intrinsics.checkNotNullParameter(coroutineDispatchers2, "coroutineDispatchers");
        Intrinsics.checkNotNullParameter(bridgeProposalProvider2, "bridgeProposalProvider");
        Intrinsics.checkNotNullParameter(zkPaymasterRepository2, "zkPaymasterRepository");
        Intrinsics.checkNotNullParameter(zkRewardsRepository2, "zkRewardsRepository");
        this.coroutineDispatchers = coroutineDispatchers2;
        this.bridgeProposalProvider = bridgeProposalProvider2;
        this.zkPaymasterRepository = zkPaymasterRepository2;
        this.zkRewardsRepository = zkRewardsRepository2;
    }

    /* access modifiers changed from: private */
    public static final CompletionStage buildSignature$lambda$3(ZkWallet zkWallet, Transaction712 transaction712, Eip712Domain eip712Domain) {
        ZkSyncWallet zkSyncWallet = zkWallet.wallet;
        if (zkSyncWallet == null) {
            Intrinsics.throwUninitializedPropertyAccessException("wallet");
            zkSyncWallet = null;
        }
        return zkSyncWallet.getSigner().signTypedData(eip712Domain, transaction712);
    }

    /* access modifiers changed from: private */
    public static final CompletionStage buildSignature$lambda$4(Function1 function1, Object obj) {
        return (CompletionStage) function1.invoke(obj);
    }

    /* access modifiers changed from: private */
    public static final CompletionStage executeWithPaymaster$lambda$0(ZkWallet zkWallet, Transaction712 transaction712, Eip712Domain eip712Domain) {
        ZkSyncWallet zkSyncWallet = zkWallet.wallet;
        if (zkSyncWallet == null) {
            Intrinsics.throwUninitializedPropertyAccessException("wallet");
            zkSyncWallet = null;
        }
        return zkSyncWallet.getSigner().signTypedData(eip712Domain, transaction712);
    }

    /* access modifiers changed from: private */
    public static final CompletionStage executeWithPaymaster$lambda$1(Function1 function1, Object obj) {
        return (CompletionStage) function1.invoke(obj);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getBridgeTransactionDelay(kotlin.coroutines.Continuation<? super java.math.BigInteger> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.nodle.wallet.zksync.ZkWallet$getBridgeTransactionDelay$1
            if (r0 == 0) goto L_0x0013
            r0 = r6
            com.nodle.wallet.zksync.ZkWallet$getBridgeTransactionDelay$1 r0 = (com.nodle.wallet.zksync.ZkWallet$getBridgeTransactionDelay$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.nodle.wallet.zksync.ZkWallet$getBridgeTransactionDelay$1 r0 = new com.nodle.wallet.zksync.ZkWallet$getBridgeTransactionDelay$1
            r0.<init>(r5, r6)
        L_0x0018:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0031
            if (r2 != r3) goto L_0x0029
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0047
        L_0x0029:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0031:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.CoroutineDispatcher r6 = kotlinx.coroutines.Dispatchers.getIO()
            com.nodle.wallet.zksync.ZkWallet$getBridgeTransactionDelay$2 r2 = new com.nodle.wallet.zksync.ZkWallet$getBridgeTransactionDelay$2
            r4 = 0
            r2.<init>(r5, r4)
            r0.label = r3
            java.lang.Object r6 = kotlinx.coroutines.BuildersKt.withContext(r6, r2, r0)
            if (r6 != r1) goto L_0x0047
            return r1
        L_0x0047:
            java.lang.String r5 = "withContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nodle.wallet.zksync.ZkWallet.getBridgeTransactionDelay(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final BigInteger getGasLimit(String str, String str2, String str3) {
        BigInteger bigInteger = BigInteger.ZERO;
        Transaction createFunctionCallTransaction = Transaction.createFunctionCallTransaction(str, str2, bigInteger, bigInteger, str3);
        ZkSyncWallet zkSyncWallet = this.wallet;
        if (zkSyncWallet == null) {
            Intrinsics.throwUninitializedPropertyAccessException("wallet");
            zkSyncWallet = null;
        }
        BigInteger value = zkSyncWallet.getFeeProvider().getFee(createFunctionCallTransaction).getGasLimit().getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return value;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getPaymasterTransactionData(java.lang.String r17, java.math.BigInteger r18, com.nodle.wallet.token.GenericToken r19, com.nodle.wallet.token.GenericToken r20, kotlin.coroutines.Continuation<? super kotlin.Pair<java.lang.String, com.nodle.wallet.zksync.data.ZkPaymasterData>> r21) {
        /*
            r16 = this;
            r0 = r16
            r1 = r21
            boolean r2 = r1 instanceof com.nodle.wallet.zksync.ZkWallet$getPaymasterTransactionData$1
            if (r2 == 0) goto L_0x0017
            r2 = r1
            com.nodle.wallet.zksync.ZkWallet$getPaymasterTransactionData$1 r2 = (com.nodle.wallet.zksync.ZkWallet$getPaymasterTransactionData$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L_0x0017
            int r3 = r3 - r4
            r2.label = r3
            goto L_0x001c
        L_0x0017:
            com.nodle.wallet.zksync.ZkWallet$getPaymasterTransactionData$1 r2 = new com.nodle.wallet.zksync.ZkWallet$getPaymasterTransactionData$1
            r2.<init>(r0, r1)
        L_0x001c:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 0
            r6 = 1
            if (r4 == 0) goto L_0x0057
            if (r4 != r6) goto L_0x004f
            java.lang.Object r0 = r2.L$7
            com.nodle.wallet.zksync.data.api.model.ApiZyfiPaymasterRequest r0 = (com.nodle.wallet.zksync.data.api.model.ApiZyfiPaymasterRequest) r0
            java.lang.Object r0 = r2.L$6
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r0 = r2.L$5
            com.nodle.wallet.zksync.data.api.model.ApiZyfiPaymasterRequest$TxData r0 = (com.nodle.wallet.zksync.data.api.model.ApiZyfiPaymasterRequest.TxData) r0
            java.lang.Object r0 = r2.L$4
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r3 = r2.L$3
            com.nodle.wallet.token.GenericToken r3 = (com.nodle.wallet.token.GenericToken) r3
            java.lang.Object r3 = r2.L$2
            com.nodle.wallet.token.GenericToken r3 = (com.nodle.wallet.token.GenericToken) r3
            java.lang.Object r3 = r2.L$1
            java.math.BigInteger r3 = (java.math.BigInteger) r3
            java.lang.Object r2 = r2.L$0
            java.lang.String r2 = (java.lang.String) r2
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x00f1
        L_0x004f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0057:
            kotlin.ResultKt.throwOnFailure(r1)
            java.lang.String r1 = io.zksync.wrappers.ERC20.encodeTransfer(r17, r18)
            com.nodle.wallet.zksync.data.api.model.ApiZyfiPaymasterRequest$TxData r4 = new com.nodle.wallet.zksync.data.api.model.ApiZyfiPaymasterRequest$TxData
            java.lang.String r7 = r16.getAddress()
            java.lang.String r8 = r19.getAddress()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            java.lang.String r9 = "0"
            r4.<init>(r7, r8, r9, r1)
            java.lang.String r7 = r16.getAddress()
            java.lang.String r8 = r19.getAddress()
            java.math.BigInteger r7 = r0.getGasLimit(r7, r8, r1)
            java.lang.String r13 = r7.toString()
            java.lang.String r7 = "toString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r7)
            com.nodle.wallet.zksync.data.api.model.ApiZyfiPaymasterRequest r14 = new com.nodle.wallet.zksync.data.api.model.ApiZyfiPaymasterRequest
            java.lang.String r8 = r20.getAddress()
            com.nodle.wallet.ChainType r7 = r0.chainType
            if (r7 != 0) goto L_0x0095
            java.lang.String r7 = "chainType"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            r7 = 0
        L_0x0095:
            boolean r10 = com.nodle.wallet.ChainTypeKt.isTestnet(r7)
            r11 = 0
            r7 = r14
            r9 = r13
            r12 = r4
            r7.<init>(r8, r9, r10, r11, r12)
            io.nodle.cash.core.android.utils.NodleLogger r7 = io.nodle.cash.core.android.utils.NodleLogger.INSTANCE
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "[ZkWallet] Paymaster request: "
            r7.<init>(r8)
            r7.append(r14)
            java.lang.String r7 = r7.toString()
            timber.log.Timber$Forest r8 = timber.log.Timber.Forest
            java.lang.Object[] r9 = new java.lang.Object[r5]
            r8.d(r7, r9)
            com.nodle.wallet.zksync.data.ZkPaymasterRepository r0 = r0.zkPaymasterRepository
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r17)
            r2.L$0 = r7
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r18)
            r2.L$1 = r7
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r19)
            r2.L$2 = r7
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r20)
            r2.L$3 = r7
            r2.L$4 = r1
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)
            r2.L$5 = r4
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)
            r2.L$6 = r4
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r14)
            r2.L$7 = r4
            r2.label = r6
            java.lang.Object r0 = r0.getZyfiPaymasterData(r14, r2)
            if (r0 != r3) goto L_0x00ee
            return r3
        L_0x00ee:
            r15 = r1
            r1 = r0
            r0 = r15
        L_0x00f1:
            com.nodle.wallet.zksync.data.ZkPaymasterData r1 = (com.nodle.wallet.zksync.data.ZkPaymasterData) r1
            io.nodle.cash.core.android.utils.NodleLogger r2 = io.nodle.cash.core.android.utils.NodleLogger.INSTANCE
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "[ZkWallet] Paymaster response: "
            r2.<init>(r3)
            r2.append(r1)
            java.lang.String r2 = r2.toString()
            timber.log.Timber$Forest r3 = timber.log.Timber.Forest
            java.lang.Object[] r4 = new java.lang.Object[r5]
            r3.d(r2, r4)
            kotlin.Pair r2 = new kotlin.Pair
            r2.<init>(r0, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nodle.wallet.zksync.ZkWallet.getPaymasterTransactionData(java.lang.String, java.math.BigInteger, com.nodle.wallet.token.GenericToken, com.nodle.wallet.token.GenericToken, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final Token toZkToken(GenericToken genericToken) {
        if (!(genericToken instanceof EthToken)) {
            return new Token("", genericToken.getAddress(), genericToken.getSymbol(), Integer.valueOf(genericToken.getDecimals()));
        }
        Token token = Token.ETH;
        Intrinsics.checkNotNull(token);
        return token;
    }

    @NotNull
    public final String buildEIP712Signature(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "typedJson");
        byte[] hashStructuredData = new StructuredDataEncoder(new Regex("(\"chainId\"\\s*:\\s*)(\\d+)\\.0").replace((CharSequence) str, "$1$2")).hashStructuredData();
        Intrinsics.checkNotNullExpressionValue(hashStructuredData, "hashStructuredData(...)");
        String str2 = this.mnemonic;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mnemonic");
            str2 = null;
        }
        Sign.SignatureData signMessage = Sign.signMessage(hashStructuredData, Credentials.create(Numeric.toHexStringNoPrefix(Bip32ECKeyPair.deriveKeyPair(Bip32ECKeyPair.generateKeyPair(MnemonicUtils.generateSeed(str2, "")), new int[]{-2147483604, -2147483588, Integer.MIN_VALUE, 0, 0}).getPrivateKeyBytes33())).getEcKeyPair(), false);
        byte[] bArr = new byte[65];
        System.arraycopy(signMessage.getR(), 0, bArr, 0, 32);
        System.arraycopy(signMessage.getS(), 0, bArr, 32, 32);
        System.arraycopy(signMessage.getV(), 0, bArr, 64, 1);
        String hexString = Numeric.toHexString(bArr);
        Intrinsics.checkNotNullExpressionValue(hexString, "toHexString(...)");
        return hexString;
    }

    @Nullable
    public String buildSignature(@NotNull TransactionParams transactionParams) {
        TransactionParams transactionParams2 = transactionParams;
        Intrinsics.checkNotNullParameter(transactionParams2, "transactionParams");
        ZkSyncWallet zkSyncWallet = this.wallet;
        if (zkSyncWallet == null) {
            Intrinsics.throwUninitializedPropertyAccessException("wallet");
            zkSyncWallet = null;
        }
        BigInteger send = zkSyncWallet.getNonce().send();
        NodleLogger nodleLogger = NodleLogger.INSTANCE;
        Timber.Forest forest = Timber.Forest;
        forest.d("Nonce: " + send + ", transactionParams: " + transactionParams2, new Object[0]);
        ChainType chainType2 = this.chainType;
        if (chainType2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chainType");
            chainType2 = null;
        }
        long chainId = ChainTypeKt.getChainId(chainType2);
        String from = transactionParams.getFrom();
        String to = transactionParams.getTo();
        BigInteger bigInteger = BigInteger.ZERO;
        Transaction createFunctionCallTransaction = Transaction.createFunctionCallTransaction(from, to, bigInteger, bigInteger, transactionParams.getData());
        ZkSyncWallet zkSyncWallet2 = this.wallet;
        if (zkSyncWallet2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("wallet");
            zkSyncWallet2 = null;
        }
        ZksEstimateFee send2 = zkSyncWallet2.getZksync().zksEstimateFee(createFunctionCallTransaction).send();
        if (send2.hasError()) {
            forest.d(c.a("[ZkWallet] estimateResult has error msg: ", send2.getError().getMessage()), new Object[0]);
        }
        Fee fee = (Fee) send2.getResult();
        BigInteger value = fee.getGasLimit().getValue();
        BigInteger value2 = fee.getGasPerPubdataLimit().getValue();
        BigInteger value3 = fee.getMaxFeePerGas().getValue();
        BigInteger value4 = fee.getMaxPriorityFeePerGas().getValue();
        Eip712Meta eip712Meta = new Eip712Meta();
        eip712Meta.setGasPerPubdata(value2);
        Eip712Meta eip712Meta2 = eip712Meta;
        String to2 = transactionParams.getTo();
        String str = "wallet";
        Transaction712 transaction712 = r5;
        Transaction712 transaction7122 = new Transaction712(chainId, send, value, to2, bigInteger, transactionParams.getData(), value4, value3, transactionParams.getFrom(), eip712Meta2);
        String gasLimit = transactionParams.getGasLimit();
        String to3 = transactionParams.getTo();
        String value5 = transactionParams.getValue();
        String data = transactionParams.getData();
        String maxFeePerGas = transactionParams.getMaxFeePerGas();
        String from2 = transactionParams.getFrom();
        StringBuilder sb = new StringBuilder("[ZkWallet] Prepared Transaction - chainId: ");
        sb.append(chainId);
        sb.append(", nonce: ");
        sb.append(send);
        b.w(sb, ", gasLimit: ", gasLimit, ", to: ", to3);
        b.w(sb, ", value: ", value5, ", data: ", data);
        Timber.Forest forest2 = forest;
        forest2.d(a.r(sb, ", maxFeePerGas: ", maxFeePerGas, ", from: ", from2), new Object[0]);
        ZkSyncWallet zkSyncWallet3 = this.wallet;
        if (zkSyncWallet3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str);
            zkSyncWallet3 = null;
        }
        Transaction712 transaction7123 = transaction712;
        byte[] encode = TransactionEncoder.encode(transaction7123, TransactionEncoder.getSignatureData((String) zkSyncWallet3.getSigner().getDomain().thenCompose(new c(new a(this, transaction7123, 1), 4)).join()));
        ZkSyncWallet zkSyncWallet4 = this.wallet;
        if (zkSyncWallet4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str);
            zkSyncWallet4 = null;
        }
        EthSendTransaction send3 = zkSyncWallet4.getZksync().ethSendRawTransaction(Numeric.toHexString(encode)).send();
        if (!send3.hasError()) {
            return send3.getTransactionHash();
        }
        forest2.d(C0118y.f("[ZkWallet] Error: msg: ", send3.getError().getMessage(), "\n data: ", send3.getError().getData()), new Object[0]);
        return null;
    }

    @NotNull
    @VisibleForTesting
    public final ZkSyncWallet createWallet$zksync_release(@NotNull String str, @NotNull ZkSyncConfig zkSyncConfig) {
        Intrinsics.checkNotNullParameter(str, "mnemonic");
        Intrinsics.checkNotNullParameter(zkSyncConfig, "config");
        Credentials create = Credentials.create(Numeric.toHexStringNoPrefix(Bip32ECKeyPair.deriveKeyPair(Bip32ECKeyPair.generateKeyPair(MnemonicUtils.generateSeed(str, "")), new int[]{-2147483604, -2147483588, Integer.MIN_VALUE, 0, 0}).getPrivateKeyBytes33()));
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        ZkSync build = ZkSync.build(new HttpService(zkSyncConfig.getEraUrl()));
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return new ZkSyncWallet(build, create, Long.valueOf(zkSyncConfig.getChainId()));
    }

    @NotNull
    public final String executeWithPaymaster(@NotNull String str, @NotNull String str2, @NotNull BigInteger bigInteger, @NotNull String str3, @NotNull ZkPaymasterData zkPaymasterData) {
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        ZkPaymasterData zkPaymasterData2 = zkPaymasterData;
        Intrinsics.checkNotNullParameter(str4, "from");
        Intrinsics.checkNotNullParameter(str5, TypedValues.TransitionType.S_TO);
        Intrinsics.checkNotNullParameter(bigInteger, "value");
        Intrinsics.checkNotNullParameter(str6, "callData");
        Intrinsics.checkNotNullParameter(zkPaymasterData2, "paymasterData");
        NodleLogger nodleLogger = NodleLogger.INSTANCE;
        ZkSyncConfig zkSyncConfig = this.config;
        ZkSyncWallet zkSyncWallet = null;
        if (zkSyncConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
            zkSyncConfig = null;
        }
        StringBuilder v2 = androidx.work.impl.a.v(zkSyncConfig.getChainId(), "[ZkWallet] execute for chainId: ", ", from: ", str4);
        v2.append(" to: ");
        v2.append(str5);
        v2.append(" paymaster: ");
        v2.append(zkPaymasterData2);
        String n2 = A.a.n(v2, ", callData: ", str6);
        Timber.Forest forest = Timber.Forest;
        forest.d(n2, new Object[0]);
        Eip712Meta eip712Meta = new Eip712Meta(zkPaymasterData.getGasPerPubData(), (byte[]) null, (byte[][]) null, new PaymasterParams(zkPaymasterData.getPaymaster(), Numeric.hexStringToByteArray(zkPaymasterData.getPaymasterInput())));
        forest.d("[ZkWallet] Meta: gasPerPubData " + eip712Meta.getGasPerPubdata() + ", gasPerPubNumber= " + eip712Meta.getGasPerPubdataNumber(), new Object[0]);
        ZkSyncWallet zkSyncWallet2 = this.wallet;
        if (zkSyncWallet2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("wallet");
            zkSyncWallet2 = null;
        }
        BigInteger send = zkSyncWallet2.getNonce().send();
        forest.d("[ZkWallet] Nonce: " + send + ", callData: " + str6, new Object[0]);
        ZkSyncConfig zkSyncConfig2 = this.config;
        if (zkSyncConfig2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
            zkSyncConfig2 = null;
        }
        Transaction712 transaction712 = r1;
        Transaction712 transaction7122 = new Transaction712(zkSyncConfig2.getChainId(), send, zkPaymasterData.getGasLimit(), str2, bigInteger, str3, BigInteger.ZERO, zkPaymasterData.getMaxFeePerGas(), str, eip712Meta);
        long chainId = transaction712.getChainId();
        BigInteger nonce = transaction712.getNonce();
        BigInteger gasLimit = transaction712.getGasLimit();
        String to = transaction712.getTo();
        BigInteger value = transaction712.getValue();
        String data = transaction712.getData();
        BigInteger maxPriorityFeePerGas = transaction712.getMaxPriorityFeePerGas();
        BigInteger maxFeePerGas = transaction712.getMaxFeePerGas();
        String from = transaction712.getFrom();
        StringBuilder sb = new StringBuilder("[ZkWallet] Prepared transaction: chainId = ");
        sb.append(chainId);
        sb.append(" nonce = ");
        sb.append(nonce);
        sb.append(" gasLimit = ");
        sb.append(gasLimit);
        sb.append(" to = ");
        sb.append(to);
        sb.append(" value = ");
        sb.append(value);
        sb.append(" data = ");
        sb.append(data);
        sb.append(" maxPriorityFeePerGas = ");
        sb.append(maxPriorityFeePerGas);
        sb.append(" maxFeePerGas = ");
        sb.append(maxFeePerGas);
        forest.d(A.a.n(sb, " from = ", from), new Object[0]);
        ZkSyncWallet zkSyncWallet3 = this.wallet;
        if (zkSyncWallet3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("wallet");
            zkSyncWallet3 = null;
        }
        Transaction712 transaction7123 = transaction712;
        byte[] encode = TransactionEncoder.encode(transaction7123, TransactionEncoder.getSignatureData((String) zkSyncWallet3.getSigner().getDomain().thenCompose(new c(new a(this, transaction7123, 0), 3)).join()));
        ZkSyncWallet zkSyncWallet4 = this.wallet;
        if (zkSyncWallet4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("wallet");
        } else {
            zkSyncWallet = zkSyncWallet4;
        }
        EthSendTransaction send2 = zkSyncWallet.getZksync().ethSendRawTransaction(Numeric.toHexString(encode)).send();
        if (!send2.hasError()) {
            String transactionHash = send2.getTransactionHash();
            Intrinsics.checkNotNullExpressionValue(transactionHash, "getTransactionHash(...)");
            return transactionHash;
        }
        forest.d(C0118y.f("[ZkWallet] Error: msg: ", send2.getError().getMessage(), "\n data: ", send2.getError().getData()), new Object[0]);
        throw new Exception("Error: code=" + send2.getError().getCode() + " msg=" + send2.getError().getMessage());
    }

    @NotNull
    public String getAddress() {
        EthAddressValidator ethAddressValidator = EthAddressValidator.INSTANCE;
        ZkSyncWallet zkSyncWallet = this.wallet;
        if (zkSyncWallet == null) {
            Intrinsics.throwUninitializedPropertyAccessException("wallet");
            zkSyncWallet = null;
        }
        String address = zkSyncWallet.getSigner().getAddress();
        Intrinsics.checkNotNullExpressionValue(address, "getAddress(...)");
        return ethAddressValidator.toChecksumAddress(address);
    }

    @Nullable
    public Object getBalance(@NotNull GenericToken genericToken, @NotNull Continuation<? super BigInteger> continuation) {
        return getBalance(getAddress(), genericToken, continuation);
    }

    @Nullable
    public final Object getBridgeTransactionStatus(@NotNull String str, @NotNull Continuation<? super BridgeTransactionStatus> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new ZkWallet$getBridgeTransactionStatus$2(this, str, (Continuation<? super ZkWallet$getBridgeTransactionStatus$2>) null), continuation);
    }

    @NotNull
    public final ZkSyncConfig getChainConfig() {
        ZkSyncConfig zkSyncConfig = this.config;
        if (zkSyncConfig != null) {
            return zkSyncConfig;
        }
        Intrinsics.throwUninitializedPropertyAccessException("config");
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getRewardsSequence(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.math.BigInteger> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.nodle.wallet.zksync.ZkWallet$getRewardsSequence$1
            if (r0 == 0) goto L_0x0013
            r0 = r6
            com.nodle.wallet.zksync.ZkWallet$getRewardsSequence$1 r0 = (com.nodle.wallet.zksync.ZkWallet$getRewardsSequence$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.nodle.wallet.zksync.ZkWallet$getRewardsSequence$1 r0 = new com.nodle.wallet.zksync.ZkWallet$getRewardsSequence$1
            r0.<init>(r5, r6)
        L_0x0018:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0031
            if (r2 != r3) goto L_0x0029
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0047
        L_0x0029:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0031:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.CoroutineDispatcher r6 = kotlinx.coroutines.Dispatchers.getIO()
            com.nodle.wallet.zksync.ZkWallet$getRewardsSequence$2 r2 = new com.nodle.wallet.zksync.ZkWallet$getRewardsSequence$2
            r4 = 0
            r2.<init>(r5, r4)
            r0.label = r3
            java.lang.Object r6 = kotlinx.coroutines.BuildersKt.withContext(r6, r2, r0)
            if (r6 != r1) goto L_0x0047
            return r1
        L_0x0047:
            java.lang.String r5 = "withContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nodle.wallet.zksync.ZkWallet.getRewardsSequence(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public Object getTransferFees(@NotNull GenericToken genericToken, @NotNull BigInteger bigInteger, @NotNull String str, @NotNull Continuation<? super FeeInToken> continuation) {
        return BuildersKt.withContext(this.coroutineDispatchers.getIo(), new ZkWallet$getTransferFees$2(genericToken, this, str, bigInteger, (Continuation<? super ZkWallet$getTransferFees$2>) null), continuation);
    }

    @Nullable
    public Object getTransferWithPaymasterFee(@NotNull GenericToken genericToken, @NotNull BigInteger bigInteger, @NotNull String str, @NotNull GenericToken genericToken2, @NotNull Continuation<? super FeeInToken> continuation) {
        return BuildersKt.withContext(this.coroutineDispatchers.getIo(), new ZkWallet$getTransferWithPaymasterFee$2(this, str, bigInteger, genericToken, genericToken2, (Continuation<? super ZkWallet$getTransferWithPaymasterFee$2>) null), continuation);
    }

    public void init(@NotNull String str, @NotNull ChainType chainType2) {
        Intrinsics.checkNotNullParameter(str, "mnemonic");
        Intrinsics.checkNotNullParameter(chainType2, "chainType");
        this.chainType = chainType2;
        this.mnemonic = str;
        ZkSyncConfig config2 = ZkSyncConfigProvider.INSTANCE.getConfig(chainType2);
        this.config = config2;
        if (config2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
            config2 = null;
        }
        this.wallet = createWallet$zksync_release(str, config2);
        NodleLogger nodleLogger = NodleLogger.INSTANCE;
        String address = getAddress();
        Timber.Forest.d("[ZkWallet] Wallet initialized with public key: " + address + ", for chainType = " + chainType2, new Object[0]);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object queryProposalByHash(@org.jetbrains.annotations.NotNull java.lang.String r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.nodle.wallet.zksync.data.api.types.Proposal> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.nodle.wallet.zksync.ZkWallet$queryProposalByHash$1
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.nodle.wallet.zksync.ZkWallet$queryProposalByHash$1 r0 = (com.nodle.wallet.zksync.ZkWallet$queryProposalByHash$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.nodle.wallet.zksync.ZkWallet$queryProposalByHash$1 r0 = new com.nodle.wallet.zksync.ZkWallet$queryProposalByHash$1
            r0.<init>(r5, r7)
        L_0x0018:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            java.lang.Object r5 = r0.L$0
            java.lang.String r5 = (java.lang.String) r5
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0051
        L_0x002d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0035:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlinx.coroutines.CoroutineDispatcher r7 = kotlinx.coroutines.Dispatchers.getIO()
            com.nodle.wallet.zksync.ZkWallet$queryProposalByHash$2 r2 = new com.nodle.wallet.zksync.ZkWallet$queryProposalByHash$2
            r4 = 0
            r2.<init>(r6, r5, r4)
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r7, r2, r0)
            if (r7 != r1) goto L_0x0051
            return r1
        L_0x0051:
            java.lang.String r5 = "withContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r5)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nodle.wallet.zksync.ZkWallet.queryProposalByHash(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object rewardsIssue(@NotNull BigInteger bigInteger, @NotNull Continuation<? super ApiZkRewardsIssueResponse> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new ZkWallet$rewardsIssue$2(this, bigInteger, (Continuation<? super ZkWallet$rewardsIssue$2>) null), continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object transfer(@org.jetbrains.annotations.NotNull com.nodle.wallet.token.GenericToken r11, @org.jetbrains.annotations.NotNull java.math.BigInteger r12, @org.jetbrains.annotations.NotNull java.lang.String r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.String> r14) {
        /*
            r10 = this;
            boolean r0 = r14 instanceof com.nodle.wallet.zksync.ZkWallet$transfer$1
            if (r0 == 0) goto L_0x0013
            r0 = r14
            com.nodle.wallet.zksync.ZkWallet$transfer$1 r0 = (com.nodle.wallet.zksync.ZkWallet$transfer$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.nodle.wallet.zksync.ZkWallet$transfer$1 r0 = new com.nodle.wallet.zksync.ZkWallet$transfer$1
            r0.<init>(r10, r14)
        L_0x0018:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003d
            if (r2 != r3) goto L_0x0035
            java.lang.Object r10 = r0.L$2
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r10 = r0.L$1
            java.math.BigInteger r10 = (java.math.BigInteger) r10
            java.lang.Object r10 = r0.L$0
            com.nodle.wallet.token.GenericToken r10 = (com.nodle.wallet.token.GenericToken) r10
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x006a
        L_0x0035:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x003d:
            kotlin.ResultKt.throwOnFailure(r14)
            kotlinx.coroutines.CoroutineDispatcher r14 = kotlinx.coroutines.Dispatchers.getIO()
            com.nodle.wallet.zksync.ZkWallet$transfer$2 r2 = new com.nodle.wallet.zksync.ZkWallet$transfer$2
            r9 = 0
            r4 = r2
            r5 = r10
            r6 = r13
            r7 = r12
            r8 = r11
            r4.<init>(r5, r6, r7, r8, r9)
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)
            r0.L$0 = r10
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)
            r0.L$1 = r10
            java.lang.Object r10 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)
            r0.L$2 = r10
            r0.label = r3
            java.lang.Object r14 = kotlinx.coroutines.BuildersKt.withContext(r14, r2, r0)
            if (r14 != r1) goto L_0x006a
            return r1
        L_0x006a:
            java.lang.String r10 = "withContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r14, r10)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nodle.wallet.zksync.ZkWallet.transfer(com.nodle.wallet.token.GenericToken, java.math.BigInteger, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public Object transferWithPaymaster(@NotNull GenericToken genericToken, @NotNull BigInteger bigInteger, @NotNull String str, @NotNull GenericToken genericToken2, @NotNull Continuation<? super String> continuation) {
        return BuildersKt.withContext(this.coroutineDispatchers.getIo(), new ZkWallet$transferWithPaymaster$2(genericToken, bigInteger, str, this, genericToken2, (Continuation<? super ZkWallet$transferWithPaymaster$2>) null), continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getBalance(@org.jetbrains.annotations.NotNull java.lang.String r6, @org.jetbrains.annotations.NotNull com.nodle.wallet.token.GenericToken r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.math.BigInteger> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.nodle.wallet.zksync.ZkWallet$getBalance$2
            if (r0 == 0) goto L_0x0013
            r0 = r8
            com.nodle.wallet.zksync.ZkWallet$getBalance$2 r0 = (com.nodle.wallet.zksync.ZkWallet$getBalance$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.nodle.wallet.zksync.ZkWallet$getBalance$2 r0 = new com.nodle.wallet.zksync.ZkWallet$getBalance$2
            r0.<init>(r5, r8)
        L_0x0018:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r5 = r0.L$1
            com.nodle.wallet.token.GenericToken r5 = (com.nodle.wallet.token.GenericToken) r5
            java.lang.Object r5 = r0.L$0
            java.lang.String r5 = (java.lang.String) r5
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x005b
        L_0x0031:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.CoroutineDispatcher r8 = kotlinx.coroutines.Dispatchers.getIO()
            com.nodle.wallet.zksync.ZkWallet$getBalance$3 r2 = new com.nodle.wallet.zksync.ZkWallet$getBalance$3
            r4 = 0
            r2.<init>(r5, r6, r7, r4)
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)
            r0.L$0 = r5
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r8, r2, r0)
            if (r8 != r1) goto L_0x005b
            return r1
        L_0x005b:
            java.lang.String r5 = "withContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r5)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nodle.wallet.zksync.ZkWallet.getBalance(java.lang.String, com.nodle.wallet.token.GenericToken, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final String buildSignature(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "jsonData");
        String str2 = this.mnemonic;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mnemonic");
            str2 = null;
        }
        Credentials create = Credentials.create(Numeric.toHexStringNoPrefix(Bip32ECKeyPair.deriveKeyPair(Bip32ECKeyPair.generateKeyPair(MnemonicUtils.generateSeed(str2, "")), new int[]{-2147483604, -2147483588, Integer.MIN_VALUE, 0, 0}).getPrivateKeyBytes33()));
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        Sign.SignatureData signTypedData = Sign.signTypedData(str, create.getEcKeyPair());
        byte[] bArr = new byte[65];
        System.arraycopy(signTypedData.getR(), 0, bArr, 0, 32);
        System.arraycopy(signTypedData.getS(), 0, bArr, 32, 32);
        System.arraycopy(signTypedData.getV(), 0, bArr, 64, 1);
        String hexString = Numeric.toHexString(bArr);
        NodleLogger nodleLogger = NodleLogger.INSTANCE;
        Timber.Forest.d(c.a("[ZkWallet] Signature: ", hexString), new Object[0]);
        Intrinsics.checkNotNull(hexString);
        return hexString;
    }
}
