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
@DebugMetadata(c = "com.reown.android.keyserver.domain.IdentitiesInteractor", f = "IdentitiesInteractor.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {151, 152}, m = "registerIdentityKeyInKeyserver-uJKfU3c", n = {"accountId", "identityKey", "statement", "domain", "resources", "onSign", "cacao", "accountId", "identityKey", "statement", "domain", "resources", "onSign", "cacao", "it", "$i$a$-onSuccess-IdentitiesInteractor$registerIdentityKeyInKeyserver$2"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8", "I$0"})
public final class IdentitiesInteractor$registerIdentityKeyInKeyserver$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ IdentitiesInteractor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public IdentitiesInteractor$registerIdentityKeyInKeyserver$1(IdentitiesInteractor identitiesInteractor, Continuation<? super IdentitiesInteractor$registerIdentityKeyInKeyserver$1> continuation) {
        super(continuation);
        this.this$0 = identitiesInteractor;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r8 = this.this$0.m8822registerIdentityKeyInKeyserveruJKfU3c((String) null, (String) null, (String) null, (String) null, (List<String>) null, (Function1<? super String, Cacao.Signature>) null, this);
        return r8 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r8 : Result.m8978boximpl(r8);
    }
}
