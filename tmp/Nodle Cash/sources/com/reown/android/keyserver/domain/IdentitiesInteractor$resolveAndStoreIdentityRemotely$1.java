package com.reown.android.keyserver.domain;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.keyserver.domain.IdentitiesInteractor", f = "IdentitiesInteractor.kt", i = {0, 1, 1, 1, 1}, l = {117, 120}, m = "resolveAndStoreIdentityRemotely-gIAlu-s", n = {"identityKey", "identityKey", "response", "accountId", "$i$a$-mapCatching-IdentitiesInteractor$resolveAndStoreIdentityRemotely$2"}, s = {"L$0", "L$0", "L$1", "L$2", "I$0"})
public final class IdentitiesInteractor$resolveAndStoreIdentityRemotely$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ IdentitiesInteractor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public IdentitiesInteractor$resolveAndStoreIdentityRemotely$1(IdentitiesInteractor identitiesInteractor, Continuation<? super IdentitiesInteractor$resolveAndStoreIdentityRemotely$1> continuation) {
        super(continuation);
        this.this$0 = identitiesInteractor;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r12 = this.this$0.m8824resolveAndStoreIdentityRemotelygIAlus((String) null, this);
        return r12 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r12 : Result.m8978boximpl(r12);
    }
}
