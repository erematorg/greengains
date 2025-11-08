package com.reown.android.keyserver.domain;

import com.reown.foundation.common.model.PrivateKey;
import com.reown.foundation.common.model.PublicKey;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.keyserver.domain.IdentitiesInteractor", f = "IdentitiesInteractor.kt", i = {0, 0, 0, 1, 1, 1, 1, 1}, l = {157, 158}, m = "unregisterIdentityKeyInKeyserver-slDN04U", n = {"accountId", "keyserverUrl", "identityKeyPair", "accountId", "keyserverUrl", "identityKeyPair", "it", "$i$a$-onSuccess-IdentitiesInteractor$unregisterIdentityKeyInKeyserver$2"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$4", "I$0"})
public final class IdentitiesInteractor$unregisterIdentityKeyInKeyserver$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ IdentitiesInteractor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public IdentitiesInteractor$unregisterIdentityKeyInKeyserver$1(IdentitiesInteractor identitiesInteractor, Continuation<? super IdentitiesInteractor$unregisterIdentityKeyInKeyserver$1> continuation) {
        super(continuation);
        this.this$0 = identitiesInteractor;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r12 = this.this$0.m8828unregisterIdentityKeyInKeyserverslDN04U((String) null, (String) null, (Pair<PublicKey, PrivateKey>) null, this);
        return r12 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r12 : Result.m8978boximpl(r12);
    }
}
