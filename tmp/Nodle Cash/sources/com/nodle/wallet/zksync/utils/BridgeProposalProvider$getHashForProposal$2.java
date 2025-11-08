package com.nodle.wallet.zksync.utils;

import androidx.browser.trusted.c;
import androidx.camera.camera2.internal.C0118y;
import io.nodle.cash.substrate.SubstrateEnvironmentRepository;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.web3j.crypto.Hash;
import org.web3j.utils.Numeric;
import timber.log.Timber;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.nodle.wallet.zksync.utils.BridgeProposalProvider$getHashForProposal$2", f = "BridgeProposalProvider.kt", i = {0, 0}, l = {22}, m = "invokeSuspend", n = {"blockNumber", "eventNumber"}, s = {"L$0", "L$1"})
public final class BridgeProposalProvider$getHashForProposal$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    final /* synthetic */ String $id;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ BridgeProposalProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BridgeProposalProvider$getHashForProposal$2(String str, BridgeProposalProvider bridgeProposalProvider, Continuation<? super BridgeProposalProvider$getHashForProposal$2> continuation) {
        super(2, continuation);
        this.$id = str;
        this.this$0 = bridgeProposalProvider;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BridgeProposalProvider$getHashForProposal$2(this.$id, this.this$0, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            List take = CollectionsKt.take(StringsKt__StringsKt.split$default((CharSequence) this.$id, new String[]{"-"}, false, 0, 6, (Object) null), 2);
            String str2 = (String) take.get(0);
            String str3 = (String) take.get(1);
            Timber.Forest.d(C0118y.f("blockNumber: ", str2, ", eventNumber: ", str3), new Object[0]);
            SubstrateEnvironmentRepository access$getSubstrateEnvironmentRepository$p = this.this$0.substrateEnvironmentRepository;
            long parseLong = Long.parseLong(str2);
            this.L$0 = SpillingKt.nullOutSpilledVariable(str2);
            this.L$1 = str3;
            this.label = 1;
            Object blockHash = access$getSubstrateEnvironmentRepository$p.getBlockHash(parseLong, this);
            if (blockHash == coroutine_suspended) {
                return coroutine_suspended;
            }
            str = str3;
            obj = blockHash;
        } else if (i3 == 1) {
            str = (String) this.L$1;
            String str4 = (String) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        String str5 = (String) obj;
        byte[] hexStringToByteArray = Numeric.hexStringToByteArray(str5);
        Timber.Forest.d(c.a("blockHash: ", str5), new Object[0]);
        byte[] array = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(Integer.parseInt(str) + 1).array();
        Intrinsics.checkNotNull(hexStringToByteArray);
        Intrinsics.checkNotNull(array);
        return Numeric.toHexStringNoPrefix(Hash.sha3(ArraysKt.plus(hexStringToByteArray, array)));
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((BridgeProposalProvider$getHashForProposal$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
