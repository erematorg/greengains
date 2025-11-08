package com.reown.android.verify.domain;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.verify.domain.VerifyRepository", f = "VerifyRepository.kt", i = {0, 0, 1, 1, 1, 1, 1, 1, 1}, l = {140, 29}, m = "getVerifyPublicKey-IoAF18A", n = {"$this$withLock_u24default$iv", "$i$f$withLock", "$this$withLock_u24default$iv", "$this$getVerifyPublicKey_IoAF18A_u24lambda_u241_u24lambda_u240", "localPublicKey", "expiresAt", "$i$f$withLock", "$i$a$-withLock$default-VerifyRepository$getVerifyPublicKey$2", "$i$a$-runCatching-VerifyRepository$getVerifyPublicKey$2$1"}, s = {"L$0", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0", "I$1", "I$2"})
public final class VerifyRepository$getVerifyPublicKey$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ VerifyRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VerifyRepository$getVerifyPublicKey$1(VerifyRepository verifyRepository, Continuation<? super VerifyRepository$getVerifyPublicKey$1> continuation) {
        super(continuation);
        this.this$0 = verifyRepository;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r12 = this.this$0.m8846getVerifyPublicKeyIoAF18A(this);
        return r12 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r12 : Result.m8978boximpl(r12);
    }
}
