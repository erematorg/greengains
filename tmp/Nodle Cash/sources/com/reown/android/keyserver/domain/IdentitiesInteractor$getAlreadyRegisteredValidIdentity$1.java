package com.reown.android.keyserver.domain;

import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.keyserver.domain.IdentitiesInteractor", f = "IdentitiesInteractor.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {76}, m = "getAlreadyRegisteredValidIdentity-cBM7WSQ", n = {"accountId", "statement", "domain", "resources", "$this$getAlreadyRegisteredValidIdentity_cBM7WSQ_u24lambda_u242", "storedPublicKey", "$i$a$-runCatching-IdentitiesInteractor$getAlreadyRegisteredValidIdentity$2"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0"})
public final class IdentitiesInteractor$getAlreadyRegisteredValidIdentity$1 extends ContinuationImpl {
    int I$0;
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
    public IdentitiesInteractor$getAlreadyRegisteredValidIdentity$1(IdentitiesInteractor identitiesInteractor, Continuation<? super IdentitiesInteractor$getAlreadyRegisteredValidIdentity$1> continuation) {
        super(continuation);
        this.this$0 = identitiesInteractor;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r6 = this.this$0.m8831getAlreadyRegisteredValidIdentitycBM7WSQ((String) null, (String) null, (String) null, (List<String>) null, this);
        return r6 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r6 : Result.m8978boximpl(r6);
    }
}
