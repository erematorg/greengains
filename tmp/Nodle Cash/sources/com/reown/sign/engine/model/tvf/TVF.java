package com.reown.sign.engine.model.tvf;

import com.reown.sign.engine.model.tvf.CosmosSignDirect;
import com.reown.sign.engine.model.tvf.NearSignTransaction;
import com.reown.sign.engine.model.tvf.PolkadotSignTransaction;
import com.reown.sign.engine.model.tvf.SignAndExecute;
import com.reown.sign.engine.model.tvf.SignTransaction;
import com.reown.sign.engine.model.tvf.XRPLSignTransaction;
import com.reown.sign.engine.model.tvf.XRPLSignTransactionFor;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J>\u0010\r\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u0004\u0012\u00020\b0\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bJ(\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078BX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u00078BX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/reown/sign/engine/model/tvf/TVF;", "", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "evm", "", "", "getEvm", "()Ljava/util/List;", "wallet", "getWallet", "collect", "Lkotlin/Triple;", "rpcMethod", "rpcParams", "chainId", "collectTxHashes", "rpcResult", "Companion", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nTVF.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TVF.kt\ncom/reown/sign/engine/model/tvf/TVF\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,228:1\n1#2:229\n1563#3:230\n1634#3,3:231\n1563#3:234\n1634#3,3:235\n*S KotlinDebug\n*F\n+ 1 TVF.kt\ncom/reown/sign/engine/model/tvf/TVF\n*L\n66#1:230\n66#1:231,3\n157#1:234\n157#1:235,3\n*E\n"})
public final class TVF {
    @NotNull
    private static final String ALGO_SIGN_TXN = "algo_signTxn";
    @NotNull
    private static final String COSMOS_SIGN_DIRECT = "cosmos_signDirect";
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    private static final String ETH_SEND_RAW_TRANSACTION = "eth_sendRawTransaction";
    @NotNull
    private static final String ETH_SEND_TRANSACTION = "eth_sendTransaction";
    @NotNull
    private static final String HEDERA_EXECUTE_TRANSACTION = "hedera_executeTransaction";
    @NotNull
    private static final String HEDERA_SIGN_AND_EXECUTE_TRANSACTION = "hedera_signAndExecuteTransaction";
    @NotNull
    private static final String NEAR_SIGN_TRANSACTION = "near_signTransaction";
    @NotNull
    private static final String NEAR_SIGN_TRANSACTIONS = "near_signTransactions";
    @NotNull
    private static final String POLKADOT_SIGN_TRANSACTION = "polkadot_signTransaction";
    @NotNull
    private static final String SEND_TRANSFER = "sendTransfer";
    @NotNull
    private static final String SOLANA_SIGN_ALL_TRANSACTION = "solana_signAllTransactions";
    @NotNull
    private static final String SOLANA_SIGN_AND_SEND_TRANSACTION = "solana_signAndSendTransaction";
    @NotNull
    private static final String SOLANA_SIGN_TRANSACTION = "solana_signTransaction";
    @NotNull
    private static final String STX_TRANSFER = "stx_transferStx";
    @NotNull
    private static final String SUI_SIGN_AND_EXECUTE_TRANSACTION = "sui_signAndExecuteTransaction";
    @NotNull
    private static final String SUI_SIGN_TRANSACTION = "sui_signTransaction";
    @NotNull
    private static final String TRON_SIGN_TRANSACTION = "tron_signTransaction";
    @NotNull
    private static final String WALLET_SEND_CALLS = "wallet_sendCalls";
    @NotNull
    private static final String XRPL_SIGN_TRANSACTION = "xrpl_signTransaction";
    @NotNull
    private static final String XRPL_SIGN_TRANSACTION_FOR = "xrpl_signTransactionFor";
    @NotNull
    private final Moshi moshi;

