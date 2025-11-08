package com.reown.android.verify.domain;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.verify.domain.VerifyRepository", f = "VerifyRepository.kt", i = {}, l = {102}, m = "fetchAndCacheKey", n = {}, s = {})
public final class VerifyRepository$fetchAndCacheKey$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ VerifyRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VerifyRepository$fetchAndCacheKey$1(VerifyRepository verifyRepository, Continuation<? super VerifyRepository$fetchAndCacheKey$1> continuation) {
        super(continuation);
        this.this$0 = verifyRepository;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.fetchAndCacheKey(this);
    }
}
