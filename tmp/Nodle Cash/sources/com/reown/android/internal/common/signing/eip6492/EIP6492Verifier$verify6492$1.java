package com.reown.android.internal.common.signing.eip6492;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import uniffi.yttrium.Erc6492Client;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.signing.eip6492.EIP6492Verifier$verify6492$1", f = "EIP6492Verifier.kt", i = {}, l = {24}, m = "invokeSuspend", n = {}, s = {})
public final class EIP6492Verifier$verify6492$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ String $address;
    final /* synthetic */ String $messageHashHex;
    final /* synthetic */ String $signature;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EIP6492Verifier$verify6492$1(String str, String str2, String str3, Continuation<? super EIP6492Verifier$verify6492$1> continuation) {
        super(2, continuation);
        this.$signature = str;
        this.$address = str2;
        this.$messageHashHex = str3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new EIP6492Verifier$verify6492$1(this.$signature, this.$address, this.$messageHashHex, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            Erc6492Client access$getErc6492Client$p = EIP6492Verifier.erc6492Client;
            if (access$getErc6492Client$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("erc6492Client");
                access$getErc6492Client$p = null;
            }
            String str = this.$signature;
            String str2 = this.$address;
            String str3 = this.$messageHashHex;
            Intrinsics.checkNotNull(str3);
            this.label = 1;
            obj = access$getErc6492Client$p.verifySignature(str, str2, str3, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((EIP6492Verifier$verify6492$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