    @SourceDebugExtension({"SMAP\nTVF.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TVF.kt\ncom/reown/sign/engine/model/tvf/TVF$Companion\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,228:1\n12647#2,2:229\n1#3:231\n1869#4,2:232\n*S KotlinDebug\n*F\n+ 1 TVF.kt\ncom/reown/sign/engine/model/tvf/TVF$Companion\n*L\n212#1:229,2\n224#1:232,2\n*E\n"})
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u0012\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u001bR\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/reown/sign/engine/model/tvf/TVF$Companion;", "", "<init>", "()V", "ETH_SEND_TRANSACTION", "", "ETH_SEND_RAW_TRANSACTION", "WALLET_SEND_CALLS", "SOLANA_SIGN_TRANSACTION", "SOLANA_SIGN_AND_SEND_TRANSACTION", "SOLANA_SIGN_ALL_TRANSACTION", "POLKADOT_SIGN_TRANSACTION", "NEAR_SIGN_TRANSACTION", "NEAR_SIGN_TRANSACTIONS", "SUI_SIGN_AND_EXECUTE_TRANSACTION", "SUI_SIGN_TRANSACTION", "XRPL_SIGN_TRANSACTION", "XRPL_SIGN_TRANSACTION_FOR", "ALGO_SIGN_TXN", "COSMOS_SIGN_DIRECT", "TRON_SIGN_TRANSACTION", "HEDERA_SIGN_AND_EXECUTE_TRANSACTION", "HEDERA_EXECUTE_TRANSACTION", "STX_TRANSFER", "SEND_TRANSFER", "toBase58", "bytes", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String toBase58(@NotNull byte[] bArr) {
            Intrinsics.checkNotNullParameter(bArr, "bytes");
            int length = bArr.length;
            int i3 = 0;
            int i4 = 0;
            while (i3 < length && bArr[i3] == 0) {
                i4++;
                i3++;
            }
            byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(...)");
            ArrayList arrayList = new ArrayList();
            loop1:
            while (true) {
                int length2 = copyOf.length;
                int i5 = 0;
                while (i5 < length2) {
                    if (copyOf[i5] != 0) {
                        int length3 = copyOf.length;
                        int i6 = 0;
                        for (int i7 = 0; i7 < length3; i7++) {
                            int i8 = (i6 * 256) + (copyOf[i7] & 255);
                            copyOf[i7] = (byte) (i8 / 58);
                            i6 = i8 % 58;
                        }
                        arrayList.add(Character.valueOf("123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".charAt(i6)));
                    } else {
                        i5++;
                    }
                }
                break loop1;
            }
            StringBuilder sb = new StringBuilder();
            for (int i9 = 0; i9 < i4; i9++) {
                sb.append('1');
            }
            for (Character charValue : CollectionsKt.reversed(arrayList)) {
                sb.append(charValue.charValue());
            }
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
            return sb2;
        }

        private Companion() {
        }
    }

    public TVF(@NotNull Moshi moshi2) {
        Intrinsics.checkNotNullParameter(moshi2, "moshi");
        this.moshi = moshi2;
    }

    public static /* synthetic */ List collectTxHashes$default(TVF tvf, String str, String str2, String str3, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            str3 = "";
        }
        return tvf.collectTxHashes(str, str2, str3);
    }

    private final List<String> getEvm() {
        return CollectionsKt.listOf(ETH_SEND_TRANSACTION, ETH_SEND_RAW_TRANSACTION);
    }

    private final List<String> getWallet() {
        return CollectionsKt.listOf(WALLET_SEND_CALLS);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0064  */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final kotlin.Triple<java.util.List<java.lang.String>, java.util.List<java.lang.String>, java.lang.String> collect(@org.jetbrains.annotations.NotNull java.lang.String r3, @org.jetbrains.annotations.NotNull java.lang.String r4, @org.jetbrains.annotations.NotNull java.lang.String r5) {
        /*
            r2 = this;
            java.lang.String r0 = "rpcMethod"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "rpcParams"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "chainId"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "eth_sendTransaction"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r0)
            r1 = 0
            if (r0 == 0) goto L_0x0068
            kotlin.Result$Companion r0 = kotlin.Result.Companion     // Catch:{ all -> 0x0047 }
            com.squareup.moshi.Moshi r2 = r2.moshi     // Catch:{ all -> 0x0047 }
            java.lang.Class<com.reown.sign.engine.model.tvf.EthSendTransaction[]> r0 = com.reown.sign.engine.model.tvf.EthSendTransaction[].class
            com.squareup.moshi.JsonAdapter r2 = r2.adapter(r0)     // Catch:{ all -> 0x0047 }
            java.lang.Object r2 = r2.fromJson((java.lang.String) r4)     // Catch:{ all -> 0x0047 }
            com.reown.sign.engine.model.tvf.EthSendTransaction[] r2 = (com.reown.sign.engine.model.tvf.EthSendTransaction[]) r2     // Catch:{ all -> 0x0047 }
            if (r2 == 0) goto L_0x0049
            java.lang.Object r2 = kotlin.collections.ArraysKt.firstOrNull((T[]) r2)     // Catch:{ all -> 0x0047 }
            com.reown.sign.engine.model.tvf.EthSendTransaction r2 = (com.reown.sign.engine.model.tvf.EthSendTransaction) r2     // Catch:{ all -> 0x0047 }
            if (r2 == 0) goto L_0x0049
            java.lang.String r4 = r2.getData()     // Catch:{ all -> 0x0047 }
            java.lang.String r0 = "0x"
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r0)     // Catch:{ all -> 0x0047 }
            if (r4 != 0) goto L_0x003f
            goto L_0x0040
        L_0x003f:
            r2 = r1
        L_0x0040:
            if (r2 == 0) goto L_0x0049
            java.lang.String r2 = r2.getTo()     // Catch:{ all -> 0x0047 }
            goto L_0x004a
        L_0x0047:
            r2 = move-exception
            goto L_0x004f
        L_0x0049:
            r2 = r1
        L_0x004a:
            java.lang.Object r2 = kotlin.Result.m8979constructorimpl(r2)     // Catch:{ all -> 0x0047 }
            goto L_0x0059
        L_0x004f:
            kotlin.Result$Companion r4 = kotlin.Result.Companion
            java.lang.Object r2 = kotlin.ResultKt.createFailure(r2)
            java.lang.Object r2 = kotlin.Result.m8979constructorimpl(r2)
        L_0x0059:
            boolean r4 = kotlin.Result.m8985isFailureimpl(r2)
            if (r4 == 0) goto L_0x0060
            r2 = r1
        L_0x0060:
            java.lang.String r2 = (java.lang.String) r2
            if (r2 == 0) goto L_0x0068
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r2)
        L_0x0068:
            kotlin.Triple r2 = new kotlin.Triple
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
            r2.<init>(r3, r1, r5)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.model.tvf.TVF.collect(java.lang.String, java.lang.String, java.lang.String):kotlin.Triple");
    }

    @Nullable
    public final List<String> collectTxHashes(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        String transactionId;
        PolkadotSignTransaction.SignatureResponse fromJson;
        PolkadotSignTransaction.RequestParams fromJson2;
        String calculatePolkadotHash;
        ArrayList arrayList;
        String txid;
        String txid2;
        TransactionResult result;
        String txID;
        List<String> transactions;
        String signature;
        String signature2;
        List<String> list;
        Intrinsics.checkNotNullParameter(str, "rpcMethod");
        Intrinsics.checkNotNullParameter(str2, "rpcResult");
        Intrinsics.checkNotNullParameter(str3, "rpcParams");
        try {
            if (CollectionsKt.plus(getEvm(), getWallet()).contains(str)) {
                if (!Intrinsics.areEqual((Object) str, (Object) WALLET_SEND_CALLS)) {
                    return CollectionsKt.listOf(str2);
                }
                Wallet fromJson3 = this.moshi.adapter(Wallet.class).fromJson(str2);
                if (fromJson3 != null) {
                    WalletCapabilities capabilities = fromJson3.getCapabilities();
                    CAIP345 caip345 = capabilities != null ? capabilities.getCaip345() : null;
                    List<String> transactionHashes = caip345 != null ? caip345.getTransactionHashes() : null;
                    if (transactionHashes == null) {
                        transactionHashes = CollectionsKt.emptyList();
                    }
                    list = CollectionsKt.plus(CollectionsKt.listOf(fromJson3.getId()), transactionHashes);
                } else {
                    list = null;
                }
                return list == null ? CollectionsKt.listOf(str2) : list;
            } else if (Intrinsics.areEqual((Object) str, (Object) SOLANA_SIGN_TRANSACTION)) {
                SolanaSignTransactionResult fromJson4 = this.moshi.adapter(SolanaSignTransactionResult.class).fromJson(str2);
                if (fromJson4 == null || (signature2 = fromJson4.getSignature()) == null) {
                    return null;
                }
                return CollectionsKt.listOf(signature2);
            } else if (Intrinsics.areEqual((Object) str, (Object) SOLANA_SIGN_AND_SEND_TRANSACTION)) {
                SolanaSignAndSendTransactionResult fromJson5 = this.moshi.adapter(SolanaSignAndSendTransactionResult.class).fromJson(str2);
                if (fromJson5 == null || (signature = fromJson5.getSignature()) == null) {
                    return null;
                }
                return CollectionsKt.listOf(signature);
            } else {
                if (Intrinsics.areEqual((Object) str, (Object) SOLANA_SIGN_ALL_TRANSACTION)) {
                    SolanaSignAllTransactionsResult fromJson6 = this.moshi.adapter(SolanaSignAllTransactionsResult.class).fromJson(str2);
                    if (fromJson6 == null || (transactions = fromJson6.getTransactions()) == null) {
                        return null;
                    }
                    Iterable<String> iterable = transactions;
                    arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
                    for (String extractSignature : iterable) {
                        arrayList.add(SolanaKt.extractSignature(extractSignature));
                    }
                } else if (Intrinsics.areEqual((Object) str, (Object) TRON_SIGN_TRANSACTION)) {
                    TransactionResponse fromJson7 = this.moshi.adapter(TransactionResponse.class).fromJson(str2);
                    if (fromJson7 == null || (result = fromJson7.getResult()) == null || (txID = result.getTxID()) == null) {
                        return null;
                    }
                    return CollectionsKt.listOf(txID);
                } else {
                    if (!Intrinsics.areEqual((Object) str, (Object) HEDERA_SIGN_AND_EXECUTE_TRANSACTION)) {
                        if (!Intrinsics.areEqual((Object) str, (Object) HEDERA_EXECUTE_TRANSACTION)) {
                            if (Intrinsics.areEqual((Object) str, (Object) STX_TRANSFER)) {
                                StacksTransactionData fromJson8 = this.moshi.adapter(StacksTransactionData.class).fromJson(str2);
                                if (fromJson8 == null || (txid2 = fromJson8.getTxid()) == null) {
                                    return null;
                                }
                                return CollectionsKt.listOf(txid2);
                            } else if (Intrinsics.areEqual((Object) str, (Object) SEND_TRANSFER)) {
                                BitcoinTransactionResult fromJson9 = this.moshi.adapter(BitcoinTransactionResult.class).fromJson(str2);
                                if (fromJson9 == null || (txid = fromJson9.getTxid()) == null) {
                                    return null;
                                }
                                return CollectionsKt.listOf(txid);
                            } else if (Intrinsics.areEqual((Object) str, (Object) COSMOS_SIGN_DIRECT)) {
                                CosmosSignDirect.SignatureData fromJson10 = this.moshi.adapter(CosmosSignDirect.SignatureData.class).fromJson(str2);
                                if (fromJson10 != null) {
                                    return CollectionsKt.listOf(CosmosSignDirect.INSTANCE.computeTxHash(fromJson10.getSigned().getBodyBytes(), fromJson10.getSigned().getAuthInfoBytes(), fromJson10.getSignature().getSignature()));
                                }
                                return null;
                            } else {
                                Class<List> cls = List.class;
                                if (Intrinsics.areEqual((Object) str, (Object) ALGO_SIGN_TXN)) {
                                    List list2 = (List) this.moshi.adapter((Type) Types.newParameterizedType(cls, String.class)).fromJson(str2);
                                    if (list2 != null) {
                                        return AlgorandKt.calculateTxIDs(list2);
                                    }
                                    return null;
                                } else if (Intrinsics.areEqual((Object) str, (Object) XRPL_SIGN_TRANSACTION)) {
                                    XRPLSignTransaction.TransactionWrapper fromJson11 = this.moshi.adapter(XRPLSignTransaction.TransactionWrapper.class).fromJson(str2);
                                    if (fromJson11 != null) {
                                        return CollectionsKt.listOf(fromJson11.getTx_json().getHash());
                                    }
                                    return null;
                                } else if (Intrinsics.areEqual((Object) str, (Object) XRPL_SIGN_TRANSACTION_FOR)) {
                                    XRPLSignTransactionFor.TransactionWrapper fromJson12 = this.moshi.adapter(XRPLSignTransactionFor.TransactionWrapper.class).fromJson(str2);
                                    if (fromJson12 != null) {
                                        return CollectionsKt.listOf(fromJson12.getTx_json().getHash());
                                    }
                                    return null;
                                } else if (Intrinsics.areEqual((Object) str, (Object) SUI_SIGN_AND_EXECUTE_TRANSACTION)) {
                                    SignAndExecute.SuiTransactionResponse fromJson13 = this.moshi.adapter(SignAndExecute.SuiTransactionResponse.class).fromJson(str2);
                                    if (fromJson13 != null) {
                                        return CollectionsKt.listOf(fromJson13.getDigest());
                                    }
                                    return null;
                                } else if (Intrinsics.areEqual((Object) str, (Object) SUI_SIGN_TRANSACTION)) {
                                    SignTransaction.SignatureResult fromJson14 = this.moshi.adapter(SignTransaction.SignatureResult.class).fromJson(str2);
                                    if (fromJson14 != null) {
                                        return CollectionsKt.listOf(SignTransaction.INSTANCE.calculateTransactionDigest(fromJson14.getTransactionBytes()));
                                    }
                                    return null;
                                } else {
                                    Class<NearSignTransaction.BufferData> cls2 = NearSignTransaction.BufferData.class;
                                    if (Intrinsics.areEqual((Object) str, (Object) NEAR_SIGN_TRANSACTION)) {
                                        NearSignTransaction.BufferData fromJson15 = this.moshi.adapter(cls2).fromJson(str2);
                                        if (fromJson15 != null) {
                                            return CollectionsKt.listOf(NearSignTransaction.INSTANCE.calculateTxID(fromJson15));
                                        }
                                        return null;
                                    } else if (Intrinsics.areEqual((Object) str, (Object) NEAR_SIGN_TRANSACTIONS)) {
                                        List list3 = (List) this.moshi.adapter((Type) Types.newParameterizedType(cls, cls2)).fromJson(str2);
                                        if (list3 == null) {
                                            return null;
                                        }
                                        Iterable<NearSignTransaction.BufferData> iterable2 = list3;
                                        arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable2, 10));
                                        for (NearSignTransaction.BufferData calculateTxID : iterable2) {
                                            arrayList.add(NearSignTransaction.INSTANCE.calculateTxID(calculateTxID));
                                        }
                                    } else if (!Intrinsics.areEqual((Object) str, (Object) POLKADOT_SIGN_TRANSACTION) || (fromJson = this.moshi.adapter(PolkadotSignTransaction.SignatureResponse.class).fromJson(str2)) == null || (fromJson2 = this.moshi.adapter(PolkadotSignTransaction.RequestParams.class).fromJson(str3)) == null || (calculatePolkadotHash = PolkadotSignTransaction.INSTANCE.calculatePolkadotHash(fromJson, fromJson2)) == null) {
                                        return null;
                                    } else {
                                        return CollectionsKt.listOf(calculatePolkadotHash);
                                    }
                                }
                            }
                        }
                    }
                    HederaSignAndExecuteTransactionResult fromJson16 = this.moshi.adapter(HederaSignAndExecuteTransactionResult.class).fromJson(str2);
                    if (fromJson16 == null || (transactionId = fromJson16.getTransactionId()) == null) {
                        return null;
                    }
                    return CollectionsKt.listOf(transactionId);
                }
                return arrayList;
            }
        } catch (Exception e3) {
            System.out.println("Error processing " + str + " - " + e3);
            return null;
        }
    }
}
