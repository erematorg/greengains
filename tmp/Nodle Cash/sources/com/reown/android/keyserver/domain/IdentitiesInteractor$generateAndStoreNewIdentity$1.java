package com.reown.android.keyserver.domain;

import com.reown.android.internal.common.signing.cacao.Cacao;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.keyserver.domain.IdentitiesInteractor", f = "IdentitiesInteractor.kt", i = {0, 0, 0, 0, 0, 0}, l = {85}, m = "generateAndStoreNewIdentity-21kkFcg", n = {"accountId", "statement", "domain", "resources", "onSign", "identityPublicKey"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
public final class IdentitiesInteractor$generateAndStoreNewIdentity$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ IdentitiesInteractor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public IdentitiesInteractor$generateAndStoreNewIdentity$1(IdentitiesInteractor identitiesInteractor, Continuation<? super IdentitiesInteractor$generateAndStoreNewIdentity$1> continuation) {
        super(continuation);
        this.this$0 = identitiesInteractor;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r7 = this.this$0.m8817generateAndStoreNewIdentity21kkFcg((String) null, (String) null, (String) null, (List<String>) null, (Function1<? super String, Cacao.Signature>) null, this);
        return r7 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r7 : Result.m8978boximpl(r7);
    }
}
