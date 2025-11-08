package com.airbnb.lottie.compose;

import com.airbnb.lottie.LottieListener;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "T", "e", "", "kotlin.jvm.PlatformType", "onResult"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class RememberLottieCompositionKt$await$2$2<T> implements LottieListener {
    final /* synthetic */ CancellableContinuation<T> $cont;

    public RememberLottieCompositionKt$await$2$2(CancellableContinuation<? super T> cancellableContinuation) {
        this.$cont = cancellableContinuation;
    }

    public final void onResult(Throwable th) {
        if (!this.$cont.isCompleted()) {
            CancellableContinuation<T> cancellableContinuation = this.$cont;
            Result.Companion companion = Result.Companion;
            Intrinsics.checkNotNull(th);
            cancellableContinuation.resumeWith(Result.m8979constructorimpl(ResultKt.createFailure(th)));
        }
    }
}
